package com.pdfscrollerapp;

import javax.swing.*;

/**
 * Autoscroller logic class.
 */
public class AutoScroller {
  private JScrollPane scrollPane;
  private Timer timer;

  public AutoScroller(JScrollPane scrollPane) {
    this.scrollPane = scrollPane;
  }

  public void startScrolling() {
    timer = new Timer(0, e -> {
      JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
      int currentValue = verticalBar.getValue();
      int maxValue = verticalBar.getMaximum();

      if (currentValue + 3 < maxValue) {
        verticalBar.setValue(currentValue + 3);
      }
    });
    timer.start();
  }

  public void stopScrolling() {
    if (timer != null) {
      timer.stop();
    }
  }
}
