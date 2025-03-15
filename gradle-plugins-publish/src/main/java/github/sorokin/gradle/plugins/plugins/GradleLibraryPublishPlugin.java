package github.sorokin.gradle.plugins.plugins;

import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;

public class GradleLibraryPublishPlugin implements GradlePublishPlugin {
    @Override
    public void setupPluginExtension(Project project, GradlePublishPluginExtension extension) {
        extension.setPublicationName("library");
        extension.setBuildComponent(project.getComponents().getByName("java"));
        extension.setPublicationGroup(project.getGroup().toString());
        extension.setPublicationArtifactId(project.getName());
        extension.setPublicationVersion(project.getVersion().toString());
        //TODO Add other repositories as needed
        extension.setPublicationRepositories(RepositoryHandler::mavenLocal);
    }
}
