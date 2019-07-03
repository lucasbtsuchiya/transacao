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

/**
 *
 * @author lucastsuchiya
 */
public class ServerMateriais extends UnicastRemoteObject implements Materiais{
    
    protected ServerMateriais() throws RemoteException {
	super();
    }
    
    public static void main(String[] args) throws RemoteException {
        try {
            ServerMateriais servidormateriais = new ServerMateriais();
            String localizacao = "//localhost/materiais";
            Naming.rebind(localizacao, servidormateriais);
        } catch (MalformedURLException e) {
            System.out.println("Erro de URL mal formada: "+e.getMessage());
        } catch (RemoteException e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
    @Override
    public String getDataHora() throws RemoteException {
        // TODO Auto-generated method stub
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy MM:mm");
	return "Server Materiais";
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
