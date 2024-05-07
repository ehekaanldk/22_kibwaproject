package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class waste_request02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_request02);

        Button moveButton1=(Button)findViewById(R.id.next_bu);
        moveButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), waste_request_confirm01.class);  //정보 입력 및 납부금액 결정 > 예약자 확인 화면으로 이동
                startActivity(intent);
            }
        });
    }
}