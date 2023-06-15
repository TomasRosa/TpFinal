package Reservas;
import Enum.TiposDeMontosHabitaciones;

public class Habitacion
{
    private Boolean ocupadaONo = false; ///La habitacion comienza vacia, una vez que la reserven su estado cambia a true.
    private int numero; ///Numero de habitacion, distintivo.
    private double montoPorDia; ///Precio de cada tipo de habitacion.
    private Pasajero pasajeroQueLaOcupa = null; ///Ya que la habitacion comienza vacia esto quiere decir que el pasajero que la ocupa es nulo.
    private int cantDiasQueSeraOcupada; ///Cantidad de dias que estara reservada la habitacion.
    private TiposDeMontosHabitaciones tipo;

    public enum motivo
    {

         LIMPIEZA ("Disculpe, esta habitacion esta siendo en proceso de higienizacion"),
         REPARACION ("Disculpe, estamos haciendo remodelaciones en esta habitacion"),
         DESINFECCION ("Disculpe, esta habitacion esta siento desinfectada");

        private String descripcion;

       private motivo(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }



    }
    public Habitacion(Boolean ocupadaONo, int numero, double montoPorDia, Pasajero pasajeroQueLaOcupa, int cantDiasQueSeraOcupada, TiposDeMontosHabitaciones tipo) {
        this.ocupadaONo = ocupadaONo;
        this.numero = numero;
        this.montoPorDia = montoPorDia;
        this.pasajeroQueLaOcupa = pasajeroQueLaOcupa;
        this.cantDiasQueSeraOcupada = cantDiasQueSeraOcupada;
        this.tipo = tipo;
    }

    public TiposDeMontosHabitaciones getTipo() {
        return tipo;
    }

    public void setTipo(TiposDeMontosHabitaciones tipo) {
        this.tipo = tipo;
    }

    public Habitacion() {
    }

    public Boolean desocuparHabitacion()
    {
        return true;
    }

    public Boolean getOcupadaONo() {
        return ocupadaONo;
    }

    public void setOcupadaONo(Boolean ocupadaONo) {
        this.ocupadaONo = ocupadaONo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getMontoPorDia() {
        return montoPorDia;
    }

    public void setMontoPorDia(double montoPorDia) {
        this.montoPorDia = montoPorDia;
    }

    public Pasajero getPasajeroQueLaOcupa() {
        return pasajeroQueLaOcupa;
    }

    public void setPasajeroQueLaOcupa(Pasajero pasajeroQueLaOcupa) {
        this.pasajeroQueLaOcupa = pasajeroQueLaOcupa;
    }

    public int getCantDiasQueSeraOcupada() {
        return cantDiasQueSeraOcupada;
    }

    public void setCantDiasQueSeraOcupada(int cantDiasQueSeraOcupada) {
        this.cantDiasQueSeraOcupada = cantDiasQueSeraOcupada;
    }

    @Override
    public String toString() {
        return "Reservas.Habitacion{" +
                "ocupadaONo=" + ocupadaONo +
                ", numero=" + numero +
                ", montoPorDia=" + montoPorDia +
                '}';
    }

    public double valorMonto ()
    {
        return 0;

    }

    public double calculoPorDias (int cantDias)
    {
        return montoPorDia*cantDias;
    }
}
