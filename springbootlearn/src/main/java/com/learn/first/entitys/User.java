package com.learn.first.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.learn.first.commons.StringTools;


@Entity
public class User{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 32)
    private String userName;
    @Column(nullable = false,length = 32)
    private String passWord;
    @Column(nullable = false, unique = true,length = 32)
    private String phoneNum;

    @Transient
    private String noChange;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean checkPassWorl(String decodePass){
        if(this.passWord != null && this.passWord.equals(StringTools.getMD5(decodePass))){
            return true;
        }
        return false;
    }

    public void setPassWord(String passWord) {
        this.passWord = StringTools.getMD5(passWord);
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNoChange() {
        return noChange;
    }

    public void setNoChange(String noChange) {
        this.noChange = noChange;
    }
}