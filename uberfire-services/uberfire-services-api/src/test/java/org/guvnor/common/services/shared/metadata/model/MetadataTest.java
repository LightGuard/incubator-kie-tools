/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.guvnor.common.services.shared.metadata.model;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.impl.LockInfo;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MetadataTest {

    @Mock
    private Path path;

    private Date date = Calendar.getInstance().getTime();

    private Metadata metadata;

    @Test
    public void checkLockInfoDoesNotAffectHashCode() {
        this.metadata = new Metadata(path,
                                     path,
                                     "checkinComment",
                                     "lastContributor",
                                     "creator",
                                     date,
                                     date,
                                     "subject",
                                     "type",
                                     "externalRelation",
                                     "externalSource",
                                     "description",
                                     Collections.emptyList(),
                                     Collections.emptyList(),
                                     Collections.emptyList(),
                                     new LockInfo(false,
                                                  "",
                                                  path),
                                     false);

        final int originalHashCode = metadata.hashCode();
        metadata.setLockInfo(new LockInfo(true,
                                          "admin",
                                          path));

        assertEquals(originalHashCode,
                     metadata.hashCode());
    }
}
