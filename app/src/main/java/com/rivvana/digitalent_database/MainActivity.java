package com.rivvana.digitalent_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String FILENAME = "namaFile.txt";
    Button btnCreate, btnUpdate, btnRead, btnDelete;
    TextView tvHasil;
//    ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btn_create);
        btnUpdate = findViewById(R.id.btn_update);
        btnRead = findViewById(R.id.btn_read);
        btnDelete = findViewById(R.id.btn_delete);
        tvHasil = findViewById(R.id.tv_hasil);

        btnCreate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                buatFile();
                break;
            case R.id.btn_update:
                updateFile();
                break;
            case R.id.btn_read:
                bacaFile();
                break;
            case R.id.btn_delete:
                hapusFile();
                break;
        }
    }

    void buatFile() {
        String isiFile = "Ini Adalah Isi File\n";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
//            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
            Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
            tvHasil.setText("");
        } else {
            Toast.makeText(getBaseContext(),"file not found",Toast.LENGTH_SHORT).show();
        }
    }

    private void bacaFile() {
        try {
            FileInputStream fin = openFileInput(FILENAME);
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            tvHasil.setText(temp);
            Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void updateFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            String isiFile = "Update data myFile";
            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                outputStream.write(isiFile.getBytes());
                outputStream.close();
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(),"file not found",Toast.LENGTH_SHORT).show();
        }
    }


}