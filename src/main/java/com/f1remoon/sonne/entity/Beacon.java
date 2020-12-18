package com.f1remoon.sonne.entity;

import com.f1remoon.sonne.util.Colors;
import org.bukkit.Location;

public class Beacon extends DMXObject {

    private Location beacon;

    public Beacon(Integer universe, Integer startChannel, Location l) {
        super(universe, startChannel, 3);
        this.beacon = l;
    }

    @Override
    public void apply(byte[] dmxData) {
        super.apply(dmxData);
        String color = Colors.findColor(dmxData);
    }
}
