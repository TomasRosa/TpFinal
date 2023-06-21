package Reservas;
import java.time.LocalDate;
import java.util.UUID;
public class Factura
{
    Pasajero pasajero;
    private UUID id;
    private LocalDate fecha;
    private double monto;
    Habitacion habitacion;

    public Factura(Pasajero pasajero, UUID id, LocalDate fecha, double monto, Habitacion habitacion)
    {
        this.pasajero = pasajero;
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.habitacion = habitacion;
    }

    public Factura()
    {

    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void mostrarFactura ()
    {
        System.out.println("\n-------------------FACTURA-------------------");
        System.out.println("A cargo de: " + pasajero.getNombre() + " "+ pasajero.getApellido());
        System.out.println("ID: " + id);
        System.out.println("FECHA: " + fecha);
        System.out.println("MONTO: " + monto);
        habitacion.toString();
    }
}
