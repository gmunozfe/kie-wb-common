/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.dmn.api.property.dmn;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.soup.commons.validation.PortablePreconditions;
import org.kie.workbench.common.dmn.api.property.DMNProperty;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.FieldDefinition;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.FieldReadOnly;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.FieldValue;
import org.kie.workbench.common.forms.adf.definitions.annotations.metaModel.I18nMode;
import org.kie.workbench.common.stunner.core.definition.annotation.Property;
import org.kie.workbench.common.stunner.core.definition.annotation.property.ReadOnly;
import org.kie.workbench.common.stunner.core.definition.annotation.property.Value;

@Portable
@Bindable
@Property
@FieldDefinition(i18nMode = I18nMode.OVERRIDE_I18N_KEY)
public class QName implements DMNProperty {

    public static final String NULL_NS_URI = "";

    public static final String DEFAULT_NS_PREFIX = "";

    @ReadOnly
    @FieldReadOnly
    public static final Boolean readOnly = true;

    @Value
    @FieldValue
    private String value;

    private String namespaceURI;

    private String localPart;

    private String prefix;

    public QName() {
        this(NULL_NS_URI,
             "string",
             "feel");
    }

    public QName(final String namespaceURI,
                 final String localPart) {
        this(namespaceURI,
             localPart,
             DEFAULT_NS_PREFIX);
    }

    public QName(final String namespaceURI,
                 final String localPart,
                 final String prefix) {
        if (namespaceURI == null) {
            this.namespaceURI = NULL_NS_URI;
        } else {
            this.namespaceURI = namespaceURI;
        }
        this.localPart = PortablePreconditions.checkNotNull("localPart", localPart);
        this.prefix = PortablePreconditions.checkNotNull("prefix", prefix);

        //For now we simply store the String representation of the QName
        //This will change when we add support for Data Types.
        this.value = getStringRepresentation();
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public String getLocalPart() {
        return localPart;
    }

    public String getPrefix() {
        return prefix;
    }

    private String getStringRepresentation() {
        if (namespaceURI.equals(NULL_NS_URI)) {
            return localPart;
        } else {
            return "{" + namespaceURI + "}" + localPart;
        }
    }

    @Override
    public String toString() {
        return getStringRepresentation();
    }

    /**
     * See {@link javax.xml.namespace.QName#equals(Object)}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QName qName = (QName) o;

        if (!namespaceURI.equals(qName.namespaceURI)) {
            return false;
        }
        return localPart.equals(qName.localPart);
    }

    /**
     * See {@link javax.xml.namespace.QName#hashCode()}
     */
    @Override
    public int hashCode() {
        int result = namespaceURI.hashCode();
        result = ~~result;
        result = 31 * result + localPart.hashCode();
        result = ~~result;
        return result;
    }
}