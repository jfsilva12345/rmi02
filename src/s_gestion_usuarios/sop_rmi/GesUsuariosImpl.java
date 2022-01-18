package s_gestion_usuarios.sop_rmi;

import s_seguimiento_usuarios.sop_rmi.GestionNotificacionesInt;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.utilidades.UtilidadesRegistroC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GesUsuariosImpl extends UnicastRemoteObject implements GesUsuariosInt{
    private ArrayList<PersonalDTO> personal;
    private int contador=0;
    private GestionNotificacionesInt objReferenciaRemota;
    
    public GesUsuariosImpl() throws RemoteException{
        super();
        this.personal = new ArrayList();
    }

    @Override
    public boolean registrarUsuario(PersonalDTO objUsuario) throws RemoteException{
        System.out.println("Entrando a registrar usuario");
        boolean bandera=false;
        
        if(personal.size() < 2)
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
        for(PersonalDTO varPersonal : personal){
            if(personal.get(contador).getId()==id){
                
                objUsuario=personal.get(contador);

                break;
            }
            contador++;
        }
        return objUsuario;  
    }

    @Override
    public int abrirSesion(CredencialDTO objCredencial) throws RemoteException{
        switch(ocupacionBuscadaCredenciales(objCredencial)){
            case "Admin":
                return 0;
            case "paf":
                return 1;
            case "Secretaria":
                return 2;
        }
        return -1;
    }

    public boolean usuarioExiste(CredencialDTO objCredencial){
        String tmpUsuario=objCredencial.getUsuario();
        int contador = 0;
        for(PersonalDTO varPersonal : personal){
            if(personal.get(contador).getUsuario()==tmpUsuario){
                return true;
            }
            contador++;
        }
        return false;
    }

    public String ocupacionBuscadaCredenciales(CredencialDTO objCredencial){
        if(!usuarioExiste(objCredencial)){
            return "Inexistente";
        }
        String tmpUsuario=objCredencial.getUsuario();
        int contador = 0;
        while(contador < personal.size()){
            if(personal.get(contador).getUsuario()==tmpUsuario){
                return personal.get(contador).getOcupacion();
            }
            contador++;
        }
        return "Inexistente";
    }

    public void consultarReferenciaRemota(String direccionIpRMIRegistry, int numPuertoRMIRegistry) {
        System.out.println(" ");
        System.out.println(" Desde consultarREferenciaREmota()... ");
        this.objReferenciaRemota=(GestionNotificacionesInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoAlerta");
    }
}
