package Excepciones;

import Excepciones.NombreContieneNumeros;
import Excepciones.StringContieneLetras;
import Enum.TiposDeMontosHabitaciones;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validacion
{
    public static boolean validarStringNoNumeros(String nombre) throws NombreContieneNumeros
    {
        if (nombre.matches("[a-zA-Z ]+"))
        {
            return true;
        }
        else
        {
            throw new NombreContieneNumeros();
        }
    }

    public static boolean validarStringNoLetras(String dni) throws StringContieneLetras
    {
        if (dni.matches("[a-zA-Z]+"))
        {
            throw new StringContieneLetras();
        }
        else
        {
            return true;
        }
    }
    public static boolean validarCodigoAdmin(String codigoAdmin, String intento) throws CodigoErroneoException
    {
        if (!codigoAdmin.equals(intento))
        {
            throw new CodigoErroneoException();
        }
        else
        {
            return true;
        }
    }
    public static boolean validarNroTarjeta (String numeroTarjeta) throws NroTarjetaException
    {
        if(numeroTarjeta.length() != 16)
        {
            throw new NroTarjetaException();
        }
        else
        {
            return true;
        }
    }
    public static boolean validarCodigoSeguridad (int codigoSeguridad) throws CodigoSeguridadException
    {
        if(codigoSeguridad > 99 && codigoSeguridad < 1000)
        {
            throw new CodigoSeguridadException();
        }
        else
        {
            return true;
        }
    }
    public static boolean validarLongitudDNI (String dni) throws DniLongitudException
    {
        if(dni.length() != 8)
        {
            throw new DniLongitudException();
        }
        else
        {
            return true;
        }
    }

    public static boolean validarChar (char control) throws TeclaIncorrecta
    {
        if (control == 's' || control == 'S' || control == 'n' || control == 'N') return true;
        else
        {
            throw new TeclaIncorrecta();
        }
    }

    public static boolean esFechaValida(String fecha) {
        // Validar que se ingresen solo números
        if (!contieneSoloNumeros(fecha)) {
            return false;
        }

        // Validar el formato dd/mm/aaaa
        if (!esFormatoValido(fecha)) {
            return false;
        }

        // Validar si es una fecha válida
        if (!esFechaReal(fecha)) {
            return false;
        }

        return true;
    }

    private static boolean contieneSoloNumeros(String cadena) {
        return cadena.matches("^[0-9]+$");
    }

    private static boolean esFormatoValido(String fecha) {
        return fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

    private static boolean esFechaReal(String fecha) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(fecha, formato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static boolean verificarTipo(String tipo) throws TipoIncorrecto
    {
        if (!tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.SIMPLE)) &&
                !tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.DOBLE)) &&
                !tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.CUADRUPLE))) {
            throw new TipoIncorrecto();
        } else {
            return true;
        }
    }

    public static boolean validarFecha (String fecha) throws FechaInvalida
    {
        boolean flag = esFechaValida(fecha);
        if (!flag) flag = contieneSoloNumeros(fecha);
        if (!flag) flag = esFormatoValido(fecha);
        if (!flag) flag = esFechaReal(fecha);

        if (!flag)
        {
            throw new FechaInvalida();
        }
        else
        {
            return true;
        }
    }
}
