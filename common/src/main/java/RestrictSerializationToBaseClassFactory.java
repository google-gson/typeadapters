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

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Use this type adapter factory to restrict your JSON API. Extend the JSON API classes in server,
 * and serialize them without serializing additional elements.
 *
 * @author Inderjeet Singh
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class RestrictSerializationToBaseClassFactory<B> implements TypeAdapterFactory {

  private final Class<? extends B>[] subClasses;
  private final Class<B> baseClass;

  /** Restricts all sub-classes of the specified base class for serialization */
  public RestrictSerializationToBaseClassFactory(Class<B> baseClass) {
    this.baseClass = baseClass;
    this.subClasses = null;
  }

  /**
   * Restricts the serialization of the specified set of sub-classes to the specified base class.
   * @param subClasses if null, all sub-classes are restricted.
   */
  public RestrictSerializationToBaseClassFactory(Class<B> baseClass, Class<? extends B>... subClasses) {
    this.subClasses = subClasses;
    this.baseClass = baseClass;
  }

  @Override
  public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
    Class incoming = type.getRawType();
    if (subClasses != null) {
      boolean matched = false;
      for (Class<? extends B> subClass : subClasses) {
        if (incoming == subClass) {
          matched = true;
          break;
        }
      }
      if (!matched) return null;
    } else { // restricting all sub-classes since none specified
      // don't match the baseClass since we want to use the default factory for it
      if (incoming == baseClass || !baseClass.isAssignableFrom(incoming)) return null;
    }
    TypeAdapter<T> adapter = new TypeAdapter<T>() {
      @Override
      public T read(JsonReader reader) throws IOException {
        final TypeAdapter subAdapter = gson.getDelegateAdapter(
            RestrictSerializationToBaseClassFactory.this, type);
        return (T) subAdapter.read(reader);
      }

      @Override
      public void write(JsonWriter writer, T value) throws IOException {
        final TypeAdapter<B> baseAdapter = gson.getAdapter(baseClass);
        baseAdapter.write(writer, (B) value);
      }
    };
    return adapter;
  }
}
