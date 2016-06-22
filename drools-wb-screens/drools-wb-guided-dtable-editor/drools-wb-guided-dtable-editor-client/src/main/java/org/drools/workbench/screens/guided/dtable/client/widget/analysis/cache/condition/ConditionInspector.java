/*
 * Copyright 2011 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.condition;

import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.HumanReadable;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.IsConflicting;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.IsOverlapping;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.IsRedundant;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.IsSubsuming;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.Condition;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.Values;

public abstract class ConditionInspector<T extends Comparable<T>>
        implements IsRedundant,
                   IsOverlapping,
                   IsSubsuming,
                   IsConflicting,
                   HumanReadable {


    private Condition<T> condition;

    public ConditionInspector( final Condition<T> condition ) {
        this.condition = condition;
    }

    public T getValue() {
        if ( condition.getValues().isEmpty() ) {
            return null;
        } else {
            return ( T ) condition.getValues().get( 0 );
        }
    }

    protected boolean valueIsGreaterThanOrEqualTo( final Comparable<T> otherValue ) {
        return valueIsEqualTo( otherValue ) || valueIsGreaterThan( otherValue );
    }

    protected boolean valueIsLessThanOrEqualTo( final Comparable<T> otherValue ) {
        return valueIsEqualTo( otherValue ) || valueIsLessThan( otherValue );
    }

    protected boolean valueIsGreaterThan( final Comparable<T> otherValue ) {
        return otherValue.compareTo( getValue() ) > 0;
    }

    protected boolean valueIsLessThan( final Comparable<T> otherValue ) {
        return otherValue.compareTo( getValue() ) < 0;
    }

    protected boolean valueIsEqualTo( final Comparable<T> otherValue ) {
        if ( otherValue == null ) {
            if ( getValue() == null ) {
                return true;
            } else {
                return false;
            }
        } else {
            if ( getValue() == null ) {
                return false;
            } else {
                return otherValue.compareTo( getValue() ) == 0;
            }
        }
    }

    @Override
    public boolean isRedundant( final Object object ) {
        if ( object instanceof IsSubsuming ) {
            return subsumes( object ) && (( IsSubsuming ) object).subsumes( this );
        } else {
            return false;
        }
    }

    public Values<T> getValues() {
        return condition.getValues();
    }

    public boolean hasValue() {
        return !condition.getValues().isEmpty();
    }

    public abstract String toHumanReadableString();

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( this == obj ) {
            return true;
        }
        if ( !obj.getClass().equals( this.getClass() ) ) {
            return false;
        }
        if ( this.toHumanReadableString().equals( (( ConditionInspector ) obj).toHumanReadableString() ) ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toHumanReadableString().hashCode();
    }

}
