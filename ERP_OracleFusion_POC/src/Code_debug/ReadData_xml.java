package Code_debug;


	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import org.w3c.dom.Node;
	import org.w3c.dom.Element;
	import java.io.File;

	public class ReadData_xml {

	  public static void main(String argv[]) {

	    try {

		File fXmlFile = new File("C:\\Automation_OCTS\\Data\\naren_Output.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("G_1");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				System.out.println("G_1 : " + eElement.getAttribute("id"));
				System.out.println("IMPORTED STATUS : " + eElement.getElementsByTagName("IMPORTED_STATUS").item(0).getTextContent());
				System.out.println("TRANSFER_STATUS : " + eElement.getElementsByTagName("TRANSFER_STATUS").item(0).getTextContent());
				System.out.println("LOADED STATUS : " + eElement.getElementsByTagName("LOADED_STATUS").item(0).getTextContent());
				//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

	}



