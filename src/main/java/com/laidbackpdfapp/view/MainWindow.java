package com.laidbackpdfapp.view;

import com.laidbackpdfapp.controller.AutoScroller;
import com.laidbackpdfapp.util.PDFLoader;

import java.awt.*;
import java.io.File;

import javax.swing.*;

/**
 * Sets up and manages the GUI for the PDF Auto Scroller application.
 * This class initializes the main application window and handles PDF file selection,
 * configures the PDF viewing panel, and starts automatic scrolling through the PDF content.
 * Uses {@link PDFView} to display the PDF and {@link AutoScroller} to enable automatic scrolling.
 */
public class MainWindow extends JFrame {
  private final PDFView pdfViewer;
  private JTextField pageNumberField;
  private final AutoScroller autoScroller;
  private boolean isCurrentlyScrolling = false;

  /**
   * Constructs the PDF Auto Scroller GUI.
   * Initializes the main frame, prompts the user to select a PDF file, and displays the
   * content in a scrollable view. It dynamically sets the frame size based on the PDFs
   * dimensions, and starts automatic scrolling.
   *
   * @throws IllegalStateException if no file is selected or if the selected file path is null
   */
  public MainWindow() {
    setTitle("PDF Auto Scroller");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    String filePath = selectPDFFile();

    if (filePath == null) {
      throw new IllegalStateException("File path cannot be null");
    }

    pdfViewer = new PDFView();
    pdfViewer.loadPDF(filePath);
    add(pdfViewer.getScrollPane(), BorderLayout.CENTER);

    autoScroller = new AutoScroller(pdfViewer.getScrollPane());

    JPanel controlPanel = new JPanel();
    controlPanel.add(getButtonPanel());
    controlPanel.add(getPageNavigationPanel());
    add(controlPanel, BorderLayout.SOUTH);

    PDFLoader loader = new PDFLoader();
    int[] dimensions = loader.getFirstPageDimensions(filePath);
    int pdfWidth = dimensions[0];
    int pdfHeight = dimensions[1] / 2;
    setSize(pdfWidth, pdfHeight);
  }

  private JPanel getPageNavigationPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    pageNumberField = new JTextField(3);
    JLabel totalPagesLabel = new JLabel(" / " + pdfViewer.getTotalPages());

    JButton goButton = new JButton("Go");
    goButton.addActionListener(e -> jumpToPage());

    pageNumberField.addActionListener(e -> jumpToPage());

    panel.add(new JLabel("Page: "));
    panel.add(pageNumberField);
    panel.add(totalPagesLabel);
    panel.add(goButton);

    return panel;
  }

  private void jumpToPage() {
    try {
      int pageNumber = Integer.parseInt(pageNumberField.getText().trim());
      if (pageNumber > 0 && pageNumber <= pdfViewer.getTotalPages()) {
        if (isCurrentlyScrolling) {
          autoScroller.stopScrolling();
          isCurrentlyScrolling = false;
        }
        pdfViewer.scrollToPage(pageNumber);
      } else {
        JOptionPane.showMessageDialog(this,
                "Please enter a page number between 1 and " + pdfViewer.getTotalPages(),
                "Invalid Page Number",
                JOptionPane.ERROR_MESSAGE);
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this,
              "Please enter a valid page number",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  private JPanel getButtonPanel() {
    JButton scrollButton = new JButton("Start Scrolling");
    JButton increaseScrollSpeed = new JButton("Increase Scroll Speed");
    JButton decreaseScrollSpeed = new JButton("Decrease Scroll Speed");

    increaseScrollSpeed.setVisible(true);
    decreaseScrollSpeed.setVisible(true);

    scrollButton.addActionListener(e -> {
      if (isCurrentlyScrolling) {
        autoScroller.stopScrolling();
        scrollButton.setText("Start Scrolling");
        increaseScrollSpeed.setVisible(true);
        decreaseScrollSpeed.setVisible(true);
      } else {
        autoScroller.startScrolling();
        scrollButton.setText("Stop Scrolling");
        increaseScrollSpeed.setVisible(false);
        decreaseScrollSpeed.setVisible(false);
      }
      isCurrentlyScrolling = !isCurrentlyScrolling;
    });

    increaseScrollSpeed.addActionListener(e -> autoScroller.increaseScrollSpeed());
    decreaseScrollSpeed.addActionListener(e -> autoScroller.decreaseScrollSpeed());

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(increaseScrollSpeed);
    buttonPanel.add(scrollButton);
    buttonPanel.add(decreaseScrollSpeed);

    return buttonPanel;
  }

  /**
   * Opens a file chooser dialog to allow the user to select a PDF file.
   * Displays a file chooser and returns the path to the selected PDF file. If no file
   * is selected or the dialog is canceled, it returns null.
   *
   * @return the absolute path to the selected PDF file, or null if no file is selected
   */
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
