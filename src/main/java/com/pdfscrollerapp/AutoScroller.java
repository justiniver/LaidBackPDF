package com.pdfscrollerapp;

import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.JScrollBar;

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
    timer = new Timer(10, e -> {
      JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
      int currentValue = verticalBar.getValue();
      int maxValue = verticalBar.getMaximum();

      if (currentValue + 1 < maxValue) {
        verticalBar.setValue(currentValue + 1);
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
