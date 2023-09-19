package com.qqhouse.dungeon18plus.gamedata;

//import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.utils.Array;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

abstract class DataCore {
	
	private static final int DEFAULT_ID = 0;
	
	static abstract class DataPart {
		final int id;
		
		DataPart() {
			this.id = DEFAULT_ID;
		}
		
		DataPart(int id) {
			this.id = id;
		}
		
		byte[] doWrite(ByteBuffer buffer) {
			buffer.flip();
			byte[] b = new byte[buffer.limit()];
			buffer.get(b);
			return b;
		}
		
		
		// should not call this.
		byte[] write() {
			throw new RuntimeException("should not call this.");
		}
		// default is do nothing.
		void read(byte[] data) {
			throw new RuntimeException("should not call this.");
		}
		// add some data for debug use, default is do nothing.
		void afterRead() {}
	}
	
	/*
	 * marker
	 */
	// do not load.
	interface DoNotLoadThis {}
	// do not save.
	interface DoNotSaveThis {}
	
	private final Array<DataPart> mData = new Array<>();

	/*
	 * load data
	 */
	abstract void afterLoad();

	public void load() {}

	/*synchronized public void load(Context context, String fileName) {
		try {
			BufferedInputStream bis = new BufferedInputStream(context.openFileInput(fileNameGame.GAME_DATA_FILE_NAME));

			try {
				doLoad(bis);
			} finally {
				bis.close();
			}
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch(IOException e) {
	    	e.printStackTrace();
	    }
	    afterLoad();
	}*/

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

	synchronized public boolean save() {return true;}

	/*synchronized public boolean save(Context context, String fileName) {
		boolean success = true;
		try {
			BufferedOutputStream bos = new BufferedOutputStream(context.openFileOutput(fileName/*Game.GAME_DATA_FILE_NAME*//*, Context.MODE_PRIVATE));
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
	}*/
	
	private void doSave(BufferedOutputStream bos) throws IOException {
		
		ByteBuffer buffer = ByteBuffer.allocate(8192 * 2);
		for (int i = 0; i < mData.size; ++i) {
			DataPart part = mData.get(i);
			// skip legacy.
			if (part instanceof DoNotSaveThis)
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

	/*
	 * add data part
	 */
	void addDataPart(DataPart part) {
		if (DEFAULT_ID == part.id)
			throw new RuntimeException(String.format("%s with invalid id ! change to : %08X", part.getClass().getSimpleName(), new Random().nextInt()));
		if (null != mData.get(part.id))
			throw new RuntimeException(String.format("%s with duplicate id ! change to : %08X", part.getClass().getSimpleName(), new Random().nextInt()));
		//mData.add(part.id, part);
	}
	
	DataPart find(int key) {
		return mData.get(key);
	}
	
}
