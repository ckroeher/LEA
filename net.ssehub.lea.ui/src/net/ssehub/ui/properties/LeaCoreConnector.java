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

import java.util.List;

import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageElementProvider;
import net.ssehub.integration.LanguageRegistry;
import net.ssehub.utilities.AbstractLogger;
import net.ssehub.utilities.LoggerFactory;
import net.ssehub.utilities.AbstractLogger.MessageType;

/**
 * @author Christian Kroeher
 *
 */
public class LeaCoreConnector {
    
    /**
     * The singleton instance of this {@link LeaCoreConnector}.
     */
    public static final LeaCoreConnector INSTANCE = new LeaCoreConnector();
    
    /**
     * The identifier of this class, e.g. for printing messages.
     */
    private static final String ID = "LeaCoreConnector";
    
    /**
     * The current logger to use for printing information.
     */
    private AbstractLogger logger = LoggerFactory.INSTANCE.getLogger();
    
    private LeaCoreConnector() {
        
    }

    
    public void provideLanguageElements() throws LanguageElementException {
        /*
         * TODO get the list of paths from preferences and, if not null, use the other method to load all elements from
         * all available plug-ins
         * 
         *  TODO also call the language element provider in the properties page, if the list of plug-in paths changes!
         */
        LanguageElementProvider languageElementProvider = new LanguageElementProvider();
        List<String> pluginPathList = LeaPreferences.getPluginPaths();
        if (pluginPathList == null || pluginPathList.isEmpty()) {
            logger.log(ID, "Calling language element provider to create core language elements only",
                    "No external plug-in search paths defined", MessageType.DEBUG);
            languageElementProvider.provideLanguageElements();
        } else {
            logger.log(ID, "Calling language element provider to create core and external language elements only",
                    "External plug-in search paths defined", MessageType.DEBUG);
            languageElementProvider.provideLanguageElements(pluginPathList);
            System.out.println(LanguageRegistry.INSTANCE);
        }
        logger.log(ID, "Language elements in registry: " + LanguageRegistry.INSTANCE.size(), null, MessageType.ERROR);
    }
}
