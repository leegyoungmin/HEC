package com.example.hec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.util.ArrayList;
import java.util.List;

public class KakaoLogin extends Activity {

    private DatabaseReference mDatabaseRef;
    public static class KakaoSessionCallback implements ISessionCallback {
        private Context mContext;
        private login login;

        public KakaoSessionCallback(Context context, login activity) {
            this.mContext = context;
            this.login = activity;
        }

        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(mContext, "KaKao 로그인 오류가 발생했습니다. " + e.toString(), Toast.LENGTH_SHORT).show();
        }

        protected void requestMe() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                public FirebaseAuth mFirebaseAuth;
                private DatabaseReference mDatabaseRef;

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    login.directToSecondActivity(false);
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    List<String> userInfo = new ArrayList<>();
                    userInfo.add(String.valueOf(result.getId()));
                    userInfo.add(result.getKakaoAccount().getProfile().getNickname());
                    userInfo.add(result.getKakaoAccount().getBirthday());
                    userInfo.add(result.getKakaoAccount().getEmail());
                    GlobalApplication mGlobalHelper = new GlobalApplication();

                    mGlobalHelper.setGlobalUserLoginInfo(userInfo);
                    mFirebaseAuth=FirebaseAuth.getInstance();
                    mDatabaseRef= FirebaseDatabase.getInstance().getReference("HEC");
                    mDatabaseRef.child("UserAccount").setValue(userInfo);
                    login.directToSecondActivity(true);
                }
            });
        }

    }
}
