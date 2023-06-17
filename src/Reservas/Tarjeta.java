package Reservas;

public class Tarjeta{
    private String numeroTarjeta;
    private String nombreYapellido;
    private String fechaVencimiento; //preguntar
    private int codigoSeguridad;
    private String  dniTitular;

    public Tarjeta(String numeroTarjeta, String nombreYapellido, String fechaVencimiento, int codigoSeguridad, String dniTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreYapellido = nombreYapellido;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.dniTitular = dniTitular;
    }

    public Tarjeta() {
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
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

    public String getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(String dniTitular) {
        this.dniTitular = dniTitular;
    }

}
