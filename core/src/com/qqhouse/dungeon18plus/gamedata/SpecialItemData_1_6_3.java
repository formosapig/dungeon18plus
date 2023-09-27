package com.qqhouse.dungeon18plus.gamedata;

import com.qqhouse.io.QQSaveGame;

import java.nio.ByteBuffer;

class SpecialItemData_1_6_3 extends QQSaveGame.DataPart {

    /*
     * loot code, count pair.
     */

    /*final intArray specialItems;*/

    SpecialItemData_1_6_3() {
        super(0x1405D51A);
        //specialItems = new SparseIntArray();
    }

    @Override
    public byte[] write() {
        ByteBuffer buffer = ByteBuffer.allocate(256);	// 8 bytes * 32 種 ...
        //for (int i = 0, s = specialItems.size(); i < s; ++i) {
        //    buffer.putInt(specialItems.keyAt(i));
        //    buffer.putInt(specialItems.valueAt(i));
        //}
        return doWrite(buffer);
    }

    @Override
    public void read(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        //specialItems.clear();
        while (buffer.hasRemaining()) {
            final int lootCode = buffer.getInt();
            final int count = buffer.getInt();
            //specialItems.put(lootCode, count);
        }
    }
}
