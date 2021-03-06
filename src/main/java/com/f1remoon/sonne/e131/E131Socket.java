package com.f1remoon.sonne.e131;

import java.io.IOException;
import java.net.*;

public class E131Socket {
    /* E1.31 Public Constants */
    public final static Integer E131_DEFAULT_PORT = 5568;
    public final static Integer E131_DEFAULT_PRIORITY = 0x64;

    final private DatagramSocket socket;

    public E131Socket(String address, Integer port) throws SocketException {
        InetSocketAddress addr = new InetSocketAddress(address, port);
        this.socket = new DatagramSocket(addr);
    }

    public E131Socket(Integer port) throws SocketException {
        this("0.0.0.0", port);
    }

    public E131Packet receive() throws IOException {
        byte[] buf = new byte[638];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        return new E131Packet(packet.getData());
    }

    public void close() {
        this.socket.close();
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }
}
