package github.sorokin.gradle.plugins;

import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;

public class GradleBomPublishPlugin implements GradlePublishPlugin {
    @Override
    public void setupPluginExtension(Project project, GradlePublishPluginExtension extension) {
        extension.setPublicationName("bom");
        extension.setBuildComponent(project.getComponents().getByName("javaPlatform"));
        extension.setPublicationGroup(project.getGroup().toString());
        extension.setPublicationArtifactId(project.getName());
        extension.setPublicationVersion(project.getVersion().toString());
        //TODO Add other repositories as needed
        extension.setPublicationRepositories(RepositoryHandler::mavenLocal);

    }
}
