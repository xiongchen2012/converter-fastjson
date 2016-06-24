package retrofit2.converter.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

	private Type type;
	private FastJsonConfig config;

	public FastjsonResponseBodyConverter() {
	}

	public FastjsonResponseBodyConverter(Type type, FastJsonConfig config) {
		this.type = type;
		this.config = config;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return JSON.parseObject(value.byteStream(), config.getCharset(), type, config.getFeatures());
		} finally {
			value.close();
		}
	}

}
