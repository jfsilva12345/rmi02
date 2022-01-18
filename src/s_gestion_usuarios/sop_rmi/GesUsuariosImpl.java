package s_gestion_usuarios.sop_rmi;

import s_seguimiento_usuarios.dto.NotificacionDTO;
import s_seguimiento_usuarios.sop_rmi.GestionNotificacionesInt;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.utilidades.UtilidadesRegistroC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GesUsuariosImpl extends UnicastRemoteObject implements GesUsuariosInt{
    private ArrayList<PersonalDTO> personal;
    private GestionNotificacionesInt objReferenciaRemota;
    
    public GesUsuariosImpl() throws RemoteException{
        super();
        this.personal = new ArrayList<>();
        String tipoId = "CC";
        int id = 6536;
        String nombrecompleto = "Josefino Eusebio De las Nieves";
        String ocupacion = "Admin";
        String usuario = "admin12345";
        String clave = "12345678";
        PersonalDTO admin = new PersonalDTO(tipoId, id, nombrecompleto, ocupacion, usuario, clave);
        personal.add(admin);
    }

    @Override
    public boolean registrarUsuario(PersonalDTO objUsuario) throws RemoteException{
        System.out.println("Entrando a registrar usuario");
        boolean bandera=false;
        
        if(personal.size() < 3)
        {            
            bandera=personal.add(objUsuario);
            System.out.println("Usuario registrado: identificación: " + objUsuario.getId() + ", nombres: " + objUsuario.getNombreCompleto());
        }
        
        return bandera;
    }

    @Override
    public PersonalDTO consultarUsuario(int id) throws RemoteException{
        
        System.out.println("Entrando a consultar usuario");
        PersonalDTO objUsuario=null;
        int contador = 0;
        for (PersonalDTO personalDTO : personal) {
            if(personalDTO.getId() == id){
                objUsuario=personal.get(contador);
                break;
            }
        }
        return objUsuario;  
    }

    @Override
    public int abrirSesion(CredencialDTO objCredencial) throws RemoteException{
        NotificacionDTO tmpNotificacion = ocupacionBuscadaCredenciales(objCredencial);
        String ocupacion = tmpNotificacion.getOcupacion();
        switch(ocupacion){
            case "Admin":
                return 0;
            case "Personal de acondicionamiento fisico":
                NotificacionDTO notificacion = tmpNotificacion;
                objReferenciaRemota.enviarNotificacion(notificacion);
                return 1;
            case "Secretaria":
                return 2;
        }
        return -1;
    }

    public boolean usuarioExiste(CredencialDTO objCredencial){
        String tmpUsuario=objCredencial.getUsuario();
        for (PersonalDTO personalDTO : personal) {
            if(personalDTO.getUsuario().equals(tmpUsuario)){
                return true;
            }
        }
        return false;
    }

    public NotificacionDTO ocupacionBuscadaCredenciales(CredencialDTO objCredencial){
        if(!usuarioExiste(objCredencial)){
            return null;
        }
        String tmpUsuario=objCredencial.getUsuario();
        for (PersonalDTO personalDTO : personal) {
            if(personalDTO.getUsuario().equals(tmpUsuario)){
                NotificacionDTO notificacion=new NotificacionDTO(personalDTO.getNombreCompleto(), personalDTO.getOcupacion());
                return notificacion;
            }
        }
        return null;
    }

    public void consultarReferenciaRemota(String direccionIpRMIRegistry, int numPuertoRMIRegistry) {
        System.out.println(" ");
        System.out.println(" Desde consultarREferenciaREmota()... ");
        this.objReferenciaRemota=(GestionNotificacionesInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoAlerta");
    }
}
