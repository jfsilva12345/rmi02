package cliente;

import sop_rmi.GesUsuariosInt;
import sop_rmi.PersonalDTO;
import java.rmi.RemoteException;

public class ClienteDeObjetos{
    private static GesUsuariosInt objRemoto;

    public static void main (String[] args){
        int numPuertoRMIRegistry =0;
        String direccionIpRMIRegistry ="";
        System.out.println("Cual es la direccion ip donde se encuentra el rmiREgistry");
        direccionIpRMIRegistry = cliente.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiREgistry");
        numPuertoRMIRegistry = cliente.UtilidadesConsola.leerEntero();

        objRemoto = (GesUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry,"ObjetoRemotoPersonal");
        MenuPrincipal();
    }
    

    private static void MenuPrincipal()
    {
        int opcion = 0;
        
        do
        {
            System.out.println("==Menu==");
            System.out.println("1. Registrar personal");			
            System.out.println("2. Consultar personal");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch(opcion)
            {
                case 1:
                        Opcion1();
                        break;
                case 2:
                        Opcion2();
                        break;	
                case 3:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opción incorrecta");
            }

        }while(opcion != 4);
    }

    private static void Opcion1() 
    {
        try
        {
            System.out.println("==Registro del Cliente==");
            boolean bandera=false;
            int opcionTI = 0;
            String varTipoIdentificacion="";

                System.out.println("==TIPO DE IDENTIFICACION==");
                System.out.println("1. Cedula de Ciudadania");			
                System.out.println("2. Tarjeta de Identidad");
                System.out.println("3. Pasaporte");
                


                opcionTI = UtilidadesConsola.leerEntero();

                if(opcionTI==1){
                    varTipoIdentificacion="CC";
                }else if(opcionTI==2){
                    varTipoIdentificacion="TI";
                }else if(opcionTI==3){
                    varTipoIdentificacion="PP";
                }else{
                    bandera=true;
                }


            System.out.println("Ingrese el numero de identificacion");
            int varId = UtilidadesConsola.leerEntero();
            if (varId < 0){
                bandera = true;
            }

            System.out.println("Ingrese el nombre completo ");
            String varNombres = UtilidadesConsola.leerCadena();

            System.out.println("Ingrese la ocupacion del nuevo usuario ");
            String varOcupacion="";

                System.out.println("==TIPO DE OCUPACION==");
                System.out.println("1. Secretaria");			
                System.out.println("2. Profesional de acondicionamiento fisico");
                

                opcionTI = UtilidadesConsola.leerEntero();


                if(opcionTI==1){
                    varTipoIdentificacion="Secretaria";
                }else if(opcionTI==2){
                    varTipoIdentificacion="Personal de acondicioamiento fisico";
                }else{
                    bandera=true;
                }



        

            System.out.println("Ingrese el usuario ");
            String varUsuario = UtilidadesConsola.leerCadena();

            if (varUsuario.length()<8){
                bandera=true;
            }


            System.out.println("Ingrese la contraseña ");
            String varClave = UtilidadesConsola.leerCadena();

            if (varClave.length()<8){
                bandera=true;
            }
            if(!bandera){

                PersonalDTO objUsuario= new PersonalDTO(varTipoIdentificacion, varId, varNombres,varOcupacion,varUsuario,varClave);

                boolean valor = objRemoto.registrarPersonal(objUsuario);//invocación al método remoto
                if(valor)
                        System.out.println("Registro realizado satisfactoriamente...");
                else
                        System.out.println("no se pudo realizar el registro...");

            }else{
                System.out.println("datos erroneos");
            }
           
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    private static void Opcion2()
    {	
        int id = -1;
        try
        {
            System.out.println("========================");
            System.out.println("==Consulta de personal==");
            System.out.println("========================");

            System.out.println("Digite el id del personal a buscar");

            id = UtilidadesConsola.leerEntero();

            PersonalDTO personal  = objRemoto.consultarPersonal(id);
            System.out.println(personal.getTipo_id());
            System.out.println(personal.getId());
            System.out.println(personal.getUsuario());
            System.out.println(personal.getNombreCompleto());
            System.out.println(personal.getOcupacion());
        }
        catch(RemoteException e)
        {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }	
    }

}