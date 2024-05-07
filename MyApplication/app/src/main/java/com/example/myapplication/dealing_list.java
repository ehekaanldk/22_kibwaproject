package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class dealing_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealing_list);

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