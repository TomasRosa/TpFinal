package Reservas;
import Enum.TiposDeMontosHabitaciones;
import Enum.MotivoHabitacion;
public class Habitacion {
    private Boolean ocupadaONo = false; ///La habitacion comienza vacia, una vez que la reserven su estado cambia a true.
    private int numero; ///Numero de habitacion, distintivo.
    private Pasajero pasajeroQueLaOcupa = null; ///Ya que la habitacion comienza vacia esto quiere decir que el pasajero que la ocupa es nulo.
    private int cantDiasQueSeraOcupada; ///Cantidad de dias que estara reservada la habitacion.
    private TiposDeMontosHabitaciones tipo;

    ///consultar que les parece la idea de hacer un fecha inicio y fecha fin


    public Habitacion(Boolean ocupadaONo, int numero, Pasajero pasajeroQueLaOcupa, int cantDiasQueSeraOcupada, TiposDeMontosHabitaciones tipo) {
        this.ocupadaONo = ocupadaONo;
        this.numero = numero;
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

    public void desocuparHabitacion() {
        if (ocupadaONo) {

            ocupadaONo = false;
            pasajeroQueLaOcupa = null;
            cantDiasQueSeraOcupada = 0;

            System.out.println("La habitacion : " + numero + " ha sido desocupada con exito.");
        } else {

            System.out.println("La habitacion: " + numero + " que desea desocupar esta actualmente sin alojaciones.");

        }

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
        return "Habitacion{" +
                "ocupadaONo=" + ocupadaONo +
                ", numero=" + numero +
                ", pasajeroQueLaOcupa=" + pasajeroQueLaOcupa +
                ", cantDiasQueSeraOcupada=" + cantDiasQueSeraOcupada +
                ", tipo=" + tipo +
                '}';
    }

    public double valorMonto() ///preguntar que se le pasaria
    {

        return 0;
    }

    public double calculoPorDias(String tipo, int cantDias) ///ver en donde lo implemnetamos en el main
    {


        if (tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.SIMPLE))) {
            return TiposDeMontosHabitaciones.SIMPLE.getPrecioDia() * cantDias;
        }
        if (tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.DOBLE))) {
            return TiposDeMontosHabitaciones.DOBLE.getPrecioDia() * cantDias;
        }
        if (tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.CUADRUPLE))) {
            return TiposDeMontosHabitaciones.CUADRUPLE.getPrecioDia() * cantDias;
        }

        return 0;
    }
}
