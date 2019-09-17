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

/**
 * This abstract class defines the common attributes and methods for each (external) language element.
 *  
 * @author Christian Kroeher
 *
 */
public abstract class LanguageElement {

    /**
     * The {@link ElementType} of this element.
     */
    private ElementType elementType;
    
    /**
     * The name of this element.
     */
    private String name;
    
    /**
     * The {@link Class} from where this element was created.
     */
    private Class<?> sourceClass;
    
    /**
     * The {@link File}, which is a Java archive file, from where this element was created.
     */
    private File sourcePlugin;
    
    /**
     * Constructs a new {@link LanguageElement} with the given attributes.
     * 
     * @param elementType the {@link ElementType} of this new element
     * @param name the name of this new element
     * @param sourceClass the {@link Class} from where this new element is created
     * @param sourcePlugin the {@link File}, which is a Java archive file, from where this new element is created
     * @throws LanguageElementException if any of the above parameters is <code>null</code> or the name is <i>blank</i>
     */
    protected LanguageElement(ElementType elementType, String name, Class<?> sourceClass, File sourcePlugin) 
            throws LanguageElementException {
        if (elementType == null) {
            throw new LanguageElementException("The element type for the new language element is null");
        }
        if (name == null || name.isBlank()) {
            throw new LanguageElementException("The name for the new language element is null or blank");
        }
        if (sourceClass == null) {
            throw new LanguageElementException("The source class for the new language element is null");
        }
        if (sourcePlugin == null) {
            throw new LanguageElementException("The source plug-in for the new language element is null");
        }
        this.elementType = elementType;
        this.name = name;
        this.sourceClass = sourceClass;
        this.sourcePlugin = sourcePlugin;
    }
    
    /**
     * Returns the {@link ElementType} of this element.
     * 
     * @return the {@link ElementType} of this element; never <code>null</code>
     */
    public ElementType getElementType() {
        return elementType;
    }
    
    /**
     * Returns the name of this element.
     * 
     * @return the name of this element; never <code>null</code> nor <i>blank</i>
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the fully-qualified name of this element.
     * 
     * @return the fully-qualified name of this element
     */
    public abstract String getFullyQualifiedName();
    
    /**
     * Returns the {@link Class} from where this element was created.
     * 
     * @return the {@link Class} from where this element was created; never <code>null</code>
     */
    public Class<?> getSourceClass() {
        return sourceClass;
    }
    
    /**
     * Returns the {@link File}, which is a Java archive file, from where this element was created.
     * 
     * @return the {@link File} from where this element was created; never <code>null</code>
     */
    public File getSourcePlugin() {
        return sourcePlugin;
    }
    
    /**
     * Compares this {@link LanguageElement} with the given {@link LanguageElement} for equality. This is the case, if
     * both elements have the same:
     * <ul>
     * <li>{@link ElementType}</li>
     * <li>Runtime {@link Class} (retrieved via {@link LanguageElement#getClass()})</li>
     * <li>Fully-qualified name</li>
     * <li>Source {@link Class}</li>
     * <li>Source plug-in (the same absolute path of the {@link File})</li>
     * </ul>
     * @param comparable the {@link LanguageElement} to compare to this element
     * @return <code>true</code>, if this {@link LanguageElement} is equal to the given {@link LanguageElement};
     *         <code>false</code> otherwise
     */
    public boolean equals(LanguageElement comparable) {
        boolean isEqual = false;
        if (comparable != null && this.elementType == comparable.getElementType()) {
            isEqual = equalsIgnoreType(comparable);
        }
        return isEqual;
    }
    
    /**
     * Performs the same equality check as {@link #equals(LanguageElement)}, but without consideration of the 
     * {@link ElementType} of this and the given {@link LanguageElement}.
     * 
     * @param comparable the {@link LanguageElement} to compare to this element, while ignoring the {@link ElementType}
     * @return <code>true</code>, if all attributes except for the {@link ElementType} of this {@link LanguageElement}
     *         are equal to the attributes of the given {@link LanguageElement}; <code>false</code> otherwise
     */
    public boolean equalsIgnoreType(LanguageElement comparable) {
        boolean isEqual = false;
        if (comparable != null) {            
            if (this.getClass() == comparable.getClass()
                    && this.getFullyQualifiedName().equals(comparable.getFullyQualifiedName())
                    && this.sourceClass == comparable.getSourceClass()
                    && this.sourcePlugin.getAbsolutePath().equals(comparable.getSourcePlugin().getAbsolutePath())) {
                isEqual = true;
            }
        }
        return isEqual;
    }
}
