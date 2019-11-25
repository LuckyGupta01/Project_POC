package com.robobank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.robobank.domain.CustomerStatement;
public class XMLReaderDOM {
    public static void main(String[] args) throws Exception {
    	Path statementsfilePath = Paths.get("src/data/records.xml");
    	
        File xmlFile = new File(statementsfilePath.toString());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();                       
            XPathFactory xpathFactory = XPathFactory.newInstance();           
            XPath xpath = xpathFactory.newXPath();
            String xpathExpression="";
            xpathExpression = "/records/record/@reference";
            XPathExpression expr = xpath.compile(xpathExpression); 
            NodeList nodesList = 	(NodeList) expr.evaluate(doc, XPathConstants.NODESET); 
            NodeList nodeList = doc.getElementsByTagName("record");           
            List<CustomerStatement> CustStatements = new ArrayList<CustomerStatement>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                CustStatements.add(getEmployee(nodeList.item(i),nodesList.item(i).getNodeValue()));                
            }
            
            for(CustomerStatement cust : CustStatements)
            {
            	System.out.println(cust.getReference());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static CustomerStatement getEmployee(Node node, String reference) throws Exception {
        
    	CustomerStatement emp = new CustomerStatement();
    	
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
           
           emp.setReference(Integer.parseInt(reference));
           emp.setAccount_Number((getTagValue("accountNumber", element)));
           emp.setDescription((getTagValue("description", element)));
           emp.setStart_Balance(Double.parseDouble(getTagValue("startBalance", element)));
           emp.setMutation((Double.parseDouble(getTagValue("mutation", element))));
           emp.setEnd_Balance(Double.parseDouble(getTagValue("endBalance", element)));     
        
        }
        return emp;
    }
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }  
    
    
}