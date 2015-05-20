package fsannsim;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SimConfigure extends JInternalFrame implements ActionListener, Serializable{
	JTextField txteta;
	JTextField txtalpha;
	JTextField txttolerance;
	JTextField txtspeed;

	JButton btnSave;
	JButton btnReset;
	JButton btnClose;

	JCheckBox chkNeuron;
	JCheckBox chkValue;
	JCheckBox chkWeight;
	JCheckBox chkWaitAndShow;
	JCheckBox chkSynapse;
	JCheckBox chkUnlimited;
        JCheckBox chkColorNetwork;
        JCheckBox chkColorPlot;

	Simulator sMain;

	SimConfigure(Simulator sMain){
		super("Simulation Configuration", false, true, true, true);
		this.sMain = sMain;
		txteta = new JTextField("");
		txtalpha = new JTextField("");
		txttolerance = new JTextField("");
		txtspeed = new JTextField("");

		txteta.setText(sMain.eta+"");
		txtalpha.setText(sMain.alpha+"");
		txtspeed.setText(sMain.speed+"");
		txttolerance.setText(sMain.tolerance+"");

		chkNeuron		= new JCheckBox("Show Neurons", sMain.isNeuron);
		chkValue		= new JCheckBox("Show Values", sMain.isValue);
		chkWeight		= new JCheckBox("Show Weights", sMain.isWeight);
		chkWaitAndShow	= new JCheckBox("Wait In Iteration ", sMain.isWaitAndShow);
		chkSynapse		= new JCheckBox("Synapsis", sMain.isSynapse);
		chkUnlimited	= new JCheckBox("Record The Errors", sMain.isUnlimited);
                chkColorNetwork	= new JCheckBox("Color Network", sMain.isColorNetwork);
                chkColorPlot	= new JCheckBox("Color Plot", sMain.isColorPlot);


		JPanel pnC = new JPanel();
		pnC.setLayout(new GridLayout(8,2,1,2));
		pnC.add(new JLabel("Learning Rate (ita): "));
		pnC.add(txteta);
		pnC.add(new JLabel("Momentum Co-efficient (alpha): "));
		pnC.add(txtalpha);
		pnC.add(new JLabel("Tolerance Threshold: "));
		pnC.add(txttolerance);
		pnC.add(new JLabel("Simulation Speed: "));
		pnC.add(txtspeed);
		pnC.add(chkNeuron);
		pnC.add(chkValue);
		pnC.add(chkWeight);
		pnC.add(chkWaitAndShow);
		pnC.add(chkSynapse);
		pnC.add(chkUnlimited);
                pnC.add(chkColorNetwork);
                pnC.add(chkColorPlot);

		JLabel lblCap = new JLabel("Simulation Configuration");
		lblCap.setFont(new Font("Arial",3,30));
		lblCap.setBackground(new Color(255,255,255));
		lblCap.setForeground(new Color(155,155,255));
		lblCap.setOpaque(true);

		btnSave = new JButton("Save");
		btnReset = new JButton("Load");
		btnClose = new JButton("Close");

		btnSave.addActionListener(this);
		btnReset.addActionListener(this);
		btnClose.addActionListener(this);



		JPanel pnD = new JPanel();
		pnD.setLayout(new FlowLayout());

		pnD.add(btnSave);
		pnD.add(btnReset);
		pnD.add(btnClose);

		getContentPane().add(lblCap, BorderLayout.NORTH);
		getContentPane().add(pnC, BorderLayout.CENTER);
		getContentPane().add(pnD, BorderLayout.SOUTH);
		getContentPane().add(new JLabel("  "), BorderLayout.WEST);

		//setSize(300,200);
		pack();
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == btnSave){
			sMain.eta = Double.parseDouble(txteta.getText());
			sMain.alpha = Double.parseDouble(txtalpha.getText());
			sMain.speed = Integer.parseInt(txtspeed.getText());
			sMain.tolerance = Double.parseDouble(txttolerance.getText());

			if(!sMain.isUnlimited && chkUnlimited.isSelected())
				sMain._t = 0;

			sMain.isNeuron		= chkNeuron.isSelected();
			sMain.isValue		= chkValue.isSelected();
			sMain.isWeight		= chkWeight.isSelected();
			sMain.isWaitAndShow	= chkWaitAndShow.isSelected();
			sMain.isSynapse		= chkSynapse.isSelected();
			sMain.isUnlimited	= chkUnlimited.isSelected();
                        sMain.isColorNetwork	= chkColorNetwork.isSelected();
                        sMain.isColorPlot	= chkColorPlot.isSelected();

		}
		else if(ae.getSource() == btnReset){
			txteta.setText(sMain.eta+"");
			txtalpha.setText(sMain.alpha+"");
			txtspeed.setText(sMain.speed+"");
			txttolerance.setText(sMain.tolerance+"");

			chkNeuron.setSelected(sMain.isNeuron);
			chkValue.setSelected(sMain.isValue);
			chkWeight.setSelected(sMain.isWeight);
			chkWaitAndShow.setSelected(sMain.isWaitAndShow);
			chkSynapse.setSelected(sMain.isSynapse);
			chkUnlimited.setSelected(sMain.isUnlimited);
                        chkColorNetwork.setSelected(sMain.isColorNetwork);
                        chkColorPlot.setSelected(sMain.isColorPlot);
		}
		else
		{
			dispose();
		}
	}
}
