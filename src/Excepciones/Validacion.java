package Excepciones;

import Excepciones.NombreContieneNumeros;
import Excepciones.StringContieneLetras;

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
}
