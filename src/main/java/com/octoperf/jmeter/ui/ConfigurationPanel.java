package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import kg.apc.jmeter.JMeterPluginsUtils;
import kg.apc.jmeter.gui.ButtonPanelAddCopyRemove;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.gui.util.PowerTableModel;
import org.apache.jmeter.testelement.property.CollectionProperty;

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

  static Long[] DEFAULT_VALUES = new Long[]{10000L, 10L};
  static String[] COLUMN_IDENTIFIERS = new String[]{"Time in milliseconds", "Thread count"};
  static Class[] COLUMN_CLASSES = new Class[]{Long.class, Long.class};

  @Getter
  JPanel panel;
  PowerTableModel tableModel;
  List<ConfigurationPanelListener> listeners;

  ConfigurationPanel() {
    listeners = new ArrayList<>();

    panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(BorderFactory.createTitledBorder("Threads Schedule"));
    panel.setPreferredSize(new Dimension(200, 200));

    final JTable table = new JTable();
    table.getDefaultEditor(String.class).addCellEditorListener(this);
    tableModel = new PowerTableModel(COLUMN_IDENTIFIERS, COLUMN_CLASSES);
    tableModel.addTableModelListener(this);
    table.setModel(tableModel);
    table.setSelectionMode(SINGLE_SELECTION);
    table.setMinimumSize(new Dimension(200, 100));

    final JScrollPane scroll = new JScrollPane(table);
    scroll.setPreferredSize(scroll.getMinimumSize());
    panel.add(scroll, BorderLayout.CENTER);
    final ButtonPanelAddCopyRemove buttons = new ButtonPanelAddCopyRemove(table, tableModel, DEFAULT_VALUES);
    panel.add(buttons, BorderLayout.SOUTH);
  }

  public CollectionProperty getCollectionProperty() {
    return JMeterPluginsUtils.tableModelRowsToCollectionProperty(tableModel, ThreadGroupPoint.POINTS);
  }

  public void setCollectionProperty(CollectionProperty collectionProperty) {
    JMeterPluginsUtils.collectionPropertyToTableModelRows(collectionProperty, tableModel);
  }

  @Override
  public void tableChanged(TableModelEvent event) {
    notifyChange();
  }

  @Override
  public void editingStopped(ChangeEvent changeEvent) {
    notifyChange();
  }

  @Override
  public void editingCanceled(ChangeEvent changeEvent) {
    notifyChange();
  }

  public void addListener(final ConfigurationPanelListener listener) {
    this.listeners.add(listener);
  }

  private void notifyChange() {
    listeners.forEach(listener -> {
      final CollectionProperty points = JMeterPluginsUtils.tableModelRowsToCollectionProperty(tableModel, ThreadGroupPoint.POINTS);
      listener.configurationChanged(points);
    });
  }
}
