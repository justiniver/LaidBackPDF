package com.laidbackpdfapp;

import com.laidbackpdfapp.view.MainWindow;

import javax.swing.SwingUtilities;

/**
 * Main entry point to run the PDF Auto Scroller application.
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow scroller = new MainWindow();
      scroller.setVisible(true);
    });
  }
}
