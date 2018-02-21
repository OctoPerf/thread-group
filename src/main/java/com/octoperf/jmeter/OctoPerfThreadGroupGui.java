package com.octoperf.jmeter;

import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.threads.gui.AbstractThreadGroupGui;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class OctoPerfThreadGroupGui extends AbstractThreadGroupGui /*implements TableModelListener,  CellEditorListener*/ {

//  @Override
//  public void editingStopped(ChangeEvent changeEvent) {
//
//  }
//
//  @Override
//  public void editingCanceled(ChangeEvent changeEvent) {
//
//  }
//
//  @Override
//  public void tableChanged(TableModelEvent tableModelEvent) {
//
//  }

  @Override
  public String getLabelResource() {
    return this.getClass().getSimpleName();
  }

  @Override
  public TestElement createTestElement() {
    return new OctoPerfThreadGroup();
  }

  @Override
  public void modifyTestElement(TestElement testElement) {
    // TODO
  }
}
