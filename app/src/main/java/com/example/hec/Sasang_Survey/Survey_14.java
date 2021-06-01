package com.example.hec.Sasang_Survey;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hec.R;
import com.example.hec.Sasang_result;

import java.util.HashMap;

public class Survey_14 extends AppCompatActivity {

    Button btn_1,btn_2,btn_3,next;
    int value;
    HashMap<String,Integer> result= new HashMap();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sasang_activity_survey_14);

        Intent values=getIntent();
        HashMap result=(HashMap)values.getSerializableExtra("result");

        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        btn_3=(Button)findViewById(R.id.btn_3);
        next=(Button)findViewById(R.id.next);

        btn_1.setClickable(true);
        btn_1.setPressed(true);
        btn_2.setPressed(true);
        btn_3.setPressed(true);
        btn_2.setClickable(true);
        btn_3.setClickable(true);
        next.setPressed(true);
        next.setClickable(false);
        next.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.put("answer14",value);
                Intent intent = new Intent(getApplicationContext(), Sasang_result.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("result",result);
                startActivity(intent);
                Log.i("data", String.valueOf(result));
                overridePendingTransition(0,0);

            }
        });



    }
    public void onclick(View view){
        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        btn_3=(Button)findViewById(R.id.btn_3);
        next=(Button)findViewById(R.id.next);

        switch (view.getId()){
            case R.id.btn_1:
                value=1;
                btn_1.setSelected(true);
                btn_2.setSelected(false);
                btn_3.setSelected(false);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
                value=1;
                break;
            case R.id.btn_2:
                value=2;
                btn_2.setSelected(true);
                btn_1.setSelected(false);
                btn_3.setSelected(false);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
                break;

            case R.id.btn_3:
                value=3;
                btn_3.setSelected(true);
                btn_1.setSelected(false);
                btn_2.setSelected(false);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
                break;

        }
    }

}

