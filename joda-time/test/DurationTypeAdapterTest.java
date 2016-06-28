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
import org.joda.time.Duration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link DurationTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class DurationTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(Duration.class, new DurationTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    Duration duration = Duration.millis(1396141013512L);
    String json = gson.toJson(duration);
    assertEquals("\"PT1396141013.512S\"", json);
  }

  @Test
  public void testDeserialization() {
    Duration duration = gson.fromJson("\"PT1396141013.512S\"", Duration.class);
    assertEquals(1396141013512L, duration.getMillis());
  }
}
