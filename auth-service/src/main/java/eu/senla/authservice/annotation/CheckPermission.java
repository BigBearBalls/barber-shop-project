package eu.senla.authservice.annotation;

import eu.senla.authservice.enums.PermissionValue;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
    PermissionValue value();
}