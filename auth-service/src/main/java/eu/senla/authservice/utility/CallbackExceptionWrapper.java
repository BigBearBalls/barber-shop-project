package eu.senla.authservice.utility;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class CallbackExceptionWrapper {

    public static <T> T wrap(Supplier<T> wrappedFunc, Runnable exceptionCallback) {
        try {
            return wrappedFunc.get();
        } catch (Exception e) {
            exceptionCallback.run();
            throw e;
        }
    }

    public static void wrap(Runnable wrappedFunc, Runnable exceptionCallback) {
        try {
            wrappedFunc.run();
        } catch (Exception e) {
            exceptionCallback.run();
            throw e;
        }
    }
}
