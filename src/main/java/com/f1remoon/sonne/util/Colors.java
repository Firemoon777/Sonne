package com.f1remoon.sonne.util;

import org.bukkit.Bukkit;

import java.util.HashMap;

public class Colors {

    final static private byte[] WHITE = {(byte)0xFF, (byte)0xFF, (byte)0xFF};
    final static private byte[] RED = {(byte)0xFF, 0x00, 0x00};
    final static private byte[] ORANGE = {(byte)0xD8, 0x7F, 0x33};
    final static private byte[] PINK = {(byte)0xF2, 0x7F, (byte)0xA5};
    final static private byte[] YELLOW = {(byte)0xE5, (byte)0xE5, 0x33};
    final static private byte[] LIME = {0x7F, (byte)0xCC, 0x19};
    final static private byte[] GREEN = {0x00, (byte)0xFF, 0x00};
    final static private byte[] LIGHT_BLUE = {0x66, (byte)0x99, (byte)0xD8};
    final static private byte[] CYAN = {0x4C, 0x7F, (byte)0x99};
    final static private byte[] BLUE = {0x00, 0x00, (byte)0xFF};
    final static private byte[] MAGENTA = {(byte)0xB2, 0x4D, (byte)0xC8};
    final static private byte[] PURPLE = {0x7F, 0x3F, (byte)0xB2};
    final static private byte[] BROWN = {0x66, 0x4C, 0x33};
    final static private byte[] GRAY = {0x4C, 0x4C, 0x4C};
    final static private byte[] LIGHT_GRAY = {(byte)0x99, (byte)0x99, (byte)0x99};
    final static private byte[] BLACK = {0x00, 0x00, 0x00};

    final static private HashMap<String, byte[]> COLORS = new HashMap<String, byte[]>() {{
        put("WHITE", WHITE);
        put("RED", RED);
        put("ORANGE", ORANGE);
        put("PINK", PINK);
        put("YELLOW", YELLOW);
        put("LIME", LIME);
        put("GREEN", GREEN);
        put("LIGHT_BLUE", LIGHT_BLUE);
        put("CYAN", CYAN);
        put("BLUE", BLUE);
        put("MAGENTA", MAGENTA);
        put("PURPLE", PURPLE);
        put("BROWN", BROWN);
        put("GRAY", GRAY);
        put("LIGHT_GRAY", LIGHT_GRAY);
        put("BLACK", BLACK);
    }};

    private double distance(byte[] a, byte[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2) + Math.pow(a[2] - b[2], 2));
    }

    private String findColor(byte[] rgb) {
        if(rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0) {
            return null;
        }
        String result = "";
        double d = 100000000;
        for(String key : COLORS.keySet()) {
            Bukkit.getLogger().info(String.format("%02x %02x %02x ~ %02x %02x %02x -> %.6f",
                    rgb[0], rgb[1], rgb[2], COLORS.get(key)[0], COLORS.get(key)[1], COLORS.get(key)[2],
                    distance(COLORS.get(key), rgb)));
            if(d > distance(COLORS.get(key), rgb)) {
                d = distance(COLORS.get(key), rgb);
                result = key;
            }
        }
        return result.toLowerCase();
    }
}
