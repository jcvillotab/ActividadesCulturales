/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontera;

import Control.ArtistatController;
import Control.ClientetController;
import Control.EventotController;
import Control.LugartController;
import Entidad.Eventot;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manue
 */
public class ClientApp extends javax.swing.JPanel {

    /**
     * Creates new form ClientApp
     */
    public HomeApp ha;
    private final EventotController eventoCtrl = new EventotController();
    private final ArtistatController artistaCtrl = new ArtistatController();
    private final ClientetController clienteCtrl = new ClientetController();
    private final LugartController lugarCtrl = new LugartController();
    
    public ClientApp() {
        initComponents();
        actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bg = new ImagePanel("C:\\Users\\Joan\\Documents\\NetBeansProjects\\ActividadesCulturales\\resources\\fondo2.jpg");
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        reservarP = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        clientIdTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        reserveEventB = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        goBackREventB = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        eventsRTable = new javax.swing.JTable();
        addClientEventB = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        consultarP = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        idSearchTF = new javax.swing.JTextField();
        goBackCEventB = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        deleteEventB = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        eventsCTable = new javax.swing.JTable();
        searchEventB = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();

        Bg.setMinimumSize(new java.awt.Dimension(980, 689));
        Bg.setPreferredSize(new java.awt.Dimension(1360, 720));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(741, 402));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(741, 402));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel3.setText("Id del cliente");

        clientIdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientIdTFActionPerformed(evt);
            }
        });

        reserveEventB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        reserveEventB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reserveEventBMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Reservar");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout reserveEventBLayout = new javax.swing.GroupLayout(reserveEventB);
        reserveEventB.setLayout(reserveEventBLayout);
        reserveEventBLayout.setHorizontalGroup(
            reserveEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reserveEventBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );
        reserveEventBLayout.setVerticalGroup(
            reserveEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reserveEventBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        goBackREventB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Volver");

        javax.swing.GroupLayout goBackREventBLayout = new javax.swing.GroupLayout(goBackREventB);
        goBackREventB.setLayout(goBackREventBLayout);
        goBackREventBLayout.setHorizontalGroup(
            goBackREventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, goBackREventBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );
        goBackREventBLayout.setVerticalGroup(
            goBackREventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goBackREventBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eventsRTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Evento", "Lugar", "Fecha", "Hora", "Artista Principal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventsRTable.setCellSelectionEnabled(true);
        eventsRTable.setDefaultEditor(Object.class, null);
        eventsRTable.setColumnSelectionAllowed(false);
        jScrollPane3.setViewportView(eventsRTable);
        if (eventsRTable.getColumnModel().getColumnCount() > 0) {
            eventsRTable.getColumnModel().getColumn(0).setResizable(false);
            eventsRTable.getColumnModel().getColumn(1).setResizable(false);
            eventsRTable.getColumnModel().getColumn(2).setResizable(false);
            eventsRTable.getColumnModel().getColumn(3).setResizable(false);
            eventsRTable.getColumnModel().getColumn(4).setResizable(false);
        }

        addClientEventB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Agregar cliente");

        javax.swing.GroupLayout addClientEventBLayout = new javax.swing.GroupLayout(addClientEventB);
        addClientEventB.setLayout(addClientEventBLayout);
        addClientEventBLayout.setHorizontalGroup(
            addClientEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addClientEventBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );
        addClientEventBLayout.setVerticalGroup(
            addClientEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addClientEventBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reservarPLayout = new javax.swing.GroupLayout(reservarP);
        reservarP.setLayout(reservarPLayout);
        reservarPLayout.setHorizontalGroup(
            reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservarPLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(62, 62, 62)
                .addGroup(reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clientIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addClientEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservarPLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservarPLayout.createSequentialGroup()
                        .addComponent(goBackREventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(reserveEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservarPLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        reservarPLayout.setVerticalGroup(
            reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservarPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clientIdTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addClientEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(reservarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reserveEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goBackREventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130))
        );

        jTabbedPane1.addTab("Reservas", reservarP);

        jLabel4.setText("Id del cliente");

        idSearchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idSearchTFActionPerformed(evt);
            }
        });

        goBackCEventB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Volver");

        javax.swing.GroupLayout goBackCEventBLayout = new javax.swing.GroupLayout(goBackCEventB);
        goBackCEventB.setLayout(goBackCEventBLayout);
        goBackCEventBLayout.setHorizontalGroup(
            goBackCEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, goBackCEventBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );
        goBackCEventBLayout.setVerticalGroup(
            goBackCEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goBackCEventBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        deleteEventB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        deleteEventB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteEventBMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Eliminar Reserva");

        javax.swing.GroupLayout deleteEventBLayout = new javax.swing.GroupLayout(deleteEventB);
        deleteEventB.setLayout(deleteEventBLayout);
        deleteEventBLayout.setHorizontalGroup(
            deleteEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteEventBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );
        deleteEventBLayout.setVerticalGroup(
            deleteEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteEventBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eventsCTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Evento", "Lugar", "Fecha", "Hora", "Artista Principal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventsCTable.setCellSelectionEnabled(true);
        eventsCTable.setDefaultEditor(Object.class, null);
        eventsCTable.setColumnSelectionAllowed(false);
        jScrollPane1.setViewportView(eventsCTable);
        if (eventsCTable.getColumnModel().getColumnCount() > 0) {
            eventsCTable.getColumnModel().getColumn(0).setResizable(false);
            eventsCTable.getColumnModel().getColumn(1).setResizable(false);
            eventsCTable.getColumnModel().getColumn(2).setResizable(false);
            eventsCTable.getColumnModel().getColumn(3).setResizable(false);
            eventsCTable.getColumnModel().getColumn(4).setResizable(false);
        }

        searchEventB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchEventB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchEventBMouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Buscar");

        javax.swing.GroupLayout searchEventBLayout = new javax.swing.GroupLayout(searchEventB);
        searchEventB.setLayout(searchEventBLayout);
        searchEventBLayout.setHorizontalGroup(
            searchEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchEventBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );
        searchEventBLayout.setVerticalGroup(
            searchEventBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchEventBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout consultarPLayout = new javax.swing.GroupLayout(consultarP);
        consultarP.setLayout(consultarPLayout);
        consultarPLayout.setHorizontalGroup(
            consultarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultarPLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addGap(32, 32, 32)
                .addComponent(idSearchTF, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(searchEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultarPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(deleteEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goBackCEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultarPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        consultarPLayout.setVerticalGroup(
            consultarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultarPLayout.createSequentialGroup()
                .addGroup(consultarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultarPLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(consultarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idSearchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(consultarPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(consultarPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(goBackCEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteEventB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultas", consultarP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("cliente");

        javax.swing.GroupLayout BgLayout = new javax.swing.GroupLayout(Bg);
        Bg.setLayout(BgLayout);
        BgLayout.setHorizontalGroup(
            BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(327, 327, 327))
        );
        BgLayout.setVerticalGroup(
            BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BgLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clientIdTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientIdTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientIdTFActionPerformed

    private void idSearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idSearchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idSearchTFActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if(jTabbedPane1.getSelectedIndex() == 0){
            actualizarTabla();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        
    }//GEN-LAST:event_jLabel21MouseClicked

    private void reserveEventBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reserveEventBMouseClicked
        String res;
        DefaultTableModel model = (DefaultTableModel) eventsRTable.getModel();
        int index = eventsRTable.getSelectedRow();
        
        res = clienteCtrl.reservar(Integer.parseInt(clientIdTF.getText()), Integer.parseInt(model.getValueAt(index, 0).toString()));
        JOptionPane.showMessageDialog(ha,res);
    }//GEN-LAST:event_reserveEventBMouseClicked

    private void searchEventBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchEventBMouseClicked
        ArrayList<Integer> eventoId = clienteCtrl.listar_id_evento_reservas(Integer.parseInt(idSearchTF.getText()));
        ArrayList<Eventot> eventos = new ArrayList<>();
        Eventot temp;
        for (Integer eventoId1 : eventoId) {
            temp = eventoCtrl.findEventot(eventoId1);
            eventos.add(temp);
        }
        actualizarTabla(eventos);
    }//GEN-LAST:event_searchEventBMouseClicked

    private void deleteEventBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteEventBMouseClicked
        String res;
        DefaultTableModel model = (DefaultTableModel) eventsCTable.getModel();
        int index = eventsCTable.getSelectedRow();
        
        res = clienteCtrl.eliminarReserva(Integer.parseInt(idSearchTF.getText()), Integer.parseInt(model.getValueAt(index, 0).toString()));
        JOptionPane.showMessageDialog(ha,res);
    }//GEN-LAST:event_deleteEventBMouseClicked
    
    private void actualizarTabla(){
        Eventot temp;
        int capacidad;
        ArrayList<Integer> artistIds;
        ArrayList<String> artistNames;
        String nameLugar;
        String[] tableNames = {"Id", "Nombre", "Lugar", "Artista Principal", "Capacidad", "Fecha/Hora"};
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<Eventot> eventList = eventoCtrl.listarEventos();
        String[][] datos = new String[eventList.size()][6];
        
        for (int i = 0; i < eventList.size(); i++) {
            temp = eventList.get(i);
            artistIds = eventoCtrl.buscar_ids_artistas(temp.getIdEvento());
            artistNames = artistaCtrl.buscarByIds(artistIds);
            capacidad = eventoCtrl.buscarCapacidad(temp.getIdEvento());
            nameLugar = lugarCtrl.findById(eventoCtrl.buscarIdLugar(temp.getIdEvento())).getNombreLugar();
            
            datos[i][0] = String.valueOf(temp.getIdEvento());
            datos[i][1] = temp.getNombreEvento();
            datos[i][2] = nameLugar;
            datos[i][3] = artistNames.get(0);
            datos[i][4] = String.valueOf(capacidad);
            datos[i][5] = format1.format(temp.getFechaEvento());
        }

        eventsRTable.setModel(new DefaultTableModel(datos, tableNames));

    }
    
    private void actualizarTabla(ArrayList<Eventot> eventList){
        Eventot temp;
        int capacidad;
        ArrayList<Integer> artistIds;
        ArrayList<String> artistNames;
        String[] tableNames = {"Id", "Nombre", "Lugar", "Artista Principal", "Capacidad", "Fecha/Hora"};
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String[][] datos = new String[eventList.size()][6];
        String nameLugar;
        
        for (int i = 0; i < eventList.size(); i++) {
            temp = eventList.get(i);
            nameLugar = lugarCtrl.findById(eventoCtrl.buscarIdLugar(temp.getIdEvento())).getNombreLugar();
            artistIds = eventoCtrl.buscar_ids_artistas(temp.getIdEvento());
            artistNames = artistaCtrl.buscarByIds(artistIds);
            capacidad = eventoCtrl.buscarCapacidad(temp.getIdEvento());
            
            datos[i][0] = String.valueOf(temp.getIdEvento());
            datos[i][1] = temp.getNombreEvento();
            datos[i][2] = nameLugar;
            datos[i][3] = artistNames.get(0);
            datos[i][4] = String.valueOf(capacidad);
            datos[i][5] = format1.format(temp.getFechaEvento());
        }

        eventsCTable.setModel(new DefaultTableModel(datos, tableNames));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bg;
    private javax.swing.JPanel addClientEventB;
    private javax.swing.JTextField clientIdTF;
    private javax.swing.JPanel consultarP;
    private javax.swing.JPanel deleteEventB;
    private javax.swing.JTable eventsCTable;
    private javax.swing.JTable eventsRTable;
    private javax.swing.JPanel goBackCEventB;
    private javax.swing.JPanel goBackREventB;
    private javax.swing.JTextField idSearchTF;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel reservarP;
    private javax.swing.JPanel reserveEventB;
    private javax.swing.JPanel searchEventB;
    // End of variables declaration//GEN-END:variables
}
