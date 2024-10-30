package com.pdfscrollerapp;

import java.io.File;

import javax.swing.*;

/**
 * Entry point to run the autoscroller.
 */
public class PDFAutoScroller extends JFrame {
  private PDFViewer pdfViewer;
  private AutoScroller autoScroller;

  public PDFAutoScroller() {
    setTitle("PDF Auto Scroller");
    setSize(600, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String filePath = selectPDFFile();
    if (filePath != null) {
      pdfViewer = new PDFViewer();
      pdfViewer.loadPDF(filePath);

      add(pdfViewer.getScrollPane());

      autoScroller = new AutoScroller(pdfViewer.getScrollPane());
      autoScroller.startScrolling();
    } else {
      System.out.println("No file selected.");
    }
  }

  private String selectPDFFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select a PDF file");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      PDFAutoScroller scroller = new PDFAutoScroller();
      scroller.setVisible(true);
    });
  }
}
