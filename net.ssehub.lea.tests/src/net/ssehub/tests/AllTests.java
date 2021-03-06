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

import net.ssehub.tests.integration.AnalysisCallCreationTests;
import net.ssehub.tests.integration.ArtifactParameterTypeCreationTests;
import net.ssehub.tests.integration.BasicLanguageElementCreatorTests;
import net.ssehub.tests.integration.BasicLanguageElementProviderTests;
import net.ssehub.tests.integration.ChangeIdentifierCreationTests;
import net.ssehub.tests.integration.ExtractorCallCreationTests;
import net.ssehub.tests.integration.FragmentParameterTypeCreationTests;
import net.ssehub.tests.integration.LanguageElementEqualityTests;
import net.ssehub.tests.integration.LanguageElementPartialEqualityTests;
import net.ssehub.tests.integration.LanguageRegistryDuplicateDetectionTests;
import net.ssehub.tests.integration.LanguageRegistryGetCallTests;
import net.ssehub.tests.integration.LanguageRegistryGetCallsTests;
import net.ssehub.tests.integration.LanguageRegistryGetChangeIdentifierTests;
import net.ssehub.tests.integration.LanguageRegistryGetChangeIdentifiersTests;
import net.ssehub.tests.integration.LanguageRegistryGetParameterTypesTests;
import net.ssehub.tests.integration.LanguageRegistryGetParamterTypeTests;
import net.ssehub.tests.integration.LanguageRegistryHasCallTests;
import net.ssehub.tests.integration.LanguageRegistryHasChangeIdentifierTests;
import net.ssehub.tests.integration.LanguageRegistryHasParameterTypeTests;
import net.ssehub.tests.integration.MemberOperationCreationTests;
import net.ssehub.tests.integration.OperationCreationTests;
import net.ssehub.tests.integration.ResultParameterTypeCreationTests;
import net.ssehub.tests.parsing.AdvancedElementDeclarationParsingTests;
import net.ssehub.tests.parsing.BasicAnalysisDefinitionParsingTests;
import net.ssehub.tests.parsing.BasicChangeIdentifierAssignmentParsingTests;
import net.ssehub.tests.parsing.BasicElementDeclarationParsingTests;

/**
 * This class summarizes all unit test classes into a single test suite.
 * 
 * @author Christian Kroeher
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
    // Parsing tests
    BasicAnalysisDefinitionParsingTests.class,
    BasicElementDeclarationParsingTests.class,
    BasicChangeIdentifierAssignmentParsingTests.class,
    AdvancedElementDeclarationParsingTests.class,
    // Integration tests
    BasicLanguageElementCreatorTests.class,
    ArtifactParameterTypeCreationTests.class,
    FragmentParameterTypeCreationTests.class,
    ResultParameterTypeCreationTests.class,
    ChangeIdentifierCreationTests.class,
    OperationCreationTests.class,
    ExtractorCallCreationTests.class,
    AnalysisCallCreationTests.class,
    MemberOperationCreationTests.class,
    
    LanguageElementEqualityTests.class,
    LanguageElementPartialEqualityTests.class,
    
    BasicLanguageElementProviderTests.class,
    
    LanguageRegistryDuplicateDetectionTests.class,
    
    LanguageRegistryHasParameterTypeTests.class,
    LanguageRegistryHasChangeIdentifierTests.class,
    LanguageRegistryHasCallTests.class,
    
    LanguageRegistryGetParamterTypeTests.class,
    LanguageRegistryGetParameterTypesTests.class,
    LanguageRegistryGetChangeIdentifierTests.class,
    LanguageRegistryGetChangeIdentifiersTests.class,
    LanguageRegistryGetCallTests.class,
    LanguageRegistryGetCallsTests.class,
    })
public class AllTests {

}
