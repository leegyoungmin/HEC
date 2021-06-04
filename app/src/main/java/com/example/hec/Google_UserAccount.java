package com.example.hec;

/**
 * 사용자 계정 정보 모델 클래스
 */
public class Google_UserAccount
{
    private String idTokwn; //Firebase Uid(고유 토큰 정보)
    private String emailId; //이메일 아이디
    private String password; //비밀번호
    private String sex; //성별
    private int age; //나이
    private String nickname; //닉네임
    private String name; //이름
    private String Email;
    private String Profile;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Google_UserAccount() { }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdTokwn() {
        return idTokwn;
    }

    public void setIdTokwn(String idTokwn) {
        this.idTokwn = idTokwn;
    }

}
