package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.convert.PointsToRanges;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.function.BiFunction;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link PointsToRanges}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class PointsToRangesTest {

  @Mock
  private BiFunction<Long, List<ThreadGroupPoint>, List<ThreadRange>> threadCountToRanges;

  private PointsToRanges pointsToRanges;

  @Before
  public void before() {
    pointsToRanges = new PointsToRanges(threadCountToRanges);
    when(threadCountToRanges.apply(anyLong(), anyList())).thenReturn(of(new ThreadRange(0L, 5000L), new ThreadRange(5001L, 10000L)));
  }

  @Test
  public void shouldConvertToRanges() {
    final List<ThreadRange> ranges = pointsToRanges.apply(of(new ThreadGroupPoint(0L, 10L), new ThreadGroupPoint(10000L, 10L)));
    assertEquals(20, ranges.size());
    verify(threadCountToRanges, Mockito.times(10)).apply(anyLong(), anyList());
  }
}
