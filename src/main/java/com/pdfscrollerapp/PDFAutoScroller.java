package com.pdfscrollerapp;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JFileChooser;

/**
 * GUI setup for the PDF Auto Scroller.
 */
public class PDFAutoScroller extends JFrame {

  public PDFAutoScroller() {
    setTitle("PDF Auto Scroller");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String filePath = selectPDFFile();

    if (filePath == null) {
      throw new IllegalStateException("File path cannot be null");
    }

    PDFViewer pdfViewer = new PDFViewer();
    pdfViewer.loadPDF(filePath);

    add(pdfViewer.getScrollPane());

    PDFLoader loader = new PDFLoader();
    int[] dimensions = loader.getFirstPageDimensions(filePath);
    int pdfWidth = dimensions[0];
    int pdfHeight = dimensions[1] / 2;
    setSize(pdfWidth, pdfHeight);

    AutoScroller autoScroller = new AutoScroller(pdfViewer.getScrollPane());
    autoScroller.startScrolling();
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
}
