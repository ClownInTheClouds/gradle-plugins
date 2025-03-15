package github.sorokin.gradle.plugins.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface GradlePublishPlugin extends Plugin<Project> {

    @Override
    default void apply(@NotNull Project project) {
        applyMavenPublishPluginIfAbsent(project);
        initPluginExtension(project);
        setupPublishingExtension(project);
    }

    private void applyMavenPublishPluginIfAbsent(Project project) {
        var plugins = project.getPlugins();
        if (!plugins.hasPlugin(MavenPublishPlugin.class)) {
            plugins.apply(MavenPublishPlugin.class);
        }
    }

    private void initPluginExtension(Project project) {
        var extensions = project.getExtensions();
        var emisarPublish = extensions.create("emisarPublish", GradlePublishPluginExtension.class);
        setupPluginExtension(project, emisarPublish);
    }

    void setupPluginExtension(Project project, GradlePublishPluginExtension extension);

    private void setupPublishingExtension(Project project) {
        var extensions = project.getExtensions();
        var pluginExtension = getExtensionOrThrow(extensions, GradlePublishPluginExtension.class);
        var publishingExtension = getExtensionOrThrow(extensions, PublishingExtension.class);
        publishingExtension.publications(publication -> {
            var mavenPublication = publication.create(pluginExtension.getPublicationName(), MavenPublication.class);
            setupMavenPublication(mavenPublication, pluginExtension);
        });
        publishingExtension.repositories(pluginExtension.getPublicationRepositories());
    }

    private void setupMavenPublication(MavenPublication mavenPublication, GradlePublishPluginExtension pluginExtension) {
        mavenPublication.from(pluginExtension.getBuildComponent());
        mavenPublication.setGroupId(pluginExtension.getPublicationGroup());
        mavenPublication.setArtifactId(pluginExtension.getPublicationArtifactId());
        mavenPublication.setVersion(pluginExtension.getPublicationVersion());
    }

    private <T> T getExtensionOrThrow(ExtensionContainer extensions, Class<T> type) {
        return Optional.of(extensions.getByType(type))
                .orElseThrow(() -> new ExtensionNotFoundException(type));
    }
}
