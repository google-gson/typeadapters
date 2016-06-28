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
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link LocalTimeTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class LocalTimeTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    LocalTime time = new LocalTime(0, 56, 53, 512);
    String json = gson.toJson(time);
    assertEquals("\"00:56:53.512\"", json);
  }

  @Test
  public void testDeserialization() {
    LocalTime time = gson.fromJson("\"00:56:53.512\"", LocalTime.class);
    assertEquals(0, time.getHourOfDay());
    assertEquals(56, time.getMinuteOfHour());
    assertEquals(53, time.getSecondOfMinute());
    assertEquals(512, time.getMillisOfSecond());
  }
}
