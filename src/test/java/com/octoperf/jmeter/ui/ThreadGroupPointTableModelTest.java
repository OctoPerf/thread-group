package com.octoperf.jmeter.ui;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link ThreadGroupPointTableModel}.
 *
 * @author Gérald Pereira
 */
public class ThreadGroupPointTableModelTest {

  private ThreadGroupPointTableModel model;

  @Before
  public void before() {
    model = new ThreadGroupPointTableModel();
  }

  @Test
  public void shouldSetPoints() {
    model.setPoints(of(new ThreadGroupPoint(0L,0L)));
    assertEquals(0L, model.getValueAt(0,0));
  }

  @Test
  public void shouldAddPoint() {
    model.addPoint();
    model.addPoint();
    assertEquals(30000L, model.getValueAt(0,0));
    assertEquals(60000L, model.getValueAt(1,0));
  }

  @Test
  public void shouldRemovePoint() {
    model.addPoint();
    model.removePoint(0);
    assertEquals(0, model.getRowCount());
  }

  @Test
  public void shouldReturnColCount() {
    assertEquals(2, model.getColumnCount());
  }

  @Test
  public void shouldSetAndGetValue() {
    model.addPoint();
    model.setValueAt(10L, 0, 0);
    model.setValueAt(20L, 0, 1);
    assertEquals(10L, model.getValueAt(0,0));
    assertEquals(20L, model.getValueAt(0,1));
  }

  @Test
  public void shouldReturnCellEditable() {
    assertTrue(model.isCellEditable(0,0));
  }

  @Test
  public void shouldReturnColumnClass() {
    assertEquals(Long.class, model.getColumnClass(0));
  }

  @Test
  public void shouldReturnColumnName() {
    assertNotNull(model.getColumnName(0));
  }

}
