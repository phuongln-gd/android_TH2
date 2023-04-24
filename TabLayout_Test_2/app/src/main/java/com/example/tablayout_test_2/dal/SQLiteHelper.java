package com.example.tablayout_test_2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tablayout_test_2.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="NhanVien.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT, " +
                "dienThoai TEXT, " +
                "namSinh TEXT, " +
                "gioiTinh TEXT, " +
                "kyNang TEXT)";
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
    public List<NhanVien> getAll (){
        List<NhanVien> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "id ASC";
        Cursor rs = st.query("items",null,null,null,null,null, order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String dienThoai = rs.getString(2);
            String namSinh = rs.getString(3);
            String gioiTinh = rs.getString(4);
            String kyNang = rs.getString(5);
            list.add(new NhanVien(id,ten,dienThoai,namSinh,gioiTinh,kyNang));
        }
        return list;
    }

    //add
    public long addItem(NhanVien item){
        ContentValues values = new ContentValues();
        values.put("ten",item.getTen());
        values.put("dienThoai",item.getDienThoai());
        values.put("namSinh",item.getNamSinh());
        values.put("gioiTinh",item.getGioiTinh());
        values.put("kyNang",item.getKyNang());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null,values);
    }


    //update
    public int update(NhanVien item){
        ContentValues values = new ContentValues();
        values.put("ten",item.getTen());
        values.put("dienThoai",item.getDienThoai());
        values.put("namSinh",item.getNamSinh());
        values.put("gioiTinh",item.getGioiTinh());
        values.put("kyNang",item.getKyNang());
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

    // lay cac item theo ky nang
    public List<NhanVien> searchByKyNang(String kyNang){
        List<NhanVien> list = new ArrayList<>();
        String whereClause = "kyNang like ?";
        String[] whereArgs = {"%"+kyNang+"%"};
        String order = "id ASC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",null,whereClause,whereArgs,null,null,order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String dienThoai = rs.getString(2);
            String namSinh = rs.getString(3);
            String gioiTinh = rs.getString(4);
            String kn = rs.getString(5);
            list.add(new NhanVien(id,ten,dienThoai,namSinh,gioiTinh,kn));
        }
        return list;
    }
}
