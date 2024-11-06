package com.laidbackpdfapp;

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
  private ScrollState scrollState;
  private int scrollSpeed;

  public AutoScroller(JScrollPane scrollPane) {
    this.scrollPane = scrollPane;
    this.scrollState = ScrollState.STOPPED;
    this.scrollSpeed = 10;
  }

  @Override
  public void startScrolling() {
    if (scrollState == ScrollState.RUNNING) {
      return;
    }

    createAndStartTimer();
    scrollState = ScrollState.RUNNING;
  }

  @Override
  public void stopScrolling() {
    if (timer != null) {
      timer.stop();
      scrollState = ScrollState.STOPPED;
    } else {
      throw new IllegalStateException("Timer cannot be null");
    }
  }

  @Override
  public ScrollState getScrollState() {
    return scrollState;
  }

  @Override
  public void increaseScrollSpeed() {
    scrollSpeed = Math.max(1, scrollSpeed - 1);
    if (scrollState == ScrollState.RUNNING) {
      resetTimer();
    }
  }

  @Override
  public void decreaseScrollSpeed() {
    scrollSpeed++;
    if (scrollState == ScrollState.RUNNING) {
      resetTimer();
    }
  }

  private void resetTimer() {
    stopScrolling();
    createAndStartTimer();
  }

  private void createAndStartTimer() {
    timer = new Timer(scrollSpeed, e -> {
      JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
      int currentValue = verticalBar.getValue();
      int maxValue = verticalBar.getMaximum();

      if (currentValue + 1 < maxValue) {
        verticalBar.setValue(currentValue + 1);
      }
    });
    timer.start();
  }
}
