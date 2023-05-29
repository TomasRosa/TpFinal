import java.util.ArrayList;

public class SistemaRecepcionista
{
    static final String nombreHotel = "Harmony Retreat";
    private ArrayList<Habitacion> habitaciones;

    private ArrayList<Pasajero> pasajeros;

    private ArrayList<Servicio> servicios;

    public SistemaRecepcionista(ArrayList<Habitacion> habitaciones, ArrayList<Pasajero> pasajeros, ArrayList<Servicio> servicios) {
        this.habitaciones = habitaciones;
        this.pasajeros = pasajeros;
        this.servicios = servicios;
    }

    public SistemaRecepcionista() {
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

    public void reserva ()
    {

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
