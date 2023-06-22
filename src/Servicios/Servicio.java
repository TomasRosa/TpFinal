package Servicios;
import Enum.TiposDeServicios;
import Excepciones.TurnoNoCancelado;
import Excepciones.TurnoNoReservado;
import Reservas.Pasajero;
import java.util.HashSet;
import java.util.Set;

public class Servicio
{
    private String horarioDeApertura;
    private String horarioDeCierre;
    private int cantCupos;
    private TiposDeServicios tipoServicio;
    private Set<Pasajero> pasajerosDelServicio = new HashSet<>();

    public Servicio(String horarioDeApertura, String horarioDeCerrado, int cantCupos, TiposDeServicios tipoServicio) {
        this.horarioDeApertura = horarioDeApertura;
        this.horarioDeCierre = horarioDeCerrado;
        this.cantCupos = cantCupos;
        this.tipoServicio = tipoServicio;
    }

    public Servicio()
    {

    }

    public String getHorarioDeApertura() {
        return horarioDeApertura;
    }

    public Set<Pasajero> getPasajerosDelServicio() {
        return pasajerosDelServicio;
    }

    public void setPasajerosDelServicio(Set<Pasajero> pasajerosDelServicio) {
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

    public void mostrarServicio()
    {
        System.out.println("\n---------------------SERVICIO: " + tipoServicio + "---------------------");
        System.out.println("ABIERTO DE " + horarioDeApertura + " hs HASTA " + horarioDeCierre + " hs");
        System.out.println("CANTIDAD DE CUPOS: " + cantCupos);
    }

    public boolean reservarTurnoPortipo(Pasajero pasajero, String tipo)
    { ///en el limitamos que el enum no salga de los limites

        boolean reservar = false;

        if (tipo.equalsIgnoreCase((TiposDeServicios.GIMNASIO.getServicios())))
        {
            reservar = reservarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase((TiposDeServicios.PILETA.getServicios())))
        {
            reservar = reservarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase((TiposDeServicios.DESAYUNADOR.getServicios())))
        {
            reservar = reservarTurno(pasajero);
        }

        return reservar;
    }

    public boolean reservarTurno(Pasajero pasajero) throws TurnoNoReservado
    {
            if (cantCupos > 0)
            {
                pasajerosDelServicio.add(pasajero);
                cantCupos--;
                return true;
            }
            else
            {
                throw new TurnoNoReservado();
            }
    }

    public boolean cancelarTurnoPortipo(Pasajero pasajero, String tipo) { ///en el limitamos que el enum no salga de los limites

        boolean cancelar = false;

        if (tipo.equalsIgnoreCase((TiposDeServicios.GIMNASIO.getServicios()))) {
            cancelar = cancelarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase((TiposDeServicios.PILETA.getServicios()))) {
            cancelar = cancelarTurno(pasajero);
        } else if (tipo.equalsIgnoreCase((TiposDeServicios.DESAYUNADOR.getServicios()))) {
            cancelar = cancelarTurno(pasajero);
        }
        return cancelar;
    }

    public boolean cancelarTurno(Pasajero pasajero) throws TurnoNoCancelado{

            if (pasajerosDelServicio.contains(pasajero)) {
                cantCupos++;
                pasajerosDelServicio.remove(pasajero);
                System.out.println("Su turno ha sido cancelado.");
                return true;
            }
            else
            {
                throw new TurnoNoCancelado();
            }
    }
}

