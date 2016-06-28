/*
 * Copyright (C) 2016 Gson Type Adapter Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link IntervalTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class IntervalTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(Interval.class, new IntervalTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    Interval interval = new Interval( new Instant(1396141013512L), new Instant(1467107368789L));
    String json = gson.toJson(interval);
    assertEquals("\"2014-03-30T00:56:53.512Z/2016-06-28T09:49:28.789Z\"", json);
  }

  @Test
  public void testDeserialization() {
    Interval interval = gson.fromJson("\"2014-03-30T00:56:53.512Z/2016-06-28T09:49:28.789Z\"", Interval.class);
    assertEquals(1396141013512L, interval.getStartMillis());
    assertEquals(1467107368789L, interval.getEndMillis());
  }
}
