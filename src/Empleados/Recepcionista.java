package Empleados;
import Interfaces.Sueldos;
import Reservas.SistemaRecepcionista;

public class Recepcionista extends Empleado implements Sueldos
{
    private int clientesAtendidos;
    private final String codigo = "26ab";
    private SistemaRecepcionista recepcionista;

    public Recepcionista(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, int clientesAtendidos,SistemaRecepcionista recepcionista) {
        super(nombre, apellido, dni, telefono, domicilio, experiencia, salario, true);
        this.clientesAtendidos = clientesAtendidos;
        this.recepcionista = recepcionista;
    }

    public Recepcionista()
    {
    }

    public SistemaRecepcionista getSistemaRecepcionista() {
        return recepcionista;
    }

    public void setSistemaRecepcionista(SistemaRecepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(int clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    @Override
    public void mostrar()
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
