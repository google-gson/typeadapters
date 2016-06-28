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
import com.google.gson.GsonBuilder;

import org.joda.time.*;

/**
 * Helper methods to register Joda type adapters.
 *
 * @author Christophe Bornet
 */
public class JodaTypeAdapters {

  private JodaTypeAdapters() {
  }

  @Deprecated
  public static GsonBuilder registerDateMidnightTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(DateMidnight.class, new DateMidnightTypeAdapter());
  }

  public static GsonBuilder registerDateTimeTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter());
  }

  public static GsonBuilder registerDurationTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(Duration.class, new DurationTypeAdapter());
  }

  public static GsonBuilder registerInstantTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(Instant.class, new InstantTypeAdapter());
  }

  public static GsonBuilder registerIntervalTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(Interval.class, new IntervalTypeAdapter());
  }

  public static GsonBuilder registerLocalDateTimeTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());
  }

  public static GsonBuilder registerLocalDateTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
  }

  public static GsonBuilder registerLocalTimeTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter());
  }

  public static GsonBuilder registerMonthDayTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(MonthDay.class, new MonthDayTypeAdapter());
  }

  public static GsonBuilder registerPeriodTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(Period.class, new PeriodTypeAdapter());
  }

  public static GsonBuilder registerYearMonthTypeAdapter(GsonBuilder gsonBuilder) {
    return gsonBuilder.registerTypeAdapter(YearMonth.class, new YearMonthTypeAdapter());
  }

  /**
   * Helper method to register all the available Joda adapters at once.
   * @param gsonBuilder the gsonBuilder on which all the JSR310 adapters must be registered.
   * @return the gsonBuilder with the JSR310 adapters registered.
   */
  public static GsonBuilder registerJSR310TypeAdapters(GsonBuilder gsonBuilder) {
    registerDateMidnightTypeAdapter(gsonBuilder);
    registerDateTimeTypeAdapter(gsonBuilder);
    registerDurationTypeAdapter(gsonBuilder);
    registerInstantTypeAdapter(gsonBuilder);
    registerIntervalTypeAdapter(gsonBuilder);
    registerLocalDateTimeTypeAdapter(gsonBuilder);
    registerLocalDateTypeAdapter(gsonBuilder);
    registerLocalTimeTypeAdapter(gsonBuilder);
    registerMonthDayTypeAdapter(gsonBuilder);
    registerPeriodTypeAdapter(gsonBuilder);
    registerYearMonthTypeAdapter(gsonBuilder);

    return gsonBuilder;
  }


}
