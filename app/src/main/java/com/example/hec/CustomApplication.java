package com.example.hec;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.sdk.common. KakaoSdk;
public class CustomApplication extends Application
{
    private static CustomApplication instance;
   @Override
   public void onCreate(){
       super.onCreate();
       instance=this;
       //kakao Sdk 초기화

   }
}
