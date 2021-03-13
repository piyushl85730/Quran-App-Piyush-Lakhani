package com.app.quran;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.quran.adapater.QuranAdapter;
import com.app.quran.helper.DatabaseAccess;
import com.app.quran.realm.model.Quran;

import java.text.MessageFormat;

import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {
    public DatabaseAccess databaseAccess;
    private RecyclerView recyclerView;
    RealmList<Quran> quranArrayList = new RealmList<Quran>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    private void findViewById() {
        databaseAccess = new DatabaseAccess(MainActivity.this);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        setAdapter();

    }

    private void setAdapter() {
        quranArrayList = databaseAccess.getAllQuranText();
        QuranAdapter quranAdapter = new QuranAdapter(this, quranArrayList) {
            @Override
            public void onClickItem(int adapterPosition) {
                Quran quran = quranArrayList.get(adapterPosition);
                Toast.makeText(MainActivity.this, MessageFormat.format("Sura-{0}\naya-{1}", quran.getSura(), quran.getAya()), Toast.LENGTH_SHORT).show();
            }
        };
        recyclerView.setAdapter(quranAdapter);
    }
}