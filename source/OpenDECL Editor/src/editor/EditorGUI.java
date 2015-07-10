package editor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import java.io.File; 
import java.io.FileReader; 
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import xmleditorkit.*;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;




public class EditorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTest;
	private OpenDECLdoc myDoc;
	private final static String DEFAULT_MIN_URI = "default-min.xml";
	
	private static final JFileChooser xmlfc = new JFileChooser();
	
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
	
    private int indent = 0;
    private final String basicIndent = " ";
    private JTextField txtTester1;
    private JTextField txtTester2;
    private JTextField txtTester3;
    private JTextField txtTester4;
    private JTextField txtTester5;
    private JTextField txtTester6;
    
    private JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		xmlfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		xmlfc.setFileFilter(new xmlFileFilter());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorGUI frame = new EditorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditorGUI() {
		setResizable(false);
		
		setTitle("OpenDECL Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnXml = new JMenu("XML");
		menuBar.add(mnXml);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Wellformed Test");
		mnXml.add(mntmNewMenuItem);
		
		JMenuItem mntmValidation = new JMenuItem("Validation");
		mnXml.add(mntmValidation);
		
		JMenu mnXslt = new JMenu("XSLT");
		menuBar.add(mnXslt);
		
		JMenuItem mntmApplyXslt = new JMenuItem("Apply XSLT");
		mnXslt.add(mntmApplyXslt);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		editorPane.setBounds(10, 11, 502, 637);
		editorPane.setEditorKit(new XMLEditorKit());
		//contentPane.add(editorPane);
		
		JScrollPane scrollPaneEditor = new JScrollPane(editorPane);
		scrollPaneEditor.setBounds(10, 11, 617, 647);
		contentPane.add(scrollPaneEditor);
		
		JPanel editPanel = new JPanel();
		editPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		editPanel.setBounds(637, 11, 369, 647);
		contentPane.add(editPanel);
		editPanel.setLayout(null);
		
		JButton btnTest = new JButton("Test");
		btnTest.setBounds(272, 25, 87, 23);
		editPanel.add(btnTest);
		
		txtTest = new JTextField();
		txtTest.setBounds(10, 26, 252, 20);
		editPanel.add(txtTest);
		txtTest.setColumns(10);
		
		txtTester1 = new JTextField();
		txtTester1.setBounds(10, 54, 86, 20);
		editPanel.add(txtTester1);
		txtTester1.setColumns(10);
		
		txtTester2 = new JTextField();
		txtTester2.setBounds(106, 54, 86, 20);
		editPanel.add(txtTester2);
		txtTester2.setColumns(10);
		
		txtTester3 = new JTextField();
		txtTester3.setBounds(202, 54, 86, 20);
		editPanel.add(txtTester3);
		txtTester3.setColumns(10);
		
		txtTester4 = new JTextField();
		txtTester4.setBounds(10, 81, 86, 20);
		editPanel.add(txtTester4);
		txtTester4.setColumns(10);
		
		txtTester5 = new JTextField();
		txtTester5.setBounds(106, 81, 86, 20);
		editPanel.add(txtTester5);
		txtTester5.setColumns(10);
		
		txtTester6 = new JTextField();
		txtTester6.setBounds(202, 81, 86, 20);
		editPanel.add(txtTester6);
		txtTester6.setColumns(10);
		
		JButton btnAddnode = new JButton("AddNode");
		btnAddnode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myDoc!=null && txtTester1.getText()!=""){
					myDoc.addNode(txtTester1.getText());
					if(!txtTester2.getText().isEmpty()) myDoc.setNodePurpose(txtTester1.getText(), txtTester2.getText());
					try {
						myDoc.save("temp.");
					} catch (TransformerFactoryConfigurationError
							| TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateEditorPane();
				}
			}
		});
		btnAddnode.setBounds(10, 113, 91, 23);
		editPanel.add(btnAddnode);
		
		JButton btnSetnodename = new JButton("SetNodeName");
		btnSetnodename.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSetnodename.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myDoc!=null && txtTester1.getText()!="" && txtTester2.getText()!=""){
					myDoc.setNodeName(txtTester1.getText(), txtTester2.getText());
					try {
						myDoc.save("temp.");
					} catch (TransformerFactoryConfigurationError
							| TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateEditorPane();
				}
			}
		});
		btnSetnodename.setBounds(106, 113, 91, 23);
		editPanel.add(btnSetnodename);
		
		JButton btnSetnodepupose = new JButton("SetNodePupose");
		btnSetnodepupose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myDoc!=null && txtTester1.getText()!="" && txtTester2.getText()!=""){
					myDoc.setNodePurpose(txtTester1.getText(), txtTester2.getText());
					try {
						myDoc.save("temp.");
					} catch (TransformerFactoryConfigurationError
							| TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateEditorPane();
				}
			}
		});
		btnSetnodepupose.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSetnodepupose.setBounds(202, 113, 91, 23);
		editPanel.add(btnSetnodepupose);
		
		JButton btnAddgdevice = new JButton("AddGDevice");
		btnAddgdevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myDoc!=null && !txtTester1.getText().isEmpty() && !txtTester2.getText().isEmpty()){
					myDoc.addGraphicsDevice(txtTester1.getText(), txtTester2.getText());
					if(!txtTester3.getText().isEmpty()) myDoc.setGraphicsDeviceGpuCount(txtTester1.getText(), txtTester2.getText(), txtTester3.getText());
					if(!txtTester4.getText().isEmpty()) myDoc.setGraphicsDeviceVram(txtTester1.getText(), txtTester2.getText(), txtTester4.getText());
					if(!txtTester5.getText().isEmpty()) myDoc.setGraphicsDeviceModelName(txtTester1.getText(), txtTester2.getText(), txtTester5.getText());
					try {
						myDoc.save("temp.");
					} catch (TransformerFactoryConfigurationError
							| TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateEditorPane();
				}
			}
		});
		btnAddgdevice.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnAddgdevice.setBounds(10, 147, 91, 23);
		editPanel.add(btnAddgdevice);
		
		JButton btnSetgdevicsid = new JButton("SetGDevicsID");
		btnSetgdevicsid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myDoc!=null && !txtTester1.getText().isEmpty() && !txtTester2.getText().isEmpty() && !txtTester2.getText().isEmpty()){
					myDoc.setGraphicsDeviceId(txtTester1.getText(), txtTester2.getText(), txtTester3.getText());
					try {
						myDoc.save("temp.");
					} catch (TransformerFactoryConfigurationError
							| TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateEditorPane();
				}
			}
		});
		btnSetgdevicsid.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSetgdevicsid.setBounds(106, 147, 91, 23);
		editPanel.add(btnSetgdevicsid);
		
		JButton btnSetgdeviceoptionals = new JButton("setGDeviceOptionals");
		btnSetgdeviceoptionals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myDoc!=null && !txtTester1.getText().isEmpty() && !txtTester2.getText().isEmpty()){
					if(!txtTester3.getText().isEmpty()) myDoc.setGraphicsDeviceGpuCount(txtTester1.getText(), txtTester2.getText(), txtTester3.getText());
					if(!txtTester4.getText().isEmpty()) myDoc.setGraphicsDeviceVram(txtTester1.getText(), txtTester2.getText(), txtTester4.getText());
					if(!txtTester5.getText().isEmpty()) myDoc.setGraphicsDeviceModelName(txtTester1.getText(), txtTester2.getText(), txtTester5.getText());
					try {
						myDoc.save("temp.");
					} catch (TransformerFactoryConfigurationError
							| TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateEditorPane();
				}
			}
		});
		btnSetgdeviceoptionals.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSetgdeviceoptionals.setBounds(202, 147, 91, 23);
		editPanel.add(btnSetgdeviceoptionals);
		
		JTextPane logPane = new JTextPane();		
		logPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		logPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logPane.setEditable(false);
		logPane.setBounds(10, 669, 996, 40);
		
		JScrollPane scrollPaneLog = new JScrollPane(logPane);
		scrollPaneLog.setBounds(10, 664, 996, 50);
		contentPane.add(scrollPaneLog);
		
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(DEFAULT_MIN_URI);
				String dateiinhalt=loadFile(file.getPath());
				editorPane.setText("");
				editorPane.setText(dateiinhalt);
				try {
					myDoc = new OpenDECLdoc(file.getPath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = xmlfc.showOpenDialog(EditorGUI.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = xmlfc.getSelectedFile();
		            //This is where a real application would open the file.
		            logPane.setText(logPane.getText()+"\n- Opening: " + file.getName());
		            String dateiinhalt=loadFile(file.getPath());
					editorPane.setText("");
					editorPane.setText(dateiinhalt);
					try {
						myDoc = new  OpenDECLdoc(file.getPath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					logPane.setText(logPane.getText()+"\n- "+file.getPath()+" erfolgreich geladen");
		        } else {
		        	logPane.setText(logPane.getText()+"\n- Opening aborted");
		        }
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				logPane.setText("- window loaded");
				logPane.setText(logPane.getText()+"\n- READY");
			}
		});
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					testFunction();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		editorPane.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				Caret currentcaret = editorPane.getCaret();
				logPane.setText(logPane.getText()+"\n- Caret-Position: "+currentcaret.getDot());
			}
		});
	}
	private void updateEditorPane(){
		File file = new File("temp.output.xml");
		String dateiinhalt=loadFile(file.getPath());
		try {
			editorPane.getDocument().remove(0, editorPane.getDocument().getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editorPane.setText(dateiinhalt);
	}
	private String loadFile(String path){
		File file = new File(path); 
        FileReader fr = null; 
        int c; 
        StringBuffer buff = new StringBuffer(); 
        try { 
            fr = new FileReader(file); 
            while ((c = fr.read()) != -1) { 
                buff.append((char) c); 
            } 
            fr.close(); 

        } catch (IOException e) { 
            e.printStackTrace(); 
        } 

        return buff.toString(); 
	}
	private void testFunction()throws Exception{
		/*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		OutputStreamWriter errorWriter = new OutputStreamWriter(System.err,"UTF-8");
		db.setErrorHandler(new MyErrorHandler (new PrintWriter(errorWriter, true)));
	    Document doc = db.parse(new File("C:/Ronald-Desktop.xml"));
	    String info = doc.getDocumentElement().getChildNodes().item(3).getNodeName();
	    System.out.println(info);
	    echo(doc.getDocumentElement());*/
		/*OpenDECLdoc myDoc = new OpenDECLdoc();
		myDoc.printToConsole();
		myDoc.addNode("Ronald-PC");
		myDoc.setNodeName("Ronald-PC", "Barbara");
		myDoc.addGraphicsDevice("Barbara", "GraKa1");
		myDoc.setGraphicsDeviceGpuCount("Barbara", "GraKa1", "1");
		myDoc.setGraphicsDeviceModelName("Barbara", "GraKa1", "AMD Slowmaster 5000");
		myDoc.setGraphicsDeviceVram("Barbara", "GraKa1", "128 mb");
		myDoc.addDisplaySetup("simple1");
		myDoc.addDisplay("simple1", "M1");
		myDoc.setDisplayMetricsize("simple1", "M1", 1f, 1f);
		myDoc.setDisplayPixelsize("simple1", "M1", 1000f, 1000f);
		Float physicalX[] = {-1f,-1f,1f,1f};
		Float physicalY[] = {1f,-1f,-1f,1f};
		Float physicalZ[] = {-0.5f,-0.5f,-0.5f,-0.5f};
		myDoc.addDisplayPhysical("simple1", "M1", physicalX, physicalY, physicalZ);
		myDoc.save("C:/");*/
	}
	private void echo(Node n) {
	    outputIndentation();
	    int type = n.getNodeType();

	    switch (type) {
	        case Node.ATTRIBUTE_NODE:
	            System.out.print("ATTR:");
	            printlnCommon(n);
	            break;

	        case Node.CDATA_SECTION_NODE:
	        	System.out.print("CDATA:");
	            printlnCommon(n);
	            break;

	        case Node.COMMENT_NODE:
	        	System.out.print("COMM:");
	            printlnCommon(n);
	            break;

	        case Node.DOCUMENT_FRAGMENT_NODE:
	        	System.out.print("DOC_FRAG:");
	            printlnCommon(n);
	            break;

	        case Node.DOCUMENT_NODE:
	        	System.out.print("DOC:");
	            printlnCommon(n);
	            break;

	        case Node.DOCUMENT_TYPE_NODE:
	        	System.out.print("DOC_TYPE:");
	            printlnCommon(n);
	            NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
	            indent += 2;
	            for (int i = 0; i < nodeMap.getLength(); i++) {
	                Entity entity = (Entity)nodeMap.item(i);
	                echo(entity);
	            }
	            indent -= 2;
	            break;

	        case Node.ELEMENT_NODE:
	        	System.out.print("ELEM:");
	            printlnCommon(n);

	            NamedNodeMap atts = n.getAttributes();
	            indent += 2;
	            for (int i = 0; i < atts.getLength(); i++) {
	                Node att = atts.item(i);
	                echo(att);
	            }
	            indent -= 2;
	            break;

	        case Node.ENTITY_NODE:
	        	System.out.print("ENT:");
	            printlnCommon(n);
	            break;

	        case Node.ENTITY_REFERENCE_NODE:
	        	System.out.print("ENT_REF:");
	            printlnCommon(n);
	            break;

	        case Node.NOTATION_NODE:
	        	System.out.print("NOTATION:");
	            printlnCommon(n);
	            break;

	        case Node.PROCESSING_INSTRUCTION_NODE:
	        	System.out.print("PROC_INST:");
	            printlnCommon(n);
	            break;

	        case Node.TEXT_NODE:
	        	System.out.print("TEXT:");
	            printlnCommon(n);
	            break;

	        default:
	        	System.out.print("UNSUPPORTED NODE: " + type);
	            printlnCommon(n);
	            break;
	    }

	    indent++;
	    for (Node child = n.getFirstChild(); child != null;
	         child = child.getNextSibling()) {
	        echo(child);
	    }
	    indent--;
	}
	private void outputIndentation() {
	    for (int i = 0; i < indent; i++) {
	    	System.out.print(basicIndent);
	    }
	}
	private void printlnCommon(Node n) {
		System.out.print(" nodeName=\"" + n.getNodeName() + "\"");

	    String val = n.getNamespaceURI();
	    if (val != null) {
	    	System.out.print(" uri=\"" + val + "\"");
	    }

	    val = n.getPrefix();

	    if (val != null) {
	    	System.out.print(" pre=\"" + val + "\"");
	    }

	    val = n.getLocalName();
	    if (val != null) {
	    	System.out.print(" local=\"" + val + "\"");
	    }

	    val = n.getNodeValue();
	    if (val != null) {
	    	System.out.print(" nodeValue=");
	        if (val.trim().equals("")) {
	            // Whitespace
	        	System.out.print("[WS]");
	        }
	        else {
	        	System.out.print("\"" + n.getNodeValue() + "\"");
	        }
	    }
	    System.out.println();
	}
}
