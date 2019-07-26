/*
 * Copyright 2019 University of Hildesheim, Software Systems Engineering
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.ssehub.integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class TODO.
 * 
 * @author Christian Kroeher
 *
 */
public class LanguageElementProvider {
    
    /**
     * This inner class defines a custom exception that is thrown, if the {@link LanguageElementProvider} encounters
     * errors or other problems while trying to detect external language elements. It enables propagating such errors
     * to other classes that use this provider as well as internal error handling.
     * 
     * @author Christian Kroeher
     *
     */
    protected class ExternalElementException extends Exception {
        
        /**
         * The id of this serializable class.
         */
        private static final long serialVersionUID = 7628457858659845337L;

        /**
         * Constructs a new {@link ExternalElementException} with the given message.
         * 
         * @param message the description of the error causing this exception
         */
        private ExternalElementException(String message) {
            super(message);
        }
        
        /**
         * Constructs a new {@link ExternalElementException} with the given message and cause.
         * 
         * @param message the specific description of the error causing this exception
         * @param cause the {@link Throwable} representing the actual cause of this exception
         */
        private ExternalElementException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * The file extension including "." of a Java archive file.
     */
    private static final String JAVA_ARCHIVE_FILE_EXTENSION = ".jar";
    
    /**
     * The file extension including "." of a Java class file.
     */
    private static final String JAVA_CLASS_FILE_EXTENSION = ".class";

    /**
     * TODO.
     */
    protected LanguageElementProvider() {
        
    }
    
    /**
     * TODO.
     * @param pluginDirectory TODO
     * @throws ExternalElementException if the given directory is <code>null</code>, does not exist, is not a directory,
     *         or detecting language elements from the plug-ins in that directory causes an internal error; it is 
     *         <b>not</b> thrown, if no plug-ins or elements could be found
     */
    protected void detectLanguageElements(File pluginDirectory) throws ExternalElementException {
        checkDirectory(pluginDirectory);
        List<File> plugins = getJarFiles(pluginDirectory);
        URL[] pluginUrls = getPluginUrls(plugins);
        for (File plugin : plugins) {
            detectLanguageElements(plugin, pluginUrls);
        }
    }
    
    /**
     * Detects language elements declared in the classes of the given {@link File}, which is assumed to be a Java
     * archive file, and adds these elements to the TODO. Note that, if the given plug-in could not be read to detect
     * language elements, the user will be informed by TODO. There is no further error propagation at this point, as
     * this method is called for each plug-in individually and, hence, an error for one plug-in should not prevent
     * reading other plug-ins.
     *  
     * @param plugin the {@link File} denoting a Java archive file in which all classes will be scanned for declaring
     *        language elements; should never be <code>null</code> 
     * @param pluginUrls the array of {@link URL}s of all available Java archive files for class loading; although
     *        this method only processes a single plug-in (Java archive file), that plug-in may depend on other
     *        plug-ins, which have to be available for this method to detect language elements in the given plug-in;
     *        should never be <code>null</code>
     */
    private void detectLanguageElements(File plugin, URL[] pluginUrls) {
        try {
            List<String> pluginClassNames = getPluginClassNames(plugin);
            Class<?> pluginClass;
            for (String pluginClassName : pluginClassNames) {
                try {
                    pluginClass = Class.forName(pluginClassName, false, new URLClassLoader(pluginUrls));
                    /*
                     * TODO Creating the actual language elements based on the loaded plug-in class should be done in a 
                     * separate class. First, as this class would become to large and, second, because this "extraction"
                     * is a different concern than loading the classes here.
                     * 
                     * Old call: scanClassForAnnotation(pluginClass, plugin);
                     */
                } catch (LinkageError | ClassNotFoundException | SecurityException e) {
                    throw new ExternalElementException("Could not load class \"" + pluginClassName + "\"", e);
                } catch (NullPointerException e) {
                    throw new ExternalElementException("Could not load class due to plug-in URLs being null", e);
                }
            }
        } catch (ExternalElementException e) {
            /*
             * Problems reading a particular plug-in should not be propagated to the caller, as this is a 
             * plug-in-specific problem, which should not influence or abort reading other plug-ins. Hence, we only
             * inform the user about not using the plug-in for which the exception is thrown. 
             */
            /*
             * TODO how to inform the user: print to console in DEBUG or in Error Log of the Eclipse instance?
             * For now, simply print the stack trace.
             */
            e.printStackTrace();
        } 
    }
    
    /**
     * Returns a list of names of all classes detected in the given {@link File}, which is assumed to be a Java archive
     * file. Hence, this method only considers those entries in that archive, which have a 
     * {@link #JAVA_CLASS_FILE_EXTENSION}. The returned class names are fully-qualified.
     * 
     * @param plugin the {@link File} denoting a Java archive file from which all fully-qualified class names should be
     *        returned; should never be <code>null</code> 
     * @return a list of {@link String}s representing fully-qualified class names; never <code>null</code>, but may be
     *         <i>empty</i>
     * @throws ExternalElementException if the given file could not be found, its content could not be read, or 
     *         the archive entries could not be read
     */
    private List<String> getPluginClassNames(File plugin) throws ExternalElementException {
        List<String> pluginClassNames = new ArrayList<String>();
        FileInputStream fileInputStream = null;
        ZipInputStream zipInputStream = null;
        try {
            fileInputStream = new FileInputStream(plugin.getAbsolutePath());
            zipInputStream = new ZipInputStream(fileInputStream);
            ZipEntry zipEntry;
            String zipEntryName;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (!zipEntry.isDirectory()) {
                    zipEntryName = zipEntry.getName();
                    if (zipEntryName.endsWith(JAVA_CLASS_FILE_EXTENSION)) {
                        pluginClassNames.add(zipEntryName.substring(0,
                                zipEntryName.length() - JAVA_CLASS_FILE_EXTENSION.length()).replace('/', '.'));
                    }
                }
            }
        } catch (FileNotFoundException | SecurityException e) {
            throw new ExternalElementException("Could not create file input stream for file \"" 
                    + plugin.getAbsolutePath() + "\"", e);
        } catch (IOException e) {
            throw new ExternalElementException("Could not read entry from zip file \"" + plugin.getAbsolutePath() 
                    + "\"", e);
        } finally {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    throw new ExternalElementException("Could not close zip input stream for file \"" 
                            + plugin.getAbsolutePath() + "\"", e);
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new ExternalElementException("Could not close file input stream for file \"" 
                            + plugin.getAbsolutePath() + "\"", e);
                }
            }
        }
        return pluginClassNames;
    }
    
    /**
     * Returns the {@link URL}s of the given {@link File}s, typically denoting Java archive files, as an array.
     * 
     * @param plugins the list of {@link Files} for which the {@link URL}s should be returned; should never be
     *        <code>null</code>, but may be <i>empty</i>
     * @return the array of {@link URL}s of the given {@link File}s; never <code>null</code>, but may be <i>empty</i>
     * @throws ExternalElementException if the {@link URI} or the {@link URL} of a file could not be determined
     */
    private URL[] getPluginUrls(List<File> plugins) throws ExternalElementException {
        URL[] pluginUrls = new URL[plugins.size()];
        for (int i = 0; i < pluginUrls.length; i++) {
            try {
                pluginUrls[i] = plugins.get(i).toURI().toURL();
            } catch (SecurityException e) {
                throw new ExternalElementException("Could not determine the URI of plugin \"" 
                        + plugins.get(i).getAbsolutePath() + "\"", e);
            } catch (IllegalArgumentException | MalformedURLException e) {
                throw new ExternalElementException("Could not determine the URL of plugin \"" 
                        + plugins.get(i).getAbsolutePath() + "\"", e);
            }
        }
        return pluginUrls;
    }
    
    /**
     * Returns a list of all {@link File}s in the given directory and its sub-directories that have a 
     * {@link #JAVA_ARCHIVE_FILE_EXTENSION}.
     * 
     * @param pluginDirectory the root directory in which Java archive files should be detected; should never be
     *        <code>null</code>, but always a directory
     * @return a list of Java archive files found in the given directory and its sub-directories; never
     *         <code>null</code>, but may be <i>empty</i>
     */
    private List<File> getJarFiles(File pluginDirectory) {
        List<File> jarFiles = new ArrayList<File>();
        File[] nestedFiles = pluginDirectory.listFiles();
        File nestedFile;
        for (int i = 0; i < nestedFiles.length; i++) {
            nestedFile = nestedFiles[i];
            if (nestedFile.isDirectory()) {
                jarFiles.addAll(getJarFiles(nestedFile));
            } else if (nestedFile.getName().endsWith(JAVA_ARCHIVE_FILE_EXTENSION)) {
                jarFiles.add(nestedFile);
            }
        }
        return jarFiles;
    }
    
    /**
     * Checks the given {@link File} for being not <code>null</code>, for existence, and for being a directory.
     * 
     * @param directory the {@link File} to be checked
     * @throws ExternalElementException if the given file is <code>null</code>, does not exist, or is not a directory
     */
    private void checkDirectory(File directory) throws ExternalElementException {
        if (directory == null) {
            throw new ExternalElementException("The plug-in directory is null");
        } else if (!directory.exists()) {
            throw new ExternalElementException("The plug-in directory \"" + directory.getAbsolutePath() 
                    + "\" does not exist");
        } else if (!directory.isDirectory()) {
            throw new ExternalElementException("The plug-in directory \"" + directory.getAbsolutePath() 
                    + "\" is not a directory");
        }
    }
}
