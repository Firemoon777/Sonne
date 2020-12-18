package com.f1remoon.sonne.entity;

import com.f1remoon.sonne.effect.FireLine;
import org.bukkit.Location;

public class FireDispenser extends DMXObject {

    private Location location;

    public FireDispenser() {}

    public FireDispenser(Location l) {
        this.setChannelCount(2);
        this.location = new Location(l.getWorld(), l.getBlockX(), l.getBlockY() + 1, l.getBlockZ());
    }

    @Override
    public void apply(byte[] dmxData) {
        super.apply(dmxData);
        if(dmxData[0] == 0 || dmxData[1] == 0) {
            return;
        }
        FireLine.burn(location, dmxData[0], dmxData[1]);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
