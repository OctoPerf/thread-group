package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.OctoPerfThreadGroup;
import com.octoperf.jmeter.convert.ConvertService;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import kg.apc.jmeter.JMeterPluginsUtils;
import kg.apc.jmeter.gui.GuiBuilderHelper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.threads.gui.AbstractThreadGroupGui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OctoPerfThreadGroupGui extends AbstractThreadGroupGui implements ConfigurationPanelListener {

  static List<ThreadGroupPoint> POINTS = of(new ThreadGroupPoint(0, 0), new ThreadGroupPoint(60000, 10));

  ConvertService convert;
  LineChart chart;
  ConfigurationPanel configuration;

  public OctoPerfThreadGroupGui() {
    this.convert = new ConvertService();
    this.chart = new LineChart();
    this.configuration = new ConfigurationPanel(this.convert);
    init();
  }

  final void init() {
    final JPanel containerPanel = new VerticalPanel();

    configuration.addListener(this);
    containerPanel.add(configuration.getPanel(), BorderLayout.NORTH);

    containerPanel.add(GuiBuilderHelper.getComponentWithMargin(chart.getChart(), 2, 2, 0, 2), BorderLayout.CENTER);
    add(containerPanel, BorderLayout.CENTER);
  }

  @Override
  public String getLabelResource() {
    return "OctoPerf Thread Group";
  }

  @Override
  public TestElement createTestElement() {
    final OctoPerfThreadGroup threadGroup = new OctoPerfThreadGroup();
    threadGroup.setPoints(POINTS);
    chart.refresh(threadGroup.getPoints());
    configuration.setPoints(threadGroup.getPoints());
    return threadGroup;
  }

  @Override
  public void modifyTestElement(TestElement testElement) {
    final OctoPerfThreadGroup threadGroup = (OctoPerfThreadGroup) testElement;
    threadGroup.setPoints(configuration.getPoints());
    chart.refresh(threadGroup.getPoints());
  }

  @Override
  public void configurationChanged(final List<ThreadGroupPoint> points) {
    chart.refresh(points);
  }
}
