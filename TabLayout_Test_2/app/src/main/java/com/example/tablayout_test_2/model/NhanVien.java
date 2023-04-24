package com.example.tablayout_test_2.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int id;
    private String ten,dienThoai,namSinh,gioiTinh,kyNang;

    public NhanVien() {
    }

    public NhanVien(String ten, String dienThoai, String namSinh, String gioiTinh, String kyNang) {
        this.ten = ten;
        this.dienThoai = dienThoai;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.kyNang = kyNang;
    }

    public NhanVien(int id, String ten, String dienThoai, String namSinh, String gioiTinh, String kyNang) {
        this.id = id;
        this.ten = ten;
        this.dienThoai = dienThoai;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.kyNang = kyNang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getKyNang() {
        return kyNang;
    }

    public void setKyNang(String kyNang) {
        this.kyNang = kyNang;
    }
}
