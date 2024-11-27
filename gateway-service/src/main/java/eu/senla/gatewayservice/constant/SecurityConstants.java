package eu.senla.gatewayservice.constant;

public class SecurityConstants {

    //JWT AUTH DATA
    public static final String TOKEN_PATTERN = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_WAS_STOLEN_LOG_MESSAGE = """
            Token was stolen!
             User email from token: %s
             User email from context: %s""";
    public static final String API_KEY_HEADER = "X-Request-Api-Key";
    public static final String REQUEST_SOURCE_HEADER = "X-Request-Source";
    public static final String INTERNAL_REQUEST_SOURCE = "INTERNAL";
}
