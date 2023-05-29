public class Pasajero extends Persona
{
    private String origen;

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

    ///CON LOS ARCHIVOS
    public void verHistorial ()
    {

    }
}
