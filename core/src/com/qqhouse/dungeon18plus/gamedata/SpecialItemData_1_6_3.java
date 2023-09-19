package com.qqhouse.dungeon18plus.gamedata;

import java.nio.ByteBuffer;

class SpecialItemData_1_6_3 extends DataCore.DataPart {

    /*
     * loot code, count pair.
     */

    /*final intArray specialItems;*/

    SpecialItemData_1_6_3() {
        super(0x52E99F1D);
        //specialItems = new SparseIntArray();
    }

    @Override
    byte[] write() {
        ByteBuffer buffer = ByteBuffer.allocate(256);	// 8 bytes * 32 種 ...
        //for (int i = 0, s = specialItems.size(); i < s; ++i) {
        //    buffer.putInt(specialItems.keyAt(i));
        //    buffer.putInt(specialItems.valueAt(i));
        //}
        return doWrite(buffer);
    }

    @Override
    void read(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        //specialItems.clear();
        while (buffer.hasRemaining()) {
            final int lootCode = buffer.getInt();
            final int count = buffer.getInt();
            //specialItems.put(lootCode, count);
        }
    }
}
