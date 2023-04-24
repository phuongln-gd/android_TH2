package com.example.tablayout_test_2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout_test_2.R;
import com.example.tablayout_test_2.UpdateDeleteActivity;
import com.example.tablayout_test_2.adapter.RecycleViewAdapter;
import com.example.tablayout_test_2.dal.SQLiteHelper;
import com.example.tablayout_test_2.model.NhanVien;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentDanhSach extends Fragment implements RecycleViewAdapter.ItemListener {

    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danhsach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<NhanVien> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<NhanVien> list = db.getAll();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int pos) {
        NhanVien item = adapter.getItem(pos);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}
