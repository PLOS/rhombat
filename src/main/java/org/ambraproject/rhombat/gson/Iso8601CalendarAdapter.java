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
import java.util.TimeZone;

/**
 * Serializer/deserializer that converts between Java Calendar objects and ISO 8601
 * compliant date strings.  The date strings are expressed in the UTC timezone,
 * regardless of the local timezone.
 * <p/>
 * Because of the way the GSON library works, you <em>must</em> register both java.util.Calendar
 * and the specific subclass of Calendar that is produced (usually GregorianCalendar).  For instance:
 * <code>
 *   GsonBuilder builder = new GsonBuilder();
 *   builder.registerTypeAdapter(Calendar.class, new Iso8601CalendarAdapter());
 *   builder.registerTypeAdapter(GregorianCalendar.class, new Iso8601CalendarAdapter());
 * </code>
 */
public class Iso8601CalendarAdapter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

  @Override
  public JsonElement serialize(Calendar calendar, Type type,
      JsonSerializationContext jsonSerializationContext) {
    calendar.setTimeZone(Iso8601Util.UTC);
    return new JsonPrimitive(DatatypeConverter.printDateTime(calendar));
  }

  @Override
  public Calendar deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) {
    Calendar calendar = DatatypeConverter.parseDateTime(jsonElement.getAsString());
    calendar.setTimeZone(Iso8601Util.UTC);
    return calendar;
  }
}
