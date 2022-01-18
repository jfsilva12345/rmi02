package s_gestion_usuarios;
import s_gestion_usuarios.sop_rmi.GesUsuariosImpl;
import s_gestion_usuarios.utilidades.UtilidadesConsola;
import s_gestion_usuarios.utilidades.UtilidadesRegistroS;
import java.rmi.RemoteException;

public class ServidorDeObjetos1{

    public static void main(String args[]) throws RemoteException{

        int numPuertoRMIRegistry =0;
        String direccionIpRMIRegistry ="";
        System.out.println("Cual es la direccion ip donde se encuentra el rmiREgistry");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiREgistry");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

        GesUsuariosImpl objRemoto = new GesUsuariosImpl();

        try{
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoPersonal");
        }catch(Exception e){
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto"+ e.getMessage());
        }
    }
}