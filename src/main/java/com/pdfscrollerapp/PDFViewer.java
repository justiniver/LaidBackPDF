package com.pdfscrollerapp;

import javax.swing.*;

/**
 * The view for the PDF.
 * The PDF will always be viewed vertically.
 */
public class PDFViewer {
  private JScrollPane scrollPane;
  private JLabel pdfLabel;

  public PDFViewer() {
    pdfLabel = new JLabel();
    scrollPane = new JScrollPane(pdfLabel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public void loadPDF(String filePath) {
    PDFLoader loader = new PDFLoader();
    ImageIcon pdfImage = loader.loadPDF(filePath);
    if (pdfImage != null) {
      pdfLabel.setIcon(pdfImage);
    } else {
      pdfLabel.setText("Failed to load PDF.");
    }
  }
}
