package com.example.permisospoo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.TextView;

public class Bateria {
    private TextView tvNivel;
    private IntentFilter batteryFilter;


    public Bateria(TextView tvNivel){
        this.tvNivel = tvNivel;
        batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    }

    public IntentFilter getBatteryFilter() {
        return batteryFilter;
    }

    public void asignarNivelDeBateria(Intent intent){
        int escala = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int nivelActual = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        if(escala == 100){
            tvNivel.setText("Nivel de batería: "+nivelActual+"%");
        }else{
            int nivel = 0;
            if(nivelActual>=0 && escala >0){
                nivel = (nivelActual*100)/escala;
                tvNivel.setText("Nivel de batería: "+nivel+"%");
            }
        }
    }

}
