/*
 * <license>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * 
 * The Original Code is Java Image Album.
 * 
 * The Initial Developer of the Original Code is Mirko Actis Grosso.  Portions 
 * created by Mirko Actis Grosso are Copyright (C) 2006 Mirko Actis Grosso.  
 * All Rights Reserved.
 * 
 * Contributor(s) listed below.
 * </license>
 */

package com.javaimagealbum;

import java.util.Locale;
import javax.swing.DefaultComboBoxModel;

/**
 * Allows user to change the order of the files in the list.
 *
 * @author  Mirko Actis Grosso
 */
public class OutputDescriptionPanel extends javax.swing.JPanel implements WizardPanel
{

    /** Default serial version */
	private static final long serialVersionUID = 1L;
	
	/** Creates new form ReorderFilesPanel */
    public OutputDescriptionPanel(PublishManager publishManager) {
        this.publishManager = publishManager;
        initComponents ();
        publishManager.setAlbumTitle( OutputDescriptionStage.loadTitle(publishManager.getPhotoSource().getSourceDir()) );
        publishManager.setAlbumDescription( OutputDescriptionStage.loadDescription(publishManager.getPhotoSource().getSourceDir()) );
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
        pnlAlbumTitleOption = new javax.swing.JPanel();
        lblAlbumTitle = new javax.swing.JLabel();
        tfAlbumTitle = new javax.swing.JTextField();
        lblAlbumDescription = new javax.swing.JLabel();
        taAlbumDescription = new javax.swing.JTextArea();
        pnlCheckboxes = new javax.swing.JPanel();
        pnlDescriptionPage = new javax.swing.JPanel();
        cbDescriptionInEmptyPage = new javax.swing.JCheckBox();
        pnlLinkToAlbumIndex = new javax.swing.JPanel();
        cbLinkToAlbumIndex = new javax.swing.JCheckBox();
        pnlOutLanguage = new javax.swing.JPanel();
        lblOutLanguage = new javax.swing.JLabel();
        coOutLanguage = new javax.swing.JComboBox();
        pnlBroom2 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        setBackground(java.awt.Color.white);
        setFont(new java.awt.Font("SansSerif", 0, 12));
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 14));
        lblTitle.setForeground(new java.awt.Color(0, 153, 153));
        lblTitle.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("OUTPUT_OPTIONS"));
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        add(lblTitle, java.awt.BorderLayout.NORTH);

        pnlContents.setLayout(new java.awt.GridBagLayout());

        pnlContents.setBackground(java.awt.Color.white);
        tpInstructions.setEditable(false);
        tpInstructions.setFont(new java.awt.Font("SansSerif", 0, 12));
        tpInstructions.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("OUTPUT_DESCRIPTIN_PANEL_DESCRIPTION"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlContents.add(tpInstructions, gridBagConstraints);

        pnlAlbumTitleOption.setLayout(new java.awt.GridBagLayout());

        pnlAlbumTitleOption.setBackground(java.awt.Color.white);
        lblAlbumTitle.setDisplayedMnemonic('t');
        lblAlbumTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblAlbumTitle.setLabelFor(tfAlbumTitle);
        lblAlbumTitle.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("ALBUM_TITLE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        pnlAlbumTitleOption.add(lblAlbumTitle, gridBagConstraints);

        tfAlbumTitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        tfAlbumTitle.setText("Photos");
        tfAlbumTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfAlbumTitleKeyPressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        pnlAlbumTitleOption.add(tfAlbumTitle, gridBagConstraints);
        tfAlbumTitle.getAccessibleContext().setAccessibleName("Album Title");

        lblAlbumDescription.setDisplayedMnemonic('d');
        lblAlbumDescription.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblAlbumDescription.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("ALBUM_DESCRIPTION"));
        lblAlbumDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlAlbumTitleOption.add(lblAlbumDescription, new java.awt.GridBagConstraints());

        taAlbumDescription.setFont(new java.awt.Font("SansSerif", 0, 12));
        taAlbumDescription.setLineWrap(true);
        taAlbumDescription.setRows(4);
        taAlbumDescription.setText("Description");
        taAlbumDescription.setToolTipText("");
        taAlbumDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        taAlbumDescription.setCaretPosition(6);
        taAlbumDescription.setMargin(new java.awt.Insets(1, 5, 2, 4));
        taAlbumDescription.setMinimumSize(new java.awt.Dimension(11, 21));
        taAlbumDescription.setPreferredSize(new java.awt.Dimension(440, 100));
        taAlbumDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taAlbumDescriptionKeyPressed(evt);
            }
        });

        pnlAlbumTitleOption.add(taAlbumDescription, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlContents.add(pnlAlbumTitleOption, gridBagConstraints);

        pnlCheckboxes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlCheckboxes.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnlCheckboxes.setMinimumSize(new java.awt.Dimension(300, 120));
        pnlCheckboxes.setOpaque(false);
        pnlCheckboxes.setPreferredSize(new java.awt.Dimension(544, 126));
        pnlDescriptionPage.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlDescriptionPage.setBackground(java.awt.Color.white);
        pnlDescriptionPage.setMaximumSize(new java.awt.Dimension(300, 25));
        pnlDescriptionPage.setMinimumSize(new java.awt.Dimension(300, 25));
        pnlDescriptionPage.setPreferredSize(new java.awt.Dimension(544, 30));
        cbDescriptionInEmptyPage.setBackground(java.awt.Color.white);
        cbDescriptionInEmptyPage.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbDescriptionInEmptyPage.setMnemonic('o');
        cbDescriptionInEmptyPage.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("CREATE_DESCRIPTION_PAGE"));
        cbDescriptionInEmptyPage.setMaximumSize(new java.awt.Dimension(300, 25));
        cbDescriptionInEmptyPage.setMinimumSize(new java.awt.Dimension(300, 25));
        cbDescriptionInEmptyPage.setPreferredSize(new java.awt.Dimension(544, 20));
        cbDescriptionInEmptyPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDescriptionInEmptyPageActionPerformed(evt);
            }
        });

        pnlDescriptionPage.add(cbDescriptionInEmptyPage);

        pnlCheckboxes.add(pnlDescriptionPage);

        pnlLinkToAlbumIndex.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlLinkToAlbumIndex.setBackground(java.awt.Color.white);
        pnlLinkToAlbumIndex.setMaximumSize(new java.awt.Dimension(300, 25));
        pnlLinkToAlbumIndex.setMinimumSize(new java.awt.Dimension(300, 25));
        pnlLinkToAlbumIndex.setPreferredSize(new java.awt.Dimension(544, 30));
        cbLinkToAlbumIndex.setBackground(java.awt.Color.white);
        cbLinkToAlbumIndex.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbLinkToAlbumIndex.setMnemonic('i');
        cbLinkToAlbumIndex.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("LINK_TO_PARENT_FOLDER"));
        cbLinkToAlbumIndex.setToolTipText("Select this option if you plan on uploading more\nthan one photo album to your account.  A link\nto the parent folder will be included in the output.");
        cbLinkToAlbumIndex.setMaximumSize(new java.awt.Dimension(300, 25));
        cbLinkToAlbumIndex.setMinimumSize(new java.awt.Dimension(300, 25));
        cbLinkToAlbumIndex.setPreferredSize(new java.awt.Dimension(544, 20));
        cbLinkToAlbumIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLinkToAlbumIndexActionPerformed(evt);
            }
        });

        pnlLinkToAlbumIndex.add(cbLinkToAlbumIndex);

        pnlCheckboxes.add(pnlLinkToAlbumIndex);

        pnlOutLanguage.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlOutLanguage.setBackground(java.awt.Color.white);
        pnlOutLanguage.setMaximumSize(new java.awt.Dimension(300, 25));
        pnlOutLanguage.setMinimumSize(new java.awt.Dimension(300, 25));
        pnlOutLanguage.setPreferredSize(new java.awt.Dimension(544, 30));
        lblOutLanguage.setBackground(new java.awt.Color(255, 255, 255));
        lblOutLanguage.setDisplayedMnemonic('m');
        lblOutLanguage.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblOutLanguage.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("OUTPUT_LANGUAGE"));
        lblOutLanguage.setMaximumSize(new java.awt.Dimension(400, 16));
        pnlOutLanguage.add(lblOutLanguage);

        coOutLanguage.setFont(new java.awt.Font("SansSerif", 0, 12));
        coOutLanguage.setModel(new DefaultComboBoxModel( Constants.listLocaleForOutput ));
        coOutLanguage.setMinimumSize(new java.awt.Dimension(120, 20));
        coOutLanguage.setPreferredSize(new java.awt.Dimension(120, 20));
        coOutLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coOutLanguageActionPerformed(evt);
            }
        });

        pnlOutLanguage.add(coOutLanguage);

        pnlCheckboxes.add(pnlOutLanguage);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlContents.add(pnlCheckboxes, gridBagConstraints);

        pnlBroom2.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        pnlContents.add(pnlBroom2, gridBagConstraints);

        add(pnlContents, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void taAlbumDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taAlbumDescriptionKeyPressed
        publishManager.setUnsavedDescriptions(true);
        scanAndPersistOptions();
    }//GEN-LAST:event_taAlbumDescriptionKeyPressed

    private void tfAlbumTitleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAlbumTitleKeyPressed
        publishManager.setUnsavedDescriptions(true);
        scanAndPersistOptions();
    }//GEN-LAST:event_tfAlbumTitleKeyPressed

    private void coOutLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coOutLanguageActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_coOutLanguageActionPerformed

    private void cbDescriptionInEmptyPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescriptionInEmptyPageActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbDescriptionInEmptyPageActionPerformed

    private void cbLinkToAlbumIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLinkToAlbumIndexActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbLinkToAlbumIndexActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbDescriptionInEmptyPage;
    private javax.swing.JCheckBox cbLinkToAlbumIndex;
    private javax.swing.JComboBox coOutLanguage;
    private javax.swing.JLabel lblAlbumDescription;
    private javax.swing.JLabel lblAlbumTitle;
    private javax.swing.JLabel lblOutLanguage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAlbumTitleOption;
    private javax.swing.JPanel pnlBroom2;
    private javax.swing.JPanel pnlCheckboxes;
    private javax.swing.JPanel pnlContents;
    private javax.swing.JPanel pnlDescriptionPage;
    private javax.swing.JPanel pnlLinkToAlbumIndex;
    private javax.swing.JPanel pnlOutLanguage;
    private javax.swing.JTextArea taAlbumDescription;
    private javax.swing.JTextField tfAlbumTitle;
    private javax.swing.JTextPane tpInstructions;
    // End of variables declaration//GEN-END:variables

    private PublishManager publishManager;
//    private File selectedDirectory;
    
    /** Returns true if all required data was filled in for this panel.
     */
    public boolean isSatisfied() {
        return true;
    }
    
    /** 
     * Called when the panel is shown to the user
     */
    public void showPanel() {
        tfAlbumTitle.setText( publishManager.getAlbumTitle() );
        taAlbumDescription.setText( publishManager.getAlbumDescription() );
        cbDescriptionInEmptyPage.setSelected( publishManager.getDescriptionInEmptyPage() );
        cbLinkToAlbumIndex.setSelected( publishManager.getLinkToAlbumIndex() );
    }

    /** 
     * Called when the panel is hidden from the user
     */
    public void hidePanel(boolean forwardDirection) 
//        throws CannotChangePanelException 
    {
        scanAndPersistOptions();
    }
    
    private void scanAndPersistOptions() {
        publishManager.setAlbumTitle( tfAlbumTitle.getText() );
        publishManager.setAlbumDescription( taAlbumDescription.getText() );
        publishManager.setDescriptionInEmptyPage( cbDescriptionInEmptyPage.isSelected() );
        publishManager.setLinkToAlbumIndex( cbLinkToAlbumIndex.isSelected() );
        publishManager.setOutputLanguage( (Locale) coOutLanguage.getSelectedItem() );
        //System.out.println("Language selected="+((Locale)coOutLanguage.getSelectedItem()));
        
        publishManager.persistOutputSettings();
    }
}

