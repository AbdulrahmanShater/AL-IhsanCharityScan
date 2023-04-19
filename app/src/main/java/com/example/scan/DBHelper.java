package com.example.scan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hreitan.db";
    public static final int Version = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table Scan " +
                "(id text unique )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS ID");
        onCreate(db);
    }

    public boolean insertID(String id) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
        try {
            db.insert("Scan", id, contentValues);
        }
        catch (SQLException e){
            System.out.println("exist");
        }
            return true;
    }

    public String getData(String id) {
        String d = "";
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Scan where id =  '" + id + "' ", null);
        res.moveToFirst();
        int iid = res.getColumnIndex("id");
        if (!res.isAfterLast())
            d = res.getString(iid);

        return d;


    }

//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, "contacts");
//        return numRows;
//    }
//
//    public boolean updateMobile (Integer id, String mobile_name, double price) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("mobile_name", mobile_name);
//        contentValues.put("price", price);
//        db.update("mobiles", contentValues, "id = "+id , new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Integer deleteMobile (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("mobiles",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList <mobile> getAllMobiles() {
//        ArrayList array_list = new ArrayList<mobile>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts ", null );
//        res.moveToFirst();
//        int iname=res.getColumnIndex("mobile_name");
//        int iprice=res.getColumnIndex("price");
//
//        while(res.isAfterLast() == false){
//            String name=res.getString(iname);
//            String price=res.getString(iprice);
//
//
//
//            array_list.add(new mobile(name,price));
//
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
