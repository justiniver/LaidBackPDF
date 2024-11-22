package com.laidbackpdfapp.controller;

import com.laidbackpdfapp.model.ScrollState;

/**
 * Interface defining the basic scrolling behavior for any scroller implementation.
 */
public interface Scroller {

  /**
   * Starts the automatic scrolling behavior.
   */
  void startScrolling();

  /**
   * Stops the automatic scrolling action.
   *
   * @throws IllegalStateException if the timer has not been initialized
   */
  void stopScrolling();

  /**
   * Returns the current scroll state.
   *
   * @return the current scroll state
   */
  ScrollState getScrollState();

  /**
   * Increases scroll speed.
   */
  void increaseScrollSpeed();

  /**
   * Decreases scroll speed.
   */
  void decreaseScrollSpeed();
}
