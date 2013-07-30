
/*
 * $HeadURL$
 * $Id$
 * Copyright (c) 2006-2013 by Public Library of Science http://plos.org http://ambraproject.org
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ambraproject.rhombat.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.testng.Assert.assertEquals;

public class Iso8601CalendarAdapterTest {

  @Test
  public void testRoundTrip() throws Exception {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Calendar.class, new Iso8601CalendarAdapter());
    builder.registerTypeAdapter(GregorianCalendar.class, new Iso8601CalendarAdapter());
    Gson gson = builder.create();

    String expected = "\"2012-02-29T13:25:17Z\"";
    assertEquals(gson.toJson(gson.fromJson(expected, Calendar.class)), expected);
  }
}
