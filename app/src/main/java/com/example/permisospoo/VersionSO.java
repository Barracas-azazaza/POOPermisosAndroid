package com.example.permisospoo;

import android.os.Build;

public class VersionSO {
    String versionSO;
    int versionSDK;

    public String Obtener_Version_SO(){
        return versionSO = Build.VERSION.RELEASE;
    }
    public int Obtener_Version_SDK(){
        return versionSDK = Build.VERSION.SDK_INT;
    }
}
