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

/**
 * 
 */

package org.opensaml.saml1.core.impl;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.opensaml.common.BaseSAMLObjectProviderTestCase;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml1.core.Assertion;
import org.opensaml.saml1.core.Response;
import org.opensaml.saml1.core.Status;

/**
 * Test class for org.opensaml.saml1.core.Response
 */
public class ResponseTest extends BaseSAMLObjectProviderTestCase {

    /** name used to generate objects */
    private final QName qname;

    /** Representation of IssueInstant in test file. */
    private final String expectedID;

    /** Representation of IssueInstant in test file. */
    private final DateTime expectedIssueInstant;

    /** Representation of InResponseTo in test file. */
    private final String expectedInResponseTo;

    /** Representation of MinorVersion in test file. */
    private final int expectedMinorVersion;

    /** Representation of Recipient in test file. */
    private final String expectedRecipient;

    /**
     * Constructor
     */
    public ResponseTest() {
        expectedID = "ident";
        singleElementFile = "/data/org/opensaml/saml1/impl/singleResponse.xml";
        singleElementOptionalAttributesFile = "/data/org/opensaml/saml1/impl/singleResponseAttributes.xml";
        childElementsFile = "/data/org/opensaml/saml1/impl/ResponseWithChildren.xml";
        //
        // IssueInstant="1970-01-01T00:00:00.100Z"
        //
        expectedIssueInstant = new DateTime(1970, 1, 1, 0, 0, 0, 100, ISOChronology.getInstanceUTC());

        expectedInResponseTo="inresponseto";
        expectedMinorVersion=1;
        expectedRecipient="recipient";
        
        qname = new QName(SAMLConstants.SAML10P_NS, Response.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML1P_PREFIX);
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {

        Response response = (Response) unmarshallElement(singleElementFile);

        String id = response.getID();
        assertNull("ID attribute has value " + id + "expected no value", id);
       
        assertNull("IssueInstant attribute has a value of " + response.getIssueInstant() 
                + ", expected no value", response.getIssueInstant());

        assertEquals("Assertion elements count", 0, response.getAssertions().size());

        Status status;
        status = response.getStatus();
        assertNull("Status element has a value of " + status + ", expected no value", status);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        Response response;
        response = (Response) unmarshallElement(singleElementOptionalAttributesFile);

        assertEquals("ID", expectedID, response.getID());
        assertEquals("IssueInstant attribute ", expectedIssueInstant, response.getIssueInstant());

        String string = response.getInResponseTo();
        assertEquals("InResponseTo attribute ", expectedInResponseTo, string);

        string = response.getRecipient();
        assertEquals("Recipient attribute ", expectedRecipient, string);

        int i = response.getMinorVersion();
        assertEquals("MinorVersion attribute ", expectedMinorVersion, i);
    }

    /** {@inheritDoc} */
    public void testChildElementsUnmarshall() {
        Response response = (Response) unmarshallElement(childElementsFile);

        assertEquals("No Assertion elements count", 1, response.getAssertions().size());

        Status status;
        status = response.getStatus();
        assertNotNull("No Status element found", status);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        assertEquals(expectedDOM, buildXMLObject(qname));
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        Response response = (Response) buildXMLObject(qname);

        response.setID(expectedID);
        response.setInResponseTo(expectedInResponseTo);
        response.setIssueInstant(expectedIssueInstant);
        response.setRecipient(expectedRecipient);

        assertEquals(expectedOptionalAttributesDOM, response);
    }

    /** {@inheritDoc} */
    public void testChildElementsMarshall() {
        Response response = (Response) buildXMLObject(qname);

        response.getAssertions().add((Assertion) buildXMLObject(new QName(SAMLConstants.SAML1_NS, Assertion.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML1_PREFIX)));
        response.setStatus((Status)buildXMLObject(new QName(SAMLConstants.SAML10P_NS, Status.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML1P_PREFIX)));

        assertEquals(expectedChildElementsDOM, response);

    }
}
