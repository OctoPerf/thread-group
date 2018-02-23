package com.octoperf.jmeter.ui;

import com.google.common.testing.NullPointerTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link AddPointAction}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class AddPointActionTest {

  @Mock
  JTable table;
  @Mock
  TableCellEditor cellEditor;

  private AddPointAction action;
  private ThreadGroupPointTableModel tableModel;

  @Before
  public void before() {
    tableModel = new ThreadGroupPointTableModel();
    action = new AddPointAction(table, tableModel);
  }

  @Test
  public void shouldAddPoint() {
    when(table.isEditing()).thenReturn(false);
    action.actionPerformed(null);
    assertEquals(1, tableModel.getPoints().size());
  }

  @Test
  public void shouldStopCellEditing() {
    when(table.isEditing()).thenReturn(true);
    when(table.getEditingRow()).thenReturn(0);
    when(table.getEditingColumn()).thenReturn(0);
    when(table.getCellEditor(0,0)).thenReturn(cellEditor);
    action.actionPerformed(null);
    verify(cellEditor).stopCellEditing();
  }

  @Test
  public void shouldPassNullPointerTester() {
    new NullPointerTester().setDefault(ThreadGroupPointTableModel.class, new ThreadGroupPointTableModel()).testConstructors(AddPointAction.class, PACKAGE);
  }
}
