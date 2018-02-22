package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.convert.NormalizePoints;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@link NormalizePoints}.
 *
 * @author Gérald Pereira
 */
public class NormalizePointsTest {

  private NormalizePoints normalizePoints;

  @Before
  public void before() {
    normalizePoints = new NormalizePoints();
  }

  @Test
  public void shouldAddZero() {
    assertEquals(of(new ThreadGroupPoint(0L, 0L)), normalizePoints.apply(of()));
  }

  @Test
  public void shouldSortPoints() {
    assertEquals(of(new ThreadGroupPoint(0L, 0L), new ThreadGroupPoint(1000L, 0L)), normalizePoints.apply(of(new ThreadGroupPoint(1000L, 0L), new ThreadGroupPoint(0L, 0L))));
  }

  @Test
  public void shouldRemoveDuplicates() {
    assertEquals(of(new ThreadGroupPoint(0L, 0L), new ThreadGroupPoint(1000L, 0L)), normalizePoints.apply(of(new ThreadGroupPoint(0L, 0L), new ThreadGroupPoint(1000L, 0L), new ThreadGroupPoint(1000L, 0L))));
  }
}
