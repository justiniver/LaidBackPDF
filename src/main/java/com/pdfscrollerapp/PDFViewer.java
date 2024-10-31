package com.pdfscrollerapp;

import java.util.List;

import javax.swing.*;

/**
 * The view for displaying all pages of the PDF.
 * The PDF will be viewed vertically, with each page displayed in order.
 */
public class PDFViewer {
  private JScrollPane scrollPane;
  private JPanel pdfPanel;

  public PDFViewer() {
    pdfPanel = new JPanel();
    pdfPanel.setLayout(new BoxLayout(pdfPanel, BoxLayout.Y_AXIS));
    scrollPane = new JScrollPane(pdfPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public void loadPDF(String filePath) {
    PDFLoader loader = new PDFLoader();
    List<ImageIcon> pdfImages = loader.loadPDF(filePath);

    if (pdfImages.isEmpty()) {
      JLabel errorLabel = new JLabel("Failed to load PDF.");
      pdfPanel.add(errorLabel);
    } else {
      for (ImageIcon image : pdfImages) {
        JLabel pageLabel = new JLabel(image);
        pdfPanel.add(pageLabel);
      }
    }

    pdfPanel.revalidate();
    pdfPanel.repaint();
  }
}
