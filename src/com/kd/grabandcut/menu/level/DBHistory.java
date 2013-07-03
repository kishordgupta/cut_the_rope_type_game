package com.kd.grabandcut.menu.level;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHistory {
	private static final String DATABASE_CREATE = "create table if not exists GAME_HISTORY("
			+ "_id integer primary key autoincrement, "
			+ "levelId integer,"
			+ "status integer not null,"
			+ "starNumber integer not null,"
			+ "score integer not null" + ");";

	private static final String DATABASE_NAME = "ROPE_DB";
	private static final String DATABASE_TABLE = "GAME_HISTORY";

	private int lvl_num = 18;

	private SQLiteDatabase db;
	private Context ctx;

	public DBHistory(Context ctx) {
		// System.out.println("Creating Database...");
		this.ctx = ctx;
		try {
			if (db == null)
				db = openDB();
			createDB(db);
		} catch (Exception e) {
		}
	}

	public SQLiteDatabase openDB() {
		int i = 10;
		while (i-- > 0) {
			try {
				// System.out.println("Opening database");
				return ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return null;
	}

	public void createDB(SQLiteDatabase dbl) {
		try {
			dbl.execSQL(DATABASE_CREATE);
			// System.out.println("Database created");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void close() {
		try {
			if (db != null) {
				db.close();
				db = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// dbh.createRow()
	// first insert 18 levels info

	public void createRow() {

		ArrayList<GameHistory> rows = fetchAllRows();
		if (rows.size() > 0)
			return;

		try {
			if (db == null) {
				db = openDB();
			}
			for (int i = 1; i <= lvl_num; i++) {
				ContentValues vals = new ContentValues();
				vals.put("levelId", i);
				if (i == 1)
					vals.put("status", 1);
				else
					vals.put("status", 0);
				vals.put("starNumber", 0);
				vals.put("score", 0);
				db.insert(DATABASE_TABLE, null, vals);
				System.out.println("ROw created " + i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void resetTable() {

		// int lvl=18;

		try {
			if (db == null)
				db = openDB();
			for (int levelId = 1; levelId <= lvl_num; levelId++) {
				deleteRow(levelId);
			}

			createRow();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void deleteRow(int levelId) {

		try {
			if (db == null)
				db = openDB();
			db.delete(DATABASE_TABLE, "levelId=" + "'" + levelId + "'", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public ArrayList<GameHistory> fetchAllRows() {

		Cursor c = null;
		ArrayList<GameHistory> ret = new ArrayList<GameHistory>();
		try {
			if (db == null)
				db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
			c = db.query(DATABASE_TABLE, new String[] { "levelId", "status",
					"starNumber", "score" }, null, null, null, null, null);
			int numRows = c.getCount();
			c.moveToFirst();
			for (int i = 0; i < numRows; ++i) {
				GameHistory row = new GameHistory(c.getInt(0), c.getInt(1), c
						.getInt(2), c.getInt(3));
				ret.add(row);
				c.moveToNext();
			}
		} catch (Exception e) {
			Log.e("Exception on query", e.toString());
		} finally {
			this.close();
			if (c != null)
				c.close();
		}
		return ret;
	}

	public void updatelevel(GameHistory gs) {

		try {
			if (db == null)
				db = openDB();
			ContentValues vals = new ContentValues();
			vals.put("levelId", gs.levelId);
			vals.put("status", gs.status);
			vals.put("starNumber", gs.starNumber);
			vals.put("score", gs.score);

			db.update(DATABASE_TABLE, vals,
					"levelId=" + "'" + gs.levelId + "'", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void updateStatus(GameHistory gs) {

		try {
			if (db == null)
				db = openDB();
			int nextLevel = gs.levelId;

			String update_status;
			if (nextLevel < 18)
				update_status = "update " + DATABASE_TABLE
						+ " set status=1 where levelId=" + (nextLevel + 1)
						+ ";";
			else
				update_status = "update " + DATABASE_TABLE
						+ " set status=1 where levelId=" + nextLevel + ";";
			db.execSQL(update_status);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

}