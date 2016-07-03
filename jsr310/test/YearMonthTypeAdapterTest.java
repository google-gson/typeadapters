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

import java.time.YearMonth;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link YearMonthTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class YearMonthTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(YearMonth.class, new YearMonthTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    YearMonth yearMonth = YearMonth.of(2014, 3);
    String json = gson.toJson(yearMonth);
    assertEquals("\"2014-03\"", json);
  }

  @Test
  public void testDeserialization() {
    YearMonth yearMonth = gson.fromJson("'2014-03'", YearMonth.class);
    assertEquals(2014, yearMonth.getYear());
    assertEquals(3, yearMonth.getMonthValue());
  }
}
