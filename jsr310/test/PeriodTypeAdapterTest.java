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

import java.time.Period;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link PeriodTypeAdapter}.
 *
 * @author Christophe Bornet
 */
public class PeriodTypeAdapterTest {
  private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(Period.class, new PeriodTypeAdapter())
    .create();

  @Test
  public void testSerialization() {
    Period period = Period.of(1,3,30);
    String json = gson.toJson(period);
    assertEquals("\"P1Y3M30D\"", json);
  }

  @Test
  public void testDeserialization() {
    Period period = gson.fromJson("\"P1Y3M30D\"", Period.class);
    assertEquals(1, period.getYears());
    assertEquals(3, period.getMonths());
    assertEquals(30, period.getDays());
  }
}
