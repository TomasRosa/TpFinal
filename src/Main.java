import Archivos.ManejoArchivo;
import Empleados.Administrador;
import Empleados.Conserje;
import Empleados.Empleado;
import Empleados.Recepcionista;
import Excepciones.*;
import Reservas.*;
import java.io.IOException;
import java.util.*;
import Enum.TiposDeMontosHabitaciones;
import Enum.MotivoHabitacion;
import Enum.TiposDeServicios;
import Servicios.Servicio;

public class Main
{
    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);
        final String nombreHotel = "Harmony Retreat";
        int opcion;

        ManejoArchivo <Empleado> manejoArchivoEmpleado = new ManejoArchivo<>();
        ManejoArchivo <Pasajero> manejoArchivoPasajero = new ManejoArchivo<>();
        baseDeDatosPasajeros();
        Administrador admin = new Administrador();
        char control = 's';

        System.out.println("Bienvenido al hotel: " + nombreHotel + "!");

        do
        {
            System.out.println("Como desea ingresar?");
            System.out.println("[1] Administrador");
            System.out.println("[2] Recepcionista");
            System.out.println("[3] Salir");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> {
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
                        System.out.println("[1]. Agregar un empleado");
                        System.out.println("[2]. Buscar Empleado por DNI");
                        System.out.println("[3]. Dar de baja un empleado");
                        System.out.println("[4]. Dar de alta un empleado");
                        System.out.println("[5]. Ver archivo de empleados");
                        System.out.println("[6]. Modificar sueldo de un empleado");
                        System.out.println("[7]. Ver sus datos");
                        System.out.println("[8]. Ver sueldos con extras de los empleados");
                        System.out.println("[9]. Volver Atras");
                        opcion = teclado.nextInt();

                        String dni = "";

                        switch (opcion) {
                            case 1 -> {
                                admin.agregar(cargaUnEmpleado(teclado));

                                try {
                                    manejoArchivoEmpleado.escribirArchivoSet(admin.getNombreArchivoEmpleados(), admin.getEmpleados());
                                } catch (IOException e) {
                                    System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE EMPLEADOS\n");
                                }

                            }
                            case 2 -> {
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
                            }
                            case 3 -> {
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
                            }
                            case 4 -> {
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

                            }
                            case 5 -> admin.verEmpleados();

                            case 6 -> {
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

                                admin.modificarSueldos(dni, nuevoSueldo);

                            }
                            case 7 -> admin.mostrar();
                            case 8 -> admin.verSueldosExtrasDeEmpleados();
                        }

                        if (opcion != 9) {
                            do {
                                System.out.println("Desea hacer otra cosa? (s | n)");
                                try {
                                    control = teclado.next().charAt(0);
                                    flag = Validacion.validarChar(control);
                                } catch (TeclaIncorrecta e) {
                                    System.out.println("\nERROR: TECLA INVALIDA\n");
                                }
                            } while (!flag);
                        } else {
                            control = 'n';
                        }

                    } while (control == 's');

                }
                case 2 -> {
                    Recepcionista recepcionista = new Recepcionista();

                    int intentos = 3;
                    boolean flag = false;
                    do {
                        System.out.println("Ingrese el codigo para ingresar como recepcionista");
                        teclado.nextLine();
                        try {
                            String codigo = teclado.next();
                            flag = Validacion.validarCodigoAdmin(recepcionista.getCodigo(), codigo);
                        } catch (CodigoErroneoException e) {
                            System.out.println("\nERROR: CODIGO ERRONEO\n");
                            intentos--;
                            System.out.println("CANTIDAD DE INTENTOS: " + intentos);
                        }
                    } while (!flag && intentos > 0);

                    if (intentos == 0) {
                        System.out.println("Los codigos indicados no fueron correctos. Se cerrara el sistema.");
                    } else {
                        recepcionista.setRecepcionista(baseDeDatosSistema());
                        do {
                            int opcionVolverAtras = 0;
                            do {
                                System.out.println("Elija que funcion desea realizar.");
                                System.out.println("[1]. Reservar una habitacion + check in. ");
                                System.out.println("[2]. Realizar check out.");
                                System.out.println("[3]. Mostrar habitaciones y datos de ocupantes. ");
                                System.out.println("[4]. Mostrar habitaciones disponibles.");
                                System.out.println("[5]. Mostrar habitaciones no disponibles. ");
                                System.out.println("[6]. Ver reserva por DNI. ");
                                System.out.println("[7]. Ver archivo de pasajeros del hotel");
                                System.out.println("[8]. Servicios. ");
                                System.out.println("[9]. Volver atras");
                                opcion = teclado.nextInt();

                                String tipo = "";
                                String dni = "";
                                int cantDias = 0;
                                flag = false;

                                switch (opcion) {
                                    case 1 -> {
                                        do {
                                            System.out.println("Ingrese el tipo de habitacion que desea reservar (SIMPLE- DOBLE- CUADRUPLE):");
                                            teclado.nextLine();
                                            try {
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
                                        Habitacion habitacionDisponible = recepcionista.getRecepcionista().buscarHabitacionDisponible(tipo);
                                        if (habitacionDisponible != null) {
                                            System.out.println("Se le asigno la habitacion nro: " + habitacionDisponible.getNumero());

                                            do {
                                                System.out.println("Ingrese cuantos dias va a quedarse en el hotel: ");
                                                try {
                                                    cantDias = teclado.nextInt();
                                                    flag = true;
                                                } catch (InputMismatchException e) {
                                                    System.out.println("\nERROR: DEBE SER SOLO UN NUMERO\n");
                                                    flag = false;
                                                    teclado.nextLine();
                                                }
                                            } while (!flag);
                                            teclado.nextLine();
                                            System.out.println("Cargue sus datos para realizar el check in y finalizar la reserva:");

                                            Pasajero pasajero = recepcionista.getRecepcionista().checkIn(habitacionDisponible, teclado);

                                            try {
                                                manejoArchivoPasajero.escribirArchivoSet(recepcionista.getRecepcionista().getNombreArchPasajeros(), recepcionista.getRecepcionista().getPasajeros());
                                            } catch (IOException e) {
                                                System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE PASAJEROS\n");
                                            }

                                            flag = false;
                                            boolean pagoONo;

                                            do {
                                                System.out.println("\n¿Como desea pagar la habitacion?");
                                                System.out.println("[1]. Tarjeta");
                                                System.out.println("[2]. Efectivo");
                                                opcion = teclado.nextInt();

                                                try {
                                                    flag = Validacion.validarOpcion(opcion);
                                                } catch (OpcionIncorrecta e) {
                                                    System.out.println("\nERROR: OPCION INVALIDA");
                                                }

                                            } while (!flag);

                                            do {
                                                if (opcion == 1) {
                                                    pasajero.setTarjeta(SistemaRecepcionista.cargarTarjeta(teclado, pasajero.getDni()));
                                                    do {
                                                        pagoONo = pagoDeTarjeta();

                                                        if (pagoONo) {
                                                            System.out.println("\nSe efectuo el pago correctamente!\n");
                                                        } else {
                                                            System.out.println("\nOcurrio un error en el pago\n");
                                                            do {
                                                                System.out.println("Desea intentar de nuevo el pago con tarjeta? (s | n)");
                                                                try {
                                                                    control = teclado.next().charAt(0);
                                                                    flag = Validacion.validarChar(control);
                                                                } catch (TeclaIncorrecta e) {
                                                                    System.out.println("\nERROR: TECLA INVALIDA\n");
                                                                }
                                                            } while (!flag);
                                                        }
                                                    } while (!pagoONo && control == 's');

                                                    if (control == 'n') {
                                                        opcion = 2;
                                                    }
                                                } else {
                                                    System.out.println("\nSe abono en efectivo!\n");
                                                    pagoONo = true;
                                                }
                                            } while (!pagoONo);

                                            Factura factura = recepcionista.getRecepcionista().reserva(habitacionDisponible, pasajero, cantDias);
                                            System.out.println("Datos de la habitacion: ");
                                            habitacionDisponible.mostrarHabitacion();
                                            factura.mostrarFactura();
                                        } else {
                                            System.out.println("No hay habitaciones disponibles con los requisitos solicitados.");
                                            ///Caso de hotel lleno.
                                        }
                                    }
                                    case 2 -> {
                                        System.out.println("Ingrese el NRO de habitacion que desea desocupar.  ");
                                        int num = teclado.nextInt();
                                        try {
                                            Habitacion habitacion = recepcionista.getRecepcionista().buscarHabitacionPorNumero(num);
                                            recepcionista.getRecepcionista().checkOut(habitacion);
                                        } catch (NullPointerException e) {
                                            System.out.println("La habitacion: " + num + " que desea desocupar esta actualmente sin alojaciones.");
                                        }
                                    }
                                    case 3 -> {
                                        System.out.println("A continunacion te mostramos las habitaciones y los datos de los ocupantes:");
                                        recepcionista.getRecepcionista().mostrarHabitacionesYdatosDeOcupantes();
                                    }
                                    case 4 -> {
                                        System.out.println("Habitaciones disponibles:");
                                        recepcionista.getRecepcionista().mostrarHabitacionesDisponibles();
                                    }
                                    case 5 -> {
                                        System.out.println("Habitaciones no disponibles:");
                                        recepcionista.getRecepcionista().mostrarHabitacionesNoDisponibles();
                                    }
                                    case 6 -> {
                                        do {
                                            System.out.println("Ingrese el DNI para ver la reserva que le corresponde:");
                                            try {
                                                dni = teclado.next();
                                                teclado.nextLine();
                                                flag = Validacion.validarStringNoLetras(dni);
                                                if (flag) {
                                                    try {
                                                        flag = Validacion.validarLongitudDNI(dni);
                                                    } catch (LongitudException e) {
                                                        System.out.println("\nERROR: EL DNI SOLO DEBE CONTENER 8 DIGITOS\n");
                                                    }
                                                }
                                            } catch (StringContieneLetras e) {
                                                System.out.println("\nERROR: EL DNI CONTIENE LETRAS\n");
                                            }
                                        } while (!flag);

                                        recepcionista.getRecepcionista().verReservaPorDNI(dni);
                                    }
                                    case 7 -> recepcionista.getRecepcionista().verPasajeros();

                                    case 8 -> {
                                        System.out.println("Que desea realizar en el apartado de servicios? ");
                                        System.out.println("[1]. Reservar turno en algun servicio.");
                                        System.out.println("[2]. Cancelar turno en algun servicio.");
                                        System.out.println("[3]. Ver los servicios disponibles.");
                                        System.out.println("[4]. Ver que servicios le corresponden a un dni determinado.");
                                        System.out.println("[5]. Ver servicios y pasajeros");
                                        System.out.println("[6]. Volver atras");
                                        opcionVolverAtras = teclado.nextInt();
                                        switch (opcionVolverAtras) {
                                            case 1 -> {
                                                do {
                                                    System.out.println("¿Como desea realizar la reserva?");
                                                    System.out.println("[1]. Cargar un nuevo pasajero.");
                                                    System.out.println("[2]. Utilizar un pasajero ya cargado.");
                                                    opcion = teclado.nextInt();
                                                    try {
                                                        flag = Validacion.validarOpcion(opcion);
                                                    } catch (OpcionIncorrecta e) {
                                                        System.out.println("\nERROR: OPCION INVALIDA\n");
                                                    }
                                                } while (!flag);
                                                if (opcion == 1) {
                                                    Pasajero pasajero = SistemaRecepcionista.cargarUnPasajero(teclado);
                                                    do {
                                                        System.out.println("¿A que servicio desea anotarse? GIMNASIO-PILETA-DESAYUNADOR");

                                                        try {
                                                            tipo = teclado.next();
                                                            flag = Validacion.validarStringNoNumeros(tipo) && Validacion.validarServicio(tipo);
                                                        } catch (NombreContieneNumeros e) {
                                                            System.out.println("Error: El servicio no puede contener numeros.");
                                                        } catch (TipoIncorrecto e) {
                                                            System.out.println("Error: El servicio es invalido.");
                                                        }
                                                        if (!flag) {
                                                            System.out.println("Por favor, ingrese el tipo de servicio de nuevo, de manera correcta.");
                                                        }
                                                    } while (!flag);

                                                    Servicio aux = recepcionista.getRecepcionista().retornarServicio(tipo);

                                                    try {
                                                        boolean reservadoONo = aux.reservarTurnoPortipo(pasajero, tipo);

                                                        actualizarDatosDeServicios(recepcionista, aux);

                                                        if (reservadoONo) {
                                                            System.out.println("Se reservo el turno con exito!");
                                                        }
                                                    } catch (TurnoNoReservado e) {
                                                        System.out.println("\nNo se pudo reservar el turno\n");
                                                    }
                                                } else if (opcion == 2) {
                                                    do {
                                                        System.out.println("Ingrese el dni del pasajero a utilizar");
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

                                                    Pasajero pasajeroRetornado = new Pasajero();

                                                    try {
                                                        int i = recepcionista.getRecepcionista().busquedaPorDNIPasajero(dni);
                                                        pasajeroRetornado = recepcionista.getRecepcionista().retornarPasajeroPorPosicion(i);
                                                    } catch (DNINoExiste e) {
                                                        System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
                                                    } catch (PosicionInvalida e) {
                                                        System.out.println("\nERROR: LA POSICION NO EXISTE\n");
                                                    }

                                                    do {
                                                        System.out.println("¿A que servicio desea anotarse? GIMNASIO-PILETA-DESAYUNADOR");

                                                        try {
                                                            tipo = teclado.next();
                                                            flag = Validacion.validarStringNoNumeros(tipo) && Validacion.validarServicio(tipo);
                                                        } catch (NombreContieneNumeros e) {
                                                            System.out.println("Error: El servicio no puede contener numeros.");
                                                        } catch (TipoIncorrecto e) {
                                                            System.out.println("Error: El servicio es invalido.");
                                                        }
                                                        if (!flag) {
                                                            System.out.println("Por favor, ingrese el tipo de servicio de nuevo, de manera correcta.");
                                                        }

                                                    } while (!flag);

                                                    Servicio aux = recepcionista.getRecepcionista().retornarServicio(tipo);

                                                    try {
                                                        boolean reservadoONo = aux.reservarTurnoPortipo(pasajeroRetornado, tipo);

                                                        actualizarDatosDeServicios(recepcionista, aux);

                                                        if (reservadoONo) {
                                                            System.out.println("Se reservo el turno con exito!");
                                                        }
                                                    } catch (TurnoNoReservado e) {
                                                        System.out.println("\nNo se pudo reservar el turno\n");
                                                    }
                                                } else {
                                                    System.out.println("OPCION INVALIDA");
                                                }

                                            }
                                            case 2 -> {
                                                do {
                                                    System.out.println("Ingrese el dni del pasajero a utilizar");
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

                                                Pasajero pasajeroRetornado = new Pasajero();

                                                try {
                                                    int i = recepcionista.getRecepcionista().busquedaPorDNIPasajero(dni);
                                                    pasajeroRetornado = recepcionista.getRecepcionista().retornarPasajeroPorPosicion(i);
                                                } catch (DNINoExiste e) {
                                                    System.out.println("\nERROR: EL DNI NO CORRESPONDE A NINGUN EMPLEADO\n");
                                                } catch (PosicionInvalida e) {
                                                    System.out.println("\nERROR: LA POSICION NO EXISTE\n");
                                                }

                                                do {
                                                    System.out.println("¿Que servicio de ese dni desea cancelar? GIMNASIO-PILETA-DESAYUNADOR");

                                                    try {
                                                        tipo = teclado.next();
                                                        flag = Validacion.validarStringNoNumeros(tipo) && Validacion.validarServicio(tipo);
                                                    } catch (NombreContieneNumeros e) {
                                                        System.out.println("Error: El servicio no puede contener numeros.");
                                                    } catch (TipoIncorrecto e) {
                                                        System.out.println("Error: El servicio es invalido.");
                                                    }
                                                    if (!flag) {
                                                        System.out.println("Por favor, ingrese el tipo de servicio de nuevo, de manera correcta.");
                                                    }
                                                } while (!flag);

                                                Servicio aux = recepcionista.getRecepcionista().retornarServicio(tipo);

                                                try {
                                                    boolean canceladaONo = aux.cancelarTurnoPortipo(pasajeroRetornado, tipo);

                                                    actualizarDatosDeServicios(recepcionista, aux);

                                                    if (canceladaONo) {
                                                        System.out.println("Se cancelo el turno con exito!");
                                                    }
                                                } catch (TurnoNoCancelado e) {
                                                    System.out.println("\nNo se pudo cancelar el turno\n");
                                                }

                                            }
                                            case 3 -> recepcionista.getRecepcionista().mostrarTodosLosServicios();
                                            case 4 -> {
                                                do {
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

                                                recepcionista.getRecepcionista().busquedaPorDNIServicio(dni);
                                            }
                                            case 5 -> recepcionista.getRecepcionista().mostrarServiciosYPasajeros();
                                        }
                                    }
                                }
                                if (opcion != 8) {
                                    opcionVolverAtras = 0;
                                }

                            } while (opcionVolverAtras == 6);

                            if (opcion != 9) {
                                do {
                                    System.out.println("Desea hacer otra cosa? (s | n)");
                                    try {
                                        control = teclado.next().charAt(0);
                                        flag = Validacion.validarChar(control);
                                    } catch (TeclaIncorrecta e) {
                                        System.out.println("\nERROR: TECLA INVALIDA\n");
                                    }
                                } while (!flag);
                            } else {
                                control = 'n';
                            }

                        } while (control == 's');
                    }
                }
            }

        }while (opcion == 9);

        System.out.println("Gracias por visitarnos! Vuelva pronto :)");
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
            pasajeroManejoArchivo.escribirArchivoSet(new SistemaRecepcionista().getNombreArchPasajeros(), pasajeros);
        }
        catch (IOException e)
        {
            System.out.println("ARCHIVO NO EXISTE, CREANDOLO...");
        }
    }
    public static SistemaRecepcionista baseDeDatosSistema ()
    {
        SistemaRecepcionista sistema = new SistemaRecepcionista();

        ManejoArchivo<Pasajero> pasajeroManejoArchivo = new ManejoArchivo<>();
        Set<Pasajero> pasajeros1 = new HashSet<>();
        try
        {
            Pasajero auxLeer = new Pasajero ();
            pasajeros1 = pasajeroManejoArchivo.leerArchivoSet(sistema.getNombreArchPasajeros(), auxLeer);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        Pasajero[] pasajeros = pasajeros1.toArray(new Pasajero[0]);

        Habitacion h1 = new Habitacion(true,1,pasajeros[0],14,TiposDeMontosHabitaciones.DOBLE,null);
        Habitacion h2 = new Habitacion(false,2,null,0,TiposDeMontosHabitaciones.SIMPLE,null);
        Habitacion h3 = new Habitacion(false,3,null,0,TiposDeMontosHabitaciones.CUADRUPLE,null);
        Habitacion h4 = new Habitacion(true,4,pasajeros[1],7,TiposDeMontosHabitaciones.CUADRUPLE,null);
        Habitacion h5 = new Habitacion(false,5,null,0,TiposDeMontosHabitaciones.SIMPLE,null);
        Habitacion h6 = new Habitacion(true,6,pasajeros[2],21,TiposDeMontosHabitaciones.CUADRUPLE,null);
        Habitacion h7 = new Habitacion(true,7,null,0,TiposDeMontosHabitaciones.DOBLE,MotivoHabitacion.LIMPIEZA);
        Habitacion h8 = new Habitacion(true,8,pasajeros[3],0,TiposDeMontosHabitaciones.DOBLE,null);
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

        Set<Pasajero> aux1 = new HashSet<>();
        Set<Pasajero> aux2 = new HashSet<>();

        aux1.add(pasajeros[0]);
        aux2.add(pasajeros[1]);
        aux1.add(pasajeros[2]);
        aux2.add(pasajeros[2]);

        gimnasio.setPasajerosDelServicio(aux2);
        pileta.setPasajerosDelServicio(aux1);
        desayunador.setPasajerosDelServicio(pasajeros1);

        ArrayList<Servicio> servicios = new ArrayList<>();
        servicios.add(gimnasio);
        servicios.add(desayunador);
        servicios.add(pileta);

        sistema.setServicios(servicios);

        return sistema;
    }
    public static Administrador baseDeDatosEmpleados (Scanner teclado)
    {
        Recepcionista recepcionista = new Recepcionista("Marcos", "Perez", "12345678", "2234567890", "Calle 123", 2, 2000.0, 15, baseDeDatosSistema());
        Empleado empleado2 = new Empleado("Florencia", "López", "85555321", "2239876543", "Avenida 456", 5, 3000.0, true);
        Empleado empleado3 = new Empleado("Pepe", "Gómez", "45333901", "2237418529", "Calle Principal 789", 3, 2500.0, true);
        Empleado empleado4 = new Empleado("Marina", "Martínez", "10999654", "2233647582", "Avenida Central 987", 1, 1800.0, true);
        Conserje conserje1 = new Conserje("Alberto", "Rodríguez", "34562890", "2235647382", "Calle Secundaria 654", 4, 2800.0, 10, true);

        Administrador administrador;
        ManejoArchivo <Empleado> manejoArchivo = new ManejoArchivo<>();

        administrador = cargaUnAdmin(teclado);

        try
        {
            administrador.agregar(empleado2);
            administrador.agregar(empleado3);
            administrador.agregar(empleado4);
            administrador.agregar(conserje1);
            administrador.agregar(recepcionista);
        }
        catch (ObjetoNullException e)
        {
            System.out.println("\nERROR: EMPLEADO NULO\n");
        }

        try
        {
            manejoArchivo.escribirArchivoRecepcionista("Recepcionista.json", recepcionista);
        }catch (IOException e)
        {
            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE RECEPCIONISTA\n");
        }

        try
        {
            manejoArchivo.escribirArchivoConserje("Conserje.json", conserje1);
        }catch (IOException e)
        {
            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE CONSERJE\n");
        }

        try
        {
            manejoArchivo.escribirArchivoSet(administrador.getNombreArchivoEmpleados(), administrador.getEmpleados());
        }catch (IOException e)
        {
            System.out.println("\nERROR AL ESCRIBIR EN EL ARCHIVO DE EMPLEADOS\n");
        }

        return administrador;
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
        int experiencia = 0;
        double salario = 0;

        do
        {
            System.out.println("Ingrese el nombre del empleado");
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
            System.out.println("Ingrese el numero de telefono del empleado");
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

        System.out.println("Ingrese el domicilio del empleado");
        teclado.nextLine();
        String domicilio = teclado.nextLine();

        do
        {
            System.out.println("Ingrese los años de experiencia del empleado");
            try
            {
                experiencia = teclado.nextInt();
                flag = true;
            } catch (InputMismatchException e)
            {
                System.out.println("\nERROR: DEBE SER SOLO UN NUMERO\n");
                flag = false;
                teclado.nextLine();
            }

        }while (!flag);

        do
        {
            System.out.println("Ingrese el salario del empleado");
            try
            {
                salario = teclado.nextDouble();
                flag = true;
            } catch (InputMismatchException e)
            {
                System.out.println("\nERROR: DEBE SER SOLO UN NUMERO\n");
                flag = false;
                teclado.nextLine();
            }

        }while (!flag);

        return new Empleado(nombre, apellido, dni, num, domicilio, experiencia, salario, true);
    }
    public static boolean pagoDeTarjeta ()
    {
        Random random = new Random();

        int numRandom = random.nextInt(100) +1;

        ///si es menor a 90 retorna true que quiere decir que se pago, caso contrario no se paga (probabilidad de 90% de que se pague)
        return (numRandom < 90);
    }
    public static void actualizarDatosDeServicios (Recepcionista recepcionista, Servicio servicioAActualizar)
    {
        ArrayList <Servicio> servicios = recepcionista.getRecepcionista().getServicios();

        servicios.remove(servicioAActualizar);
        servicios.add(servicioAActualizar);

        recepcionista.getRecepcionista().setServicios(servicios);

    }
}