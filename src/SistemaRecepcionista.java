import java.util.ArrayList;

public class SistemaRecepcionista //hay q implementar interfaz generica
{
    static final String nombreHotel = "Harmony Retreat";
    private ArrayList<Habitacion> habitaciones;

    private ArrayList<Pasajero> pasajeros;

    private ArrayList<Servicio> servicios;

    public SistemaRecepcionista()
    {
        this.habitaciones = new ArrayList<Habitacion>();
        this.pasajeros = new ArrayList<Pasajero>();
        this.servicios = new ArrayList<Servicio>();
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Factura reserva (Habitacion habitacionAReservar, Pasajero pasajeroReservador)
    {
        Factura factura = new Factura();
        return factura;
    }

    public void checkIn ()
    {

    }

    public void checkOut ()
    {

    }

    public void mostrarHabitacionesYdatosDeOcupantes ()
    {

    }

    public void mostrarHabitacionesDisponibles ()
    {

    }

    ///DETALLAR MOTIVO
    public void mostrarHabitacionesNoDisponibles ()
    {

    }

    public void verHistorialGeneral()
    {

    }

    public void verHistorialPasajeroParticular()
    {

    }

    public void mostrarTodosServicios()
    {

    }

    public void verReservaPorDNI(String dni)
    {

    }
}
