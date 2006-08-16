/*
 * Copyright [2006] [University Corporation for Advanced Internet Development, Inc.]
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

package org.opensaml.samlext.saml2mdquery.impl;

import java.util.List;

import javolution.util.FastList;

import org.opensaml.saml2.metadata.AttributeConsumingService;
import org.opensaml.samlext.saml2mdquery.AttributeQueryDescriptor;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.util.XMLObjectChildrenList;

/**
 * Concrete implementation of {@link AttributeQueryDescriptor}.
 */
public class AttributeQueryDescriptorImpl extends QueryDescriptorTypeImpl implements AttributeQueryDescriptor {

    /** Attribute consuming endpoints */
    private XMLObjectChildrenList<AttributeConsumingService> attributeConsumingServices;

    /**
     * Constructor
     * 
     * @param namespaceURI the namespace the element is in
     * @param elementLocalName the local name of the XML element this Object represents
     * @param namespacePrefix the prefix for the given namespace
     */
    protected AttributeQueryDescriptorImpl(String namespaceURI, String elementLocalName, String namespacePrefix) {
        super(namespaceURI, elementLocalName, namespacePrefix);

        attributeConsumingServices = new XMLObjectChildrenList<AttributeConsumingService>(this);
    }

    /** {@inheritDoc} */
    public List<AttributeConsumingService> getAttributeConsumingServices() {
        return attributeConsumingServices;
    }

    /** {@inheritDoc} */
    public List<XMLObject> getOrderedChildren() {
        FastList<XMLObject> children = new FastList<XMLObject>();

        children.addAll(super.getOrderedChildren());
        children.addAll(attributeConsumingServices);

        return children.unmodifiable();
    }
}