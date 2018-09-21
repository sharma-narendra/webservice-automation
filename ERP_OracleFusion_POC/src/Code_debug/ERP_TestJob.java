package Code_debug;





import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ERP_TestJob {
	String resultgetXML;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ERP_TestJob testJob=new ERP_TestJob();
		String testing=testJob.getXMLQuery();

	}


	@SuppressWarnings({ "unused" })
public String getXMLQuery() throws Exception
	{

	        MessageFactory factory = MessageFactory.newInstance();
	        SOAPMessage soapmsg = factory.createMessage();
	        SOAPPart soapPart = soapmsg.getSOAPPart();




	        String myNamespaceURI = "http://www.w3.org/2003/05/soap-envelope";
	        String myNamespace = "soap";
	        String myNamespaceURI1 = "http://xmlns.oracle.com/oxp/service/PublicReportService";
	        String myNamespace1 = "pub";
	      //  String destination = "https://ecqg-test.fs.us2.oraclecloud.com/xmlpserver/services/PublicReportService";
	        String destination	="https://ecqg-test.fs.us2.oraclecloud.com/xmlpserver/services/PublicReportService?WSDL";
	     ///   "http://xmlns.oracle.com/oxp/service/PublicReportService/PublicReportService/runReportRequest";
	      //  https://ecqg-test.fs.us2.oraclecloud.com/xmlpserver/services/PublicReportService?WSDL

	        SOAPEnvelope envelope = soapPart.getEnvelope();


	        //envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
	        envelope.addNamespaceDeclaration(myNamespace1, myNamespaceURI1);
	        String abc=envelope.getValue();
	        System.out.println(abc);
	        
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
	        SOAPElement soapBodyElem9 = soapBody.addChildElement("userID", myNamespace1);
	        soapBodyElem9.addTextNode("Michelle.shannon");
	        SOAPElement soapBodyElem10 = soapBody.addChildElement("password", myNamespace1);
	        soapBodyElem10.addTextNode("Welcome1");




	        try {
	            // Create SOAP Connection
	            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	            // Send SOAP Message to SOAP Server
	            SOAPMessage soapResponse = soapConnection.call(soapmsg,destination);

	            // Print the SOAP Response
	            System.out.println("Response SOAP Message:");
	            //soapResponse.writeTo(System.out);
	            //System.out.println();

	            Document document = soapResponse.getSOAPBody().extractContentAsDocument();
	            NodeList list = document.getChildNodes();
	            System.out.println(list.item(0).getTextContent());
	            resultgetXML =  list.item(0).getTextContent();
	            soapConnection.close();
	        } catch (Exception e) {
	        	resultgetXML=" Error occurred while sending SOAP Request to Server";
	            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
	            e.printStackTrace();
	            return "Error"+e;
	        }
			return resultgetXML;
	    }






}
