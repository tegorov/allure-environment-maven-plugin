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
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Mojo(name = "allure-env", defaultPhase = LifecyclePhase.COMPILE)
public class AllureEnvMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(
            property = "allure-results-path",
            defaultValue = "target/allure-results",
            readonly = true
    )
    String allureResultsPath;

    public void execute() throws MojoExecutionException {
        if (allureResultsPath == null || allureResultsPath.isEmpty()) {
            getLog().error("Parameter allureResultsPath is null or empty");
            return;
        }

        File allureResultsDir = new File(allureResultsPath);
        if (!allureResultsDir.exists()) {
            getLog().error("Not found allure results directory");
            return;
        }

        Properties properties = new Properties();
        properties.put("os.name", System.getProperty("os.name"));
        properties.put("os.arch", System.getProperty("os.arch"));
        properties.put("os.version", System.getProperty("os.version"));
        properties.put("java.version", System.getProperty("java.version"));

        File envPropertiesFile = new File(allureResultsDir, "environment.properties");
        if (envPropertiesFile.exists()) {
            envPropertiesFile.delete();
        }

        try {
            FileOutputStream outputStrem = new FileOutputStream(envPropertiesFile);
            properties.store(outputStrem, null);
            getLog().info("environment.properties file created for allure report");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
