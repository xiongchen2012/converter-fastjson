# converter-fastjson
Simple request and response body converter for `retrofit2`(https://github.com/square/retrofit) via `fastjson`(https://github.com/alibaba/fastjson)

### Download

### Usage
<pre>
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(FastjsonConverterFactory.create())
        .client(new OkHttpClient())
        .build();
</pre>
or
<pre>
    FastJsonConfig fastjsonConfig = new FastJsonConfig();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(FastjsonConverterFactory.create().config(fastjsonConfig))
        .client(new OkHttpClient())
        .build();
</pre>

### Requirements
- fastjosn version 1.2.x or above
- retrofit version 2.x or above
