import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class SistemaRecepcionista //hay q implementar interfaz generica
{
    static final String nombreHotel = "Harmony Retreat";
    private LinkedHashMap<Integer, Habitacion> habitaciones;
    private Set<Pasajero> pasajeros;

    private ArrayList<Servicio> servicios;

    public SistemaRecepcionista()
    {
        this.habitaciones = new LinkedHashMap<>();
        this.pasajeros = new HashSet<>();
        this.servicios = new ArrayList<Servicio>();
    }

    public LinkedHashMap<Integer, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(LinkedHashMap<Integer, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Set<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Set<Pasajero> pasajeros) {
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
