package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.model.ThreadGroupPoint;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Normalizes a list of ThreadGroupPoint:
 * - Removes duplicate points (same time),
 * - Sorts the list.
 *
 * @author Gérald Pereira
 */
final class NormalizePoints implements Function<List<ThreadGroupPoint>, List<ThreadGroupPoint>> {

  @Override
  public List<ThreadGroupPoint> apply(final List<ThreadGroupPoint> threadGroupPoints) {
    final HashMap<Long, ThreadGroupPoint> map = new HashMap<>();
    for (final ThreadGroupPoint point : threadGroupPoints) {
      map.merge(point.getTimeInMs(), point, (p1, p2) -> new ThreadGroupPoint(point.getTimeInMs(), Math.max(p1.getThreadsCount(), p2.getThreadsCount())));
    }
    return map.values()
      .stream()
      .sorted(Comparator.comparing(ThreadGroupPoint::getTimeInMs))
      .distinct()
      .collect(Collectors.toList());
  }
}
