package fsannsim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.beans.PropertyVetoException;

/*
 * Simulator.java
 *
 * Created on June 7, 2006, 8:14 AM
 */

/**
 *
 * @author  Mahmud
 */


public class Simulator extends javax.swing.JInternalFrame implements
        ActionListener, Serializable {
    ScreenContainer ss = null;
    InputData idata = null;
    NetworkGenerator ng = null;
    SimConfigure simcong = null;
    ErrorPlot ep = null;
    TaskManager tm = null;

    ///menu
    JMenu mnuFile = new JMenu("File");

    JMenuItem mnuSave;
    JMenuItem mnuSaveAs;
    JMenuItem mnuClose;

    JMenu mnuConfugure = new JMenu("Configure");

    JMenuItem mnuLoadData;
    JMenuItem mnuLoadWeight;
    JMenuItem mnuConfigureNetwork;
    JMenuItem mnuConfigureSimulation;
    JMenuItem mnuTaskManager;

    JMenu mnuSimulation = new JMenu("Simulation");

    JMenuItem mnuRun;
    JMenuItem mnuContinue;
    JMenuItem mnuForward;
    JMenuItem mnuNStep;
    JMenuItem mnuReset;
    JMenuItem mnuStop;
    JMenuItem mnuTaskStart;

    JMenu mnuReport = new JMenu("Report");

    JMenuItem mnuPlot;

    JMenuBar mnuBar = new JMenuBar();
    JDesktopPane rightTool;
    //////

    ///data
    public String vinputs;
    public String doutputs;
    public double eta;
    public double alpha;
    public double tolerance;
    public boolean isInfere;

    public boolean isNeuron = true;
    public boolean isValue = true;
    public boolean isWeight = true;
    public boolean isWaitAndShow = false;
    public boolean isSynapse = true;
    public boolean isUnlimited = true;
    public boolean stop = false;
    public boolean isColorNetwork = true;
    public boolean isColorPlot = true;

    public int speed;
    public int I = -1; //number of input neurons
    public int O = -1; //number of output neurons
    public int T; //number of training
    public double W[][][]; //The weights
    public double V[][]; //The values
    public double DO[]; //Desired Output
    public double err[][][];
    public double derror[]; //
    public double dW[][][][];
    public double dV[][][];
    int _t = 0; //training set serial no
    int _tt = 0;
    int _T = 1000; //number of training
    public int N; //number of layers
    public java.io.File inputFile = null;
    private javax.swing.JFileChooser jfc = null;

    //////
    int taskStart;
    int taskCurrent;
    long taskLoopTotal;
    long taskLoopCount;
    boolean isLooping;
    boolean isRun;
    long inum;

    File file = null;

    boolean isInitialized = false;
    /** Creates new form Simulator */

    public Simulator() {
        super("Artificial Neural Network", true, true, true, true);
        eta = 0.2;
        alpha = 0.3;
        speed = 1000;
        tolerance = 0.001;
        isInfere = false;
        initComponents();
        //derror = new Vector();
        //vinputs = new Vector();
        //doutputs = new Vector();
        mnuSimulation.setEnabled(false);
        //mnuReport.setEnabled(false);

        MenuAdd(mnuFile, mnuSave = new JMenuItem("Save"));
        MenuAdd(mnuFile, mnuSaveAs = new JMenuItem("Save As"));
        mnuFile.add(new JSeparator());
        MenuAdd(mnuFile, mnuClose = new JMenuItem("Close"));

        MenuAdd(mnuConfugure, mnuLoadData = new JMenuItem("Load Data"), 'L');
        MenuAdd(mnuConfugure, mnuLoadWeight = new JMenuItem("Load Weight"), 'W');
        MenuAdd(mnuConfugure,
                mnuConfigureNetwork = new JMenuItem("Configure Network"), 'W');
        mnuConfigureNetwork.setEnabled(false);
        MenuAdd(mnuConfugure,
                mnuConfigureSimulation = new JMenuItem("Configure Simulation"),
                                         'C');
        mnuConfigureSimulation.setEnabled(false);
        MenuAdd(mnuConfugure, mnuTaskManager = new JMenuItem("Task Manager"),
                                               'M');
        mnuTaskManager.setEnabled(false);

        MenuAdd(mnuSimulation, mnuRun = new JMenuItem("Run"), 'R');
        MenuAdd(mnuSimulation, mnuContinue = new JMenuItem("Adapt The Input"),
                                             'A');
        MenuAdd(mnuSimulation, mnuForward = new JMenuItem("Step Forward"), 'S');
        MenuAdd(mnuSimulation, mnuNStep = new JMenuItem("Run N Step"), 'N');
        //MenuAdd(mnuSimulation, mnuReset, "Reset");
        MenuAdd(mnuSimulation, mnuStop = new JMenuItem("Stop"), 'X');
        MenuAdd(mnuSimulation, mnuTaskStart = new JMenuItem("Task Start"), 'T');

        MenuAdd(mnuReport, mnuPlot = new JMenuItem("Plot"), 'P');

        mnuBar.add(mnuFile);
        mnuBar.add(mnuConfugure);
        mnuBar.add(mnuSimulation);
        mnuBar.add(mnuReport);

        rightTool = new JDesktopPane();
        getContentPane().add(rightTool, BorderLayout.CENTER);
        //getContentPane().add(ss, BorderLayout.CENTER);
        //setExtendedState (JFrame.MAXIMIZED_BOTH);
        setJMenuBar(mnuBar);
        setVisible(true);

        //testmat();
    }

    public void saveData(ObjectOutputStream oos) {

        try {
            oos.writeInt(1000);
            oos.writeInt(1);

            //oos.writeObject("fsnannsim.100.a");

            //oos.writeObject(ss);

            //oos.writeObject(idata );
            //oos.writeObject(ng );
            //oos.writeObject(simcong );
            //oos.writeObject(ep );
            try {
                UIManager.setLookAndFeel(
                        "javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (UnsupportedLookAndFeelException ex1) {
            } catch (IllegalAccessException ex1) {
            } catch (InstantiationException ex1) {
            } catch (ClassNotFoundException ex1) {
            }

            oos.writeObject(tm);
            try {
                UIManager.setLookAndFeel(UIManager.
                                         getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException ex1) {
            } catch (IllegalAccessException ex1) {
            } catch (InstantiationException ex1) {
            } catch (ClassNotFoundException ex1) {
            }
            oos.writeObject(vinputs);
            oos.writeObject(doutputs);
            oos.writeDouble(eta);
            oos.writeDouble(alpha);
            oos.writeDouble(tolerance);
            oos.writeBoolean(isInfere);

            oos.writeBoolean(isNeuron);
            oos.writeBoolean(isValue);
            oos.writeBoolean(isWeight);
            oos.writeBoolean(isWaitAndShow);
            oos.writeBoolean(isSynapse);
            oos.writeBoolean(isUnlimited);
            oos.writeBoolean(stop);

            oos.writeInt(speed);
            oos.writeInt(I); //number of input neurons
            oos.writeInt(O); //number of output neurons
            oos.writeInt(T); //number of training
            oos.writeObject(W); //The weights
            oos.writeObject(V); //The values
            oos.writeObject(DO); //Desired Output
            oos.writeObject(err);
            oos.writeObject(derror); //
            oos.writeObject(dW);
            oos.writeObject(dV);
            oos.writeInt(_t); //training set serial no
            oos.writeInt(_tt);
            oos.writeInt(_T); //number of training
            oos.writeInt(N); //number of layers
            oos.writeObject(inputFile);

            //////
            oos.writeInt(taskStart);
            oos.writeInt(taskCurrent);
            oos.writeLong(taskLoopTotal);
            oos.writeLong(taskLoopCount);
            oos.writeBoolean(isLooping);
            oos.writeBoolean(isRun);
            oos.writeLong(inum);
            /*
                        int itm;
                        if ((tm != null)) {
                            itm = 1;
                            oos.writeInt(itm);
                            tm.saveData(oos);
                        } else {
                            itm = 0;
                            oos.writeInt(itm);
                        }
             */
            oos.writeObject(file);

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void loadData(ObjectInputStream ois) {

        /*
                 ss ;
              idata ;
              ng ;
              simcong ;
              ep ;
              tm ;
         */


        try {

            tm = (TaskManager) ois.readObject();

            vinputs = (String) ois.readObject();

            doutputs = (String) ois.readObject();
            eta = ois.readDouble();
            alpha = ois.readDouble();
            tolerance = ois.readDouble();
            isInfere = ois.readBoolean();

            isNeuron = ois.readBoolean();
            isValue = ois.readBoolean();
            isWeight = ois.readBoolean();
            isWaitAndShow = ois.readBoolean();
            isSynapse = ois.readBoolean();
            isUnlimited = ois.readBoolean();
            stop = ois.readBoolean();

            speed = ois.readInt();
            I = ois.readInt(); //number of input neurons
            O = ois.readInt(); //number of output neurons
            T = ois.readInt(); //number of training
            W = (double[][][]) ois.readObject(); //The weights
            V = (double[][]) ois.readObject(); //The values
            DO = (double[]) ois.readObject(); //Desired Output
            err = (double[][][]) ois.readObject();
            derror = (double[]) ois.readObject(); //
            dW = (double[][][][]) ois.readObject();
            dV = (double[][][]) ois.readObject();
            _t = ois.readInt(); ; //training set serial no
            _tt = ois.readInt(); ;
            _T = ois.readInt(); ; //number of training
            N = ois.readInt(); ; //number of layers
            inputFile = (File) ois.readObject();

            //////
            taskStart = ois.readInt();
            taskCurrent = ois.readInt();
            taskLoopTotal = ois.readLong();
            taskLoopCount = ois.readLong();
            isLooping = ois.readBoolean();
            isRun = ois.readBoolean(); ;
            inum = ois.readLong();

            mnuSimulation.setEnabled(true);
            mnuConfigureNetwork.setEnabled(false);
            isInitialized = true;

            if (idata == null) {
                idata = new InputData(this);
            }
            try {
                rightTool.add(idata);
                idata.setSelected(true);
            } catch (Exception e) {}
            /*
                        int istm = ois.readInt();
                        if (istm == 1) {
                            if (tm == null) {
                                tm = new TaskManager(this);
                            }
                            tm.loadData(ois);
                        }
             */
            file = (File) ois.readObject();
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        }

    }

    public void testmat() {
        double a[][] = { {1, 2, 3}, {2, 3, 4}, {3, 4, 5}, {4, 5, 6}
        };
        double b[][] = { {1, 2, 3}, {2, 3, 4}, {3, 4, 5}, {4, 5, 6}
        };
        double c[][] = mats(a, b, '*');
        double e[][] = transpose(b);
        double d[][] = matmul(a, transpose(b));

        double f[] = {2, 4, 6, 8, 10};
        matprint(c);
        matprint(d);
        matprint(e);
        matprint(transpose(f));
        matprint(transpose(transpose(f)));
        matprint(transpose(toVec(a)));
    }

    public void matprint(double x[][]) {
        int i, j;
        for (i = 0; i < x.length; i++) {
            for (j = 0; j < x[0].length; j++) {
                System.out.print(x[i][j] + "  ");

            }
            System.out.println();
        }
        System.out.println();
    }

    public void MenuAdd(JMenu mnu, JMenuItem mnuItem) {
        //mnuItem = new JMenuItem(str);
        mnuItem.addActionListener(this);
        mnu.add(mnuItem);
    }

    public void MenuAdd(JMenu mnu, JMenuItem mnuItem, char c) {
        //mnuItem = new JMenuItem(str);
        mnuItem.addActionListener(this);
        mnu.add(mnuItem);
        mnuItem.setAccelerator(KeyStroke.getKeyStroke(c, 0));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        //jButton1 = new javax.swing.JButton();
        getContentPane().setLayout(new BorderLayout());
        //setDesktopIcon(null);
        //jButton1.setText("jButton1");
        //getContentPane().add(jButton1, java.awt.BorderLayout.SOUTH);
        setSize(800, 600);
        //setBounds(0, 0, 800, 600);
    }

    // </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void doAction(String action) {
        //System.out.print(action);
        if (action.equals("Save")) {
            if (file == null) {
                JFileChooser jfc = new JFileChooser();
                jfc.showSaveDialog(this);
                if (jfc.getSelectedFile() == null) {
                    return;
                }
                file = jfc.getSelectedFile();
            }
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new java.io.
                        FileOutputStream(file));
                saveData(oos);
                //oos.writeObject(this);

            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
                /** @todo Handle this exception */
            }

        } else if (action.equals("Save As")) {

            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(this);
            if (jfc.getSelectedFile() == null) {
                return;
            }
            file = jfc.getSelectedFile();

            try {
                ObjectOutputStream oos = new ObjectOutputStream(new java.io.
                        FileOutputStream(file));

                //oos.writeObject(this);

                saveData(oos);
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {

            }

        } else if (action.equals("Load Data")) {
            if (jfc == null) {
                jfc = new javax.swing.JFileChooser();
            }
            if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
                inputFile = jfc.getSelectedFile();
                if (idata == null) {
                    idata = new InputData(this);
                }

                try {
                    rightTool.add(idata);
                    idata.setSelected(true);
                } catch (Exception e) {}
            }
        } else if (action.equals("Load Weight")) {
            jfc = new javax.swing.JFileChooser();
            if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
                LoadWeight(jfc.getSelectedFile());
                /*
                              if(idata == null)
                 idata = new InputData(this);

                              try{
                 rightTool.add(idata);
                 idata.setSelected(true);
                            }catch(Exception e){}
                 */
            }
        } else if (action.equals("Configure Network")) {
            if (ng == null) {
                ng = new NetworkGenerator(this);
                try {
                    rightTool.add(ng);
                    ng.setSelected(true);
                    //((JMenuItem)ae.getSource()).setEnabled(false);
                } catch (Exception e) {}
            } else {
                JOptionPane.showMessageDialog(this,
                                              "The neural network is already configured.");
            }

        } else if (action.equals("Configure Simulation")) {
            if (simcong == null) {
                simcong = new SimConfigure(this);
            } else {
                simcong.setVisible(true);
            }
            try {
                rightTool.add(simcong);
                simcong.setSelected(true);
            } catch (Exception e) {}
        } else if (action.equals("Task Manager")) {
            if (tm == null) {
                /*
                                 try {
                    UIManager.setLookAndFeel(
                            "javax.swing.plaf.metal.MetalLookAndFeel");

                    UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                 } catch (UnsupportedLookAndFeelException ex1) {
                                 } catch (IllegalAccessException ex1) {
                                 } catch (InstantiationException ex1) {
                                 } catch (ClassNotFoundException ex1) {
                                 }*/
                tm = new TaskManager(this);
            } else {
                tm.setVisible(true);
            }
            try {
                rightTool.add(tm);
                tm.setSelected(true);
            } catch (Exception e) {}
        } else if (action.equals("Run")) {
            //System.out.println(ng);
            if (ng == null && !isInitialized) {
                ng = new NetworkGenerator(this);
                try {
                    rightTool.add(ng);
                    ng.setSelected(true);
                } catch (Exception e) {}
                return ;
            }
            if (ss == null) {
                ss = new ScreenContainer(this);
            } else {
                ss.setVisible(true);
            }
            try {
                if (!isInitialized) {
                    initVOthers();
                }
                rightTool.add(ss);
                ss.setSelected(true);
            } catch (Exception e) {}
        } else if (action.equals("Step Forward")) {
            stepForward();
            mnuForward.setText("Step Backward");
        } else if (action.equals("Step Backward")) {
            stepBackward();
            mnuForward.setText("Step Forward");
        } else if (action.equals("Run N Step")) {
            //mnuForward.setText("Stop");
            new Thread() {
                public void run() {
                    //((JMenuItem)ae.getSource()).setEnabled(false);
                    isRun = true;
                    stop = false;

                    long i = 0;
                    //System.out.println("S");
                    do {
                        stepForward();
                        if (isWaitAndShow) {
                            try {
                                Thread.sleep(speed);
                            } catch (Exception e) {}
                        }
                        i++;
                        //System.out.print("("+i+")");

                    } while (!stop && stepBackward() && i < inum);
                    isRun = false;
                    //System.out.println("E");
                    //System.out.println("  inum:"+inum+"  stop:"+stop);
                    //((JMenuItem)ae.getSource()).setEnabled(true);
                    System.gc();
                }
            }.start();
            //mnuForward.setText("Step Forward");
        } else if (action.equals("Adapt The Input")) {
            //mnuForward.setText("Stop");
            new Thread() {
                public void run() {
                    //((JMenuItem)ae.getSource()).setEnabled(false);
                    stop = false;
                    isRun = true;
                    do {
                        stepForward();
                        if (isWaitAndShow) {
                            try {
                                Thread.sleep(speed);
                            } catch (Exception e) {}
                        }
                    } while (!stop && stepBackward());
                    isRun = false;
                    System.gc();
                    //((JMenuItem)ae.getSource()).setEnabled(true);
                }
            }.start();
            //mnuForward.setText("Step Forward");
        } else if (action.equals("Task Start")) {
            taskStart();
            taskRun();
        } else if (action.equals("Close")) {
            dispose();
        } else if (action.equals("Stop")) {
            stop = true;
            isRun = false;
        } else if (action.equals("Task Start")) {
            taskStart();
        } else if (action.equals("Plot") && isUnlimited) {
            //if(ep == null)
            ep = new ErrorPlot(this, -1, -1);
            //else
            //	ep.setVisible(true);
            try {
                rightTool.add(ep);
                ep.setSelected(true);
            } catch (Exception e) {}

        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Run N Step")) {
            int rest = _T - _t - 10;
            String snum = JOptionPane.showInputDialog(null,
                    "Enter number of maximum iteration: (max " + rest + ")");
            ///enlargeArrays();
            inum = Integer.parseInt(snum);
        }
        doAction(ae.getActionCommand());
    }

    public void LoadWeight(File weightFile) {
        Vector tv = new Vector();
        String s;
        String t[];
        int layer = 0;
        int i, j, l, m, k;
        try {
            RandomAccessFile ras = new RandomAccessFile(weightFile, "r");

            s = ras.readLine();
            t = s.split(" ");

            //Configure Network
            N = t.length;

            W = new double[N][][];
            V = new double[N][];
            dW = new double[2][][][];
            err = new double[_T][][];
            derror = new double[_T];

            dW[0] = new double[N][][];
            dW[1] = new double[N][][];
            for (j = 0; j < _T; j++) {
                err[j] = new double[N][];
            }
            m = 1;

            for (i = 0; i < N - 1; i++) {
                l = Integer.parseInt(t[i]);
                m = Integer.parseInt(t[i + 1]);
                W[i] = new double[l][m];
                V[i] = new double[l];

                dW[0][i] = new double[l][m];
                dW[1][i] = new double[l][m];
                for (j = 0; j < _T; j++) {
                    err[j][i] = new double[l];
                }
            }

            for (j = 0; j < _T; j++) {
                err[j][i] = new double[m];
            }
            W[i] = new double[m][1];
            V[i] = new double[m];
            DO = new double[m];

            //Load Weight Data


            for (i = 0; i < W.length; i++) {
                for (j = 0; j < W[i].length; j++) {
                    if (ras.getFilePointer() < ras.length()) {
                        s = ras.readLine();
                        if (!s.equals("")) {
                            t = s.split(" ");
                            //System.out.println(s+" OK");
                            for (k = 0; k < W[i][j].length; k++) {
                                W[i][j][k] = Double.parseDouble(t[k]);
                            }
                            //System.out.println(s+" ---");
                        }
                    }
                }
            }

            //System.out.println("OK");

            s = vinputs;
            t = s.trim().split("" + Main.inputSeperator);

            for (i = 0; i < t.length; i++) {
                V[0][i] = Double.parseDouble(t[i]);
            }

            k = V.length - 1;
            s = doutputs;
            t = s.trim().split("" + Main.inputSeperator);
            //Desired output
            for (i = 0; i < O; i++) {
                DO[i] = Double.parseDouble(t[i]);
            }

            //		sMain.appMenu.DoSimulate(1);
            /*
                int i,j,k;
                for(i=0; i<sMain.W.length; i++)
             for(j=0; j<sMain.W[i].length; j++)
              for(k=0; k<sMain.W[i][j].length; k++)
               sMain.W[i][j][k] = i+j+k;
             */

            mnuConfigureNetwork.setEnabled(false);
            mnuSimulation.setEnabled(true);

            /*
                         vinputs = (String)((Vector)rowData.get(0)).get(1);
                   doutputs = (String)((Vector)rowData.get(0)).get(2);

                         mnuLoadData.setEnabled(false);
                   mnuConfigureNetwork.setEnabled(true);
                   mnuConfigureSimulation.setEnabled(true);
                   mnuTaskManager.setEnabled(true);
             */

        } catch (Exception e) {
            System.out.println(e);
        }
        //tblInput.invalidate();
    }


    public void taskStart() {
        if (tm != null) {
            taskStart = tm.getNumTasks();
            taskCurrent = 0;
            taskLoopTotal = 0;
            taskLoopCount = 0;
            stop = false;
            isLooping = false;
        }
    }

    /*
      int taskStart;
       int taskCurrent;
       long taskCount;
       long taskLoop;
     */
    /*
      LOOP_START = "Loop Start";
      LOOP_END = "Loop End";
      ADAPT_INPUT = "Adapt The Input";
      SINGLE_STEP = "Single Step";
      N_STEP = "Run N Step";
     */
    public void taskRun() {

        new Thread() {
            public void run() {
                //((JMenuItem)ae.getSource()).setEnabled(false);
                int totalTask = tm.getNumTasks();
                String strTask;
                while (taskCurrent < totalTask && !stop) {
                    strTask = tm.getTask(taskCurrent);
                    if (strTask.equals(tm.LOOP_START)) {
                        if (isLooping) {
                            taskLoopCount++;
                            if (taskLoopCount == taskLoopTotal) {
                                //initiate loop
                                isLooping = true;
                                taskLoopTotal = tm.getTaskValue(taskCurrent);
                                taskLoopCount = 0;
                                taskStart = taskCurrent;
                            } else {
                                taskCurrent = taskStart;
                            }
                        } else {
                            isLooping = true;
                            taskLoopTotal = tm.getTaskValue(taskCurrent);
                            taskLoopCount = 0;
                            taskStart = taskCurrent;
                        }
                    } else if (strTask.equals(tm.LOOP_END)) {
                        if (isLooping) {
                            taskLoopCount++;
                            if (taskLoopCount == taskLoopTotal) {
                                //initiate loop
                                isLooping = false;
                            } else {
                                taskCurrent = taskStart;
                            }
                        }
                    } else if (strTask.equals(tm.ADAPT_INPUT)) {
                        int inindex = tm.getInput(taskCurrent);
                        idata.LoadInputs(inindex - 1);
                        setTitle("ANN (" + strTask + ":" + inindex + ")");
                        doAction(tm.ADAPT_INPUT);
                        do {
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {}
                        } while (isRun);
                    } else if (strTask.equals(tm.N_STEP)) {
                        //System.out.print("<>");
                        int inindex = tm.getInput(taskCurrent);
                        idata.LoadInputs(inindex - 1);

                        inum = tm.getTaskValue(taskCurrent);
                        setTitle("ANN (" + strTask + "[" + inum + "]:" +
                                 inindex + ")");
                        int ltm = (int) (inum / 100);
                        doAction(tm.N_STEP);
                        do {
                            try {
                                Thread.sleep(ltm);
                                //System.out.print("."+isRun);
                            } catch (Exception e) {}
                        } while (isRun);
                        //System.out.print("."+isRun);
                    } else if (strTask.equals(tm.SINGLE_STEP)) {
                        int inindex = tm.getInput(taskCurrent);
                        idata.LoadInputs(inindex - 1);
                        setTitle("ANN (" + strTask + ":" + inindex + ")");
                        doAction(mnuForward.getText());
                        doAction(mnuForward.getText());
                    }
                    //System.out.println("("+taskCurrent+" == Current Task");
                    taskCurrent++;
                }
                //((JMenuItem)ae.getSource()).setEnabled(true);
            }
        }.start();

    }

    public void initVOthers() {
        int i, j;
        for (i = 1; i < V.length; i++) {
            for (j = 0; j < V[i].length; j++) {
                V[i][j] = 0;
            }
        }
    }

    /*
     public void initVInput(){
      int i;
      String s = (String)vinputs;
         String t[] = s.split(""+Main.inputSeperator);
         for(i=0; i<I; i++){
          if(s.charAt(i) == '0')
           V[0][i] = 0.0;
          else
           V[0][i] = 1.0;
         }
     }
     */
    private double function(double x) {
        return 1.0 / (1.0 + (Math.exp( -x)));
    }

    public void stepForward() {
        int i, j, k;
        double o, e;

        for (i = 1; i < W.length; i++) {
            //System.out.println(i+"  "+W[i].length+"   "+W[i-1].length+"   ");
            for (j = 0; j < W[i].length; j++) {
                //System.out.println(W[i-1].length+"---"+j);
                o = 0.0;
                for (k = 0; k < W[i - 1].length; k++) {
                    o += V[i - 1][k] * W[i - 1][k][j];
                }
                V[i][j] = function(o);
            }
        }
        //System.out.println("Successfull");
        ss.redraw();
    }

    public double[][] transpose(double x[][]) {
        double y[][] = new double[x[0].length][x.length];
        int i, j;
        for (i = 0; i < x[0].length; i++) {
            for (j = 0; j < x.length; j++) {
                y[i][j] = x[j][i];
            }
        }
        return y;
    }

    public double[][] transpose(double x[]) {
        double y[][] = new double[1][x.length];
        int j;

        for (j = 0; j < x.length; j++) {

            y[0][j] = x[j];
        }

        return y;
    }

    public double[][] matmul(double x[][], double y[][]) {
        int i, j, k;
        if (x[0].length != y.length) {
            System.out.println("Error mat mul: " + x.length + " x " +
                               y[0].length);
            System.out.println("Continue: " + x[0].length + " != " + y.length);
            return null;
        }
        double z[][] = new double[x.length][y[0].length];
        for (i = 0; i < x.length; i++) {
            for (j = 0; j < y[0].length; j++) {
                z[i][j] = 0;
                for (k = 0; k < x[0].length; k++) {
                    z[i][j] += (x[i][k] * y[k][j]);
                }
            }
        }
        return z;
    }

    public double[][] mats(double x, double y[][], char op) {
        int i, j;

        double z[][] = null;

        try {
            z = new double[y.length][y[0].length];
            if (op == '*') {
                for (i = 0; i < y.length; i++) {
                    for (j = 0; j < y[0].length; j++) {
                        z[i][j] = x * y[i][j];
                    }
                }
            } else if (op == '/') {
                for (i = 0; i < y.length; i++) {
                    for (j = 0; j < y[0].length; j++) {
                        z[i][j] = x / y[i][j];
                    }
                }
            } else if (op == '+') {
                for (i = 0; i < y.length; i++) {
                    for (j = 0; j < y[0].length; j++) {
                        z[i][j] = x + y[i][j];
                    }
                }
            } else if (op == '-') {
                for (i = 0; i < y.length; i++) {
                    for (j = 0; j < y[0].length; j++) {
                        z[i][j] = x - y[i][j];
                    }
                }
            }
        } catch (Exception ee) {
            System.out.println("y:" + y.length);
            System.out.println("y[0]:" + y[0].length);
        }
        return z;
    }

    public double[][] mats(double x[][], double y[][], char op) {
        int i, j;
        double z[][] = new double[y.length][y[0].length];
        if (op == '*') {
            for (i = 0; i < y.length; i++) {
                for (j = 0; j < y[0].length; j++) {
                    z[i][j] = x[i][j] * y[i][j];
                }
            }
        } else if (op == '/') {
            for (i = 0; i < y.length; i++) {
                for (j = 0; j < y[0].length; j++) {
                    z[i][j] = x[i][j] / y[i][j];
                }
            }
        } else if (op == '+') {
            for (i = 0; i < y.length; i++) {
                for (j = 0; j < y[0].length; j++) {
                    z[i][j] = x[i][j] + y[i][j];
                }
            }
        } else if (op == '-') {
            for (i = 0; i < y.length; i++) {
                for (j = 0; j < y[0].length; j++) {
                    z[i][j] = x[i][j] - y[i][j];
                }
            }
        }
        return z;
    }

    public double[] toVec(double x[][]) {
        int i;
        if (x.length > 1) {
            double y[] = new double[x.length];
            for (i = 0; i < x.length; i++) {
                y[i] = x[i][0];
            }
            return y;
        } else {
            double y[] = new double[x[0].length];
            for (i = 0; i < x[0].length; i++) {
                y[i] = x[0][i];
            }
            return y;
        }
    }

public void isInfered(){
    stepForward();
    if(isErrorTolerated()){
        JOptionPane.showMessageDialog(this, "Successful: The Pattern is Infered.", "Inference", JOptionPane.INFORMATION_MESSAGE);
    }else{
        JOptionPane.showMessageDialog(this, "Failure: Pattern doesn't Infered.", "Inference", JOptionPane.ERROR_MESSAGE);
    }
}
    private boolean isErrorTolerated() {
        int i, j, k;
        double Ep;
        double sumEp = 0.0;

        for (i = 0; i < DO.length; i++) {
            Ep = (DO[i] - V[N - 1][i]);
            sumEp += (Ep * Ep);
            /* Calculate the output layer error (step 3 for output cell) */
            err[_tt][N -
                    1][i] = (DO[i] - V[N - 1][i]) * V[N -
                            1][i] * (1.0 - V[N - 1][i]);
            //System.out.println((N-1)+","+i+":  "+err[_t][N-1][i]);
        }
        double ep = Math.sqrt(sumEp) / DO.length;
        if (isUnlimited) {
            derror[_t] = ep;
        }
        return ep < tolerance;
    }

    public boolean stepBackward() {

        double o, e;
        int layer;

        int lastLayer = V.length - 1;

        if (isErrorTolerated()) {
            _t++;
            if (isUnlimited && derror.length <= _t) {
                double _ttt[] = new double[derror.length + _T];
                System.arraycopy(derror, 0, _ttt, 0, derror.length);
                derror = null;
                derror = _ttt;
            }
            return false;
        }

        double tmp[][];

        //for each layer
        for (layer = N - 2; layer >= 0; layer--) {

            /* Calculate the hidden layer error (step 3 for hidden cell) */
            ///ERRR////
            tmp = transpose(transpose(V[layer]));
            err[_tt][layer] =
                    toVec(
                            mats(
                                    mats(
                                            matmul(W[layer],
                    transpose(transpose(err[_tt][layer + 1]))),
                                            tmp, '*'),
                                    mats(1, tmp, '-'),
                                    '*')
                    );

            /* Update the weights for the output layer (step 4 for output cell) */


            dW[0][layer] = mats(
                    mats(alpha, dW[0][layer], '*'),
                    mats(eta,
                         matmul(transpose(transpose(V[layer])),
                                transpose(err[_tt][layer + 1])), '*'), '+');
            //matprint(	dW[_t][layer]);
            //matprint(	W[layer]);

////////////
            W[layer] = mats(W[layer], dW[0][layer], '+');
            //matprint(	W[layer]);
        }

        if (_T > _tt + 10) {
            _tt++;
        }
        _t++;
        //System.out.print(">"+_t);
        if (isUnlimited && derror.length <= _t) {
            double _ttt[] = new double[derror.length + _T];
            System.arraycopy(derror, 0, _ttt, 0, derror.length);
            derror = null;
            derror = _ttt;
        }

        ss.redraw();
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    //private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
