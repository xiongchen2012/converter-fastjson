/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.converter.fastjson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.alibaba.fastjson.support.config.FastJsonConfig;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * FastJson Converter
 * 
 * @author deathdealer
 *
 */
public class FastjsonConverterFactory extends Converter.Factory {

	// Create {@code FastJsonConfig} for conversion.
	private FastJsonConfig fastJsonConfig;

	private FastjsonConverterFactory() {
		if (fastJsonConfig == null) {
			fastJsonConfig = new FastJsonConfig();
		}
	}

	/**
	 * Create your own {@code fastjsonConfig} instance
	 * 
	 * @param fastJsonConfig
	 * @return FastjsonConverterFactory
	 */
	public FastjsonConverterFactory config(FastJsonConfig fastJsonConfig) {
		this.fastJsonConfig = fastJsonConfig;
		return this;
	}

	/**
	 * Create an instance for conversion.
	 */
	public static FastjsonConverterFactory create() {
		return new FastjsonConverterFactory();
	}

	/**
	 * Returns a {@link Converter} for converting an HTTP response body to
	 * {@code type}, or null if {@code type} cannot be handled by this factory.
	 * This is used to create converters for response types such as
	 * {@code SimpleResponse} from a {@code Call<SimpleResponse>} declaration.
	 */
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		return new FastjsonResponseBodyConverter<>(type, fastJsonConfig);
	}

	/**
	 * Returns a {@link Converter} for converting {@code type} to an HTTP
	 * request body, or null if {@code type} cannot be handled by this factory.
	 * This is used to create converters for types specified by
	 * {@link Body @Body}, {@link Part @Part}, and {@link PartMap @PartMap}
	 * values.
	 */
	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
			Annotation[] methodAnnotations, Retrofit retrofit) {
		return new FastjsonRequestBodyConverter<>(fastJsonConfig);
	}

}
