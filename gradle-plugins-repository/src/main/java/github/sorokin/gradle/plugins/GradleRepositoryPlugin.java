package github.sorokin.gradle.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.initialization.Settings;
import org.gradle.api.initialization.resolve.RepositoriesMode;
import org.jetbrains.annotations.NotNull;

public class GradleRepositoryPlugin implements Plugin<Settings> {

    @Override
    public void apply(@NotNull Settings settings) {
        setupPluginRepositories(settings);
        setupDependencyRepositories(settings);
    }

    private void setupPluginRepositories(Settings settings) {
        var pluginManagement = settings.getPluginManagement();
        pluginManagement.repositories(repository -> {
            repository.mavenLocal();
            repository.mavenCentral();
            repository.gradlePluginPortal();
        });
    }

    @SuppressWarnings("UnstableApiUsage")
    private void setupDependencyRepositories(Settings settings) {
        var dependencyResolutionManagement = settings.getDependencyResolutionManagement();
        dependencyResolutionManagement.getRepositoriesMode().set(RepositoriesMode.PREFER_SETTINGS);
        dependencyResolutionManagement.repositories(repository -> {
            repository.mavenLocal();
            repository.mavenCentral();
            repository.gradlePluginPortal();
        });
    }
}
