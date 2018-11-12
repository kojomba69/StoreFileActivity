package com.example.edu.storefileactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String INTERNAL_FILENAME = "sun_internal";
    //변하지않는 상수값이란의미에서 final을 넣어주는것.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.buttonFileReadFromInner)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonFileWriteFromInner)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editTextInput = findViewById(R.id.editTextInput);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        switch (v.getId()){
            case  R.id.buttonFileReadFromInner:
                try {
                    fileInputStream=openFileInput(INTERNAL_FILENAME);
                    byte[] buffer=new  byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    editTextInput.setText(new String(buffer));
                    fileInputStream.close();
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case R.id.buttonFileWriteFromInner:
                try {
                    fileOutputStream=openFileOutput(INTERNAL_FILENAME, Context.MODE_PRIVATE);
                    fileOutputStream.write(editTextInput.getText().toString().getBytes());
                    editTextInput.setText("");
                    fileOutputStream.close();
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
