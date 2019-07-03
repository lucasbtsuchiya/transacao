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
public interface Materiais extends Remote{
    public int consultarMateriais(String material)throws RemoteException;
    public String reservarMateriais(int id, String material) throws RemoteException;
}
