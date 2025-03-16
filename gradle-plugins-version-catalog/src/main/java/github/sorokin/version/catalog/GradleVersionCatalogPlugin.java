package github.sorokin.version.catalog;

import org.gradle.api.Plugin;
import org.gradle.api.initialization.Settings;
import org.gradle.api.initialization.resolve.DependencyResolutionManagement;
import org.gradle.api.initialization.resolve.MutableVersionCatalogContainer;

public class GradleVersionCatalogPlugin implements Plugin<Settings> {

    @Override
    public void apply(Settings settings) {
        DependencyResolutionManagement dependencyResolutionManagement = settings.getDependencyResolutionManagement();
        MutableVersionCatalogContainer versionCatalogs = dependencyResolutionManagement.getVersionCatalogs();
        versionCatalogs.create("libs", versionCatalog -> {
            versionCatalog.library("versionCatalogBom", "github.sorokin", "gradle-plugins-version-catalog-bom").version("0.1.0");

            versionCatalog.library("lombok", "org.projectlombok", "lombok").withoutVersion();
        });
    }
}
