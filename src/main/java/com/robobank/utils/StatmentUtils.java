package com.robobank.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.robobank.domain.CustomerStatement;

@Repository
public class StatmentUtils {

	private List<CustomerStatement> statements = null;

	private static final Logger log = LoggerFactory.getLogger(StatmentUtils.class);

	public List<CustomerStatement> initializeDataFromFile(Path statementsfilePath) {

		log.info("In initializeDataFromFile() StatmentUtils ");
		try {
			BufferedReader br = new BufferedReader(new FileReader(statementsfilePath.toString()));
			String line = null;
			statements = new ArrayList<>();
			int count=0;
			while ((line = br.readLine()) != null) {
				count++;
				if(count==1) {
					continue;
				}
				String statement[] = line.trim().split(",");
				CustomerStatement cust = new CustomerStatement();
				cust.setReference(Integer.parseInt(statement[0].trim()));
				cust.setAccount_Number(statement[1].trim());
				cust.setDescription(statement[2].trim());
				cust.setStart_Balance(Double.parseDouble(statement[3].trim()));
				cust.setMutation(Double.parseDouble(statement[4].trim()));
				cust.setEnd_Balance(Double.parseDouble(statement[5].trim()));
				statements.add(cust);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return statements;
	}

	public List<CustomerStatement> initializeDataFile(Path statementsfilePath) throws Exception {
		log.info("In initializeDataFile() StatmentUtils ");
		File xmlFile = new File(statementsfilePath.toString());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			String xpathExpression = "";
			xpathExpression = "/records/record/@reference";
			XPathExpression expr = xpath.compile(xpathExpression);
			NodeList nodesList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodeList = doc.getElementsByTagName("record");			
			for (int i = 0; i < nodeList.getLength(); i++) {
				statements.add(getCustomersStatements(nodeList.item(i), nodesList.item(i).getNodeValue()));
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

		return statements;
	}

	public CustomerStatement getCustomersStatements(Node node, String reference) throws Exception {
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

	private String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}
