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

package org.opensaml.xml.signature;

import org.opensaml.xml.XMLObject;

/**
 * XMLObject representing XML Digital Signature, version 20020212, SignatureMethod element.
 */
public interface SignatureMethod extends XMLObject {

    /** Element local name */
    public final static String LOCAL_NAME = "SignatureMethod";

    /** Algorithm attribute name */
    public final static String ALGORITHM_ATTRIB_NAME = "Algorithm";

    /**
     * Gets the algorithm used for canonicalizing content to be signed.
     * 
     * @return the algorithm used for canonicalizing content to be signed
     */
    public String getAlgorithm();

    /**
     * Sets the algorithm used for canonicalizing content to be signed.
     * 
     * @param newAlgorithm the algorithm used for canonicalizing content to be signed
     */
    public void setAlgorithm(String newAlgorithm);
    
    /**
     * Gets the HMAC output length for the signature.
     * 
     * @return the HMAC output length for the signature
     */
    public HMACOutputLength getHMACOutputLength();
    
    /**
     * Sets the HMAC output length for the signature.
     * 
     * @param newLength the HMAC output length for the signature
     */
    public void setHMACOutputLength(HMACOutputLength newLength);
}