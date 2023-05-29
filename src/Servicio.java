public class Servicio
{
    private int horarioDeApertura;

    private int horarioDeCerrado;

    private int cantCupos;
    private String tipoServicio;

    public Servicio(int horarioDeApertura, int horarioDeCerrado, int cantCupos, String tipoServicio) {
        this.horarioDeApertura = horarioDeApertura;
        this.horarioDeCerrado = horarioDeCerrado;
        this.cantCupos = cantCupos;
        this.tipoServicio = tipoServicio;
    }

    public Servicio() {
    }

    public int getHorarioDeApertura() {
        return horarioDeApertura;
    }

    public void setHorarioDeApertura(int horarioDeApertura) {
        this.horarioDeApertura = horarioDeApertura;
    }

    public int getHorarioDeCerrado() {
        return horarioDeCerrado;
    }

    public void setHorarioDeCerrado(int horarioDeCerrado) {
        this.horarioDeCerrado = horarioDeCerrado;
    }

    public int getCantCupos() {
        return cantCupos;
    }

    public void setCantCupos(int cantCupos) {
        this.cantCupos = cantCupos;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public void mostrarServicio ()
    {
        System.out.println("\n---------------------SERVICIO: " + tipoServicio.toUpperCase() + "---------------------");
        System.out.println("ABIERTO DE " + horarioDeApertura + "hs HASTA " + horarioDeCerrado + "hs");
        System.out.println("CANTIDAD DE CUPOS: " + cantCupos);
    }
}
