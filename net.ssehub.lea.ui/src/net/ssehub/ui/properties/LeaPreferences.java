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
package net.ssehub.ui.properties;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import net.ssehub.utilities.AbstractLogger;
import net.ssehub.utilities.LoggerFactory;

/**
 * This class provides methods for storing and retrieving LEA-specific preferences to/from the Eclipse preferences
 * store.
 * 
 * @author Christian Kroeher
 *
 */
public class LeaPreferences {
    
    /**
     * The identifier of this class, e.g. for printing messages.
     */
    private static final String ID = "LeaUiModule";
    
    /**
     * The constant qualifier of the LEA-specific preferences in the Eclipse preferences store.
     */
    private static final String LEA_PREFERENCES_QUALIFIER = "net.ssehub.lea.preferences";
    
    /**
     * The constant key for the plug-in paths (values) in the LEA-specific preferences.
     */
    private static final String PLUGIN_PATHS_KEY = "pluginPaths";
    
    /**
     * The constant plug-in path separator to separate individual plug-in paths within a single string.
     */
    private static final char PLUGIN_PATHS_SEPARATOR = ';';
    
    /**
     * The current logger to use for printing information.
     */
    private static AbstractLogger logger = LoggerFactory.INSTANCE.getLogger();

    /**
     * Returns the {@link List} of all plug-in paths (strings) stored in the LEA-specific preferences.
     * 
     * @return the {@link List} of all plug-in paths (strings) stored in the LEA-specific preferences or
     *         <code>null</code>, if that preference does not exist; may be <i>empty</i>, if no path was stored
     */
    public static List<String> getPluginPaths() {
        List<String> pluginPaths = null;
        IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode(LEA_PREFERENCES_QUALIFIER);
        if (preferences != null) {
            String pluginPathsString = preferences.get(PLUGIN_PATHS_KEY, null);
            if (pluginPathsString != null) {
                char currentChar;
                StringBuilder pluginPathStringBuilder = new StringBuilder();
                pluginPaths = new ArrayList<String>();
                for (int i = 0; i < pluginPathsString.length(); i++) {
                    currentChar = pluginPathsString.charAt(i);
                    if (currentChar == PLUGIN_PATHS_SEPARATOR) {
                        pluginPaths.add(pluginPathStringBuilder.toString());
                        pluginPathStringBuilder.setLength(0); // Reset the string builder
                    } else {
                        pluginPathStringBuilder.append(currentChar);
                    }
                }
            }
        }
        return pluginPaths;
    }
    
    /**
     * Stores the given plug-in paths (string array elements) in the LEA-specific preferences of the Eclipse preferences
     * store using the {@link #PLUGIN_PATHS_KEY}.<br>
     * <br>
     * <b>Note</b> that calling this method will first clear the current entries in the preferences.
     * 
     * @param pluginPaths the array of {@link String}s, each denoting a specific plug-in path to store; may be
     *        <code>null</code> or <i>empty</i>, which leads to clearing the current entries in the preferences
     */
    public static void setPluginPaths(String[] pluginPaths) {
        IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode(LEA_PREFERENCES_QUALIFIER);
        if (preferences != null) {
            try {
                preferences.clear();
                preferences.flush();
                if (pluginPaths != null && pluginPaths.length > 0) {
                    StringBuilder pluginPathsStringBuilder = new StringBuilder();
                    for (String pluginPath : pluginPaths) {
                        pluginPathsStringBuilder.append(pluginPath);
                        pluginPathsStringBuilder.append(PLUGIN_PATHS_SEPARATOR);
                    }
                    preferences.put(PLUGIN_PATHS_KEY, pluginPathsStringBuilder.toString());
                    preferences.flush();
                }
            } catch (BackingStoreException e) {
                logger.logException(ID, "Storing plug-in paths in preferences failed", e);
            }
        }
    }
}
