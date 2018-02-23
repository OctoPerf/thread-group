package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.apache.jmeter.testelement.property.CollectionProperty;

import java.util.List;
import java.util.function.Function;


/**
 * Handles conversions from/to list of ThreadGroupPoint and CollectionProperty
 *
 * @author Gérald Pereira
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class ConvertService {

  @NonNull
  Function<CollectionProperty, List<ThreadGroupPoint>> toPoints;
  @NonNull
  Function<List<ThreadGroupPoint>, List<ThreadRange>> toRanges;
  @NonNull
  Function<List<ThreadGroupPoint>, CollectionProperty> toCollection;
  @NonNull
  Function<List<ThreadGroupPoint>, List<ThreadGroupPoint>> normalize;

  public ConvertService() {
    this.toPoints = new CollectionPropertyToPoints(new PropertyIteratorToList(), new CollectionPropertyToPoint(new PropertyIteratorToMap()));
    this.toRanges = new PointsToRanges(new ThreadCountToRanges());
    this.toCollection = new PointsToCollectionProperty(new PointToCollectionProperty());
    this.normalize = new NormalizePoints();
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

  public List<ThreadGroupPoint> normalize(final List<ThreadGroupPoint> points){
    return this.normalize.apply(points);
  }

}

