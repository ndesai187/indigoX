package com.nirav.indigox.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class IndigoDBManager {
    private IndigoDBHandler dbHandler;
    private Context context;
    private SQLiteDatabase database;
    public static final String TABLE_BARCODE_SCAN = "barcode_scan";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BARCODE_TEXT = "barCodeText";


    public IndigoDBManager(Context c){
        context = c;
    }

    public IndigoDBManager open() throws SQLException{
        dbHandler = new IndigoDBHandler(context,null,null,1);
        database = dbHandler.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHandler.close();
    }

    public void addSamplerCode(BarCodeScan samplerCode) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_BARCODE_TEXT, samplerCode.getBarCodeText());
        database.insert(TABLE_BARCODE_SCAN, null, values);
    }

    public String getAllCodes() {
        String query = "Select * FROM " + TABLE_BARCODE_SCAN;
        String output = "";

        Cursor cursor = database.rawQuery(query, null);

        BarCodeScan codeScan = new BarCodeScan();

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            codeScan.setID(Integer.parseInt(cursor.getString(0)));
            codeScan.setBarCodeText(cursor.getString(1));
            output = output + codeScan.toString();
            cursor.moveToNext();
        }
        cursor.close();

        return output;
    }

    public Cursor geAllScanCodes(){
        String[] columns = new String[]{IndigoDBHandler.COLUMN_ID,
                IndigoDBHandler.COLUMN_BARCODE_TEXT};
        Cursor cursor = database.query(IndigoDBHandler.TABLE_BARCODE_SCAN,
                columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
}
