package com.pdfscrollerapp;

import javax.swing.*;

public class PDFAutoScroller extends JFrame {
  private PDFViewer pdfViewer;
  private AutoScroller autoScroller;

  public PDFAutoScroller() {
    setTitle("PDF Auto Scroller");
    setSize(600, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pdfViewer = new PDFViewer();
    add(pdfViewer.getScrollPane());

    autoScroller = new AutoScroller(pdfViewer.getScrollPane());
    autoScroller.startScrolling();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      PDFAutoScroller scroller = new PDFAutoScroller();
      scroller.setVisible(true);
    });
  }
}
