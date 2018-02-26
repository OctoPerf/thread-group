package com.octoperf.jmeter.ui;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link OctoPerfThreadGroupGui}.
 *
 * @author Gérald Pereira
 */
public class OctoPerfThreadGroupGuiTest {

  private OctoPerfThreadGroupGui gui;

  @Before
  public void before() {
    gui = new OctoPerfThreadGroupGui();
  }

  @Test
  public void shouldReturnLabel() {
    assertNotNull(gui.getLabelResource());
  }

  @Test
  public void shouldCreateTestElement() {
    assertNotNull(gui.createTestElement());
  }

  @Test
  public void shouldModifyTestElement() {
    gui.modifyTestElement(gui.createTestElement());
  }

  @Test
  public void shouldConfigureTestElement() {
    gui.configure(gui.createTestElement());
  }

  @Test
  public void shouldHandleConfigurationChanged() {
    gui.configurationChanged(of());
  }


}
