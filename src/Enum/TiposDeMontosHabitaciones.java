package Enum;

public enum TiposDeMontosHabitaciones
{
    SIMPLE (10000 ),
    DOBLE (17000),
    CUADRUPLE (24000);

    private double precioDia;

    private TiposDeMontosHabitaciones(double precioDia) {
        this.precioDia=precioDia;
    }

    public double getPrecioDia() {
        return precioDia;
    }
}
