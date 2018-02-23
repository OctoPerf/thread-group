package com.octoperf.jmeter.ui;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Adds a new ThreadGroupPoint to the table model
 *
 * @author Gérald Pereira
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
final class AddPointAction
  implements ActionListener {

  @NonNull
  JTable table;
  @NonNull
  ThreadGroupPointTableModel tableModel;

  public void actionPerformed(ActionEvent event) {
    if (table.isEditing()) {
      final TableCellEditor cellEditor = table.getCellEditor(table.getEditingRow(), table.getEditingColumn());
      cellEditor.stopCellEditing();
    }
    tableModel.addPoint();
  }
}
