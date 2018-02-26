package com.octoperf.jmeter.ui;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Contains add/delete point buttons. Handle the enabled state of the delete button depending on the table selection.
 *
 * @author Gérald Pereira
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ButtonsPanel implements ListSelectionListener {

  @Getter(AccessLevel.PACKAGE)
  JButton deletePointButton;
  JTable table;
  @Getter(AccessLevel.PACKAGE)
  JPanel buttonsPanel;

  ButtonsPanel(final JTable table, final ThreadGroupPointTableModel tableModel) {
    buttonsPanel = new JPanel();
    this.table = table;
    buttonsPanel.setLayout(new GridLayout(1, 2));

    final JButton addPointButton = new JButton();
    addPointButton.setText("Add Point");
    deletePointButton = new JButton();
    deletePointButton.setText("Delete Point");

    addPointButton.addActionListener(new AddPointAction(table, tableModel));
    deletePointButton.addActionListener(new DeletePointAction(table, tableModel));
    deletePointButton.setEnabled(false);

    buttonsPanel.add(addPointButton);
    buttonsPanel.add(deletePointButton);

    table.getSelectionModel().addListSelectionListener(this);
  }

  @Override
  public void valueChanged(ListSelectionEvent listSelectionEvent) {
    deletePointButton.setEnabled(!table.getSelectionModel().isSelectionEmpty());
  }
}
