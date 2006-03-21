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

package org.opensaml.saml1.core.validator;

import javax.xml.namespace.QName;

import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml1.core.AudienceRestrictionCondition;
import org.opensaml.saml1.core.Condition;
import org.opensaml.saml1.core.Conditions;
import org.opensaml.saml1.core.DoNotCacheCondition;

/**
 * Test case for {@link org.opensaml.saml1.core.validator.AttributeQuerySchemaValidator}.
 */
public class ConditionsSpecTest extends SAML1ObjectValidatorBaseTestCase  {

    /** Constructor */
    public ConditionsSpecTest() {
        super();
        targetQName = new QName(SAMLConstants.SAML1_NS, Conditions.LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
        validator = new ConditionsSpecValidator();
    }

    /*
     * @see org.opensaml.common.SAMLObjectValidatorBaseTestCase#populateRequiredData()
     */
    protected void populateRequiredData() {
        super.populateRequiredData();
        
        Conditions conditions = (Conditions) target;
        QName qname = new QName(SAMLConstants.SAML1_NS, AudienceRestrictionCondition.LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
        conditions.getConditions().add((AudienceRestrictionCondition) buildXMLObject(qname, context));
    }
    
    public void testVersions() {
        Conditions conditions = (Conditions) target;
        QName qname = new QName(SAMLConstants.SAML1_NS, AudienceRestrictionCondition.LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
        conditions.getConditions().add((Condition) buildXMLObject(qname, otherContext));
        assertValidationFail("Condition version mismatch, should thow an exception ");
    }
    
    public void testDoNotCacheCondition() {
        target = buildXMLObject(targetQName, otherContext);
        Conditions conditions = (Conditions) target;

        QName qname = new QName(SAMLConstants.SAML1_NS, DoNotCacheCondition.LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
        conditions.getConditions().add((Condition) buildXMLObject(qname, otherContext));
        assertValidationFail("DoNotCache , should thow an exception ");
    }
    
}