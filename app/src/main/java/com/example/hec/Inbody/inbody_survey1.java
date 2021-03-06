package com.example.hec.Inbody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.hec.R;

import java.util.HashMap;

public class inbody_survey1 extends AppCompatActivity {

    Button btn_1,btn_2,next;
    int value;
    HashMap<String,Integer> inbody_result= new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbody_activity_survey1);

        HashMap<String,Integer> inbody_result= new HashMap();

        btn_1=(Button)findViewById(R.id.inbody_1);
        btn_2=(Button)findViewById(R.id.inbody_2);
        next=(Button)findViewById(R.id.inbody_next);

        btn_1.setPressed(true);
        btn_2.setPressed(true);

        btn_1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                btn_1.setSelected(true);
                btn_2.setSelected(false);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
                value=1;
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                btn_1.setSelected(false);
                btn_2.setSelected(true);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
                value=2;
            }
        });

        next.setClickable(false);
        next.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inbody_result.put("answer1",value);

                Intent intent = new Intent(getApplicationContext(), inbody_survey2.class);
                intent.putExtra("result",inbody_result);
                startActivity(intent);

                overridePendingTransition(0,0);
            }
        });
        Log.i("inbody_result", String.valueOf(inbody_result));
    }

}