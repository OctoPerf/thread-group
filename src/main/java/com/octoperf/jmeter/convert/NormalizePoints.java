package com.octoperf.jmeter.convert;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.model.ThreadGroupPoint;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Normalizes a list of ThreadGroupPoint:
 * - Adds a first point (0,0),
 * - Removes duplicate points (same time),
 * - Sorts the list.
 *
 * @author Gérald Pereira
 */
final class NormalizePoints implements Function<List<ThreadGroupPoint>, List<ThreadGroupPoint>> {

  @Override
  public List<ThreadGroupPoint> apply(final List<ThreadGroupPoint> threadGroupPoints) {
    final ThreadGroupPoint first = new ThreadGroupPoint(0, 0);
    final List<ThreadGroupPoint> allPoints = new ImmutableList.Builder<ThreadGroupPoint>().add(first).addAll(threadGroupPoints).build();
    final HashMap<Long, ThreadGroupPoint> map = new HashMap<>();
    for (ThreadGroupPoint point : allPoints) {
      map.merge(point.getTimeInMs(), point, (p1, p2) -> new ThreadGroupPoint(point.getTimeInMs(), Math.max(p1.getThreadsCount(), p2.getThreadsCount())));
    }
    return map.values()
      .stream()
      .sorted(Comparator.comparing(ThreadGroupPoint::getTimeInMs))
      .distinct()
      .collect(Collectors.toList());
  }
}
