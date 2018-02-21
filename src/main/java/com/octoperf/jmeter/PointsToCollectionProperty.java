package com.octoperf.jmeter;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.jmeter.testelement.property.CollectionProperty;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts a List of ThreadGroupPoint into a CollectionProperty
 *
 * @author Gérald Pereira
 */
@AllArgsConstructor
final class PointsToCollectionProperty implements Function<List<ThreadGroupPoint>, CollectionProperty> {

  @NonNull
  Function<ThreadGroupPoint, CollectionProperty> toCollection;

  public CollectionProperty apply(final List<ThreadGroupPoint> list) {
    return new CollectionProperty(OctoPerfThreadGroup.POINTS, list.stream().map(toCollection).collect(Collectors.toList()));
  }

}
