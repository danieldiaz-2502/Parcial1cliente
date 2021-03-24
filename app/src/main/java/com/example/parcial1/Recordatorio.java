package com.example.parcial1;

public class Recordatorio {

    private int posx, posy;
    private String mensaje, importancia, confirmacion;

    public Recordatorio(int posx, int posy, String mensaje, String importancia,String confirmacion) {
        this.posx = posx;
        this.posy = posy;
        this.mensaje = mensaje;
        this.importancia = importancia;
        this.confirmacion = confirmacion;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
}
