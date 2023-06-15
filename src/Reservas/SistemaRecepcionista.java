package Reservas;

import java.time.LocalDate;
import java.util.*;

import Reservas.Factura;
import Reservas.Pasajero;
import Servicios.Servicio;
import Enum.TiposDeMontosHabitaciones;

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
        ///Se le dice al usuario que ingrese que habitacion quiere (SIMPLE-NORMAL-PREMIUM).
        //Se busca la primer habitacion con esas caracteristicas y se pasa por parametro.
        //Se reserva esa habitacion y se realiza el pago.
        double monto;
        switch (habitacionAReservar.getTipo()) {
            case SIMPLE:
                monto = TiposDeMontosHabitaciones.SIMPLE.getPrecioDia();
                break;
            case DOBLE:
                monto = TiposDeMontosHabitaciones.DOBLE.getPrecioDia();
                break;
            case CUADRUPLE:
                monto = TiposDeMontosHabitaciones.CUADRUPLE.getPrecioDia();
                break;
            default:
                throw new IllegalArgumentException("Tipo de habitación inválido");
        }

        Factura factura = new Factura(pasajeroReservador, UUID.randomUUID(), LocalDate.now(), monto, habitacionAReservar);

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
