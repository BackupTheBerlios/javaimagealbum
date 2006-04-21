/*
 * <license>
 * The contents of this file are subject to the Mozilla Public License 
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License 
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or 
 * implied. See the License for the specific language governing rights 
 * and limitations under the License.
 * 
 * The Original Code is Web Photo Publisher.
 * 
 * The Initial Developer of the Original Code is Mark Roth.  Portions 
 * created by Mark Roth are Copyright (C) 2003 Mark Roth.  
 * All Rights Reserved.
 * 
 * Contributor(s) listed below.
 * </license>
 */

package com.javaimagealbum;

import java.util.*;

import javax.swing.*;

import com.javaimagealbum.resources.ResourceFactory;

/**
 * Allows user to change the order of the files in the list.
 *
 * @author  Mark Roth
 * @author  Mirko Actis Grosso
 */
public class SummaryPanel 
    extends javax.swing.JPanel 
    implements WizardPanel, Observer
{

	/** Default serial version */
	private static final long serialVersionUID = 1L;
	
	static ResourceBundle res = ResourceFactory.getBundle();
    static ResourceBundle resM = ResourceFactory.getMnemonicBundle();

    /** Creates new form ReorderFilesPanel */
    public SummaryPanel(PublishManager publishManager) {
        this.publishManager = publishManager;
        publishManager.addObserver( this );
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblTitle = new javax.swing.JLabel();
        pnlContents = new javax.swing.JPanel();
        tpInstructions = new javax.swing.JTextPane();
        epSummary = new javax.swing.JEditorPane();
        btnGenerate = new javax.swing.JButton();
        lblProgressMessage = new javax.swing.JLabel();
        pbProgress = new javax.swing.JProgressBar();

        setLayout(new java.awt.BorderLayout());

        setBackground(java.awt.Color.white);
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 14));
        lblTitle.setForeground(new java.awt.Color(0, 153, 153));
        lblTitle.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("SUMMARY"));
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        add(lblTitle, java.awt.BorderLayout.NORTH);

        pnlContents.setLayout(new java.awt.GridBagLayout());

        pnlContents.setBackground(java.awt.Color.white);
        tpInstructions.setEditable(false);
        tpInstructions.setFont(new java.awt.Font("SansSerif", 0, 12));
        tpInstructions.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("SUMMARY_PANEL_DESCRIPTION"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlContents.add(tpInstructions, gridBagConstraints);

        epSummary.setEditable(false);
        epSummary.setContentType("text/html");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlContents.add(epSummary, gridBagConstraints);

        btnGenerate.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnGenerate.setMnemonic(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/MnemonicResource").getString("Generate").charAt(0));
        btnGenerate.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("GENERATE"));
        btnGenerate.setActionCommand(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("GENERATE"));
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlContents.add(btnGenerate, gridBagConstraints);

        lblProgressMessage.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblProgressMessage.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("PROGRESS"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlContents.add(lblProgressMessage, gridBagConstraints);

        pbProgress.setForeground(new java.awt.Color(0, 153, 153));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        pnlContents.add(pbProgress, gridBagConstraints);

        add(pnlContents, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if( publishManager.isGenerating() ) {
        btnGenerate.setText( res.getString("GENERATE") );
        btnGenerate.setMnemonic( resM.getString("Generate").charAt(0) );
        publishManager.stopGeneration( res.getString("GENERATION_ABORTED") );
    }
    else {
        btnGenerate.setText( res.getString("ABORT_GENERATION") );
        btnGenerate.setMnemonic( resM.getString("Abort").charAt(0) );
        publishManager.startGeneration();
    }
  }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JEditorPane epSummary;
    private javax.swing.JLabel lblProgressMessage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JProgressBar pbProgress;
    private javax.swing.JPanel pnlContents;
    private javax.swing.JTextPane tpInstructions;
    // End of variables declaration//GEN-END:variables

    private PublishManager publishManager;
//    private File selectedDirectory;
    
    /** Returns true if all required data was filled in for this panel.
     */
    public boolean isSatisfied() {
        return true;
    }

    /** Called when the panel is shown to the user
     */
    public void showPanel() {
        epSummary.setText( publishManager.buildSummary() );
        
        // If the user made it this far, they probably want to generate
        // their photos, so warn if they exit accidentally.
        publishManager.setUnsavedPhotos( true );
    }
    
    /** Called when the panel is hidden from the user
     */
    public void hidePanel() {
    }
    
    public void update(final java.util.Observable p1,final java.lang.Object p2) {
        int progress = publishManager.getGenerationProgress();
        String message = publishManager.getGenerationMessage();
        lblProgressMessage.setText( message );
        pbProgress.setValue( progress );
        
        if( !publishManager.isGenerating() ) {
            btnGenerate.setText( res.getString("GENERATE") );
            btnGenerate.setMnemonic( resM.getString("Generate").charAt(0) );
        }
        
        if( publishManager.isGenerationComplete() ) {
            btnGenerate.setText( res.getString("REGENERATE") );
            btnGenerate.setMnemonic( resM.getString("Regenerate").charAt(0) );
        }
    }
    
    /**
     * Called when the panel is hidden from the user
     *
     * @param forwardDirection True if the panel is being hidden because
     *    we are moving forward.  This allows the wizard panel to do
     *    last minute validation.
     * @exception CannotChangePanelException Thrown if, during last
     *    checks, we cannot change to the next panel.
     */
    public void hidePanel(boolean forwardDirection) 
        throws CannotChangePanelException 
    {
        if( publishManager.isGenerating() ) {
            int result = JOptionPane.showConfirmDialog( this, 
                res.getString("STOP_PUBLISHING") +
                (forwardDirection ? res.getString("GO_FORWARD") : res.getString("GO_BACK")) + "?", 
                res.getString("GENERATION_IN_PROGRESS"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE );
                
            if( result == JOptionPane.NO_OPTION ) {
                throw new CannotChangePanelException();
            }
            else {
                publishManager.stopGeneration( res.getString("GENERATION_ABORTED") );
            }
        }
    }
    
}
