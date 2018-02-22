package com.octoperf.jmeter.convert;

import com.octoperf.jmeter.model.ThreadGroupPoint;
import com.octoperf.jmeter.model.ThreadRange;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Converts a list of ThreadGroupPoint into a list of ThreadRange
 *
 * @author Gérald Pereira
 */
@AllArgsConstructor
final class PointsToRanges implements Function<List<ThreadGroupPoint>, List<ThreadRange>> {

  @NonNull
  BiFunction<Long, List<ThreadGroupPoint>, List<ThreadRange>> toRanges;

  @Override
  public List<ThreadRange> apply(final List<ThreadGroupPoint> threadGroupPoints) {
    final long maxThreadCount = threadGroupPoints
      .stream()
      .map(ThreadGroupPoint::getThreadsCount)
      .max(Long::compareTo)
      .orElse(0L);

    return LongStream.range(1, maxThreadCount + 1)
      .mapToObj(threadCount -> toRanges.apply(threadCount, threadGroupPoints))
      .flatMap(List::stream)
      .collect(Collectors.toList());
  }
}
