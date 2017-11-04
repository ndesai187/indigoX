package com.nirav.indigox.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IndigoDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "indigoDB.db";
    public static final String TABLE_BARCODE_SCAN = "barcode_scan";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BARCODE_TEXT = "barCodeText";

    public IndigoDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public IndigoDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BARCODE_SCAN_TABLE = "CREATE TABLE " +
                TABLE_BARCODE_SCAN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_BARCODE_TEXT
                + " TEXT" + ")";
        db.execSQL(CREATE_BARCODE_SCAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARCODE_SCAN);
        onCreate(db);
    }

    public void addSamplerCode(BarCodeScan samplerCode) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_BARCODE_TEXT, samplerCode.getBarCodeText());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_BARCODE_SCAN, null, values);
        db.close();
    }

    public String getAllCodes() {
        String query = "Select * FROM " + TABLE_BARCODE_SCAN;
        SQLiteDatabase db = this.getWritableDatabase();
        String output = "";

        Cursor cursor = db.rawQuery(query, null);

        BarCodeScan codeScan = new BarCodeScan();

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            codeScan.setID(Integer.parseInt(cursor.getString(0)));
            codeScan.setBarCodeText(cursor.getString(1));
            output = output + codeScan.toString();
            cursor.moveToNext();
        }
        cursor.close();
        /*
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            codeScan.setID(Integer.parseInt(cursor.getString(0)));
            codeScan.setBarCodeText(cursor.getString(1));
            cursor.close();
        } else {
            codeScan = null;
        }
        */
        db.close();
        return output;
    }
}
