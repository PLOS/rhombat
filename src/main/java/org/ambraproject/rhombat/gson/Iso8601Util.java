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

import java.util.TimeZone;

class Iso8601Util {
  private Iso8601Util() {
    throw new AssertionError("Not instantiable");
  }

  /**
   * Constant object representing the UTC time zone.
   * <p/>
   * Avoiding redundant calls to {@link java.util.TimeZone#getTimeZone} is especially important because that method is
   * {@code static syncrhonized}. Calling it from an adapter object's instance method leads to contention and slowness
   * if the adapter (or a {@link com.google.gson.Gson} object containing it) is shared among many threads.
   */
  static final TimeZone UTC = TimeZone.getTimeZone("UTC");

}
