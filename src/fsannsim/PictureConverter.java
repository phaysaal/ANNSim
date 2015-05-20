package fsannsim;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

/**
 * <p>Title: Fs ANN Sim</p>
 *
 * <p>Description: Artificial Neural Network Simulator</p>
 *
 * <p>Copyright: Open Source and Open Resource </p>
 *
 * <p>Company: F Sharp</p>
 *
 * @author Mahmudul Faisal Al Ameen
 * @version 1.0
 */
public class PictureConverter extends JFrame {
    RandomAccessFile ras = null;
    int picdata[][][] = null;
    public PictureConverter() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jButton1.setBounds(new Rectangle(197, 314, 131, 25));
        jButton1.setText("jButton1");
        jButton1.addActionListener(new PictureConverter_jButton1_actionAdapter(this));
        jScrollPane1.setBounds(new Rectangle(56, 376, 430, 218));
        jTextArea1.setText("jTextArea1");
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jScrollPane1);
        jScrollPane1.getViewport().add(jTextArea1);
    }

    public static void main(String[] args) {
        PictureConverter pictureconverter = new PictureConverter();
        pictureconverter.setSize(600, 500);
        pictureconverter.setVisible(true);
    }

    JButton jButton1 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();
    public void load() {
        int x, i;
        jTextArea1.setText("");
        try {
            ras.seek(0);
            x = ras.read();
            i = 0;
            while (x > -1) {
                jTextArea1.append(i + ":" + x + "\n");
                x = ras.read();
                i++;
            }
        } catch (IOException ex) {
        }

    }

    public void jButton1_actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(this);
        Image img;

        try {
            ras = new RandomAccessFile(jfc.getSelectedFile(), "r");

            //load();
            int width, height;

            int i = 0;
            ras.seek(18);
            int w1 = ras.read();
            int w2 = ras.read();
            width = w2 * 256 + w1;

            ras.seek(22);
            int h1 = ras.read();
            int h2 = ras.read();
            height = h2 * 256 + h1;

            picdata = new int[height][width][3];
            ras.seek(54);
            int j, k;
            int left = 50, top = 50 + height;
            int count = 0;
            long sc = (ras.length() - ((width * height * 3) + 53)) / height;
            for (i = height - 1; i > -1; i--) {
                for (j = 0; j < width; j++) {
                    picdata[i][j][2] = ras.read();
                    picdata[i][j][1] = ras.read();
                    picdata[i][j][0] = ras.read();
                }
                for (k = 0; k < sc; k++) {
                    ras.read();
                }
            }
            repaint();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            /** @todo Handle this exception */
        }
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        //*
         if (picdata != null) {
             int i, j, col;
             for (i = 0; i < picdata.length; i++) {
                 for (j = 0; j < picdata[i].length; j++) {
                     col = (picdata[i][j][0]+ picdata[i][j][1]+ picdata[i][j][2]) /
                           3;
                     if (col < 128) {
                         gr.setColor(Color.BLACK);
                     } else {
                         gr.setColor(Color.WHITE);
                     }
                     //gr.setColor(new Color(picdata[i][j][0],picdata[i][j][1],picdata[i][j][2]));
                     gr.drawLine(50 + j, 50 + i, 50 + j, 50 + i);
                 }
             }
         }
         //*/


    }
}


class PictureConverter_jButton1_actionAdapter implements ActionListener {
    private PictureConverter adaptee;
    PictureConverter_jButton1_actionAdapter(PictureConverter adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
