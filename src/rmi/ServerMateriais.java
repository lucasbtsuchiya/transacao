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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas B Tsuchiya e Pedro Bazia Neto
 */
public class ServerMateriais extends UnicastRemoteObject implements Materiais{
    //Lista de materiais e reserva de materiais 
    public ArrayList lista_materiais = new ArrayList();
    public ArrayList reservar_materiais = new ArrayList(); 
    public LocalTime time = LocalTime.now();
    
    //Contrutor
    protected ServerMateriais() throws RemoteException {
	super();
    }
    //Classe principal 
    public static void main(String[] args) throws RemoteException, IOException {
        try {
            //Configurações para executar o servidor de materiais
            ServerMateriais servidormateriais = new ServerMateriais();
            String localizacao = "//localhost/materiais";
            servidormateriais.lista_materiais.add("Projetor");
            servidormateriais.lista_materiais.add("Notebook");
            //Criar arquivo
            File arquivo = new File("C:\\LogServerMateriais.txt");
            Naming.rebind(localizacao, servidormateriais);
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            servidormateriais.time = LocalTime.now();
            bw.write(servidormateriais.time+" Servidor Materiais Executando\r\n");
            bw.close();
            
            
        } catch (MalformedURLException e) {
            System.out.println("Erro de URL mal formada: "+e.getMessage());
        } catch (RemoteException e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
    // Método para testar o disponibilidade do servidor
     @Override
    public int TesteMateriais()throws RemoteException{
        FileWriter fw = null;
        try {
            //Variável de controle
            //Criar arquivo
            File arquivo = new File("C:\\LogServerMateriais.txt");
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }   fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            time = LocalTime.now();
            int teste = 1;
            if (teste == 1){
                //Registro de Log
                bw.write(time+" Servidor Materiais disponível \r\n");
                bw.close();
                System.out.println("Servidor disponível");
                return 1;
            }else
                //Registro de Log
                bw.write(time+" Servidor Materiais não disponível \r\n");
                bw.close();
                System.out.println("Servidor materiais indisponível");
                return 0;
        } catch (IOException ex) {
            Logger.getLogger(ServerMateriais.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerMateriais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    // Consulta a disponibilidade de materiais
    @Override
    public synchronized int consultarMateriais(String material)throws RemoteException{
        try {
            //Criar arquivo
            File arquivo = new File("C:\\LogServerMateriais.txt");
            FileWriter fw = null;
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();   
            }
            fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            time = LocalTime.now();
            
            if(lista_materiais.contains(material)){
                //Registro de Log
                bw.write(time+" Materiais disponível \r\n");
                bw.close();
                System.out.println("Material disponível");
                return 1;
            }else
                //Registro de Log
                bw.write(time+" Materiais não disponível \r\n");
                bw.close();
                System.out.println("Material não disponível");
            return 0;  
        } catch (IOException ex) {
            Logger.getLogger(ServerMateriais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //Método para efutar a reserva dos materiais
    @Override
    public synchronized String reservarMateriais(int id, String material) throws RemoteException {
        try {
            // TODO Auto-generated method stub
            ReservaMaterial r = new ReservaMaterial();
            
            //Criar arquivo
            File arquivo = new File("C:\\LogServerMateriais.txt");
            FileWriter fw = null;
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();   
            }
            fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            time = LocalTime.now();
            //Verifica se o material esta disponível 
            if(lista_materiais.contains(material)){
                //Instancia o objeto e coloca na lista 
                r.setId(1);
                r.setMaterial(material);
                //Efetua a reserva do material
                reservar_materiais.add(r);
                //Remove o material da lista de disponibilidade
                lista_materiais.remove(material);
                bw.write(time+" Materiais Reservado \r\n");
                bw.close();
                return "Sala Reservada";
            }else
                bw.write(time+" Materiais não Não reservado \r\n");
                bw.close();
                return "Sala não disponivel";
        } catch (IOException ex) {
            Logger.getLogger(ServerMateriais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
