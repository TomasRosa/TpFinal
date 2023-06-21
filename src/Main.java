import Archivos.ManejoArchivo;
import Empleados.Administrador;
import Empleados.Empleado;
import Empleados.Recepcionista;
import Excepciones.*;
import Reservas.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Enum.TiposDeMontosHabitaciones;
import Enum.MotivoHabitacion;
import Enum.TiposDeServicios;
import Servicios.Servicio;

public class Main {
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);

        ManejoArchivo <Empleado> manejoArchivo = new ManejoArchivo<>();
        baseDeDatosPasajeros();
        Administrador admin = new Administrador();
        char control = 's';

        System.out.println("Como desea ingresar?");
        System.out.println("[1] Administrador");
        System.out.println("[2] Recepcionista");
        System.out.println("[3] Salir");

        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1:
            {
                int intentos = 3;
                boolean flag = false;
                do {
                    System.out.println("Ingrese el codigo para ingresar como administrador");
                    try {
                        String codigo = teclado.next();

                        flag = Validacion.validarCodigoAdmin(admin.getCodigo(), codigo);

                    } catch (CodigoErroneoException e) {
                        System.out.println("\nERROR: CODIGO ERRONEO\n");
                        intentos--;
                        System.out.println("CANTIDAD DE INTENTOS: " + intentos);
                    }

                } while (!flag && intentos > 0);

                if (intentos == 0) {
                    System.out.println("Los codigos indicados no fueron correctos. Se cerrara el sistema.");
                    break;
                }

                admin = baseDeDatosEmpleados(teclado);

                System.out.println("¡Bienvenido: " + admin.getNombre() + " " + admin.getApellido() + "!");
                do {
                    System.out.println("\n¿Que desea hacer?");
                    System.out.println("[1] Agregar un empleado");
                    System.out.println("[2] Buscar Empleado por DNI");
                    System.out.println("[3] Dar de baja un empleado");
                    System.out.println("[4] Dar de alta un empleado");
                    System.out.println("[5] Ver todos los empleados");
                    System.out.println("[6] Aumentar sueldo de un empleado");
                    System.out.println("[7] Salir");
                    opcion = teclado.nextInt();

                    String dni = "";

                    switch (opcion) {
                        case 1: {
                            admin.agregar(cargaUnEmpleado(teclado));

                            try {
                                manejoArchivo.escribirArchivoSet(admin.getNombreArchivoEmpleados(), admin.getEmpleados());
                            } catch (IOException e) {
                                System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE EMPLEADOS\n");
                            }

                            break;
                        }
                        case 2: {
                            do {
                                System.out.println("Ingrese el DNI a buscar");
                                try {
                                    dni = teclado.next();
                                    teclado.nextLine();
                                    flag = Validacion.validarStringNoLetras(dni);
                                    flag = Validacion.validarLongitudDNI(dni);
                                } catch (StringContieneLetras e) {
                                    System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                } catch (LongitudException e) {

                                    System.out.println("\n ERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                }
                            } while (!flag);

                            int i = admin.busquedaPorDNI(dni);
                            Empleado empleadoBuscado = admin.retornarEmpleadoPorPosicion(i);

                            if (empleadoBuscado == null) {
                                System.out.println("\nEMPLEADO NO ENCONTRADO");
                            } else {
                                System.out.println("\nEMPLEADO ENCONTRADO CON EXITO!\n");
                                empleadoBuscado.mostrar();
                            }
                            break;
                        }
                        case 3: {
                            do {
                                System.out.println("Ingrese el DNI a buscar");
                                try {
                                    dni = teclado.next();
                                    teclado.nextLine();
                                    flag = Validacion.validarStringNoLetras(dni);
                                    flag = Validacion.validarLongitudDNI(dni);
                                } catch (StringContieneLetras e) {
                                    System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                } catch (LongitudException e) {

                                    System.out.println("\n ERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                }
                            } while (!flag);

                            try {
                                admin.darDeBajaEmpleado(dni, teclado);
                            } catch (EmpleadoYaDadoDeBaja e) {
                                System.out.println("\nERROR: EMPLEADO YA DADO DE BAJA\n");
                            }
                            break;
                        }
                        case 4: {
                            do {
                                System.out.println("Ingrese el DNI a buscar");
                                try {
                                    dni = teclado.next();
                                    teclado.nextLine();
                                    flag = Validacion.validarStringNoLetras(dni);
                                    flag = Validacion.validarLongitudDNI(dni);
                                } catch (StringContieneLetras e) {
                                    System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                } catch (LongitudException e) {

                                    System.out.println("\n ERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                }
                            } while (!flag);

                            try {
                                admin.darDeAltaEmpleado(dni, teclado);
                            } catch (EmpleadoYaDadoDeAlta e) {
                                System.out.println("\nERROR: EMPLEADO YA DADO DE ALTA\n");
                            }

                            break;
                        }
                        case 5: {
                            admin.verEmpleados();
                            break;
                        }
                        case 6: {
                            do {
                                System.out.println("Ingrese el DNI del empleado a aumentar el sueldo");
                                try {
                                    dni = teclado.next();
                                    teclado.nextLine();
                                    flag = Validacion.validarStringNoLetras(dni);
                                    flag = Validacion.validarLongitudDNI(dni);
                                } catch (StringContieneLetras e) {
                                    System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                } catch (LongitudException e) {

                                    System.out.println("\n ERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                }
                            } while (!flag);

                            System.out.println("Ingrese el nuevo sueldo");
                            double nuevoSueldo = teclado.nextDouble();

                            admin.aumentarSueldos(dni, nuevoSueldo);

                            break;
                        }
                        case 7:
                        {
                            control = 'n';
                            break;
                        }
                    }

                    do {
                        System.out.println("Desea hacer otra cosa? (s | n)");
                        try {
                            control = teclado.next().charAt(0);
                            flag = Validacion.validarChar(control);
                        } catch (TeclaIncorrecta e) {
                            System.out.println("\nERROR: TECLA INVALIDA\n");
                        }

                    } while (!flag);

                    if (control == 'n') {
                        opcion = 7;
                    }

                } while (control == 's' && opcion != 7);
            }

            case 2:
            {
                Recepcionista recepcionista = new Recepcionista();

                int intentos = 3;
                boolean flag = false;
                do
                {
                    System.out.println("Ingrese el codigo para ingresar como recepcionista");
                    teclado.nextLine();
                    try
                    {
                        String codigo = teclado.next();
                        flag = Validacion.validarCodigoAdmin(recepcionista.getCodigo(), codigo);
                    } catch (CodigoErroneoException e)
                    {
                        System.out.println("\nERROR: CODIGO ERRONEO\n");
                        intentos--;
                        System.out.println("CANTIDAD DE INTENTOS: " + intentos);
                    }
                } while (!flag && intentos > 0);

                if (intentos == 0)
                {
                    System.out.println("Los codigos indicados no fueron correctos. Se cerrara el sistema.");
                    break;
                }
                else
                {
                    recepcionista.setSistemaRecepcionista(baseDeDatosSistema());
                    System.out.println("Elija que funcion desea realizar.");
                    System.out.println("[1]. Reservar una habitacion. ");
                    System.out.println("[2]. Realizar check in. ");
                    System.out.println("[3]. Realizar check out.");
                    System.out.println("[4]. Mostrar habitaciones y datos de ocupantes. ");
                    System.out.println("[5]. Mostrar habitaciones disponibles.");
                    System.out.println("[6]. Mostrar habitaciones no disponibles. ");
                    System.out.println("[7]. Ver reserva por DNI. ");
                    System.out.println("[8]. Ver en que servicio se encuentra una persona por DNI. ");
                    opcion = teclado.nextInt();

                    String tipo = "";
                    String dni = "";
                    flag = false;

                    switch (opcion)
                    {
                        case 1:
                        {
                            do
                            {
                                System.out.println("Ingrese el tipo de habitacion que desea reservar (SIMPLE- DOBLE- CUADRUPLE):");
                                teclado.nextLine();
                                try
                                {
                                    tipo = teclado.next();
                                    flag = Validacion.validarStringNoNumeros(tipo) && Validacion.verificarTipo(tipo);
                                } catch (NombreContieneNumeros e) {
                                    System.out.println("Error: El tipo no puede contener numeros.");
                                } catch (TipoIncorrecto e) {
                                    System.out.println("Error: El tipo esta fuera de los limites de habitaciones posibles.");
                                }
                                if (!flag) {
                                    System.out.println("Por favor, ingrese el tipo de habitacion de nuevo, de manera correcta.");
                                }
                            } while (!flag);
                            System.out.println("Buscando habitaciones...");
                            Habitacion habitacionDisponible = buscarHabitacionDisponible(recepcionista.getSistemaRecepcionista(), tipo);
                            if (habitacionDisponible != null)
                            {
                                System.out.println("Se le asigno la habitacion nro: " + habitacionDisponible.getNumero());
                                System.out.println("Ingrese cuantos dias va a quedarse en el hotel: ");
                                int cantDias = teclado.nextInt();
                                teclado.nextLine();
                                System.out.println("Cargue sus datos para finalizar la reserva:");

                                Pasajero pasajero = cargarUnPasajero(teclado);

                                Factura factura = recepcionista.getSistemaRecepcionista().reserva(habitacionDisponible, pasajero,cantDias);
                                System.out.println("Datos de la habitacion: ");
                                habitacionDisponible.mostrarHabitacion();
                                factura.mostrarFactura();
                            }
                            else
                            {
                                System.out.println("No hay habitaciones disponibles con los requisitos solicitados.");
                                ///Caso de hotel lleno.
                            }
                            break;
                        }
                        case 2:
                        {
                            do
                            {
                                System.out.println("Ingrese el tipo de habitacion que desea reservar (check in). (SIMPLE- DOBLE- CUADRUPLE):");
                                teclado.nextLine();
                                try
                                {
                                    tipo = teclado.next();
                                    flag = Validacion.validarStringNoNumeros(tipo) && Validacion.verificarTipo(tipo);
                                } catch (NombreContieneNumeros e)
                                {
                                    System.out.println("ERROR: El tipo no puede contener numeros.");
                                } catch (TipoIncorrecto e)
                                {
                                    System.out.println("ERROR: El tipo esta fuera de los limites de habitaciones posibles.");
                                }
                                if (!flag) {
                                    System.out.println("Por favor, ingrese el tipo de habitacion de nuevo, de manera correcta.");
                                }
                            } while (!flag);
                            System.out.println("Buscando habitaciones...");
                            Habitacion habitacionDisponible = buscarHabitacionDisponible(recepcionista.getSistemaRecepcionista(), tipo);
                            if (habitacionDisponible != null)
                            {
                                System.out.println("Se le asigno la habitacion nro: " + habitacionDisponible.getNumero());
                                System.out.println("Cargue sus datos para finalizar el check in:");

                                Pasajero pasajero = cargarUnPasajero(teclado);

                                recepcionista.getSistemaRecepcionista().checkIn(habitacionDisponible,pasajero);
                                System.out.println("CHECK IN DE HABITACION: ");
                                habitacionDisponible.mostrarHabitacion();
                            }
                            break;
                        }
                        case 3:
                        {
                            System.out.println("Dejando la habitacion...");
                           // recepcionista.getSistemaRecepcionista().checkOut();
                        }
                        case 4:
                        {
                            System.out.println("A continunacion te mostramos las habitaciones y los datos de los ocupantes:");
                            recepcionista.getSistemaRecepcionista().mostrarHabitacionesYdatosDeOcupantes();
                            break;
                        }
                        case 5:
                        {
                            System.out.println("Habitaciones disponibles:");
                            recepcionista.getSistemaRecepcionista().mostrarHabitacionesDisponibles();
                            ///ver con los chicos, funciona igual.
                            break;
                        }
                        case 6:
                        {
                            System.out.println("Habitaciones no disponibles:");
                            recepcionista.getSistemaRecepcionista().mostrarHabitacionesNoDisponibles();
                            break;
                        }
                        case 7:
                        {
                            do
                            {
                                System.out.println("Ingrese el DNI para ver la reserva que le corresponde:");
                                try {
                                    dni = teclado.next();
                                    teclado.nextLine();
                                    flag = Validacion.validarStringNoLetras(dni);
                                    flag = Validacion.validarLongitudDNI(dni);
                                } catch (StringContieneLetras e) {
                                    System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                } catch (LongitudException e) {

                                    System.out.println("\n ERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                }
                            } while (!flag);

                            recepcionista.getSistemaRecepcionista().verReservaPorDNI(dni);
                            break;
                        }
                        case 8:
                        {
                            ///Este no puede probarse
                            do
                            {
                                System.out.println("Ingrese el DNI para ver que servicios le corresponden:");
                                try {
                                    dni = teclado.next();
                                    teclado.nextLine();
                                    flag = Validacion.validarStringNoLetras(dni);
                                    flag = Validacion.validarLongitudDNI(dni);
                                } catch (StringContieneLetras e) {
                                    System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                } catch (LongitudException e) {

                                    System.out.println("\n ERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                }
                            } while (!flag);

                            recepcionista.getSistemaRecepcionista().busquedaPorDNIServicio(dni);
                            break;
                        }
                    }
                    do
                    {
                        System.out.println("Desea hacer otra cosa? (s | n)");
                        try
                        {
                            control = teclado.next().charAt(0);
                            flag = Validacion.validarChar(control);
                        } catch (TeclaIncorrecta e)
                        {
                            System.out.println("\nERROR: TECLA INVALIDA\n");
                        }
                    } while (!flag);

                    if (control == 'n')
                    {
                        break;
                    }
                } while (control == 's' && opcion != 9) ;
            }
            case 3:
            {
                System.out.println("Gracias vuelva prontos.");
                break;
            }
        }
        teclado.close();
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

        Set<Pasajero> pasajeros = new HashSet<>();
        ManejoArchivo<Pasajero> pasajeroManejoArchivo = new ManejoArchivo<>();

        pasajeros.add(p1);
        pasajeros.add(p2);
        pasajeros.add(p3);
        pasajeros.add(p4);
        pasajeros.add(p5);

        try
        {
            pasajeroManejoArchivo.escribirArchivoSet("archivo_pasajeros.json", pasajeros);
        }
        catch (IOException e)
        {
            System.out.println("ARCHIVO NO EXISTE, CREANDOLO...");
        }
    /*          VERIFICAR LECTURA
        try
        {
            Pasajero auxLeer = new Pasajero ();
            Set<Pasajero> pasajeros1 = pasajeroManejoArchivo.leerArchivoSet("archivo_pasajeros.json", auxLeer);
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
    public static SistemaRecepcionista baseDeDatosSistema ()
    {
        SistemaRecepcionista sistema = new SistemaRecepcionista();

        ManejoArchivo<Pasajero> pasajeroManejoArchivo = new ManejoArchivo<>();
        Set<Pasajero> pasajeros1 = new HashSet<>();
        try
        {
            Pasajero auxLeer = new Pasajero ();
            pasajeros1 = (HashSet<Pasajero>) pasajeroManejoArchivo.leerArchivoSet("archivo_pasajeros.json", auxLeer);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        Pasajero[] pasajeros = pasajeros1.toArray(new Pasajero[0]);

        Habitacion h1 = new Habitacion(true,1,pasajeros[0],14,TiposDeMontosHabitaciones.DOBLE,null);
        Habitacion h2 = new Habitacion(false,2,null,0,TiposDeMontosHabitaciones.SIMPLE,null);
        ///ANA MARTINEZ H3, VER CON LOS CHICOS.
        Habitacion h3 = new Habitacion(true,3,pasajeros[3],0,TiposDeMontosHabitaciones.CUADRUPLE,MotivoHabitacion.LIMPIEZA);
        Habitacion h4 = new Habitacion(true,4,pasajeros[1],7,TiposDeMontosHabitaciones.CUADRUPLE,null);
        Habitacion h5 = new Habitacion(false,5,null,0,TiposDeMontosHabitaciones.SIMPLE,null);
        Habitacion h6 = new Habitacion(true,6,pasajeros[2],21,TiposDeMontosHabitaciones.CUADRUPLE,null);
        Habitacion h7 = new Habitacion(false,7,null,0,TiposDeMontosHabitaciones.DOBLE,MotivoHabitacion.LIMPIEZA);
        Habitacion h8 = new Habitacion(false,8,null,0,TiposDeMontosHabitaciones.DOBLE,MotivoHabitacion.REPARACION);
        Habitacion h9 = new Habitacion(true,9,pasajeros[4],4,TiposDeMontosHabitaciones.DOBLE,null);

        ArrayList<Habitacion> habitacionesPiso1 = new ArrayList<>();
        ArrayList<Habitacion> habitacionesPiso2 = new ArrayList<>();
        ArrayList<Habitacion> habitacionesPiso3 = new ArrayList<>();

        habitacionesPiso1.add(h1);
        habitacionesPiso1.add(h2);
        habitacionesPiso1.add(h3);

        habitacionesPiso2.add(h4);
        habitacionesPiso2.add(h5);
        habitacionesPiso2.add(h6);

        habitacionesPiso3.add(h7);
        habitacionesPiso3.add(h8);
        habitacionesPiso3.add(h9);

        LinkedHashMap<Integer,List<Habitacion>> pisos = new LinkedHashMap<>();

        pisos.put(1,habitacionesPiso1);
        pisos.put(2,habitacionesPiso2);
        pisos.put(3,habitacionesPiso3);

        sistema.setHabitaciones(pisos);
        sistema.setPasajeros(pasajeros1);

        Servicio gimnasio = new Servicio("9:00 am","23:00 pm",25,TiposDeServicios.GIMNASIO);
        Servicio pileta = new Servicio("14:00 pm","19:00 pm",30,TiposDeServicios.PILETA);
        Servicio desayunador = new Servicio("6:00 am","12:00 pm",20,TiposDeServicios.DESAYUNADOR);

        ArrayList<Servicio> servicios = new ArrayList<>();
        servicios.add(gimnasio);
        servicios.add(pileta);
        servicios.add(desayunador);

        sistema.setServicios(servicios);

        return sistema;
    }
    public static Administrador baseDeDatosEmpleados (Scanner teclado)
    {
        Empleado empleado1 = new Empleado("Juan", "Perez", "12345678", "2234567890", "Calle 123", 2, 2000.0, true);
        Empleado empleado2 = new Empleado("María", "López", "87654321", "2239876543", "Avenida 456", 5, 3000.0, true);
        Empleado empleado3 = new Empleado("Carlos", "Gómez", "45678901", "2237418529", "Calle Principal 789", 3, 2500.0, true);
        Empleado empleado4 = new Empleado("Ana", "Martínez", "10987654", "2233647582", "Avenida Central 987", 1, 1800.0, true);
        Empleado empleado5 = new Empleado("Luis", "Rodríguez", "34567890", "2235647382", "Calle Secundaria 654", 4, 2800.0, true);

        Administrador administrador = new Administrador();
        ManejoArchivo <Empleado> manejoArchivo = new ManejoArchivo<>();

        administrador = cargaUnAdmin(teclado);

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
    public static Habitacion buscarHabitacionDisponible(SistemaRecepcionista sistema, String tipo)
    {
        tipo = tipo.toUpperCase();

        for (List<Habitacion> habitaciones : sistema.getHabitaciones().values()) {
            for (Habitacion habitacion : habitaciones) {
                if (!habitacion.getOcupadaONo() && habitacion.getTipo().equals(TiposDeMontosHabitaciones.valueOf(tipo))) {
                    return habitacion;
                }
            }
        }
        return null; // No se encontró ninguna habitación disponible
    }


    public static Administrador cargaUnAdmin (Scanner teclado)
    {
        boolean flag = false;
        String nombre = "";
        String apellido = "";
        String dni = "";
        String num= "";

        do
        {
            System.out.println("Ingrese su nombre");
            try
            {
                nombre = teclado.next();
                flag = Validacion.validarStringNoNumeros(nombre);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL NOMBRE CONTIENE NUMEROS\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese su apellido");
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
            System.out.println("Ingrese su DNI");
            try
            {
                dni = teclado.next();
                flag = Validacion.validarStringNoLetras(dni);

                if (flag)
                {
                    flag = false;
                    try
                    {
                        flag = Validacion.validarLongitudDNI(dni);
                    }catch (LongitudException e)
                    {
                        System.out.println("\nERROR: EL DNI NO CUMPLE CON LA LONGITUD ADECUADA\n");
                    }
                }
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
            }

        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese su numero de telefono");
            try
            {
                num = teclado.next();
                flag = Validacion.validarStringNoLetras(num);

                if (flag)
                {
                    flag = false;
                    try
                    {
                        flag = Validacion.validarNroTelefono(num);
                    }catch (LongitudException e)
                    {
                        System.out.println("\nERROR: EL NUMERO DE TELEFONO NO CUMPLE CON LA LONGITUD ADECUADA\n");
                    }
                }
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL NUMERO CONTIENE LETRAS\n");
            }
        }while (!flag);

        System.out.println("Ingrese su domicilio");
        teclado.nextLine();
        String domicilio = teclado.nextLine();

        Administrador administrador = new Administrador();

        administrador.setNombre(nombre);
        administrador.setApellido(apellido);
        administrador.setDni(dni);
        administrador.setTelefono(num);
        administrador.setDomicilio(domicilio);

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
                flag=false;
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
                flag=false;
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
                flag=false;
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
                flag=false;
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
    public static Pasajero cargarUnPasajero (Scanner teclado)
    {
        boolean flag = false;
        String nombre = "";
        String apellido = "";
        String dni = "";
        String num= "";

        do
        {
            System.out.println("Ingrese el nombre del pasajero");
            try
            {
                nombre = teclado.nextLine();
                flag = Validacion.validarStringNoNumeros(nombre);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL NOMBRE CONTIENE NUMEROS\n");
                flag=false;
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el apellido del pasajero");
            try
            {
                apellido = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoNumeros(apellido);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nERROR: EL APELLIDO CONTIENE NUMEROS\n");
                flag=false;
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el DNI del pasajero");
            try
            {
                dni = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoLetras(dni) && Validacion.validarLongitudDNI(dni);
            }
            catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                flag=false;
            }
            catch (LongitudException e)
            {
                System.out.println("\nERROR: EL DNI NO TIENE LA LONGITUD ADECUADA\n");
            }
        }while (!flag);

        flag = false;

        do
        {
            System.out.println("Ingrese el numero de telefono del pasajero");
            try
            {
                num = teclado.next();
                teclado.nextLine();
                flag = Validacion.validarStringNoLetras(num);
            }catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL NUMERO CONTIENE LETRAS\n");
                flag=false;
            }
        }while (!flag);

        System.out.println("Ingrese el domicilio del pasajero");
        String domicilio = teclado.next();
        teclado.nextLine();

        System.out.println("Ingrese el origen del pasajero");
        String origen = teclado.nextLine();

        Tarjeta t1 = cargarTarjeta(teclado,dni);

        return new Pasajero(nombre,apellido,dni,num,domicilio,origen,t1);
    }
    public static Pasajero buscarPasajeroEnHabitaciones (String dni)
    {
        Pasajero pasajerito = null;
        return pasajerito;
    }
    public static Tarjeta cargarTarjeta (Scanner scan,String intento)
    {
        String nroTarjeta = " ";
        String nombreYApellido = " ";
        String fechaVencimiento = " ";
        int codigoSeguridad = 0;
        String dni = " ";
        boolean flag = false;

        do
        {
            System.out.println("Ingrese el numero de la tarjeta. ");
            try
            {
                nroTarjeta = scan.nextLine();
                flag = Validacion.validarStringNoLetras(nroTarjeta);
                try
                {
                    flag = Validacion.validarNroTarjeta(nroTarjeta);
                }
                catch (LongitudException e)
                {
                    System.out.println("\nError: la longitud no es adecuada.\n");
                    flag=false;
                }
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nError: el apellido contiene numeros\n");
                flag=false;
            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese el nombre y apellido titular de la tarjeta. ");
            try
            {
                nombreYApellido = scan.nextLine();
                flag = Validacion.validarStringNoNumeros(nombreYApellido);
            }catch (NombreContieneNumeros e)
            {
                System.out.println("\nError: el nombre y apellido contiene numeros\n");
                flag=false;

            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese la fecha de vencimiento de la tarjeta. dd/mm/aaaa ");
            try
            {
                fechaVencimiento = scan.nextLine();
                flag = Validacion.validarFecha(fechaVencimiento);

            }catch (FechaInvalida e)
            {
                System.out.println("\nError: fecha invalida\n");
                flag=false;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaVencimiento,formatter);
            try
            {
                flag = Validacion.validarFechaVencimientoTarjeta(fecha);
            }
            catch (FechaVencidaException e)
            {
                System.out.println("La fecha de su tarjeta esta vencida. Asegurese de escribirla bien. (dd/MM/yyyy)");
                flag = false;
            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese el codigo de seguridad de la tarjeta. (3 digitos) ");
            try
            {
                codigoSeguridad = scan.nextInt();
                flag = Validacion.validarCodigoSeguridad(codigoSeguridad);
            }
            catch (CodigoSeguridadException e)
            {
                System.out.println("\nERROR: EL CODIGO DE SEGURIDAD NO ES APTO.\n");
                flag=false;
            }
        }while (!flag);
        do
        {
            System.out.println("Ingrese el DNI del pasajero");
            try
            {
                dni = scan.next();
                scan.nextLine();
                flag = Validacion.validarStringNoLetras(dni) && Validacion.validarDniSeaIgualATarjeta(dni,intento) && Validacion.validarLongitudDNI(dni);
            }
            catch (LongitudException e)
            {
                System.out.println("\n LA LONGITUD DEL DNI NO ES APTA \n");
                flag=false;
            }
            catch (DniTarjetaPersonaException e)
            {
                System.out.println("\n EL DNI INGRESADO NO ES EL MISMO QUE EL DE LA PERSONA TITULAR.");
                flag = false;
            }
            catch (StringContieneLetras e)
            {
                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                flag=false;
            }

        }while (!flag);
        return new Tarjeta(nroTarjeta,nombreYApellido,fechaVencimiento,codigoSeguridad,dni);
    }

    public boolean pagoDeTarjeta ()
    {
        Random random = new Random();

        int numRandom = random.nextInt(100) +1;

        ///si es menor a 90 retorna true que quiere decir que se pago, caso contrario no se paga (probabilidad de 90% de que se pague)
        return (numRandom < 90);
    }
}