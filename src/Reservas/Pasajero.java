package Reservas;

public class Pasajero extends Persona
{
    private String origen;
    private Tarjeta tarjeta;

    public Pasajero(String nombre, String apellido, String dni, String telefono, String domicilio, String origen) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.origen = origen;
    }

    public Pasajero() {
    }
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
    @Override
    public void mostrar()
    {
        System.out.println("\n-----------------------PASAJERO-----------------------");
        super.mostrarPersona();
        System.out.println("ORIGEN: " + origen);
    }

    public void agregarTarjeta (double nroTarjeta, String nombreApellido, String fechaVencimiento, int codigoSeguridad, double dni)
    {
        tarjeta = new Tarjeta(nroTarjeta, nombreApellido, fechaVencimiento, codigoSeguridad, dni);

    }
    public void muestraTarjeta ()
    {
        System.out.println("\n---------------------Reservas.Tarjeta---------------------");
        System.out.println(tarjeta.getNumeroTarjeta());
        System.out.println(tarjeta.getNombreYapellido());
        System.out.println(tarjeta.getDniTitular());
        System.out.println(tarjeta.getFechaVencimiento());
        System.out.println(tarjeta.getCodigoSeguridad());
    }
    ///CON LOS ARCHIVOS
    public void verHistorial ()
    {

    }
}
