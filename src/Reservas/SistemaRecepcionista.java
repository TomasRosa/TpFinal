package Reservas;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Archivos.ManejoArchivo;
import Excepciones.*;
import Servicios.Servicio;
import Enum.TiposDeMontosHabitaciones;

public class SistemaRecepcionista
{
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

    public String getNombreArchPasajeros() {
        return "Pasajeros.json";
    }

    public String getNombreArchHabitaciones() {
        return "Habitaciones.json";
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
        Pasajero pasajero = cargarUnPasajero(teclado);

        pasajeros.add(pasajero);

        habitacion.setOcupadaONo(true);
        habitacion.setPasajeroQueLaOcupa(pasajero);

        return pasajero;
    }
    public static Tarjeta cargarTarjeta (Scanner scan, String dniPasajero)
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
                nroTarjeta = scan.next();
                flag = Validacion.validarStringNoLetras(nroTarjeta);
                if (flag)
                {
                    flag = false;
                    try
                    {
                        flag = Validacion.validarNroTarjeta(nroTarjeta);
                    }
                    catch (LongitudException e)
                    {
                        System.out.println("\nError: la longitud no es adecuada.\n");
                    }
                }
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nError: el apellido contiene numeros\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el nombre y apellido titular de la tarjeta. ");
            try
            {
                scan.next();
                nombreYApellido = scan.nextLine();
                flag = Validacion.validarStringNoNumeros(nombreYApellido);
            }
            catch (NombreContieneNumeros e)
            {
                System.out.println("\nError: el nombre y apellido contiene numeros\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese la fecha de vencimiento de la tarjeta. dd/MMM/yyyy ");
            try
            {
                fechaVencimiento = scan.nextLine();
                flag = Validacion.validarFecha(fechaVencimiento);

                if (flag)
                {
                    flag = false;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha = LocalDate.parse(fechaVencimiento,formatter);

                    try
                    {
                        flag = Validacion.validarFechaVencimientoTarjeta(fecha);
                    }
                    catch (FechaVencidaException e)
                    {
                        System.out.println("La fecha de su tarjeta esta vencida. Asegurese de escribirla bien. (dd/MM/yyyy)");
                    }
                }

            }catch (FechaInvalida e)
            {
                System.out.println("\nError: fecha invalida\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el codigo de seguridad de la tarjeta. (3 digitos) ");
            try
            {
                    codigoSeguridad = scan.nextInt();
                    try
                    {
                        flag = Validacion.validarCodigoSeguridad(codigoSeguridad);

                    }catch (CodigoSeguridadException e)
                    {
                        System.out.println("\nERROR: CODIGO INVALIDO\n");
                    }
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nERROR: EL CODIGO DE SEGURIDAD DEBE SER NUMEROS.\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el DNI del pasajero");
            try
            {
                dni = scan.next();
                scan.nextLine();
                flag = Validacion.validarStringNoLetras(dni) && Validacion.validarDniSeaIgualATarjeta(dniPasajero,dni) && Validacion.validarLongitudDNI(dni);
            }
            catch (LongitudException e)
            {
                System.out.println("\n LA LONGITUD DEL DNI NO ES APTA \n");
            }
            catch (DniTarjetaPersonaException e)
            {
                System.out.println("\n EL DNI INGRESADO NO ES EL MISMO QUE EL DE LA PERSONA TITULAR.");
            }
            catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
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
        String origen = "";

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
                if (flag)
                {
                    flag = false;
                    try {
                        flag = Validacion.validarNroTelefono(num);
                    }catch (LongitudException e)
                    {
                        System.out.println("\nERROR: LONGITUD INVALIDA");
                    }
                }
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL NUMERO CONTIENE LETRAS\n");
            }
        }while (!flag);

        System.out.println("Ingrese el domicilio del pasajero");
        String domicilio = teclado.nextLine();

        flag = false;

       do {
            System.out.println("Ingrese el origen del pasajero");
            try
            {
                origen = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoNumeros(origen);

            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL ORIGEN CONTIENE NUMEROS\n");
            }
        }while (!flag);

        return new Pasajero(nombre,apellido,dni,num,domicilio,origen,null);
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
    public Habitacion buscarHabitacionPorNumero(int nroHabitacion)
    {
        Habitacion habitacion = null;

        for (List<Habitacion> habi : habitaciones.values()) {
            for (Habitacion h : habi) {
                if (h.getNumero() == nroHabitacion) {
                    habitacion = h;
                    break;
                }
            }
            if (habitacion != null) {
                break;
            }
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
                for (Pasajero aux: servicioBuscar.getPasajerosDelServicio())
                {
                    if (dni.equals(aux.getDni())) {
                        System.out.println("El pasajero se encuentra anotado en el servicio: " + servicioBuscar.getTipoServicio());
                        flag=true;
                    }

                }
            }
            if(!flag){
                System.out.println("El dni no se encuentra asociado a ningún servicio.");
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
    public void mostrarTodosLosServicios ()
    {
        for(Servicio servi: this.servicios)
        {
            servi.mostrarServicio();
        }
    }

    public void mostrarServiciosYPasajeros ()
    {
        for(Servicio servi: this.servicios)
        {
            System.out.println("\n----------------NOMBRE DE SERVICIO: " + servi.getTipoServicio() + "----------------");
            System.out.println("PASAJEROS QUE LO RESERVARON: ");

            for (Pasajero aux: servi.getPasajerosDelServicio())
            {
                aux.mostrar();
            }
        }
    }

    public Servicio retornarServicio (String tipo)
    {
        Servicio aux = new Servicio();
        int i = 0;

        while (i < servicios.size())
        {
            if (servicios.get(i).getTipoServicio().getServicios().equalsIgnoreCase(tipo))
            {
                aux = servicios.get(i);
            }

            i++;

        }

        return aux;
    }

    public Habitacion buscarHabitacionDisponible(String tipo)
    {
        tipo = tipo.toUpperCase();

        for (List<Habitacion> habitaciones : habitaciones.values()) {
            for (Habitacion habitacion : habitaciones) {
                if (!habitacion.getOcupadaONo() && habitacion.getTipo().equals(TiposDeMontosHabitaciones.valueOf(tipo))) {
                    return habitacion;
                }
            }
        }
        return null; // No se encontró ninguna habitación disponible
    }

    public void verPasajeros ()
    {
        ManejoArchivo<Pasajero> pasajeroManejoArchivo = new ManejoArchivo<>();
        Set<Pasajero> pasajeros1 = new HashSet<>();
        try
        {
            Pasajero auxLeer = new Pasajero ();
            pasajeros1 = pasajeroManejoArchivo.leerArchivoSet(getNombreArchPasajeros(), auxLeer);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        for (Pasajero aux: pasajeros1)
        {
            aux.mostrar();
        }
    }
}
