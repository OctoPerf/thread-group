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
  Long start;
  @NonNull
  Long end;
}
