package es.uji.crypto.xades.jxades.security.xml.XAdES;

import java.security.GeneralSecurityException;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/*
 <SignedSignatureProperties>
 (SigningTime)?
 (SigningCertificate)?
 (SignatureProductionPlace)?
 (SignerRole)?
 </SignedSignatureProperties>
 */

/**
 * 
 * @author miro
 */
public class SignedSignatureProperties extends XAdESStructure
{
    private Document document;

    public SignedSignatureProperties(Document document, SignedProperties sp, String xadesPrefix,
            String xadesNamespace, String xmlSignaturePrefix)
    {
        super(document, sp, "SignedSignatureProperties", xadesPrefix, xadesNamespace, xmlSignaturePrefix);
        this.document = document;
    }

    public SignedSignatureProperties(Node node, String xadesPrefix, String xadesNamespace,
            String xmlSignaturePrefix)
    {
        super(node, xadesPrefix, xadesNamespace, xmlSignaturePrefix);
    }

    public void setSigningTime()
    {
        setSigningTime(new Date());
    }

    public void setSigningTime(Date signingTime)
    {
        new SigningTime(document, this, signingTime, xadesPrefix, xadesNamespace, xmlSignaturePrefix);
    }

    public void setSigner(Signer signer)
    {
        new SignerDetails(document, this, signer, xadesPrefix, xadesNamespace, xmlSignaturePrefix);
    }

    public void setSigningCertificate(SigningCertificate signingCertificate)
            throws GeneralSecurityException
    {
        if (signingCertificate != null)
        {
            new SigningCertificateDetails(document, this, signingCertificate, xadesPrefix, xadesNamespace,
                    xmlSignaturePrefix);
        }
    }

    public void setSignerRole(SignerRole signerRole)
    {
        if (signerRole != null)
        {
            if (signerRole.getClaimedRole().size() > 0 || signerRole.getCertifiedRole().size() > 0)
            {
                new SignerRoleDetails(document, this, signerRole, xadesPrefix, xadesNamespace,
                        xmlSignaturePrefix);
            }
        }
    }

    public Signer getSigner()
    {
        SignerDetails details = getSignerDetails();
        if (details != null)
        {
            Signer signer = details.getSigner();
            return signer;
        }

        return null;
    }

    protected SignerDetails getSignerDetails()
    {
        Element element = getChildElementNS("SignerDetails");
        if (element != null)
            return new SignerDetails(element, xadesPrefix, xadesNamespace, xmlSignaturePrefix);
        else
            return null;
    }

    public void setSignatureProductionPlace(SignatureProductionPlace signatureProductionPlace)
    {
        if (signatureProductionPlace != null)
        {
            new SignatureProductionPlaceDetails(document, this, signatureProductionPlace, xadesPrefix,
                    xadesNamespace, xmlSignaturePrefix);
        }
    }

    public void setSignaturePolicyIdentifier(SignaturePolicyIdentifier signaturePolicyIdentifier)
    {
        if (signaturePolicyIdentifier != null)
        {
            new SignaturePolicyIdentifierDetails(document, this, signaturePolicyIdentifier, xadesPrefix,
                    xadesNamespace, xmlSignaturePrefix);
        }
    }
}
