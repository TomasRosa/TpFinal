package Enum;

public enum TiposDeServicios {
    GIMNASIO("GIMNASIO"),
    PILETA("PILETA"),
    DESAYUNADOR("DESAYUNADOR");
    private final String servicios;

    TiposDeServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getServicios() {
        return servicios;
    }
}
