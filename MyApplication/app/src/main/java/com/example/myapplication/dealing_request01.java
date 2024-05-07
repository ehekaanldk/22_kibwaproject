package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class dealing_request01 extends AppCompatActivity implements View.OnClickListener {

    Button btnCamera2;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealing_request01);

        // 디자인 정의
        btnCamera2 = (Button) findViewById(R.id.btnPhoto2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        btnCamera2.setOnClickListener(this);

        Button moveButton1=(Button)findViewById(R.id.next_bu);
        moveButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), dealing_context01.class);  //정보 입력 및 납부금액 결정 > 예약자 확인 화면으로 이동
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view)    {
        switch (view.getId()) {
            // 카메라촬영 클릭 이벤트
            case R.id.btnPhoto2:
                // 카메라 기능을 Intent
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)    {
        super.onActivityResult(requestCode, resultCode, data);

        // 카메라 촬영을 하면 이미지뷰에 사진 삽입
        if(requestCode == 0 && resultCode == RESULT_OK) {
            // Bundle로 데이터를 입력
            Bundle extras = data.getExtras();

            // Bitmap으로 컨버전
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // 이미지뷰에 Bitmap으로 이미지를 입력
            imageView2.setImageBitmap(imageBitmap);
        }
    }


}