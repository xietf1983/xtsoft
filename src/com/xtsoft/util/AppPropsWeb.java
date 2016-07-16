package com.xtsoft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.xtsoft.exception.SystemException;

/**
 * Manages properties for the entire Application system. Properties are merely
 * pieces of information that need to be saved in between server restarts. The
 * class also reports the version of Application.
 * <p>
 * At the moment, properties are stored in a XML file.
 * <p>
 * Application properties are only meant to be set and retrevied by core
 * Application classes. Therefore, skin writers should probably ignore this
 * class.
 */
public class AppPropsWeb implements PropertiesConfig {

	private static Map properties = new ConcurrentHashMap(16, 0.75f, 1);
	private static String resourceURI = "/app.xml";
	// 用于运行时配置
	private static Document doc = null;
	// 用于保存原始配置
	private static Document docOri = null;
	private static boolean isModified = false;

	private static AppPropsWeb appProps = new AppPropsWeb();

	public static final String ROOT_NODE_NAME = "appProps";

	public static AppPropsWeb getInstance() {
		return appProps;
	}

	private AppPropsWeb() {
		try {
			reloadProps();
		} catch (Exception e) {
			System.err.println("初始化应用配置信息失败！请检查配置文件后重新启动应用");
			e.printStackTrace();
		}
	}

	/**
	 * Gets a Application property.
	 * 
	 * @param name
	 *            the name of the property to get.
	 * @return the property specified by name.
	 */
	static public String getProp(String name) {
		String property = (String) properties.get(name);
		if (property == null) {
			return null;
		} else {
			return property.trim();
		}
	}

	static public Object getProObject(String name) {
		Object property = properties.get(name);
		if (property == null) {
			return null;
		} else {
			return property;
		}
	}

	/**
	 * Sets a Application property. Because the properties must be saved to disk
	 * every time a property is set, property setting is relatively slow.
	 * 
	 * @throws UserCausedException
	 */
	static synchronized public void setPropPersistent(String name, String value) throws SystemException {
		Element prop = null;
		Element propOri = null;
		NodeList list = doc.getElementsByTagName(name);
		if (list.getLength() == 0) {
			list = doc.getElementsByTagName(ROOT_NODE_NAME);
			Element props = (Element) list.item(0);
			prop = doc.createElement(name);
			props.appendChild(prop);
		} else {
			prop = (Element) list.item(0);
		}

		list = docOri.getElementsByTagName(name);
		if (list.getLength() == 0) {
			list = docOri.getElementsByTagName(ROOT_NODE_NAME);
			Element props = (Element) list.item(0);
			propOri = docOri.createElement(name);
			props.appendChild(propOri);
		} else {
			propOri = (Element) list.item(0);
		}

		prop.setAttribute("value", value);
		propOri.setAttribute("value", value);

		properties.put(name, value);

		writeToDisk();
	}

	synchronized public void setPropRunTime(String name, String value) throws SystemException {

		Element prop = null;
		NodeList list = doc.getElementsByTagName(name);
		if (list.getLength() == 0) {
			list = doc.getElementsByTagName(ROOT_NODE_NAME);
			Element props = (Element) list.item(0);
			prop = doc.createElement(name);
			props.appendChild(prop);
		} else {
			prop = (Element) list.item(0);
		}

		prop.setAttribute("value", value);

		properties.put(name, value);
		isModified = true;
	}

	synchronized public void saveProp() throws SystemException {
		if (!isModified) {
			return;
		}
		docOri = (Document) doc.cloneNode(true);
		writeToDisk();
	}

	/**
	 * reload Application property.
	 * 
	 * @throws NanwangException
	 * @throws FileNotFoundException
	 */
	public void reloadProps() throws SystemException {
		Reader in = null;
		try {
			String file = AppPropsWeb.class.getResource(resourceURI).getFile();

			if (file.startsWith("file:/")) {
				file = file.substring(6);
			}
			in = new BufferedReader(new FileReader(new File(file)));
			loadFromStream(in);
			docOri = (Document) doc.cloneNode(true);
		} catch (FileNotFoundException ex) {
			throw new SystemException("装载应用配置信息失败", ex);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Saves Application properties to disk.
	 * 
	 * @throws NanwangException
	 */
	static private void writeToDisk() throws SystemException {
		// String path = "";
		Writer out = null;
		try {
			String file = AppPropsWeb.class.getProtectionDomain().getCodeSource().getLocation().toString();

			if (file.startsWith("file:/")) {
				file = file.substring(6);
			}
			file = file + "/app.xml";
			out = new FileWriter(file);
			outputDoc(docOri, out, "gb2312");
		} catch (Exception ex) {
			throw new SystemException("保存应用配置信息失败", ex);
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		isModified = false;
	}

	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	static public boolean propFileIsReadable() {
		return new File(resourceURI).canRead();
	}

	/**
	 * Returns true if the application.properties file exists where the path
	 * property purports that it does.
	 */
	static public boolean propFileExists() {
		return new File(resourceURI).exists();
	}

	/**
	 * Returns true if the properties are writable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	static public boolean propFileIsWritable() {
		return new File(resourceURI).canWrite();
	}

	public String toXmlStr(boolean isRuntime) throws SystemException {
		Writer buff = new StringWriter(200);
		try {
			if (isRuntime) {
				outputDoc(doc, buff, "gb2312");
			} else {
				outputDoc(docOri, buff, "gb2312");
			}
		} catch (IOException e) {
			throw new SystemException("生成xml字符串失败", e);
		}
		return buff.toString();
	}

	public void fromXmlStr(String xml) throws SystemException {
		Reader is = new BufferedReader(new StringReader(xml));
		loadFromStream(is);
		isModified = true;
	}

	static private void outputDoc(Document document, Writer os, String encoding) throws IOException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = null;
		try {
			t = tf.newTransformer();
			// t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, PROPS_DTD_URI);
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.ENCODING, encoding);
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		}
		DOMSource doms = new DOMSource(document);
		StreamResult sr = new StreamResult(os);
		try {
			t.transform(doms, sr);
		} catch (TransformerException te) {
			IOException ioe = new IOException();
			ioe.initCause(te);
			throw ioe;
		}
	}

	static synchronized private void loadFromStream(Reader in) throws SystemException {
		doc = getLoadingDoc(in);
		NodeList list = doc.getElementsByTagName(ROOT_NODE_NAME);
		if (list.getLength() != 1) {
			throw new SystemException("xml格式不正确");
		}
		Element propertiesElement = (Element) list.item(0);
		importProperties(propertiesElement);
	}

	static private Document getLoadingDoc(Reader in) throws SystemException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(false);
		dbf.setCoalescing(true);
		dbf.setIgnoringComments(true);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			// db.setEntityResolver(new Resolver());
			// db.setErrorHandler(new EH());
			InputSource is = new InputSource(in);
			return db.parse(is);
		} catch (Exception x) {
			throw new SystemException("解析xml失败", x);
		}
	}

	static private void importProperties(Element propertiesElement) {
		NodeList entries = propertiesElement.getChildNodes();
		int numEntries = entries.getLength();
		for (int i = 0; i < numEntries; i++) {
			Node node = entries.item(i);
			if (node instanceof Element) {
				Element entry = (Element) node;
				if (entry.hasAttribute("value")) {
					properties.put(entry.getNodeName(), entry.getAttribute("value"));
				}
				NodeList children = node.getChildNodes();
				int length = children.getLength();
				if (length != 0) {
					List<Map<String, String>> list = new ArrayList<Map<String, String>>();
					for (int k = 0; k < length; k++) {
						Node nChild = children.item(k);
						if (nChild instanceof Element) {
							Element child = (Element) children.item(k);
							Map<String, String> map = new HashMap<String, String>();
							if (child.hasAttribute("value")) {
								map.put("name", child.getAttribute("name"));
								map.put("value", child.getAttribute("value"));
								if (entry.getNodeName().equals("photoRs")) {
									map.put("domain", child.getAttribute("domain"));
								}
							}
							list.add(map);
						}
						properties.put(node.getNodeName(), list);
					}

				}
			}

		}

		/*
		 * NamedNodeMap nodeMap = propertiesElement.getAttributes(); for (int i
		 * = 0; i < nodeMap.getLength(); i++) { Node node = nodeMap.item(i);
		 * properties.put(node.getNodeName(), node.getNodeValue()); } //
		 */
	}

	/**
	 * Returns the version number of Application as a String. i.e. --
	 * major.minor.revision
	 */
	public static String getAppVersion() {
		return getAppVersionMajor() + "." + getAppVersionMinor() + "." + getAppVersionRevision();
	}

	/**
	 * Returns the major version number of Application. i.e. -- 1.x.x
	 */
	public static String getAppVersionMajor() {
		return getProp("app_major_version");
	}

	/**
	 * Returns the minor version number of Application. i.e. -- x.1.x
	 */
	public static String getAppVersionMinor() {
		return getProp("app_minor_version");
	}

	/**
	 * Returns the revision version number of Application. i.e. -- x.x.1
	 */
	public static String getAppVersionRevision() {
		return getProp("app_revision_version");
	}

	public static List<Map<String, String>> getProphotoRsObject() {
		return (List<Map<String, String>>) AppPropsWeb.getProObject(WebKeys.PHOTORS);
	}
	
	public static  String getBeforeDay() {
		String property = (String) properties.get(WebKeys.BEFOREDAY);
		if (property == null) {
			return "3";
		} else {
			return property.trim();
		}
	}
	
	public static  String gettryTimes() {
		String property = (String) properties.get(WebKeys.TRYTIMES);
		if (property == null) {
			return "10";
		} else {
			return property.trim();
		}
	}

	public static String mainDir() {
		String property = (String) properties.get(WebKeys.MAINDIR);
		if (property == null) {
			return "10";
		} else {
			return property.trim();
		}
	}

}
