package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.OctoPerfThreadGroup;
import com.octoperf.jmeter.convert.ConvertService;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import kg.apc.jmeter.gui.GuiBuilderHelper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.threads.gui.AbstractThreadGroupGui;

import javax.swing.*;
import java.awt.*;

import static com.google.common.collect.ImmutableList.of;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OctoPerfThreadGroupGui extends AbstractThreadGroupGui implements ConfigurationPanelListener {

  ConvertService convert;
  LineChart chart;
  ConfigurationPanel configuration;

  public OctoPerfThreadGroupGui() {
    this.convert = new ConvertService();
    this.chart = new LineChart();
    this.configuration = new ConfigurationPanel();
    init();
  }

  final void init() {
//    JMeterPluginsUtils.addHelpLinkToPanel(this, "http://octoperf.com");
    final JPanel containerPanel = new VerticalPanel();

    configuration.addListener(this);
    containerPanel.add(configuration.getPanel(), BorderLayout.NORTH);

    containerPanel.add(GuiBuilderHelper.getComponentWithMargin(chart.getChart(), 2, 2, 0, 2), BorderLayout.CENTER);
    add(containerPanel, BorderLayout.CENTER);
  }

  @Override
  public String getLabelResource() {
    return this.getClass().getSimpleName();
  }

  @Override
  public TestElement createTestElement() {
    final OctoPerfThreadGroup threadGroup = new OctoPerfThreadGroup();
    threadGroup.setProperty(convert.toCollection(of(new ThreadGroupPoint(0, 0), new ThreadGroupPoint(60000, 10))));
    chart.refresh(threadGroup.getPoints());
    configuration.setCollectionProperty(threadGroup.getCollectionProperty());
    System.out.println("createTestElement " + threadGroup);
    return threadGroup;
  }

  @Override
  public void modifyTestElement(TestElement testElement) {
    final OctoPerfThreadGroup threadGroup = (OctoPerfThreadGroup) testElement;
    threadGroup.setProperty(configuration.getCollectionProperty());
    chart.refresh(threadGroup.getPoints());
    configuration.setCollectionProperty(threadGroup.getCollectionProperty());
    System.out.println("modifyTestElement " + testElement);
  }

  @Override
  public void configurationChanged(CollectionProperty collectionProperty) {
    chart.refresh(convert.toPoints(collectionProperty));
  }
}
