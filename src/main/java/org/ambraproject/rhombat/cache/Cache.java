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

package org.ambraproject.rhombat.cache;

/**
 * Interface for a simple key-value cache.  Keys are always Strings while values
 * can be any type that implements {@link java.io.Serializable}.
 */
public interface Cache {

  /**
   * Attempts to retrieve a value from the cache.
   *
   * @param key the cache key
   * @param <T> type of the value
   * @return the value if it is found in the cache, or null if it is not
   */
  <T> T get(String key);

  /**
   * Stores a value in the cache.  The inserted value may have a TTL that is implementation-dependent.
   * Use the three-argument put method if you want to define the TTL.
   *
   * @param key cache key
   * @param value object to store
   */
  void put(String key, Object value);

  /**
   * Stores a value in the cache.
   *
   * @param key cache key
   * @param value object to store
   * @param ttl cache TTL for the value, in seconds
   */
  void put(String key, Object value, int ttl);

  /**
   * Removes a key/value mapping from the cache, if it exists.
   *
   * @param key cache key
   */
  void remove(String key);
}
