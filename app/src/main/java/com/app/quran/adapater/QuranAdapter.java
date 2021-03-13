package com.app.quran.adapater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quran.R;
import com.app.quran.realm.model.Quran;

import java.util.ArrayList;

import io.realm.RealmList;


public abstract class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.MyViewHolder> {
    private Activity context;
    private final RealmList<Quran> quranArrayList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtText;
        LinearLayout layoutMain;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtText = itemView.findViewById(R.id.txtText);
            this.layoutMain = itemView.findViewById(R.id.layoutMain);
            layoutMain.setOnClickListener(view -> onClickItem(getAdapterPosition()));

        }
    }

    public abstract void onClickItem(int adapterPosition);

    public QuranAdapter(Context context, RealmList<Quran> arrayList) {
        this.quranArrayList = arrayList;
        context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_quran_adapter, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int listPosition) {
        // animate(holder);

        Quran quran = quranArrayList.get(listPosition);
        holder.txtText.setText(quran.getText());

    }


    @Override
    public int getItemCount() {
        return quranArrayList == null ? 0 : quranArrayList.size();
    }
}


