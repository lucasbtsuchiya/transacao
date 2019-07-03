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
 * @author lucastsuchiya
 */
public interface Salas extends Remote{
    
    public String getDataHora() throws RemoteException;	
    public String inverteString(String string) throws RemoteException;
}
