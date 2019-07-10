/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lucas B Tsuchiya e Pedro Bazia Neto
 */
public class ServerSalas extends UnicastRemoteObject implements Salas {
    
    
    public ArrayList lista_salas = new ArrayList();
    public ArrayList reservar_sala = new ArrayList();
    public LocalTime time = LocalTime.now();
    
 
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
            servidorsalas.time = LocalTime.now();
            //Criar arquivo 
            File arquivo = new File("C:\\LogServerSalas.txt");
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw); 
            bw.write(servidorsalas.time+" Servidor Executando e Salas Adcionadas \r\n");
            bw.close();
            /*
            FileWriter arq = new FileWriter("C:\\LogServerSalas.txt");
            PrintWriter gravarAq = new PrintWriter(arq);
            gravarAq.printf(servidorsalas.time+" Servidor Executando e Salas Adcionadas");
            System.out.println(servidorsalas.time+ " Servidor Executando e Salas Adcionadas");
            arq.close();
                    */
            
        } catch (MalformedURLException e) {
		System.out.println("Erro de URL mal formada: "+e.getMessage());
	} catch (RemoteException e) {
			System.out.println("Erro: "+e.getMessage());
	} catch (IOException ex) {
            Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public int TesteSala()throws RemoteException{
        //Variável de controle
        int teste = 1;
        
        if (teste == 1){
            try {
                //Criar arquivo
                File arquivo = new File("C:\\LogServerSalas.txt");
                //Se o arquivo não existir, ele gera
                if(!arquivo.exists()){
                    arquivo.createNewFile();
                }
                FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(),true);
                BufferedWriter bw = new BufferedWriter(fw);
                time = LocalTime.now();
                bw.write(time+" Servidor disponível");
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
           //Registro de Log
           System.out.println("Servidor indisponível");
            return 0;  
    }
    // Consulta a disponibilidade da sala
    @Override
    public int consultarSala(String sala)throws RemoteException{
        try {
            File arquivo = new File("C:\\LogServerSalas.txt");
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            time = LocalTime.now();
            
            
            if(lista_salas.contains(sala)){
                try {
                    //Registro de Log
                    bw.write(time+" Servidor disponível");
                    bw.close();
                    System.out.println("Sala disponível");
                    return 1;
                } catch (IOException ex) {
                    Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else
                //Registro de Log
                bw.write(time+" Servidor disponível");
                bw.close();
                System.out.println("Sala não disponível");
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    
    @Override
    public synchronized String reservarSala(int id, String sala) throws RemoteException {
        // TODO Auto-generated method stub
        ReservaSala r = new ReservaSala();
        if(lista_salas.contains(sala)){
            r.setId(1);
            r.setSala(sala);
            reservar_sala.add(r);
            lista_salas.remove(sala);
            System.out.println("Sala Reservada");
            return "Sala Reservada";
            
        }else
            return "Sala não disponivel";
        
    }
    
}