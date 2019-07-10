/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas B Tsuchiya e Pedro Bazia Neto
 */
public interface Salas extends Remote{
    
    //public String getDataHora() throws RemoteException;
    public int TesteSala()throws RemoteException;
    public int consultarSala(String sala)throws RemoteException;
    public String reservarSala(int id, String sala) throws RemoteException;
    
}
