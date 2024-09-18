package com.k12code.spring.component;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Carl
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "java")
public class Properties {

    private String home;

    private String version;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
