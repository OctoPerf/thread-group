package com.octoperf.jmeter;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.PropertyIterator;

import java.util.Map;
import java.util.function.Function;

/**
 * Converts a CollectionProperty into a ThreadGroupPoint
 * <p>
 * <pre>
 * {@code
 * <xml>
 *  <collectionProp name="(60000,100)">
 *    <longProp name="timeInMs">60000</longProp>
 *    <longProp name="threadsCount">100</longProp>
 *  </collectionProp>
 * </xml>
 * }
 * </pre>
 *
 * @author Gérald Pereira
 */
@AllArgsConstructor
final class CollectionPropertyToPoint implements Function<CollectionProperty, ThreadGroupPoint> {

  @NonNull
  Function<PropertyIterator, Map<String, JMeterProperty>> toMap;

  public ThreadGroupPoint apply(final CollectionProperty collectionProperty) {
    final Map<String, JMeterProperty> properties = toMap.apply(collectionProperty.iterator());
    return new ThreadGroupPoint(properties.get(ThreadGroupPoint.TIME_IN_MS).getLongValue(), properties.get(ThreadGroupPoint.THREADS_COUNT).getLongValue());
  }

}
