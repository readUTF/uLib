package com.readutf.uLib.libraries;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BungeeUtil implements PluginMessageListener {

    public int globalCount;
    @Getter HashMap<String, Integer> serverCounts = new HashMap<String, Integer>();
    @Getter List<String> activeServers = new ArrayList<String>();

    HashMap<String, String> serverIps = new HashMap<String, String>();
    @Getter HashMap<String, Boolean> serverStatus = new HashMap<String, Boolean>();

    JavaPlugin main;

    public BungeeUtil(JavaPlugin main) {
        this.main = main;
        main.getServer().getMessenger().registerOutgoingPluginChannel(main, "BungeeCord");
        main.getServer().getMessenger().registerIncomingPluginChannel(main, "BungeeCord", this);


        startActiveServerTask();
        startServerCountTask();
        startStatusTask();
    }


    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("GetServers")) {
            activeServers = Arrays.asList(in.readUTF().split(", "));
        } else if(subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            int playercount = in.readInt();
            if(server.equalsIgnoreCase("ALL")) {
                globalCount = playercount;
                return;
            }
            serverCounts.put(server, playercount);
        } else if(subchannel.equalsIgnoreCase("ServerIP")) {
            String server = in.readUTF();
            String ip = in.readUTF();
            int port = in.readUnsignedShort();
            serverIps.put(server, ip + ":" + port);
        }
    }

    public int getCount(String server) {
        if(activeServers.contains(server) && serverCounts.containsKey(server)) {
            return serverCounts.get(server);
        }
        return -1;
    }

    public void startServerCountTask() {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(Players.getOnlinePlayers().size() < 1) {
                    return;
                }

                for(String s : activeServers) {

                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("PlayerCount");
                    out.writeUTF(s);

                    Player player = Players.getOnlinePlayers().iterator().next();
                    player.sendPluginMessage(main, "BungeeCord", out.toByteArray());

                    out = ByteStreams.newDataOutput();
                    out.writeUTF("ServerIP");
                    out.writeUTF(s);
                    player = Players.getOnlinePlayers().iterator().next();
                    player.sendPluginMessage(main, "BungeeCord", out.toByteArray());


                }
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("PlayerCount");
                out.writeUTF("ALL");

                Player player = Players.getOnlinePlayers().iterator().next();
                player.sendPluginMessage(main, "BungeeCord", out.toByteArray());

            }
        }.runTaskTimer(main, 0L, 20L);
    }

    public void sendTo(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(player.getName());
        out.writeUTF(server);
        player = Players.getOnlinePlayers().iterator().next();
        player.sendPluginMessage(main, "BungeeCord", out.toByteArray());


    }

    public void startStatusTask() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(String server : serverIps.keySet()) {
                    String ip = serverIps.get(server);
                    String address = ip.split(":")[0];
                    int port = Integer.parseInt(ip.split(":")[1]);

                    serverStatus.put(server, Pinger.checkOnline(address, port));
                }
            }
        }.runTaskTimer(main, 0L, 20 * 5L);
    }

    public boolean checkStatus(String server) {
        if(!serverStatus.containsKey(server)) {
            return false;
        }
        return serverStatus.get(server);
    }

    public void startActiveServerTask() {

        new BukkitRunnable() {

            @Override
            public void run() {

                if(Players.getOnlinePlayers().size() < 1) {
                    return;
                }
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("GetServers");

                Player player = Players.getOnlinePlayers().iterator().next();
                player.sendPluginMessage(main, "BungeeCord", out.toByteArray());


            }
        }.runTaskTimer(main, 0, 20 * 10);

    }

}
