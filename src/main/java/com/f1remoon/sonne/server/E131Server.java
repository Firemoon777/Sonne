package com.f1remoon.sonne.server;

import com.f1remoon.sonne.e131.E131Packet;
import com.f1remoon.sonne.e131.E131Socket;
import com.f1remoon.sonne.entity.DMXObject;
import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E131Server extends Thread {

    final private E131Socket socket;

    private Boolean interrupted = false;
    private Integer universeStart = 1;
    private Integer universeCount = 1;

    public E131Server() throws SocketException {
        this(E131Socket.E131_DEFAULT_PORT);
    }

    public E131Server(Integer port) throws SocketException {
        this("0.0.0.0", port);
    }

    public E131Server(String address, Integer port) throws SocketException {
        this.socket = new E131Socket(address, port);
        Bukkit.getLogger().info(String.format("Running E1.31 server on %s:%d", address, port));
    }

    private Byte currentSequenceNumber = 0;

    private ByteBuffer buildDataFromAllUniverses(List<E131Packet> packets) {
        // Sort all packets by dmx universe
        Collections.sort(packets);
        // Allocate buffer for data in case each packet contains 512 bytes
        byte[] buffer = new byte[512*packets.size()];

        Integer offset = 0;
        for(E131Packet packet : packets) {
            // Ignore first byte of each DMX Packet.
            System.arraycopy(packet.dmp.prop_val, 1, buffer, offset, packet.dmp.prop_val_cnt - 1);
            offset += packet.dmp.prop_val_cnt - 1;
        }
        return ByteBuffer.wrap(buffer);
    }

    @Override
    public void run() {
        super.run();
        List<E131Packet> packetList = new ArrayList<>();

        // Read first packet
        E131Packet packet = null;
        try {
            packet = this.socket.receive();
            packetList.add(packet);
            // Init sequence number
            currentSequenceNumber = packet.frame.seq_number;
        } catch (IOException e) {
            Bukkit.getLogger().severe("Cannot init seq_number");
            e.printStackTrace();
            return;
        }

        while (!interrupted) {
            try {
                if(currentSequenceNumber != packet.frame.seq_number) {
                    // Packet from different sequence. Maybe bug or packet loss. Just drop previous sequence
                    packetList.clear();
                    // set correct number for current sequence
                    currentSequenceNumber = packet.frame.seq_number;
                }
                packetList.add(packet);

                if(packetList.size() == universeCount) {
                    // Building byte[] from all universes
                    ByteBuffer data = this.buildDataFromAllUniverses(packetList);

                    // Pass bytes to DMX objects
                    for (DMXObject o : DMXStorage.getInstance().getObjects()) {
                        byte[] dmx = new byte[o.getChannelCount()];
                        data.get(dmx, 0, o.getChannelCount());
                        o.apply(dmx);
                    }

                    // Received all universes from sequence. Waiting for next sequence...
                    currentSequenceNumber++;

                    // Clearing packets list
                    packetList.clear();
                }

                packet = this.socket.receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.socket.close();
    }

    @Override
    public void interrupt() {
        super.interrupt();
        this.interrupted = true;
    }

    public Integer getUniverseStart() {
        return universeStart;
    }

    public void setUniverseStart(Integer universeStart) {
        this.universeStart = universeStart;
    }

    public Integer getUniverseCount() {
        return universeCount;
    }

    public void setUniverseCount(Integer universeCount) {
        this.universeCount = universeCount;
    }
}
