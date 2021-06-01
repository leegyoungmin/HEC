package com.example.hec;

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
    HashMap<String, Boolean> type = new HashMap();
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Intent result=getIntent();

        type.put("sasang",false);
        type.put("inbody",false);
        type.put("disease",false);

        ckbox1=(CheckedTextView) findViewById(R.id.checkbox_1);
        ckbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckbox1.isChecked()){
                    ckbox1.setChecked(false);
                    type.put("sasang",false);
                }
                else if(!ckbox1.isChecked()){
                    ckbox1.setChecked(true);
                    type.put("sasang",true);
                }
            }
        });

        ckbox2=(CheckedTextView) findViewById(R.id.checkbox_2);
        ckbox2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(ckbox2.isChecked()){
                    ckbox2.setChecked(false);
                    type.put("inbody",false);
                }
                else if(!ckbox2.isChecked()){
                    ckbox2.setChecked(true);
                    type.put("inbody",true);
                }
            }

        });
        Log.i("inbody", String.valueOf(type));
        ckbox3= (CheckedTextView) findViewById(R.id.checkbox_3);
        ckbox3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(ckbox3.isChecked()){
                    ckbox3.setChecked(false);
                    type.put("disease",false);
                }
                else if(!ckbox3.isChecked()){
                    ckbox3.setChecked(true);
                    type.put("disease",true);
                }
            }
        });

        Log.i("example", String.valueOf(type));
        next= (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i("sasang", String.valueOf(type.get("sasang")));
                Log.i("inbody", String.valueOf(type.get("inbody")));
                Intent intent = new Intent(getApplicationContext(),Sasang_result.class);
                intent.putExtra("type",type);
                if (type.get("sasang")==true){
                    Intent intent_1 = new Intent(getApplicationContext(),Survey_1.class);
                    startActivity(intent_1);
                    overridePendingTransition(0,0);
                }
                else if(type.get("inbody")==true){
                    Intent intent_1= new Intent(getApplicationContext(),inbody_survey1.class);
                    intent.putExtra("type",type);
                    startActivity(intent_1);
                    overridePendingTransition(0,0);
                }
                else if(type.get("disease")==true){

                }
            }
        });


        Log.i("example", String.valueOf(type));

    }
}