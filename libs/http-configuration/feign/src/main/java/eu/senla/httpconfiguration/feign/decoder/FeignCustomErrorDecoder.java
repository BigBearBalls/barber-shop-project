package eu.senla.httpconfiguration.feign.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.dto.ExceptionResponse;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ExternalApiException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class FeignCustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        ErrorCode errorCode = ErrorCode.ERR_UNKNOWN_CODE;
        String message = errorCode.getMessage();

        try {
            if (response.body() != null) {
                String body = readResponseBody(response.body());
                ExceptionResponse exceptionResponse = objectMapper.readValue(body, ExceptionResponse.class);
                errorCode = exceptionResponse.code();
                message = exceptionResponse.message();
            }
        } catch (Exception e) {
            log.error("Error decoding Feign exception: {}", e.getMessage());
        }
        return new ExternalApiException(message, errorCode, HttpStatus.valueOf(response.status() > 0 ? response.status()
                : HttpStatus.SERVICE_UNAVAILABLE.value()));
    }

    private String readResponseBody(Response.Body body) throws IOException {
        try (var inputStream = body.asInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
