/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Lucas B Tsuchiya e Pedro Bazia Neto
 */
public class ServerMateriais extends UnicastRemoteObject implements Materiais{
    
    public ArrayList lista_materiais = new ArrayList();
    public ArrayList reservar_materiais = new ArrayList(); 
    
    protected ServerMateriais() throws RemoteException {
	super();
    }
    
    public static void main(String[] args) throws RemoteException {
        try {
            ServerMateriais servidormateriais = new ServerMateriais();
            String localizacao = "//localhost/materiais";
            servidormateriais.lista_materiais.add("Projetor");
            servidormateriais.lista_materiais.add("Notebook");
            Naming.rebind(localizacao, servidormateriais);
        } catch (MalformedURLException e) {
            System.out.println("Erro de URL mal formada: "+e.getMessage());
        } catch (RemoteException e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
    @Override
    public int consultarMateriais(String material)throws RemoteException{
       if(lista_materiais.contains(material)){
            return 1;
        }else
            return 0;  
    }
    
    @Override
    public String reservarMateriais(int id, String material) throws RemoteException {
        // TODO Auto-generated method stub
        ReservaMaterial r = new ReservaMaterial();
        if(lista_materiais.contains(material)){
            r.setId(1);
            r.setMaterial(material);
            reservar_materiais.add(r);
            lista_materiais.remove(material);
            return "Sala Reservada";
        }else
            return "Sala não disponivel";
        
    }
}
