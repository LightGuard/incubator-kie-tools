/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class KeyDefinitionBuilderTest {

    @Test( expected = IllegalArgumentException.class )
    public void testNoIdSet() throws Exception {
        KeyDefinition.newKeyDefinition().build();
    }

    @Test
    public void testDefaults() throws Exception {
        final KeyDefinition keyDefinition = KeyDefinition.newKeyDefinition().withId( "test" ).build();
        assertFalse( keyDefinition.canBeEmpty() );
        assertFalse( keyDefinition.isValueList() );
    }

    @Test
    public void testCanBeEmpty() throws Exception {
        final KeyDefinition keyDefinition = KeyDefinition.newKeyDefinition()
                                                         .withId( "test" )
                                                         .canBeEmpty()
                                                         .build();
        assertTrue( keyDefinition.canBeEmpty() );
        assertFalse( keyDefinition.isValueList() );
    }

    @Test
    public void testIsValueList() throws Exception {
        final KeyDefinition keyDefinition = KeyDefinition.newKeyDefinition()
                                                         .withId( "test" )
                                                         .valueList()
                                                         .build();
        assertFalse( keyDefinition.canBeEmpty() );
        assertTrue( keyDefinition.isValueList() );
    }

    @Test
    public void testIsValueListThatCanBeEmpty() throws Exception {
        final KeyDefinition keyDefinition = KeyDefinition.newKeyDefinition()
                                                         .withId( "test" )
                                                         .canBeEmpty()
                                                         .valueList()
                                                         .build();
        assertTrue( keyDefinition.canBeEmpty() );
        assertTrue( keyDefinition.isValueList() );
    }
}