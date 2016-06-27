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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Unit tests for {@link OffsetDateTimeTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class OffsetDateTimeTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    OffsetDateTime time = OffsetDateTime.of(2014,3,30,0,56,53,512000000,ZoneOffset.ofHours(1));
    String json = gson.toJson(time);
    assertEquals("\"2014-03-30T00:56:53.512+01:00\"", json);
  }

  @Test
  public void testDeserialization() {
    OffsetDateTime time = gson.fromJson("'2014-03-30T00:56:53.512+01:00'", OffsetDateTime.class);
    assertEquals(ZoneOffset.ofHours(1), time.getOffset());
    assertEquals(2014, time.getYear());
    assertEquals(3, time.getMonthValue());
    assertEquals(30, time.getDayOfMonth());
    assertEquals(0, time.getHour());
    assertEquals(56, time.getMinute());
    assertEquals(53, time.getSecond());
    assertEquals(512000000, time.getNano());
  }
}
