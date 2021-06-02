package com.example.hec;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hec.Inbody.inbody_survey1;
import com.example.hec.Sasang_Survey.Survey_1;
import com.example.hec.Sasang_Survey.sex_Survey;

import java.util.HashMap;

public class choose extends AppCompatActivity {

    CheckedTextView ckbox1,ckbox2,ckbox3;
    public Boolean ss,ib,ds;
    public static Context context_choose;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        context_choose = this;

        Intent intent = getIntent();
        ss=false;
        ib=false;
        ds=false;

        ckbox1=(CheckedTextView) findViewById(R.id.checkbox_1);
        ckbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckbox1.isChecked()){
                    ckbox1.setChecked(false);
                    ss=false;
                }
                else if(!ckbox1.isChecked()){
                    ckbox1.setChecked(true);
                    ss=true;
                }
            }
        });

        ckbox2=(CheckedTextView) findViewById(R.id.checkbox_2);
        ckbox2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(ckbox2.isChecked()){
                    ckbox2.setChecked(false);
                    ib=false;
                }
                else if(!ckbox2.isChecked()){
                    ckbox2.setChecked(true);
                    ib=true;
                }
            }

        });
        ckbox3= (CheckedTextView) findViewById(R.id.checkbox_3);
        ckbox3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(ckbox3.isChecked()){
                    ckbox3.setChecked(false);
                    ds=false;
                }
                else if(!ckbox3.isChecked()){
                    ckbox3.setChecked(true);
                    ds=true;
                }
            }
        });

        next= (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (ss==true){
                    Intent intent = new Intent(getApplicationContext(),Survey_1.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
                else if(ib==true){
                    Intent intent = new Intent(getApplicationContext(),inbody_survey1.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
                else if(ds==true){
                    Intent intent = new Intent(getApplicationContext(),inbody_survey1.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            }
        });

    }
}