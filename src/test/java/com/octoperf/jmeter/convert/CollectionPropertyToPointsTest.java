package com.octoperf.jmeter.convert;

import com.google.common.testing.NullPointerTester;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.function.Function;

import static com.google.common.collect.ImmutableList.of;
import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Tests {@link CollectionPropertyToPoints}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class CollectionPropertyToPointsTest {

  private static final ThreadGroupPoint POINT = new ThreadGroupPoint(1000L, 20L);

  @Mock
  Function<PropertyIterator, List<JMeterProperty>> toList;

  @Mock
  Function<CollectionProperty, ThreadGroupPoint> toPoint;

  private CollectionPropertyToPoints toPoints;

  @Before
  public void before() {
    when(toList.apply(any(PropertyIterator.class))).thenReturn(of(new CollectionProperty(), new CollectionProperty()));
    when(toPoint.apply(any(CollectionProperty.class))).thenReturn(POINT);
    toPoints = new CollectionPropertyToPoints(toList, toPoint);
  }

  @Test
  public void shouldPassNullPointerTester() {
    new NullPointerTester().testConstructors(CollectionPropertyToPoints.class, PACKAGE);
  }


  @Test
  public void shouldConvertToPoints() {
    assertEquals(of(POINT, POINT), toPoints.apply(new CollectionProperty()));
  }
}
