package com.example.workmanager_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.workmanager_test_2.dal.SQLiteHelper;
import com.example.workmanager_test_2.model.CongViec;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spTinhTrang;
    private EditText txtName,txtContent, txtDate;
    private RadioGroup gCongTac;

    private RadioButton rbMotMinh,rbLamChung;
    private Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        txtDate.setOnClickListener(this);
    }

    private void initView() {
        spTinhTrang = findViewById(R.id.spTinhTrang);
        txtName = findViewById(R.id.txtName);
        txtContent = findViewById(R.id.txtContent);
        txtDate = findViewById(R.id.txtDate);
        gCongTac = findViewById(R.id.gCongTac);
        rbMotMinh = findViewById(R.id.rbMotMinh);
        rbLamChung = findViewById(R.id.rbLamchung);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        spTinhTrang.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.tinh_trang)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtDate:
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        String date ="";
                        if(m>8){
                            date = d+"/" +(m+1)+"/"+y;
                        }else{
                            date = d+"/0" +(m+1)+"/"+y;
                        }
                        txtDate.setText(date);
                    }
                },year,month,day);
                dialog.show();
                break;
            case R.id.btCancel:
                finish();
                break;
            case R.id.btUpdate:
                String n = txtName.getText()+"";
                String ct = txtContent.getText()+"";
                String d = txtDate.getText()+"";
                String tinhTrang = spTinhTrang.getSelectedItem().toString();
                int congTac = gCongTac.getCheckedRadioButtonId();
                if(!n.isEmpty() && !ct.isEmpty() && !d.isEmpty() &&  congTac != -1){
                    RadioButton congTacRadio = findViewById(congTac);
                    String congTacStr = congTacRadio.getText().toString();

                    CongViec i = new CongViec(n,ct,d,tinhTrang,congTacStr);
                    SQLiteHelper db = new SQLiteHelper(this);
                    db.addItem(i);
                    finish();
                }
                break;
        }
    }
}