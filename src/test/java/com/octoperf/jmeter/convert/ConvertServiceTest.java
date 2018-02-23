package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.function.Function;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@link ConvertService}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class ConvertServiceTest {

  private static final List<ThreadGroupPoint> POINTS = of(new ThreadGroupPoint(1000L, 20));
  private static final CollectionProperty COLLECTION_PROPERTY = new CollectionProperty();

  @Mock
  Function<CollectionProperty, List<ThreadGroupPoint>> toPoints;
  @Mock
  Function<List<ThreadGroupPoint>, List<ThreadRange>> toRanges;
  @Mock
  Function<List<ThreadGroupPoint>, CollectionProperty> toCollection;
  @Mock
  Function<List<ThreadGroupPoint>, List<ThreadGroupPoint>> normalize;

  private ConvertService convert;

  @Before
  public void before() {
    convert = new ConvertService(toPoints, toRanges, toCollection, normalize);
  }

  @Test
  public void shouldConvertCollectionToPoints() {
    convert.toPoints(COLLECTION_PROPERTY);
    verify(toPoints).apply(COLLECTION_PROPERTY);
  }

  @Test
  public void shouldConvertPointsToCollection() {
    convert.toCollection(POINTS);
    verify(toCollection).apply(POINTS);
  }

  @Test
  public void shouldConvertPointsToRanges() {
    convert.toRanges(POINTS);
    verify(toRanges).apply(POINTS);
  }

  @Test
  public void shouldNormalize() {
    convert.normalize(POINTS);
    verify(normalize).apply(POINTS);
  }
}
