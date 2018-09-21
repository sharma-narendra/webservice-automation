package OCTS_HCM_PersonDisability;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.net.ssl.*;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.soap.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
//import com.sun.org.apache.xml.internal.security.utils.Base64;



public class ERP_getJobStatus {

	String resultsubmitESSJobRequest_ReqImport;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ERP_getJobStatus jobStatus=new ERP_getJobStatus();
		jobStatus.getJobStatusLoadAndImport();

	}

public void getJobStatusLoadAndImport() throws Exception {

	System.out.println("inside getJobStatusLoadAndImport");
	 MessageFactory factory = MessageFactory.newInstance();
     SOAPMessage soapmsg = factory.createMessage();
     SOAPPart soapPart = soapmsg.getSOAPPart();

   //  String authorization = auth;
   /* MimeHeaders hd = soapmsg.getMimeHeaders();
     hd.addHeader("Authorization", "Basic " + authorization);
*/
 String myNamespaceURI = "http://www.w3.org/2003/05/soap-envelope";
 String myNamespace = "soap";
 String myNamespaceURI1 = "http://xmlns.oracle.com/oxp/service/PublicReportService";
 String myNamespace1 = "pub";
//    String destination = "https://ecqg-test.fin.us2.oraclecloud.com:443/publicFinancialCommonErpIntegration/ErpIntegrationService";
 String destination = "https://ecqg-test.fs.us2.oraclecloud.com/xmlpserver/services/PublicReportService";


 SOAPEnvelope envelope = soapPart.getEnvelope();
 envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
 envelope.addNamespaceDeclaration(myNamespace1, myNamespaceURI1);
 System.out.println("\n");
 SOAPHeader header = envelope.getHeader();
 SOAPBody soapBody = envelope.getBody();

 SOAPElement soapBodyElem = soapBody.addChildElement("runReport", myNamespace1);
 SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("reportRequest", myNamespace1);
 SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("parameterNameValues", myNamespace1);
 SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("item", myNamespace1);
 SOAPElement soapBodyElem4 = soapBodyElem3.addChildElement("name", myNamespace1);
 soapBodyElem4.addTextNode("ESSJOBID");
 SOAPElement soapBodyElem5 = soapBodyElem3.addChildElement("values", myNamespace1);
 SOAPElement soapBodyElem6 = soapBodyElem5.addChildElement("item", myNamespace1);
 soapBodyElem6.addTextNode("212017");

 SOAPElement soapBodyElem7 = soapBodyElem1.addChildElement("reportAbsolutePath", myNamespace1);
 soapBodyElem7.addTextNode("/Custom/HCMEMPEXT/OCTSESSJOBSTATUS.xdo");

 SOAPElement soapBodyElem8 = soapBodyElem1.addChildElement("sizeOfDataChunkDownload", myNamespace1);
 soapBodyElem8.addTextNode("-1");

 SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("userID", myNamespace1);
 soapBodyElem9.addTextNode("Michelle.shannon");
 SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("password", myNamespace1);
 soapBodyElem10.addTextNode("Welcome1");

 try {
     // Create SOAP Connection
     SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
     SOAPConnection soapConnection = soapConnectionFactory.createConnection();

     // Send SOAP Message to SOAP Server
     SOAPMessage soapResponse = soapConnection.call(soapmsg,destination);

     // Print the SOAP Response
     System.out.println("Response SOAP Message:");
     soapResponse.writeTo(System.out);
     System.out.println();

     Document document = soapResponse.getSOAPBody().extractContentAsDocument();
     NodeList list = document.getChildNodes();
     System.out.println(list.item(0).getTextContent());

     resultsubmitESSJobRequest_ReqImport=list.item(0).getTextContent();
     soapConnection.close();
 } catch (Exception e) {
 	resultsubmitESSJobRequest_ReqImport="submitESSJobRequest-ReqImport: Error occurred while sending SOAP Request to Server";
     System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
     e.printStackTrace();
 }









}





}
