package com.octoperf.jmeter;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.function.Function;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
