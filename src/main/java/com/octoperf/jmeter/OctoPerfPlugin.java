package com.octoperf.jmeter;

import org.apache.jmeter.plugin.JMeterPlugin;

public class OctoPerfPlugin implements JMeterPlugin {

  @Override
  public String[][] getIconMappings() {
    final String[][] icons = new String[1][2];
    icons[0][0] = "com.octoperf.jmeter.OctoPerfThreadGroup";
    icons[0][1] = "OctoPerfThreadGroupGui.png";
    return icons;
  }

  @Override
  public String[][] getResourceBundles() {
    return new String[0][];
  }
}
