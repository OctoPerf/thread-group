package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link LineChart}.
 *
 * @author Gérald Pereira
 */
public class LineChartTest {

  private LineChart lineChart;

  @Before
  public void before() {
    lineChart = new LineChart();
  }

  @Test
  public void shouldRefresh() {
    lineChart.refresh(of(new ThreadGroupPoint(0L, 0L)));
  }

  @Test
  public void shouldReturnChart() {
    assertNotNull(lineChart.getChart());
  }
}
