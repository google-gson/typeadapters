/*
 * Copyright (C) 2014 Gson Type Adapter Authors.
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
import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Unit tests for {@link DateTimeTypeAdapter}.
 *
 * @author Inderjeet Singh
 */
public class DateTimeTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    DateTime time = new DateTime("2014-03-30T00:56:53.512-00:00");
    String json = gson.toJson(time);
    assertEquals('"' + time.toString() + '"', json);
  }

  @Test
  public void testDeserialization() {
    DateTime time = gson.fromJson("'2014-03-30T00:56:53.512-00:00'", DateTime.class);
    assertEquals(DateTimeZone.getDefault(), time.getZone());
    time = time.toDateTime(DateTimeZone.UTC);
    assertEquals(2014, time.getYear());
    assertEquals(3, time.getMonthOfYear());
    assertEquals(30, time.getDayOfMonth());
    assertEquals(0, time.getHourOfDay());
    assertEquals(56, time.getMinuteOfHour());
    assertEquals(53, time.getSecondOfMinute());
    assertEquals(512, time.getMillisOfSecond());
  }
}
