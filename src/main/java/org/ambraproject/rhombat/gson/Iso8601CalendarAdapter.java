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
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    return new JsonPrimitive(DatatypeConverter.printDateTime(calendar));
  }

  @Override
  public Calendar deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) {
    Calendar calendar = DatatypeConverter.parseDateTime(jsonElement.getAsString());
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    return calendar;
  }
}
