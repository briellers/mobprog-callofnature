package com.example.callofnature;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBase {
    public static final String K_RID = "_id";
    public static final String K_DATE = "date";
    public static final String K_ANSWER = "status";
    private static final String DB_NAME = "DBPoopTracker";
    private static final String DB_TABLE = "tblStudent";
    private static final int DB_VERSION = 1;

    private DBHelper dbHelper;
    private final Context context;
    private SQLiteDatabase dBase;

    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(
                    "CREATE TABLE " + DB_TABLE + " (" +
                            K_RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            K_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                            K_ANSWER + " TEXT NOT NULL);"
            );
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

    //Constructor
    public DBase(Context c){
        context = c;
    }

    //Open database
    public DBase open() throws SQLException{
        dbHelper = new DBHelper(context);
        dBase = dbHelper.getWritableDatabase();
        return this;
    }

    //Close database
    public void close(){
        dbHelper.close();
    }

    //Add student record
    public long add(String _answer) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(K_ANSWER, _answer);
        return dBase.insert(DB_TABLE, null, cv);
    }

    //Return all records in String value
    public String getAllRecords() {
        // TODO Auto-generated method stub
        String[] cols = new String[]{K_RID, K_DATE, K_ANSWER};
        Cursor c = dBase.query(DB_TABLE, cols, null, null, null, null, null);
        String result = "";
        int indexDate = c.getColumnIndex(K_DATE);
        int indexAnswer = c.getColumnIndex(K_ANSWER);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(indexDate) + " \t\t\t\t"
                    + c.getString(indexAnswer)
                    + "\n";

        }
        return result;
    }

    //return one record(whose values are in array form) based on its id value
    public String[] getRecord(int rid) throws SQLException{
        String selectQuery = "SELECT * FROM "+DB_TABLE+" WHERE "+K_RID+"="+rid;
        Cursor c = null;
        c = dBase.rawQuery(selectQuery, null);
        String[] data = new String[2];
        if(c.moveToFirst()){
            int indexDate = c.getColumnIndex(K_DATE);
            int indexAnswer = c.getColumnIndex(K_ANSWER);
            data[0] = c.getString(indexDate);
            data[1] = c.getString(indexAnswer);
        }
        return data;
    }

    //update current record
    public void update(long ledit, String name, String course) throws SQLException{
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(K_DATE, name);
        cv.put(K_ANSWER, course);
        dBase.update(DB_TABLE, cv, K_RID +"=" + ledit , null);
    }

    //delete record
    public void delete(long id) throws SQLException{
        dBase.delete(DB_TABLE, K_RID+"="+id, null);
    }

    public void deleteAll() {
        dBase.delete(DB_TABLE, null, null);
    }

}