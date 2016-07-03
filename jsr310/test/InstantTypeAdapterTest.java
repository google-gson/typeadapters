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
import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Unit tests for {@link InstantTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class InstantTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    Instant time = Instant.ofEpochMilli(1396141013512L);
    String json = gson.toJson(time);
    assertEquals("\"2014-03-30T00:56:53.512Z\"", json);
  }

  @Test
  public void testDeserialization() {
    Instant instant = gson.fromJson("'2014-03-30T00:56:53.512Z'", Instant.class);
    OffsetDateTime time = instant.atOffset(ZoneOffset.UTC);
    assertEquals(2014, time.getYear());
    assertEquals(3, time.getMonthValue());
    assertEquals(30, time.getDayOfMonth());
    assertEquals(0, time.getHour());
    assertEquals(56, time.getMinute());
    assertEquals(53, time.getSecond());
    assertEquals(512000000, time.getNano());
  }
}
