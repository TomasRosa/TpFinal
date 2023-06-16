package Reservas;

import java.time.LocalDate;
import java.util.*;

import Reservas.Factura;
import Reservas.Pasajero;
import Servicios.Servicio;
import Enum.TiposDeMontosHabitaciones;
import Enum.TiposDeServicios;

public class SistemaRecepcionista //hay q implementar interfaz generica
{
    static final String nombreHotel = "Harmony Retreat";
    private LinkedHashMap<Integer, List<Habitacion>> habitaciones;
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

    public void checkIn (Habitacion habitacion, Pasajero pasajero)
    {
        //Este metodo le asigna un pasajero a la habitacion y asigna la habitacion como ocupada
        //En el main se utiliza cuando el pasajero quiere hospedarse en el hotel
        habitacion.setOcupadaONo(true);
        habitacion.setPasajeroQueLaOcupa(pasajero);
    }

    public void checkOut (Habitacion habitacion)
    {
        //Este metodo libera la habitacion cuando el huesped finaliza su estadia
        //En el main se utilizaria cuando el huesped abandonda el hotel
        habitacion.setOcupadaONo(false);
        habitacion.setPasajeroQueLaOcupa(null);
    }

    public void mostrarHabitacionesYdatosDeOcupantes ()
    {
        for (Habitacion habi: habitaciones.values())
        {
            if (habi.getOcupadaONo() == false)
            {
                habi.toString();
                Pasajero pasajero = habi.getPasajeroQueLaOcupa();
                pasajero.mostrar();
            }
        }
    }

    public void mostrarHabitacionesDisponibles ()
    {
        for (Habitacion habi: habitaciones.values())
        {
            if (habi.getOcupadaONo() == false)
            {
                habi.toString();
            }
        }
    }

    ///DETALLAR MOTIVO
    public void mostrarHabitacionesNoDisponibles ()
    {
        for (Habitacion habi: habitaciones.values())
        {
            if (habi.getOcupadaONo() == true)
            {
                habi.toString();
                //System.out.println(habi.getMotivo().name()); HACER EL ENUM "TIPO" DE HABITACION EN EL PACKAGE DE ENUM
            }
        }
    }

    public void verHistorialGeneral() //ESTO ES CON ARCHIVO
    {

    }

    public void verHistorialPasajeroParticular() //ESTO ES CON ARCHIVO
    {

    }

    public void mostrarTodosServicios()
    {
        TiposDeServicios[] tipos = TiposDeServicios.values();
        for (int i = 0; i < tipos.length; i++)
        {
            System.out.println(tipos[i]);
        }
    }

    public void verReservaPorDNI(String dni)
    {

    }
}
