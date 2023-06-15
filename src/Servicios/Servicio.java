package Servicios;
import Enum.TiposDeServicios;
import java.util.HashMap;


public class Servicio
{
    private int horarioDeApertura;

    private int horarioDeCierre;
    private int cantCupos;
    private TiposDeServicios tipoServicio;

    private HashMap<String, Boolean> turnos=new HashMap<>();


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

    public HashMap<String, Boolean> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<String, Boolean> turnos) {
        this.turnos = turnos;
    }

    public void mostrarServicio (TiposDeServicios servicio)
    {
        System.out.println("\n---------------------SERVICIO: " + servicio + "---------------------");
        System.out.println("ABIERTO DE " + horarioDeApertura + "hs HASTA " + horarioDeCierre + "hs");
        System.out.println("CANTIDAD DE CUPOS: " + cantCupos);
    }

    public boolean reservarTurnoPortipo (String dni, String tipo){ ///en el limitamos que el enum no salga de los limites

        boolean reservar=false;

        if(tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.GIMNASIO))){
            reservar=resevarTurno(dni);
        }
        else if(tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.PILETA))){
            reservar=resevarTurno(dni);
        }
        else if(tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.DESAYUNADOR))){
            reservar=resevarTurno(dni);
        }

        return reservar;

    }

    public boolean resevarTurno(String dni) {


            if (cantCupos > 0) {
                cantCupos--;
                System.out.println("Ya tiene su turno para el servicio de:  " + tipoServicio);
                turnos.put(dni, true);
                return true;

            } else {

                System.out.println("Disculpe, el turno del servicio elegido no cuenta con disponibilidad de cupos. Intente mas tarde.");
                return false;
            }
        }

    public boolean cancelarTurnoPortipo (String dni, String tipo){ ///en el limitamos que el enum no salga de los limites

        boolean cancelar=false;

        if(tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.GIMNASIO))){
            cancelar=cancelarTurno(dni);
        }
        else if(tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.PILETA))){
            cancelar=cancelarTurno(dni);
        }
        else if(tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.DESAYUNADOR))){
            cancelar=cancelarTurno(dni);
        }

        return cancelar;

    }

    public boolean cancelarTurno (String dni ){ ///Le paso el dni por el main y cancela el turno del dni reservado

        if(turnos.containsKey(dni)){
            if(turnos.get(dni)){
                turnos.put(dni, false);
                cantCupos++;
                System.out.println("Su turno ha sido cancelado.");
                return true;
            }else{

                System.out.println("El turno con ese DNI, ya ha sido cancelado previamente.");
            }
        }else{
            System.out.println("No se encontro un turno que este asociado con su DNI.");
        }
        return false;

    }
}
