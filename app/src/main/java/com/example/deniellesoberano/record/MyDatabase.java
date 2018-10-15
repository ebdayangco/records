package com.example.deniellesoberano.record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deniellesoberano.record.Model.Record;

public class MyDatabase extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Record.db";
    public static final String TABLE_NAME = "record_tbl";
    public static final String COL1 = "productID";
    public static final String COL2 = "description";
    public static final String COL3 = "quantity";
    public static final String COL4 = "price";
    public static final String COL5 = "productname";


    public MyDatabase(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String idStatement = COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,";
        String decStatement = COL2 + " TEXT, ";
        String quantStatement = COL3 + " INTEGER, ";
        String priceStatement = COL4 + " DOUBLE,";
        String productNameStatement = COL5 + " TEXT";
        String statement = TABLE_NAME + "("+ idStatement+ decStatement + quantStatement + priceStatement +productNameStatement+")";

        db.execSQL("CREATE TABLE "+statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void dropTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }

    public void createTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        String idStatement = COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,";
        String decStatement = COL2 + " TEXT, ";
        String quantStatement = COL3 + " INTEGER, ";
        String priceStatement = COL4 + " DOUBLE,";
        String productNameStatement = COL5 + " TEXT";
        String statement = TABLE_NAME + "("+ idStatement+ decStatement + quantStatement + priceStatement +productNameStatement+")";

        db.execSQL("CREATE TABLE "+statement);
    }

    public boolean insertData(Record record){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,record.getDescription());
        contentValues.put(COL3,record.getQuantity());
        contentValues.put(COL4,record.getPrice());
        contentValues.put(COL5,record.getProductname());
        return db.insert(TABLE_NAME,null,contentValues) != -1;

    }
    public Cursor getData(String cond){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor retDatas = db.rawQuery("SELECT * FROM "+TABLE_NAME+" "+cond,null);
        return retDatas;
    }
    public boolean updateData(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,record.getDescription());
        contentValues.put(COL3,record.getQuantity());
        contentValues.put(COL4,record.getPrice());
        contentValues.put(COL5,record.getProductname());
        return db.update(TABLE_NAME,contentValues,"productID=?",new String[]{record.getId()+ ""}) !=-1;

    }
    public boolean deleteData(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"productID=?",new String[]{record.getId()+""}) != -1;

    }
}
