package Enum;

public enum MotivoHabitacion {

    LIMPIEZA("Habitacion en proceso de higienizacion."),
    REPARACION("Habitacion en proceso de remodelacion."),
    DESINFECCION("Habitacion en proceso de desinfeccion.");

    private final String descripcion;

    MotivoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
            return descripcion;
        }
}
