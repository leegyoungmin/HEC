package com.example.hec.Inbody;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hec.R;

import java.util.HashMap;

public class inbody_survey2 extends AppCompatActivity {

    Button btn_1,btn_2,next;
    int value;
    HashMap<String,Integer> inbody_result= new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbody_activity_survey1);
        Intent values=getIntent();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inbody_result.put("answer1",value);
                Intent intent = new Intent(getApplicationContext(), inbody_survey1.class);
                intent.putExtra("result",inbody_result);
                startActivity(intent);
                Log.i("data", String.valueOf(inbody_result));
                overridePendingTransition(0,0);
            }
        });
    }

    public void onclick(View view){
        btn_1=(Button)findViewById(R.id.inbody_1);
        btn_2=(Button)findViewById(R.id.inbody_2);
        next=(Button)findViewById(R.id.inbody_next);

        switch (view.getId()){
            case R.id.btn_1:
                value=1;
                btn_1.setSelected(true);
                btn_2.setSelected(false);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);


                break;
            case R.id.btn_2:
                value=2;
                btn_2.setSelected(true);
                btn_1.setSelected(false);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);

                break;

        }
    }
}