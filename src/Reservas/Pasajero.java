package Reservas;

public class Pasajero extends Persona
{
    private String origen;
    private Tarjeta tarjeta;

    public Pasajero(String nombre, String apellido, String dni, String telefono, String domicilio, String origen, Tarjeta tarjeta) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.origen = origen;
        this.tarjeta = tarjeta;
    }

    public Pasajero() {
    }
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public void mostrar()
    {
        System.out.println("\n-----------------------PASAJERO-----------------------");
        super.mostrarPersona();
        System.out.println("ORIGEN: " + origen);
        muestraTarjeta();
    }
    public void mostrarSoloPersona ()
    {
        super.mostrarPersona();
    }

    public void muestraTarjeta ()
    {
        System.out.println("\n---------------------TARJETA---------------------");
        System.out.println("NUMERO DE TARJETA: " + tarjeta.getNumeroTarjeta());
        System.out.println("NOMBRE Y APELLIDO DEL TITULAR: " + tarjeta.getNombreYapellido());
        System.out.println("DNI DEL TITULAR: " + tarjeta.getDniTitular());
        System.out.println("FECHA DE VENCIMIENTO: " + tarjeta.getFechaVencimiento());
        System.out.println("CODIGO DE SEGURIDAD: " + tarjeta.getCodigoSeguridad());
    }
}
