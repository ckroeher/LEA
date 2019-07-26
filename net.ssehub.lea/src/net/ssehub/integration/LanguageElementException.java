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

/**
 * This class defines a custom exception that is thrown, if the creation of a new {@link LanguageElement}
 * instance or any internal operations during the lifetime of such an object fail. It enables propagating such
 * errors to other classes that use this element as well as internal error handling.
 * 
 * @author Christian Kroeher
 *
 */
class LanguageElementException extends Exception {
    
    /**
     * The id of this serializable class.
     */
    private static final long serialVersionUID = 8291425602113307761L;

    /**
     * Constructs a new {@link LanguageElementException} with the given message.
     * 
     * @param message the description of the error causing this exception
     */
    protected LanguageElementException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new {@link LanguageElementException} with the given message and cause.
     * 
     * @param message the specific description of the error causing this exception
     * @param cause the {@link Throwable} representing the actual cause of this exception
     */
    protected LanguageElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
