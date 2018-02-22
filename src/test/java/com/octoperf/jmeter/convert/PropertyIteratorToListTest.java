package com.octoperf.jmeter.convert;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.convert.PropertyIteratorToList;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.LongProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

/**
 * Tests {@link PropertyIteratorToList}.
 *
 * @author Gérald Pereira
 */
public class PropertyIteratorToListTest {

  private PropertyIteratorToList toList;

  @Before
  public void before() {
    toList = new PropertyIteratorToList();
  }

  @Test
  public void shouldConvertToList() {
    final LongProperty prop1 = new LongProperty("prop1", 1L);
    final LongProperty prop2 = new LongProperty("prop2", 2L);
    final CollectionProperty collection = new CollectionProperty("myCollection", new ArrayList<>(ImmutableList.of(prop1, prop2)));
    final List<JMeterProperty> list = toList.apply(collection.iterator());
    assertSame(prop1, list.get(0));
    assertSame(prop2, list.get(1));
  }
}
