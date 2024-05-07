package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class waste_request_confirm02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_request_comfirm02);

        Button moveButton3=(Button) findViewById(R.id.prev_bu);
        moveButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), waste_request01.class);  //폐기물 등록 추가 화면> 초기화면으로 이동
                startActivity(intent);
            }
        });

        Button moveButton4=(Button) findViewById(R.id.confirm_bu);
        moveButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), waste_list.class);  //폐기물 등록 완료> 홈으로 이동
                startActivity(intent);
            }
        });
    }
}