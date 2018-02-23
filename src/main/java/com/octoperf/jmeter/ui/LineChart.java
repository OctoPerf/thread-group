package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import kg.apc.charting.AbstractGraphRow;
import kg.apc.charting.DateTimeRenderer;
import kg.apc.charting.GraphPanelChart;
import kg.apc.charting.rows.GraphRowSimple;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class LineChart {

  @Getter(AccessLevel.PACKAGE)
  GraphPanelChart chart;

  LineChart() {
    chart = new GraphPanelChart(false, true);
    chart.getChartSettings().setDrawFinalZeroingLines(true);
    chart.setxAxisLabel("Elapsed time (in ms)");
    chart.setYAxisLabel("Number of active threads");
    chart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    chart.setxAxisLabelRenderer(new DateTimeRenderer(DateTimeRenderer.HHMMSS, 0));
    chart.setForcedMinX(0);
  }

  public void refresh(final List<ThreadGroupPoint> points) {
    final GraphRowSimple row = new GraphRowSimple();
    row.setColor(Color.RED);
    row.setDrawLine(true);
    row.setMarkerSize(AbstractGraphRow.MARKER_SIZE_NONE);
    row.setDrawThickLines(true);
    points.stream().forEach(point -> row.add(point.getTimeInMs(), point.getThreadsCount()));
    final ConcurrentHashMap<String, AbstractGraphRow> model = new ConcurrentHashMap<>();
    model.put("Concurrent users", row);
    chart.setRows(model);
    chart.invalidateCache();
    chart.repaint();
    chart.revalidate();
  }
}
