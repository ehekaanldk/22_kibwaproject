package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dealing_request02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealing_request02);

        Button next_btn = (Button) findViewById(R.id.next_bu);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 중고거래 내용 등록창으로 이동
                Intent intent = new Intent(getApplicationContext(), dealing_context01.class);
                startActivity(intent);
            }
        });
    }
}