package com.example.jgervas.datamanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sharedPreferences(View v)
    {
        Intent intent = new Intent(v.getContext(), SharedActivity.class);
        startActivity(intent);
    }

    public void internalStorage(View v)
    {
        Intent intent = new Intent(v.getContext(), InternalActivity.class);
        startActivity(intent);
    }

    public void externalStorage(View v)
    {
        Intent intent = new Intent(v.getContext(), ExternalActivity.class);
        startActivity(intent);
    }

    public void sqliteDb(View v)
    {
        Intent intent = new Intent(v.getContext(), SqliteActivity.class);
        startActivity(intent);
    }
}
