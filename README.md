[![](https://jitpack.io/v/tegorov/allure-environment-maven-plugin.svg)](https://jitpack.io/#tegorov/allure-environment-maven-plugin)

# Allure Environment Maven Plugin

This plugin generates [environment.properties](https://docs.qameta.io/allure/#_environment) for Allure report.

![Allure Report](.github/preview_report.png)

# Getting Started

* Add the JitPack repository into your `pom.xml`:
```xml
<project>
    ...
    <pluginRepositories>
        <pluginRepository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </pluginRepository>
    </pluginRepositories>
    ...
</project>
```

* Add the plugin:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.github.tegorov</groupId>
            <artifactId>allure-environment-maven-plugin</artifactId>
            <version>0.1.2</version>
            <executions>
                <execution>
                    <goals>
                        <goal>allure-environment</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

# Configuration

* Add properties to the plugin configuration:
```xml
<plugin>
    <groupId>com.github.tegorov</groupId>
    <artifactId>allure-environment-maven-plugin</artifactId>
    ...
    <configuration>
        <properties>
            <property>
                <name>os.name</name>
                <value>${os.name}</value>
            </property>
            <property>
                <name>java.version</name>
                <value>${java.version}</value>
            </property>
            <property>
                <name>junit.version</name>
                <value>${junit.version}</value>
            </property>
            <property>
                <name>branch.name</name>
                <value>${branch.name}</value>
            </property>
        </properties>
    </configuration>
</plugin>
```

You can pass property value via the command line:
```shell
mvn clean test -Dbranch.name=develop
```

* `environment.properties` will be generated tо directory: `target/allure-results`

You can change this directory:
```xml
<plugin>
    <groupId>com.github.tegorov</groupId>
    <artifactId>allure-environment-maven-plugin</artifactId>
    ...
    <configuration>
        <allureResultsDirectory>newtarger/allure-results</allureResultsDirectory>
        ...
    </configuration>
</plugin>
```
