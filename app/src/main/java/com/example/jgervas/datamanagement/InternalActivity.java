package com.example.jgervas.datamanagement;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ActionBar.LayoutParams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends Activity {

    EditText edFileName, edContent;
    Button btnSave;
    ListView listSavedFiles;

    String[] SavedFiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        edFileName = (EditText)findViewById(R.id.filename);
        edContent = (EditText)findViewById(R.id.content);
        btnSave = (Button)findViewById(R.id.save);
        listSavedFiles = (ListView)findViewById(R.id.list);
        listSavedFiles.setOnItemClickListener(listSavedFilesOnItemClickListener);


        ShowSavedFiles();

    }

    OnItemClickListener listSavedFilesOnItemClickListener
            = new OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub
            String clickedFile = (String) parent.getItemAtPosition(position);
            OpenFileDialog(clickedFile);
        }

    };


    void ShowSavedFiles(){
        SavedFiles = getApplicationContext().fileList();
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                SavedFiles);

        listSavedFiles.setAdapter(adapter);
    }


    public void save(View v)
    {
        String fileName = edFileName.getText().toString();
        String content = edContent.getText().toString();

        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();

            Toast.makeText(
                    getApplicationContext(),
                    fileName + " saved",
                    Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ShowSavedFiles();

    }

    void OpenFileDialog(String file){

        //Read file in Internal Storage
        FileInputStream fis;
        String content = "";
        try {
            fis = openFileInput(file);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {}
            content += new String(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), file + " : " + content, Toast.LENGTH_LONG).show();

    }




}
