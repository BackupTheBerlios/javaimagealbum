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

import com.javaimagealbum.util.BrowserControl;

/**
 * Introduces the user to the program
 *
 * @author  Mark Roth
 * @author  Mirko Actis
 */
public class IntroPanel 
    extends javax.swing.JPanel 
    implements WizardPanel
{

    /** Creates new form IntroPanel */
    public IntroPanel() {
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlDescription = new javax.swing.JPanel();
        tpDescription = new javax.swing.JTextPane();
        pnlButtons = new javax.swing.JPanel();
        btnWebSite = new javax.swing.JButton();
        btnMailingList = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        cbSkipIntro = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        setBackground(java.awt.Color.white);
        pnlTitle.setLayout(new javax.swing.BoxLayout(pnlTitle, javax.swing.BoxLayout.Y_AXIS));

        pnlTitle.setBackground(java.awt.Color.white);
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 14));
        lblTitle.setForeground(new java.awt.Color(0, 153, 153));
        lblTitle.setText("Java Image Album");
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlTitle.add(lblTitle);

        add(pnlTitle, java.awt.BorderLayout.NORTH);

        pnlDescription.setLayout(new java.awt.BorderLayout());

        pnlDescription.setBackground(java.awt.Color.white);
        pnlDescription.setAlignmentX(0.0F);
        pnlDescription.setMaximumSize(new java.awt.Dimension(132, 25));
        pnlDescription.setMinimumSize(new java.awt.Dimension(132, 25));
        pnlDescription.setPreferredSize(new java.awt.Dimension(132, 25));
        tpDescription.setEditable(false);
        tpDescription.setFont(new java.awt.Font("SansSerif", 0, 12));
        tpDescription.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("INTRO_PANEL_DESCRIPTION"));
        tpDescription.setMinimumSize(new java.awt.Dimension(6, 2));
        tpDescription.setPreferredSize(new java.awt.Dimension(400, 2));
        tpDescription.setRequestFocusEnabled(false);
        pnlDescription.add(tpDescription, java.awt.BorderLayout.CENTER);

        pnlButtons.setBackground(java.awt.Color.white);
        btnWebSite.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("WEB_SITE"));
        btnWebSite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnWebSiteMouseClicked(evt);
            }
        });

        pnlButtons.add(btnWebSite);

        btnMailingList.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("MAILING_LIST"));
        btnMailingList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMailingListMouseClicked(evt);
            }
        });

        pnlButtons.add(btnMailingList);

        pnlDescription.add(pnlButtons, java.awt.BorderLayout.SOUTH);

        add(pnlDescription, java.awt.BorderLayout.CENTER);

        pnlBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        pnlBottom.setBackground(java.awt.Color.white);
        cbSkipIntro.setBackground(java.awt.Color.white);
        cbSkipIntro.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbSkipIntro.setMnemonic('d');
        cbSkipIntro.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("DONT_SHOW_INTRO"));
        cbSkipIntro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSkipIntroActionPerformed(evt);
            }
        });

        pnlBottom.add(cbSkipIntro);

        add(pnlBottom, java.awt.BorderLayout.SOUTH);

    }// </editor-fold>//GEN-END:initComponents

    private void btnMailingListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMailingListMouseClicked
        BrowserControl.displayURL("http://developer.berlios.de/mail/?group_id=5725");
    }//GEN-LAST:event_btnMailingListMouseClicked

    private void btnWebSiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWebSiteMouseClicked
        BrowserControl.displayURL("http://javaimagealbum.berlios.de");
    }//GEN-LAST:event_btnWebSiteMouseClicked

    private void cbSkipIntroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSkipIntroActionPerformed
        Settings.getInstance().setProperty( Constants.SKIP_INTRO, 
            "" + cbSkipIntro.isSelected() );
    }//GEN-LAST:event_cbSkipIntroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMailingList;
    private javax.swing.JButton btnWebSite;
    private javax.swing.JCheckBox cbSkipIntro;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlDescription;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTextPane tpDescription;
    // End of variables declaration//GEN-END:variables

    /** Returns true if all required data was filled in for this panel.
     */
    public boolean isSatisfied() {
        return true;
    }

    /** Called when the panel is shown to the user
     */
    public void showPanel() {
    }

    /** Called when the panel is hidden from the user
     */
    public void hidePanel(boolean forwardDirection) throws CannotChangePanelException {
    }
}
