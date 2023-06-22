package Empleados;
import Interfaces.Sueldos;
import Reservas.SistemaRecepcionista;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Recepcionista extends Empleado implements Sueldos
{

    private int clientesAtendidos;
    @JsonIgnore
    private SistemaRecepcionista recepcionista;

    public Recepcionista(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario, int clientesAtendidos,SistemaRecepcionista recepcionista) {
        super(nombre, apellido, dni, telefono, domicilio, experiencia, salario, true);
        this.clientesAtendidos = clientesAtendidos;
        this.recepcionista = recepcionista;
    }

    public Recepcionista()
    {
    }
    @JsonIgnore
    public String getCodigo() {
        return "26ab";
    }

    public SistemaRecepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(SistemaRecepcionista recepcionista) {
        this.recepcionista = recepcionista;
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
    }

    public void mostrarRecepcionista ()
    {
        mostrar();
        System.out.println("CLIENTES ATENDIDOS: " + clientesAtendidos);
        System.out.println("SUELDO CON EXTRA: " + sueldoExtra());
    }
    @Override
    public double sueldoExtra()
    {
        return getSalario() + (200 * clientesAtendidos);
    }
}
