/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tools.mapping;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JList;
/**
 *
 * @author Brandon
 */
public class MapMaker extends javax.swing.JFrame {
    static File file;
    static BufferedImage spritesheet;
    static BufferedImage selectedTile;
    static int selectedTileX;
    static int selectedTileY;
   // static LinkedList<Map> layers;
    static int maxLayer;
    static int mapRow;
    static int mapCol;
    //static Map map;
    private static int scalex=1;
    private static int scaley=1;
    private static int gridSize=64;
    /**
     * Creates new form MapMaker
     */
    public MapMaker() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        newBtn = new javax.swing.JButton();
        loadBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        mapPanel = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                repaint();
                if(file!=null||spritesheet!=null||layerList.getSelectedValue()!=null){
                    g.setColor(new Color(64,64,64));
                    g.fillRect(0,0,mapCol*gridSize,mapRow*gridSize);

                    for(int i=0;i<=maxLayer;i++)
                    for(int r=0;r<layerList.getSelectedValue().rowSize;r++)
                    for(int c=0;c<layerList.getSelectedValue().colSize;c++)
                    if(listModel.elementAt(i).get(r, c)!=0)
                    g.drawImage(spritesheet.getSubimage(gridSize*((listModel.elementAt(i).get(r,c)-1)%(spritesheet.getWidth()/gridSize)), gridSize*((listModel.elementAt(i).get(r,c)-1)/(spritesheet.getWidth()/gridSize)), gridSize,gridSize),c*gridSize,r*gridSize,null); 

                    g.setColor(Color.BLACK);
                    for(int r=0;r<layerList.getSelectedValue().rowSize+1;r++)
                    g.drawLine(0,r*gridSize,layerList.getSelectedValue().colSize*gridSize,r*gridSize);
                    for(int c=0;c<layerList.getSelectedValue().colSize+1;c++)
                    g.drawLine(c*gridSize,0,c*gridSize,layerList.getSelectedValue().rowSize*gridSize);
                    Point pos = this.getMousePosition();
                    if(pos!=null&&insideGrid(pos)){
                        int r = (pos.y/gridSize)*gridSize;
                        int c = (pos.x/gridSize)*gridSize;
                        g.setColor(new Color(0,0,0,32));
                        g.fillRect(c, r, gridSize, gridSize);
                    }
                }
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        spriteScrollPane = new javax.swing.JScrollPane();
        spriteSheetPanel = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                repaint();
                if(spritesheet!=null)
                g.drawImage(spritesheet,0,0,null);
                g.setColor(Color.BLACK);
                ((java.awt.Graphics2D)g).setStroke(new java.awt.BasicStroke(3));
                if(selectedTile!=null)
                g.drawRect(selectedTileX,selectedTileY,gridSize,gridSize);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        addLayerBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listModel = new DefaultListModel();
        layerList = new JList<Map>(listModel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Winds Of Blade - Map Editor");
        setPreferredSize(new java.awt.Dimension(1280, 720));

        toolbar.setBorder(null);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        newBtn.setText("New");
        newBtn.setFocusable(false);
        newBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });
        toolbar.add(newBtn);

        loadBtn.setText("Load");
        loadBtn.setFocusable(false);
        loadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtnActionPerformed(evt);
            }
        });
        toolbar.add(loadBtn);

        saveBtn.setText("Save");
        saveBtn.setFocusable(false);
        saveBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        toolbar.add(saveBtn);

        mapPanel.setFocusable(false);
        mapPanel.setName("mapPane"); // NOI18N
        mapPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mapPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1339, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(mapPanel);

        jLabel2.setText("Sprite Sheet");

        spriteScrollPane.setToolTipText("");
        spriteScrollPane.setAutoscrolls(true);
        spriteScrollPane.setName("spritePane"); // NOI18N
        spriteScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                spriteScrollPaneMousePressed(evt);
            }
        });

        spriteSheetPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                spriteSheetPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout spriteSheetPanelLayout = new javax.swing.GroupLayout(spriteSheetPanel);
        spriteSheetPanel.setLayout(spriteSheetPanelLayout);
        spriteSheetPanelLayout.setHorizontalGroup(
            spriteSheetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );
        spriteSheetPanelLayout.setVerticalGroup(
            spriteSheetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        spriteScrollPane.setViewportView(spriteSheetPanel);

        jLabel1.setText("Layers");

        addLayerBtn.setText("Add");
        addLayerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLayerBtnActionPerformed(evt);
            }
        });

        delBtn.setText("Delete");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(layerList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spriteScrollPane)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addLayerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spriteScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLayerBtn)
                    .addComponent(delBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>                        

    private void loadBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {                                       
        NewForm nf = new NewForm(this, true);
        nf.setVisible(true);
    }                                      

    private void addLayerBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        maxLayer++;
        listModel.addElement(new Map(mapRow, mapCol, maxLayer));
    }                                           

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {                                       
        listModel.remove(layerList.getSelectedIndex());
    }                                      

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void spriteScrollPaneMousePressed(java.awt.event.MouseEvent evt) {                                              

    }                                             

    private void spriteSheetPanelMousePressed(java.awt.event.MouseEvent evt) {                                              
        selectedTileX = (evt.getX()/gridSize)*gridSize;
        selectedTileY = (evt.getY()/gridSize)*gridSize;
        selectedTile = spritesheet.getSubimage( selectedTileX, selectedTileY, gridSize, gridSize);
    }                                             

    private void mapPanelMousePressed(java.awt.event.MouseEvent evt) {                                      
        if(selectedTile!=null){
            layerList.getSelectedValue().set((evt.getY()/gridSize),(evt.getX()/gridSize),(selectedTileY/gridSize)*(spritesheet.getWidth()/gridSize)+(selectedTileX/gridSize)+1); 
           // System.out.println(selectedTileY+","+selectedTileX+" "+((selectedTileY/gridSize)*(spritesheet.getWidth()/gridSize)+(selectedTileX/gridSize)));
           // System.out.println((selectedTileY/gridSize)+"*"+(spritesheet.getWidth()/gridSize)+"+"+(selectedTileX/gridSize));
        }
       /* if(map!=null){
            for(int r=0;r<map.rowSize;r++){
                for(int c=0;c<map.colSize;c++){
                    System.out.print(map.get(r, c));
                }
                System.out.println("");
            }
        }*/
    }                                     
    private static boolean insideGrid(Point pos){
        return (pos.x>-1&&pos.x<layerList.getSelectedValue().colSize*gridSize&&pos.y>-1&&pos.y<layerList.getSelectedValue().rowSize*gridSize);
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
            java.util.logging.Logger.getLogger(MapMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MapMaker().setVisible(true);
            }
        });
    }
    static DefaultListModel<Map> listModel;
    // Variables declaration - do not modify                     
    private javax.swing.JButton addLayerBtn;
    private javax.swing.JButton delBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    static javax.swing.JList<Map> layerList;
    private javax.swing.JButton loadBtn;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JButton newBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane spriteScrollPane;
    static javax.swing.JPanel spriteSheetPanel;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration                   
}
