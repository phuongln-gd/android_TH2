package com.example.workmanager_test_2.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager_test_2.R;
import com.example.workmanager_test_2.dal.RecycleViewAdapter;
import com.example.workmanager_test_2.dal.SQLiteHelper;
import com.example.workmanager_test_2.model.CongViec;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentTimKiem extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button btSearch;
    private SearchView searchView;
    private Spinner spTinhTrang;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timkiem,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<CongViec> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<CongViec> listItem = db.searchByTen(newText);
                adapter.setList(listItem);
                return true;
            }
        });
        btSearch.setOnClickListener(this);
        spTinhTrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String cate = spTinhTrang.getItemAtPosition(pos).toString();
                List<CongViec> listItem = new ArrayList<>();
                if(!cate.equalsIgnoreCase("all")){
                    listItem = db.searchByTinhTrang(cate);
                }
                else{
                    listItem = db.getAll();
                }
                adapter.setList(listItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycleView);
        btSearch = view.findViewById(R.id.btSearch);
        searchView = view.findViewById(R.id.search);
        spTinhTrang = view.findViewById(R.id.spTinhTrang);
        String[] arr = getResources().getStringArray(R.array.tinh_trang);
        String[] arr1 = new String[arr.length + 1];
        arr1[0] = "All";
        for (int i = 0; i < arr.length; i++) {
            arr1[i + 1] = arr[i];
        }
        spTinhTrang.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onResume() {
        super.onResume();
        List<CongViec> list = db.getAll();
        adapter.setList(list);
    }
}
