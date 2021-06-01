package com.example.hec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.hec.Inbody.inbody_survey1;

import java.util.ArrayList;
import java.util.HashMap;

public class Sasang_result extends AppCompatActivity {

    TextView textview;
    HashMap result = new HashMap();
    Button next;
    int sex;
    double te_score, se_score, sy_score;
    HashMap type = new HashMap();
    ArrayList<Double> survey_result;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sasang_activity_result);

        textview = findViewById(R.id.text1);
        next=findViewById(R.id.next_survey);

        sex = getIntent().getIntExtra("sex", 1);

        result = (HashMap) getIntent().getSerializableExtra("result");
        type=(HashMap<String,Boolean>) getIntent().getSerializableExtra("type");
        Log.i("type_1", String.valueOf(type));
        textview.setText(String.valueOf(result));
        survey_result= result_cal(result);


        Log.i("result_type", String.valueOf(type));

        for(int i=0; i<4;i++){
            Log.i("survey_result", String.valueOf(survey_result.get(i)));
        }

        Log.i("data", String.valueOf(result.keySet()));
        Log.i("data_result", String.valueOf(result_cal(result)));
        Log.i("type", String.valueOf(type));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    private ArrayList result_cal(HashMap result) {
        ArrayList<Double> result_sasang = new ArrayList<Double>();

        for (int i = 1; i <= result.size(); i++) {
            String answer = "answer" + i;
            int values = (int) result.get(answer);
                if (sex == 1) {

                    switch (answer) {
                        case "answer1":
                            switch (values) {
                                case 1:
                                    te_score = 1.621;
                                    se_score = -5.496;
                                    sy_score = 1.444;
                                    break;
                                case 2:
                                    te_score = 0;
                                    se_score = 0;
                                    sy_score = 0;
                                    break;
                                case 3:
                                    te_score = 0;
                                    se_score = 3.507;
                                    sy_score = 1.444;
                                    break;
                            }
                            break;
                        case "answer2":
                            switch (values) {
                                case 1:
                                    te_score += -6.467;
                                    se_score += 0;
                                    sy_score += 7.432;
                                    break;
                                case 2:
                                    te_score += 2.521;
                                    se_score += 0;
                                    sy_score += -2.97;
                                    break;
                                case 3:
                                    te_score += 2.829;
                                    se_score += 0;
                                    sy_score += -3.087;
                                    break;

                            }
                            break;
                        case "answer3":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -5.655;
                                    sy_score += 3.969;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 1.991;
                                    sy_score += -1.521;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 3.04;
                                    sy_score += -2.04;
                                    break;

                            }
                            break;
                        case "answer4":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -3.274;
                                    sy_score += 3.923;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 2.829;
                                    sy_score += -3.087;
                                    break;

                            }
                            break;
                        case "answer5":
                            switch (values) {
                                case 1:
                                    te_score += 2.113;
                                    se_score += -6.669;
                                    sy_score += 1.419;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 3.157;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 2.282;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer6":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -1.77;
                                    sy_score += 1.444;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += -1.869;
                                    break;

                            }
                            break;
                        case "answer7":
                            switch (values) {
                                case 1:
                                    te_score += 1.721;
                                    se_score += -4.952;
                                    sy_score += 1.596;
                                    break;
                                case 2:
                                    te_score += -3.565;
                                    se_score += 8.435;
                                    sy_score += -1.596;
                                    break;
                                case 3:
                                    te_score += -1.721;
                                    se_score += 6.943;
                                    sy_score += -1.596;
                                    break;

                            }
                            break;
                        case "answer8":
                            switch (values) {
                                case 1:
                                    te_score += 6.535;
                                    se_score += -8.747;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += -4.975;
                                    se_score += 6.309;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += -1.342;
                                    se_score += 1.342;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer9":
                            switch (values) {
                                case 1:
                                    te_score += 4.861;
                                    se_score += -4.587;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += -2.695;
                                    se_score += 4.493;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer10":
                            switch (values) {
                                case 1:
                                    te_score += 2.186;
                                    se_score += -3.669;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += -4.997;
                                    se_score += 6.039;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer11":
                            te_score += 0;
                            se_score += 0;
                            sy_score += 0;
                            break;
                        case "answer12":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += 1.894;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += -2.48;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer13":
                            switch (values) {
                                case 1:
                                    te_score += -6.129;
                                    se_score += 6.422;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 9.239;
                                    se_score += -7.655;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;

                            }
                            break;

                        case "answer14":
                            switch (values) {
                                case 1:
                                    te_score += -1.967;
                                    se_score += 3.134;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += -1.368;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;

                            }
                            break;

                    }
                } else if (sex == 2) {

                    switch (answer) {
                        case "answer1":
                            switch (values) {
                                case 1:
                                    te_score = 4.496;
                                    se_score = -9.484;
                                    sy_score = 0;
                                    break;
                                case 2:
                                    te_score = 0;
                                    se_score = 0;
                                    sy_score = 0;
                                    break;
                                case 3:
                                    te_score = -3.576;
                                    se_score = 7.991;
                                    sy_score = 0;
                                    break;
                            }
                            break;
                        case "answer2":
                            switch (values) {
                                case 1:
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += -1.844;
                                    break;

                                case 3:
                                    te_score += 0;
                                    se_score += 2.711;
                                    sy_score += -1.746;
                                    break;

                            }
                            break;
                        case "answer3":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -7.163;
                                    sy_score += 2.089;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 2.829;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 2.711;
                                    sy_score += -1.746;
                                    break;

                            }
                            break;
                        case "answer4":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -7.185;
                                    sy_score += 4.404;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 5.881;
                                    sy_score += -5.836;
                                    break;

                            }
                            break;
                        case "answer5":
                            switch (values) {
                                case 1:
                                    te_score += 4.793;
                                    se_score += -7.32;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += -1.918;
                                    sy_score += 1.419;
                                    break;
                                case 3:
                                    te_score += -3.437;
                                    se_score += 12;
                                    sy_score += -2.426;
                                    break;

                            }
                            break;
                        case "answer6":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -5.61;
                                    sy_score += 2.758;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 3.04;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 1.495;
                                    sy_score += -1.795;
                                    break;

                            }
                            break;
                        case "answer7":
                            switch (values) {
                                case 1:
                                    te_score += 2.497;
                                    se_score += -5.474;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += -2.497;
                                    se_score += 7.539;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += -3.839;
                                    se_score += 9.004;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer8":
                            switch (values) {
                                case 1:
                                    te_score += 5.056;
                                    se_score += -8.423;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += -4.838;
                                    se_score += 4.747;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 1.991;
                                    sy_score += -1.342;
                                    break;

                            }
                            break;
                        case "answer9":
                            switch (values) {
                                case 1:
                                    te_score += 8.259;
                                    se_score += -5.474;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += -3.772;
                                    se_score += 5.043;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer10":
                            switch (values) {
                                case 1:
                                    te_score += 6.714;
                                    se_score += -7.723;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += -2.592;
                                    se_score += 5.971;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += -1.596;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                        case "answer11":
                            switch (values) {
                                case 1:
                                    te_score += 0;
                                    se_score += -1.47;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 2.354;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 2.426;
                                    sy_score += -1.546;
                                    break;

                            }
                            break;
                        case "answer12":
                            te_score += 0;
                            se_score += 0;
                            sy_score += 0;
                            break;
                        case "answer13":
                            switch (values) {
                                case 1:
                                    te_score += -1.546;
                                    se_score += 2.64;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 3.599;
                                    se_score += -4.77;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += 0;
                                    sy_score += 0;
                                    break;

                            }
                            break;

                        case "answer14":
                            switch (values) {
                                case 1:
                                    te_score += -3.064;
                                    se_score += 4.929;
                                    sy_score += 0;
                                    break;
                                case 2:
                                    te_score += 0;
                                    se_score += -1.869;
                                    sy_score += 0;
                                    break;
                                case 3:
                                    te_score += 0;
                                    se_score += -1.316;
                                    sy_score += 0;
                                    break;

                            }
                            break;
                    }

                }
                result_sasang.add(te_score);
                result_sasang.add(se_score);
                result_sasang.add(sy_score);

            }
            return result_sasang;
    }
}

