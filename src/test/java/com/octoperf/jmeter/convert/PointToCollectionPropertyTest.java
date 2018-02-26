package com.octoperf.jmeter.convert;

import com.google.common.testing.NullPointerTester;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.convert.PointToCollectionProperty;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.LongProperty;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@link PointToCollectionProperty}.
 *
 * @author Gérald Pereira
 */
public class PointToCollectionPropertyTest {

  private PointToCollectionProperty toCollection;

  @Before
  public void before() {
    toCollection = new PointToCollectionProperty();
  }

  @Test
  public void shouldPassNullPointerTester() {
    new NullPointerTester().testConstructors(PointToCollectionProperty.class, PACKAGE);
  }

  @Test
  public void shouldConvertToMap() {
    final CollectionProperty collection = toCollection.apply(new ThreadGroupPoint(1000L, 10L));
    assertEquals(new LongProperty("timeInMs", 1000L), collection.get(0));
    assertEquals(new LongProperty("threadsCount", 10L), collection.get(1));
  }
}
