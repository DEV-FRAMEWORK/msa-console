package com.msa.console.common;
/*package com.sicc.console.common;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.ServletContextAware;
 
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
public class JarFileResourcesExtractor implements ServletContextAware {

private String resourcePathPattern = "WEB-INF/**";
private String resourcePathPrefix = "WEB-INF/";
private String destination = "/WEB-INF/";
private ServletContext servletContext;
private AntPathMatcher pathMatcher = new AntPathMatcher();



*//**
 * Extracts the resource files found in the specified jar file into the destination path
 *
 * @throws IOException           If an IO error occurs when reading the jar file
 * @throws FileNotFoundException If the jar file cannot be found
 *//*
@PostConstruct
public void extractFiles() throws IOException {
    try {
        JarFile jarFile = (JarFile) getClass().getProtectionDomain().getCodeSource().getLocation().getContent();
        Enumeration<JarEntry> entries = jarFile.entries();
        System.out.println("Tomcat destination : " + servletContext.getRealPath(destination));
        while (entries.hasMoreElements()) {
            processJarEntry(jarFile, entries);
        }

    } catch (MalformedURLException e) {
        throw new FileNotFoundException("Cannot find jar file in libs: ");
    } catch (IOException e) {
        throw new IOException("IOException while moving resources.", e);
    }
}

private void processJarEntry(JarFile jarFile, Enumeration<JarEntry> entries) throws IOException {
    JarEntry entry = entries.nextElement();
    if (pathMatcher.match(resourcePathPattern, entry.getName())) {
        String fileName = getFileName(entry);
        if (checkFileName(fileName)) return;
        String sourcePath = getSourcePath(entry, fileName);
        if (checkSourcePath(sourcePath)) return;
        copyAndClose(jarFile.getInputStream(entry), getOutputStream(fileName, sourcePath));

    }
}

private FileOutputStream getOutputStream(String fileName, String sourcePath) throws IOException {
    File destinationFolder = new File(servletContext.getRealPath(destination)+sourcePath);
    destinationFolder.mkdirs();
    File materializedFile = new File(destinationFolder, fileName);
    materializedFile.createNewFile();
    return new FileOutputStream(materializedFile);
}

private boolean checkSourcePath(String sourcePath) {
    return sourcePath.startsWith(".idea");
}

private String getSourcePath(JarEntry entry, String fileName) {
    String sourcePath = entry.getName().replaceFirst(fileName, "");
    sourcePath = sourcePath.replaceFirst(resourcePathPrefix, "").trim();
    return sourcePath;
}

private boolean checkFileName(String fileName) {
    return fileName.trim().equals("");
}

private String getFileName(JarEntry entry) {
    return entry.getName().replaceFirst(".*\\/", "");
}

@Override
public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
}

public static int IO_BUFFER_SIZE = 8192;

private static void copyAndClose(InputStream in, OutputStream out) throws IOException {
    try {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    } finally {
        in.close();
        out.close();
    }
}
}*/