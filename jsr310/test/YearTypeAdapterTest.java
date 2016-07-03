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

import java.time.Year;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link YearTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class YearTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(Year.class, new YearTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    Year year = Year.of(2014);
    String json = gson.toJson(year);
    assertEquals("\"2014\"", json);
  }

  @Test
  public void testDeserialization() {
    Year year = gson.fromJson("'2014'", Year.class);
    assertEquals(2014, year.getValue());
  }
}
