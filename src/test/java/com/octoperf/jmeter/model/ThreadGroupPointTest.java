package com.octoperf.jmeter.model;

import com.google.common.testing.NullPointerTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static com.google.common.testing.NullPointerTester.Visibility.PACKAGE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link ThreadGroupPoint}.
 *
 * @author Gérald Pereira
 */
public class ThreadGroupPointTest {

  @Test
  public void shouldPassEqualsVerifier() {
    EqualsVerifier.forClass(ThreadGroupPoint.class).verify();
  }

  @Test
  public void shouldPassNullPointerTester() {
    new NullPointerTester().testConstructors(ThreadGroupPoint.class, PACKAGE);
  }

  @Test
  public void shouldCreate() {
    assertNotNull(create());
  }

  @Test
  public void shouldHaveNonStandardToString() {
    assertFalse(create().toString().contains("@"));
  }

  private ThreadGroupPoint create() {
    return new ThreadGroupPoint(0L , 0L);
  }

}
