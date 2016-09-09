package com.anylife.jsont.json_null_str_obj_to;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 比如说String 是空的我希望是 "" ,结果来了null 的蛋等情况
 *
 * Created by zenglb on 2016/9/9.
 */
public class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
	@SuppressWarnings("unchecked")
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		Class<T> rawType = (Class<T>) type.getRawType();
		if (rawType != String.class) {
			return null;
		}
		return (TypeAdapter<T>) new StringNullAdapter();
	}
}

class StringNullAdapter extends TypeAdapter<String> {
   @Override
   public String read(JsonReader reader) throws IOException {
	   // TODO Auto-generated method stub
	   if (reader.peek() == JsonToken.NULL) {
		   reader.nextNull();
		   return "";
	   }
	   return reader.nextString();
   }
   @Override
   public void write(JsonWriter writer, String value) throws IOException {
	   // TODO Auto-generated method stub
	   if (value == null) {
		   writer.nullValue();
		   return;
	   }
	   writer.value(value);
   }
}