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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Unit tests for {@link RestrictSerializationToBaseClassFactory}.
 *
 * @author Inderjeet Singh
 */
public class RestrictSerializationToBaseClassFactoryTest {

  @SuppressWarnings("unchecked")
  @Test
  public void testSerializeTopLevel() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(new RestrictSerializationToBaseClassFactory<Base>(Base.class, Sub1.class, Sub2.class))
      .create();
    String json = gson.toJson(new Sub1());
    assertTrue(json.contains("\"a\":1"));
    assertFalse(json.contains("b"));
    json = gson.toJson(new Sub2());
    assertTrue(json.contains("\"a\":1"));
    assertFalse(json.contains("c"));
  }

  @Test
  public void testDeserializeTopLevel() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(new RestrictSerializationToBaseClassFactory<Base>(Base.class))
      .create();
    String json = "{'a':5,'b':6,'c':7}";
    Sub1 sub1 = gson.fromJson(json, Sub1.class);
    assertEquals(5, sub1.a);
    assertEquals(6, sub1.b);
    Sub2 sub2 = gson.fromJson(json, Sub2.class);
    assertEquals(5, sub2.a);
    assertEquals(7, sub2.c);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testSerializeAsField() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(new RestrictSerializationToBaseClassFactory<Base>(Base.class, Sub1.class, Sub2.class))
      .create();
    String json = gson.toJson(new Container());
    assertTrue(json.contains("\"a\":1"));
    assertFalse(json.contains("\"b\""));
    assertFalse(json.contains("\"c\""));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testSerializeUnrestrictedSubTypeFields() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(new RestrictSerializationToBaseClassFactory<Base>(Base.class, Sub1.class))
      .create();
    String json = gson.toJson(new Container());
    assertTrue(json.contains("\"a\":1"));
    assertFalse(json.contains("\"b\""));
    assertTrue(json.contains("\"c\":3"));
  }

  @Test
  public void testDeserializeAsField() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(new RestrictSerializationToBaseClassFactory<Base>(Base.class))
      .create();
    String json = "{'sub1':{'a':5,'b':6},'sub2':{'a':5,'c':7}}";
    Container container = gson.fromJson(json, Container.class);
    Sub1 sub1 = container.sub1;
    assertEquals(5, sub1.a);
    assertEquals(6, sub1.b);
    Sub2 sub2 = container.sub2;
    assertEquals(5, sub2.a);
    assertEquals(7, sub2.c);
  }

  private static class Base {
    int a = 1;
  }

  private static class Sub1 extends Base {
    int b = 2;
  }

  private static class Sub2 extends Base {
    int c = 3;
  }

  private static class Container {
    Sub1 sub1 = new Sub1();
    Sub2 sub2 = new Sub2();
  }
}
