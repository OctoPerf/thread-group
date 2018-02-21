package com.octoperf.jmeter;

import com.google.common.collect.ImmutableList;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;

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
    final ImmutableList.Builder<JMeterProperty> listBuilder = ImmutableList.builder();
    while (propertyIterator.hasNext()) {
      listBuilder.add(propertyIterator.next());
    }
    return listBuilder.build();
  }

}
