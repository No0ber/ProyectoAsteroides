package com.example.asteroidesjge;

import java.util.List;

public interface AlmacenPuntuaciones {
    public void guardarPuntuacion(int puntos, String nombre, long fecha);
    public List<String> listaPuntuaciones(int cantidad);
}