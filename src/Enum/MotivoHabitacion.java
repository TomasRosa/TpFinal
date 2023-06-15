package Enum;

public enum MotivoHabitacion {

    LIMPIEZA("Disculpe, esta habitacion esta siendo en proceso de higienizacion"),
    REPARACION("Disculpe, estamos haciendo remodelaciones en esta habitacion"),
    DESINFECCION("Disculpe, esta habitacion esta siento desinfectada");

    private String descripcion;

    MotivoHabitacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
            return descripcion;
        }
}
