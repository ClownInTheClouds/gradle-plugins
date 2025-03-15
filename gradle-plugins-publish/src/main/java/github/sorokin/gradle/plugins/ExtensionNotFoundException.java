package github.sorokin.gradle.plugins;

public class ExtensionNotFoundException extends RuntimeException {
    public ExtensionNotFoundException(Class<?> type) {
        super("Extension " + type.getName() + " not found");
    }
}
