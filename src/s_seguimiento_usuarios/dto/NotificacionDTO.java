package s_seguimiento_usuarios.dto;

import java.io.Serializable;

public class NotificacionDTO implements Serializable{
    private String nombreCompleto;
    private String ocupacion;

    public NotificacionDTO(String nombreCompleto, String ocupacion) {
        this.nombreCompleto = nombreCompleto;
        this.ocupacion = ocupacion;
    }


    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getOcupacion() {
        return this.ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

}