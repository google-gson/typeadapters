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
import java.io.IOException;
import java.util.Date;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * A Type adapter for {@link Date} that uses millisecond long values to write out date.
 * Remember to use an appropriate long serialization policy to avoid JSON parsers trimming
 * longs to a shorter value.
 *
 * @author Inderjeet Singh
 */
public class LongDateTypeAdapter extends TypeAdapter<Date> {

  @Override
  public void write(JsonWriter out, Date value) throws IOException {
    if (value == null) {
      out.nullValue();
    } else {
      out.value(String.valueOf(value.getTime()));
    }
  }

  @Override
  public Date read(JsonReader in) throws IOException {
    switch (in.peek()) {
    case NULL: return null;
    case STRING: 
      try {
        return new Date(Long.parseLong(in.nextString()));
      } catch (NumberFormatException e) {
        throw new JsonSyntaxException(e);
      }
    default: throw new JsonSyntaxException("invalid date" + in.getPath());
    }
  }
}
