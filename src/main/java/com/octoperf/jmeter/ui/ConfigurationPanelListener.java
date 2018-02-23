package com.octoperf.jmeter.ui;

import com.octoperf.jmeter.model.ThreadGroupPoint;

import java.util.List;

interface ConfigurationPanelListener {

  void configurationChanged(List<ThreadGroupPoint> points);

}
