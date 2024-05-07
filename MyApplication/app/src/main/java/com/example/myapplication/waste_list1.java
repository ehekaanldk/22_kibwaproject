package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class waste_list1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_list1);

        Button moveButton7=(Button) findViewById(R.id.btn_done);
        moveButton7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), waste_list.class);  //등록 완료
                startActivity(intent);
            }
        });



        Button moveButton9=(Button)findViewById(R.id.btn_get);
        moveButton9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), waste_list2.class);  //수거 완료
                startActivity(intent);
            }
        });

        Button moveButton3=(Button) findViewById(R.id.button11);
        moveButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);  //폐기물 등록 추가 화면> 초기화면으로 이동
                startActivity(intent);
            }
        });

        ImageButton go_home = (ImageButton) findViewById(R.id.imageButton_home);
        go_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);  //폐기물 등록 추가 화면> 초기화면으로 이동
                startActivity(intent);
            }
        });

    }
}
