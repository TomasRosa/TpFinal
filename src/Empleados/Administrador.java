package Empleados;
import Archivos.ManejoArchivo;
import Excepciones.*;
import Interfaces.MetodosBasicos;
import Reservas.Persona;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Administrador extends Persona implements MetodosBasicos<Empleado>
{
    private Set<Empleado> empleados = new HashSet<>();
    private final String nombreArchivoEmpleados = "Empleados.json";
    private final ManejoArchivo<Empleado> manejoArchivo = new ManejoArchivo<>();

    public Administrador(String nombre, String apellido, String dni, String telefono, String domicilio, HashSet<Empleado> empleados) {
        super(nombre, apellido, dni, telefono, domicilio);
        this.empleados = empleados;
    }

    public Administrador() {
    }

    public String getNombreArchivoEmpleados() {
        return nombreArchivoEmpleados;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public String getCodigo() {
        return "12ab";
    }

    @Override
    public void agregar(Empleado empleado) throws ObjetoNullException
    {
        if (empleado == null)
        {
            throw new ObjetoNullException();
        }
        else
        {
            empleados.add(empleado);
        }
    }

    @Override
    public boolean buscar(Empleado empleado) throws EmpleadoNoExiste
    {
        if (!empleados.contains(empleado))
        {
            throw new EmpleadoNoExiste();
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean eliminar(Empleado empleado)
    {
        if (buscar(empleado))
        {
            empleados.remove(empleado);
            return true;
        }
        return false;
    }

    @Override
    public void mostrar()
    {
        System.out.println("\n---------------------ADMIN---------------------");
        super.mostrarPersona();
    }

    public Empleado retornarEmpleadoPorPosicion (int pos) throws PosicionInvalida
    {
        Empleado aux;

        if (pos == empleados.size())
        {
            throw new PosicionInvalida();
        }
        else
        {
            Empleado []auxArray = empleados.toArray(new Empleado[0]);

            aux = auxArray[pos];
        }

        return aux;
    }

    public int busquedaPorDNI (String dni) throws DNINoExiste
    {
        int i = 0;
        boolean flag = false;
        Empleado [] empleados1 = empleados.toArray(new Empleado[0]);

        if (dni == null)
        {
            throw new DNINoExiste();
        }

        while (i < empleados1.length && !flag)
        {
            if (empleados1[i].getDni().equals(dni))
            {
                flag = true;
            }
            else
            {
                i++;
            }
        }

        if (!flag)
        {
            throw new DNINoExiste();
        }

        return i;
    }
    public void darDeBajaEmpleado (String dni, Scanner teclado) throws EmpleadoYaDadoDeBaja
    {
        try
        {
            int i = busquedaPorDNI(dni);
            Empleado empleado1 = retornarEmpleadoPorPosicion(i);

            if (empleado1.isAltaOno()) {
                System.out.println("\nSeguro que desea dar de baja el empleado: " + empleado1.getNombre() + " " + empleado1.getApellido());
                char confirmacion = teclado.next().charAt(0);

                if (confirmacion == 's') {
                    empleado1.setAltaOno(false);

                    ///HAGO ESTO PARA MODIFICAR EL EMPLEADO
                    eliminar(empleado1);
                    agregar(empleado1);

                    System.out.println("EMPLEADO DADO DE BAJA CON EXITO!\n");

                    try {
                        manejoArchivo.escribirArchivoSet(nombreArchivoEmpleados, empleados);
                    } catch (IOException e) {
                        System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO\n");
                    }
                } else {
                    System.out.println("EMPLEADO NO DADO DE BAJA\n");
                }
            }
            else
            {
                throw new EmpleadoYaDadoDeBaja();
            }

        }
        catch (DNINoExiste e)
        {
            System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
        }
        catch (PosicionInvalida e)
        {
            System.out.println("\nERROR: LA POSICION NO EXISTE\n");
        }
    }

    public void darDeAltaEmpleado (String dni, Scanner teclado) throws EmpleadoYaDadoDeAlta
    {
        try
        {
            int i = busquedaPorDNI(dni);
            Empleado empleado1 = retornarEmpleadoPorPosicion(i);

            if (!empleado1.isAltaOno()) {
                System.out.println("\nSeguro que desea dar de alta el empleado: " + empleado1.getNombre() + " " + empleado1.getApellido());
                char confirmacion = teclado.next().charAt(0);

                if (confirmacion == 's') {
                    empleado1.setAltaOno(true);

                    ///HAGO ESTO PARA MODIFICAR EL EMPLEADO
                    eliminar(empleado1);
                    agregar(empleado1);

                    System.out.println("EMPLEADO DADO DE ALTA CON EXITO!\n");

                    try {
                        manejoArchivo.escribirArchivoSet(nombreArchivoEmpleados, empleados);
                    } catch (IOException e) {
                        System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO\n");
                    }
                } else {
                    System.out.println("EMPLEADO NO DADO DE ALTA\n");
                }
            }
            else
            {
                throw new EmpleadoYaDadoDeAlta();
            }

        }
        catch (DNINoExiste e)
        {
            System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
        }
        catch (PosicionInvalida e)
        {
            System.out.println("\nERROR: LA POSICION NO EXISTE\n");
        }
    }

    public void verEmpleados ()
    {
        for (Empleado aux: empleados)
        {
            aux.mostrar();
        }
    }

    public void aumentarSueldos (String dni, double nuevoSueldo)
    {
        try
        {
            int i = busquedaPorDNI(dni);
            Empleado empleado = retornarEmpleadoPorPosicion(i);

            empleado.setSalario(nuevoSueldo);

            ///HAGO ESTO PARA MODIFICAR EL EMPLEADO
            eliminar(empleado);
            agregar(empleado);

            try
            {
                manejoArchivo.escribirArchivoSet(nombreArchivoEmpleados, empleados);
            }catch (IOException e)
            {
                System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO\n");
            }

        }
        catch (DNINoExiste e)
        {
            System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
        }
        catch (PosicionInvalida e)
        {
            System.out.println("\nERROR: LA POSICION NO EXISTE\n");
        }
    }

}
