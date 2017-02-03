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
