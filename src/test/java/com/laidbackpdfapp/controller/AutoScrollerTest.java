package com.laidbackpdfapp.controller;

import com.laidbackpdfapp.model.ScrollState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class AutoScrollerTest {

  private JScrollPane scrollPane;
  private JScrollBar verticalScrollBar;
  private AutoScroller autoScroller;

  @BeforeEach
  public void setUp() {
    scrollPane = new JScrollPane();
    verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
    scrollPane.setVerticalScrollBar(verticalScrollBar);
    autoScroller = new AutoScroller(scrollPane);
  }

  @Test
  public void testInitialScrollState() {
    assertEquals(ScrollState.STOPPED, autoScroller.getScrollState(),
            "AutoScroller should initialize with STOPPED state");
  }

  @Test
  public void testStartScrolling() {
    autoScroller.startScrolling();
    assertEquals(ScrollState.RUNNING, autoScroller.getScrollState(),
            "AutoScroller state should change to RUNNING after startScrolling is called");
  }

  @Test
  public void testStopScrolling() {
    autoScroller.startScrolling();
    autoScroller.stopScrolling();
    assertEquals(ScrollState.STOPPED, autoScroller.getScrollState(),
            "AutoScroller state should change to STOPPED after stopScrolling is called");
  }

  @Test
  public void testStopScrollingWithoutStart() {
    assertThrows(IllegalStateException.class, autoScroller::stopScrolling,
            "Calling stopScrolling without starting should throw an IllegalStateException");
  }
}
