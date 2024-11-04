package com.pdfscrollerapp;

import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.JScrollBar;

/**
 * Handles automatic scrolling for a {@link JScrollPane}.
 * This class manages an auto-scrolling feature. It provides methods to start and stop the
 * scrolling action, with adjustable speed based on the timer delay.
 */
public class AutoScroller implements Scroller {
  private final JScrollPane scrollPane;
  private Timer timer;

  public AutoScroller(JScrollPane scrollPane) {
    this.scrollPane = scrollPane;
  }

  /**
   * Starts the automatic scrolling action.
   */
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

  /**
   * Stops the automatic scrolling action.
   *
   * @throws IllegalStateException if the timer has not been initialized
   */
  @Override
  public void stopScrolling() {
    if (timer == null) {
      throw new IllegalStateException("Timer cannot be null");
    }
    timer.stop();
  }
}
