package Reservas;
import Enum.TiposDeMontosHabitaciones;
import Enum.MotivoHabitacion;
public class Habitacion
{
    private Boolean ocupadaONo = false; ///La habitacion comienza vacia, una vez que la reserven su estado cambia a true.
    private int numero; ///Numero de habitacion, distintivo.
    private Pasajero pasajeroQueLaOcupa = null; ///Ya que la habitacion comienza vacia esto quiere decir que el pasajero que la ocupa es nulo.
    private int cantDiasQueSeraOcupada; ///Cantidad de dias que estara reservada la habitacion.
    private TiposDeMontosHabitaciones tipo;
    private MotivoHabitacion motivo;

    public Habitacion(Boolean ocupadaONo, int numero, Pasajero pasajeroQueLaOcupa, int cantDiasQueSeraOcupada, TiposDeMontosHabitaciones tipo, MotivoHabitacion motivo) {
        this.ocupadaONo = ocupadaONo;
        this.numero = numero;
        this.pasajeroQueLaOcupa = pasajeroQueLaOcupa;
        this.cantDiasQueSeraOcupada = cantDiasQueSeraOcupada;
        this.tipo = tipo;
        this.motivo = motivo;
    }

    public MotivoHabitacion getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoHabitacion motivo) {
        this.motivo = motivo;
    }

    public TiposDeMontosHabitaciones getTipo() {
        return tipo;
    }

    public void setTipo(TiposDeMontosHabitaciones tipo) {
        this.tipo = tipo;
    }

    public Habitacion() {
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
    public void mostrarHabitacion ()
    {
        System.out.println("---Habitacion---");
        System.out.println("NRO de habitacion: " + this.numero);
        if(this.ocupadaONo == false)
        {
            System.out.println("La habitacion no esta ocupada. ");
            if(this.motivo != null)
            {
                System.out.println("Debido a: " + this.motivo);
            }
        }
        else if(pasajeroQueLaOcupa != null)
        {
            System.out.println("La habitacion se encuentra ocupada por: ");
            pasajeroQueLaOcupa.mostrar();
        }
        else
        {
            System.out.println("La habitacion se encuentra ocupada por el siguiente motivo: " + this.motivo.getDescripcion());
        }
        if(this.cantDiasQueSeraOcupada > 0)
        {
            System.out.println("Sera ocupada por: " + this.cantDiasQueSeraOcupada + " dias.");

        }
        System.out.println("La habitacion es: " + this.tipo);
    }

    public double calculoPorDias(String tipo) ///ver en donde lo implemnetamos en el main CUANDO RESERVE SE LE INFORMA CUANTO LE SALE
    {
        if (tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.SIMPLE)))
        {
            return TiposDeMontosHabitaciones.SIMPLE.getPrecioDia() * this.cantDiasQueSeraOcupada;
        }
        else if(tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.DOBLE))) {
            return TiposDeMontosHabitaciones.DOBLE.getPrecioDia() * this.cantDiasQueSeraOcupada;
        }
        else if(tipo.equalsIgnoreCase(String.valueOf(TiposDeMontosHabitaciones.CUADRUPLE))) {
            return TiposDeMontosHabitaciones.CUADRUPLE.getPrecioDia() * this.cantDiasQueSeraOcupada;
        }
        return -1;
    }
}
