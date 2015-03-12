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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Serializer/deserializer that converts between Java Date objects and ISO 8601
 * compliant date strings.  The date strings are expressed in the UTC timezone,
 * regardless of the local timezone.
 * <p/>
 * Adapted from code at http://code.google.com/p/google-gson/issues/detail?id=281
 */
public class Iso8601DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

  private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

  // Implementation note: it would be most straightforward to use a SimpleDateFormat
  // here, but prior to Java 7, it is not possible for this class to correctly
  // parse ISO 8601 dates in the UTC timezone.
  // TODO: after we upgrade to Java 7, consider using something like the following line:
  // dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);

  @Override
  public JsonElement serialize(Date date, Type type,
      JsonSerializationContext jsonSerializationContext) {
    Calendar calendar = new GregorianCalendar(UTC);
    calendar.setTime(date);
    return new JsonPrimitive(DatatypeConverter.printDateTime(calendar));
  }

  @Override
  public Date deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) {
    Calendar calendar = DatatypeConverter.parseDateTime(jsonElement.getAsString());
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    return calendar.getTime();
  }
}
