package eu.senla.catalog.controller.aspect;

public record ResponseException(String errorMessage,
                                int errorCode) {
}