/*GestionNotificacionesInt.java*/
package s_seguimiento_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import s_seguimiento_usuarios.dto.NotificacionDTO;

public class GestionNotificacionesImpl extends UnicastRemoteObject implements GestionNotificacionesInt {
 public GestionNotificacionesImpl() throws RemoteException{
     super();
 }
 @Override
 public void enviarNotificacion(NotificacionDTO objNotificacion) throws RemoteException{
        System.out.println("**La persona:"+objNotificacion.getNombreCompleto()+", con ocupacion"+objNotificacion.getOcupacion()+"**");
        System.out.println("**esta autorizado para ingresar al sistema**");
 }
}