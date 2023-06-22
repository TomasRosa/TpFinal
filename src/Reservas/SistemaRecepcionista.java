package Reservas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Empleados.Empleado;
import Excepciones.*;
import Servicios.Servicio;
import Enum.TiposDeMontosHabitaciones;
import com.sun.tools.javac.Main;

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
    public Pasajero checkIn (Habitacion habitacion, Scanner teclado)
    {
        Pasajero pasajero= cargarUnPasajero(teclado);

        habitacion.setOcupadaONo(true);
        habitacion.setPasajeroQueLaOcupa(pasajero);

        return pasajero;
    }
    public static Tarjeta cargarTarjeta (Scanner scan,String intento)
    {
        String nroTarjeta = " ";
        String nombreYApellido = " ";
        String fechaVencimiento = " ";
        int codigoSeguridad = 0;
        String dni = " ";
        boolean flag = false;

        do
        {
            System.out.println("Ingrese el numero de la tarjeta. ");
            try
            {
                nroTarjeta = scan.nextLine();
                flag = Validacion.validarStringNoLetras(nroTarjeta);
                try
                {
                    flag = Validacion.validarNroTarjeta(nroTarjeta);
                }
                catch (LongitudException e)
                {
                    System.out.println("\nError: la longitud no es adecuada.\n");
                    flag=false;
                }
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nError: el apellido contiene numeros\n");
                flag=false;
            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese el nombre y apellido titular de la tarjeta. ");
            try
            {
                nombreYApellido = scan.nextLine();
                flag = Validacion.validarStringNoNumeros(nombreYApellido);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nError: el nombre y apellido contiene numeros\n");
                flag=false;

            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese la fecha de vencimiento de la tarjeta. dd/mm/aaaa ");
            try
            {
                fechaVencimiento = scan.nextLine();
                flag = Validacion.validarFecha(fechaVencimiento);

            }catch (FechaInvalida e)
            {
                System.out.println("\nError: fecha invalida\n");
                flag=false;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaVencimiento,formatter);
            try
            {
                flag = Validacion.validarFechaVencimientoTarjeta(fecha);
            }
            catch (FechaVencidaException e)
            {
                System.out.println("La fecha de su tarjeta esta vencida. Asegurese de escribirla bien. (dd/MM/yyyy)");
                flag = false;
            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese el codigo de seguridad de la tarjeta. (3 digitos) ");
            try
            {
                codigoSeguridad = scan.nextInt();
                flag = Validacion.validarCodigoSeguridad(codigoSeguridad);
            }
            catch (CodigoSeguridadException e)
            {
                System.out.println("\nERROR: EL CODIGO DE SEGURIDAD NO ES APTO.\n");
                flag=false;
            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese el DNI del pasajero");
            try
            {
                dni = scan.next();
                scan.nextLine();
                flag = Validacion.validarStringNoLetras(dni) && Validacion.validarDniSeaIgualATarjeta(dni,intento) && Validacion.validarLongitudDNI(dni);
            }
            catch (LongitudException e)
            {
                System.out.println("\n LA LONGITUD DEL DNI NO ES APTA \n");
                flag=false;
            }
            catch (DniTarjetaPersonaException e)
            {
                System.out.println("\n EL DNI INGRESADO NO ES EL MISMO QUE EL DE LA PERSONA TITULAR.");
                flag = false;
            }
            catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                flag=false;
            }

        }while (!flag);
        return new Tarjeta(nroTarjeta,nombreYApellido,fechaVencimiento,codigoSeguridad,dni);
    }
    public static Pasajero cargarUnPasajero (Scanner teclado)
    {
        boolean flag = false;
        String nombre = "";
        String apellido = "";
        String dni = "";
        String num= "";

        do
        {
            System.out.println("Ingrese el nombre del pasajero");
            try
            {
                nombre = teclado.next();
                flag = Validacion.validarStringNoNumeros(nombre);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL NOMBRE CONTIENE NUMEROS\n");
                flag=false;
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el apellido del pasajero");
            try
            {
                apellido = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoNumeros(apellido);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL APELLIDO CONTIENE NUMEROS\n");
                flag=false;
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el DNI del pasajero");
            try
            {
                dni = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoLetras(dni) && Validacion.validarLongitudDNI(dni);
            }
            catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                flag=false;
            }
            catch (LongitudException e)
            {
                System.out.println("\nERROR: EL DNI NO TIENE LA LONGITUD ADECUADA\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el numero de telefono del pasajero");
            try
            {
                num = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoLetras(num);
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL NUMERO CONTIENE LETRAS\n");
                flag=false;
            }
        }while (!flag);

        System.out.println("Ingrese el domicilio del pasajero");
        String domicilio = teclado.next();
        teclado.nextLine();

        System.out.println("Ingrese el origen del pasajero");
        String origen = teclado.nextLine();

        Tarjeta t1 = cargarTarjeta(teclado,dni);

        return new Pasajero(nombre,apellido,dni,num,domicilio,origen,t1);
    }

    public void checkOut (Habitacion habitacion)
    {
        //Cancela la reserva.
        if (habitacion.getOcupadaONo() && habitacion.getPasajeroQueLaOcupa() != null)
        {
            habitacion.setOcupadaONo(false);
            habitacion.setPasajeroQueLaOcupa(null);
            habitacion.setCantDiasQueSeraOcupada(0);
            System.out.println("La habitacion : " + habitacion.getNumero() + " ha sido desocupada con exito.");
            habitacion.mostrarHabitacion();
        }
        else if(habitacion.getOcupadaONo() && habitacion.getPasajeroQueLaOcupa() == null)
        {
            habitacion.setOcupadaONo(false);
            habitacion.setMotivo(null);
            System.out.println("La habitacion ocupada por algun motivo ha sido desocupada con exito.");
            habitacion.mostrarHabitacion();
        }
        else
        {
            System.out.println("La habitacion: " + habitacion.getNumero() + " que desea desocupar esta actualmente sin alojaciones.");
        }
    }
    public Habitacion buscarHabitacionPorNumero (int nroHabitacion)
    {
        int i = 0;
        Habitacion habitacion = null;

        for (List <Habitacion> habi: habitaciones.values())
        {
            if (habi.get(i).getNumero() == nroHabitacion)
            {
                habitacion = habi.get(i);
                break;
            }
            i++;
        }
        return habitacion;
    }

    public void mostrarHabitacionesYdatosDeOcupantes ()
    {
        for (List<Habitacion> habitacionList : habitaciones.values())
        {
            for (Habitacion habitacion : habitacionList)
            {
                if (habitacion.getOcupadaONo() && habitacion.getPasajeroQueLaOcupa() != null)
                {
                    habitacion.mostrarHabitacion();
                }
            }
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
                while(it.hasNext() && !flag)
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

    public Pasajero retornarPasajeroPorPosicion (int pos) throws PosicionInvalida
    {
        Pasajero aux;

        if (pos == pasajeros.size())
        {
            throw new PosicionInvalida();
        }
        else
        {
            Pasajero []auxArray = pasajeros.toArray(new Pasajero[0]);

            aux = auxArray[pos];
        }

        return aux;
    }

    public int busquedaPorDNIPasajero (String dni) throws DNINoExiste
    {
        int i = 0;
        boolean flag = false;
        Pasajero [] pasajeros1 = pasajeros.toArray(new Pasajero[0]);

        if (dni == null)
        {
            throw new DNINoExiste();
        }

        while (i < pasajeros1.length && !flag)
        {
            if (pasajeros1[i].getDni().equals(dni))
            {
                flag = true;
            }
            else
            {
                i++;
            }
        }

        if (!flag)
        {
            throw new DNINoExiste();
        }

        return i;
    }
}
