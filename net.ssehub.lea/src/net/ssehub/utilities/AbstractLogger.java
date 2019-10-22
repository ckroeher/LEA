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
 * This abstract class provides the common attributes and methods that each specific logger requires or has to
 * implement.
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractLogger {

    /**
     * This enumeration defines the different types of messages. This class differentiates:
     * <ul>
     * <li>INFO: Information about current status of the tool</li>
     * <li>WARNING: Information about problems that do not influence the overall execution of the tool</li>
     * <li>ERROR: Information about problems that influence or even force stopping the execution of the tool</li>
     * <li>DEBUG: Additional information for debugging (only displayed if {@link LogLevel#DEBUG} is defined)</li>
     * </ul>
     * 
     * @author Christian Kroeher
     *
     */
    public enum MessageType { INFO, WARNING, ERROR, DEBUG };
    
    /**
     * This enumeration defines the different log levels. This class differentiates:
     * <ul>
     * <li>0 - SILENT: No information is logged, hence, there will be no messages at all</li>
     * <li>1 - STANDARD: Basic information, warnings, and errors are logged and displayed</li>
     * <li>2 - DEBUG: Similar to STANDARD, but logs and displays additional debug information</li>
     * </ul>
     * 
     * @author Christian Kroeher
     *
     */
    public enum LogLevel { SILENT, STANDARD, DEBUG };
    
    /**
     * The identifier of the specific logger instance, e.g., for printing messages created explicitly by this class.
     */
    protected String id;
    
    /**
     * The current {@link LogLevel} of this logger.
     */
    protected LogLevel logLevel;
    
    /**
     * Constructs a new {@link AbstractLogger} instance with a default log level of {@link LogLevel#STANDARD}.
     * 
     * @param id the identifier of the specific logger instance, e.g., for printing messages created explicitly by this
     *        class; should never be <code>null</code> 
     */
    protected AbstractLogger(String id) {
        this.id = id;
        logLevel = LogLevel.STANDARD;
    }
    
    /**
     * Sets the given {@link LogLevel} as the current log level of this logger.
     * 
     * @param logLevel the {@link LogLevel} to use; should never be <code>null</code>
     */
    public void setLogLevel(LogLevel logLevel) {
        if (logLevel != null) {
            this.logLevel = logLevel;
        } else {
            log(id, "New log level is \"null\"", "Keeping current log level \"" + this.logLevel.name() + "\"",
                    MessageType.WARNING);
        }
    }
    
    /**
     * Logs the given information.
     * 
     * @param origin the name of the class calling this method; should never be <code>null</code>
     * @param message the message to be displayed; should never be <code>null</code>
     * @param description optional description; can be <code>null</code> 
     * @param type the {@link MessageType} of this message; should never be <code>null</code>
     */
    public abstract void log(String origin, String message, String description, MessageType type);
    
    /**
     * Logs the given {@link Throwable} and its cause(s) as an error message.
     * 
     * @param origin the name of the class calling this method; should never be <code>null</code>
     * @param message a custom error message displayed before the throwable message and its cause(s); can be
     *        <code>null</code>
     * @param throwable the exception to be displayed; can be <code>null</code>
     */
    public abstract void logException(String origin, String message, Throwable throwable);
}
