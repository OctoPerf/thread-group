package com.octoperf.jmeter;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts a CollectionProperty into a List of ThreadGroupPoint
 * <p>
 * <pre>
 * {@code
 * <xml>
 *   <collectionProp name="points">
 *      <collectionProp name="(0,0)">
 *        <longProp name="timeInMs">0</longProp>
 *        <longProp name="threadsCount">0</longProp>
 *      </collectionProp>
 *      <collectionProp name="(60000,100)">
 *        <longProp name="timeInMs">60000</longProp>
 *        <longProp name="threadsCount">100</longProp>
 *      </collectionProp>
 *   </collectionProp>
 * </xml>
 * }
 * </pre>
 *
 * @author Gérald Pereira
 */
@AllArgsConstructor
final class CollectionPropertyToPoints implements Function<CollectionProperty, List<ThreadGroupPoint>> {

  @NonNull
  Function<PropertyIterator, List<JMeterProperty>> toList;

  @NonNull
  Function<CollectionProperty, ThreadGroupPoint> toPoint;

  public List<ThreadGroupPoint> apply(final CollectionProperty collectionProperty) {
    final List<JMeterProperty> properties = toList.apply(collectionProperty.iterator());
    return properties.stream()
      .filter(CollectionProperty.class::isInstance)
      .map(CollectionProperty.class::cast)
      .map(toPoint)
      .collect(Collectors.toList());
  }

}
