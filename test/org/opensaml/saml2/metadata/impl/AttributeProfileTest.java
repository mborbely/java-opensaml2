/*
 * Copyright [2005] [University Corporation for Advanced Internet Development, Inc.]
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

package org.opensaml.saml2.metadata.impl;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObjectBaseTestCase;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.AttributeProfile;

public class AttributeProfileTest extends SAMLObjectBaseTestCase {
    
    /** Expected ProfileURI */
    private String expectedProfileURI;
    
    /**
     * Constructor
     */
    public AttributeProfileTest(){
        singleElementFile = "/data/org/opensaml/saml2/metadata/impl/AttributeProfile.xml";
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        expectedProfileURI = "http://example.org";
    }

    /*
     * @see org.opensaml.common.SAMLObjectBaseTestCase#testSingleElementUnmarshall()
     */
    public void testSingleElementUnmarshall(){
        AttributeProfile profile = (AttributeProfile) unmarshallElement(singleElementFile);
        
        assertEquals("Profile URI has a value of " + profile.getProfileURI() + ", expected a value of " + expectedProfileURI, expectedProfileURI, profile.getProfileURI());
    }

    /*
     * @see org.opensaml.common.SAMLObjectBaseTestCase#testSingleElementOptionalAttributesUnmarshall()
     */
    public void testSingleElementOptionalAttributesUnmarshall(){
        // No optional attributes
    }

    /*
     * @see org.opensaml.common.SAMLObjectBaseTestCase#testSingleElementMarshall()
     */
    public void testSingleElementMarshall(){
        QName qname = new QName(SAMLConstants.SAML20MD_NS, AttributeProfile.LOCAL_NAME, SAMLConstants.SAML20MD_PREFIX);
        AttributeProfile profile = (AttributeProfile) buildSAMLObject(qname);
        
        profile.setProfileURI(expectedProfileURI);
        
        assertEquals(expectedDOM, profile);
    }

    /*
     * @see org.opensaml.common.SAMLObjectBaseTestCase#testSingleElementOptionalAttributesMarshall()
     */
    public void testSingleElementOptionalAttributesMarshall(){
        // No optional attributes
    }
}