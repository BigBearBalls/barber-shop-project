package eu.senla.authservice.constant;

public class Constants {

    //JWT AUTH DATA
    public static final String TOKEN_PATTERN = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_WAS_STOLEN_LOG_MESSAGE = """
            Token was stolen!
             User email from token: %s
             User email from context: %s""";
}
