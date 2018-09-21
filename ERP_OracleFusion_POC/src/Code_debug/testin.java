package Code_debug;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
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
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
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

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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

//import com.sun.org.apache.xml.internal.security.utils.Base64;

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

public class testin {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String reportId="228009";
		String userName="Michelle.shannon";
		String password="Welcome1";

		String soapXml =  "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:pub=\"http://xmlns.oracle.com/oxp/service/PublicReportService\">\r\n" +
				"   <soap:Header/>\r\n" +
				"   <soap:Body>\r\n" +
				"      <pub:runReport>\r\n" +
				"         <pub:reportRequest>\r\n" +
				"            <pub:parameterNameValues>\r\n" +
				"              <pub:item>\r\n" +
				"                  <pub:name>ESSJOBID</pub:name>\r\n" +
				"                  <pub:values>\r\n" +
				"                     <pub:item>"+reportId+"</pub:item>\r\n" +
				"                  </pub:values>\r\n" +
				"               </pub:item>\r\n" +
				"            </pub:parameterNameValues>\r\n" +
				"            <pub:reportAbsolutePath>/Custom/HCMEMPEXT/OCTSESSJOBSTATUS.xdo</pub:reportAbsolutePath>\r\n" +
				"            <pub:sizeOfDataChunkDownload>-1</pub:sizeOfDataChunkDownload>\r\n" +
				"         </pub:reportRequest>\r\n" +
				"         <pub:userID>"+userName+"</pub:userID>\r\n" +
				"         <pub:password>"+password+"</pub:password>\r\n" +
				"      </pub:runReport>\r\n" +
				"   </soap:Body>\r\n" +
				"</soap:Envelope>";


				java.net.URL url = new java.net.URL("https://ecqg-test.fs.us2.oraclecloud.com/xmlpserver/services/PublicReportService");
				java.net.URLConnection conn = url.openConnection();
				// Set the necessary header fields
				conn.setRequestProperty("SOAPAction", "https://ecqg-test.fs.us2.oraclecloud.com/xmlpserver/services/PublicReportService");
				conn.setDoOutput(true);
				// Send the request
				java.io.OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream());
				wr.write(soapXml);
				wr.flush();
				// Read the response
				java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document=builder.parse(new InputSource(new StringReader(rd.readLine())));
				NodeList list = document.getChildNodes();
	           // System.out.println(list.item(0).getTextContent());
	            String resultsubmitESSJobRequest_ReqImport=list.item(0).getTextContent();
	            String resultsubmitESSJobRequest_ReqImport1 = resultsubmitESSJobRequest_ReqImport.substring(0, resultsubmitESSJobRequest_ReqImport.length() - 8);
	            System.out.println(resultsubmitESSJobRequest_ReqImport1);

	            byte[] decoded = Base64.getMimeDecoder().decode(resultsubmitESSJobRequest_ReqImport1);
	            String output = new String(decoded);
	            System.out.println(output);
	            FileUtils.writeStringToFile(new File("C:\\Automation_OCTS\\Data\\naren_Output.xml"), output);




	}

}
