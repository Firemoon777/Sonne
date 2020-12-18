package com.f1remoon.sonne.entity;

import java.io.Serializable;

public class DMXObject implements Serializable {
    private String name;
    private Integer universe;
    private Integer startChannel;
    private Integer channelCount;

    public DMXObject() {

    }

    public DMXObject(Integer universe, Integer startChannel, Integer channelCount) {
        this.universe = universe;
        this.startChannel = startChannel;
        this.channelCount = channelCount;
    }

    public void apply(byte[] dmxData) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUniverse() {
        return universe;
    }

    public void setUniverse(Integer universe) {
        this.universe = universe;
    }

    public Integer getStartChannel() {
        return startChannel;
    }

    public void setStartChannel(Integer startChannel) {
        this.startChannel = startChannel;
    }

    public Integer getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Integer channelCount) {
        this.channelCount = channelCount;
    }
}
