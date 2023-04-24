package com.example.tablayout_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tablayout_test_2.dal.SQLiteHelper;
import com.example.tablayout_test_2.model.NhanVien;

import java.util.Calendar;


public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spGioiTinh;
    private EditText txtTen,txtDienThoai, txtNamSinh;
    private CheckBox cbWeb, cbAndroid,cbIOS;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
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
        btCancel = findViewById(R.id.btCancel);
        spGioiTinh.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.gioi_tinh)));
        // example checkbox onclick
        cbAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                }
                else{

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCancel:
                finish();
                break;
            case R.id.btUpdate:
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
                    NhanVien i = new NhanVien(ten,dienThoai,namSinh,gioiTinh,kn);
                    SQLiteHelper db = new SQLiteHelper(this);
                    db.addItem(i);
                    finish();
                }
                break;
        }
    }
}