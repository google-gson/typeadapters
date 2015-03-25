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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Unit tests for {@link BundleTypeAdapterFactory}
 *
 * @author Inderjeet Singh
 */
@Config(manifest = Config.NONE, emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class BundleTypeAdapterFactoryTest {

    private final Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(new BundleTypeAdapterFactory())
      .create();

    @Test
    public void testSerialize() {
        Bundle bundle = new Bundle();
        bundle.putInt("a", 1);
        bundle.putString("b", "b value");
        JsonObject json = gson.toJsonTree(bundle).getAsJsonObject();
        assertEquals(2, json.entrySet().size());
        assertEquals("b value", json.get("b").getAsString());
        assertEquals(1, json.get("a").getAsInt());
    }

    @Test
    public void testDeserialize() {
        String json = "{'a':1,'b':'abcd'}";
        Bundle bundle = gson.fromJson(json, Bundle.class);
        assertEquals(1, bundle.getInt("a"));
        assertEquals("abcd", bundle.getString("b"));
    }

    @Test
    public void testEmpty() {
        Bundle bundle = new Bundle();
        String json = gson.toJson(bundle);
        assertEquals("{}", json);
        json = "{}";
        bundle = gson.fromJson(json, Bundle.class);
        assertEquals(0, bundle.size());
    }

    @Test
    public void testBundleOfBundles() {
        Bundle outer = new Bundle();
        outer.putString("a", "abc");
        Bundle inner = new Bundle();
        inner.putString("b", "bcd");
        outer.putParcelable("c", inner);
        JsonObject jsonObj = gson.toJsonTree(outer).getAsJsonObject();
        assertEquals("bcd", jsonObj.get("c").getAsJsonObject().get("b").getAsString());

        String json = "{a:1,b:{c:2},c:{d:3}}";
        Bundle bundle = gson.fromJson(json, Bundle.class);
        assertEquals(3, ((Bundle)bundle.get("c")).get("d"));
    }
}
