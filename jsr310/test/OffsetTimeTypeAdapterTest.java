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

import java.time.OffsetTime;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link OffsetTimeTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class OffsetTimeTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(OffsetTime.class, new OffsetTimeTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    OffsetTime time = OffsetTime.of(0,56,53,512000000, ZoneOffset.ofHours(1));
    String json = gson.toJson(time);
    assertEquals("\"00:56:53.512+01:00\"", json);
  }

  @Test
  public void testDeserialization() {
    OffsetTime time = gson.fromJson("'00:56:53.512+01:00'", OffsetTime.class);
    assertEquals(0, time.getHour());
    assertEquals(56, time.getMinute());
    assertEquals(53, time.getSecond());
    assertEquals(512000000, time.getNano());
  }
}
