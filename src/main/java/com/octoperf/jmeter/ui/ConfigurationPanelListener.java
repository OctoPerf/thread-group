package com.octoperf.jmeter.ui;

import org.apache.jmeter.testelement.property.CollectionProperty;

interface ConfigurationPanelListener {

  void configurationChanged(CollectionProperty points);

}
