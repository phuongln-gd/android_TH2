package com.example.tablayout_test_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout_test_2.R;
import com.example.tablayout_test_2.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {

    private List<NhanVien> list;

    private ItemListener itemListener;

    public RecycleViewAdapter(){
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<NhanVien> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public NhanVien getItem(int pos){
        return list.get(pos);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        NhanVien item = list.get(position);
        holder.tvTen.setText(item.getTen());
        holder.tvDienThoai.setText(item.getDienThoai());
        holder.tvNamSinh.setText(item.getNamSinh());
        holder.tvGioiTinh.setText(item.getGioiTinh());
        holder.tvKyNang.setText(item.getKyNang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTen, tvDienThoai, tvNamSinh, tvGioiTinh, tvKyNang;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            tvTen = view.findViewById(R.id.tvTen);
            tvDienThoai = view.findViewById(R.id.tvDienThoai);
            tvNamSinh = view.findViewById(R.id.tvNamSinh);
            tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
            tvKyNang = view.findViewById(R.id.tvKynang);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view,int pos);
    }
}
