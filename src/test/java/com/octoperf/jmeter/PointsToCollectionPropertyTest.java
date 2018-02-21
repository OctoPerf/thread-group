package com.octoperf.jmeter;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link PointsToCollectionProperty}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class PointsToCollectionPropertyTest {

  @Mock
  Function<ThreadGroupPoint, CollectionProperty> pointToCollection;

  private PointsToCollectionProperty pointsToCollectionProperty;

  @Before
  public void before() {
    pointsToCollectionProperty = new PointsToCollectionProperty(pointToCollection);
    when(pointToCollection.apply(Mockito.any(ThreadGroupPoint.class))).thenReturn(new CollectionProperty());
  }

  @Test
  public void shouldConvertToCollection() {
    final CollectionProperty collection = pointsToCollectionProperty.apply(of(new ThreadGroupPoint(0L, 10L), new ThreadGroupPoint(10000L, 10L)));
    assertEquals(2, collection.size());
  }
}
