package com.pdfscrollerapp;

/**
 * Interface defining the basic scrolling behavior for any scroller implementation.
 */
public interface Scroller {

  /**
   * Starts the automatic scrolling behavior.
   */
  void startScrolling();

  /**
   * Stops the automatic scrolling behavior.
   */
  void stopScrolling();
}
