package integration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ContextConfiguration;
@SpringBootConfiguration
@ContextConfiguration
public class TestsConfig {
    public static final int SERVER_PORT = 8888;

    public static final String HEADER_PARAM_AUTHORIZATION = "Authorization";
    public static final String HEADER_PARAM_ORIGIN = "Origin";

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_XML = "application/xml";


}
