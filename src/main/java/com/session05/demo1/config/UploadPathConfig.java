package com.session05.demo1.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class UploadPathConfig {

    private static final String UPLOAD_DIR_PROPERTY = "demo1.upload.dir";
    private static final Path DEFAULT_UPLOAD_DIR = Paths
            .get("/Users/anlinh/Study/Java Web Application CamLinh/IT210-Session10/demo1/uploads")
            .toAbsolutePath()
            .normalize();

    private UploadPathConfig() {
    }

    public static Path resolveUploadDir() {
        String configuredPath = System.getProperty(UPLOAD_DIR_PROPERTY);
        if (configuredPath != null && !configuredPath.isBlank()) {
            return Paths.get(configuredPath).toAbsolutePath().normalize();
        }

        return DEFAULT_UPLOAD_DIR;
    }

    public static String uploadResourceLocation() {
        return resolveUploadDir().toUri().toString();
    }
}



