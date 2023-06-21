package Reservas;
import java.time.LocalDate;
import java.util.*;
import Excepciones.DNINoExiste;
import Excepciones.StringContieneLetras;
import Excepciones.Validacion;
import Servicios.Servicio;
import Enum.TiposDeMontosHabitaciones;

public class SistemaRecepcionista
{
    static final String nombreHotel = "Harmony Retreat";
    private LinkedHashMap<Integer, List<Habitacion>> habitaciones;
    private Set<Pasajero> pasajeros;
    private ArrayList<Servicio> servicios;

    public SistemaRecepcionista()
    {
        this.habitaciones = new LinkedHashMap<>();
        this.pasajeros = new HashSet<>();
        this.servicios = new ArrayList<>();
    }

    public LinkedHashMap<Integer, List<Habitacion>> getHabitaciones()
    {
        return habitaciones;
    }

    public void setHabitaciones(LinkedHashMap<Integer, List<Habitacion>> habitaciones) {
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

    public Factura reserva (Habitacion habitacionAReservar, Pasajero pasajeroReservador, int cantDias) throws IllegalArgumentException
    {
        ///Se le dice al usuario que ingrese que habitacion quiere (SIMPLE-NORMAL-PREMIUM).
        //Se busca la primer habitacion con esas caracteristicas y se pasa por parametro.
        //Se reserva esa habitacion y se realiza el pago.
        double monto;
        switch (habitacionAReservar.getTipo())
        {
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
        habitacionAReservar.setOcupadaONo(true);
        habitacionAReservar.setPasajeroQueLaOcupa(pasajeroReservador);
        habitacionAReservar.setCantDiasQueSeraOcupada(cantDias);
        return new Factura(pasajeroReservador, UUID.randomUUID(), LocalDate.now(), monto, habitacionAReservar);
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
        //Cancela la reserva.
        if (habitacion.getOcupadaONo())
        {
            habitacion.setOcupadaONo(false);
            habitacion.setPasajeroQueLaOcupa(null);
            habitacion.setCantDiasQueSeraOcupada(0);
            System.out.println("La habitacion : " + habitacion.getNumero() + " ha sido desocupada con exito.");
        }
        else
        {
            System.out.println("La habitacion: " + habitacion.getNumero() + " que desea desocupar esta actualmente sin alojaciones.");
        }
    }

    public void mostrarHabitacionesYdatosDeOcupantes ()
    {
        int i = 0;

        for (List <Habitacion> habi: habitaciones.values())
        {
            if (habi.get(i).getOcupadaONo())
            {
                habi.get(i).mostrarHabitacion();
                Pasajero pasajero = habi.get(i).getPasajeroQueLaOcupa();
                pasajero.mostrar();
            }
            i++;
        }
    }
    public void mostrarHabitacionesDisponibles()
    {
        for (List<Habitacion> habitacionList : habitaciones.values())
        {
            for (Habitacion habitacion : habitacionList)
            {
                if (!habitacion.getOcupadaONo())
                {
                    habitacion.mostrarHabitacion();
                }
            }
        }
    }

    ///DETALLAR MOTIVO
    public void mostrarHabitacionesNoDisponibles() throws NullPointerException
    {
        for (List<Habitacion> habitacionList : habitaciones.values())
        {
            for (Habitacion habitacion : habitacionList)
            {
                if (habitacion.getOcupadaONo())
                {
                    habitacion.mostrarHabitacion();
                    try
                    {
                        System.out.println(habitacion.getMotivo().getDescripcion());
                    }
                    catch (NullPointerException e)
                    {
                        System.out.println("No esta disponible ya que se encuentra ocupada.");
                    }
                }
            }
        }
    }

    public void verReservaPorDNI(String dni)
    {
        boolean encontrada = false;

        for (List<Habitacion> listaHabitaciones : this.habitaciones.values())
        {
            for (Habitacion habitacion : listaHabitaciones)
            {
                if (habitacion.getPasajeroQueLaOcupa() != null && habitacion.getPasajeroQueLaOcupa().getDni().equals(dni)) {
                    habitacion.mostrarHabitacion();
                    encontrada = true;
                    break; // Sale del bucle cuando se encuentra la reserva
                }
            }
            if (encontrada)
            {
                break; // Sale del bucle externo cuando se encuentra la reserva
            }
        }

        if (!encontrada)
        {
            System.out.println("No se encontró ninguna reserva con el DNI especificado.");
        }
    }
    public void busquedaPorDNIServicio (String dni) throws DNINoExiste
    {
        boolean flag = false;

        if (dni == null)
        {
            throw new DNINoExiste();
        }
        try
        {
            Validacion.validarStringNoLetras(dni);
            for(Servicio servicioBuscar: this.servicios)
            {
                Iterator<Pasajero> it = servicioBuscar.getPasajerosDelServicio().iterator();
                while(it.hasNext() && flag == false)
                {
                    if(dni.equals(it.next().getDni()))
                    {
                        System.out.println("El pasajero se encuentra anotado en el servicio: " + servicioBuscar.getTipoServicio());
                        flag = true;
                    }
                }
            }
            if (!flag)
            {
                throw new DNINoExiste();
            }
        }
        catch (StringContieneLetras e)
        {
            System.out.println("\nERROR: DNI INVALIDO\n");
        }
    }
}
