package es.uji.crypto.xades.jxades.security.xml.XAdES;

import es.uji.crypto.xades.jxades.util.Base64;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SignatureTimeStampDetails extends XAdESStructure
{
    public SignatureTimeStampDetails(Document document, XAdESStructure parent,
            SignatureTimeStamp signatureTimeStamp, String xadesPrefix, String xadesNamespace,
            String xmlSignaturePrefix, String tsaURL)
    {
        super(document, parent, "SignatureTimeStamp", xadesPrefix, xadesNamespace,
                xmlSignaturePrefix);

        try
        {
            String tsBase64Data = Base64.encodeBytes(signatureTimeStamp
                    .generateEncapsulatedTimeStamp(getDocument(), tsaURL));

            Element tsNode = createElement("EncapsulatedTimeStamp");
            tsNode.appendChild(getDocument().createTextNode(tsBase64Data));

            getNode().appendChild(tsNode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
