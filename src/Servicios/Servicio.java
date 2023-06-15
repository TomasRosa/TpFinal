package Servicios;

public class Servicio
{
    private int horarioDeApertura;

    private int horarioDeCierre;
    private int cantCupos;
    private TiposDeServicios tipoServicio;

    public Servicio(int horarioDeApertura, int horarioDeCerrado, int cantCupos, TiposDeServicios tipoServicio) {
        this.horarioDeApertura = horarioDeApertura;
        this.horarioDeCierre = horarioDeCerrado;
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

    public int getHorarioDeCierre() {
        return horarioDeCierre;
    }

    public void setHorarioDeCierre(int horarioDeCierre) {
        this.horarioDeCierre = horarioDeCierre;
    }

    public int getCantCupos() {
        return cantCupos;
    }

    public void setCantCupos(int cantCupos) {
        this.cantCupos = cantCupos;
    }

    public TiposDeServicios getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TiposDeServicios tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public void mostrarServicio (TiposDeServicios servicio)
    {
        System.out.println("\n---------------------SERVICIO: " + servicio + "---------------------");
        System.out.println("ABIERTO DE " + horarioDeApertura + "hs HASTA " + horarioDeCierre + "hs");
        System.out.println("CANTIDAD DE CUPOS: " + cantCupos);
    }

    public boolean reservarTurno (){

        if(cantCupos>0){
            cantCupos--;
            System.out.println("Ya tiene su turno para el servicio de:  " + tipoServicio);
            return true;

        }
        else{

            System.out.println("Disculpe, el turno del servicio elegido no cuenta con disponibilidad de cupos. Intente mas tarde.");
            return false;
        }
    }


    public boolean cancelarTurno (String dni){

        System.out.println("Por favor ingrese su DNI para verificar sus turnos pendientes: ");


          return false;

    }
}
