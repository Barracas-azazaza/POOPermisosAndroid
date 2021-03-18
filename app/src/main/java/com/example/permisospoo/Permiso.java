package com.example.permisospoo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permiso {

    private Activity activity;
    private int codigoRespuesta = 1;
    private int codigoHabilita = 100;
    private Context context;

    public Permiso(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public boolean statusPermiso(String permiso){
        int respuesta = ContextCompat.checkSelfPermission(context, permiso);
        if(respuesta == PackageManager.PERMISSION_GRANTED) return true;
        else return false;
    }
    public void Solicitar_Permiso(String permiso){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this.activity, permiso))
            Log.i(null, "solicitarPermiso: ");
        else
            ActivityCompat.requestPermissions(activity, new String[]
                    {permiso}, codigoRespuesta);
    }
}
