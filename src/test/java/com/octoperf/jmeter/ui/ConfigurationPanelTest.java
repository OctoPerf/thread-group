package com.octoperf.jmeter.ui;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.convert.ConvertService;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link ConfigurationPanel}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationPanelTest {

  @Mock
  ConfigurationPanelListener listener;

  private ConfigurationPanel configuration;

  @Before
  public void before() {
    configuration = new ConfigurationPanel();
  }

  @Test
  public void shouldReturnPanel() {
    assertNotNull(configuration.getPanel());
  }

  @Test
  public void shouldSetAndGetPoints() {
    final List<ThreadGroupPoint> points = of(new ThreadGroupPoint(0L, 0L));
    configuration.setPoints(points);
    assertEquals(points, configuration.getPoints());
  }

  @Test
  public void shouldFireEventOnChange() {
    configuration.addListener(listener);
    configuration.tableChanged(null);
    configuration.editingCanceled(null);
    configuration.editingStopped(null);
    verify(listener, times(3)).configurationChanged(anyList());
  }
}
