package Empleados;

import Reservas.Persona;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable
{
    private int experiencia;
    private double salario;
    private boolean altaOno;

    public Empleado(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, boolean altaOno) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.experiencia = experiencia;
        this.salario = salario;
        this.altaOno = altaOno;
    }

    public Empleado() {
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAltaOno() {
        return altaOno;
    }

    public void setAltaOno(boolean altaOno) {
        this.altaOno = altaOno;
    }

    @Override
    public void mostrar()
    {
        System.out.println("\n-------------------------EMPLEADO-------------------------");
        super.mostrarPersona();
        System.out.println("AÑOS DE EXPERIENCIA: " + experiencia);
        System.out.println("SALARIO: " + salario);
    }
}
