package sop_rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface GesUsuariosInt extends Remote{

 public boolean registrarPersonal(PersonalDTO obj) throws RemoteException;
 public PersonalDTO consultarPersonal(int id) throws RemoteException;

}