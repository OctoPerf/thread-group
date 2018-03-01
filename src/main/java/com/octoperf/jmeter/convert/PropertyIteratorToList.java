package com.octoperf.jmeter.convert;

import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Converts a PropertyIterator into an immutable list of JMeterProperty
 *
 * @author Gérald Pereira
 */
final class PropertyIteratorToList implements Function<PropertyIterator, List<JMeterProperty>> {

  @Override
  public List<JMeterProperty> apply(final PropertyIterator propertyIterator) {
    final List<JMeterProperty> list = new ArrayList<>();
    while (propertyIterator.hasNext()) {
      list.add(propertyIterator.next());
    }
    return list;
  }

}
