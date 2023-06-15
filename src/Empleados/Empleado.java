package Empleados;

public abstract class Empleado extends Persona
{
    private int experiencia;
    private double salario;

    public Empleado(String nombre, String apellido, String dni, String telefono, String domicilio, int experiencia, double salario) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.experiencia = experiencia;
        this.salario = salario;
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
    @Override
    public void mostrar()
    {
        System.out.println("\n-------------------------EMPLEADO-------------------------");
        super.mostrarPersona();
        System.out.println("AÑOS DE EXPERIENCIA: " + experiencia);
        System.out.println("SALARIO: " + salario);
    }

    public abstract void mostrarEmpleado ();
}
