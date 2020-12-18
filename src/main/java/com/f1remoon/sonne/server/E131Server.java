package com.f1remoon.sonne.server;

import com.f1remoon.sonne.e131.E131Packet;
import com.f1remoon.sonne.e131.E131Socket;
import com.f1remoon.sonne.entity.DMXObject;
import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

public class E131Server extends Thread {

    final private E131Socket socket;

    private Boolean interrupted = false;

    public E131Server() throws SocketException {
        this(E131Socket.E131_DEFAULT_PORT);
    }

    public E131Server(Integer port) throws SocketException {
        this.socket = new E131Socket(port);
    }

    @Override
    public void run() {
        super.run();
        while (!interrupted) {
            try {
                E131Packet packet = this.socket.receive();
                Integer i = 1;
                for (DMXObject o : DMXStorage.getInstance().getObjects()) {
                    o.apply(Arrays.copyOfRange(packet.dmp.prop_val, i, i + o.getChannelCount()));
                    i += o.getChannelCount();
                }
            } catch (IOException e) {
                if(!this.socket.isClosed()) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        this.interrupted = true;
        this.socket.close();
        Bukkit.getLogger().info("Socket closed");
    }
}
