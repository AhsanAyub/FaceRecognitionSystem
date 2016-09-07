/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.jws.WebResult;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeArray;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author ahsan_000
 */
public class FaceDetection extends javax.swing.JFrame {

    //defination
    static int imageLoad = 0;
    BufferedImage image;
    private DaemonThread myThread = null; //inner class
    int count = 0;
    VideoCapture webSource = null;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    CascadeClassifier faceDetector = new CascadeClassifier(FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
    MatOfRect faceDetections = new MatOfRect();
    
    //user inputs definations
    String name = "", phone = "", flat = "";
    
    //class as defined
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Graphics g = jPanel1.getGraphics();
                            faceDetector.detectMultiScale(frame, faceDetections);
                            for (Rect rect : faceDetections.toArray()) {
                               Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255,0));
                            }
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            //if (g.drawImage(buff, 0, 0, getWidth(), getHeight()-150 , 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                            if (g.drawImage(buff, 0, 0, 381, 399, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Paused ..... ");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            //System.out.println("Error");
                        }
                    }
                }
            }
        }
    }
    
    
    public FaceDetection() {
        initComponents();
        
        this.setResizable(false);
        loadVideo();
        firstStageOfAction();
    }
    private void firstStageOfAction()
    {       
        //buttons
        num0.setVisible(false);
        num1.setVisible(false);
        num2.setVisible(false);
        num3.setVisible(false);
        num4.setVisible(false);
        num5.setVisible(false);
        num6.setVisible(false);
        num7.setVisible(false);
        num8.setVisible(false);
        num9.setVisible(false);
        
        numAsteric.setVisible(false);
        numHash.setVisible(false);
        
        saveToDB.setVisible(false);
        
        //textfields
        nameText.setVisible(false);
        phoneText.setVisible(false);
        
        //labels
        nameIcon.setVisible(false);
        phoneIcon.setVisible(false);
        flatIcon.setVisible(false);
        
        //combobox
        flatCombo.setVisible(false);
        
        //camera button
        //jButton1.setVisible(true);
        imageLoad = 0;
        
    }
    
    private void SecondStageOfAction()
    {
        //buttons
        num0.setVisible(true);
        num1.setVisible(true);
        num2.setVisible(true);
        num3.setVisible(true);
        num4.setVisible(true);
        num5.setVisible(true);
        num6.setVisible(true);
        num7.setVisible(true);
        num8.setVisible(true);
        num9.setVisible(true);
        
        numAsteric.setVisible(true);
        numHash.setVisible(true);
        
        saveToDB.setVisible(true);
        
        //textfields
        nameText.setVisible(true);
        phoneText.setVisible(true);
        
        //labels
        nameIcon.setVisible(true);
        phoneIcon.setVisible(true);
        flatIcon.setVisible(true);
        
        //combobox
        flatCombo.setVisible(true);
        
        
        //text field
        phoneText.setEditable(false);
        
        /*camera button
        jButton3.setEnabled(true);
        jButton1.setEnabled(false);*/
    }

    private void loadVideo()
    {
        webSource = new VideoCapture(0); // video capture from default cam
        myThread = new DaemonThread(); //create object of threat class
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
    
    private void makeItSleep()
    {
        try {
            Thread.sleep(5000);
            System.out.println("Sleep");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        flatCombo = new javax.swing.JComboBox();
        phoneText = new javax.swing.JTextField();
        nameText = new javax.swing.JTextField();
        saveToDB = new javax.swing.JButton();
        flatIcon = new javax.swing.JLabel();
        nameIcon = new javax.swing.JLabel();
        phoneIcon = new javax.swing.JLabel();
        num1 = new javax.swing.JButton();
        num2 = new javax.swing.JButton();
        num3 = new javax.swing.JButton();
        num4 = new javax.swing.JButton();
        num5 = new javax.swing.JButton();
        num6 = new javax.swing.JButton();
        num7 = new javax.swing.JButton();
        num8 = new javax.swing.JButton();
        num9 = new javax.swing.JButton();
        numAsteric = new javax.swing.JButton();
        num0 = new javax.swing.JButton();
        numHash = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Face Detection");
        setBackground(new java.awt.Color(102, 205, 170));
        setForeground(java.awt.Color.darkGray);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/capture.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        flatCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1A", "2A", "2B", "3A", "3B", "4A", "4B", "5A", "5B", "6A", "6B" }));

        phoneText.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 51)));
        phoneText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTextActionPerformed(evt);
            }
        });

        saveToDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add-contacts-icon-image-gallery-L4V88D-clipart.png"))); // NOI18N
        saveToDB.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 255, 51)));
        saveToDB.setLabel("");
        saveToDB.setMaximumSize(new java.awt.Dimension(180, 60));
        saveToDB.setMinimumSize(new java.awt.Dimension(180, 60));
        saveToDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToDBActionPerformed(evt);
            }
        });

        flatIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/KijM6z4iq.png"))); // NOI18N
        flatIcon.setText("jLabel1");

        nameIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/person-outline-icon-png-person-outline-icon-png-person-17.png"))); // NOI18N
        nameIcon.setName(""); // NOI18N

        phoneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/aceR6eydi.png"))); // NOI18N
        phoneIcon.setText("jLabel1");

        num1.setBackground(new java.awt.Color(204, 255, 204));
        num1.setText("1");
        num1.setBorder(null);
        num1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num1ActionPerformed(evt);
            }
        });

        num2.setBackground(new java.awt.Color(204, 255, 204));
        num2.setText("2");
        num2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num2ActionPerformed(evt);
            }
        });

        num3.setBackground(new java.awt.Color(204, 255, 204));
        num3.setText("3");
        num3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num3ActionPerformed(evt);
            }
        });

        num4.setBackground(new java.awt.Color(204, 255, 204));
        num4.setText("4");
        num4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num4ActionPerformed(evt);
            }
        });

        num5.setBackground(new java.awt.Color(204, 255, 204));
        num5.setText("5");
        num5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num5ActionPerformed(evt);
            }
        });

        num6.setBackground(new java.awt.Color(204, 255, 204));
        num6.setText("6");
        num6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num6ActionPerformed(evt);
            }
        });

        num7.setBackground(new java.awt.Color(204, 255, 204));
        num7.setText("7");
        num7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num7ActionPerformed(evt);
            }
        });

        num8.setBackground(new java.awt.Color(204, 255, 204));
        num8.setText("8");
        num8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num8ActionPerformed(evt);
            }
        });

        num9.setBackground(new java.awt.Color(204, 255, 204));
        num9.setText("9");
        num9.setToolTipText("");
        num9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num9ActionPerformed(evt);
            }
        });

        numAsteric.setBackground(new java.awt.Color(204, 255, 204));
        numAsteric.setText("C");
        numAsteric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numAstericActionPerformed(evt);
            }
        });

        num0.setBackground(new java.awt.Color(204, 255, 204));
        num0.setText("0");
        num0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num0ActionPerformed(evt);
            }
        });

        numHash.setBackground(new java.awt.Color(204, 255, 204));
        numHash.setLabel("<--");
        numHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numHashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/refresh.png"))); // NOI18N
        jButton3.setBorder(new javax.swing.border.MatteBorder(null));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(49, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveToDB, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(num7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(num5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(num2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(num8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(num3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(num6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(num9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numAsteric, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num0, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numHash, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(flatIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(phoneText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(flatCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(flatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(flatIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(phoneIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(num1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(num4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(num7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numAsteric, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num0, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numHash, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveToDB, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        numHash.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void LoadImage()
    {
        Graphics graphics = jPanel4.getGraphics();
        if(graphics.drawImage(image, 0, 0, 180, 70, this))
            System.out.println("image drawn");
        
        jButton1.setEnabled(false);
        imageLoad = 0;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(imageLoad == 1)
        {
            LoadImage();
            return;
        }
        
        BufferedImage buffer = null;
      
        if(!webSource.isOpened())
        {
            System.out.println("Camera is NOT opened!");
        }
        else
        {                  
            while(true)
            {
                if (webSource.read(frame)){

                    buffer = MatToBufferedImage(frame);
                    saveImage(buffer);
                    break;
                }
            }
            if(renameImage("temp", "temp.png"))
            {
                try
                {
                    image = ImageIO.read(new File("temp.png"));
                    
                    SecondStageOfAction();
                    //makeItSleep();
                    //JLabel label = new JLabel(new ImageIcon(buff));
                    //jPanel4.add(label);
                    
                    imageLoad = 1;
                }
                catch (IOException ex) {
                    System.out.println("Error to load");
                }
            }
            else
            {
                System.out.println("File renaming problem");
                return;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean renameImage(String f1, String f2)
    {
        File file1 = new File(f1); //file that should exist
        File file2 = new File(f2); //This is the way file should be changed
        if(!file1.exists() && file2.exists())
        {
            return false; // no temp named file exists
        }
        else
        {
            if(file1.renameTo(file2))
                return true; //successful operation
            
            return false; //couldn't rename the file - FAILURE
        }
    }
    
    /*public BufferedImage loadImage(String file) {
        BufferedImage img;

        try {
            File input = new File(file);
            img = ImageIO.read(input);

            return img;
        } catch (Exception e) {
            System.out.println("error");
        }

        return null;
    }*/
    
    private BufferedImage MatToBufferedImage(Mat frame)
    {
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }
    
    private void saveImage(BufferedImage img) {        
        try {
            File outputfile = new File("temp");
            ImageIO.write(img, "png", outputfile);
            //return loadImage(fileName);
        } catch (Exception e) {
            System.out.println("error");
        }
        //return null;
    }
    
    private void phoneTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTextActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_phoneTextActionPerformed

    private void num4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num4ActionPerformed
        // TODO add your handling code here:
        phone += num4.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num4ActionPerformed

    private void num8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num8ActionPerformed
        // TODO add your handling code here:
        phone += num8.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num8ActionPerformed

    private void num9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num9ActionPerformed
        // TODO add your handling code here:
        phone += num9.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num9ActionPerformed

    private void num1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num1ActionPerformed
        // TODO add your handling code here:
        phone += num1.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num1ActionPerformed

    private void num2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num2ActionPerformed
        // TODO add your handling code here:
        phone += num2.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num2ActionPerformed

    private void num3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num3ActionPerformed
        // TODO add your handling code here:
        phone += num3.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num3ActionPerformed

    private void num5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num5ActionPerformed
        // TODO add your handling code here:
        phone += num5.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num5ActionPerformed

    private void num6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num6ActionPerformed
        // TODO add your handling code here:
        phone += num6.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num6ActionPerformed

    private void num7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num7ActionPerformed
        // TODO add your handling code here:
        phone += num7.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num7ActionPerformed

    private void num0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num0ActionPerformed
        // TODO add your handling code here:
        phone += num0.getText().toString();
        phoneText.setText(phone);
    }//GEN-LAST:event_num0ActionPerformed

    private void numAstericActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numAstericActionPerformed
        // TODO add your handling code here:
        phoneText.setText("");
        phone = "";
        return;
    }//GEN-LAST:event_numAstericActionPerformed

    private void numHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numHashActionPerformed
        // TODO add your handling code here:
        //int lastIndex = phone.lastIndexOf(phone);
        phone = phone.substring(0, phone.length() - 1);
        phoneText.setText(phone);

        return;
    }//GEN-LAST:event_numHashActionPerformed

    private void saveToDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToDBActionPerformed
        // TODO add your handling code here:
        String fileName = "";
        name = nameText.getText();
        flat = flatCombo.getSelectedItem().toString();
      
        if(name.length() > 0)
            //Name exists
        {
            if(phone.length() > 0) //phone entered
            {
                if(!flat.equals("1A"))
                {
                    fileName = name + "_" + phone + "_" + flat + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
                else
                {
                    fileName = name + "_" + phone + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
            }
            else
            {
                if(!flat.equals("1A"))
                {
                    fileName = name + "_" + flat + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
                else
                {
                    fileName = name + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
            }
        }
        else
            //Name NOT exists
        {
            if(phone.length() > 0) //phone entered
            {
                if(!flat.equals("1A")) //flat entered
                {
                    fileName = phone + "_" + flat + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
                else
                {
                    fileName = phone + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
            }
            else
            {
                if(!flat.equals("1A"))
                {
                    fileName = flat + ".png";
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
                else
                {
                    Date date = new Date();
                    fileName = date.toString();
                    fileName += ".png";
                    int len = fileName.length() - 1;
                    StringBuilder fName = new StringBuilder(fileName);
                    for(int i = 0; i <= len; i++)
                    {
                        if(fileName.charAt(i) == ':')
                            fName.setCharAt(i, '.');
                            
                    }
                    fileName = fName.toString();
                    //System.out.println(fileName);
                    //return;
                    //System.out.println(fileName);
                    renameImage("temp.png", fileName);
                    firstStageOfAction();
                    return;
                }
            }
        }
    }//GEN-LAST:event_saveToDBActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /*if(imageLoad == 0)
        {
            jButton1.setEnabled(true);
            return;
        }
        if(imageLoad == 1)
        {
            LoadImage();
            jButton3.setEnabled(false);
        }*/
        if(jButton1.isEnabled())
            System.out.println("enabled");
        else
            System.out.println("NOT enabled");

        if(!jButton1.isEnabled())
        {
            System.out.println("NOT E If");
            if(!saveToDB.isVisible())
            {
                System.out.println("NOT E If - T");
                jButton1.setEnabled(true);
            }
        }
            
        return;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        myThread.runnable = false;
        webSource.release();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FaceDetection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox flatCombo;
    private javax.swing.JLabel flatIcon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel nameIcon;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton num0;
    private javax.swing.JButton num1;
    private javax.swing.JButton num2;
    private javax.swing.JButton num3;
    private javax.swing.JButton num4;
    private javax.swing.JButton num5;
    private javax.swing.JButton num6;
    private javax.swing.JButton num7;
    private javax.swing.JButton num8;
    private javax.swing.JButton num9;
    private javax.swing.JButton numAsteric;
    private javax.swing.JButton numHash;
    private javax.swing.JLabel phoneIcon;
    private javax.swing.JTextField phoneText;
    private javax.swing.JButton saveToDB;
    // End of variables declaration//GEN-END:variables
}
