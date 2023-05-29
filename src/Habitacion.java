public class Habitacion
{
    private Boolean ocupadaONo;
    private int numero;
    private double montoPorDia;

    public enum motivo
    {
    }

    public enum tipo
    {

    }

    public Habitacion(Boolean ocupadaONo, int numero, double montoPorDia) {
        this.ocupadaONo = ocupadaONo;
        this.numero = numero;
        this.montoPorDia = montoPorDia;
    }

    public Habitacion() {
    }

    public Boolean desocuparHabitacion()
    {
        return true;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "ocupadaONo=" + ocupadaONo +
                ", numero=" + numero +
                ", montoPorDia=" + montoPorDia +
                '}';
    }

    public double valorMonto ()
    {
        return  0;
    }

    public double calculoPorDias (int cantDias)
    {
        return 0;
    }
}
