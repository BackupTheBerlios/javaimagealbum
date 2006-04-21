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

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;

/**
 * Allows the user to rename the images in the list and insert
 * a caption for the image.
 *
 * @author  Mark Roth
 */
public class RenameAndDescribePanel
extends javax.swing.JPanel
implements WizardPanel, TableModelListener, ListSelectionListener
{

    /** Default serial version */
	private static final long serialVersionUID = 1L;
	
	/** Creates new form RenameAndDescribePanel */
    public RenameAndDescribePanel( PublishManager publishManager ) {
        this.publishManager = publishManager;
        initComponents ();
        
        // TODO: - pnlModifiers not complete yet
        pnlModifiers.setVisible( false );
        
        // Read default setting for auto preview checkbox:
        autoPreview = Settings.getInstance().getProperty( 
            Constants.AUTO_PREVIEW, "true" ).toLowerCase().equals( "true" );
        cbAutoPreview.setSelected( autoPreview );
        updateButtons();
        
        // Set cursor to magnifying glass for preview panel
        ImageIcon zoomImageIcon = new ImageIcon(getClass().getResource(
            "/com/javaimagealbum/images/zoomcursor.gif"));
        Cursor zoomCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            zoomImageIcon.getImage(), new Point(10, 10), "zoom");
        previewPanel.setCursor(zoomCursor);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlContents = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        tpInstructions = new javax.swing.JTextPane();
        pnlModifiers = new javax.swing.JPanel();
        btnRotateCCW = new javax.swing.JButton();
        btnRotateCW = new javax.swing.JButton();
        pnlPreview = new javax.swing.JPanel();
        previewPanel = new ImagePreviewPanel();
        pnlOptions = new javax.swing.JPanel();
        cbAutoPreview = new javax.swing.JCheckBox();
        btnSelectAll = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        pnlGridAndPreview = new javax.swing.JPanel();
        pnlGrid = new javax.swing.JPanel();
        spGrid = new javax.swing.JScrollPane();
        tblCaptions = new javax.swing.JTable();
        pnlUpDownButtons = new javax.swing.JPanel();
        btnUp = new javax.swing.JButton();
        lblChangeOrder = new javax.swing.JLabel();
        btnDown = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        setBackground(java.awt.Color.white);
        pnlContents.setLayout(new java.awt.GridBagLayout());

        pnlContents.setBackground(java.awt.Color.white);
        pnlTop.setLayout(new java.awt.GridBagLayout());

        pnlTop.setBackground(java.awt.Color.white);
        pnlLeft.setLayout(new javax.swing.BoxLayout(pnlLeft, javax.swing.BoxLayout.Y_AXIS));

        pnlLeft.setBackground(java.awt.Color.white);
        lblTitle.setBackground(java.awt.Color.white);
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 14));
        lblTitle.setForeground(new java.awt.Color(0, 153, 153));
        lblTitle.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("RENAME_ADD_CAPTIONS"));
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlLeft.add(lblTitle);

        tpInstructions.setEditable(false);
        tpInstructions.setFont(new java.awt.Font("SansSerif", 0, 12));
        tpInstructions.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("RENAME_AND_DESCRIBE_PANEL_DESCRIPTION"));
        pnlLeft.add(tpInstructions);

        pnlModifiers.setBackground(java.awt.Color.white);
        pnlModifiers.setBorder(javax.swing.BorderFactory.createTitledBorder("Modifiers"));
        btnRotateCCW.setText("Rotate");
        btnRotateCCW.setToolTipText("Rotate Counter-clockwise");
        btnRotateCCW.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnRotateCCW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotateCCWActionPerformed(evt);
            }
        });

        pnlModifiers.add(btnRotateCCW);

        btnRotateCW.setText("Rotate");
        btnRotateCW.setToolTipText("Rotate Clockwise");
        btnRotateCW.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnRotateCW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotateCWActionPerformed(evt);
            }
        });

        pnlModifiers.add(btnRotateCW);

        pnlLeft.add(pnlModifiers);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTop.add(pnlLeft, gridBagConstraints);

        pnlPreview.setLayout(new java.awt.BorderLayout());

        pnlPreview.setBackground(java.awt.Color.white);
        pnlPreview.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));
        pnlPreview.setMaximumSize(new java.awt.Dimension(320, 240));
        pnlPreview.setMinimumSize(new java.awt.Dimension(320, 240));
        pnlPreview.setPreferredSize(new java.awt.Dimension(320, 240));
        previewPanel.setBackground(java.awt.Color.white);
        previewPanel.setEnabled(false);
        previewPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                previewPanelMousePressed(evt);
            }
        });

        pnlPreview.add(previewPanel, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        pnlTop.add(pnlPreview, gridBagConstraints);

        pnlOptions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlOptions.setOpaque(false);
        cbAutoPreview.setBackground(java.awt.Color.white);
        cbAutoPreview.setFont(new java.awt.Font("SansSerif", 0, 12));
        cbAutoPreview.setMnemonic('p');
        cbAutoPreview.setSelected(true);
        cbAutoPreview.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("AUTO_PREVIEW"));
        cbAutoPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAutoPreviewActionPerformed(evt);
            }
        });

        pnlOptions.add(cbAutoPreview);

        btnSelectAll.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnSelectAll.setMnemonic('a');
        btnSelectAll.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("SELECT_ALL"));
        btnSelectAll.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectAllActionPerformed(evt);
            }
        });

        pnlOptions.add(btnSelectAll);

        btnClearAll.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnClearAll.setMnemonic('l');
        btnClearAll.setText(java.util.ResourceBundle.getBundle("com/javaimagealbum/resources/Resource").getString("CLEAR_ALL"));
        btnClearAll.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        pnlOptions.add(btnClearAll);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlTop.add(pnlOptions, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlContents.add(pnlTop, gridBagConstraints);

        pnlGridAndPreview.setLayout(new java.awt.GridBagLayout());

        pnlGridAndPreview.setBackground(java.awt.Color.white);
        pnlGrid.setLayout(new java.awt.GridBagLayout());

        pnlGrid.setBackground(java.awt.Color.white);
        spGrid.setBackground(java.awt.Color.white);
        spGrid.setMinimumSize(new java.awt.Dimension(22, 150));
        spGrid.setPreferredSize(new java.awt.Dimension(0, 0));
        tblCaptions.setFont(new java.awt.Font("SansSerif", 0, 12));
        tblCaptions.setModel(new PhotoCaptionTableModel());
        tblCaptions.setGridColor(java.awt.Color.white);
        tblCaptions.setMinimumSize(new java.awt.Dimension(60, 200));
        tblCaptions.getModel().addTableModelListener( this );
        tblCaptions.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        tblCaptions.getSelectionModel().addListSelectionListener( this );
        TableColumnModel tableColumnModel = tblCaptions.getColumnModel();
        TableColumn column = tableColumnModel.getColumn( 0 );
        // Make as small as possible.  Min size of component will override:
        column.setMaxWidth( 1 );
        spGrid.setViewportView(tblCaptions);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        pnlGrid.add(spGrid, gridBagConstraints);

        pnlUpDownButtons.setLayout(new java.awt.GridBagLayout());

        pnlUpDownButtons.setBackground(java.awt.Color.white);
        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/javaimagealbum/images/up.gif")));
        btnUp.setToolTipText("Change Photo Order");
        btnUp.setPreferredSize(new java.awt.Dimension(20, 25));
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlUpDownButtons.add(btnUp, gridBagConstraints);

        lblChangeOrder.setBackground(java.awt.Color.white);
        lblChangeOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/javaimagealbum/images/changeorder.gif")));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        pnlUpDownButtons.add(lblChangeOrder, gridBagConstraints);

        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/javaimagealbum/images/down.gif")));
        btnDown.setToolTipText("Change Photo Order");
        btnDown.setPreferredSize(new java.awt.Dimension(20, 25));
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlUpDownButtons.add(btnDown, gridBagConstraints);

        pnlGrid.add(pnlUpDownButtons, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlGridAndPreview.add(pnlGrid, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlContents.add(pnlGridAndPreview, gridBagConstraints);

        add(pnlContents, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void previewPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewPanelMousePressed
        if(zoomFrame == null) {
            zoomFrame = new ZoomFrame();
            zoomFrame.setSize(640, 480);
            zoomFrame.setLocationRelativeTo(null);
        }
        int index = tblCaptions.getSelectionModel().getMinSelectionIndex();
        if(index != -1) {
            // If a photo is selected, show it in the zoom frame
            PhotoSource source = publishManager.getPhotoSource();
            ArrayList photoList = source.getPhotos();
            OutputPhoto ophoto = (OutputPhoto)photoList.get( index );
            File imageFile = ophoto.getSource();
            zoomFrame.setImage(imageFile);
            zoomFrame.show();
            zoomFrame.setTitle("Zoom: " + ophoto.getFilename());
        }
    }//GEN-LAST:event_previewPanelMousePressed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        PhotoSource source = publishManager.getPhotoSource();
        ArrayList photoList = source.getPhotos();
        for( int i = 0; i < photoList.size(); i++ ) {
            OutputPhoto photo = (OutputPhoto)photoList.get( i );
            photo.setSelected( false );
        }
        PhotoCaptionTableModel tmodel = (PhotoCaptionTableModel)
            tblCaptions.getModel();
        tmodel.fireTableDataChanged();
    }//GEN-LAST:event_btnClearAllActionPerformed

    private void btnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectAllActionPerformed
        PhotoSource source = publishManager.getPhotoSource();
        ArrayList photoList = source.getPhotos();
        for( int i = 0; i < photoList.size(); i++ ) {
            OutputPhoto photo = (OutputPhoto)photoList.get( i );
            photo.setSelected( true );
        }
        PhotoCaptionTableModel tmodel = (PhotoCaptionTableModel)
            tblCaptions.getModel();
        tmodel.fireTableDataChanged();
    }//GEN-LAST:event_btnSelectAllActionPerformed

    private void btnRotateCWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotateCWActionPerformed
        int index = tblCaptions.getSelectionModel().getMinSelectionIndex();
        if( index >= 0 ) {
            PhotoSource source = publishManager.getPhotoSource();
            ArrayList photoList = source.getPhotos();
            OutputPhoto ophoto = (OutputPhoto)photoList.get( index );
            // TODO: - to be implemented
            ophoto.rotateCW();
            updatePreview();
        }
    }//GEN-LAST:event_btnRotateCWActionPerformed

    private void btnRotateCCWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotateCCWActionPerformed
        int index = tblCaptions.getSelectionModel().getMinSelectionIndex();
        if( index >= 0 ) {
            PhotoSource source = publishManager.getPhotoSource();
            ArrayList photoList = source.getPhotos();
            OutputPhoto ophoto = (OutputPhoto)photoList.get( index );
            // TODO: - to be implemented
            ophoto.rotateCCW();
            updatePreview();
        }
    }//GEN-LAST:event_btnRotateCCWActionPerformed

  private void btnDownActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
    PhotoSource source = publishManager.getPhotoSource();
    ArrayList photoList = source.getPhotos();
    int y = tblCaptions.getSelectionModel().getMinSelectionIndex();

    // Swap y and y+1 (if possible)
    if( (y != -1) && (y < (photoList.size() - 1)) ) {
        OutputPhoto y0 = (OutputPhoto)photoList.get( y );
        OutputPhoto y1 = (OutputPhoto)photoList.get( y + 1 );
        photoList.set( y + 1, y0 );
        photoList.set( y, y1 );

        PhotoCaptionTableModel tmodel = (PhotoCaptionTableModel)
        tblCaptions.getModel();
        tmodel.fireTableDataChanged();

        ListSelectionModel lmodel = tblCaptions.getSelectionModel();
        lmodel.setSelectionInterval( y + 1, y + 1 );
    }
  }//GEN-LAST:event_btnDownActionPerformed

  private void btnUpActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
    PhotoSource source = publishManager.getPhotoSource();
    ArrayList photoList = source.getPhotos();
    int y = tblCaptions.getSelectionModel().getMinSelectionIndex();

    // Swap y and y-1 (if possible)
    if( y > 0 ) {
        OutputPhoto y0 = (OutputPhoto)photoList.get( y );
        OutputPhoto y1 = (OutputPhoto)photoList.get( y - 1 );
        photoList.set( y - 1, y0 );
        photoList.set( y, y1 );
        
        PhotoCaptionTableModel tmodel = (PhotoCaptionTableModel)
        tblCaptions.getModel();
        tmodel.fireTableDataChanged();

        ListSelectionModel lmodel = tblCaptions.getSelectionModel();
        lmodel.setSelectionInterval( y - 1, y - 1 );
    }
  }//GEN-LAST:event_btnUpActionPerformed

  private void cbAutoPreviewActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAutoPreviewActionPerformed
    autoPreview = cbAutoPreview.isSelected();
    
    // Remember preference:
    Settings.getInstance().setProperty( Constants.AUTO_PREVIEW,
        "" + cbAutoPreview.isSelected() );
    
    updatePreview();
  }//GEN-LAST:event_cbAutoPreviewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnRotateCCW;
    private javax.swing.JButton btnRotateCW;
    private javax.swing.JButton btnSelectAll;
    private javax.swing.JButton btnUp;
    private javax.swing.JCheckBox cbAutoPreview;
    private javax.swing.JLabel lblChangeOrder;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlContents;
    private javax.swing.JPanel pnlGrid;
    private javax.swing.JPanel pnlGridAndPreview;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlModifiers;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JPanel pnlPreview;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlUpDownButtons;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JScrollPane spGrid;
    private javax.swing.JTable tblCaptions;
    private javax.swing.JTextPane tpInstructions;
    // End of variables declaration//GEN-END:variables

    private PublishManager publishManager;
    
    // Last directory the user tried to access.  Used to invalidate the
    // preview panel's cache if we come back to this panel with a different
    // directory.
    private File lastSourceDir = null;
    
    // True if images are automatically previewed, false if not
    boolean autoPreview = true;
    
    // A frame containing a 1:1 version of the image.
    private ZoomFrame zoomFrame = null;

    /** Returns true if all required data was filled in for this panel.
     */
    public boolean isSatisfied() {
        int numSelectedPhotos = 
            publishManager.getPhotoSource().getOutputPhotoCount();
        return numSelectedPhotos > 0;
    }

    public void tableChanged(final javax.swing.event.TableModelEvent p1) {
    }

    public void valueChanged(final javax.swing.event.ListSelectionEvent p1) {
        int index = tblCaptions.getSelectionModel().getMinSelectionIndex();
        int rowHeight = tblCaptions.getRowHeight();
        int y = index * rowHeight;
        tblCaptions.scrollRectToVisible( 
            new java.awt.Rectangle( 0, y, 1, rowHeight ) );
        showPreview( index );
        updateButtons();
    }

    private void updatePreview() {
        valueChanged( null );
    }

    private void showPreview( final int index ) {
        java.awt.Dimension d = pnlPreview.getSize();
        if( autoPreview && (index >= 0) ) {
            previewPanel.setEnabled( true );
            PhotoSource source = publishManager.getPhotoSource();
            ArrayList photoList = source.getPhotos();
            OutputPhoto ophoto = (OutputPhoto)photoList.get( index );
            File imageFile = ophoto.getSource();
            ((ImagePreviewPanel)previewPanel).setImage( imageFile );
        }
        else {
            previewPanel.setEnabled( false );
            ((ImagePreviewPanel)previewPanel).setImage( (BufferedImage)null );
        }
    }

    /** Called when the panel is shown to the user
     */
    public void showPanel() {
        // Prepare image preview panel:
        
        // Tell preview panel to invalidate its cache if we changed 
        // directories.
        File sourceDir = publishManager.getPhotoSource().getSourceDir();
        final ImagePreviewPanel previewPanel = 
            (ImagePreviewPanel)this.previewPanel;
        previewPanel.setKeepLoading( true );
        if( lastSourceDir != null ) {
            if( !sourceDir.equals( lastSourceDir ) ) {
                previewPanel.invalidateCache();
            }
        }
        lastSourceDir = sourceDir;
        
        // Tell preview panel to start loading images in the background:
        // Only do this if auto preview is selected.
        if( autoPreview ) {
            int available = previewPanel.getAvailableCache();
            ArrayList outPhotos = publishManager.getPhotoSource().getPhotos();
            if( (available > 0) && (outPhotos.size() > 0) ) {
                // Suggest some files to load:
                final File[] suggestions = 
                    new File[Math.min( available, outPhotos.size() )];
                for( int i = 0; i < suggestions.length; i++ ) {
                    OutputPhoto outPhoto = (OutputPhoto)outPhotos.get( i );
                    suggestions[i] = outPhoto.getSource();
                }
                // Wait a little while for things to settle down, 
                // then send suggestions:
                new Thread() {
                    public void run() {
                        try {
                            // Arbitrary, but reasonable:
                            Thread.sleep( 2000 );
                        }
                        catch( InterruptedException e ) {
                            // Ignore
                        }
                        previewPanel.suggestList( suggestions );
                    }
                }.start();
            }
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
        tblCaptions.editCellAt( -1, -1 );
        tblCaptions.clearSelection();
        ImagePreviewPanel previewPanel = (ImagePreviewPanel)this.previewPanel;
        previewPanel.setKeepLoading( false );
        previewPanel.setImage( (File)null );
    }
    
    private class PhotoCaptionTableModel extends AbstractTableModel {
        /** Default serial version */
    	private static final long serialVersionUID = 1L;
    	
		private String[] columnNames = {
            " ", "Output Filename", "Caption" };
        private Class[] columnClasses = {
            Boolean.class, String.class, String.class
        };
        private static final int COL_SELECTED = 0;
        private static final int COL_FILENAME = 1;
        private static final int COL_CAPTION = 2;
        public PhotoCaptionTableModel() {
        }
        public int getRowCount() {
            PhotoSource source = publishManager.getPhotoSource();
            return source.getPhotos().size();
        }
        public int getColumnCount() {
            return columnNames.length;
        }
        public String getColumnName(int column) {
            return columnNames[column];
        }
        public Class getColumnClass(int columnIndex) {
            return columnClasses[columnIndex];
        }
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
        public void setValueAt( Object aValue, int rowIndex, int columnIndex ){
            PhotoSource source = publishManager.getPhotoSource();
            ArrayList photoList = source.getPhotos();
            OutputPhoto ophoto = (OutputPhoto)photoList.get( rowIndex );

            switch( columnIndex ) {
                case COL_SELECTED:
                    ophoto.setSelected( ((Boolean)aValue).booleanValue() );
                    break;
                case COL_FILENAME:
                    ophoto.setFilename( (String)aValue );
                    break;
                case COL_CAPTION:
                    String oldCaption = ophoto.getCaption();
                    if( !oldCaption.equals( aValue ) ) {
                        ophoto.setCaption( (String)aValue );
                        publishManager.setUnsavedCaptions( true );
                    }
                    break;
            }
        }
        public Object getValueAt(int row, int column) {
            PhotoSource source = publishManager.getPhotoSource();
            ArrayList photoList = source.getPhotos();
            OutputPhoto ophoto = (OutputPhoto)photoList.get( row );
            Object result = "";

            switch( column ) {
                case COL_SELECTED:
                    result = ophoto.isSelected() ? 
                        Boolean.TRUE : Boolean.FALSE;
                    break;
                case COL_FILENAME:
                    result = ophoto.getFilename();
                    break;
                case COL_CAPTION:
                    result = ophoto.getCaption();
                    break;
            }

            return result;
        }
    }
    
    /**
     * Updates the modifier buttons based on the current selection state
     */
    private void updateButtons() {
        int index = tblCaptions.getSelectionModel().getMinSelectionIndex();
        if( index >= 0 ) {
            // something selected.  Enable modifiers.
            btnRotateCW.setEnabled( true );
            btnRotateCCW.setEnabled( true );
        }
        else {
            // nothing selected.  Disable modifiers.
            btnRotateCW.setEnabled( false );
            btnRotateCCW.setEnabled( false );
        }
    }
}
