package com.octoperf.jmeter;

import com.octoperf.jmeter.convert.*;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import kg.apc.jmeter.threads.AbstractSimpleThreadGroup;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.threads.JMeterThread;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class OctoPerfThreadGroup extends AbstractSimpleThreadGroup implements Serializable, TestStateListener {

  final ConvertService convert;
  ListIterator<ThreadRange> ranges;

  public OctoPerfThreadGroup() {
    super();
    convert = new ConvertService();
  }

  @Override
  protected void scheduleThread(JMeterThread thread, long now) {
    final ThreadRange range = ranges.next();
    thread.setStartTime(range.getStart() + now);
    thread.setEndTime(range.getEnd() + now);
    thread.setScheduled(true);
  }

  @Override
  public int getNumThreads() {
    return getThreadRanges().size();
  }

  @Override
  public JMeterThread addNewThread(int delay, StandardJMeterEngine engine) {
    return null;
  }

  @Override
  public void testStarted() {
    this.ranges = getThreadRanges().listIterator();
  }

  @Override
  public void testStarted(String host) {
    testStarted();
  }

  @Override
  public void testEnded() {
  }

  @Override
  public void testEnded(String host) {
    testEnded();
  }

  public void setPoints(final List<ThreadGroupPoint> points) {
    setProperty(convert.toCollection(points));
  }

  public List<ThreadGroupPoint> getPoints() {
    return convert.toPoints((CollectionProperty) getProperty(ThreadGroupPoint.POINTS));
  }

  private List<ThreadRange> getThreadRanges() {
    return convert.toRanges(getPoints());
  }
}
