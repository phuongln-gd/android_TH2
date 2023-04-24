package com.example.tablayout_test_2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout_test_2.R;
import com.example.tablayout_test_2.adapter.RecycleViewAdapter;
import com.example.tablayout_test_2.dal.SQLiteHelper;
import com.example.tablayout_test_2.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class FragmentTimKiem extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView tvTong;
    private Button btSearch;
    private SearchView searchView;
    private Spinner spKyNang;
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
        List<NhanVien> list = db.getAll();
        adapter.setList(list);
        tvTong.setText("Tong nhan vien: "+ tong(list));
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

                return true;
            }
        });
        btSearch.setOnClickListener(this);
        spKyNang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String kyNang = spKyNang.getItemAtPosition(pos).toString();
                List<NhanVien> listItem = new ArrayList<>();
                if(!kyNang.equalsIgnoreCase("all")){
                    listItem = db.searchByKyNang(kyNang);
                }
                else{
                    listItem = db.getAll();
                }
                adapter.setList(listItem);
                tvTong.setText("Tong nhan vien: "+ tong(listItem));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycleView);
        tvTong = view.findViewById(R.id.tvTong);
        btSearch = view.findViewById(R.id.btSearch);
        searchView = view.findViewById(R.id.search);
        spKyNang = view.findViewById(R.id.spTinhTrang);
        String[] arr = getResources().getStringArray(R.array.ky_nang);
        String[] arr1 = new String[arr.length + 1];
        arr1[0] = "All";
        for (int i = 0; i < arr.length; i++) {
            arr1[i + 1] = arr[i];
        }
        spKyNang.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onResume() {
        super.onResume();
        List<NhanVien> list = db.getAll();
        adapter.setList(list);
    }

    private int tong(List<NhanVien> list){
//        int t =0;
//        for(NhanVien i:list){
//            t += Integer.parseInt(i.getPrice());
//        }
//        return t;
        return list.size();
    }
}
