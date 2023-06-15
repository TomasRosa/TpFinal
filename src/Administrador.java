import java.util.HashSet;
import java.util.Set;

public class Administrador extends Persona implements MetodosBasicos<Empleado>//hay que utilizar interfaz generica
{
    private Set<Empleado> empleados = new HashSet<>();
    private String codigo;

    public Administrador(String nombre, String apellido, String dni, String telefono, String domicilio, HashSet<Empleado> empleados, String codigo) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.empleados = empleados;
        this.codigo = codigo;
    }

    public Administrador() {
    }

    @Override
    public void agregar(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public boolean buscar(Empleado empleado) {
        return empleados.contains(empleado);
    }

    @Override
    public boolean eliminar(Empleado empleado) {
        return empleados.remove(empleado);
    }

    @Override
    public void mostrar()
    {
        System.out.println("\n---------------------ADMIN---------------------");
        super.mostrarPersona();
    }

    public void darDeBajaEmpleado (Empleado empleado) //CON LA INTERFAZ ESTE METODO ESTA DE MAS AHORA
    {

    }

    public void darDeAltaEmpleado (Empleado empleado) //CON LA INTERFAZ ESTE METODO ESTA DE MAS AHORA
    {

    }

    public void verEmpleados () //AHORA QUE TENEMOS UN SET DEBERIAMOS PONER SOLO UN SOUT
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
