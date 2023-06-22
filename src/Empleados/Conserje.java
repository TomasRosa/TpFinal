package Empleados;

import Interfaces.Sueldos;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Conserje extends Empleado implements Sueldos
{
    @JsonIgnore
    private int horasExtras;

    public Conserje(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, int horasExtras, boolean altaONo) {
        super(nombre, apellido, dni, telefono, domicilio, experiencia, salario, altaONo);
        this.horasExtras = horasExtras;
    }

    public Conserje() {
    }
    @JsonIgnore
    public int getHorasExtras() {
        return horasExtras;
    }
    @JsonIgnore
    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    @Override
    public void mostrar()
    {
        super.mostrar();
        System.out.println("HORAS EXTRAS: " + horasExtras);
        System.out.println("SUELDO CON EXTRA: " + sueldoExtra());
    }

    @Override
    public double sueldoExtra() {
        return getSalario() + (800 * horasExtras);
    }
}
