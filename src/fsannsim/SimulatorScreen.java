/*
 * SimulatorScreen.java
 *
 * Created on June 7, 2006, 8:23 AM
 */

package fsannsim;

import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author  Mahmud
 */
class ScreenContainer extends JInternalFrame implements Serializable {
    Simulator Main;
    SimulatorScreen ss;
    ScreenContainer(Simulator Main) {
        super("Simulation", true, true, true, true);
        this.Main = Main;
        ss = new SimulatorScreen(Main);
        getContentPane().add(ss, BorderLayout.CENTER);
        setSize(800, 600);
        setVisible(true);
        ss.repaint();
    }

    public void redraw() {
        ss.repaint();
    }
}


public class SimulatorScreen extends JPanel implements Serializable {
    private static final int MAXNEURON = 31;
    int dx, dy, w, h, x, y, r, d;
    int selx, sely;
    int Wh[][];

    /** Creates new form SimulatorScreen */
    Simulator Main;
    public SimulatorScreen(Simulator Main) {
        w = 10;
        h = 10;
        x = 70;
        y = 50;
        r = 75;
        d = 10;
        this.Main = Main;
        setBackground(new Color(255, 255, 255));
        initComponents();
        //setVisible(true);
    }

    public void LoadInput() {
        int i;
        for (i = 0; i < Main.V[0].length; i++) {
            Main.V[0][i] = 1;
        }
    }

    public boolean DoSimulate(int step) { //Return false after completion
        switch (step) {
        case 1: {
            //Random Weight

        }
        break;
        case 2: {
            int i, j, k;
            LoadInput();
        }
        break;

        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        setLayout(new java.awt.BorderLayout());

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

    }

    // </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_formMouseClicked
        int i, j;

        for (i = 0; i < Main.N; i++) {
            for (j = 0; j < Main.W[i].length; j++) {
                if (((i * dx + x) - evt.getX()) < w &&
                    (Wh[i][j] - evt.getY()) < h) {
                    selx = i;
                    sely = j;
                }
            }
        }

        if (evt.getButton() == 2) {
            try {
                //.out.println(selx +","+ sely);
                new ErrorPlot(Main, selx, sely); //.setSelected(true);
                //System.out.println("!!");
            } catch (Exception e) {

            }
        }
        if (Main.isUnlimited && evt.getButton() == 3) {
            try {
                //.out.println(selx +","+ sely);
                ErrorPlot ep = new ErrorPlot(Main, -1, -1); //.setSelected(true);
                Main.rightTool.add(ep);
                //System.out.println("!!");
            } catch (Exception e) {

            }
        }
        repaint();
    } //GEN-LAST:event_formMouseClicked

    public void paint(Graphics g) {
        super.paint(g);
        int i, j;
        int midy;
        double mid;
        Font fnt;
        java.awt.Dimension screenSize = getSize();
        midy = ((screenSize.height - y - d) / 2) + y;

        dx = (screenSize.width - x - r) / (Main.N - 1);
        Wh = new int[Main.N][];

        if (Main.isColorNetwork) {
            g.setColor(new Color(122, 0, 0));
        }
        g.drawString("Iteration Step: " + Main._t, screenSize.width - 200, 40);
        //System.out.println();
        //Draw The Neurons
        if (Main.isColorNetwork) {
            g.setColor(new Color(0, 175, 0));
        }
        for (i = 0; i < Main.N; i++) {
            Wh[i] = new int[Main.W[i].length];
            dy = (screenSize.height - y - d) / Main.W[i].length;
            mid = (Main.W[i].length) / 2;
            if (Main.W[i].length % 2 == 0) {
                mid -= .5;
            }

            if (Main.W[i].length < MAXNEURON) {
                for (j = 0; j < Main.W[i].length; j++) {
                    Wh[i][j] = midy - (int) ((mid - (double) j) * (double) dy);
                    //Draw the Numbers
                    ///g.drawString(i+","+j, i * dx + x + w, Wh[i][j]);
                    //Draw the Values
                    ///if(i == 0)
                    ///	g.drawString(""+Main.V[i][j], i * dx + x + w - 30, Wh[i][j] + 10);
                    ///else
                    ///	g.drawString(""+Main.V[i][j], i * dx + x + w - 15, Wh[i][j]);
                    if (Main.isNeuron) {
                        g.drawOval(i * dx + x, Wh[i][j], w, h);
                    }
                }
            } else {
                g.drawOval(i * dx + x - 15, midy - 15, 30, 30);
                g.drawOval(i * dx + x - 13, midy - 13, 26, 26);
                g.drawOval(i * dx + x - 11, midy - 11, 22, 22);
                g.drawOval(i * dx + x - 9, midy - 9, 18, 18);
                fnt = g.getFont();
                g.setFont(new Font(fnt.getFontName(), Font.ITALIC + Font.BOLD,
                                   18));
                g.drawString("" + Main.W[i].length, i * dx + x - 15, midy + 30);
                g.setFont(fnt);
            }
        }

        //Draw the Numbers
        /*
                 g.setColor(new Color(0,75,0));
                 for(i = 0; i < Main.N; i++){
            dy = (screenSize.height - y - d) / Main.W[i].length;
            mid = (Main.W[i].length) / 2;
            if(Main.W[i].length % 2 == 0)
             mid -= .5;
            for(j = 0; j < Main.W[i].length; j++){

                g.drawString(i+","+j, i * dx + x + w, Wh[i][j]);
            }
                 }
         */

        //Draw the Values
        if (Main.isValue) {
            if (Main.isColorNetwork) {
                g.setColor(new Color(0, 125, 0));
            }
            for (i = 0; i < Main.N; i++) {
                if (Main.W[i].length < MAXNEURON) {
                    dy = (screenSize.height - y - d) / Main.W[i].length;
                    mid = (Main.W[i].length) / 2;
                    if (Main.W[i].length % 2 == 0) {
                        mid -= .5;
                    }
                    for (j = 0; j < Main.W[i].length; j++) {

                        if (i == 0) {
                            g.drawString("" + Main.V[i][j],
                                         i * dx + x + w - 50, Wh[i][j] + 11);
                        } else {
                            g.drawString("" + Main.V[i][j],
                                         i * dx + x + w - 15, Wh[i][j] + 30);
                        }

                        if (i == Main.N - 1) {
                            if (Main.isColorNetwork) {
                                g.setColor(Color.red);
                            }
                            g.drawString("" + Main.DO[j],
                                         i * dx + x + w + 30, Wh[i][j] + 5);
                        }
                    }
                }
            }
        }
        //Draw the Synapsis

        double midnext;
        int k;

        int shiftright = 0, percent = 0, lead = 0;

        for (i = 1; i < Main.N; i++) {
            dy = (screenSize.height - y - d) / Main.W[i].length;
            mid = Main.W[i].length / 2;
            midnext = Main.W[i - 1].length / 2;

            percent = (((dx * 60) / 100) / Main.W[i].length);
            if (percent > 15) {
                percent = 15;
            }
            lead = (dx * 20 / 100);
            if (Main.W[i].length < MAXNEURON) {
                for (j = 0; j < Main.W[i].length; j++) {
                    if (!(selx == i && sely == j)) {
                        if (Main.isColorNetwork) {
                            g.setColor(new Color(195, 195, 180));
                        }
                        shiftright = (percent * j) + lead;

                        if (Main.W[i - 1].length < MAXNEURON) {
                            for (k = 0; k < Main.W[i - 1].length; k++) {
                                ///Draw the synapsis
                                if (Main.isSynapse) {
                                    g.drawLine(i * dx + x, Wh[i][j],
                                               (i - 1) * dx + x,
                                               Wh[i - 1][k]);
                                }
                                ///Draw the weights
                                if (Main.isWeight) {
                                    g.drawString("" + Main.W[i - 1][k][j],
                                                 ((i - 1) * dx + x) +
                                                 shiftright,
                                                 -((Wh[i - 1][k] - Wh[i][j]) *
                                            shiftright) / dx + Wh[i - 1][k] -
                                                 5);
                                }
                            }
                        } else {
                            if (Main.isSynapse) {
                                g.drawLine(i * dx + x, Wh[i][j],
                                           (i - 1) * dx + x,
                                           midy);
                            }
                        }
                    }
                }
            } else {
                if (Main.isColorNetwork) {
                    g.setColor(new Color(195, 195, 180));
                }

                if (Main.W[i - 1].length < MAXNEURON) {
                    for (k = 0; k < Main.W[i - 1].length; k++) {
                        ///Draw the synapsis
                        if (Main.isSynapse) {
                            g.drawLine(i * dx + x, midy,
                                       (i - 1) * dx + x,
                                       Wh[i - 1][k]);
                        }
                    }
                } else {
                    if (Main.isColorNetwork) {
                        g.setColor(new Color(195, 195, 180));
                    }
                    g.drawLine(i * dx + x, midy,
                               (i - 1) * dx + x,
                               midy);
                }
            }
        }

        if (selx > 0) {
            if (Main.isColorNetwork) {
                g.setColor(new Color(0, 0, 175));
            }
            if (Main.W[selx].length < MAXNEURON) {
                shiftright = (percent * sely) + lead;
                if (Main.W[selx - 1].length < MAXNEURON) {
                    for (k = 0; k < Main.W[selx - 1].length; k++) {
                        g.drawLine(selx * dx + x, Wh[selx][sely],
                                   (selx - 1) * dx + x, Wh[selx - 1][k]);
                        g.drawString("" + Main.W[selx - 1][k][sely],
                                     ((selx - 1) * dx + x) + shiftright,
                                     -((Wh[selx - 1][k] - Wh[selx][sely]) *
                                       shiftright) / dx + Wh[selx - 1][k] - 5);
                    }
                } else {
                    g.drawLine(selx * dx + x, Wh[selx][sely],
                               (selx - 1) * dx + x, midy);
                }
            } else {
                if (Main.W[selx - 1].length < MAXNEURON) {
                    for (k = 0; k < Main.W[selx - 1].length; k++) {
                        g.drawLine(selx * dx + x, midy,
                                   (selx - 1) * dx + x, Wh[selx - 1][k]);
                    }
                } else {
                    g.drawLine(selx * dx + x, midy,
                               (selx - 1) * dx + x, midy);
                }
            }

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}