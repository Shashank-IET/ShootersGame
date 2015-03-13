package games.andoid.shooter_ver5_0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseAdapter {

	private myHelper DB_helper;

	public DatabaseAdapter(Context C) {
		// TODO Auto-generated constructor stub
		DB_helper = new myHelper(C);
	}

	public boolean addPayerScore(String name, int score) {

		ContentValues vals = new ContentValues();
		SQLiteDatabase db = DB_helper.getWritableDatabase();

		vals.put(myHelper.COL_NAME, name);
		vals.put(myHelper.COL_SCORE, score);

		long id = db.insert(myHelper.TABLE_NAME, null, vals);

		if (id > 0)
			return true;
		else
			return false;
	}

	public Cursor getAllData() {

		String[] colArray = { myHelper.COL_ID, myHelper.COL_NAME,
				myHelper.COL_SCORE };

		SQLiteDatabase db = DB_helper.getWritableDatabase();
		Cursor cs = db.query(myHelper.TABLE_NAME, colArray, null, null, null,
				null, myHelper.COL_SCORE + " desc");

		return cs;
	}

	static class myHelper extends SQLiteOpenHelper {

		private static final String dbname = "ShooterDatabase";
		private static final int dbVer = 1;
		private static final String TABLE_NAME = "GameData";
		private static final String COL_ID = "_id";
		private static final String COL_NAME = "NAME";
		private static final String COL_SCORE = "SCORE";

		static final String TABLE_CREATE = "Create table " + TABLE_NAME + " ("
				+ COL_ID + " integer Primary Key AUTOINCREMENT, " + COL_NAME
				+ " varchar(20), " + COL_SCORE + " integer )";

		static final String DROP_TABLE = "Drop table if Exists " + TABLE_NAME;
		Context con;

		public myHelper(Context context) {

			super(context, dbname, null, dbVer);
			con = context;
			Log.d("Message", "Called Constructor");

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Toast.makeText(con, "Giong to create table", Toast.LENGTH_SHORT)
					.show();
			try {
				db.execSQL(TABLE_CREATE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DROP_TABLE);
				onCreate(db);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
