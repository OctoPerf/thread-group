package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.OctoPerfThreadGroup;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import kg.apc.jmeter.gui.GuiBuilderHelper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.threads.AbstractThreadGroup;
import org.apache.jmeter.threads.gui.AbstractThreadGroupGui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.apache.jmeter.threads.AbstractThreadGroup.MAIN_CONTROLLER;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OctoPerfThreadGroupGui extends AbstractThreadGroupGui implements ConfigurationPanelListener {

  private static final long serialVersionUID = 1337L;

  private static final List<ThreadGroupPoint> POINTS = of(new ThreadGroupPoint(0L, 0L), new ThreadGroupPoint(60000L, 10L));

  LineChart chart; //NOSONAR
  ConfigurationPanel configuration; //NOSONAR
  LoopControlPanel loopPanel;

  public OctoPerfThreadGroupGui() {
    super();
    this.chart = new LineChart();
    this.configuration = new ConfigurationPanel();
    this.loopPanel = new LoopControlPanel(false);
    initUi();
  }

  private final void initUi() {
    final JPanel containerPanel = new VerticalPanel();

    configuration.addListener(this);
    containerPanel.add(configuration.getPanel(), BorderLayout.NORTH);

    containerPanel.add(GuiBuilderHelper.getComponentWithMargin(chart.getChart(), 2, 2, 0, 2), BorderLayout.CENTER);
    add(containerPanel, BorderLayout.CENTER);

    final LoopController looper = (LoopController) loopPanel.createTestElement();
    looper.setLoops(-1);
    looper.setContinueForever(true);
    loopPanel.configure(looper);
  }

  @Override
  public String getLabelResource() {
    return "OctoPerf Thread Group";
  }

  @Override
  public TestElement createTestElement() {
    final OctoPerfThreadGroup threadGroup = new OctoPerfThreadGroup();
    threadGroup.setPoints(POINTS);
    threadGroup.setSamplerController((LoopController) loopPanel.createTestElement());
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
  public void configure(TestElement testElement) {
    super.configure(testElement);
    final OctoPerfThreadGroup threadGroup = (OctoPerfThreadGroup) testElement;
    chart.refresh(threadGroup.getPoints());
    configuration.setPoints(threadGroup.getPoints());

    TestElement controller = (TestElement) testElement.getProperty(MAIN_CONTROLLER).getObjectValue();
    loopPanel.configure(controller);
  }

  @Override
  public void configurationChanged(final List<ThreadGroupPoint> points) {
    chart.refresh(points);
  }
}
