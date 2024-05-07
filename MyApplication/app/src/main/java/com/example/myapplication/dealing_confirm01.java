package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dealing_confirm01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealing_confirm01);

        Button confirm_btn = (Button) findViewById(R.id.confirm_bu);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 목록 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), dealing_list.class);
                startActivity(intent);
            }
        });
    }
}