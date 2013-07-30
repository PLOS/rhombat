
package org.ambraproject.rhombat.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;

public class Iso8601DateAdapterTest {

  @Test
  public void testRoundTrip() throws Exception {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Date.class, new Iso8601DateAdapter());
    Gson gson = builder.create();

    String expected = "\"2012-05-13T07:21:11Z\"";
    assertEquals(gson.toJson(gson.fromJson(expected, Date.class)), expected);
  }
}
