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
package net.ssehub.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import net.ssehub.tests.integration.ArtifactParameterTypeCreationTests;
import net.ssehub.tests.integration.BasicLanguageElementCreatorTests;
import net.ssehub.tests.integration.FragmentParameterTypeCreationTests;
import net.ssehub.tests.integration.ResultParameterTypeCreationTests;
import net.ssehub.tests.parsing.BasicAnalysisDefinitionTests;
import net.ssehub.tests.parsing.BasicArtifactParsingTests;
import net.ssehub.tests.parsing.BasicChangeIdentifierAssignmentTests;
import net.ssehub.tests.parsing.BasicFragmentParsingTests;
import net.ssehub.tests.parsing.BasicResultParsingTests;

/**
 * This class summarizes all unit test classes into a single test suite.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
    // Parsing tests
    BasicAnalysisDefinitionTests.class,
    BasicArtifactParsingTests.class,
    BasicFragmentParsingTests.class,
    BasicResultParsingTests.class,
    BasicChangeIdentifierAssignmentTests.class,
    // Integration tests
    BasicLanguageElementCreatorTests.class,
    ArtifactParameterTypeCreationTests.class,
    FragmentParameterTypeCreationTests.class,
    ResultParameterTypeCreationTests.class
    })
public class AllTests {

}
