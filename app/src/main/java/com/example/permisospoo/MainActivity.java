package com.example.permisospoo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvNivel;

    private String versionSO;
    private int versionSDK;
    private TextView tvVersionAndroid;
    private Context context;
    private Activity activity;
    private Bateria bateria;
    private EditText nombreArchivo;
    private Archivo archivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casteo();
        context = getApplicationContext();
        activity = this;
        bateria = new Bateria(tvNivel);
        registerReceiver(bateriaCarga,bateria.getBatteryFilter());
        VersionSO v1 = new VersionSO();
        versionSO = v1.Obtener_Version_SO();
        versionSDK = v1.Obtener_Version_SDK();
        tvVersionAndroid.setText("La versión de Android es: "+versionSO+" /SDK: "+versionSDK);
    }
    public void casteo(){
        tvVersionAndroid = (TextView) findViewById(R.id.tvVersionAndroid);
        tvNivel = (TextView) findViewById(R.id.tvNivel);
        nombreArchivo = (EditText) findViewById(R.id.etNombreArchivo);
    }

    BroadcastReceiver bateriaCarga = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            bateria.asignarNivelDeBateria(intent);

        }
    };

    public void habilitarBluetooth(View view){
        Bluetooth bluetooth = new Bluetooth(activity, context);
        if(bluetooth.habilitarBluetooth()){
            startActivityForResult(bluetooth.getHabilitaBT(), Bluetooth.getCodigo_habilitaBT());
        }
    }

    public void deshabilitarBluetooth(View view){
        Bluetooth bluetooth = new Bluetooth(activity, context);
        bluetooth.deshabilitarBluetooth();
    }
    @Override
    protected void onResume(){
        super.onResume();
        // instancia del objeto 'Archivo'
        archivo = new Archivo(context, activity);
    }
    //Inicio del almacenamiento
    public void guardarArchivo(View view) {
        String nombreFile = nombreArchivo.getText().toString()+".txt";
        String nivelBateria = tvNivel.getText().toString();
        archivo.crearGuardarArchivo(nombreFile, nivelBateria);
    }
}