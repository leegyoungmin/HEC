package com.example.hec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hec.Sasang_Survey.sex_Survey;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.auth.Session;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.response.model.User;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity {
    private Button mKakaoLoginBtn, mNaverLoginBtn, mGoogleLoginBtn;
    private LoginButton mKakaoLoginBtnBasic;

    private KakaoLogin.KakaoSessionCallback sessionCallback;

    final String NAVER_CLIENT_ID = "zQtbz1mqovnAUiFlo7RB";
    final String NAVER_CLIENT_SECRET = "fMosFMzLxn";
    private FirebaseAuth mGoogleLoginModule;



    Button.OnClickListener mGoogleLoginListener = new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(login.this, GoogleLogin.class);
            startActivity(intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mKakaoLoginBtn = findViewById(R.id.btn_kakao_login);
        mNaverLoginBtn = findViewById(R.id.btn_naver_login);
        mGoogleLoginBtn = findViewById(R.id.btn_google_login);
        mKakaoLoginBtnBasic = findViewById(R.id.btn_kakao_login_basic);

        mKakaoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKakaoLoginBtnBasic.performClick();

            }
        });

        mGoogleLoginBtn.setOnClickListener(mGoogleLoginListener);


        mGoogleLoginModule = FirebaseAuth.getInstance();
        if (!HasKakaoSession()  && !HasGoogleSession()) {
            sessionCallback = new KakaoLogin.KakaoSessionCallback(getApplicationContext(), login.this);
            Session.getCurrentSession().addCallback(sessionCallback);
        } else if (HasKakaoSession()) {
            sessionCallback = new KakaoLogin.KakaoSessionCallback(getApplicationContext(), login.this);
            Session.getCurrentSession().addCallback(sessionCallback);
            Session.getCurrentSession().checkAndImplicitOpen();
        } else if (HasGoogleSession()) {
            Intent intent = new Intent(login.this, GoogleLogin.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }


    private boolean HasKakaoSession() {
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            return false;
        }
        return true;
    }



    private boolean HasGoogleSession() {
        if (mGoogleLoginModule.getCurrentUser() == null) {
            return false;
        }
        return true;
    }

    public void directToSecondActivity(Boolean result) {
        Intent intent = new Intent(login.this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (result) {
            Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_SHORT).show();
            startActivity(intent);;
            finish();
        }
    }

}