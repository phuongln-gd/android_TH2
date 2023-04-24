package com.example.tablayout_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tablayout_test_2.dal.SQLiteHelper;
import com.example.tablayout_test_2.model.NhanVien;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spGioiTinh;
    private EditText txtTen,txtDienThoai, txtNamSinh;
    private CheckBox cbWeb, cbAndroid,cbIOS;
    private Button btUpdate,btDel, btBack;
    private NhanVien item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btDel.setOnClickListener(this);
        btBack.setOnClickListener(this);
        Intent intent = getIntent();
        item = (NhanVien) intent.getSerializableExtra("item");
        txtTen.setText(item.getTen());
        txtDienThoai.setText(item.getDienThoai());
        txtNamSinh.setText(item.getNamSinh());
        int p = 0;
        for(int i = 0 ; i <spGioiTinh.getCount();i++){
            if(spGioiTinh.getItemAtPosition(i).toString().equalsIgnoreCase(item.getGioiTinh())){
                p = i;
                break;
            }
        }
        spGioiTinh.setSelection(p);
        String[] kn = item.getKyNang().split(", ");
        for (int i = 0 ; i < kn.length;i++){
            if(kn[i].equalsIgnoreCase(cbWeb.getText()+"")){
                cbWeb.setChecked(true);
            }
            if(kn[i].equalsIgnoreCase(cbAndroid.getText()+"")){
                cbAndroid.setChecked(true);
            }
            if(kn[i].equalsIgnoreCase(cbIOS.getText()+"")){
                cbIOS.setChecked(true);
            }
        }
    }

    private void initView() {
        spGioiTinh = findViewById(R.id.spGioiTinh);
        txtTen = findViewById(R.id.txtTen);
        txtDienThoai = findViewById(R.id.txtDienThoai);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        cbWeb = findViewById(R.id.cbWeb);
        cbAndroid = findViewById(R.id.cbAndroid);
        cbIOS = findViewById(R.id.cbIOS);
        btUpdate = findViewById(R.id.btUpdate);
        btDel = findViewById(R.id.btDel);
        btBack = findViewById(R.id.btBack);
        spGioiTinh.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.gioi_tinh)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db= new SQLiteHelper(this);
        if(view == btBack){
            finish();
        }
        if(view == btUpdate){
            String ten = txtTen.getText()+"";
            String dienThoai = txtDienThoai.getText()+"";
            String namSinh = txtNamSinh.getText()+"";
            String gioiTinh = spGioiTinh.getSelectedItem().toString();
            String kn="";
            if(cbWeb.isChecked()){
                kn += cbWeb.getText()+", ";
            }
            if(cbAndroid.isChecked()){
                kn += cbAndroid.getText()+", ";
            }
            if(cbIOS.isChecked()){
                kn += cbIOS.getText()+", ";
            }
            if(!ten.isEmpty() && !dienThoai.isEmpty() && !namSinh.isEmpty()){
                int id = item.getId();
                NhanVien i = new NhanVien(id,ten,dienThoai,namSinh,gioiTinh,kn);
                db.update(i);
                finish();
            }
        }
        if(view == btDel){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong  bao xoa");
            builder.setMessage("Ban co chac muong xoa "+item.getTen()+" khong?");
            builder.setIcon(R.drawable.ic_delete);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}