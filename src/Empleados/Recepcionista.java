package Empleados;

import Empleados.Empleado;

public class Recepcionista extends Empleado implements Sueldos
{
    private int clientesAtendidos;

    public Recepcionista(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, int clientesAtendidos) {
        super(nombre, apellido, dni, telefono, domicilio, experiencia, salario);
        this.clientesAtendidos = clientesAtendidos;
    }

    public Recepcionista() {
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(int clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    @Override
    public void mostrarEmpleado()
    {
        super.mostrar();
        System.out.println("CLIENTES ATENDIDOS: " + clientesAtendidos);
    }

    @Override
    public double calcularSueldo()
    {
        return getSalario() + (200 * clientesAtendidos);
    }
}
