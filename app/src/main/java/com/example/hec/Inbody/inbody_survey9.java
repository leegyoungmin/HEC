package com.example.hec.Inbody;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hec.R;

import java.util.HashMap;

public class inbody_survey9 extends AppCompatActivity {

    Button btn_1,btn_2,next;
    int value;
    HashMap<String,Integer> inbody_result= new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbody_activity_survey9);

        inbody_result= (HashMap<String, Integer>) getIntent().getSerializableExtra("inbody_result");
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
                inbody_result.put("answer9",value);

                Intent intent = new Intent(getApplicationContext(), inbody_survey10.class);
                intent.putExtra("result",inbody_result);
                startActivity(intent);

                overridePendingTransition(0,0);
            }
        });
        Log.i("inbody_result", String.valueOf(inbody_result));
    }

}