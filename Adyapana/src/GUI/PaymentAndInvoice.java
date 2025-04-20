package GUI;

import MODEL.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class PaymentAndInvoice extends javax.swing.JFrame {

    public static HashMap<String, Integer> subjectMap = new HashMap();
    public static HashMap<String, Integer> teacherMap = new HashMap();

    public PaymentAndInvoice() {
        initComponents();
        loadSubject();
        loadTeacher();
        loadPaymentDetails();
    }

    public void loadTeacher() {
        try {
            ResultSet resultSet = MySQL.execute("SELECT * FROM `teacher`");
            Vector v2 = new Vector();
            v2.add("Select");
            while (resultSet.next()) {
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String space = (" ");
                String name = fname + space + lname;
                v2.add(name);
                teacherMap.put(name, resultSet.getInt("Tno"));
            }
            DefaultComboBoxModel model2 = new DefaultComboBoxModel(v2);
            jComboBox2.setModel(model2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSubject() {
        try {
            ResultSet resultSet = MySQL.execute("SELECT * FROM `subject`");
            Vector v = new Vector();
            v.add("Select");

            while (resultSet.next()) {
                v.add(resultSet.getString("discription"));
                subjectMap.put(resultSet.getString("discription"), resultSet.getInt("SubNo"));
            }
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(v);
            jComboBox1.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        jTextField2.setText("");
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField4.setText("Student number ..");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.requestFocus();
        jTable1.setEnabled(true);
        jButton3.setEnabled(true);
        jTextField1.setEnabled(true);
    }

    private void loadPaymentDetails() {
        try {
            ResultSet resultSet = MySQL.execute("SELECT * FROM `invoice` "
                    + "INNER JOIN `subject` ON `invoice`.`Subject_SubNo`=`subject`.`SubNo` "
                    + "INNER JOIN `student` ON `invoice`.`Student_Sno`=`student`.`Sno` "
                    + "INNER JOIN `teacher` ON `invoice`.`Teacher_Tno`= `teacher`.`Tno` ORDER BY `InvoiceNo` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> v = new Vector();
                String fname = resultSet.getString("teacher.first_name");
                String lname = resultSet.getString("teacher.last_name");
                String s = (" ");
                String name = fname + s + lname;
                v.add(resultSet.getString("InvoiceNo"));
                v.add(resultSet.getString("Student_Sno"));
                v.add(resultSet.getString("student.nic"));
                v.add(name);
                v.add(resultSet.getString("subject.discription"));
                v.add(resultSet.getString("month"));
                v.add(resultSet.getString("value"));
                v.add(resultSet.getString("date"));

                model.addRow(v);
                jTable1.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        jLabel1.setText("Payment & Invoice details");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/previous.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No", "SNo", "Student NIC", "Teacher Name", "Subject ", "Month", "Value", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Student Number");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Subject");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Teacher");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Month");

        jLabel6.setText("Value");

        jButton3.setBackground(new java.awt.Color(153, 153, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Payment");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 255, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 153, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField4.setText("Student number ..");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Print Invoice");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 204));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(129, 129, 129)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(57, 57, 57)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(474, 474, 474)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)))
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String subject = String.valueOf(jComboBox1.getSelectedItem());
        String teacher = String.valueOf(jComboBox2.getSelectedItem());
        String SNumber = jTextField1.getText();
        String month = jTextField2.getText();
        String value = jTextField3.getText();

        if (subject.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Invalid Subject", "Warning", JOptionPane.ERROR_MESSAGE);

        } else if (teacher.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Invalid Teacher", "Warning", JOptionPane.ERROR_MESSAGE);

        } else if (SNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Student Number", "Warning", JOptionPane.ERROR_MESSAGE);

        } else if (month.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Month", "Warning", JOptionPane.ERROR_MESSAGE);

        } else if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Value", "Warning", JOptionPane.ERROR_MESSAGE);

        } else {
            int subjectId = subjectMap.get(subject);
            int teacherId = teacherMap.get(teacher);
            try {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
                String stringDate = format.format(date);

                ResultSet resultset = MySQL.execute("INSERT INTO "
                        + "`invoice`(`Student_Sno`,`Teacher_Tno`,`Subject_SubNo`,`month`,`value`,`date`)"
                        + "VALUES('" + SNumber + "','" + teacherId + "','" + subjectId + "','" + month + "','" + value + "','" + stringDate + "')");
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadPaymentDetails();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select A Row", "Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            String InvoiceNo = String.valueOf(jTable1.getValueAt(selectedRow, 0));
            try {
                MySQL.execute("DELETE  FROM `invoice` WHERE `InvoiceNo`='" + InvoiceNo + "'");
                loadPaymentDetails();
                reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        reset();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            jTable1.setEnabled(false);
            jButton3.setEnabled(false);
            jTextField1.setEnabled(false);
            int selectedRow = jTable1.getSelectedRow();
            String sno = String.valueOf(jTable1.getValueAt(selectedRow, 1));
            jTextField1.setText(sno);
            String nic = String.valueOf(jTable1.getValueAt(selectedRow, 2));
            jTextField4.setText(nic);
            String teacher = String.valueOf(jTable1.getValueAt(selectedRow, 3));
            jComboBox2.setSelectedItem(teacher);
            String subject = String.valueOf(jTable1.getValueAt(selectedRow, 4));
            jComboBox1.setSelectedItem(subject);
            String month = String.valueOf(jTable1.getValueAt(selectedRow, 5));
            jTextField2.setText(month);
            String value = String.valueOf(jTable1.getValueAt(selectedRow, 6));
            jTextField3.setText(value);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select A Row", "Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            String InvoiceNo = String.valueOf(jTable1.getValueAt(selectedRow, 0));
            String sno = jTextField1.getText();
            String month = jTextField2.getText();
            String value = jTextField3.getText();
            String subject = String.valueOf(jComboBox1.getSelectedItem());
            String teacher = String.valueOf(jComboBox2.getSelectedItem());
            if (sno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid NIC", "Warning", JOptionPane.ERROR_MESSAGE);

            } else if (month.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Month", "Warning", JOptionPane.ERROR_MESSAGE);

            } else if (value.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid Value", "Warning", JOptionPane.ERROR_MESSAGE);

            } else if (subject.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Invalid Subject", "Warning", JOptionPane.ERROR_MESSAGE);

            } else if (teacher.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Invalid Teacher", "Warning", JOptionPane.ERROR_MESSAGE);

            } else {
                int subjectId = subjectMap.get(subject);
                int teacherId = teacherMap.get(teacher);
                try {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
                    String stringDate = format.format(date);

                    MySQL.execute("UPDATE `invoice` SET"
                            + "`InvoiceNo`='" + InvoiceNo + "',"
                            + "`Student_Sno`='" + sno + "',"
                            + "`Teacher_Tno`='" + teacherId + "',"
                            + "`Subject_SubNo`='" + subjectId + "',"
                            + "`month`='" + month + "',"
                            + "`value`='" + value + "',"
                            + "`date`='" + stringDate + "'"
                            + "WHERE `InvoiceNo`='" + InvoiceNo + "'");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadPaymentDetails();
                reset();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sno = jTextField4.getText();
        if (sno.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Number", "Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ResultSet resultSet = MySQL.execute("SELECT * FROM `invoice` "
                        + "INNER JOIN `subject` ON `invoice`.`Subject_SubNo`=`subject`.`SubNo` "
                        + "INNER JOIN `student` ON `invoice`.`Student_Sno`=`student`.`Sno` "
                        + "INNER JOIN `teacher` ON `invoice`.`Teacher_Tno`= `teacher`.`Tno` "
                        + "WHERE `Student_Sno`= '" + sno + "'");

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                while (resultSet.next()) {
                    Vector<String> v = new Vector();
                    String fname = resultSet.getString("teacher.first_name");
                    String lname = resultSet.getString("teacher.last_name");
                    String s = (" ");
                    String name = fname + s + lname;
                    v.add(resultSet.getString("InvoiceNo"));
                    v.add(resultSet.getString("Student_Sno"));
                    v.add(resultSet.getString("student.nic"));
                    v.add(name);
                    v.add(resultSet.getString("subject.discription"));
                    v.add(resultSet.getString("month"));
                    v.add(resultSet.getString("value"));
                    model.addRow(v);
                    jTable1.setModel(model);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select A Row", "Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
            String stringDate = format.format(date);
            
            String subject = String.valueOf(jComboBox1.getSelectedItem());
            String teacher = String.valueOf(jComboBox2.getSelectedItem());
            String nic = jTextField4.getText();
            String month = jTextField2.getText();
            String value = jTextField3.getText();
            
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", stringDate);
            parameters.put("Parameter2", nic);
            parameters.put("Parameter3", teacher);
            parameters.put("Parameter4", subject);
            parameters.put("Parameter5", month);
            parameters.put("Parameter6", value);

            try {
                JRTableModelDataSource datasource = new JRTableModelDataSource(jTable1.getModel());
                JasperPrint report = (JasperFillManager.fillReport("src/Reports/Adyapana_Invoice.jasper", parameters, datasource));
                JasperViewer.viewReport(report, false);
                JasperExportManager.exportReportToPdfFile(report, "invoice.pdf");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentAndInvoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
