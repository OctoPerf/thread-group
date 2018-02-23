package com.octoperf.jmeter.ui;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ButtonsPanel extends JPanel implements ListSelectionListener {

  JButton deletePointButton;
  JTable table;

  public ButtonsPanel(JTable table, ThreadGroupPointTableModel tableModel) {
    super();
    this.table = table;
    setLayout(new GridLayout(1, 2));

    final JButton addPointButton = new JButton("Add Point");
    deletePointButton = new JButton("Delete Point");

    addPointButton.addActionListener(new AddPointAction(table, tableModel));
    deletePointButton.addActionListener(new DeletePointAction(table, tableModel));
    deletePointButton.setEnabled(false);

    add(addPointButton);
    add(deletePointButton);

    table.getSelectionModel().addListSelectionListener(this);
  }

  @Override
  public void valueChanged(ListSelectionEvent listSelectionEvent) {
    deletePointButton.setEnabled(!table.getSelectionModel().isSelectionEmpty());
  }
}
