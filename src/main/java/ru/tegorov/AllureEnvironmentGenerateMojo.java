package ru.tegorov;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Mojo(name = "allure-environment", defaultPhase = LifecyclePhase.TEST)
public class AllureEnvironmentGenerateMojo extends AbstractMojo {

    private static final String ALLURE_ENVIRONMENT_PROPERTIES = "environment.properties";

    @Parameter(required = true, readonly = true)
    protected Properties properties;

    @Parameter(defaultValue = "${project.build.directory}", readonly = true)
    protected String buildDirectory;

    @Parameter(property = "allure.results.directory", defaultValue = "allure-results", readonly = true)
    protected String allureResultsDirectory;

    public void execute() throws MojoExecutionException {
        getLog().info("Generate Allure Environment to " + allureResultsDirectory);

        if (properties.size() == 0) {
            getLog().warn("Allure Environment was skipped because properties is empty");
            return;
        }

        Path targetPath = Paths.get(buildDirectory);
        Path allureResultsPath = Paths.get(allureResultsDirectory);
        File allureResultsFile = targetPath.resolve(allureResultsPath).toFile();
        allureResultsFile.mkdirs();

        File propertiesFile = new File(allureResultsFile, ALLURE_ENVIRONMENT_PROPERTIES);
        if (propertiesFile.exists()) {
            propertiesFile.delete();
        }

        try (FileOutputStream outputStrem = new FileOutputStream(propertiesFile)) {;
            properties.store(outputStrem, null);
            getLog().info("Allure Environment generated successfully");
        } catch (IOException e) {
            getLog().error("Generation error", e);
        }
    }
}
