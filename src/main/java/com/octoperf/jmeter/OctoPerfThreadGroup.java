package com.octoperf.jmeter;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import kg.apc.jmeter.threads.AbstractSimpleThreadGroup;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.threads.JMeterThread;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class OctoPerfThreadGroup extends AbstractSimpleThreadGroup implements Serializable, TestStateListener {

  public static final String POINTS = "points";

  Function<CollectionProperty, List<ThreadGroupPoint>> toPoints;
  Function<List<ThreadGroupPoint>, List<ThreadRange>> toRanges;
  ListIterator<ThreadRange> ranges;

  public OctoPerfThreadGroup() {
    super();
    toPoints = new CollectionPropertyToPoints(new PropertyIteratorToList(), new CollectionPropertyToPoint(new PropertyIteratorToMap()));
    toRanges = new PointsToRanges(new ThreadCountToRanges());
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

  private List<ThreadRange> getThreadRanges() {
    final CollectionProperty pointsCollection = (CollectionProperty) getProperty(POINTS);
    final List<ThreadGroupPoint> points = toPoints.apply(pointsCollection);
    return toRanges.apply(points);
  }
}
