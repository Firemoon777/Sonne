package com.f1remoon.sonne.e131;

import com.f1remoon.sonne.e131.layer.ACNRootLayer;
import com.f1remoon.sonne.e131.layer.DeviceManagementProtocolLayer;
import com.f1remoon.sonne.e131.layer.FramingLayer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class E131Packet implements Comparable<E131Packet> {
    /* E1.31 Private Constants */
    private final static Integer _E131_ROOT_VECTOR = 0x00000004;
    private final static Integer _E131_FRAME_VECTOR = 0x00000002;
    private final static Integer _E131_DMP_VECTOR = 0x02;
    private final static Integer _E131_DMP_TYPE = 0xa1;
    private final static Integer _E131_DMP_FIRST_ADDR = 0x0000;
    private final static Integer _E131_DMP_ADDR_INC = 0x0001;

    public E131Packet() {
        this.root = new ACNRootLayer();
        this.frame = new FramingLayer();
        this.dmp = new DeviceManagementProtocolLayer();
    }

    public E131Packet(byte[] data) throws IOException {
        this();

        ByteBuffer buffer = ByteBuffer.wrap(data);
        this.root.preamble_size = buffer.getShort();
        this.root.postamble_size = buffer.getShort();
        buffer.get(this.root.acn_pid, 0, 12);
        this.root.flength = buffer.getShort();
        this.root.vector = buffer.getInt();
        buffer.get(this.root.cid, 0, 16);

        this.frame.flength = buffer.getShort();
        this.frame.vector = buffer.getInt();
        buffer.get(this.frame.source_name, 0, 64);
        this.frame.priority = buffer.get();
        this.frame.reserved = buffer.getShort();
        this.frame.seq_number = buffer.get();
        this.frame.options = buffer.get();
        this.frame.universe = buffer.getShort();

        this.dmp.flength = buffer.getShort();
        this.dmp.vector = buffer.get();
        this.dmp.type = buffer.get();
        this.dmp.first_addr = buffer.getShort();
        this.dmp.addr_inc = buffer.getShort();
        this.dmp.prop_val_cnt = buffer.getShort();
        buffer.get(this.dmp.prop_val, 0, this.dmp.prop_val_cnt);
    }

    /* ACN Root Layer: 38 bytes */
    public ACNRootLayer root;

    /* Framing Layer: 77 bytes */
    public FramingLayer frame;

    /* Device Management Protocol (DMP) Layer: 523 bytes */
    public DeviceManagementProtocolLayer dmp;

    @Override
    public String toString() {
        return this.root.toString() + this.frame.toString() + this.dmp.toString();
    }

    @Override
    public int compareTo(E131Packet e131Packet) {
        if(this.frame.seq_number < e131Packet.frame.seq_number) {
            return -1;
        }
        if(this.frame.seq_number > e131Packet.frame.seq_number) {
            return 1;
        }
        return Short.compare(this.frame.universe, e131Packet.frame.universe);
    }
}
