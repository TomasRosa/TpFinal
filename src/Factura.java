import java.time.LocalDate;
import java.util.UUID;

public class Factura
{
    Pasajero pasajero;
    private UUID id;

    private LocalDate fecha;
    private double monto;

    Habitacion habitacion;

    public void mostrarFactura ()
    {
        System.out.println("\n-------------------FACTURA-------------------");
        pasajero.mostrar();
        System.out.println("\nID: " + id);
        System.out.println("FECHA: " + fecha);
        System.out.println("MONTO: " + monto);
        habitacion.toString();
    }
}
