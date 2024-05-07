package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class do_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_list);

        Button moveButton5=(Button) findViewById(R.id.button6);
        moveButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), waste_list.class); //폐기물 처리 목록으로 이동
                startActivity(intent);
            }
        });

        Button moveButton6=(Button) findViewById(R.id.button7);
        moveButton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), dealing_list.class);  //중고거래 목록으로 이동
                startActivity(intent);
            }
        });

        ImageButton go_home=(ImageButton) findViewById(R.id.imageButton_home);
        go_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);  //홈으로 이동
                startActivity(intent);
            }
        });

    }
}