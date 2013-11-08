
package org.ambraproject.rhombat;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HttpDateUtilTest {

  @Test
  public void testRoundTrip() {
    String s = "Wed, 13 Nov 2013 19:43:31 GMT";
    assertEquals(HttpDateUtil.format(HttpDateUtil.parse(s)), s);
  }
}
