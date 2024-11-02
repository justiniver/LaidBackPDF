package com.pdfscrollerapp;

import javax.swing.SwingUtilities;

/**
 * Main entry point to run the PDF Auto Scroller application.
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      PDFAutoScroller scroller = new PDFAutoScroller();
      scroller.setVisible(true);
    });
  }
}
