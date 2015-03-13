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
