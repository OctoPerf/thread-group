package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.testelement.property.CollectionProperty;

import java.util.List;
import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ConvertService {

  Function<CollectionProperty, List<ThreadGroupPoint>> toPoints;
  Function<List<ThreadGroupPoint>, List<ThreadRange>> toRanges;
  Function<List<ThreadGroupPoint>, CollectionProperty> toCollection;

  public ConvertService() {
    this.toPoints = new CollectionPropertyToPoints(new PropertyIteratorToList(), new CollectionPropertyToPoint(new PropertyIteratorToMap()));
    this.toRanges = new PointsToRanges(new ThreadCountToRanges());
    this.toCollection = new PointsToCollectionProperty(new PointToCollectionProperty());
  }

  public List<ThreadGroupPoint> toPoints(final CollectionProperty collectionProperty) {
    return toPoints.apply(collectionProperty);
  }

  public List<ThreadRange> toRanges(final List<ThreadGroupPoint> points) {
    return toRanges.apply(points);
  }

  public CollectionProperty toCollection(final List<ThreadGroupPoint> points) {
    return toCollection.apply(points);
  }
}

