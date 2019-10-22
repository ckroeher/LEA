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
package net.ssehub.utilities;

/**
 * This class represents a factory for creating the specific logger instance depending on the current execution
 * environment.
 *  
 * @author Christian Kroeher
 *
 */
public class LoggerFactory {

    /**
     * The singleton instance of this {@link LoggerFactory}.
     */
    public static final LoggerFactory INSTANCE = new LoggerFactory();
    
    /**
     * The current and specific logger instance to use.
     */
    private AbstractLogger logger;
    
    /**
     * Constructs the singleton instance of this {@link LoggerFactory}.
     */
    private LoggerFactory() {
        // For now, we only use the standard logger
        logger = new StandardLogger();
    }
    
    /**
     * Returns the current and specific logger instance to use.
     * 
     * @return the current and specific logger instance to use; never <code>null</code>
     */
    public AbstractLogger getLogger() {
        return logger;
    }
    
}
