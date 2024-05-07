package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;


/*
 *이미지를 앨범에서 선택하는 코드와
 *선택한 이미지를 비트맵으로 변환하는 코드와
 *비트맵을 바이트 배열로 변환하는 코드와
 *텐서플로 라이트 모델을 적용하는 코드와
 *예측 결과를 텍스트뷰로 출력하는 코드
 * */

public class waste_request01 extends AppCompatActivity {
    private static final String TAG = "CameraActivity";

    public static final int REQUEST_TAKE_PHOTO = 10;
    public static final int REQUEST_PERMISSION = 11;

    private static final int FROM_ALBUM = 1;    // onActivityResult 식별자
    private static final int FROM_CAMERA = 2;   // 카메라는 사용 안함

    Button btnPhoto1;
    ImageView imageView1;
    TextView TextViewPay, TextViewName, TextViewSize;
    RadioButton rdchair1, rdchair2, rdchair3;
    View dialogchairView, dialogdeskView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_request01);

        // 디자인 정의
        btnPhoto1 = (Button) findViewById(R.id.btnPhoto1);

        btnPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");                      // 이미지만
                intent.setAction(Intent.ACTION_GET_CONTENT);    // 카메라(ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, FROM_ALBUM);
            }
        });

        checkPermission(); //권한체크

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        TextViewPay = (TextView) findViewById(R.id.TextViewPay);
        TextViewName = (TextView) findViewById(R.id.TextViewName);
        TextViewSize = (TextView) findViewById(R.id.TextViewSize);


        findViewById(R.id.next_bu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), waste_request_confirm01.class);  //정보 입력 및 납부금액 결정 > 예약자 확인 화면으로 이동
                startActivity(intent);
            }
        });
    }



    private void checkPermission() {
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != FROM_ALBUM || resultCode != RESULT_OK)
            return;

        try {
            // 선택한 이미지에서 비트맵 생성
            InputStream stream = getContentResolver().openInputStream(data.getData());
            Bitmap bmp = BitmapFactory.decodeStream(stream);
            stream.close();


            ImageView iv = findViewById(R.id.imageView1);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);    // [300, 300]에 꽉 차게 표시
            iv.setImageBitmap(bmp);

            //각 모델에 따른 input , output shape 각자 맞게 변환
            // mobilenetcheck.h5 일시 224 * 224 * 3
            float[][][][] input = new float[1][224][224][3];
            float[][] output = new float[1][5]; //tflite에 버섯 종류 5개라서 (내기준)

            // x,y 최댓값 사진 크기에 따라 달라짐 (조절 해줘야함)
            for (int x = 0; x < 224; x++) {
                for (int y = 0; y < 224; y++) {
                    int pixel = bmp.getPixel(x, y);
                    input[0][x][y][0] = Color.red(pixel) / 255.0f;
                    input[0][x][y][1] = Color.green(pixel) / 255.0f;
                    input[0][x][y][2] = Color.blue(pixel) / 255.0f;
                }
            }
            Interpreter tf_lite = getTfliteInterpreter("final_converted_model.tflite");

            tf_lite.run(input, output);

            Log.d("predict", Arrays.toString(output[0]));
/*
            // 텍스트뷰 10개. 0~9 사이의 숫자 예측
            // 숫자값 부분 요기는 성공인데....
            int[] id_array = {R.id.result_0, R.id.result_1};

            for (int i = 0; i < 5; i++) {
                TextView tv = findViewById(id_array[i]);
                tv.setText(String.format("%.5f", output[0][i]));    // [0] : 2차원 배열의 첫 번째
            }
*/
            //인식한부본 출렫하기!!!!
            int i;
            TextView TextViewPay = findViewById(R.id.TextViewPay);
            TextView TextViewName = findViewById(R.id.TextViewName);
            for (i = 0; i < 5; i++) {
                if (output[0][i] * 100 > 30) {
                    if (i == 0) {
                        TextViewName.setText("tv");
                        TextViewPay.setText("3000원");

                        //정확도 확인
                        // TextViewPay.setText(String.format("tv, 2000원, %d, %.5f", i, output[0][0] * 100));

                    } else if (i == 1) {
                        TextViewName.setText("desk");
                        TextViewPay.setText("3000원");
                        //TextViewPay.setText(String.format("table, 2000원, %d, %.5f", i, output[0][1] * 100));
                    } else if (i == 2) {
                        TextViewName.setText("chair");
                        TextViewPay.setText("3000원");
                        //TextViewPay.setText(String.format("desk,  2000원, %d, %.5f", i, output[0][2] * 100));
                    } else if (i == 3) {
                        TextViewName.setText("chair");
                        TextViewPay.setText("3000원");
                        //TextViewPay.setText(String.format("shelf, 2000원, %d, %.5f", i, output[0][3] * 100));
                    } else {
                        TextViewName.setText("chair");
                        TextViewPay.setText("3000원");
                        //TextViewPay.setText(String.format("chair , 2000원, %d, %.5f", i, output[0][4] * 100));
                    }
                } else
                    continue;
            }

            String t1 = TextViewName.getText().toString();
            String t2 = "chair";
            String t3 = "desk";
            String t4 = "table";
            if(t1.equals(t2)) {
                TextViewSize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(waste_request01.this);
                        dlg.setTitle("폐기물의 규격을 선택해주세요");
                        dlg.setView(dialogchairView);

                        final String[] memberArray = new String[]{"바퀴달린 의자", "일반 의자", "좌식, 접이식 의자"};
                        final String[] memberArray1 = new String[]{"3000원", "2000원", "1000원"};
                        dlg.setSingleChoiceItems(memberArray, 0,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        TextViewSize.setText(memberArray[which]);
                                        TextViewPay.setText(memberArray1[which]);
                                    }
                                });

                        dlg.setNegativeButton("취소", null);
                        dlg.setPositiveButton("닫기",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        dlg.show();
                    }
                });
            }
            if(t1.equals(t3) || t1.equals(t4)){
                TextViewSize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(waste_request01.this);
                        dlg.setTitle("폐기물의 규격을 선택해주세요");
                        dlg.setView(dialogdeskView);

                        final String[] memberArray = new String[]{"100~200", "200~300", "300~400", "400~500"};
                        final String[] memberArray1 = new String[]{"1000원", "2000원", "3000원", "4000원"};
                        dlg.setSingleChoiceItems(memberArray, 0,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        TextViewSize.setText(memberArray[which]);
                                        TextViewPay.setText(memberArray1[which]);
                                    }
                                });

                        dlg.setNegativeButton("취소", null);
                        dlg.setPositiveButton("닫기",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        dlg.show();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 모델 파일 인터프리터를 생성하는 공통 함수
    // loadModelFile 함수에 예외가 포함되어 있기 때문에 반드시 try, catch 블록이 필요하다.
    private Interpreter getTfliteInterpreter(String modelPath) {
        try {
            return new Interpreter(loadModelFile(waste_request01.this, modelPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 모델을 읽어오는 함수로, 텐서플로 라이트 홈페이지에 있다.
    // MappedByteBuffer 바이트 버퍼를 Interpreter 객체에 전달하면 모델 해석을 할 수 있다.
    private MappedByteBuffer loadModelFile(Activity activity, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
}

//border_layout.xml파일 drawable에 생성
//<?xml version="1.0" encoding="utf-8"?>
//<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
//    <item
//        android:bottom="2dp"
//        android:end="2dp"
//        android:start="2dp"
//        android:top="2dp">
//        <shape android:shape="rectangle">
//            <stroke
//                android:width="2dp"
//                android:color="#000000" />
//            <solid android:color="null" />
//        </shape>
//    </item>
//</layer-list>

//