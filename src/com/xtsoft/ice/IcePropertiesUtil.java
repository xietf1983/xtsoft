package com.xtsoft.ice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

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
import com.xtsoft.util.AppPropsWeb;
import com.xtsoft.util.PropertiesConfig;


import Ice.Properties;
import Ice.Util;

/**
 * @author yangj
 * 
 */
public final class IcePropertiesUtil implements PropertiesConfig {
	public static final String ROOT_NODE_NAME = "IceProps";

	// ��������ʱ����
	private static Document iDoc = null;
	// ���ڱ���ԭʼ����
	private static Document iDocOri = null;

	private static String iFileName = null;
	private static boolean iIsModified = false;

	static private IcePropertiesUtil inst = new IcePropertiesUtil();

	public IcePropertiesUtil() {
	}

	public static IcePropertiesUtil getInstance() {
		return inst;
	}

	public Properties initProeperties(String fileName) throws SystemException {
		iFileName = fileName;
		Properties p = Util.createProperties();
		reloadProps(p);
		return p;
	}

	private void loadFromStream(Reader in, Properties p) throws SystemException {
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
			iDoc = db.parse(is);
		} catch (Exception x) {
			throw new SystemException("����xmlʧ��", x);
		}

		NodeList list = iDoc.getElementsByTagName(ROOT_NODE_NAME);
		if (list.getLength() != 1) {
			throw new SystemException("xml��ʽ����ȷ");
		}
		Element propertiesElement = (Element) list.item(0);
		NodeList entries = propertiesElement.getChildNodes();
		int numEntries = entries.getLength();
		for (int i = 0; i < numEntries; i++) {
			Node node = entries.item(i);
			if (node instanceof Element) {
				Element entry = (Element) node;
				if (entry.hasAttribute("value")) {
					p.setProperty(entry.getNodeName(), entry.getAttribute("value"));
				}
			}
		}
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
			assert (false);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nanwang.util.PropertiesConfig#fromXmlStr(java.lang.String)
	 */
	public void fromXmlStr(String xml) throws SystemException {
		Reader in = new BufferedReader(new StringReader(xml));
		loadFromStream(in, ICECommunicatorHolder.getInstance().getICECommunicator().getProperties());
		iIsModified = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nanwang.util.PropertiesConfig#saveProp()
	 */
	public void saveProp() throws SystemException {
		if (!iIsModified) {
			return;
		}
		iDocOri = (Document) iDoc.cloneNode(true);

		Writer out = null;
		try {
			// path =
			// AppProps.class.getResource(resourceURI).toString().substring(5);
			// System.out.println("path="+path);
			// path = properties.getProp("path").trim();
			out = new FileWriter(iFileName);
			outputDoc(iDocOri, out, "gb2312");
		} catch (Exception ex) {
			throw new SystemException("����Ice������Ϣʧ��", ex);
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		iIsModified = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nanwang.util.PropertiesConfig#toXmlStr(boolean)
	 */
	public String toXmlStr(boolean isRuntime) throws SystemException {
		Writer buff = new StringWriter(200);
		try {
			if (isRuntime) {
				outputDoc(iDoc, buff, "gb2312");
			} else {
				outputDoc(iDocOri, buff, "gb2312");
			}
		} catch (IOException e) {
			throw new SystemException("����xml�ַ���ʧ��", e);
		}
		return buff.toString();
	}

	private void reloadProps(Properties p) throws SystemException {
		Reader in = null;
		try {
			System.out.print(IcePropertiesUtil.class.getResource("/"));
			String url = AppPropsWeb.class.getResource("/").toString();

			if (url.startsWith("file:/")) {
				url = url.substring(6);
			} 
			in = new BufferedReader(new FileReader(new File(url+"/ice.xml")));
			loadFromStream(in, p);
			iDocOri = (Document) iDoc.cloneNode(true);
		} catch (Exception ex) {
			throw new SystemException("װ��Ice������Ϣʧ��", ex);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	public void reloadProps() throws SystemException {
		reloadProps(ICECommunicatorHolder.getInstance().getICECommunicator().getProperties());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nanwang.util.PropertiesConfig#setPropRunTime(java.lang.String,
	 *      java.lang.String)
	 */
	public void setPropRunTime(String name, String value) throws SystemException {

		Element prop = null;
		NodeList list = iDoc.getElementsByTagName(name);
		if (list.getLength() == 0) {
			list = iDoc.getElementsByTagName(ROOT_NODE_NAME);
			Element props = (Element) list.item(0);
			prop = iDoc.createElement(name);
			props.appendChild(prop);
		} else {
			prop = (Element) list.item(0);
		}

		prop.setAttribute("value", value);
		ICECommunicatorHolder.getInstance().getICECommunicator().getProperties().setProperty(name, value);

		iIsModified = true;
	}

}
