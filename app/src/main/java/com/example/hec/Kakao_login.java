package com.example.hec;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.auth.AuthType;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.sdk.user.model.Profile;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.KakaoUtilService;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.chrono.IsoChronology;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2016-01-26.
 */
public class Kakao_login extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private LoginButton sign_btn;
    private Button logout_btn;
    private SessionCallback sessionCallback;
    String token="";
    String name="";
    Session session;
    private static final String TAG="Kakao_login";
    Intent intent=new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);
        sign_btn=findViewById(R.id.btn_kakao);
        logout_btn=findViewById(R.id.logout_button);

        mFirebaseAuth=FirebaseAuth.getInstance();

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("HEC"); //파이어베이스 인증 초기화

        sessionCallback=new SessionCallback(); //카카오톡 로그인 콜백 받기
        Session.getCurrentSession().addCallback(sessionCallback);

        //loadShared();  //자기 카카오톡 프로필 정보 및 디비정보 쉐어드에 저장해놨던거 불러오기
        sign_btn.setOnClickListener(v->{
            if(Session.getCurrentSession().checkAndImplicitOpen()){
                Log.d(TAG,"onClick:로그인 세션살아있음");
                //카카오톡 로그인 시도(창이 안뜬다.)
                requestMe();
            }else{
                Log.d(TAG,"onClick:로그인 세션 끝남");
                //카카오 로그인 시도(창이 뜬다.)
                session.open(AuthType.KAKAO_LOGIN_ALL,Kakao_login.this);
            }
        });


        logout_btn.setOnClickListener(v->{
            Log.d(TAG,"onCreate:click");
            UserManagement.getInstance()
                    .requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult){
                            super.onSessionClosed(errorResult);
                            Log.d(TAG,"onSessionClosed: "+errorResult.getErrorMessage());
                        }

                        @Override
                        public void onCompleteLogout() {
                            if(sessionCallback!=null){
                                Session.getCurrentSession().removeCallback(sessionCallback);
                                FirebaseAuth.getInstance().signOut();//파이어베이스 로그아웃
                            }
                            Log.d(TAG,"onCompleteLogout:logout ");
                        }
                    });
        });


    }


    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    private class SessionCallback implements ISessionCallback{

        @Override
        public void onSessionOpened() {
            requestMe();
            //redirectSignupActivity(); //세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception!=null){
                Logger.e(exception);
            }
            setContentView(R.layout.activity_login);
        }

        public void requestMe() {
        }
    }


    private void requestMe() //유저의 정보를 받아오는 함수
    {

        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message="failed to get user info.msg="+errorResult;
                Logger.d(message);

                ErrorCode result=ErrorCode.valueOf(errorResult.getErrorCode());
                if(result==ErrorCode.CLIENT_ERROR_CODE){
                    finish();
                }else{
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSuccess(MeV2Response userProfile) {
                Logger.d("UserProfile: "+userProfile);
                Log.d(TAG,"유저가입성공");
                //create a new user with a first and last name
                //유저 카카오톡 아이디 디비에 넣음(첫 가입인 경우에만 디비 저장)
                Map<String,String> user=new HashMap<>();
                user.put("token",userProfile.getId()+"");
                user.put("name",userProfile.getNickname()+"");
                FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG,"유저정보삽입성공");
                        saveShared(userProfile.getId()+"",userProfile.getNickname());
                    }
                });
                redirectHomeActivity();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }


        });
    }
    private void redirectHomeActivity(){
        startActivity(new Intent(this,choose.class));
    }
    private void redirectLoginActivity(){
        final Intent intent=new Intent(this,login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    private void saveShared(String id, String name){
        SharedPreferences pref=getSharedPreferences("profile",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("token",id);
        editor.putString("name",name);
        editor.apply();
    }
    private void loadShared(){
        SharedPreferences pref=getSharedPreferences("profile",MODE_PRIVATE);
        token=pref.getString("token","");
        name=pref.getString("name","");
    }
}