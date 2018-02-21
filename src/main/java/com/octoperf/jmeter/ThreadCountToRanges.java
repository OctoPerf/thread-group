package com.octoperf.jmeter;

import com.google.common.collect.ImmutableList;
import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Converts a list of ThreadGroupPoint + a thread count into a list of ThreadRange
 *
 * @author Gérald Pereira
 */
final class ThreadCountToRanges implements BiFunction<Long, List<ThreadGroupPoint>, List<ThreadRange>> {

  @Override
  public List<ThreadRange> apply(final Long threadCount, final List<ThreadGroupPoint> threadGroupPoints) {
    final ImmutableList.Builder<ThreadRange> rangesBuilder = new ImmutableList.Builder<>();
    ThreadGroupPoint last = threadGroupPoints.get(0);
    Optional<Long> start = last.getThreadsCount() >= threadCount ? Optional.of(last.getTimeInMs()) : Optional.empty();

    for (final ThreadGroupPoint point : threadGroupPoints) {
      if (!start.isPresent() && point.getThreadsCount() >= threadCount) {
        start = Optional.of(computeTime(threadCount, last, point));
      }
      if (start.isPresent() && point.getThreadsCount() < threadCount) {
        rangesBuilder.add(new ThreadRange(start.get(), computeTime(threadCount, last, point)));
        start = Optional.empty();
      }
      last = point;
    }

    if (start.isPresent()) {
      rangesBuilder.add(new ThreadRange(start.get(), threadGroupPoints.get(threadGroupPoints.size() - 1).getTimeInMs()));
    }

    return rangesBuilder.build();
  }


  private static Long computeTime(final Long threadCount, final ThreadGroupPoint p1, final ThreadGroupPoint p2) {
    final double slope = (p2.getThreadsCount() - p1.getThreadsCount()) / (double) (p2.getTimeInMs() - p1.getTimeInMs());
    final double intercept = p1.getThreadsCount() - (slope * p1.getTimeInMs());
    return Math.round((threadCount - intercept) / slope);
  }
}
