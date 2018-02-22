package com.octoperf.jmeter.convert;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.convert.NormalizePoints;
import com.octoperf.jmeter.convert.ThreadCountToRanges;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link ThreadCountToRanges}.
 *
 * @author Gérald Pereira
 */
public class ThreadCountToRangesTest {

  private ThreadCountToRanges toRanges;

  @Before
  public void before() {
    toRanges = new ThreadCountToRanges();
  }

  @Test
  public void shouldComputeNumberOfRanges() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 0L),
      new ThreadGroupPoint(20000L, 10L),
      new ThreadGroupPoint(30000L, 5L),
      new ThreadGroupPoint(40000L, 15L),
      new ThreadGroupPoint(60000L, 15L),
      new ThreadGroupPoint(90000L, 0L)
    );
    assertEquals(1L, toRanges.apply(1L, points).size());
    assertEquals(1L, toRanges.apply(5L, points).size());
    assertEquals(2L, toRanges.apply(6L, points).size());
    assertEquals(2L, toRanges.apply(10L, points).size());
    assertEquals(1L, toRanges.apply(11L, points).size());
    assertEquals(1L, toRanges.apply(15L, points).size());
  }

  @Test
  public void shouldComputeNumberOfRangesWithoutLastZeroPoint() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 0L),
      new ThreadGroupPoint(20000L, 10L),
      new ThreadGroupPoint(30000L, 5L),
      new ThreadGroupPoint(40000L, 15L),
      new ThreadGroupPoint(60000L, 15L),
      new ThreadGroupPoint(90000L, 10L)
    );
    assertEquals(1L, toRanges.apply(1L, points).size());
    assertEquals(1L, toRanges.apply(5L, points).size());
    assertEquals(2L, toRanges.apply(6L, points).size());
    assertEquals(2L, toRanges.apply(10L, points).size());
    assertEquals(1L, toRanges.apply(11L, points).size());
    assertEquals(1L, toRanges.apply(15L, points).size());
  }

  @Test
  public void shouldComputeNumberOfRangesWithoutFirstZeroPoint() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 5L),
      new ThreadGroupPoint(20000L, 10L),
      new ThreadGroupPoint(30000L, 5L),
      new ThreadGroupPoint(40000L, 15L),
      new ThreadGroupPoint(60000L, 15L),
      new ThreadGroupPoint(90000L, 0L)
    );
    assertEquals(1L, toRanges.apply(1L, points).size());
    assertEquals(1L, toRanges.apply(5L, points).size());
    assertEquals(2L, toRanges.apply(6L, points).size());
    assertEquals(2L, toRanges.apply(10L, points).size());
    assertEquals(1L, toRanges.apply(11L, points).size());
    assertEquals(1L, toRanges.apply(15L, points).size());
  }

  @Test
  public void shouldComputeNumberOfRangesWithoutZeroPoints() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 5L),
      new ThreadGroupPoint(20000L, 10L),
      new ThreadGroupPoint(30000L, 5L),
      new ThreadGroupPoint(40000L, 15L),
      new ThreadGroupPoint(60000L, 15L),
      new ThreadGroupPoint(90000L, 10L)
    );
    assertEquals(1L, toRanges.apply(1L, points).size());
    assertEquals(1L, toRanges.apply(5L, points).size());
    assertEquals(2L, toRanges.apply(6L, points).size());
    assertEquals(2L, toRanges.apply(10L, points).size());
    assertEquals(1L, toRanges.apply(11L, points).size());
    assertEquals(1L, toRanges.apply(15L, points).size());
  }

  @Test
  public void shouldComputeSimpleRangeValues() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 10L),
      new ThreadGroupPoint(90000L, 10L)
    );
    assertEquals(new ThreadRange(0L, 90000L), toRanges.apply(1L, points).get(0));
    assertEquals(new ThreadRange(0L, 90000L), toRanges.apply(5L, points).get(0));
    assertEquals(new ThreadRange(0L, 90000L), toRanges.apply(10L, points).get(0));
  }


  @Test
  public void shouldComputeComplexRangeValues() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 0L),
      new ThreadGroupPoint(30000L, 10L),
      new ThreadGroupPoint(60000L, 10L),
      new ThreadGroupPoint(90000L, 0L)
    );
    assertEquals(new ThreadRange(3000L, 87000L), toRanges.apply(1L, points).get(0));
    assertEquals(new ThreadRange(15000L, 75000L), toRanges.apply(5L, points).get(0));
    assertEquals(new ThreadRange(24000L, 66000L), toRanges.apply(8L, points).get(0));
  }

  @Test
  public void shouldComputeEmptyRanges() {
    final List<ThreadGroupPoint> threadGroupPoints = new NormalizePoints().apply(ImmutableList.of());
    assertEquals(0L, toRanges.apply(1L, threadGroupPoints).size());
  }

  @Test
  public void shouldComputeOutOfRange() {
    final List<ThreadGroupPoint> points = ImmutableList.of(
      new ThreadGroupPoint(0L, 5L),
      new ThreadGroupPoint(90000L, 5L)
    );
    assertEquals(1L, toRanges.apply(5L, points).size());
    assertEquals(0L, toRanges.apply(6L, points).size());
  }
}
