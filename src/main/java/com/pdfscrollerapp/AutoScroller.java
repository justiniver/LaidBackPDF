package com.pdfscrollerapp;

import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.JScrollBar;

/**
 * Autoscroller logic class.
 */
public class AutoScroller implements Scroller {
  private final JScrollPane scrollPane;
  private Timer timer;

  public AutoScroller(JScrollPane scrollPane) {
    this.scrollPane = scrollPane;
  }

  @Override
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

  @Override
  public void stopScrolling() {
    if (timer == null) {
      throw new IllegalStateException("Timer cannot be null");
    }
    timer.stop();
  }
}
