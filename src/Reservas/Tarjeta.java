package Reservas;

public class Tarjeta{
    private double numeroTarjeta;
    private String nombreYapellido;
    private String fechaVencimiento; //preguntar
    private int codigoSeguridad;
    private double dniTitular;

    public Tarjeta(double numeroTarjeta, String nombreYapellido, String fechaVencimiento, int codigoSeguridad, double dniTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreYapellido = nombreYapellido;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.dniTitular = dniTitular;
    }

    public double getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(double numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public double getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(double dniTitular) {
        this.dniTitular = dniTitular;
    }

}
