package com.example.permisospoo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class Archivo {
    private Context context;
    private Activity activity;

    public Archivo(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void crearDirectorio(File file){
        if(!file.exists()){
            file.mkdirs();
        }
    }
    public void crearGuardarArchivo(String nombreArchivo, String informacion){
        File directorio = null;
        Permiso p1 = new Permiso(activity, context);
        p1.Solicitar_Permiso(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //Se verifica el permiso para versiones que es sólo para versiones posteriores a 6.0 ó M
        //Pero damos la opción para las otras versiones que no necesitan permisos
        if(p1.statusPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE) || Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            try{
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    //Para versiones menores o iguales a P
                    directorio = new File(Environment.getExternalStorageDirectory(), "ArchivoApp");
                    crearDirectorio(directorio);
                    Toast.makeText(context, "Ruta: "+directorio, Toast.LENGTH_LONG).show();
                }else{
                    //Versiones Q(10.0) o posteriores
                    directorio = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), "ArchivoApp");
                    crearDirectorio(directorio);
                    Toast.makeText(context, "Ruta:"+directorio, Toast.LENGTH_LONG).show();
                }
                if (directorio != null) {
                    File file = new File(directorio, nombreArchivo);
                    FileWriter writer = new FileWriter(file);
                    writer.append(informacion);
                    writer.flush();
                    writer.close();
                    Toast.makeText(context, "Se han guardado los datos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al crear directorio", Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context, "No hay permiso", Toast.LENGTH_SHORT).show();
        }
    }
}