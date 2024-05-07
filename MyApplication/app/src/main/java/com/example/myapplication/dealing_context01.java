package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dealing_context01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealing_context);

        Button prev_btn = (Button) findViewById(R.id.prev_bu);
        Button next_btn = (Button) findViewById(R.id.confirm_bu);

        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 이전 화면(사진 등록 화면)으로 이동
                Intent intent = new Intent(getApplicationContext(), dealing_context01.class);
                startActivity(intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 등록 확인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), dealing_confirm01.class);
                startActivity(intent);
            }
        });
    }
}