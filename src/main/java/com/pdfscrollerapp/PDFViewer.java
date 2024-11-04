package com.pdfscrollerapp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * The view for vertically displaying all pages of the PDF.
 */
public class PDFViewer {
  private final JScrollPane scrollPane;
  private final JPanel pdfPanel;

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
      throw new IllegalStateException("Failed to load PDF.");
    }

    JPanel pagesPanel = (JPanel) ((JPanel) scrollPane.getViewport().getView()).getComponent(0);
    for (ImageIcon image : pdfImages) {
        JLabel pageLabel = new JLabel(image);
        pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pagesPanel.add(pageLabel);
    }

    pdfPanel.revalidate();
    pdfPanel.repaint();
  }
}
