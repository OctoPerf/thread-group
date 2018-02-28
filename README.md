[![Build Status](https://travis-ci.org/OctoPerf/thread-group.svg?branch=master)](https://travis-ci.org/OctoPerf/thread-group)

# OctoPerf Thread Group

This JMeter ThreadGroup allows you to define the user load curve, point by point:

![OctoPerf Thread Group](https://raw.githubusercontent.com/OctoPerf/thread-group/master/screenshot.png)

Even though you cannot modify this option in the UI, the number of loops can be configured directly in the JMX (defaults to `forever` -1):

```xml
<com.octoperf.jmeter.OctoPerfThreadGroup guiclass="com.octoperf.jmeter.ui.OctoPerfThreadGroupGui" testclass="com.octoperf.jmeter.OctoPerfThreadGroup" testname="OctoPerfThreadGroup" enabled="true">
  <stringProp name="TestPlan.comments">https://octoperf.com</stringProp>
  <collectionProp name="points">
    <collectionProp name="ThreadGroupPoint(timeInMs=0, threadsCount=0)">
      <longProp name="timeInMs">0</longProp>
      <longProp name="threadsCount">0</longProp>
    </collectionProp>
    <collectionProp name="ThreadGroupPoint(timeInMs=10000, threadsCount=10)">
      <longProp name="timeInMs">10000</longProp>
      <longProp name="threadsCount">10</longProp>
    </collectionProp>
    <collectionProp name="ThreadGroupPoint(timeInMs=20000, threadsCount=10)">
      <longProp name="timeInMs">20000</longProp>
      <longProp name="threadsCount">10</longProp>
    </collectionProp>
    <collectionProp name="ThreadGroupPoint(timeInMs=30000, threadsCount=5)">
      <longProp name="timeInMs">30000</longProp>
      <longProp name="threadsCount">5</longProp>
    </collectionProp>
    <collectionProp name="ThreadGroupPoint(timeInMs=40000, threadsCount=5)">
      <longProp name="timeInMs">40000</longProp>
      <longProp name="threadsCount">5</longProp>
    </collectionProp>
    <collectionProp name="ThreadGroupPoint(timeInMs=50000, threadsCount=15)">
      <longProp name="timeInMs">50000</longProp>
      <longProp name="threadsCount">15</longProp>
    </collectionProp>
    <collectionProp name="ThreadGroupPoint(timeInMs=60000, threadsCount=15)">
      <longProp name="timeInMs">60000</longProp>
      <longProp name="threadsCount">15</longProp>
    </collectionProp>
  </collectionProp>
  <elementProp name="ThreadGroup.main_controller" elementType="LoopController" guiclass="LoopControlPanel" testclass="LoopController" testname="Loop Controller" enabled="true">
    <boolProp name="LoopController.continue_forever">false</boolProp>
    <intProp name="LoopController.loops">1</intProp>
  </elementProp>
</com.octoperf.jmeter.OctoPerfThreadGroup>
```
