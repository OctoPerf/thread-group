package com.octoperf.jmeter.model;

import com.google.common.testing.NullPointerTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link ThreadRange}.
 *
 * @author Gérald Pereira
 */
public class ThreadRangeTest {

  @Test
  public void shouldPassEqualsVerifier() {
    EqualsVerifier.forClass(ThreadRange.class).verify();
  }

  @Test
  public void shouldPassNullPointerTester() {
    new NullPointerTester().testConstructors(ThreadRange.class, PACKAGE);
  }

  @Test
  public void shouldCreate() {
    assertNotNull(create());
  }

  @Test
  public void shouldHaveNonStandardToString() {
    assertFalse(create().toString().contains("@"));
  }

  private ThreadRange create() {
    return new ThreadRange(0L , 0L);
  }

}
