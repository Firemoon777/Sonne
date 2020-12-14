package com.f1remoon.sonne.e131.layer;

public class DeviceManagementProtocolLayer {
    public short flength;          /* Flags (high 4 bits) / Length (low 12 bits) */
    public byte  vector;           /* Layer Vector */
    public byte  type;             /* Address Type & Data Type */
    public short first_addr;       /* First Property Address */
    public short addr_inc;         /* Address Increment */
    public short prop_val_cnt;     /* Property Value Count (1 + number of slots) */
    public byte[]  prop_val = new byte[513];    /* Property Values (DMX start code + slots data) */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("[Device Management Protocol (DMP) Layer]\n" +
                        "  Flags & Length ......... %d\n" +
                        "  Layer Vector ........... %d\n" +
                        "  Address & Data Type .... %s\n" +
                        "  First Address .......... %d\n" +
                        "  Address Increment ...... %d\n" +
                        "  Property Value Count ... %d\n" +
                        "[DMP Property Values]\n",
                this.flength,
                this.vector,
                this.type,
                this.first_addr,
                this.addr_inc,
                this.prop_val_cnt
        ));

        for(int i = 0; i < prop_val.length; i++) {
            builder.append(String.format("%02x ", this.prop_val[i]));
        }
        return builder.toString();
    }
}
