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

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.joda.time.MonthDay;

import java.io.IOException;

/**
 * Type adapter for joda-time {@link MonthDay} class.
 *
 * @author Christophe Bornet
 */
public class MonthDayTypeAdapter extends ToStringSerializedTypeAdapter<MonthDay> {

  @Override
  public MonthDay read(JsonReader in) throws IOException {
    if (in.peek() == JsonToken.NULL) {
      in.nextNull();
      return null;
    }
    return MonthDay.parse(in.nextString());
  }
}
