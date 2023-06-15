package Enum;

public enum TiposDeServicios {
    GIMNASIO("Gimnasio"),
    PILETA("Pileta"),
    DESAYUNADOR("Desayunador");
    String servicios;

    TiposDeServicios(String servicios) {
        this.servicios = servicios;
    }
}
