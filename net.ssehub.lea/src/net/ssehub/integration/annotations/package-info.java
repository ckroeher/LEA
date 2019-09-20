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
/**
 * This package contains the custom annotations for marking external classes, attributes, or methods as representing a
 * language element. The @{@link net.ssehub.integration.ExternalLanguageElementCreator} scans external classes loaded by
 * the {@link net.ssehub.integration.LanguageElementProvider} for these annotations and creates the respective
 * {@link net.ssehub.integration.LanguageElement}s for the {@link net.ssehub.integration.LanguageRegistry}.
 * 
 * @author Christian Kroeher
 *
 */
package net.ssehub.integration.annotations;