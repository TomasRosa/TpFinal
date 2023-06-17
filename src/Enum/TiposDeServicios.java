package Enum;

public enum TiposDeServicios {
    GIMNASIO("Gimnasio"),
    PILETA("Pileta"),
    DESAYUNADOR("Desayunador");
    private final String servicios;

    TiposDeServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getServicios() {
        return servicios;
    }
}
