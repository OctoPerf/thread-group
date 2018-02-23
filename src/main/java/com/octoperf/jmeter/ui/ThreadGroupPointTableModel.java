package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class ThreadGroupPointTableModel extends AbstractTableModel {

  static int COL_COUNT = 2;
  static String[] COL_NAMES = new String[]{"Time in milliseconds", "Thread count"};


  @Getter
  List<ThreadGroupPoint> points;

  ThreadGroupPointTableModel(){
    points = new ArrayList<>();
  }

  public void setPoints(final List<ThreadGroupPoint> points){
    this.points.clear();
    this.points.addAll(points);
  }

  @Override
  public int getRowCount() {
    return points.size();
  }

  @Override
  public int getColumnCount() {
    return COL_COUNT;
  }

  @Override
  public Object getValueAt(int row, int col) {
    final ThreadGroupPoint point = points.get(row);
    if (col == 0) {
      return point.getTimeInMs();
    }
    return point.getThreadsCount();
  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    final Long longValue = (Long) value;
    final ThreadGroupPoint originalPoint = points.get(row);
    final ThreadGroupPoint newPoint;
    if (col == 0) {
      newPoint = new ThreadGroupPoint(longValue, originalPoint.getThreadsCount());
    } else {
      newPoint = new ThreadGroupPoint(originalPoint.getTimeInMs(), longValue);
    }
    points.set(row, newPoint);
    fireTableCellUpdated(row, col);
  }

  @Override
  public boolean isCellEditable(int i, int i1) {
    return true;
  }

  @Override
  public Class<?> getColumnClass(int i) {
    return Long.class;
  }

  @Override
  public String getColumnName(int i) {
    return COL_NAMES[i];
  }
}
