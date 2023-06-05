public class Habitacion
{
    private Boolean ocupadaONo = false; ///La habitacion comienza vacia, una vez que la reserven su estado cambia a true.
    private int numero; ///Numero de habitacion, distintivo.
    private double montoPorDia; ///Precio de cada tipo de habitacion.
    private Pasajero pasajeroQueLaOcupa = null; ///Ya que la habitacion comienza vacia esto quiere decir que el pasajero que la ocupa es nulo.
    private int cantDiasQueSeraOcupada; ///Cantidad de dias que estara reservada la habitacion.

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

    public enum tipo
    {

        SIMPLE (10000 ),
        DOBLE (17000),
        CUADRUPLE (24000);

        private double precioDia;

        private tipo(double precioDia) {
            this.precioDia=precioDia;
        }

        public double getPrecioDia() {
            return precioDia;
        }
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
        return "Habitacion{" +
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
