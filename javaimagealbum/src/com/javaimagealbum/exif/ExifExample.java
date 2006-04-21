/*
 Copyright (C) 2001, Brent Bryan
 
 Authors:
 Brent Bryan     (brent@whitties.org)
 Brendan McMahan (mcmahahb@whitman.edu)
 
 For questions, comments, and the latest version:
 http://sourceforge.net/projects/swigs/
 
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.javaimagealbum.exif;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.BoxLayout;

public class ExifExample extends JFrame implements ActionListener {
    /** Default serial version */
    private static final long serialVersionUID = 1L;
    
    public ExifExample() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                runOnClose();
            }
        });
        
        jLabel = new JLabel();
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setPreferredSize(new Dimension(200, 200));
        
        textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane
                .setBorder(BorderFactory.createEmptyBorder(6, 10, 10, 10));
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        contentPane.add(jLabel);
        contentPane.add(areaScrollPane);
        
        // -- Create Menu Bar -- //
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // File Menu
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Open Image");
        menuItem.setActionCommand("open");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Quit");
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        
        menuBar.add(menu);
        
        pack();
        show();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            JFileChooser fc = new JFileChooser(cwd);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int retValue = fc.showOpenDialog(this);
            
            if (retValue == JFileChooser.APPROVE_OPTION) {
                File imageFile = fc.getSelectedFile();
                cwd = fc.getSelectedFile().getParentFile();
                openImage(imageFile);
            }
        } else if (e.getActionCommand().equals("quit")) {
            runOnClose();
        }
    }
    
    private void openImage(File f) {
        if (ExifReader.isExif(f)) {
            ExifHashMap hashMap = new ExifHashMap(ExifReader.decode(f));
            Image thumbImage = ExifReader.getThumbnail(f);
            if (thumbImage == null) {
                jLabel.setIcon(null);
                jLabel.setText(f.getName() + " doesn't have a thumbnail.");
            } else {
                jLabel.setIcon(new ImageIcon(thumbImage));
                jLabel.setText(null);
            }
            String s = "Image Name:\t\t" + f.getName() + newLine
                    + "Date Creation:\t\t"
                    + hashMap.getDate(ExifHashMap.CREATION_DATE) + newLine
                    + "Time Creation:\t\t"
                    + hashMap.getTime(ExifHashMap.CREATION_DATE) + newLine
                    + "Date Digitized:\t\t"
                    + hashMap.getDate(ExifHashMap.DIGITIZED_DATE) + newLine
                    + "Time Digitized:\t"
                    + hashMap.getTime(ExifHashMap.DIGITIZED_DATE) + newLine
                    + "Date Modified:\t\t"
                    + hashMap.getDate(ExifHashMap.MODIFIED_DATE) + newLine
                    + "Time Modified:\t\t"
                    + hashMap.getTime(ExifHashMap.MODIFIED_DATE) + newLine
                    + "Camera Type:\t\t" + hashMap.getCameraType() + newLine
                    + "Camera Make:\t\t" + hashMap.getCameraMake() + newLine
                    + "Camera Model:\t" + hashMap.getCameraModel() + newLine
                    + "F Stop:\t\t" + hashMap.getFStop() + newLine + newLine
                    + "Exposure Program:\t" + hashMap.getExposureProgram()
                    + newLine + "Image Height:\t\t" + hashMap.getImageHeight()
                    + newLine + "Image Width:\t\t" + hashMap.getImageWidth()
                    + newLine + "Light Source:\t\t" + hashMap.getLightSource()
                    + newLine + "ISO:\t\t" + hashMap.getISO() + newLine
                    + "Shutter Speed:\t\t" + hashMap.getShutterSpeed()
                    + newLine + "Flash:\t\t" + hashMap.getFlash();
            s += newLine + hashMap.toString();
            textArea.setText(s);
            textArea.setCaretPosition(0);
        } else {
            jLabel.setIcon(null);
            jLabel.setText(f.getName() + " is not an EXIF file.");
            textArea.setText("");
        }
    }
    
    private void runOnClose() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        new ExifExample();
    }
    
    private File cwd = new File(System.getProperty("user.dir"));
    
    private JLabel jLabel;
    
    private JTextArea textArea;
    
    private String newLine = System.getProperty("line.separator");
}
