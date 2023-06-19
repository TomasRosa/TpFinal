package Servicios;
import Enum.TiposDeServicios;
import Excepciones.TurnoNoCancelado;
import Excepciones.TurnoNoReservado;
import Reservas.Pasajero;
import java.util.HashSet;

public class Servicio {
    private String horarioDeApertura;
    private String horarioDeCierre;
    private int cantCupos;
    private TiposDeServicios tipoServicio;
    private HashSet<Pasajero> pasajerosDelServicio;

    public Servicio(String horarioDeApertura, String horarioDeCerrado, int cantCupos, TiposDeServicios tipoServicio) {
        this.horarioDeApertura = horarioDeApertura;
        this.horarioDeCierre = horarioDeCerrado;
        this.cantCupos = cantCupos;
        this.tipoServicio = tipoServicio;
    }

    public Servicio() {

    }

    public String getHorarioDeApertura() {
        return horarioDeApertura;
    }

    public HashSet<Pasajero> getPasajerosDelServicio() {
        return pasajerosDelServicio;
    }

    public void setPasajerosDelServicio(HashSet<Pasajero> pasajerosDelServicio) {
        this.pasajerosDelServicio = pasajerosDelServicio;
    }

    public void setHorarioDeApertura(String horarioDeApertura) {
        this.horarioDeApertura = horarioDeApertura;
    }

    public String getHorarioDeCierre() {
        return horarioDeCierre;
    }

    public void setHorarioDeCierre(String horarioDeCierre) {
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

    public void mostrarServicio(TiposDeServicios servicio) {
        System.out.println("\n---------------------SERVICIO: " + servicio + "---------------------");
        System.out.println("ABIERTO DE " + horarioDeApertura + "hs HASTA " + horarioDeCierre + "hs");
        System.out.println("CANTIDAD DE CUPOS: " + cantCupos);
    }

    public boolean reservarTurnoPortipo(Pasajero pasajero, String tipo) { ///en el limitamos que el enum no salga de los limites

        boolean reservar = false;

        if (tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.GIMNASIO))) {
            reservar = resevarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.PILETA))) {
            reservar = resevarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.DESAYUNADOR))) {
            reservar = resevarTurno(pasajero);
        }

        return reservar;
    }

    public boolean resevarTurno(Pasajero pasajero) throws TurnoNoReservado {

        try {
            if (cantCupos > 0) {
                this.pasajerosDelServicio.add(pasajero);
                cantCupos--;
                System.out.println("Ya tiene su turno para el servicio de:  " + tipoServicio);
                return true;
            } else {
                throw new TurnoNoReservado();

            }
        } catch (TurnoNoReservado e) {
            System.out.println("Disculpe, el turno del servicio elegido no cuenta con disponibilidad de cupos. Intente mas tarde.");
            return false;
        }
    }

    public boolean cancelarTurnoPortipo(Pasajero pasajero, String tipo) { ///en el limitamos que el enum no salga de los limites

        boolean cancelar = false;

        if (tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.GIMNASIO))) {
            cancelar = cancelarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.PILETA))) {
            cancelar = cancelarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase(String.valueOf(TiposDeServicios.DESAYUNADOR))) {
            cancelar = cancelarTurno(pasajero);
        }
        return cancelar;
    }

    public boolean cancelarTurno(Pasajero pasajero) throws TurnoNoCancelado {
        try {
            if (this.pasajerosDelServicio.contains(pasajero)) {
                cantCupos++;
                this.pasajerosDelServicio.remove(pasajero);
                System.out.println("Su turno ha sido cancelado.");
                return true;
            } else {
                throw new TurnoNoCancelado();

            }
        } catch (TurnoNoCancelado E) {
            System.out.println("El turno no puede ser cancelado porque no se encuentra al pasajero dentro de servicios");
            return false;
        }
    }
}

