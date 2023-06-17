package Excepciones;

import Excepciones.NombreContieneNumeros;
import Excepciones.StringContieneLetras;

public class Validacion
{
    public static boolean validarString(String nombre) throws NombreContieneNumeros
    {
        if (nombre.matches("[a-zA-Z]+"))
        {
            return true;
        }
        else
        {
            throw new NombreContieneNumeros();
        }
    }

    public static boolean validarDNI(String dni) throws StringContieneLetras
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


}
