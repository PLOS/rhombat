/*
 * Copyright 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
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

  // Implementation note: it would be most straightforward to use a SimpleDateFormat
  // here, but prior to Java 7, it is not possible for this class to correctly
  // parse ISO 8601 dates in the UTC timezone.
  // TODO: after we upgrade to Java 7, consider using something like the following line:
  // dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);

  @Override
  public JsonElement serialize(Date date, Type type,
      JsonSerializationContext jsonSerializationContext) {
    Calendar calendar = new GregorianCalendar(Iso8601Util.UTC);
    calendar.setTime(date);
    return new JsonPrimitive(DatatypeConverter.printDateTime(calendar));
  }

  @Override
  public Date deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) {
    Calendar calendar = DatatypeConverter.parseDateTime(jsonElement.getAsString());
    calendar.setTimeZone(Iso8601Util.UTC);
    return calendar.getTime();
  }
}
