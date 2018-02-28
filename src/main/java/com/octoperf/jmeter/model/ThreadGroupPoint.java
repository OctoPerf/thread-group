package com.octoperf.jmeter.model;

import lombok.NonNull;
import lombok.Value;

/**
 * A single point of the OctoPerfThreadGroup
 *
 * @author Gérald Pereira
 */
@Value
public final class ThreadGroupPoint {

  public static final String POINTS = "points";
  public static final String TIME_IN_MS = "timeInMs";
  public static final String THREADS_COUNT = "threadsCount";

  @NonNull
  Long timeInMs;
  @NonNull
  Long threadsCount;
}
