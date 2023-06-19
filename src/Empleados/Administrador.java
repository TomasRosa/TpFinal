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
    private final String codigo = "12ab";
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
        return codigo;
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

    public int busquedaPorDNI (String dni) throws DNINoExiste
    {
        int i = 0;
        boolean flag = false;
        Empleado [] empleados1 = empleados.toArray(new Empleado[0]);

        if (dni == null)
        {
            throw new DNINoExiste();
        }

        try
        {
            Validacion.validarStringNoLetras(dni);

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
        }
        catch (StringContieneLetras e)
        {
            System.out.println("\nERROR: DNI INVALIDO\n");
        }

        return i;
    }
    public void darDeBajaEmpleado (Empleado empleado, Scanner teclado) throws EmpleadoYaDadoDeBaja, PosicionInvalida
    {
        try
        {
            int i = busquedaPorDNI(empleado.getDni());

            if (i == empleados.size())
            {
                throw new PosicionInvalida();
            }
            else
            {
                Empleado []empleados1 = empleados.toArray(new Empleado[0]);

                if (empleados1[i].isAltaOno())
                {
                    System.out.println("\nSeguro que desea dar de baja el empleado: " + empleados1[i].getNombre() + " " + empleados1[i].getApellido());
                    char confirmacion = teclado.next().charAt(0);

                    if (confirmacion == 's')
                    {
                        empleados1[i].setAltaOno(false);

                        ///HAGO ESTO PARA MODIFICAR EL EMPLEADO
                        eliminar(empleados1[i]);
                        agregar(empleados1[i]);

                        System.out.println("EMPLEADO DADO DE BAJA CON EXITO!\n");

                        try
                        {
                            manejoArchivo.escribirArchivoSet(nombreArchivoEmpleados, empleados);
                        }catch (IOException e)
                        {
                            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO\n");
                        }
                    }
                    else
                    {
                        System.out.println("EMPLEADO NO DADO DE BAJA\n");
                    }
                }
                else
                {
                    throw new EmpleadoYaDadoDeBaja();
                }
            }

        } catch (EmpleadoNoExiste e)
        {
            System.out.println("\nERROR: EL EMPLEADO NO EXISTE\n");
        }
        catch (DNINoExiste e)
        {
            System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
        }
    }

    public void darDeAltaEmpleado (Empleado empleado, Scanner teclado) throws EmpleadoYaDadoDeAlta, PosicionInvalida
    {
        try
        {
            int i = busquedaPorDNI(empleado.getDni());

            if (i == empleados.size())
            {
                throw new PosicionInvalida();
            }
            else
            {
                Empleado []empleados1 = empleados.toArray(new Empleado[0]);

                if (!empleados1[i].isAltaOno())
                {
                    System.out.println("\nSeguro que desea dar de alta el empleado: " + empleados1[i].getNombre() + " " + empleados1[i].getApellido());
                    char confirmacion = teclado.next().charAt(0);

                    if (confirmacion == 's')
                    {
                        empleados1[i].setAltaOno(true);

                        ///HAGO ESTO PARA MODIFICAR EL EMPLEADO
                        eliminar(empleados1[i]);
                        agregar(empleados1[i]);

                        System.out.println("EMPLEADO DADO DE ALTA CON EXITO!\n");

                        try
                        {
                            manejoArchivo.escribirArchivoSet(nombreArchivoEmpleados, empleados);
                        }catch (IOException e)
                        {
                            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO\n");
                        }
                    }
                    else
                    {
                        System.out.println("EMPLEADO NO DADO DE ALTA\n");
                    }
                }
                else
                {
                    throw new EmpleadoYaDadoDeAlta();
                }

            }
        }
        catch (EmpleadoNoExiste e)
        {
            System.out.println("\nERROR: EL EMPLEADO NO EXISTE\n");
        }
        catch (DNINoExiste e)
        {
            System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
        }
    }

    public void verEmpleados ()
    {
        for (Empleado aux: empleados)
        {
            aux.mostrar();
        }
    }

    public void aumentarSueldos (Empleado empleado, double nuevoSueldo) throws PosicionInvalida
    {
        try
        {
            int i = busquedaPorDNI(empleado.getDni());

            if (i == empleados.size())
            {
                throw new PosicionInvalida();
            }
            else
            {
                Empleado []empleados1 = empleados.toArray(new Empleado[0]);

                empleados1[i].setSalario(nuevoSueldo);

                ///HAGO ESTO PARA MODIFICAR EL EMPLEADO
                eliminar(empleados1[i]);
                agregar(empleados1[i]);

                try
                {
                    manejoArchivo.escribirArchivoSet(nombreArchivoEmpleados, empleados);

                }catch (IOException e)
                {
                    System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO\n");
                }
            }
        }
        catch (EmpleadoNoExiste e)
        {
            System.out.println("\nERROR: EL EMPLEADO NO EXISTE\n");
        }
        catch (DNINoExiste e)
        {
            System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
        }
        catch (ObjetoNullException e)
        {
            System.out.println("\nERROR: EL EMPLEADO ES NULO\n");
        }
    }

}
