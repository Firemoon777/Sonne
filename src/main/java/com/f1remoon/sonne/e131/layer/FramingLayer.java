package com.f1remoon.sonne.e131.layer;

public class FramingLayer {
    public short flength;          /* Flags (high 4 bits) & Length (low 12 bits) */
    public int vector;           /* Layer Vector */
    public byte[]  source_name = new byte[64];  /* User Assigned Name of Source (UTF-8) */
    public byte  priority;         /* Packet Priority (0-200, default 100) */
    public short reserved;         /* Reserved (should be always 0) */
    public byte  seq_number;       /* Sequence Number (detect duplicates or out of order packets) */
    public byte  options;          /* Options Flags (bit 7: preview data, bit 6: stream terminated) */
    public short universe;         /* DMX Universe Number */

    @Override
    public String toString() {
        return String.format("[Framing Layer]\n" +
                        "  Flags & Length ......... %d\n" +
                        "  Layer Vector ........... %d\n" +
                        "  Source Name ............ %s\n" +
                        "  Packet Priority ........ %d\n" +
                        "  Reserved ............... %d\n" +
                        "  Sequence Number ........ %d\n" +
                        "  Options Flags .......... %d\n" +
                        "  DMX Universe Number .... %d\n",
                this.flength,
                this.vector,
                new String(this.source_name),
                this.priority,
                this.reserved,
                this.seq_number,
                this.options,
                this.universe
        );
    }
}
