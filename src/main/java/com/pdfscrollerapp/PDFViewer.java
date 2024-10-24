package com.pdfscrollerapp;

import javax.swing.*;

public class PDFViewer {
  private JScrollPane scrollPane;
  private JTextArea pdfArea;

  public PDFViewer() {
    pdfArea = new JTextArea();
    scrollPane = new JScrollPane(pdfArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    loadPDF("path/to/sample.pdf");
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public void loadPDF(String filePath) {
    PDFLoader loader = new PDFLoader();
    String pdfContent = loader.loadPDF(filePath);
    pdfArea.setText(pdfContent);
  }
}
