package sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GesUsuariosImpl extends UnicastRemoteObject implements GesUsuariosInt{
    private ArrayList<PersonalDTO> personal;

    
    public GesUsuariosImpl() throws RemoteException{
        super();
        this.personal = new ArrayList();
    }

    @Override
    public boolean registrarPersonal(PersonalDTO objUsuario) throws RemoteException{
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
    public PersonalDTO consultarPersonal(int id) throws RemoteException{
        
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
}
