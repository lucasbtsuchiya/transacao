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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas B Tsuchiya e Pedro Bazia Neto
 */
public class Coordenador extends javax.swing.JFrame {
    Salas salas = null;
    Materiais materiais = null;
    /**
     * Creates new form Coordenador
     */
    public Coordenador() {
        
        initComponents();
        try {
            //Configurações para conectar com o servidor
            String localizacao_salas = "//localhost/salas";
            salas = (Salas) Naming.lookup(localizacao_salas);
            String localizacao_materiais = "//localhost/materiais";
            materiais = (Materiais) Naming.lookup(localizacao_materiais); //Criar arquivo
            LocalTime time = LocalTime.now();
            File arquivo = new File("C:\\LogCoordenador.txt");
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(time+" Coordenador Executando\r\n");
            bw.close();
            
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Coordenador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_reserva = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_sala = new javax.swing.JTextField();
        txt_material = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_reserva.setText("Reservar");
        btn_reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reservaActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("Sala");

        txt_sala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salaActionPerformed(evt);
            }
        });

        jLabel3.setText("Material");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sala, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_material, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btn_reserva)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btn_reserva)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_reservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reservaActionPerformed
        try {                                            
            int id = Integer.parseInt(txt_id.getText());
            String sala = txt_sala.getText();
            String material = txt_material.getText();
            LocalTime time = LocalTime.now();
            File arquivo = new File("C:\\LogCoordenador.txt");
            //Se o arquivo não existir, ele gera
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(time+" Reserva\r\n");
            //bw.close();
            
            try {
                //Testando comunicação com os servidores
                bw.write(time+" Testando comunicação com os servidores \r\n");
                //bw.close();
                System.out.println("Testando comunicação com os servidores");
                if(salas.TesteSala() == 1 && materiais.TesteMateriais() == 1){
                    bw.write(time+" Servidores disponível \r\n");
                    //bw.close();
                    System.out.println("Servidores disponível");
                    
                    //Garantindo a Atomicidade
                    //Testando a disponibilidade das salas e dos materiais
                    if(salas.consultarSala(sala) == 1 && materiais.consultarMateriais(material) == 1){
                        //Registro de Log
                        bw.write(time+" Sala e Material Disponível \r\n");
                        //bw.close();
                        System.out.println("Sala e Material Disponível");
                        //Reservando Material
                        salas.reservarSala(id, sala);
                        materiais.reservarMateriais(id, material);
                        JOptionPane.showMessageDialog(null, "Sala e Material Reservados");
                        System.out.println("Sala e Material Reservado");
                        bw.write(time+" Sala e Material Reservado \r\n");
                        bw.close();
                    }else{
                        //Registro de Log
                        bw.write(time+" Sala ou Material não disponível  \r\n");
                        bw.close();
                       // bw.write(time+" Status Transação: Abortada  \r\n");
                       // bw.close();
                        System.out.println("Sala ou Material não disponível");
                        System.out.println("Status Transação: Abortada");
                        JOptionPane.showMessageDialog(null, "Sala ou Material Não Disponivel!!");
                    }
                }else{
                   bw.write(time+" Servidor não disponível  \r\n");
                   bw.close();
                   // bw.write(time+" Status Transação: Abortada \r\n");
                   // bw.close();
                    System.out.println("Servidor não disponível");
                    System.out.println("Status Transação: Abortada");
                    JOptionPane.showMessageDialog(null, "Servidor Não Disponivel!!");
                }
                
                
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Coordenador.class.getName()).log(Level.SEVERE,null, ex);
        }
    }//GEN-LAST:event_btn_reservaActionPerformed

    private void txt_salaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_salaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_salaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Coordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Coordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Coordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Coordenador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Coordenador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_material;
    private javax.swing.JTextField txt_sala;
    // End of variables declaration//GEN-END:variables
}