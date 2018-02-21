package com.octoperf.jmeter.model;

import lombok.NonNull;
import lombok.Value;

/**
 * A range used to schedule a JMeterThread
 *
 * @author Gérald Pereira
 */
@Value
public final class ThreadRange {

  @NonNull
  long start;
  @NonNull
  long end;
}
