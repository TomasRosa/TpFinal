import Archivos.ManejoArchivo;
import Reservas.Pasajero;
import Reservas.Tarjeta;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        baseDeDatosPasajeros();
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

        try
        {
            ArrayList<Pasajero> pasajeros1 = pasajeroManejoArchivo.leerArchivoList("archivo_pasajeros.json");
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
        }
    }
}