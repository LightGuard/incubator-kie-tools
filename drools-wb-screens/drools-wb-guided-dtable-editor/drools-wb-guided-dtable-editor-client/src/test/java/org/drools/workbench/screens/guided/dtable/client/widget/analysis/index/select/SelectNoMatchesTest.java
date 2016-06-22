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
package org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.select;

import java.util.Collection;

import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.HasKeys;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.KeyDefinition;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.KeyTreeMap;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.Key;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.UUIDKey;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.matchers.ExactMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectNoMatchesTest {

    private Select<Address> select;

    @Before
    public void setUp() throws Exception {
        final KeyTreeMap<Address> map = new KeyTreeMap<>();
        final Address object = new Address();
        map.put( object );

        select = new Select<>( map.get( UUIDKey.UNIQUE_UUID ),
                               new ExactMatcher( KeyDefinition.newKeyDefinition().withId( "name" ).build(),
                                                 "Toni" ) );
    }

    @Test
    public void testAll() throws Exception {
        final Collection<Address> all = select.all();

        assertTrue( all.isEmpty() );
    }

    @Test
    public void testFirst() throws Exception {
        assertNull( select.first() );
    }

    @Test
    public void testLast() throws Exception {
        assertNull( select.last() );
    }

    private class Address
            implements HasKeys {
        @Override
        public Key[] keys() {
            return new Key[]{
                    new UUIDKey( this )
            };
        }
    }
}