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

package org.ambraproject.rhombat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Methods for converting to and from the date format used in HTTP headers.
 */
public final class HttpDateUtil {

  // We can't use a single DataFormat instance since they aren't thread safe.
  private static final String HTTP_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";

  private HttpDateUtil() {}

  /**
   * Parses a date field coming from an HTTP header.
   *
   * @param dateString value from the header
   * @return date represented as a Calendar
   */
  public static Calendar parse(String dateString) {
    DateFormat dateFormat = new SimpleDateFormat(HTTP_DATE_FORMAT);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    Date date;
    try {
      date = dateFormat.parse(dateString);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(pe);
    }
    Calendar result = Calendar.getInstance();
    result.setTime(date);
    return result;
  }

  /**
   * Formats a date such that it can be included in an HTTP header.
   *
   * @param date the date to format
   * @return String representation
   */
  public static String format(Calendar date) {
    DateFormat dateFormat = new SimpleDateFormat(HTTP_DATE_FORMAT);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return dateFormat.format(date.getTime());
  }
}
