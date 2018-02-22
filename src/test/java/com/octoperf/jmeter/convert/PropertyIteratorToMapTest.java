package com.octoperf.jmeter.convert;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.convert.PropertyIteratorToMap;
import org.apache.jmeter.testelement.property.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests {@link PropertyIteratorToMap}.
 *
 * @author Gérald Pereira
 */
public class PropertyIteratorToMapTest {

  private PropertyIteratorToMap toMap;

  @Before
  public void before() {
    toMap = new PropertyIteratorToMap();
  }

  @Test
  public void shouldConvertToMap() {
    final LongProperty prop1 = new LongProperty("prop1", 1L);
    final LongProperty prop2 = new LongProperty("prop2", 2L);
    final CollectionProperty collection = new CollectionProperty("myCollection", new ArrayList<>(ImmutableList.of(prop1, prop2)));
    final Map<String, JMeterProperty> map = toMap.apply(collection.iterator());
    assertSame(prop1, map.get("prop1"));
    assertSame(prop2, map.get("prop2"));
  }
}
