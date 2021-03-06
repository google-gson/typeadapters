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
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link ZonedDateTimeTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class ZonedDateTimeTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    ZonedDateTime time = ZonedDateTime.of(2014,3,30,0,56,53,512000000,ZoneId.of("GMT+1"));
    String json = gson.toJson(time);
    assertEquals("\"2014-03-30T00:56:53.512+01:00[GMT+01:00]\"", json);
  }

  @Test
  public void testDeserialization() {
    ZonedDateTime time = gson.fromJson("'2014-03-30T00:56:53.512+01:00[GMT+01:00]'", ZonedDateTime.class);
    assertEquals(ZoneId.of("GMT+1"), time.getZone());
    assertEquals(2014, time.getYear());
    assertEquals(3, time.getMonthValue());
    assertEquals(30, time.getDayOfMonth());
    assertEquals(0, time.getHour());
    assertEquals(56, time.getMinute());
    assertEquals(53, time.getSecond());
    assertEquals(512000000, time.getNano());
  }
}
