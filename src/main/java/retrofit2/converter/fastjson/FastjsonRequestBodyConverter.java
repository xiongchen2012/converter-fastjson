package retrofit2.converter.fastjson;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class FastjsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

	private FastJsonConfig config;
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

	public FastjsonRequestBodyConverter(FastJsonConfig config) {
		this.config = config;
	}

	@Override
	public RequestBody convert(T value) throws IOException {
		
		SerializeConfig serializeConfig = config.getSerializeConfig();
		SerializerFeature[] serializerFeatures = config.getSerializerFeatures();
		
	    byte[] content;
	    if (serializeConfig != null) {
	      if (serializerFeatures != null) {
	        content = JSON.toJSONBytes(value, serializeConfig, serializerFeatures);
	      } else {
	        content = JSON.toJSONBytes(value, serializeConfig);
	      }
	    } else {
	      if (serializerFeatures != null) {
	        content = JSON.toJSONBytes(value, serializerFeatures);
	      } else {
	        content = JSON.toJSONBytes(value);
	      }
	    }
	    return RequestBody.create(MEDIA_TYPE, content);
	}
}
