package com.laidbackpdfapp;

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
 * Displays a PDF document by rendering each page as an image in a vertically scrollable view.
 */
public class PDFViewer {
  private final JScrollPane scrollPane;
  private final JPanel pdfPanel;

  /**
   * Constructs a new {@link PDFViewer} with a vertically scrollable panel.
   */
  public PDFViewer() {
    pdfPanel = new JPanel();
    pdfPanel.setLayout(new GridBagLayout());

    JPanel pagesPanel = new JPanel();
    pagesPanel.setLayout(new BoxLayout(pagesPanel, BoxLayout.Y_AXIS));
    pdfPanel.add(pagesPanel, new GridBagConstraints());

    scrollPane = new JScrollPane(pdfPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  }

  /**
   * Returns the {@link JScrollPane} containing the PDF content.
   *
   * @return the scroll pane containing the vertically arranged PDF pages
   */
  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  /**
   * Loads and displays the pages of the PDF from the specified file path.
   * This method uses a {@link PDFLoader} to load each page of the PDF as an {@link ImageIcon}.
   *
   * @param filePath the path to the PDF file to be loaded
   * @throws IllegalStateException if the PDF fails to load or contains no pages
   */
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
