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
package net.ssehub.tests.integration;

import java.io.File;

import org.junit.BeforeClass;

import net.ssehub.integration.LanguageElement;
import net.ssehub.integration.LanguageElementCreator;

/**
 * This abstract class provides common attributes and methods for testing the creation of {@link LanguageElement}s by
 * the {@link LanguageElementCreator}.
 * 
 * @author Christian Kroeher
 *
 */
public abstract class AbstractCreationTest {
    
    /**
     * The {@link LanguageElementCreator} for testing the correct creation of {@link LanguageElement}s.
     */
    protected static LanguageElementCreator elementCreator;
    
    /**
     * The {@link File} denoting the source plug-in of the {@link Class} from which a {@link LanguageElement} should be
     * created. This is just a dummy file as it has no impact on the actual creation, but is only used as a constructor
     * parameter.
     */
    protected static File sourcePlugin = new File("./");
    
    /**
     * Prepares the elements commonly used by all unit tests defined in the extending classes.
     */
    @BeforeClass
    public static void prepare() {
        elementCreator = new LanguageElementCreator();
    }

}
