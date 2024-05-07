package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView th_fur = (CardView) findViewById(R.id.cardview1);
        CardView trade_f = (CardView) findViewById(R.id.cardview2);
        CardView view_list = (CardView) findViewById(R.id.cardview3);

        th_fur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 폐기물 처리 > 로그인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), waste_request01.class);
                startActivity(intent);
            }
        });

        trade_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 중고 거래 > 로그인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), dealing_request01.class);
                startActivity(intent);
            }
        });




        view_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 개인 정보 확인창으로 이동
                Intent intent = new Intent(getApplicationContext(), do_list.class);
                startActivity(intent);
            }
        });
    }
}
