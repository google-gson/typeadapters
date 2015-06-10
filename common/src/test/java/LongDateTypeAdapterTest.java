/*
 * Copyright (C) 2015 Gson Type Adapter Authors.
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Date;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.JsonAdapter;

/**
 * Unit tests for {@link LongDateTypeAdapter}.
 *
 * @author Inderjeet Singh
 */
public class LongDateTypeAdapterTest {

  @Test
  public void testDeserializationWithGlobalTypeAdapter() {
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LongDateTypeAdapter()).create();
    Target target = new Target(new Date(1433950601961L), new Date(10001));
    String json = gson.toJson(target);
    assertTrue(json.contains("\"first\":\"1433950601961\""));
    assertTrue(json.contains("\"second\":\"10001\""));
  }

  @Test
  public void testDeserializationWithAnnotation() {
    Gson gson = new Gson();
    Target target = new Target(new Date(1433950601961L), new Date(501520001));
    String json = gson.toJson(target);
    assertTrue(json.contains("\"first\":\"1433950601961\""));
    assertFalse(json.contains("\"second\":\"501520001\""));
    assertTrue(json.contains("1970"));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSerializationWithGlobalTypeAdapter() {
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LongDateTypeAdapter()).create();
    Target target = gson.fromJson("{'first':\"1433950601961\",'second':'501520001'}", Target.class);
    assertEquals(1433950601961L, target.first.getTime());
    assertEquals(70, target.second.getYear());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSerializationWithAnnotation() {
    Gson gson = new Gson();
    Target target = gson.fromJson("{'first':\"1433950601961\",'second':'Jan 6, 1970 11:18:40 AM'}", Target.class);
    assertEquals(1433950601961L, target.first.getTime());
    assertEquals(70, target.second.getYear());
  }

  private static final class Target {
    @JsonAdapter(LongDateTypeAdapter.class)
    Date first;
    Date second;
    public Target(Date first, Date second) { this.first = first; this.second = second; }
  }
}
