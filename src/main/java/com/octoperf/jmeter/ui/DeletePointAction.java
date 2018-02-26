package com.octoperf.jmeter.ui;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Removes the selected ThreadGroupPoint from the table model
 *
 * @author Gérald Pereira
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
final class DeletePointAction
  implements ActionListener {

  @NonNull
  JTable table;
  @NonNull
  ThreadGroupPointTableModel tableModel;

  public void actionPerformed(ActionEvent e) {
    if (table.isEditing()) {
      final TableCellEditor cellEditor = table.getCellEditor(table.getEditingRow(), table.getEditingColumn());
      cellEditor.cancelCellEditing();
    }
    tableModel.removePoint(table.getSelectedRow());
  }
}
