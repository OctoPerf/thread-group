package com.octoperf.jmeter;

import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.LongProperty;
import org.apache.jmeter.threads.JMeterThread;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.google.common.collect.ImmutableList.of;
import static com.octoperf.jmeter.model.ThreadGroupPoint.THREADS_COUNT;
import static com.octoperf.jmeter.model.ThreadGroupPoint.TIME_IN_MS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link OctoPerfThreadGroup}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class OctoPerfThreadGroupTest {

  @Mock
  private JMeterThread thread;

  private OctoPerfThreadGroup instance;

  public OctoPerfThreadGroupTest() {
  }

  @Before
  public void setUp() {
    final CollectionProperty firstPoint = new CollectionProperty("(0,10)", new ArrayList<>(of(new LongProperty(TIME_IN_MS, 0L), new LongProperty(THREADS_COUNT, 10L))));
    final CollectionProperty lastPoint = new CollectionProperty("(60000,10)", new ArrayList<>(of(new LongProperty(TIME_IN_MS, 60000L), new LongProperty(THREADS_COUNT, 10L))));
    final CollectionProperty points = new CollectionProperty(OctoPerfThreadGroup.POINTS, new ArrayList<>(of(firstPoint, lastPoint)));
    instance = new OctoPerfThreadGroup();
    instance.setProperty(points);
  }

  @Test
  public void testGetNumThreads() {
    assertEquals(10, instance.getNumThreads());
  }

  @Test
  public void testScheduleThread() {
    instance.testStarted("host");
    instance.scheduleThread(thread, 30000L);
    instance.testEnded("host");
    verify(thread).setScheduled(true);
    verify(thread).setStartTime(30000L);
    verify(thread).setEndTime(90000L);
  }
}
