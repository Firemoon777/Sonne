package com.f1remoon.sonne.e131.layer;

public class ACNRootLayer {
    private final static short _E131_PREAMBLE_SIZE = 0x0010;
    private final static short _E131_POSTAMBLE_SIZE = 0x0000;
    private final static byte[] _E131_ACN_PID = {0x41, 0x53, 0x43, 0x2d, 0x45, 0x31, 0x2e, 0x31, 0x37, 0x00, 0x00, 0x00};

    public short preamble_size = _E131_PREAMBLE_SIZE;   /* Preamble Size */
    public short postamble_size = _E131_POSTAMBLE_SIZE; /* Post-amble Size */
    public byte[] acn_pid = _E131_ACN_PID;             /* ACN Packet Identifier */
    public short flength;                               /* Flags (high 4 bits) & Length (low 12 bits) */
    public int vector;                                /* Layer Vector */
    public byte[] cid = new byte[16];               /* Component Identifier (UUID) */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("[Root Layer]\n" +
                        "  Preamble Size .......... %d\n" +
                        "  Post-amble Size ........ %d\n" +
                        "  ACN Packet Identifier .. %s\n" +
                        "  Flags & Length ......... %d\n" +
                        "  Layer Vector ........... %d\n" +
                        "  Component Identifier ... ",
                this.preamble_size,
                this.postamble_size,
                new String(this.acn_pid),
                this.flength,
                this.vector
        ));
        for (byte b : cid) {
            builder.append(String.format("%02x", b));
        }
        builder.append("\n");
        return builder.toString();
    }
}
