package com.example.permisospoo;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Bluetooth {
    private static final int codigo_respuesta_bt = 1;
    private static final int codigo_habilitaBT = 100;
    private BluetoothAdapter btAdaptador;
    private boolean bandera = false;
    private Context context;
    private Activity activity;
    private Intent habilitaBT;

    public Bluetooth(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public static int getCodigo_habilitaBT() {
        return codigo_habilitaBT;
    }

    public Intent getHabilitaBT() {
        return habilitaBT;
    }

    public boolean habilitarBluetooth(){
        metodoBandera();
        Permiso p1 = new Permiso(activity, context);
        if(btAdaptador == null){
            Log.i(null, "No tiene bluetooth");
            Toast.makeText(context, "El dispositivo no posee bluetooth", Toast.LENGTH_SHORT).show();
            btAdaptador.disable();
            return false;
        }
        if(!btAdaptador.isEnabled()) {
            p1.Solicitar_Permiso(Manifest.permission.BLUETOOTH);
             habilitaBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            return true;
        }
        else{
            Toast.makeText(context, "Ya está habilitado el bluetooth", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }
    public void deshabilitarBluetooth(){
        metodoBandera();
        if(btAdaptador.isEnabled()){
            btAdaptador.disable();

        }else{
            Toast.makeText(context, "Ya está deshabilitado el bluetooth", Toast.LENGTH_LONG).show();
        }


    }
    public void metodoBandera(){
        if(bandera == false){
            btAdaptador = BluetoothAdapter.getDefaultAdapter();
            bandera = true;
        }
    }
}
