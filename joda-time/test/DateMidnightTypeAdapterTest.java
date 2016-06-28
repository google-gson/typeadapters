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
import org.joda.time.DateMidnight;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link DateMidnightTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class DateMidnightTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(DateMidnight.class, new DateMidnightTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    DateMidnight time = new DateMidnight(2014, 3, 30, DateTimeZone.forID("Europe/Paris"));
    String json = gson.toJson(time);
    assertEquals("\"2014-03-30T00:00:00.000+01:00\"", json);
  }

  @Test
  public void testDeserialization() {
    DateMidnight time = gson.fromJson("\"2014-03-30T00:00:00.000+01:00\"", DateMidnight.class);
    assertEquals(DateTimeZone.forID("Europe/Paris"), time.getZone());
    assertEquals(2014, time.getYear());
    assertEquals(3, time.getMonthOfYear());
    assertEquals(30, time.getDayOfMonth());
    assertEquals(0, time.getHourOfDay());
    assertEquals(0, time.getMinuteOfHour());
    assertEquals(0, time.getSecondOfMinute());
    assertEquals(0, time.getMillisOfSecond());
  }
}
