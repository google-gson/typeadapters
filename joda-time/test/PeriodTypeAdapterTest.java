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
import org.joda.time.Period;
import org.junit.Test;

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
    Period period = new Period(2014, 3, 1, 30, 0, 56, 53, 512);
    String json = gson.toJson(period);
    assertEquals("\"P2014Y3M1W30DT56M53.512S\"", json);
  }

  @Test
  public void testDeserialization() {
    Period period = gson.fromJson("\"P2014Y3M1W30DT56M53.512S\"", Period.class);
    assertEquals(2014, period.getYears());
    assertEquals(1, period.getWeeks());
    assertEquals(3, period.getMonths());
    assertEquals(30, period.getDays());
    assertEquals(0, period.getHours());
    assertEquals(56, period.getMinutes());
    assertEquals(53, period.getSeconds());
    assertEquals(512, period.getMillis());
  }
}
