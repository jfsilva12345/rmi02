package s_seguimiento_usuarios;

import s_seguimiento_usuarios.utilidades.UtilidadesConsola;
import s_seguimiento_usuarios.utilidades.UtilidadesRegistroS;
import s_seguimiento_usuarios.sop_rmi.GestionNotificacionesImpl;
import java.rmi.RemoteException;

public class ServidorDeObjetos2{

    public static void main(String args[]) throws RemoteException{

        int numPuertoRMIRegistry =0;
        String direccionIpRMIRegistry ="";
        System.out.println("Cual es la direccion ip donde se encuentra el rmiREgistry");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiREgistry");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

        GestionNotificacionesImpl objRemoto = new GestionNotificacionesImpl();

        try{
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoNotificacion");
        }catch(Exception e){
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto"+ e.getMessage());
        }
    }
}