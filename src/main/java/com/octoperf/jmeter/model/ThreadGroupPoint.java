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

  public static final String TIME_IN_MS = "timeInMs";
  public static final String THREADS_COUNT = "threadsCount";

  @NonNull
  long timeInMs;
  @NonNull
  long threadsCount;
}