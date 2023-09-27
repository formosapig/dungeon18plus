package com.qqhouse.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class QQSaveGame {

    private String fileName;
    private static final int DEFAULT_ID = 0;
    private final Map<Integer, DataPart> mData;


    public QQSaveGame(String fileName) {
        this.fileName = fileName;
        mData = new HashMap<Integer, DataPart>();
        prepareData();
        newGame();
    }

    protected abstract void prepareData();
    protected abstract void newGame();
    protected abstract void afterLoad();



    public final void load() {
		try {
            // will throw GdxRuntimeException : file not found...
            FileHandle file = Gdx.files.local(fileName);
            BufferedInputStream bis = new BufferedInputStream(file.read());

            try {
                doLoad(bis);
            } finally {
                bis.close();
            }
        } catch (GdxRuntimeException e) {
            e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
        afterLoad();
    }

    private void doLoad(BufferedInputStream bis) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        while (0 < bis.available()) {
            int readNum = bis.read(buf, 0 , 1024);
            if (-1 != readNum) {
                stream.write(buf, 0, readNum);
            }
        }

        ByteBuffer buffer = ByteBuffer.wrap(stream.toByteArray());

        while (buffer.hasRemaining()) {
            int id = buffer.getInt();
            int size = buffer.getInt();

            if (0 == size)
                continue;

            byte[] data = new byte[size];
            //     0323 在選角色的地方滑上滑下一直滑來滑去突然跳出 BufferUnderflowException. 需要再查查看怎麼回事.
            //     0407 先將 load 加上 synchronized , 有可能是連續執行兩次的關係?
            // XXX 0425 再確認吧.
            buffer.get(data);
            DataPart part = mData.get(id);
            if (null != part && !(part instanceof DoNotLoadThis)) {
                part.read(data);
                part.afterRead();
            }
        }

    }

    public final boolean save() {
    	boolean success = true;
		try {
            FileHandle file = Gdx.files.local(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(file.write(false));
			try {
				doSave(bos);
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
			} finally {
				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

    private void doSave(BufferedOutputStream bos) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(8192 * 2);
        for (DataPart part : mData.values()) {
            // skip legacy.
            if (part instanceof  DoNotSaveThis)
                continue;

            byte[] data = part.write();
            if (null != data && 0 < data.length) {
                buffer.putInt(part.id);
                buffer.putInt(data.length);
                buffer.put(data);
            }
        }
        buffer.flip();
        bos.write(buffer.array(), 0, buffer.limit());
        bos.flush();
    }

    public static abstract class DataPart {
        final int id;

        public DataPart() {
            this.id = DEFAULT_ID;
        }

        public DataPart(int id) {
            this.id = id;
        }

        protected byte[] doWrite(ByteBuffer buffer) {
            buffer.flip();
            byte[] b = new byte[buffer.limit()];
            buffer.get(b);
            return b;
        }


        // should not call this.
        protected byte[] write() {
            throw new RuntimeException("should not call this.");
        }
        // default is do nothing.
        protected void read(byte[] data) {
            throw new RuntimeException("should not call this.");
        }
        // add some data for debug use, default is do nothing.
        protected void afterRead() {}
    }

    /*
     * marker
     */
    // do not load.
    interface DoNotLoadThis {}
    // do not save.
    interface DoNotSaveThis {}


    /*
     * add data part
     */
    protected void addDataPart(DataPart part) {
        if (DEFAULT_ID == part.id)
            throw new RuntimeException(String.format("%s with invalid id ! change to : %08X", part.getClass().getSimpleName(), new Random().nextInt()));
        if (null != mData.get(part.id))
            throw new RuntimeException(String.format("%s with duplicate id ! change to : %08X", part.getClass().getSimpleName(), new Random().nextInt()));
        mData.put(part.id, part);
    }

    protected DataPart find(int key) {
        return mData.get(key);
    }

}
