package com.readutf.uLib.libraries;

import lombok.Getter;

public enum  SpigotLine {


    CHAT("-----------------------------------------------------"),
    SCOREBOARD("--------------------");

    @Getter private String text;

    SpigotLine(String text) {
        this.text = text;
    }
}
