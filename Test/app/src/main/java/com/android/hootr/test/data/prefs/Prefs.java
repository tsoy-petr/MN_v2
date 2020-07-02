package com.android.hootr.test.data.prefs;

public interface Prefs {

    boolean isLogin();

    String getAdress1CServer();
    String getAdressMongoServer();

    void setAdress1CServer(String adress1CServer);
    void setAdressMongoServer(String adressMongoServer);
    void setIsLogin(boolean isLogin);

}
