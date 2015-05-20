package fsannsim;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.LookAndFeel;

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
public class Main {
    boolean packFrame = false;
    public static final char inputSeperator = ' ';
    public static AppMenu appMenu;
    /**
     * Construct and show the application.
     */
    public Main() {
        appMenu = new AppMenu();
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            appMenu.pack();
        } else {
            appMenu.validate();
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = appMenu.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        appMenu.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        appMenu.setVisible(true);
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    //System.out.println(UIManager.getSystemLookAndFeelClassName());

                    //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

                  UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //new PictureConverter();
                new Main();
            }
        });
    }

    private void jbInit() throws Exception {
    }
}
