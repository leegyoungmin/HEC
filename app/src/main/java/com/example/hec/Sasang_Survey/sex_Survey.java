package com.example.hec.Sasang_Survey;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hec.R;
import com.example.hec.Sasang_result;
import com.example.hec.choose;

public class sex_Survey extends AppCompatActivity {

    Button btn_1,btn_2,btn_3,next;
    int value;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sasang_activity_sex_survey);


        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        next=(Button)findViewById(R.id.next);

        btn_1.setClickable(true);
        btn_1.setPressed(true);
        btn_2.setPressed(true);
        btn_2.setClickable(true);
        next.setClickable(false);
        next.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sasang_result.class);
                intent.putExtra("sex",value);
                startActivity(intent);
                Intent intent_2=new Intent(getApplicationContext(), choose.class);
                startActivity(intent_2);
                Log.i("data", String.valueOf(value));
                overridePendingTransition(0,0);
            }
        });


    }
    public void onclick(View view){
        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        next=(Button)findViewById(R.id.next);

        switch (view.getId()){
            case R.id.btn_1:
                value=1;
                btn_1.setSelected(true);
                btn_2.setSelected(false);
                next.setPressed(true);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);


                break;
            case R.id.btn_2:
                value=2;
                btn_2.setSelected(true);
                btn_1.setSelected(false);
                next.setPressed(true);
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);


                break;

        }
    }

}

