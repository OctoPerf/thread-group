package com.octoperf.jmeter.convert;

import com.google.common.collect.ImmutableMap;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;

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
    final ImmutableMap.Builder<String, JMeterProperty> mapBuilder = ImmutableMap.builder();
    while (propertyIterator.hasNext()) {
      final JMeterProperty property = propertyIterator.next();
      mapBuilder.put(property.getName(), property);
    }
    return mapBuilder.build();
  }

}
