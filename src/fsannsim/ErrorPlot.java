package fsannsim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class ErrorPlotCanvas extends JPanel implements java.io.Serializable{
    public static final double ZOOM_FACTOR = 1.15;
    int x, y;
    int mousex = 0, mousey = 0;


    Simulator sMain;

    int xp, yp, w, h;
    double scx, scy;
    double tx, ty;
    ErrorPlotCanvas(Simulator sMain, int x, int y) {
        this.sMain = sMain;
        this.x = x;
        this.y = y;
        scx = 1.0;
        scy = 1.0;
    }

    public void scale(double scx, double scy) {
        this.scx = scx;
        this.scy = scy;
        repaint();
    }

    public void translate(double tx, double ty) {
        this.tx = tx;
        this.ty = ty;
        repaint();
    }

    public double getScaleX() {
        return scx;
    }

    public double getScaleY() {
        return scy;
    }

    public void paint(Graphics g) {
        super.paint(g);

        //Graphics2D g = (Graphics2D) gn;
        //g.translate( -tx, -ty);
        //g.scale(scx, scy);

        w = getWidth();
        h = getHeight();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);
        int t = (int) (h * .9);
        int l = (int) (w * .1);
        w = (int) (w * .8);
        h = (int) (h * .8);
        if(sMain.isColorPlot)
        g.setColor(new Color(120, 200, 200));
        g.drawLine(l, t, w + l, t);
        g.drawLine(l, t, l, t - h);
        int i;
        if(sMain.isColorPlot)
        g.setColor(new Color(220, 100, 200));
        String s;
        double max = 1;
        if (x == -1 && y == -1) {
            max = (sMain.derror[0]);
            //System.out.println(sMain._t);
            //AMPLITUDE
            for (i = 1; i < sMain._t; i++) {
                //System.out.println(sMain.err[0][x][y]);
                if ((sMain.derror[i]) > max) {
                    //System.out.println(max);
                    max = (sMain.derror[i]);
                }
            }
            //System.out.println(max);
            //Y axis LABELING
            for (i = 0; i <= 4; i++) {
                //System.out.println((max*i/4));
                s = "" + (max * i / 4);
                if (s.length() > 4) {
                    s = s.substring(0, 4);
                }
                g.drawString(s, l - 30, t - (int) (i * h / 4));
            }

            if(sMain.isColorPlot)
            g.setColor(new Color(200, 0, 0));
            for (i = 1; i < sMain._t; i++) {
                //PLOT LINE
                g.drawLine(l + ((i - 1) * w / sMain._t),
                           t - ((int) ((sMain.derror[i - 1]) * h / max)),
                           l + ((i) * w / sMain._t),
                           t - ((int) ((sMain.derror[i]) * h / max)));
/*
                if (sMain.derror[i] / (sMain.derror[i - 1] + sMain.tolerance) >
                    10) {
                    s = "" + sMain.derror[i];
                    if (s.length() > 6) {
                        s = s.substring(0, 6);
                    }
                    g.drawString(s, l + ((i) * w / sMain._t) - 10,
                                 t - ((int) ((sMain.derror[i]) * h / max)) - 10);
                    g.drawString(i + "", l + ((i) * w / sMain._t) - 5, t + 10);

                }
*/
            }
        } else {
            max = Math.abs(sMain.err[0][x][y]);
            //System.out.println(sMain._t);
            for (i = 1; i < sMain._t; i++) {
                //System.out.println(sMain.err[0][x][y]);
                if (Math.abs(sMain.err[i][x][y]) > max) {
                    //System.out.println(max);
                    max = Math.abs(sMain.err[i][x][y]);
                }
            }
            //System.out.println(max);
            for (i = 0; i <= 4; i++) {
                //System.out.println((max*i/4));
                s = "" + (max * i / 4);
                if (s.length() > 4) {
                    s = s.substring(1, 4);
                }
                g.drawString(s, l - 30, t - (int) (i * h / 4));
            }

            if(sMain.isColorPlot)
            g.setColor(new Color(235, 0, 0));
            for (i = 1; i < sMain._t; i++) {
                g.drawLine(l + ((i - 1) * w / sMain._t),
                           t -
                           ((int) (Math.abs(sMain.err[i - 1][x][y]) * h / max)),
                           l + ((i) * w / sMain._t),
                           t - ((int) (Math.abs(sMain.err[i][x][y]) * h / max)));
            }
        }

        if(mousex > 0 && mousey > 0){

            double yval = (max/h)*(t - (mousey-26));

            //g.setColor(Color.GRAY.brighter());
            //g.fillOval(mousex-10, mousey-10, 20, 20);
            if(sMain.isColorPlot)
            g.setColor(Color.BLACK);

            if(yval > 0 && yval < max)
                g.drawString(""+yval, mousex, mousey);
            mousex = 0;
        }

    }
}


public class ErrorPlot extends JInternalFrame implements ActionListener,
        AdjustmentListener, java.io.Serializable {
    public ErrorPlot() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    ErrorPlotCanvas epc;

    private JToolBar tools;
    private JButton btnZoomInX;
    private JButton btnZoomInY;
    private JButton btnZoomInXY;
    private JButton btnZoomOutX;
    private JButton btnZoomOutY;
    private JButton btnZoomOutXY;

    private JScrollBar verBar;
    private JScrollBar horBar;

    ErrorPlot(Simulator sMain, int x, int y) {
        super("Plot: Error", true, true, true, true);
        if (x == -1 || y == -1) {
            setTitle("Plot: SME");
        } else {
            setTitle("Error Plot (" + x + "," + y + ")");
        }

        tools = new JToolBar();

        btnZoomInX = new JButton("Horizontal Zoom In");
        btnZoomInY = new JButton("Vertical Zoom In");
        btnZoomInXY = new JButton("Screen Zoom In");
        btnZoomOutX = new JButton("Horizontal Zoom Out");
        btnZoomOutY = new JButton("Vertical Zoom Out");
        btnZoomOutXY = new JButton("Screen Zoom Out");

        btnZoomInX.addActionListener(this);
        btnZoomInY.addActionListener(this);
        btnZoomInXY.addActionListener(this);
        btnZoomOutX.addActionListener(this);
        btnZoomOutY.addActionListener(this);
        btnZoomOutXY.addActionListener(this);

        tools.add(btnZoomInX);
        tools.add(btnZoomInY);
        tools.add(btnZoomInXY);
        tools.add(btnZoomOutX);
        tools.add(btnZoomOutY);
        tools.add(btnZoomOutXY);

        epc = new ErrorPlotCanvas(sMain, x, y);

        verBar = new JScrollBar(JScrollBar.VERTICAL, 1, 0, 1, 1);
        horBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 1, 1);

        verBar.addAdjustmentListener(this);
        horBar.addAdjustmentListener(this);

        //getContentPane().add(tools, BorderLayout.NORTH);
        getContentPane().add(epc, BorderLayout.CENTER);
        //getContentPane().add(verBar, BorderLayout.EAST);
        //getContentPane().add(horBar, BorderLayout.SOUTH);

        //getContentPane().add(epc);
        if (x == -1 || y == -1) {
            setSize(550, 400);
        } else {
            setSize(250, 200);
        }
        jbInit();
        setVisible(true);
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        epc.translate(horBar.getValue() * epc.getScaleX() * 10,
                      verBar.getValue() * epc.getScaleY() * 10);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnZoomInX) {
            epc.scale(epc.getScaleX() * epc.ZOOM_FACTOR, epc.getScaleY());

        } else if (e.getSource() == btnZoomInY) {
            epc.scale(epc.getScaleX(), epc.getScaleY() * epc.ZOOM_FACTOR);

        } else if (e.getSource() == btnZoomInXY) {
            epc.scale(epc.getScaleX() * epc.ZOOM_FACTOR,
                      epc.getScaleY() * epc.ZOOM_FACTOR);

        } else if (e.getSource() == btnZoomOutX) {
            epc.scale(epc.getScaleX() / epc.ZOOM_FACTOR, epc.getScaleY());
        } else if (e.getSource() == btnZoomOutY) {
            epc.scale(epc.getScaleX(), epc.getScaleY() / epc.ZOOM_FACTOR);
        } else if (e.getSource() == btnZoomOutXY) {
            epc.scale(epc.getScaleX() / epc.ZOOM_FACTOR,
                      epc.getScaleY() / epc.ZOOM_FACTOR);
        }
        horBar.setMaximum((int) (epc.getScaleX()));
        verBar.setMaximum((int) (epc.getScaleY()));
    }

    private void jbInit() {
        this.addMouseListener(new ErrorPlot_this_mouseAdapter(this));
    }

    public void this_mousePressed(MouseEvent e) {
        epc.mousex = e.getX();
        epc.mousey = e.getY();
        epc.repaint();
    }

    public void this_mouseReleased(MouseEvent e) {
        epc.mousex = 0;
        epc.mousey = 0;
        epc.repaint();
    }


}


class ErrorPlot_this_mouseAdapter extends MouseAdapter {
    private ErrorPlot adaptee;
    ErrorPlot_this_mouseAdapter(ErrorPlot adaptee) {
        this.adaptee = adaptee;
    }

    public void mousePressed(MouseEvent e) {
        adaptee.this_mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {
        adaptee.this_mouseReleased(e);
    }

}
