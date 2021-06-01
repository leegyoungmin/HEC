package com.example.hec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hec.Sasang_Survey.sex_Survey;

public class login extends AppCompatActivity {

    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login=findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_login.setPressed(true);
                Intent intent = new Intent(getApplicationContext(),sex_Survey.class);
                intent.putExtra("result",1);
                startActivity(intent);
                overridePendingTransition(0,0);

            }
        });
    }
}