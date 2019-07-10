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
    
    //Listas de salas e lista de reservas
    public ArrayList lista_salas = new ArrayList();
    public ArrayList reservar_sala = new ArrayList();
    public LocalTime time = LocalTime.now();
    
    //Contrutor 
    public ServerSalas() throws RemoteException {
	super();
	// TODO Auto-generated constructor stub
    }
    //Classe principal
    public static void main(String[] args) throws RemoteException {
        try {
            //Configurações para executar o servidor
            ServerSalas servidorsalas = new ServerSalas();
            String localizacao = "//localhost/salas";
            Naming.rebind(localizacao, servidorsalas);
            //Adicionando salas na lista
            servidorsalas.lista_salas.add("Sala1");
            servidorsalas.lista_salas.add("Sala2");
            //Pegando hora
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
            
        } catch (MalformedURLException e) {
		System.out.println("Erro de URL mal formada: "+e.getMessage());
	} catch (RemoteException e) {
			System.out.println("Erro: "+e.getMessage());
	} catch (IOException ex) {
            Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Método para testar a disponibilidade do servidor
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
                bw.write(time+" Servidor Salas disponível \r\n");
                bw.close();
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
           //Registro de Log
            System.out.println("Servidor Salas indisponível \r\n");
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
            
            //Verificando se a sala esta disponível
            if(lista_salas.contains(sala)){
                try {
                    //Registro de Log
                    bw.write(time+" Sala Disponível \r\n");
                    bw.close();
                    System.out.println("Sala Não Disponível \r\n");
                    return 1;
                } catch (IOException ex) {
                    Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else
                //Registro de Log
                bw.write(time+" Sala não disponível \r\n");
                bw.close();
                System.out.println("Sala não disponível \r\n");
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //Método para efutar a reserva da sala
    @Override
    public synchronized String reservarSala(int id, String sala) throws RemoteException {
        FileWriter fw = null;
        try {
            // TODO Auto-generated method stub
            File arquivo = new File("C:\\LogServerSalas.txt");
            //Se o arquivo não existir, ele gera
            fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            time = LocalTime.now();
            
            ReservaSala r = new ReservaSala();
            //Verificando sala
            if(lista_salas.contains(sala)){
                //Fazendo reserva 
                r.setId(1);
                r.setSala(sala);
                reservar_sala.add(r);
                lista_salas.remove(sala);
                bw.write(time+" Sala Reservada! \r\n");
                bw.close();
                System.out.println("Sala Reservada");
                return "Sala Reservada";
                
            }else
                bw.write(time+"Sala Não disponivel! Reserva Cancelada \r\n");
                bw.close();
                return "Sala não disponivel";
        } catch (IOException ex) {
            Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        
    }
    
    
}