package com.octoperf.jmeter.ui;

import lombok.Value;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Value
final class AddPointAction
  implements ActionListener {

  JTable table;
  ThreadGroupPointTableModel tableModel;

  public void actionPerformed(ActionEvent e) {
    if (table.isEditing()) {
      final TableCellEditor cellEditor = table.getCellEditor(table.getEditingRow(), table.getEditingColumn());
      cellEditor.stopCellEditing();
    }
    tableModel.addPoint();
  }
}
