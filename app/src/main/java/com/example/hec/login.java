package com.example.hec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터 베이스
    private EditText mEtEmail,mEtPwd;  //로그인 입력필드
    private Button mBtnRegister;  //회원가입 버튼
    private Button btn_login;
    private SignInButton google_btn;
    private SignInButton facebook_btn;
    private Button btn_facebook;
    private GoogleApiClient googleApiClient; //구글 API클라이언트 객체
    private static final int REQ_SIGN_GOOGLE=100; //구글 로그인 결과 코드



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth=FirebaseAuth.getInstance();

        //파이어베이스 인증 초기화
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("HEC");

        mEtEmail=findViewById(R.id.et_email);
        mEtPwd=findViewById(R.id.et_pwd);

        //싸인버튼이 눌릴때 기본적으로 시행되는 옵션
        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        SignInButton google_btn=(SignInButton) findViewById(R.id.google_btn);
        google_btn.setOnClickListener(new View.OnClickListener() {//구글로그인 버튼을 클릭했을 때 이곳을 수행
            @Override
            public void onClick(View v) {
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,REQ_SIGN_GOOGLE);

            }
        });


        btn_login=findViewById(R.id.login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청
                String strEmail=mEtEmail.getText().toString();
                String strPwd=mEtPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 성공
                            Intent intent=new Intent(login.this,Kakao_login.class);
                            startActivity(intent);
                            Toast.makeText(login.this, "로그인할게요 뿌잉", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(login.this,"로그인 오류에요 뿌잉",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        Button btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 화면으로 이동
                Intent intent=new Intent(login.this,sign_up.class);
                startActivity(intent);
            }
        });
    }

    @Override  //구글 로그인 인증을 요청 했을 때 결과 값을 돌려 받는 곳.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_SIGN_GOOGLE){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){//인증결과가 성공적인경우
                GoogleSignInAccount account=result.getSignInAccount(); //account라는 데이터는 구글로그인 정보를 담고있음.(닉네임,프로필사진Url,이메일 주소etc)
                resultLogin(account);  //로그인 결과 값 출력 수행하려는 메서드
            }
        }

    }

    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){//로그인 성공했을 경우
                            Toast.makeText(login.this,"로그인할게요 뿌잉",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),sign_up.class);
                            startActivity(intent);
                            intent.putExtra("nickName",String.valueOf(account.getDisplayName()));
                            intent.putExtra("photoUrl",String.valueOf(account.getPhotoUrl())); //특정 자료형을 String 형태로 변환
                            startActivity(intent);
                        }else{
                            //로그인이 실패했으면
                            Toast.makeText(login.this,"로그인 오류에요 뿌잉",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {

    }

}