/*
 * Copyright 2010 University Corporation for Advanced Internet Development, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.samlext.saml2mdui.impl;

import org.opensaml.common.impl.AbstractSAMLObjectBuilder;
import org.opensaml.samlext.saml2mdui.DiscoHints;
import org.opensaml.samlext.saml2mdui.GeolocationHint;

/**
 * Builder of {@link  .GeolocationHint} objects.
 */
public class GeolocationHintBuilder extends AbstractSAMLObjectBuilder<GeolocationHint> {

    /**
     * Constructor.
     */
    public GeolocationHintBuilder() {

    }

    /** {@inheritDoc} */
    public GeolocationHint buildObject() {
        return buildObject(DiscoHints.MDUI_NS, 
                           GeolocationHint.DEFAULT_ELEMENT_LOCAL_NAME, 
                           DiscoHints.MDUI_PREFIX);
    }

    /** {@inheritDoc} */
    public GeolocationHint buildObject(String namespaceURI, String localName, String namespacePrefix) {
        return new GeolocationHintImpl(namespaceURI, localName, namespacePrefix);
    }
}