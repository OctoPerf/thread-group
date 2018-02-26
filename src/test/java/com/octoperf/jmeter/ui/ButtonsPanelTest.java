package com.octoperf.jmeter.ui;

import com.google.common.testing.NullPointerTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;
import static org.junit.Assert.assertFalse;
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
  public void shouldPassNullPointerTester() {
    new NullPointerTester().setDefault(ThreadGroupPointTableModel.class, new ThreadGroupPointTableModel()).testConstructors(ButtonsPanel.class, PACKAGE);
  }

  @Test
  public void shouldEnableDeleteButton() {
    when(selectionModel.isSelectionEmpty()).thenReturn(false);
    buttons.valueChanged(null);
    assertTrue(buttons.getDeletePointButton().isEnabled());
  }

  @Test
  public void shouldDisableDeleteButton() {
    when(selectionModel.isSelectionEmpty()).thenReturn(true);
    buttons.valueChanged(null);
    assertFalse(buttons.getDeletePointButton().isEnabled());
  }
}
