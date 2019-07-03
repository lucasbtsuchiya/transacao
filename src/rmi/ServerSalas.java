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
import java.util.ArrayList;


/**
 *
 * @author Lucas B Tsuchiya e Pedro Bazia Neto
 */
public class ServerSalas extends UnicastRemoteObject implements Salas {
    
    
    public ArrayList lista_salas = new ArrayList();
    public ArrayList reservar_sala = new ArrayList();
    
 
    public ServerSalas() throws RemoteException {
	super();
	// TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) throws RemoteException {
        try {
            ServerSalas servidorsalas = new ServerSalas();
            String localizacao = "//localhost/salas";
            Naming.rebind(localizacao, servidorsalas);
            servidorsalas.lista_salas.add("Sala1");
            servidorsalas.lista_salas.add("Sala2");
            
        } catch (MalformedURLException e) {
		System.out.println("Erro de URL mal formada: "+e.getMessage());
	} catch (RemoteException e) {
			System.out.println("Erro: "+e.getMessage());
	}
    }
    
    @Override
    public int consultarSala(String sala)throws RemoteException{
       if(lista_salas.contains(sala)){
            return 1;
        }else
            return 0;  
    }
    
    @Override
    public String reservarSala(int id, String sala) throws RemoteException {
        // TODO Auto-generated method stub
        ReservaSala r = new ReservaSala();
        if(lista_salas.contains(sala)){
            r.setId(1);
            r.setSala(sala);
            reservar_sala.add(r);
            lista_salas.remove(sala);
            return "Sala Reservada";
        }else
            return "Sala não disponivel";
        
    }

    @Override
    public String inverteString(String string) throws RemoteException {
        // TODO Auto-generated method stub
	String retorno = "";
	StringBuffer strb = new StringBuffer(string);
	retorno = strb.reverse().toString();
	return retorno;
    }
    
}