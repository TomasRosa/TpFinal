public abstract class Persona
{
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String domicilio;

    public Persona(String nombre, String apellido, String dni, String telefono, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void mostrarPersona ()
    {
        System.out.println("NOMBRE: " + getNombre());
        System.out.println("APELLIDO: " + getApellido());
        System.out.println("DNI: " + getDni());
        System.out.println("TELEFONO: " + getTelefono());
        System.out.println("DIRECCION: " + getDomicilio());
    }

    public abstract void mostrar ();
}
