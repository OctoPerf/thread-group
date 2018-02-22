package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.convert.CollectionPropertyToPoint;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.LongProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.function.Function;

import static com.google.common.collect.ImmutableMap.of;
import static com.octoperf.jmeter.model.ThreadGroupPoint.THREADS_COUNT;
import static com.octoperf.jmeter.model.ThreadGroupPoint.TIME_IN_MS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Tests {@link CollectionPropertyToPoint}.
 *
 * @author Gérald Pereira
 */
@RunWith(MockitoJUnitRunner.class)
public class CollectionPropertyToPointTest {

  @Mock
  Function<PropertyIterator, Map<String, JMeterProperty>> toMap;

  private CollectionPropertyToPoint toPoint;

  @Before
  public void before() {
    when(toMap.apply(any(PropertyIterator.class))).thenReturn(of(TIME_IN_MS, new LongProperty(TIME_IN_MS, 1000L), THREADS_COUNT, new LongProperty(THREADS_COUNT, 20L)));
    toPoint = new CollectionPropertyToPoint(toMap);
  }

  @Test
  public void shouldConvertToPoint() {
    assertEquals(new ThreadGroupPoint(1000L, 20), toPoint.apply(new CollectionProperty()));
  }
}
