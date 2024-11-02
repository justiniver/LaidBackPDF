package com.pdfscrollerapp;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;

/**
 * Entry point to run the autoscroller.
 */
public class PDFAutoScroller extends JFrame {
  private PDFViewer pdfViewer;
  private AutoScroller autoScroller;

  public PDFAutoScroller() {
    setTitle("PDF Auto Scroller");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String filePath = selectPDFFile();
    if (filePath != null) {
      pdfViewer = new PDFViewer();
      pdfViewer.loadPDF(filePath);

      add(pdfViewer.getScrollPane());

      PDFLoader loader = new PDFLoader();
      int[] dimensions = loader.getFirstPageDimensions(filePath);
      int pdfWidth = dimensions[0];
      int pdfHeight = dimensions[1] / 2;
      setSize(pdfWidth, pdfHeight);

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
