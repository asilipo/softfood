package it.softfood.GUI;

import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import it.softfood.GUI.Login;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class MainView extends FrameView {

    public MainView(SingleFrameApplication app) {
        super(app);

        initComponents();      
        
        Login ordine = new Login(this);
        setComponent(ordine);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        mainPanel.setMaximumSize(new java.awt.Dimension(225, 450));
        mainPanel.setMinimumSize(new java.awt.Dimension(225, 450));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(225, 450));
        mainPanel.setLayout(null);

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
   
}
