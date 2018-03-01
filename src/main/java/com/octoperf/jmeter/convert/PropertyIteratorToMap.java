package com.octoperf.jmeter.convert;

import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Converts a PropertyIterator into an immutable map of JMeterProperty (mapped by property name).
 *
 * @author Gérald Pereira
 */
final class PropertyIteratorToMap implements Function<PropertyIterator, Map<String, JMeterProperty>> {

  @Override
  public Map<String, JMeterProperty> apply(final PropertyIterator propertyIterator) {
    final Map<String, JMeterProperty> mapBuilder = new LinkedHashMap<>();
    while (propertyIterator.hasNext()) {
      final JMeterProperty property = propertyIterator.next();
      mapBuilder.put(property.getName(), property);
    }
    return mapBuilder;
  }

}
