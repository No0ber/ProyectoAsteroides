package com.example.asteroidesjge;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlmacenPuntuacionesFicheroExtApl implements AlmacenPuntuaciones {
    //private static String FICHERO = "puntuaciones.txt";
    private static String FICHERO = Environment.getExternalStorageDirectory() + "/puntuaciones.txt";
    private Context context;

    public AlmacenPuntuacionesFicheroExtApl(Context context) {
        this.context = context;
    }

    public void guardarPuntuacion(int puntos, String nombre, long fecha){
        try {
            //FileOutputStream f = context.openFileOutput(FICHERO, Context.MODE_APPEND);
            FileOutputStream f = new FileOutputStream(FICHERO, true);
            String texto = puntos + " " + nombre + "\n";
            f.write(texto.getBytes());
            f.close();
        } catch (Exception e) {
            Log.e("Asteroides", e.getMessage(), e);
        }

        String stadoSD = Environment.getExternalStorageState();
        if (!stadoSD.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "No puedo escribir en la memoria externa",
                    Toast.LENGTH_LONG).show();
            return;
        }
    }
    public List<String> listaPuntuaciones(int cantidad) {
        List<String> result = new ArrayList<String>();
        try {
            //FileInputStream f = context.openFileInput(FICHERO);
            FileInputStream f = new FileInputStream(FICHERO);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(f));
            int n = 0;
            String linea;
            do {
                linea = entrada.readLine();
                if (linea != null) {
                    result.add(linea);
                    n++;
                }
            } while (n < cantidad && linea != null);
            f.close();
        } catch (Exception e) {
            Log.e("Asteroides", e.getMessage(), e);
        }

        String stadoSD = Environment.getExternalStorageState();
        if (!stadoSD.equals(Environment.MEDIA_MOUNTED) &&
                !stadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            Toast.makeText(context, "No puedo leer en la memoria externa",
                    Toast.LENGTH_LONG).show();
            return result;
        }
        
        return result;
    }
}