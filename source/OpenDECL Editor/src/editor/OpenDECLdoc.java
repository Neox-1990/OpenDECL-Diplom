package editor;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OpenDECLdoc {
	//Fehlerhandler f�r SAX
	static final String outputEncoding = "UTF-8";	
	private static class MyErrorHandler implements ErrorHandler {
	     
	    private PrintWriter out;

	    MyErrorHandler(PrintWriter out) {
	        this.out = out;
	    }

	    private String getParseExceptionInfo(SAXParseException spe) {
	        String systemId = spe.getSystemId();
	        if (systemId == null) {
	            systemId = "null";
	        }

	        String info = "URI=" + systemId + " Line=" + spe.getLineNumber() +
	                      ": " + spe.getMessage();
	        return info;
	    }

	    public void warning(SAXParseException spe) throws SAXException {
	        out.println("Warning: " + getParseExceptionInfo(spe));
	    }
	        
	    public void error(SAXParseException spe) throws SAXException {
	        String message = "Error: " + getParseExceptionInfo(spe);
	        throw new SAXException(message);
	    }

	    public void fatalError(SAXParseException spe) throws SAXException {
	        String message = "Fatal Error: " + getParseExceptionInfo(spe);
	        throw new SAXException(message);
	    }
	}
	//Felder
	//private final static String DEFAULT_MIN_URI = "C:/default-min.xml";
	
	private int indent = 0;
	private final String basicIndent = " ";
	
	private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private DocumentBuilder db;
	private Document doc;
	
	//Methoden
	/*public OpenDECLdoc() throws Exception{
		dbf.setNamespaceAware(false);
		dbf.setValidating(false);
		db = dbf.newDocumentBuilder();
		OutputStreamWriter errorWriter = new OutputStreamWriter(System.err,outputEncoding);
		db.setErrorHandler(new MyErrorHandler (new PrintWriter(errorWriter, true)));
		doc = db.parse(new File(DEFAULT_MIN_URI));
		
	}*/
	public OpenDECLdoc(String path) throws Exception{
		dbf.setNamespaceAware(false);
		dbf.setValidating(false);
		db = dbf.newDocumentBuilder();
		OutputStreamWriter errorWriter = new OutputStreamWriter(System.err,outputEncoding);
		db.setErrorHandler(new MyErrorHandler (new PrintWriter(errorWriter, true)));
		doc = db.parse(new File(path));
	}
	public void printToConsole(){
		//System.out.print(echo(doc.getDocumentElement()));
		String info = doc.getDocumentElement().getChildNodes().item(3).getNodeName();
		System.out.println(info);
	}
	public void save(String path) throws TransformerFactoryConfigurationError, TransformerException{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(path));
		Source input = new DOMSource(doc);
		transformer.transform(input, output);
	}
	private String printlnCommon(Node n) {
	    String result = "";
	    result.concat(" nodeName=\"" + n.getNodeName() + "\"\n");

	    String val = n.getNamespaceURI();
	    if (val != null) {
	    	result.concat(" uri=\"" + val + "\"\n");
	    }

	    val = n.getPrefix();

	    if (val != null) {
	    	result.concat(" pre=\"" + val + "\"\n");
	    }

	    val = n.getLocalName();
	    if (val != null) {
	    	result.concat(" local=\"" + val + "\"\n");
	    }

	    val = n.getNodeValue();
	    if (val != null) {
	    	result.concat(" nodeValue=");
	        if (val.trim().equals("")) {
	            // Whitespace
	        	result.concat("[WS]\n");
	        }
	        else {
	        	result.concat("\"" + n.getNodeValue() + "\"\n");
	        }
	    }
	    return result;
	}
	private void outputIndentation() {
	    for (int i = 0; i < indent; i++) {
	        System.out.print(basicIndent);
	    }
	}
	private String echo(Node n) {
		String result="";
	    outputIndentation();
	    int type = n.getNodeType();

	    switch (type) {
	        case Node.ATTRIBUTE_NODE:
	        	result.concat("ATTR:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.CDATA_SECTION_NODE:
	        	result.concat("CDATA:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.COMMENT_NODE:
	        	result.concat("COMM:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.DOCUMENT_FRAGMENT_NODE:
	        	result.concat("DOC_FRAG:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.DOCUMENT_NODE:
	        	result.concat("DOC:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.DOCUMENT_TYPE_NODE:
	        	result.concat("DOC_TYPE:");
	        	result.concat(printlnCommon(n));
	            NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
	            indent += 2;
	            for (int i = 0; i < nodeMap.getLength(); i++) {
	                Entity entity = (Entity)nodeMap.item(i);
	                result.concat(echo(entity));
	            }
	            indent -= 2;
	            break;

	        case Node.ELEMENT_NODE:
	        	result.concat("ELEM:");
	        	result.concat(printlnCommon(n));

	            NamedNodeMap atts = n.getAttributes();
	            indent += 2;
	            for (int i = 0; i < atts.getLength(); i++) {
	                Node att = atts.item(i);
	                result.concat(echo(att));
	            }
	            indent -= 2;
	            break;

	        case Node.ENTITY_NODE:
	        	result.concat("ENT:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.ENTITY_REFERENCE_NODE:
	        	result.concat("ENT_REF:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.NOTATION_NODE:
	        	result.concat("NOTATION:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.PROCESSING_INSTRUCTION_NODE:
	        	result.concat("PROC_INST:");
	        	result.concat(printlnCommon(n));
	            break;

	        case Node.TEXT_NODE:
	        	result.concat("TEXT:");
	        	result.concat(printlnCommon(n));
	            break;

	        default:
	        	result.concat("UNSUPPORTED NODE: " + type);
	        	result.concat(printlnCommon(n));
	            break;
	    }

	    indent++;
	    for (Node child = n.getFirstChild(); child != null;
	         child = child.getNextSibling()) {
	    	result.concat(echo(child));
	    }
	    indent--;
	    return result;
	}
	//test
	//basic Funktionen
	public Node getAttribute(Node n, String att){
		Node result=null;
		if(n.getNodeType()==Node.ELEMENT_NODE){
			NodeList list = n.getChildNodes();
			for(int i=0;i<list.getLength();i++){
				if(list.item(i).getNodeType()==Node.ATTRIBUTE_NODE && list.item(i).getNodeName().equalsIgnoreCase(att)){
					result=list.item(i);
					break;
				}
			}
		}
		return result;
	}
	private Node getElement(Node parent, String nodeName, String att, String attVal){
		Element result = null;
		NodeList list = parent.getChildNodes();
		for(int i=0;i<list.getLength();i++){
			if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
				if(list.item(i).getNodeName().equals(nodeName)){
					Element temp = (Element) list.item(i);
					if(temp.hasAttribute(att)){
						if(temp.getAttribute(att).equals(attVal)){
							result = temp;
							break;
						}
					}
				}
			}
		}
		return result;
	}
	private Node getElement(Node parent, String nodeName){
		Element result = null;
		NodeList list = parent.getChildNodes();
		for(int i=0;i<list.getLength();i++){
			if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
				if(list.item(i).getNodeName().equals(nodeName)){
					result = (Element) list.item(i);
				}
			}
		}
		return result;
	}
	private ArrayList<Element> getElements(Node parent, String nodeName){
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList list = parent.getChildNodes();
		for(int i=0;i<list.getLength();i++){
			if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
				if(list.item(i).getNodeName().equals(nodeName)){
					result.add((Element) list.item(i));
				}
			}
		}
		return result;
	}
	
	private void removeNode(Node n){
		n.getParentNode().removeChild(n);
	}
	private Node addElement(Node parent,String element){
		Element toAdd = doc.createElement(element);
		return parent.appendChild(toAdd);
	}
	private void replaceElement(Node n, Element element){
		if(n.getNodeType()==Node.ELEMENT_NODE) n.getParentNode().replaceChild(element, n);
	}
	private void setAttribute(Node n, String att, String val){
		if(n.getNodeType()==Node.ELEMENT_NODE){
			Element e = (Element) n;
			e.setAttribute(att, val);
			replaceElement(n, e);
		}
	}
	
	//Einzelfunktionen
	//Node
	public ArrayList<Element> getNodes(){
		return getElements(doc.getDocumentElement(),"node");
	}
	public Element getNode(String id){
		return (Element) getElement(doc.getDocumentElement(),"node","id",id);
	}
	public void addNode(String id){
		Node toAdd = addElement(doc.getDocumentElement(),"node");
		setAttribute(toAdd, "id", id);
	}
	public void removeNode(String id){
		Node toRemove = getElement(doc.getDocumentElement(),"node","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setNodeName(String oldId, String newId){
		Node toChange = getElement(doc.getDocumentElement(),"node","name",oldId);
		setAttribute(toChange,"name",newId);
	}
	public void setNodePurpose(String id, String purpose){
		Node toChange = getElement(doc.getDocumentElement(),"node","id",id);
		setAttribute(toChange,"purpose",purpose);
	}
	
	//graphics device
	public Element getGraphicsDevice(String nodeId,String id){
		return (Element) getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",id);
	}
	public void addGraphicsDevice(String nodeId,String id){
		Node toAdd = addElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device");
		setAttribute(toAdd, "id", id);
	}
	public void removeGraphicsDevice(String nodeId,String id){
		Node toRemove = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setGraphicsDeviceId(String nodeId, String oldId, String newId){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",oldId);
		setAttribute(toChange,"id",newId);
	}
	public void setGraphicsDeviceGpuCount(String nodeId,String id, String gpuCount){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",id);
		setAttribute(toChange,"gpu-count",gpuCount);
	}
	public void setGraphicsDeviceVram(String nodeId,String id, String vram){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",id);
		setAttribute(toChange,"vram",vram);
	}
	public void setGraphicsDeviceModelName(String nodeId,String id, String modelName){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",id);
		setAttribute(toChange,"model-name",modelName);
	}
	
	//port
	public Element getPort(String nodeId,String graphicsDeviceId,String id){
		return (Element) getElement(getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",graphicsDeviceId),"port","id",id);
	}
	public void addPort(String nodeId,String graphicsDeviceId,String id){
		Node toAdd = addElement(getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",graphicsDeviceId),"port");
		setAttribute(toAdd, "id", id);
	}
	public void removePort(String nodeId,String graphicsDeviceId,String id){
		Node toRemove = getElement(getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",graphicsDeviceId),"port","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setPortType(String nodeId,String graphicsDeviceId,String id, String type){
		Node toChange = getElement(getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",graphicsDeviceId),"port","id",id);
		setAttribute(toChange,"type",type);
	}
	public void setPortSlot(String nodeId,String graphicsDeviceId,String id, String slot){
		Node toChange = getElement(getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"graphics-device","id",graphicsDeviceId),"port","id",id);
		setAttribute(toChange,"type",slot);
	}
	
	//network device
	public Element getNetworkDevice(String nodeId,String id){
		return (Element) getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"network-device","id",id);
	}
	public void addNetworkDevice(String nodeId,String id){
		Node toAdd = addElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"network-device");
		setAttribute(toAdd, "id", id);
	}
	public void removeNetworkDevice(String nodeId,String id){
		Node toRemove = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"network-device","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setNetworkDeviceType(String nodeId,String id, String type){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"network-device","id",id);
		setAttribute(toChange,"type",type);
	}
	public void setNetworkDeviceNetwork(String nodeId,String id, String network){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"network-device","id",id);
		setAttribute(toChange,"network",network);
	}
	public void setNetworkDeviceAddress(String nodeId,String id, String address){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"node","name",nodeId),"network-device","id",id);
		setAttribute(toChange,"address",address);
	}
	
	//Network
	public ArrayList<Element> getNetworks(){
		return getElements(doc.getDocumentElement(),"network");
	}
	public Element getNetwork(String id){
		return (Element) getElement(doc.getDocumentElement(),"network","id",id);
	}
	public void addNetwork(String id){
		Node toAdd = addElement(doc.getDocumentElement(),"network");
		setAttribute(toAdd, "id", id);
	}
	public void removeNetwork(String id){
		Node toRemove = getElement(doc.getDocumentElement(),"network","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setNetworkId(String oldId, String newId){
		Node toChange = getElement(doc.getDocumentElement(),"network","id",oldId);
		setAttribute(toChange,"id",newId);
	}
	public void setNetworkBandwidth(String id, String bandwidth){
		Node toChange = getElement(doc.getDocumentElement(),"network","id",id);
		setAttribute(toChange,"bandwidth",bandwidth);
	}
	public void setNetworkSubnetMask(String id, String subnetMask){
		Node toChange = getElement(doc.getDocumentElement(),"network","id",id);
		setAttribute(toChange,"subnet-mask",subnetMask);
	}
	
	//Display Setup
	public ArrayList<Element> getDisplaySetups(){
		return getElements(doc.getDocumentElement(),"display-setup");
	}
	public Element getDisplaySetup(String id){
		return (Element) getElement(doc.getDocumentElement(),"display-setup","id",id);
	}
	public void addDisplaySetup(String id){
		Node toAdd = addElement(doc.getDocumentElement(),"display-setup");
		setAttribute(toAdd, "id", id);
	}
	public void removeDisplaySetup(String id){
		Node toRemove = getElement(doc.getDocumentElement(),"display-setup","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setDisplaySetupId(String oldId, String newId){
		Node toChange = getElement(doc.getDocumentElement(),"display-setup","id",oldId);
		setAttribute(toChange,"id",newId);
	}
	public void setDisplaySetupEyeDistance(String id, Float dist){
		Node toChange = getElement(doc.getDocumentElement(),"display-setup","id",id);
		setAttribute(toChange,"eye-distance",dist.toString());
	}
	
	//user
	public Element getUser(String displaySetupId){
		return (Element) getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user");
	}
	public void addUser(String displaySetupId){
		if(null==getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user")){
			addElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user");
		}
	}
	public void removeUser(String displaySetupId){
		Node toRemove = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user");
		if(null!=toRemove)removeNode(toRemove);
	}
	public void addUserPosition(String displaySetupId, Float x, Float y, Float z){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user");
		if(null!=toChange){
			toChange = addElement(toChange,"position");
			toChange = addElement(toChange,"vector");
			setAttribute(toChange,"x", x.toString());
			setAttribute(toChange,"y", y.toString());
			setAttribute(toChange,"z", z.toString());
		}
	}
	public void addUserOrientation(String displaySetupId, Float x, Float y, Float z){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user");
		if(null!=toChange){
			toChange = addElement(toChange,"orientation");
			toChange = addElement(toChange,"vector");
			setAttribute(toChange,"x", x.toString());
			setAttribute(toChange,"y", y.toString());
			setAttribute(toChange,"z", z.toString());
		}
	}
	public void setUserPosition(String displaySetupId, Float x, Float y, Float z){
		Node toChange = getElement(getElement(getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user"),"position"),"vector");
		if(null!=toChange){
			setAttribute(toChange,"x", x.toString());
			setAttribute(toChange,"y", y.toString());
			setAttribute(toChange,"z", z.toString());
		}
	}
	public void setUserOrientation(String displaySetupId, Float x, Float y, Float z){
		Node toChange = getElement(getElement(getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"user"),"orientation"),"vector");;
		if(null!=toChange){
			setAttribute(toChange,"x", x.toString());
			setAttribute(toChange,"y", y.toString());
			setAttribute(toChange,"z", z.toString());
		}
	}
	
	//display
	public Element getDisplay(String displaySetupId, String id){
		return (Element) getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
	}
	public void addDisplay(String displaySetupId, String id){
		Node toAdd = addElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display");
		setAttribute(toAdd, "id", id);
	}
	public void removeDisplay(String displaySetupId, String id){
		Node toRemove = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		if(null!=toRemove)removeNode(toRemove);
	}
	public void setDisplayId(String displaySetupId, String oldId, String newId){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",oldId);
		setAttribute(toChange,"id",newId);
	}
	public void setDisplayPortref(String displaySetupId, String id, String portref){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		setAttribute(toChange,"portref",portref);
	}
	public void setDisplayType(String displaySetupId, String id, String type){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		setAttribute(toChange,"type",type);
	}
	public void setDisplayStereo(String displaySetupId, String id, String stereo){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		setAttribute(toChange,"stereo",stereo);
	}
	public void setDisplayPixelsize(String displaySetupId, String id, Float x, Float y){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		setAttribute(toChange,"pixel-size-x",x.toString());
		setAttribute(toChange,"pixel-size-y",y.toString());
	}
	public void setDisplayMetricsize(String displaySetupId, String id, Float x, Float y){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		setAttribute(toChange,"metric-size-x",x.toString());
		setAttribute(toChange,"metric-size-y",y.toString());
	}
	public void addDisplayPhysical(String displaySetupId, String id, Float[] x, Float[] y, Float[] z){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		if(null!=toChange){
			toChange = addElement(toChange,"physical");
			Node upperLeft = addElement(toChange,"upper-left");
			Node lowerLeft = addElement(toChange,"lower-left");
			Node lowerRight = addElement(toChange,"lower-right");
			Node upperRight = addElement(toChange,"upper-right");
			upperLeft = addElement(upperLeft,"vector");
			lowerLeft = addElement(lowerLeft,"vector");
			lowerRight = addElement(lowerRight,"vector");
			upperRight = addElement(upperRight,"vector");
			setAttribute(upperLeft,"x", x[0].toString());
			setAttribute(upperLeft,"y", y[0].toString());
			setAttribute(upperLeft,"z", z[0].toString());
			setAttribute(lowerLeft,"x", x[1].toString());
			setAttribute(lowerLeft,"y", y[1].toString());
			setAttribute(lowerLeft,"z", z[1].toString());
			setAttribute(lowerRight,"x", x[2].toString());
			setAttribute(lowerRight,"y", y[2].toString());
			setAttribute(lowerRight,"z", z[2].toString());
			setAttribute(upperRight,"x", x[3].toString());
			setAttribute(upperRight,"y", y[3].toString());
			setAttribute(upperRight,"z", z[3].toString());
		}
	}
	public void addDisplayVirtual(String displaySetupId, String id, Float[] x, Float[] y){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		if(null!=toChange){
			toChange = addElement(toChange,"virtual");
			Node upperLeft = addElement(toChange,"upper-left");
			Node lowerLeft = addElement(toChange,"lower-left");
			Node lowerRight = addElement(toChange,"lower-right");
			Node upperRight = addElement(toChange,"upper-right");
			upperLeft = addElement(upperLeft,"vector");
			lowerLeft = addElement(lowerLeft,"vector");
			lowerRight = addElement(lowerRight,"vector");
			upperRight = addElement(upperRight,"vector");
			setAttribute(upperLeft,"x", x[0].toString());
			setAttribute(upperLeft,"y", y[0].toString());
			setAttribute(lowerLeft,"x", x[1].toString());
			setAttribute(lowerLeft,"y", y[1].toString());
			setAttribute(lowerRight,"x", x[2].toString());
			setAttribute(lowerRight,"y", y[2].toString());
			setAttribute(upperRight,"x", x[3].toString());
			setAttribute(upperRight,"y", y[3].toString());
		}
	}
	public void setDisplayPhysical(String displaySetupId, String id, Float[] x, Float[] y, Float[] z){
		Node toChange = getElement(getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id),"physical");
		if(null!=toChange){
			Node upperLeft = getElement(toChange,"upper-left");
			Node lowerLeft = getElement(toChange,"lower-left");
			Node lowerRight = getElement(toChange,"lower-right");
			Node upperRight = getElement(toChange,"upper-right");
			upperLeft = getElement(upperLeft,"vector");
			lowerLeft = getElement(lowerLeft,"vector");
			lowerRight = getElement(lowerRight,"vector");
			upperRight = getElement(upperRight,"vector");
			if(upperLeft!=null && lowerLeft!=null && lowerRight!=null && upperRight!=null){
				setAttribute(upperLeft,"x", x[0].toString());
				setAttribute(upperLeft,"y", y[0].toString());
				setAttribute(upperLeft,"z", z[0].toString());
				setAttribute(lowerLeft,"x", x[1].toString());
				setAttribute(lowerLeft,"y", y[1].toString());
				setAttribute(lowerLeft,"z", z[1].toString());
				setAttribute(lowerRight,"x", x[2].toString());
				setAttribute(lowerRight,"y", y[2].toString());
				setAttribute(lowerRight,"z", z[2].toString());
				setAttribute(upperRight,"x", x[3].toString());
				setAttribute(upperRight,"y", y[3].toString());
				setAttribute(upperRight,"z", z[3].toString());				
			}
		}
	}
	public void setDisplayVirtual(String displaySetupId, String id, Float[] x, Float[] y){
		Node toChange = getElement(getElement(doc.getDocumentElement(),"display-setup","id",displaySetupId),"display","id",id);
		if(null!=toChange){
			toChange = addElement(toChange,"virtual");
			Node upperLeft = getElement(toChange,"upper-left");
			Node lowerLeft = getElement(toChange,"lower-left");
			Node lowerRight = getElement(toChange,"lower-right");
			Node upperRight = getElement(toChange,"upper-right");
			upperLeft = getElement(upperLeft,"vector");
			lowerLeft = getElement(lowerLeft,"vector");
			lowerRight = getElement(lowerRight,"vector");
			upperRight = getElement(upperRight,"vector");
			setAttribute(upperLeft,"x", x[0].toString());
			setAttribute(upperLeft,"y", y[0].toString());
			setAttribute(lowerLeft,"x", x[1].toString());
			setAttribute(lowerLeft,"y", y[1].toString());
			setAttribute(lowerRight,"x", x[2].toString());
			setAttribute(lowerRight,"y", y[2].toString());
			setAttribute(upperRight,"x", x[3].toString());
			setAttribute(upperRight,"y", y[3].toString());
		}
	}
}