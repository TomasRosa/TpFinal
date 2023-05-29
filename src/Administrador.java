import java.util.ArrayList;

public class Administrador extends Persona
{
    private ArrayList <Empleado> empleados = new ArrayList<>();
    private String codigo;

    public Administrador(String nombre, String apellido, String dni, String telefono, String domicilio, ArrayList<Empleado> empleados, String codigo) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.empleados = empleados;
        this.codigo = codigo;
    }

    public Administrador() {
    }

    @Override
    public void mostrar()
    {
        System.out.println("\n---------------------ADMIN---------------------");
        super.mostrarPersona();
    }

    public void darDeBajaEmpleado (Empleado empleado)
    {

    }

    public void darDeAltaEmpleado (Empleado empleado)
    {

    }

    public void verEmpleados ()
    {
        for (Empleado aux: empleados)
        {
            aux.mostrarEmpleado();
        }
    }

    public void aumentarSueldos (Empleado empleadoABuscar)
    {

    }
}
