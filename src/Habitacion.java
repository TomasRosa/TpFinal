public class Habitacion
{
    private Boolean ocupadaONo;
    private int numero;
    private double montoPorDia;

    public enum motivo
    {

         LIMPIEZA ("Disculpe, esta habitacion, esta siendo en proceso de higienizacion"),
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
