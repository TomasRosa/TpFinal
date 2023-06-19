package Empleados;

import Interfaces.Sueldos;

public class Conserje extends Empleado implements Sueldos
{
    private int horasExtras;

    public Conserje(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, int horasExtras) {
        super(nombre, apellido, dni, telefono, domicilio, experiencia, salario, true);
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
        System.out.println("HORAS EXTRAS: " + horasExtras);
    }

    @Override
    public double calcularSueldo() {
        return getSalario() + (800 * horasExtras);
    }
}
