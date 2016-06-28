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
import org.joda.time.MonthDay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link MonthDayTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class MonthDayTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(MonthDay.class, new MonthDayTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    MonthDay monthDay = new MonthDay(3,30);
    String json = gson.toJson(monthDay);
    assertEquals("\"--03-30\"", json);
  }

  @Test
  public void testDeserialization() {
    MonthDay monthDay = gson.fromJson("\"--03-30\"", MonthDay.class);
    assertEquals(3, monthDay.getMonthOfYear());
    assertEquals(30, monthDay.getDayOfMonth());
  }
}
