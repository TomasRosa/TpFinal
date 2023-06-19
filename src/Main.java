import Archivos.ManejoArchivo;
import Empleados.Administrador;
import Empleados.Empleado;
import Excepciones.NombreContieneNumeros;
import Excepciones.ObjetoNullException;
import Excepciones.StringContieneLetras;
import Excepciones.Validacion;
import Reservas.Pasajero;
import Reservas.Tarjeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        ManejoArchivo <Empleado> manejoArchivo = new ManejoArchivo<>();
        //baseDeDatosPasajeros();
        Administrador admin = baseDeDatosEmpleados();

        admin.agregar(cargaUnEmpleado(teclado));

        try
        {
            manejoArchivo.escribirArchivoSet(admin.getNombreArchivoEmpleados(), admin.getEmpleados());
        }catch (IOException e)
        {
            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE EMPLEADOS\n");
        }

        for (Empleado aux2: admin.getEmpleados())
        {
            aux2.mostrar();
        }
    }

    public static void baseDeDatosPasajeros ()
    {
        Tarjeta t1 = new Tarjeta("1234567890123456", "Lucas Gomez", "12/25", 123, "45539094");
        Tarjeta t2 = new Tarjeta("9876543210987654", "Juan Perez", "06/24", 456, "12345678");
        Tarjeta t3 = new Tarjeta("4567890123456789", "María López", "09/23", 789, "98765432");
        Tarjeta t4 = new Tarjeta("5678901234567890", "Carlos Rodríguez", "03/27", 987, "56473829");
        Tarjeta t5 = new Tarjeta("3456789012345678", "Ana Martínez", "08/26", 654, "38475629");

        Pasajero p1 = new Pasajero("Lucas","Gomez","45539094","2236021535","Luis Agote 2676","Peru", t1);
        Pasajero p2 = new Pasajero("Juan", "Perez", "12345678", "2236547890", "Av. Rivadavia 123", "Argentina", t2);
        Pasajero p3 = new Pasajero("María", "López", "98765432", "2239876543", "Calle San Martín 456", "España", t3);
        Pasajero p4 = new Pasajero("Carlos", "Rodríguez", "56473829", "2237418529", "Rua Augusta 789", "Brasil", t4);
        Pasajero p5 = new Pasajero("Ana", "Martínez", "38475629", "2233647582", "Broadway Street 456", "Estados Unidos", t5);

        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        ManejoArchivo<Pasajero> pasajeroManejoArchivo = new ManejoArchivo<>();

        pasajeros.add(p1);
        pasajeros.add(p2);
        pasajeros.add(p3);
        pasajeros.add(p4);
        pasajeros.add(p5);

        try
        {
            pasajeroManejoArchivo.escribirArchivoList("archivo_pasajeros.json", pasajeros);
        }
        catch (IOException e)
        {
            System.out.println("ARCHIVO NO EXISTE, CREANDOLO...");
        }
/*          VERIFICAR LECTURA
        try
        {
            Pasajero auxLeer = new Pasajero ();
            ArrayList<Pasajero> pasajeros1 = pasajeroManejoArchivo.leerArchivoList("archivo_pasajeros.json", auxLeer);
            int i = 0;

            while (i < pasajeros1.size())
            {
                pasajeros1.get(i).mostrar();
                i++;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }*/
    }

    public static Administrador baseDeDatosEmpleados ()
    {
        Empleado empleado1 = new Empleado("Juan", "Perez", "12345678", "2234567890", "Calle 123", 2, 2000.0, true);
        Empleado empleado2 = new Empleado("María", "López", "87654321", "2239876543", "Avenida 456", 5, 3000.0, true);
        Empleado empleado3 = new Empleado("Carlos", "Gómez", "45678901", "2237418529", "Calle Principal 789", 3, 2500.0, true);
        Empleado empleado4 = new Empleado("Ana", "Martínez", "10987654", "2233647582", "Avenida Central 987", 1, 1800.0, true);
        Empleado empleado5 = new Empleado("Luis", "Rodríguez", "34567890", "2235647382", "Calle Secundaria 654", 4, 2800.0, true);

        Administrador administrador = new Administrador();
        ManejoArchivo <Empleado> manejoArchivo = new ManejoArchivo<>();

        try
        {
            administrador.agregar(empleado1);
            administrador.agregar(empleado2);
            administrador.agregar(empleado3);
            administrador.agregar(empleado4);
            administrador.agregar(empleado5);
        }
        catch (ObjetoNullException e)
        {
            System.out.println("\nERROR: EMPLEADO NULO\n");
        }

        try
        {
            manejoArchivo.escribirArchivoSet(administrador.getNombreArchivoEmpleados(), administrador.getEmpleados());
        }catch (IOException e)
        {
            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE EMPLEADOS\n");
        }
/*      ///VERIFICAR LECTURA
        try
        {
            Empleado auxLeer = new Empleado();
            Set <Empleado> aux = manejoArchivo.leerArchivoSet(administrador.getNombreArchivoEmpleados(), auxLeer);

            for (Empleado aux2: aux)
            {
                aux2.mostrar();
            }
        }catch (IOException e)
        {
            System.out.println("\nERROR AL LEER EL ARCHIVO DE EMPLEADOS");
        }*/

        return administrador;
    }

    public static Empleado cargaUnEmpleado (Scanner teclado)
    {
        boolean flag = false;
        String nombre = "";
        String apellido = "";
        String dni = "";
        String num= "";

        do
        {
            System.out.println("Ingrese el nombre del empleado");
            try
            {
                nombre = teclado.nextLine();
                flag = Validacion.validarStringNoNumeros(nombre);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL NOMBRE CONTIENE NUMEROS\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el apellido del empleado");
            try
            {
                apellido = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoNumeros(apellido);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL APELLIDO CONTIENE NUMEROS\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el DNI del empleado");
            try
            {
                dni = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoLetras(dni);
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el numero de telefono del empleado");
            try
            {
                num = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoLetras(num);
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL NUMERO CONTIENE LETRAS\n");
            }
        }while (!flag);

        System.out.println("Ingrese el domicilio del empleado");
        String domicilio = teclado.next();
        teclado.nextLine();

        System.out.println("Ingrese los años de experiencia del empleado");
        int experiencia = teclado.nextInt();

        System.out.println("Ingrese el salario del empleado");
        double salario = teclado.nextDouble();

        return new Empleado(nombre, apellido, dni, num, domicilio, experiencia, salario, true);
    }
}