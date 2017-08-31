package es.uji.crypto.xades.jxades.security.xml.XAdES;

public interface DataObjectFormat 
{
	public String getDescription();
	public ObjectIdentifier getObjectIdentifier();
	public String getMimeType();
	public String getEncoding();
	public String getObjectReference();
}
