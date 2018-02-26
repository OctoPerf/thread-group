package com.octoperf.jmeter.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests {@link ButtonsPanel}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonsPanelTest {

  @Mock
  JTable table;

  @Mock
  ListSelectionModel selectionModel;

  private ButtonsPanel buttons;

  @Before
  public void before() {
    when(table.getSelectionModel()).thenReturn(selectionModel);
    buttons = new ButtonsPanel(table, new ThreadGroupPointTableModel());
  }

  @Test
  public void shouldEnableDeleteButton() {
    when(selectionModel.isSelectionEmpty()).thenReturn(false);
    buttons.valueChanged(null);
    assertTrue(buttons.getDeletePointButton().isEnabled());
  }
}
