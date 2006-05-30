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

import java.util.ArrayList;
import javax.swing.*;

/**
 * Allows user to change the order of the files in the list.
 *
 * @author  Mark Roth
 * @author  Mirko Actis
 */
public class OutputOptionsPanel extends javax.swing.JPanel implements WizardPanel {
    
    /** Default serial version */
    private static final long serialVersionUID = 1L;
    
    /** Creates new form ReorderFilesPanel */
    public OutputOptionsPanel(PublishManager publishManager) {
        this.publishManager = publishManager;
        initComponents();
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
        pnlOutputColumnsOption = new javax.swing.JPanel();
        lblColumns = new javax.swing.JLabel();
        tfColumns = new javax.swing.JTextField();
        pnlThumbnailCountOption = new javax.swing.JPanel();
        lblThumbnailsPerPage = new javax.swing.JLabel();
        coThumbnailsPerPage = new javax.swing.JComboBox();
        pnlCheckboxes = new javax.swing.JPanel();
        pnlSaveCaptionsOption = new javax.swing.JPanel();
        cbSaveCaptions = new javax.swing.JCheckBox();
        pnlResizeOption = new javax.swing.JPanel();
        cbResizeAll = new javax.swing.JCheckBox();
        tfResizeWidth = new javax.swing.JTextField();
        lblBy = new javax.swing.JLabel();
        tfResizeHeight = new javax.swing.JTextField();
        pnlPortraitResizeOption = new javax.swing.JPanel();
        cbResizePortraits = new javax.swing.JCheckBox();
        tfResizePortraitsWidth = new javax.swing.JTextField();
        lblBy2 = new javax.swing.JLabel();
        tfResizePortraitsHeight = new javax.swing.JTextField();
        pnlKeepFullSizeOption = new javax.swing.JPanel();
        cbFullSize = new javax.swing.JCheckBox();
        pnShowExif = new javax.swing.JPanel();
        cbShowExif = new javax.swing.JCheckBox();
        pnlExifInSeparatePage = new javax.swing.JPanel();
        cbExifInSeparatePage = new javax.swing.JCheckBox();
        pnlDetailPages = new javax.swing.JPanel();
        lblCaptionPosition = new javax.swing.JLabel();
        cbCaptionPosition = new javax.swing.JComboBox();
        lblCaptionAlign = new javax.swing.JLabel();
        cbCaptionAlign = new javax.swing.JComboBox();
        lblPhotoPosition = new javax.swing.JLabel();
        cbPhotoPosition = new javax.swing.JComboBox();
        lblNavButtonPosition = new javax.swing.JLabel();
        cbNavButtonPosition = new javax.swing.JComboBox();
        pnlBroom = new javax.swing.JPanel();
        pnlBroom2 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        setBackground(java.awt.Color.white);
        setFont(new java.awt.Font("SansSerif", 0, 12));
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 14));
        lblTitle.setForeground(new java.awt.Color(0, 153, 153));
        lblTitle.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("OUTPUT_OPTIONS1"));
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        add(lblTitle, java.awt.BorderLayout.NORTH);

        pnlContents.setLayout(new java.awt.GridBagLayout());

        pnlContents.setBackground(java.awt.Color.white);
        tpInstructions.setEditable(false);
        tpInstructions.setFont(new java.awt.Font("SansSerif", 0, 12));
        tpInstructions.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("OUTPUT_OPTIONS_PANEL_DESCRIPTION"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlContents.add(tpInstructions, gridBagConstraints);

        pnlOutputColumnsOption.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlOutputColumnsOption.setBackground(java.awt.Color.white);
        lblColumns.setDisplayedMnemonic('n');
        lblColumns.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblColumns.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("NUMBER_OF_COLUMS"));
        pnlOutputColumnsOption.add(lblColumns);

        tfColumns.setColumns(2);
        tfColumns.setFont(new java.awt.Font("SansSerif", 0, 12));
        tfColumns.setText("4");
        tfColumns.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pnlOutputColumnsOption.add(tfColumns);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlContents.add(pnlOutputColumnsOption, gridBagConstraints);

        pnlThumbnailCountOption.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlThumbnailCountOption.setBackground(java.awt.Color.white);
        lblThumbnailsPerPage.setDisplayedMnemonic('m');
        lblThumbnailsPerPage.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblThumbnailsPerPage.setLabelFor(coThumbnailsPerPage);
        lblThumbnailsPerPage.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("MAX_THUMBNAILS"));
        pnlThumbnailCountOption.add(lblThumbnailsPerPage);

        coThumbnailsPerPage.setBackground(java.awt.Color.lightGray);
        coThumbnailsPerPage.setEditable(true);
        coThumbnailsPerPage.setFont(new java.awt.Font("SansSerif", 0, 12));
        coThumbnailsPerPage.setModel(new ThumbnailsComboBoxModel());
        pnlThumbnailCountOption.add(coThumbnailsPerPage);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlContents.add(pnlThumbnailCountOption, gridBagConstraints);

        pnlCheckboxes.setLayout(new java.awt.GridBagLayout());

        pnlCheckboxes.setOpaque(false);
        pnlSaveCaptionsOption.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlSaveCaptionsOption.setBackground(java.awt.Color.white);
        cbSaveCaptions.setBackground(java.awt.Color.white);
        cbSaveCaptions.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbSaveCaptions.setMnemonic('c');
        cbSaveCaptions.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("REMEMBER_CAPTIONS"));
        cbSaveCaptions.setToolTipText("Store captions in text files in source folder");
        cbSaveCaptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSaveCaptionsActionPerformed(evt);
            }
        });

        pnlSaveCaptionsOption.add(cbSaveCaptions);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlCheckboxes.add(pnlSaveCaptionsOption, gridBagConstraints);

        pnlResizeOption.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlResizeOption.setBackground(java.awt.Color.white);
        cbResizeAll.setBackground(java.awt.Color.white);
        cbResizeAll.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbResizeAll.setMnemonic('o');
        cbResizeAll.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("RESIZE_PHOTOS"));
        cbResizeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbResizeAllActionPerformed(evt);
            }
        });

        pnlResizeOption.add(cbResizeAll);

        tfResizeWidth.setFont(new java.awt.Font("SansSerif", 0, 12));
        tfResizeWidth.setText("640");
        tfResizeWidth.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tfResizeWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfResizeWidthKeyReleased(evt);
            }
        });

        pnlResizeOption.add(tfResizeWidth);

        lblBy.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblBy.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("BY"));
        pnlResizeOption.add(lblBy);

        tfResizeHeight.setFont(new java.awt.Font("SansSerif", 0, 12));
        tfResizeHeight.setText("480");
        tfResizeHeight.setMargin(new java.awt.Insets(2, 2, 2, 2));
        tfResizeHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfResizeHeightKeyReleased(evt);
            }
        });

        pnlResizeOption.add(tfResizeHeight);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlCheckboxes.add(pnlResizeOption, gridBagConstraints);

        pnlPortraitResizeOption.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlPortraitResizeOption.setBackground(java.awt.Color.white);
        cbResizePortraits.setBackground(java.awt.Color.white);
        cbResizePortraits.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbResizePortraits.setMnemonic('p');
        cbResizePortraits.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("RESIZE_PORTRAIT"));
        cbResizePortraits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbResizePortraitsActionPerformed(evt);
            }
        });

        pnlPortraitResizeOption.add(cbResizePortraits);

        tfResizePortraitsWidth.setFont(new java.awt.Font("SansSerif", 0, 12));
        tfResizePortraitsWidth.setText("480");
        tfResizePortraitsWidth.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pnlPortraitResizeOption.add(tfResizePortraitsWidth);

        lblBy2.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblBy2.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("BY"));
        pnlPortraitResizeOption.add(lblBy2);

        tfResizePortraitsHeight.setFont(new java.awt.Font("SansSerif", 0, 12));
        tfResizePortraitsHeight.setText("640");
        tfResizePortraitsHeight.setMargin(new java.awt.Insets(2, 2, 2, 2));
        pnlPortraitResizeOption.add(tfResizePortraitsHeight);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 5, 0);
        pnlCheckboxes.add(pnlPortraitResizeOption, gridBagConstraints);

        pnlKeepFullSizeOption.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlKeepFullSizeOption.setBackground(java.awt.Color.white);
        cbFullSize.setBackground(java.awt.Color.white);
        cbFullSize.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbFullSize.setMnemonic('h');
        cbFullSize.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("LINK_FULL_SIZE"));
        cbFullSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFullSizeActionPerformed(evt);
            }
        });

        pnlKeepFullSizeOption.add(cbFullSize);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 5, 0);
        pnlCheckboxes.add(pnlKeepFullSizeOption, gridBagConstraints);

        pnShowExif.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnShowExif.setBackground(java.awt.Color.white);
        cbShowExif.setBackground(java.awt.Color.white);
        cbShowExif.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbShowExif.setMnemonic('i');
        cbShowExif.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("PRINT_EXIF"));
        cbShowExif.setToolTipText("Select this option if you whant to have EXIF information printed in your web page.");
        cbShowExif.setPreferredSize(new java.awt.Dimension(269, 25));
        cbShowExif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowExifActionPerformed(evt);
            }
        });

        pnShowExif.add(cbShowExif);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlCheckboxes.add(pnShowExif, gridBagConstraints);

        pnlExifInSeparatePage.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlExifInSeparatePage.setBackground(java.awt.Color.white);
        cbExifInSeparatePage.setBackground(java.awt.Color.white);
        cbExifInSeparatePage.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbExifInSeparatePage.setMnemonic('h');
        cbExifInSeparatePage.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("EXIF_IN_SEPARATE_PAGE"));
        cbExifInSeparatePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExifInSeparatePageActionPerformed(evt);
            }
        });

        pnlExifInSeparatePage.add(cbExifInSeparatePage);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 5, 0);
        pnlCheckboxes.add(pnlExifInSeparatePage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlContents.add(pnlCheckboxes, gridBagConstraints);

        pnlDetailPages.setLayout(new java.awt.GridBagLayout());

        pnlDetailPages.setBorder(javax.swing.BorderFactory.createTitledBorder(null, java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("DETAIL_PAGE"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12)));
        pnlDetailPages.setFont(new java.awt.Font("SansSerif", 0, 12));
        pnlDetailPages.setOpaque(false);
        lblCaptionPosition.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblCaptionPosition.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("CAPTION_POSITION"));
        lblCaptionPosition.setMaximumSize(new java.awt.Dimension(150, 16));
        lblCaptionPosition.setPreferredSize(new java.awt.Dimension(140, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        pnlDetailPages.add(lblCaptionPosition, gridBagConstraints);

        cbCaptionPosition.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbCaptionPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Below Photo", "Above Photo" }));
        cbCaptionPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCaptionPositionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlDetailPages.add(cbCaptionPosition, gridBagConstraints);

        lblCaptionAlign.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblCaptionAlign.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("CAPTION_ALIGN"));
        lblCaptionAlign.setMaximumSize(new java.awt.Dimension(150, 16));
        lblCaptionAlign.setPreferredSize(new java.awt.Dimension(140, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        pnlDetailPages.add(lblCaptionAlign, gridBagConstraints);

        cbCaptionAlign.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbCaptionAlign.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left", "Center", "Right" }));
        cbCaptionAlign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCaptionAlignActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlDetailPages.add(cbCaptionAlign, gridBagConstraints);

        lblPhotoPosition.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblPhotoPosition.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("PHOTO_POSITION"));
        lblPhotoPosition.setMaximumSize(new java.awt.Dimension(150, 16));
        lblPhotoPosition.setPreferredSize(new java.awt.Dimension(140, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        pnlDetailPages.add(lblPhotoPosition, gridBagConstraints);

        cbPhotoPosition.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbPhotoPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left", "Center", "Right" }));
        cbPhotoPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPhotoPositionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlDetailPages.add(cbPhotoPosition, gridBagConstraints);

        lblNavButtonPosition.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblNavButtonPosition.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("NAV_BUTTON_POSITION"));
        lblNavButtonPosition.setMaximumSize(new java.awt.Dimension(150, 16));
        lblNavButtonPosition.setPreferredSize(new java.awt.Dimension(140, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        pnlDetailPages.add(lblNavButtonPosition, gridBagConstraints);

        cbNavButtonPosition.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbNavButtonPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Below Photo", "Above Photo" }));
        cbNavButtonPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNavButtonPositionActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlDetailPages.add(cbNavButtonPosition, gridBagConstraints);

        pnlBroom.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        pnlDetailPages.add(pnlBroom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        pnlContents.add(pnlDetailPages, gridBagConstraints);

        pnlBroom2.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        pnlContents.add(pnlBroom2, gridBagConstraints);

        add(pnlContents, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void cbExifInSeparatePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExifInSeparatePageActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_cbExifInSeparatePageActionPerformed

    private void cbNavButtonPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNavButtonPositionActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbNavButtonPositionActionPerformed
    
    private void cbCaptionAlignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCaptionAlignActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbCaptionAlignActionPerformed
    
    private void cbPhotoPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPhotoPositionActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbPhotoPositionActionPerformed
    
    private void cbCaptionPositionActionPerformed(java.awt.event.ActionEvent evt) {
        scanAndPersistOptions();
    }
    
    private void cbShowExifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowExifActionPerformed
        enableExifComponents();
        scanAndPersistOptions();
    }//GEN-LAST:event_cbShowExifActionPerformed
    
    private void cbFullSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFullSizeActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbFullSizeActionPerformed
    
    private void cbSaveCaptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSaveCaptionsActionPerformed
        scanAndPersistOptions();
    }//GEN-LAST:event_cbSaveCaptionsActionPerformed
    
  private void tfResizeHeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfResizeHeightKeyReleased
      tfResizePortraitsWidth.setText( tfResizeHeight.getText() );
  }//GEN-LAST:event_tfResizeHeightKeyReleased
  
  private void tfResizeWidthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfResizeWidthKeyReleased
      tfResizePortraitsHeight.setText( tfResizeWidth.getText() );
  }//GEN-LAST:event_tfResizeWidthKeyReleased
  
  private void cbResizePortraitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbResizePortraitsActionPerformed
      enableResizeComponents();
      scanAndPersistOptions();
  }//GEN-LAST:event_cbResizePortraitsActionPerformed
  
  private void cbResizeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbResizeAllActionPerformed
      enableResizeComponents();
      scanAndPersistOptions();
  }//GEN-LAST:event_cbResizeAllActionPerformed
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCaptionAlign;
    private javax.swing.JComboBox cbCaptionPosition;
    private javax.swing.JCheckBox cbExifInSeparatePage;
    private javax.swing.JCheckBox cbFullSize;
    private javax.swing.JCheckBox cbFullSize1;
    private javax.swing.JComboBox cbNavButtonPosition;
    private javax.swing.JComboBox cbPhotoPosition;
    private javax.swing.JCheckBox cbResizeAll;
    private javax.swing.JCheckBox cbResizePortraits;
    private javax.swing.JCheckBox cbSaveCaptions;
    private javax.swing.JCheckBox cbShowExif;
    private javax.swing.JComboBox coThumbnailsPerPage;
    private javax.swing.JLabel lblBy;
    private javax.swing.JLabel lblBy2;
    private javax.swing.JLabel lblCaptionAlign;
    private javax.swing.JLabel lblCaptionPosition;
    private javax.swing.JLabel lblColumns;
    private javax.swing.JLabel lblNavButtonPosition;
    private javax.swing.JLabel lblPhotoPosition;
    private javax.swing.JLabel lblThumbnailsPerPage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnShowExif;
    private javax.swing.JPanel pnlBroom;
    private javax.swing.JPanel pnlBroom2;
    private javax.swing.JPanel pnlCheckboxes;
    private javax.swing.JPanel pnlContents;
    private javax.swing.JPanel pnlDetailPages;
    private javax.swing.JPanel pnlExifInSeparatePage;
    private javax.swing.JPanel pnlKeepFullSizeOption;
    private javax.swing.JPanel pnlOutputColumnsOption;
    private javax.swing.JPanel pnlPortraitResizeOption;
    private javax.swing.JPanel pnlResizeOption;
    private javax.swing.JPanel pnlSaveCaptionsOption;
    private javax.swing.JPanel pnlThumbnailCountOption;
    private javax.swing.JTextField tfColumns;
    private javax.swing.JTextField tfResizeHeight;
    private javax.swing.JTextField tfResizePortraitsHeight;
    private javax.swing.JTextField tfResizePortraitsWidth;
    private javax.swing.JTextField tfResizeWidth;
    private javax.swing.JTextPane tpInstructions;
    // End of variables declaration//GEN-END:variables
    
    private PublishManager publishManager;
//    private File selectedDirectory;
    private static ArrayList photoPositions = new ArrayList();
    static {
        photoPositions.add( Constants.PHOTO_POSITION_LEFT );
        photoPositions.add( Constants.PHOTO_POSITION_CENTER );
        photoPositions.add( Constants.PHOTO_POSITION_RIGHT );
    }
    private static ArrayList captionPositions = new ArrayList();
    static {
        captionPositions.add( Constants.CAPTION_POSITION_BELOW );
        captionPositions.add( Constants.CAPTION_POSITION_ABOVE );
    }
    private static ArrayList captionAligns = new ArrayList();
    static {
        captionAligns.add( Constants.CAPTION_ALIGN_LEFT);
        captionAligns.add( Constants.CAPTION_ALIGN_CENTER);
        captionAligns.add( Constants.CAPTION_ALIGN_RIGHT);
    }
    private static ArrayList navButtonPositions = new ArrayList();
    static {
        navButtonPositions.add( Constants.NAV_BUTTON_POSITION_BELOW );
        navButtonPositions.add( Constants.NAV_BUTTON_POSITION_ABOVE );
    }
    
    /** 
     * Returns true if all required data was filled in for this panel.
     */
    public boolean isSatisfied() {
        return publishManager.getOutputDirectory() != null;
    }
    
    private void enableResizeComponents() {
        boolean resizeAll = cbResizeAll.isSelected();
        tfResizeWidth.setEnabled( resizeAll );
        tfResizeHeight.setEnabled( resizeAll );
        cbResizePortraits.setEnabled( resizeAll );
        boolean resizePortraits = cbResizePortraits.isSelected();
        tfResizePortraitsWidth.setEnabled( resizeAll & resizePortraits );
        tfResizePortraitsHeight.setEnabled( resizeAll & resizePortraits );
        cbFullSize.setEnabled( resizeAll );
    }
    
    private void enableExifComponents() {
        boolean printExif = cbShowExif.isSelected();
        cbExifInSeparatePage.setEnabled( printExif );
    }
    
    /**
     * Called when the panel is shown to the user
     */
    public void showPanel() {
        tfColumns.setText( "" + publishManager.getOutputColumns() );
        coThumbnailsPerPage.setSelectedItem(
                publishManager.getThumbnailsPerPage() );
        cbResizeAll.setSelected( publishManager.getResizeAll() );
        tfResizeWidth.setText( "" + publishManager.getResizeAllWidth() );
        tfResizeHeight.setText( "" + publishManager.getResizeAllHeight() );
        cbResizePortraits.setSelected( publishManager.getResizePortraits() );
        tfResizePortraitsWidth.setText( "" +
                publishManager.getResizePortraitsWidth() );
        tfResizePortraitsHeight.setText( "" +
                publishManager.getResizePortraitsHeight() );
        cbSaveCaptions.setSelected( publishManager.getStoreCaptions() );
        cbFullSize.setSelected( publishManager.getPublishFullSize() );
        cbShowExif.setSelected( publishManager.getShowExif() );
        cbExifInSeparatePage.setSelected( publishManager.getExifInSeparatePage() );
        String captionPosition = publishManager.getCaptionPosition();
        cbCaptionPosition.setSelectedIndex( captionPositions.indexOf(
                captionPosition ) );
        String captionAlign = publishManager.getCaptionAlign();
        cbCaptionAlign.setSelectedIndex( captionAligns.indexOf(
                captionAlign ) );
        String photoPosition = publishManager.getPhotoPosition();
        cbPhotoPosition.setSelectedIndex( photoPositions.indexOf(
                photoPosition ) );
        String navButtonPosition = publishManager.getNavButtonPosition();
        cbNavButtonPosition.setSelectedIndex( navButtonPositions.indexOf(
                navButtonPosition ) );

        enableResizeComponents();
        enableExifComponents();
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
        publishManager.setOutputColumns(
                Integer.parseInt( tfColumns.getText() ) );
        publishManager.setThumbnailsPerPage(
                coThumbnailsPerPage.getSelectedItem().toString() );
        publishManager.setResizeAll( cbResizeAll.isSelected() );
        publishManager.setResizeAllWidth(
                Integer.parseInt( tfResizeWidth.getText() ) );
        publishManager.setResizeAllHeight(
                Integer.parseInt( tfResizeHeight.getText() ) );
        publishManager.setResizePortraits( cbResizePortraits.isSelected() );
        publishManager.setResizePortraitsWidth(
                Integer.parseInt( tfResizePortraitsWidth.getText() ) );
        publishManager.setResizePortraitsHeight(
                Integer.parseInt( tfResizePortraitsHeight.getText() ) );
        publishManager.setStoreCaptions( cbSaveCaptions.isSelected() );
        publishManager.setPublishFullSize( cbFullSize.isSelected() );
        publishManager.setShowExif( cbShowExif.isSelected() );
        publishManager.setExifInSeparatePage( cbExifInSeparatePage.isSelected() );
        publishManager.setCaptionPosition( (String)captionPositions.get(
                cbCaptionPosition.getSelectedIndex() ) );
        publishManager.setCaptionAlign( (String)captionAligns.get(
                cbCaptionAlign.getSelectedIndex() ) );
        publishManager.setPhotoPosition( (String)photoPositions.get(
                cbPhotoPosition.getSelectedIndex() ) );
        publishManager.setNavButtonPosition( (String)navButtonPositions.get(
                cbNavButtonPosition.getSelectedIndex() ) );
        
        publishManager.persistOutputSettings();
    }
    
    private class ThumbnailsComboBoxModel extends DefaultComboBoxModel {
        
        /** Default serial version */
        private static final long serialVersionUID = 1L;
        
        public int getSize() {
            return 8;
        }
        
        public java.lang.Object getElementAt(int param) {
            String result;
            if( param == 0 ) {
                result = "unlimited";
            } else {
                int cols = 4;
                try {
                    cols = Integer.parseInt( tfColumns.getText() );
                } catch( NumberFormatException e ) {
                    // pretend it's 4.
                    cols = 4;
                }
                result = "" + (16 - (2 * param)) * cols;
            }
            return result;
        }
        
    }
}
