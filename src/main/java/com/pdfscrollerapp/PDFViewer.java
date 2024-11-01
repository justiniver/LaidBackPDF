package com.pdfscrollerapp;

import java.awt.*;
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
    pdfPanel.setLayout(new GridBagLayout());

    JPanel pagesPanel = new JPanel();
    pagesPanel.setLayout(new BoxLayout(pagesPanel, BoxLayout.Y_AXIS));
    pdfPanel.add(pagesPanel, new GridBagConstraints());

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
      JPanel pagesPanel = (JPanel) ((JPanel) scrollPane.getViewport().getView()).getComponent(0);
      for (ImageIcon image : pdfImages) {
        JLabel pageLabel = new JLabel(image);
        pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pagesPanel.add(pageLabel);
      }
    }

    pdfPanel.revalidate();
    pdfPanel.repaint();
  }
}
