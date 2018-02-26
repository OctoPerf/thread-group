package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.convert.ConvertService;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ConfigurationPanel implements TableModelListener, CellEditorListener {

  @Getter
  JPanel panel;
  ThreadGroupPointTableModel tableModel;
  List<ConfigurationPanelListener> listeners;
  ConvertService convert;

  ConfigurationPanel() {
    listeners = new ArrayList<>();
    this.convert = new ConvertService();

    panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(BorderFactory.createTitledBorder("Threads Schedule"));
    panel.setPreferredSize(new Dimension(200, 200));

    final JTable table = new JTable();
    table.getDefaultEditor(String.class).addCellEditorListener(this);
    tableModel = new ThreadGroupPointTableModel();
    tableModel.addTableModelListener(this);
    table.setModel(tableModel);
    table.setSelectionMode(SINGLE_SELECTION);
    table.setMinimumSize(new Dimension(200, 100));

    final JScrollPane scroll = new JScrollPane(table);
    scroll.setPreferredSize(scroll.getMinimumSize());
    panel.add(scroll, BorderLayout.CENTER);
    final ButtonsPanel buttons = new ButtonsPanel(table, tableModel);
    panel.add(buttons.getButtonsPanel(), BorderLayout.SOUTH);
  }

  public void setPoints(final List<ThreadGroupPoint> points) {
    tableModel.setPoints(points);
  }

  public List<ThreadGroupPoint> getPoints() {
    return tableModel.getPoints();
  }

  @Override
  public void tableChanged(TableModelEvent event) {
    modelChanged();
  }

  @Override
  public void editingStopped(ChangeEvent changeEvent) {
    modelChanged();
  }

  @Override
  public void editingCanceled(ChangeEvent changeEvent) {
    modelChanged();
  }

  public void addListener(final ConfigurationPanelListener listener) {
    this.listeners.add(listener);
  }

  private void modelChanged() {
    final List<ThreadGroupPoint> normalized = this.convert.normalize(tableModel.getPoints());
    tableModel.setPoints(normalized);
    listeners.forEach(listener -> listener.configurationChanged(normalized));
  }
}
