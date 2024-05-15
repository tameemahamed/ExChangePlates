
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author tameem
 */
public class RPosts extends javax.swing.JFrame {

    /**
     * Creates new form RPosts
     */
    public RPosts() {
        initComponents();
        Connect();
        Filter();
        ChooseDivision();
        
    }
    private void ChooseDivision(){
        Vector v = new Vector();
        String query = "Select name from divisions ORDER BY name ASC";
        try {
            Statement s = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            v.add("--Please Select");
            while(rs.next()){
                String l= rs.getString("name");
            //    System.out.println(l);
                v.add(l);
            }
            jComboBox1.setModel(new DefaultComboBoxModel(v));
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ChooseDistrict(){
        Vector v = new Vector();
        v.add("--Please Select");
        if((String)jComboBox1.getSelectedItem()=="--Please Select"){
            jComboBox2.setModel(new DefaultComboBoxModel(v));
            return;
        }
        String query = "Select name from districts where division_id = ?";
        try {
            Statement s = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,divid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String l= rs.getString("name");
            //    System.out.println(l);
                v.add(l);
            }
            jComboBox2.setModel(new DefaultComboBoxModel(v));
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ChooseThanaorUpazia(){
        Vector v = new Vector();
        v.add("--Please Select");
        if((String)jComboBox2.getSelectedItem()=="--Please Select"){
            jComboBox3.setModel(new DefaultComboBoxModel(v));
            return;
        }
        String query = "Select name from Thanas where dis_id = ? ORDER BY name ASC";
        try {
            Statement s = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,disid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String l= rs.getString("name");
            //    System.out.println(l);
                v.add(l);
            }
            query = "Select name from upazilas where district_id = ? ORDER BY name ASC";
            ps = con.prepareStatement(query);
            ps.setString(1,disid);
            rs = ps.executeQuery();
            while(rs.next()){
                String l= rs.getString("name");
            //    System.out.println(l);
                v.add(l);
            }
            
            jComboBox3.setModel(new DefaultComboBoxModel(v));
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    String disid,divid;
    int idd;
    Connection con;
    public void Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ExchangePlates"; // Assuming MySQL server is running on localhost and default port 3306
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    Map<String, Integer> hm 
            = new HashMap<String, Integer>();
    public void Filter(){
        String query = "select post_id from Posts ";
        if((String)jComboBox1.getSelectedItem()!="--Please Select"){
            query = query + "where division = '" + (String)jComboBox1.getSelectedItem() + "' ";
            if((String)jComboBox2.getSelectedItem()!="--Please Select"){
                query = query + " and district = '"+ (String)jComboBox2.getSelectedItem() + "' ";
                if((String)jComboBox3.getSelectedItem()!="--Please Select"){
                    query = query + " and thana_upazilla = '" + (String)jComboBox3.getSelectedItem() + "' ";
                }
            }
        }
        query = query + " ORDER BY post_id ASC";
        hm.clear();
        Vector v = new Vector();
        try {
            Statement s = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            v.add("--Please Select");
            int i=0;
            while(rs.next()){
                i++;
                String l= "Post "+ String.valueOf(i);
            //    System.out.println(l);
                v.add(l);
                String ss = rs.getString("post_id");
                p.add(ss);
                hm.put(l, Integer.parseInt(ss));
            }
            jComboBox4.setModel(new DefaultComboBoxModel(v));
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewPost.class.getName()).log(Level.SEVERE, null, ex);
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

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        rp1 = new Rp();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Go Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Please Select" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Please Select" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Please Select" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Division");

        jLabel2.setText("District");

        jLabel3.setText("Thana/Upazilla");

        jLabel4.setText("num of posts");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(rp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        RecHome RecHome = new RecHome();
        RecHome.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        divid=DivID();
        System.out.println("Div_id = " +divid);
        Vector v = new Vector();
        v.add("--Please Select");
        jComboBox3.setModel(new DefaultComboBoxModel(v));
        ChooseDistrict();
        Filter();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    private String DivID(){
        String query = "Select id from divisions  where name = ?";
        try {
            Statement s = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String)jComboBox1.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getString("id");
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        disid=DisID();
        System.out.println("Dis_id = "+disid);
        ChooseThanaorUpazia();
        Filter();
    }//GEN-LAST:event_jComboBox2ActionPerformed
    private String DisID(){
        String query = "Select id from districts  where name = ?";
        try {
            Statement s = con.createStatement();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String)jComboBox2.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getString("id");
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        Filter();
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        WriteId();
        Writepid();
        dispose();
        new RPosts().setVisible(true);
    }//GEN-LAST:event_jComboBox4ActionPerformed
    public void WriteId(){
        String w = (String)jComboBox4.getSelectedItem();
        try {
            FileWriter fw = new FileWriter("/home/tameem/NetBeansProjects/ExChangePlates/PostId.txt");
            fw.write(w);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(DonLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Vector p = new Vector();
    
    public void Writepid(){
        String ww = (String)jComboBox4.getSelectedItem();
        int i = hm.get(ww);
        String w = String.valueOf(i);
     //   System.out.println(w);
        try {
            FileWriter fw = new FileWriter("/home/tameem/NetBeansProjects/ExChangePlates/pid.txt");
            fw.write(w);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(DonLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
            java.util.logging.Logger.getLogger(RPosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPosts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPosts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private Rp rp1;
    // End of variables declaration//GEN-END:variables
}
