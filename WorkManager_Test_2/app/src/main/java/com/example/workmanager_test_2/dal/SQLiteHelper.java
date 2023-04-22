package com.example.workmanager_test_2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.workmanager_test_2.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="CongViec2.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT, " +
                "noiDung TEXT, " +
                "ngayHoanThanh TEXT, " +
                "tinhTrang TEXT, " +
                "congTac TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //getAll
    public List<CongViec> getAll (){
        List<CongViec> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ngayHoanThanh ASC";
        Cursor rs = st.query("items",null,null,null,null,null, order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String n = rs.getString(1);
            String ct = rs.getString(2);
            String d = rs.getString(3);
            String tt = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,n,ct,d,tt,congTac));
        }
        return list;
    }

    //add
    public long addItem(CongViec item){
        ContentValues values = new ContentValues();
        values.put("ten",item.getTen());
        values.put("noiDung",item.getNoiDung());
        values.put("ngayHoanThanh",item.getNgayHoanThanh());
        values.put("tinhTrang",item.getTinhTrang());
        values.put("congTac",item.getCongTac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null,values);
    }


    //update
    public int update(CongViec item){
        ContentValues values = new ContentValues();
        values.put("ten",item.getTen());
        values.put("noiDung",item.getNoiDung());
        values.put("ngayHoanThanh",item.getNgayHoanThanh());
        values.put("tinhTrang",item.getTinhTrang());
        values.put("congTac",item.getCongTac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {item.getId()+""};
        return sqLiteDatabase.update("items",values,whereClause,whereArgs);
    }

    //delete
    public int delete(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {id+""};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items",whereClause,whereArgs);
    }

    // lay cac item theo ten
    public List<CongViec> searchByTen(String key){
        List<CongViec> list = new ArrayList<>();
        String whereClause = "ten like ?";
        String[] whereArgs = {"%"+key+"%"};
        String order = "ngayHoanThanh ASC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",null,whereClause,whereArgs,null,null,order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String n = rs.getString(1);
            String ct = rs.getString(2);
            String d = rs.getString(3);
            String tt = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,n,ct,d,tt,congTac));
        }
        return list;
    }

    // lay cac item theo noi dung
    public List<CongViec> searchByNoiDung(String key){
        List<CongViec> list = new ArrayList<>();
        String whereClause = "noiDung like ?";
        String[] whereArgs = {"%"+key+"%"};
        String order = "ngayHoanThanh ASC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",null,whereClause,whereArgs,null,null,order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String n = rs.getString(1);
            String ct = rs.getString(2);
            String d = rs.getString(3);
            String tt = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,n,ct,d,tt,congTac));
        }
        return list;
    }

    // lay cac item theo tinh trang
    public List<CongViec> searchByTinhTrang(String category){
        List<CongViec> list = new ArrayList<>();
        String whereClause = "tinhTrang like ?";
        String[] whereArgs = {category};
        String order = "ngayHoanThanh ASC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",null,whereClause,whereArgs,null,null,order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String n = rs.getString(1);
            String ct = rs.getString(2);
            String d = rs.getString(3);
            String tt = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,n,ct,d,tt,congTac));
        }
        return list;
    }
}
