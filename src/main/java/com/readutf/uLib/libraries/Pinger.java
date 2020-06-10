package com.readutf.uLib.libraries;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Pinger {

    public static Thread thread = null;

    public static boolean checkOnline(final String adress, final int port) {
        boolean online = false;
        try {
            final Socket s = new Socket();
            s.connect(new InetSocketAddress(adress, port), 15);
            s.close();
            online = true;
        }
        catch (UnknownHostException ex) {}
        catch (IOException ex2) {}
        return online;
    }


}
