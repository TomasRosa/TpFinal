package Empleados;

import Interfaces.Sueldos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Conserje extends Empleado implements Sueldos
{
    private int horasExtras;

    public Conserje(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, int horasExtras, boolean altaONo) {
        super(nombre, apellido, dni, telefono, domicilio, experiencia, salario, altaONo);
        this.horasExtras = horasExtras;
    }

    public Conserje() {
    }


    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    @Override
    public void mostrar()
    {
        super.mostrar();
    }

    public void mostrarConserje ()
    {
        mostrar();
        System.out.println("HORAS EXTRAS: " + horasExtras);
        System.out.println("SUELDO CON EXTRA: " + sueldoExtra());
    }

    @Override
    public double sueldoExtra() {
        return getSalario() + (800 * horasExtras);
    }
}
