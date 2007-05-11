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

package org.opensaml.saml2.encryption;

import org.apache.log4j.Logger;
import org.opensaml.common.SAMLObject;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.EncryptedAssertion;
import org.opensaml.saml2.core.EncryptedAttribute;
import org.opensaml.saml2.core.EncryptedElementType;
import org.opensaml.saml2.core.EncryptedID;
import org.opensaml.saml2.core.NewEncryptedID;
import org.opensaml.saml2.core.NewID;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.encryption.DecryptionException;
import org.opensaml.xml.encryption.EncryptedKeyResolver;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;

/**
 * Class which implements SAML2-specific options for {@link EncryptedElementType} objects.
 * 
 * See additional information about general XML decrytpion issues at {@link org.opensaml.xml.encryption.Decrypter}.
 */
public class Decrypter extends org.opensaml.xml.encryption.Decrypter {
    
    /** Class logger. */
    private Logger log = Logger.getLogger(Decrypter.class);
    
    /** Flag to determine whether the Element which backs the underlying decrypted SAMLObject will be the 
     * root of a new DOM document. */
    private boolean rootInNewDocument;
    
    /**
     * Constructor.
     *
     * @param newResolver resolver for data encryption keys.
     * @param newKEKResolver resolver for key encryption keys.
     * @param newEncKeyResolver resolver for EncryptedKey elements
     */
    public Decrypter(KeyInfoCredentialResolver newResolver, KeyInfoCredentialResolver newKEKResolver, 
            EncryptedKeyResolver newEncKeyResolver) {
        super(newResolver, newKEKResolver, newEncKeyResolver);
        rootInNewDocument = false;
    }
    
    /**
     * Get the flag which indicates whether the DOM Element which backs a decrypted SAML object
     * will be the root of a new DOM document.  Defaults to false.
     * 
     * See also {@link org.opensaml.xml.encryption.Decrypter}.  This flag will be passed as-is to
     * {@link org.opensaml.xml.encryption.Decrypter#decryptData(org.opensaml.xml.encryption.EncryptedData, boolean)}.
     * 
     * @return the current value of the flag for this decrypter instance
     */
    public boolean isRootInNewDocument() {
        return rootInNewDocument;
    }
    
    /**
     * Set the flag which indicates whether the DOM Element which backs a decrypted SAML object
     * will be the root of a new DOM document.  Defaults to false.
     * 
     * See also {@link org.opensaml.xml.encryption.Decrypter}.  This flag will be passed as-is to
     * {@link org.opensaml.xml.encryption.Decrypter#decryptData(org.opensaml.xml.encryption.EncryptedData, boolean)}.
     * 
     * @param flag the current value of the flag for this decrypter instance
     */
    public void setRootInNewDocument(boolean flag) {
       rootInNewDocument = flag; 
    }
    
    /**
     * Decrypt the specified EncryptedAssertion.
     * 
     * @param encryptedAssertion the EncryptedAssertion to decrypt
     * @return an Assertion 
     * @throws DecryptionException thrown when decryption generates an error
     */
    public Assertion decrypt(EncryptedAssertion encryptedAssertion) throws DecryptionException {
        SAMLObject samlObject = decryptData(encryptedAssertion);
        if (! (samlObject instanceof Assertion)) {
            throw new DecryptionException("Decrypted SAMLObject was not an instance of Assertion");
        }
        return (Assertion) samlObject;
    }

    /**
     * Decrypt the specified EncryptedAttribute.
     * 
     * @param encryptedAttribute the EncryptedAttribute to decrypt
     * @return an Attribute
     * @throws DecryptionException thrown when decryption generates an error
     */
    public Attribute decrypt(EncryptedAttribute encryptedAttribute) throws DecryptionException {
        SAMLObject samlObject = decryptData(encryptedAttribute);
        if (! (samlObject instanceof Attribute)) {
            throw new DecryptionException("Decrypted SAMLObject was not an instance of Attribute");
        }
        return (Attribute) samlObject;
    }
    
    /**
     * Decrypt the specified EncryptedID.
     * 
     * Note that an EncryptedID can contain a NameID, an Assertion
     * or a BaseID.  It is up to the caller to determine the type of
     * the resulting SAMLObject.
     * 
     * @param encryptedID the EncryptedID to decrypt
     * @return an XMLObject
     * @throws DecryptionException thrown when decryption generates an error
     */
    public SAMLObject decrypt(EncryptedID encryptedID) throws DecryptionException {
        return decryptData(encryptedID);
    }


    /**
     * Decrypt the specified NewEncryptedID.
     * 
     * @param newEncryptedID the NewEncryptedID to decrypt
     * @return a NewID
     * @throws DecryptionException thrown when decryption generates an error
     */
    public NewID decrypt(NewEncryptedID newEncryptedID) throws DecryptionException {
        SAMLObject samlObject = decryptData(newEncryptedID);
        if (! (samlObject instanceof NewID)) {
            throw new DecryptionException("Decrypted SAMLObject was not an instance of NewID");
        }
        return (NewID) samlObject;
    }
    
    /**
     * Decrypt the specified instance of EncryptedElementType, and return it as an instance 
     * of the specified QName.
     * 
     * 
     * @param encElement the EncryptedElementType to decrypt
     * @return the decrypted SAMLObject
     * @throws DecryptionException thrown when decryption generates an error
     */
    private SAMLObject decryptData(EncryptedElementType encElement) throws DecryptionException {
        
        if (encElement.getEncryptedData() == null) {
            throw new DecryptionException("Element had no EncryptedData child");
        }
        
        XMLObject xmlObject = null;
        try {
            xmlObject = decryptData(encElement.getEncryptedData(), rootInNewDocument);
        } catch (DecryptionException e) {
            log.error("SAML Decrypter encountered an error decrypting element content", e);
            throw e; 
        }
        
        if (! (xmlObject instanceof SAMLObject)) {
            throw new DecryptionException("Decrypted XMLObject was not an instance of SAMLObject");
        }
        
        return (SAMLObject) xmlObject;
    }

}
