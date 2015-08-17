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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextField;

import xmleditorkit.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import org.w3c.dom.Element;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

public class EditorGUI extends JFrame {

	//glabale Variablen
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel editPanel;
	private OpenDECLdoc myDoc; //Hauptdokument
	private JTextPane logPane;
	private final static String DEFAULT_MIN_URI = "default-min.xml";
	
	//File-Chooser für verschiedene Dateitypen
	private static final JFileChooser xmlfc = new JFileChooser();
	private static final JFileChooser xsdfc = new JFileChooser();
	private static final JFileChooser xsltfc = new JFileChooser();
	private static final JFileChooser defaultfc = new JFileChooser();
    
	private JScrollPane scrollPaneEditor;
    private JEditorPane editorPane;
    private JEditorPane editorPane2;
    
    private JRadioButtonMenuItem radioXmlhighlight;
    private JRadioButtonMenuItem radioTexteditor;
    private boolean editorMode;
    /**
     * UI Elemente 
     */
    
    private JComboBox<String> superComboBox;
    
    //NetworkUI
    private JComboBox<String> networkComboBox;
    private JButton btnDeleteNetwork;
    private JLabel txtNetworkIDLbl;
    private JTextField txtNetworkID;
    private JTextField txtNetworkBandwidth;
    private JTextField txtNetworkSubnetmask;
    private JLabel txtNetworkBandwidthLbl;
    private JLabel txtNetworkSubnetmaskLbl;
    private JButton BtnSetNetwork;
    
    //Node UI
    private JTextField txtNodeId;
    private JTextField txtNodePurpose;
    private JTextField txtNetworkDeviceId;
    private JTextField txtNetworkDeviceType;
    private JTextField txtNetworkDeviceNetwork;
    private JTextField txtNetworkDeviceAddress;
    private JTextField txtGrapicsDeviceId;
    private JTextField txtGrapicsDeviceGPUcount;
    private JTextField txtGrapicsDeviceVram;
    private JTextField txtGrapicsDeviceModelName;
    private JTextField txtPortId;
    private JTextField txtPortType;
    private JTextField txtPortSlot;
    private JComboBox<String> nodeComboBox;
    private JButton btnNodeDelete;
    private JLabel lblNodeId;
    private JLabel lblNodePurpose;
    private JButton btnSetNode;
    private JComboBox<String> networkDeviceComboBox;
    private JSeparator nodeSeparator1;
    private JLabel lblNetworkDevice;
    private JLabel lblNetworkDeviceId;
    private JLabel lblNetworkDeviceType;
    private JLabel lblNetworkDeviceNetwork;
    private JLabel lblNetworkDeviceAddress;
    private JButton btnSetNetworkDevice;
    private JButton btnNetworkDeviceDelete;
    private JSeparator nodeSeparator2;
    private JComboBox<String> graphicsDeviceComboBox;
    private JLabel lblGraphicsDevice;
    private JButton btnGraphicsDeviceDelete;
    private JLabel lblGraphicsDeviceId;
    private JLabel lblGraphicsDeviceGpucount;
    private JLabel lblGraphicsDeviceVram;
    private JLabel lblGraphicsDeviceModelname;
    private JLabel lblPort;
    private JComboBox<String> portComboBox;
    private JButton btnPortDelete;
    private JLabel lblPortId;
    private JLabel lblPortType;
    private JLabel lblPortSlot;
    private JButton btnSetGraphicsDevicsAndPort;
    
    //Display-Setup UI
    private JTextField txtDisplaySetupId;
    private JTextField txtDisplaySetupEyeDistance;
    private JTextField txtPositionX;
    private JTextField txtPositionY;
    private JTextField txtPositionZ;
    private JTextField txtOrientationX;
    private JTextField txtOrientationY;
    private JTextField txtOrientationZ;
    private JTextField txtDisplayId;
    private JTextField txtDisplayPortref;
    private JTextField txtDisplayType;
    private JTextField txtDisplayPixelsizeX;
    private JTextField txtDisplayPixelsizeY;
    private JTextField txtDisplayMetricsizeX;
    private JTextField txtDisplayMetricsizeY;
    private JTextField txtDisplayVupperLeftX;
    private JTextField txtDisplayVupperLeftY;
    private JTextField txtDisplayVlowerLeftX;
    private JTextField txtDisplayVlowerLeftY;
    private JTextField txtDisplayVupperRightY;
    private JTextField txtDisplayVupperRightX;
    private JTextField txtDisplayVlowerRightX;
    private JTextField txtDisplayVlowerRightY;
    private JTextField txtDisplayPupperLeftX;
    private JTextField txtDisplayPupperLeftY;
    private JTextField txtDisplayPupperLeftZ;
    private JTextField txtDisplayPupperRightX;
    private JTextField txtDisplayPupperRightY;
    private JTextField txtDisplayPupperRightZ;
    private JTextField txtDisplayPlowerLeftX;
    private JTextField txtDisplayPlowerLeftY;
    private JTextField txtDisplayPlowerLeftZ;
    private JTextField txtDisplayPlowerRightX;
    private JTextField txtDisplayPlowerRightY;
    private JTextField txtDisplayPlowerRightZ;
    private JButton btnDisplaySetupDelete;
    private JComboBox<String> DisplaySetupComboBox;
    private JLabel lblDisplaySetupId;
    private JLabel lblSetupDisplayEyeDistance;
    private JButton btnSetDisplaySetup;
    private JSeparator displaySetupSeperator1;
    private JLabel lblUser;
    private JComboBox<String> userComboBox;
    private JButton btnUserDelete;
    private JLabel lblPosition;
    private JLabel lblOrientation;
    private JButton btnSetUser;
    private JSeparator DisplaySetupSeperator2;
    private JLabel lblDisplay;
    private JButton btnDisplayDelete;
    private JComboBox<String> displayComboBox;
    private JLabel lblDisplayId;
    private JLabel lblDisplayPortref;
    private JLabel lblDisplayType;
    private JLabel lblDisplayStereo;
    private JComboBox<String> displayStereoComboBox;
    private JLabel lblDisplayPixelsize;
    private JLabel lblDisplayMetricsize;
    private JButton btnSetDisplay;
    private JLabel lblDisplayPhysical1;
    private JLabel lblDisplayPhysical2;
    private JLabel lblDisplayVirtual1;
    private JLabel lblDisplayVirtual2;
    
    //Validation Ui
    private JLabel lblLoadScheme;
    private JTextField txtLoadScheme;
    private JButton btnOpenScheme;
    private JButton btnValidate;
    
    //XSLT Ui
    private JLabel lblXsltFile;
    private JTextField txtXSLTfile;
    private JLabel lblOutputFile;
    private JTextField txtOutput;
    private JButton btnOpenXSLT;
    private JButton btnOpenOutput;
    private JButton btnTransform;
//
    //Errorhandler für XML-Validierung, Wohgeformtheit und XSLT Export
    private class SimpleErrorHandler implements ErrorHandler {
        public void warning(SAXParseException e) throws SAXException {
            System.out.println(e.getMessage());
        }

        public void error(SAXParseException e) throws SAXException {
            System.out.println(e.getMessage());
        }

        public void fatalError(SAXParseException e) throws SAXException {
            System.out.println(e.getMessage());
        }
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		//File-Chooser initialisieren
		xmlfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		xmlfc.setFileFilter(new FileNameExtensionFilter("XML file", "xml"));
		xsdfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		xsdfc.setFileFilter(new FileNameExtensionFilter("XSD file", "xsd"));
		xsltfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		xsltfc.setFileFilter(new FileNameExtensionFilter("XSLT file", "xslt"));
		defaultfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		EventQueue.invokeLater(new Runnable() {
			//GUI starten
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
		//GUI Elemente in der Standardansicht
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
		
		JMenuItem mntmLoadLastDocumentstate = new JMenuItem("Load Last Documentstate");
		mntmLoadLastDocumentstate.addActionListener(new ActionListener() {
			//Laden der letzten Zwischenkopie
			public void actionPerformed(ActionEvent e) {
				File file = new File("temp.output.xml");
				String dateiinhalt=loadFile(file.getPath());
				resetEditorPane();
				editorPane.setText("");
				editorPane.setText(dateiinhalt);
				try {
					myDoc = new OpenDECLdoc(file.getPath());
					textLogPane("restored last stable documentstate");
				} catch (Exception e1) {
					
					e1.printStackTrace();
					textLogPane("error: "+e1.getMessage());
				}
			}
		});
		mnFile.add(mntmLoadLastDocumentstate);
		
		JMenu mnEditor = new JMenu("Editor");
		menuBar.add(mnEditor);
		
		radioXmlhighlight = new JRadioButtonMenuItem("XML-Highlight");
		radioXmlhighlight.setSelected(true);
		mnEditor.add(radioXmlhighlight);
		
		radioTexteditor = new JRadioButtonMenuItem("Text-Editor");
		mnEditor.add(radioTexteditor);
		
		editorMode=true;
		
		JMenu mnXml = new JMenu("XML");
		menuBar.add(mnXml);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Wellformed Test");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			//Testen auf Wohlgeformtheit in dem einfach nur getestet wird, ob es als XML Dokument eingelesen werden kann
			public void actionPerformed(ActionEvent e) {
				if(myDoc==null){
					textLogPane("error: no document loaded");
				}else{
					boolean isWellFormed=true;
					try {
						myDoc.save("temp.output.xml");
					} catch (TransformerFactoryConfigurationError e1) {
						
						e1.printStackTrace();
						textLogPane("error: "+e1.getMessage());
					} catch (TransformerException e1) {
						
						e1.printStackTrace();
						textLogPane("error: "+e1.getMessage());
					}
					File file = new File("temp.output.xml");
					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					factory.setValidating(false);
					factory.setNamespaceAware(true);

					DocumentBuilder builder;
					try {
						builder = factory.newDocumentBuilder();
						builder.setErrorHandler(new SimpleErrorHandler());
						// the "parse" method also validates XML, will throw an exception if misformatted
						try {
							builder.parse(file);
						} catch (SAXException e1) {
							
							e1.printStackTrace();
							isWellFormed=false;
						} catch (IOException e1) {
							
							e1.printStackTrace();
							textLogPane("error: "+e1.getMessage());
						}
					} catch (ParserConfigurationException e1) {
						
						e1.printStackTrace();
					}
					if(isWellFormed)textLogPane("the current document is well formed");
					else textLogPane("the current document is not well formed");
				}
			}
		});
		mnXml.add(mntmNewMenuItem);
		
		JMenuItem mntmValidation = new JMenuItem("Validation");
		mntmValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Zeichnen des Interfaces zum Validieren
				if(myDoc==null){
					textLogPane("error: no document loaded");
				}else{
					resetEdit();
					lblLoadScheme = new JLabel("load scheme");
					lblLoadScheme.setBounds(10, 109, 111, 14);
					editPanel.add(lblLoadScheme);
					
					txtLoadScheme = new JTextField();
					txtLoadScheme.setBounds(10, 134, 150, 20);
					editPanel.add(txtLoadScheme);
					txtLoadScheme.setColumns(10);
					
					btnOpenScheme = new JButton("open");
					btnOpenScheme.setBounds(162, 133, 91, 23);
					editPanel.add(btnOpenScheme);
					
					btnValidate = new JButton("validate");
					btnValidate.setEnabled(false);
					btnValidate.setBounds(254, 133, 105, 23);
					editPanel.add(btnValidate);
					
					btnOpenScheme.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//Dialog zum Öffnen des Schemas
							int returnVal = xsdfc.showOpenDialog(EditorGUI.this);

					        if (returnVal == JFileChooser.APPROVE_OPTION) {
					            File file = xsdfc.getSelectedFile();
					            //This is where a real application would open the file.
					            textLogPane("opening: "+file.getPath());
					            txtLoadScheme.setText(file.getPath());
					            btnValidate.setEnabled(true);
					        } else {
					        	textLogPane("aborted opening");
					        }
						}
					});
					btnValidate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//Validierung durchführen
							if(txtLoadScheme.getText().equals("")){
								textLogPane("error: missing scheme file");
							}else{
								try {
									myDoc.save("temp.output.xml");
								} catch (TransformerFactoryConfigurationError e1) {
									
									e1.printStackTrace();
									textLogPane("error: "+e1.getMessage());
								} catch (TransformerException e1) {
									
									e1.printStackTrace();
									textLogPane("error: "+e1.getMessage());
								}
								File file = new File("temp.output.xml");
								File file2 = new File(txtLoadScheme.getText());
								Source xmlFile = new StreamSource(file);
								SchemaFactory schemaFactory = SchemaFactory
								    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
								Schema schema;
								try {
									schema = schemaFactory.newSchema(file2);
									Validator validator = schema.newValidator();
									try {
									  try {
										validator.validate(xmlFile);
									} catch (IOException e1) {
										
										e1.printStackTrace();
										textLogPane("error: "+e1.getMessage());
									}
									  textLogPane("the current document is valid");
									} catch (SAXException e1) {
										textLogPane("the current document is not valid -> "+e1.getLocalizedMessage());
									}
								} catch (SAXException e2) {
									
									e2.printStackTrace();
									textLogPane("error: "+e2.getMessage());
								}
							}							
						}
					});
					editPanel.repaint();
				}
			}
		});
		mnXml.add(mntmValidation);
		
		JMenu mnXslt = new JMenu("XSLT");
		menuBar.add(mnXslt);
		
		JMenuItem mntmApplyXslt = new JMenuItem("Apply XSLT");
		mntmApplyXslt.addActionListener(new ActionListener() {
			//Interface für XSLT Export zeichnen
			public void actionPerformed(ActionEvent e) {
				if(myDoc==null){
					textLogPane("error: no document loaded");
				}else{
					resetEdit();
					lblXsltFile = new JLabel("xslt file");
					lblXsltFile.setBounds(10, 100, 150, 14);
					editPanel.add(lblXsltFile);
					
					txtXSLTfile = new JTextField();
					txtXSLTfile.setBounds(10, 125, 270, 20);
					editPanel.add(txtXSLTfile);
					txtXSLTfile.setColumns(10);
					
					lblOutputFile = new JLabel("output file");
					lblOutputFile.setBounds(10, 156, 150, 14);
					editPanel.add(lblOutputFile);
					
					txtOutput = new JTextField();
					txtOutput.setBounds(10, 181, 270, 20);
					editPanel.add(txtOutput);
					txtOutput.setColumns(10);
					
					btnOpenXSLT = new JButton("open");
					btnOpenXSLT.setBounds(278, 124, 81, 23);
					editPanel.add(btnOpenXSLT);
					
					btnOpenOutput = new JButton("save as");
					btnOpenOutput.setBounds(278, 180, 81, 23);
					editPanel.add(btnOpenOutput);
					
					btnTransform = new JButton("apply xsl transformation");
					btnTransform.setBounds(10, 212, 349, 23);
					editPanel.add(btnTransform);
					
					btnOpenXSLT.addActionListener(new ActionListener() {
						//Dialog zum laden der Xslt-Datei
						public void actionPerformed(ActionEvent e1) {
							int returnVal = xsltfc.showOpenDialog(EditorGUI.this);

					        if (returnVal == JFileChooser.APPROVE_OPTION) {
					            File file = xsltfc.getSelectedFile();
					            //This is where a real application would open the file.
					            textLogPane("opening: "+file.getPath());
					            txtXSLTfile.setText(file.getPath());
					        } else {
					        	textLogPane("aborted opening");
					        }
						}
					});
					btnOpenOutput.addActionListener(new ActionListener() {
						//Dialog zum speichern des outputs
						public void actionPerformed(ActionEvent e1) {
							int returnVal = defaultfc.showSaveDialog(EditorGUI.this);

					        if (returnVal == JFileChooser.APPROVE_OPTION) {
					            File file = defaultfc.getSelectedFile();
					            //This is where a real application would open the file.
					            textLogPane("opening: "+file.getPath());
					            txtOutput.setText(file.getPath());
					        } else {
					        	textLogPane("aborted opening");
					        }
						}
					});
					btnTransform.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							//XSLT Export
							if(txtXSLTfile.getText().equals("")||txtOutput.getText().equals("")){
								textLogPane("error: missing xslt file or output file");
							}else{
								try {
									myDoc.save("temp.output.xml");
								} catch (TransformerFactoryConfigurationError e2) {
									
									e2.printStackTrace();
									textLogPane("error: "+e2.getMessage());
								} catch (TransformerException e2) {
									
									e2.printStackTrace();
									textLogPane("error: "+e2.getMessage());
								}
								File file = new File("temp.output.xml");
								File file2 = new File(txtXSLTfile.getText());
								File file3 = new File(txtOutput.getText());
								
								try {
						            // Create transformer factory
						            TransformerFactory factory = TransformerFactory.newInstance();

						            // Use the factory to create a template containing the xsl file
						            Templates template = factory.newTemplates(new StreamSource(new FileInputStream(file2.getPath())));

						            // Use the template to create a transformer
						            Transformer xformer = template.newTransformer();

						            // Prepare the input and output files
						            Source source = new StreamSource(file);
						            Result result = new StreamResult(file3);

						            // Apply the xsl file to the source file and write the result to the output file
						            xformer.transform(source, result);
						            textLogPane("current document successfully transformed into "+file3.getPath());						            
						        } catch (TransformerConfigurationException e) {
						        	textLogPane("error: error in the xslt file -> "+e.getMessage());
						        } catch (TransformerException e) {
						        	textLogPane("error: error while transforming -> "+e.getMessage());
						        } catch (FileNotFoundException e) {
						        	textLogPane("error: error while opening xslt file -> "+e.getMessage());
								}
							}								
						}
					});
					editPanel.repaint();
				}
			}
		});
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
		
		scrollPaneEditor = new JScrollPane(editorPane);
		scrollPaneEditor.setBounds(10, 11, 617, 647);
		contentPane.add(scrollPaneEditor);
		
		editPanel = new JPanel();
		editPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		editPanel.setBounds(637, 11, 369, 647);
		contentPane.add(editPanel);
		editPanel.setLayout(null);
		
		String[] superComboBoxString = {"node:","network:","displaysetup:"};
		superComboBox = new JComboBox<String>(superComboBoxString);
		superComboBox.addActionListener(new ActionListener() {
			//Aktion, was soll geladen werden der drei Hauptelemente
			public void actionPerformed(ActionEvent e) {
				if(myDoc!=null){
					if((String) superComboBox.getSelectedItem()=="network:") networkEditor();
					if((String) superComboBox.getSelectedItem()=="node:") nodeEditor();
					if((String) superComboBox.getSelectedItem()=="displaysetup:") displaySetupEditor();
				}else{
					textLogPane("error: no document loaded");
				}
				
			}
		});
		superComboBox.setBounds(10, 25, 150, 22);
		editPanel.add(superComboBox);		
		
		logPane = new JTextPane();		
		logPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		logPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		logPane.setEditable(false);
		logPane.setBounds(10, 669, 996, 40);
		
		JScrollPane scrollPaneLog = new JScrollPane(logPane);
		scrollPaneLog.setBounds(10, 664, 996, 50);
		contentPane.add(scrollPaneLog);
		
		mntmNew.addActionListener(new ActionListener() {
			//Öffnen des minimaldokumentes
			public void actionPerformed(ActionEvent e) {
				File file = new File(DEFAULT_MIN_URI);
				String dateiinhalt=loadFile(file.getPath());
				resetEditorPane();
				editorPane.setText("");
				editorPane.setText(dateiinhalt);
				try {
					myDoc = new OpenDECLdoc(file.getPath());
				} catch (Exception e1) {
					
					e1.printStackTrace();
					textLogPane("error: "+e1.getMessage());
				}
			}
		});

		mntmOpen.addActionListener(new ActionListener() {
			//Öffnen eines XML Dokumentes
			public void actionPerformed(ActionEvent e) {
				int returnVal = xmlfc.showOpenDialog(EditorGUI.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = xmlfc.getSelectedFile();
		            //This is where a real application would open the file.
		            textLogPane("opening: "+file.getPath());
		            String dateiinhalt=loadFile(file.getPath());
					resetEditorPane();
					editorPane.setText("");
					editorPane.setText(dateiinhalt);
					try {
						myDoc = new  OpenDECLdoc(file.getPath());
					} catch (Exception e1) {
						
						e1.printStackTrace();
						textLogPane("error: "+e1.getMessage());
					}
					textLogPane(file.getPath()+" succesfully loaded");
		        } else {
		        	textLogPane("aborted opening");
		        }
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			//Speichern des aktuellen Dokumentes
			public void actionPerformed(ActionEvent e) {
				if(editorMode){
					int returnVal = xmlfc.showSaveDialog(EditorGUI.this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = xmlfc.getSelectedFile();
			            //This is where a real application would open the file.
			            textLogPane("saving: "+file.getPath());
			            try {
							myDoc.save(file.getPath());
						} catch (TransformerFactoryConfigurationError e1) {
							
							e1.printStackTrace();
							textLogPane("error: "+e1.getMessage());
						} catch (TransformerException e1) {
							
							e1.printStackTrace();
							textLogPane("error: "+e1.getMessage());
						}
						textLogPane(file.getPath()+" succesfully saved");
			        } else {
			        	textLogPane("aborted saving");
			        }
				}else{
					textLogPane("switch back to XML-Highlight-Mode before saving");
				}
			}
		});
		radioXmlhighlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleEditorMode();
			}
		});
		radioTexteditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleEditorMode();
			}
		});
			
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textLogPane("window loaded");
				textLogPane("READY");
			}
		});
		
	}
	private void toggleEditorMode(){
		//Umschalten der Editoransicht
		if(myDoc!=null){
			if(editorMode){ //auf Texteditor
				resetEdit();
				radioXmlhighlight.setSelected(false);
				radioTexteditor.setSelected(true);
				editorMode=false;
				//activate texteditor
				try {
					myDoc.save("temp.output.xml");
				} catch (TransformerFactoryConfigurationError e1) {
					
					e1.printStackTrace();
					textLogPane("error: "+e1.getMessage());
				} catch (TransformerException e1) {
					
					e1.printStackTrace();
					textLogPane("error: "+e1.getMessage());
				}
				File file = new File("temp.output.xml");
				String dateiinhalt=loadFile(file.getPath());
				superComboBox.setEnabled(false);
				editorPane2 = new JEditorPane();
				editorPane2.setFont(new Font("Tahoma", Font.PLAIN, 10));
				editorPane2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				editorPane2.setBounds(10, 11, 502, 637);
				contentPane.remove(scrollPaneEditor);
				contentPane.repaint();
				scrollPaneEditor = new JScrollPane(editorPane2);
				scrollPaneEditor.setBounds(10, 11, 617, 647);
				contentPane.add(scrollPaneEditor);
				contentPane.repaint();
				editorPane2.setText(dateiinhalt);
				contentPane.repaint();
				textLogPane("switched to text editor mode");
			}else{ //auf XML-Highligh, wobei geprüft werden muss, ob der Text ein gültiges XML Dokument ist
				boolean error=false;
				//activate texteditor
				try {
					PrintWriter out = new PrintWriter("temp2.output.xml");
					out.print(editorPane2.getText());
					out.close();
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
					textLogPane("error: "+e.getMessage());
				}
				File file = new File("temp2.output.xml");
				String dateiinhalt=loadFile(file.getPath());
				try {
					myDoc = new OpenDECLdoc(file.getPath());
				} catch (Exception e) {
					
					textLogPane("error: couldnt parse text into xml file -> "+e.getMessage());
					error=true;
				}
				if(!error){
					resetEdit();
					radioXmlhighlight.setSelected(true);
					radioTexteditor.setSelected(false);
					editorMode=true;
					superComboBox.setEnabled(true);
					editorPane = new JEditorPane();
					editorPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
					editorPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					editorPane.setBounds(10, 11, 502, 637);
					editorPane.setEditorKit(new XMLEditorKit());
					contentPane.remove(scrollPaneEditor);
					contentPane.repaint();
					scrollPaneEditor = new JScrollPane(editorPane);
					scrollPaneEditor.setBounds(10, 11, 617, 647);
					contentPane.add(scrollPaneEditor);
					contentPane.repaint();
					editorPane.setText(dateiinhalt);
					contentPane.repaint();
					textLogPane("switched to XML highlight mode");
				}
			}
		}else{
			textLogPane("error: no document loaded");
			radioXmlhighlight.setSelected(true);
			radioTexteditor.setSelected(false);
		}
	}
	private void resetEditorPane(){//setzt Editor ansich azf Standard zurück
		resetEdit();
		radioXmlhighlight.setSelected(true);
		radioTexteditor.setSelected(false);
		editorMode=true;
		superComboBox.setEnabled(true);
		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		editorPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		editorPane.setBounds(10, 11, 502, 637);
		editorPane.setEditorKit(new XMLEditorKit());
		contentPane.remove(scrollPaneEditor);
		contentPane.repaint();
		scrollPaneEditor = new JScrollPane(editorPane);
		scrollPaneEditor.setBounds(10, 11, 617, 647);
		contentPane.add(scrollPaneEditor);
		contentPane.repaint();
	}
	private void resetEdit(){ //Setzt Edit-Elemente rechts zurück
		if(lblXsltFile!=null){
			editPanel.remove(lblXsltFile);
			editPanel.remove(txtXSLTfile);
			editPanel.remove(lblOutputFile);
			editPanel.remove(txtOutput);
			editPanel.remove(btnOpenXSLT);
			editPanel.remove(btnOpenOutput);
			editPanel.remove(btnTransform);
		}
		
		if(lblLoadScheme!=null){
			editPanel.remove(lblLoadScheme);
			editPanel.remove(txtLoadScheme);
			editPanel.remove(btnOpenScheme);
			editPanel.remove(btnValidate);
		}
		
		if(networkComboBox!=null){
			editPanel.remove(networkComboBox);
			editPanel.remove(btnDeleteNetwork);
			editPanel.remove(txtNetworkIDLbl);
			editPanel.remove(txtNetworkID);
			editPanel.remove(txtNetworkBandwidth);
			editPanel.remove(txtNetworkSubnetmask);
			editPanel.remove(txtNetworkBandwidthLbl);
			editPanel.remove(txtNetworkSubnetmaskLbl);
			editPanel.remove(BtnSetNetwork);
		}
		
		if(nodeComboBox!=null){
			editPanel.remove(txtNodeId);
			editPanel.remove(txtNodePurpose);
			editPanel.remove(txtNetworkDeviceId);
			editPanel.remove(txtNetworkDeviceType);
			editPanel.remove(txtNetworkDeviceNetwork);
			editPanel.remove(txtNetworkDeviceAddress);
			editPanel.remove(txtGrapicsDeviceId);
			editPanel.remove(txtGrapicsDeviceGPUcount);
			editPanel.remove(txtGrapicsDeviceVram);
			editPanel.remove(txtGrapicsDeviceModelName);
			editPanel.remove(txtPortId);
			editPanel.remove(txtPortType);
			editPanel.remove(txtPortSlot);
			editPanel.remove(nodeComboBox);
			editPanel.remove(btnNodeDelete);
			editPanel.remove(lblNodePurpose);
			editPanel.remove(lblNodeId);
			editPanel.remove(btnSetNode);
			editPanel.remove(networkDeviceComboBox);
			editPanel.remove(nodeSeparator1);
			editPanel.remove(lblNetworkDevice);
			editPanel.remove(lblNetworkDeviceId);
			editPanel.remove(lblNetworkDeviceType);
			editPanel.remove(lblNetworkDeviceNetwork);
			editPanel.remove(lblNetworkDeviceAddress);
			editPanel.remove(btnSetNetworkDevice);
			editPanel.remove(btnNetworkDeviceDelete);
			editPanel.remove(nodeSeparator2);
			editPanel.remove(graphicsDeviceComboBox);
			editPanel.remove(lblGraphicsDevice);
			editPanel.remove(btnGraphicsDeviceDelete);
			editPanel.remove(lblGraphicsDeviceId);
			editPanel.remove(lblGraphicsDeviceGpucount);
			editPanel.remove(lblGraphicsDeviceVram);
			editPanel.remove(lblGraphicsDeviceModelname);
			editPanel.remove(lblPort);
			editPanel.remove(portComboBox);
			editPanel.remove(btnPortDelete);
			editPanel.remove(lblPortId);
			editPanel.remove(lblPortType);
			editPanel.remove(lblPortSlot);
			editPanel.remove(btnSetGraphicsDevicsAndPort);
		}
		
		if(DisplaySetupComboBox!=null){
			editPanel.remove(DisplaySetupComboBox);
			editPanel.remove(txtDisplaySetupId);
			editPanel.remove(txtDisplaySetupEyeDistance);
			editPanel.remove(txtPositionX);
			editPanel.remove(txtPositionY);
			editPanel.remove(txtPositionZ);
			editPanel.remove(txtOrientationX);
			editPanel.remove(txtOrientationY);
			editPanel.remove(txtOrientationZ);
			editPanel.remove(txtDisplayId);
			editPanel.remove(txtDisplayPortref);
			editPanel.remove(txtDisplayType);
			editPanel.remove(txtDisplayPixelsizeX);
			editPanel.remove(txtDisplayPixelsizeY);
			editPanel.remove(txtDisplayMetricsizeX);
			editPanel.remove(txtDisplayMetricsizeY);
			editPanel.remove(txtDisplayVupperLeftX);
			editPanel.remove(txtDisplayVupperLeftY);
			editPanel.remove(txtDisplayVlowerLeftX);
			editPanel.remove(txtDisplayVlowerLeftY);
			editPanel.remove(txtDisplayVupperRightY);
			editPanel.remove(txtDisplayVupperRightX);
			editPanel.remove(txtDisplayVlowerRightX);
			editPanel.remove(txtDisplayVlowerRightY);
			editPanel.remove(txtDisplayPupperLeftX);
			editPanel.remove(txtDisplayPupperLeftY);
			editPanel.remove(txtDisplayPupperLeftZ);
			editPanel.remove(txtDisplayPupperRightX);
			editPanel.remove(txtDisplayPupperRightY);
			editPanel.remove(txtDisplayPupperRightZ);
			editPanel.remove(txtDisplayPlowerLeftX);
			editPanel.remove(txtDisplayPlowerLeftY);
			editPanel.remove(txtDisplayPlowerLeftZ);
			editPanel.remove(txtDisplayPlowerRightX);
			editPanel.remove(txtDisplayPlowerRightY);
			editPanel.remove(txtDisplayPlowerRightZ);
			editPanel.remove(btnDisplaySetupDelete);
			editPanel.remove(lblDisplaySetupId);
			editPanel.remove(lblSetupDisplayEyeDistance);
			editPanel.remove(btnSetDisplaySetup);
			editPanel.remove(displaySetupSeperator1);
			editPanel.remove(lblUser);
			editPanel.remove(userComboBox);
			editPanel.remove(btnUserDelete);
			editPanel.remove(lblPosition);
			editPanel.remove(lblOrientation);
			editPanel.remove(btnSetUser);
			editPanel.remove(DisplaySetupSeperator2);
			editPanel.remove(lblDisplay);
			editPanel.remove(btnDisplayDelete);
			editPanel.remove(displayComboBox);
			editPanel.remove(lblDisplayId);
			editPanel.remove(lblDisplayPortref);
			editPanel.remove(lblDisplayType);
			editPanel.remove(lblDisplayStereo);
			editPanel.remove(displayStereoComboBox);
			editPanel.remove(lblDisplayPixelsize);
			editPanel.remove(lblDisplayMetricsize);
			editPanel.remove(btnSetDisplay);
			editPanel.remove(lblDisplayPhysical1);
			editPanel.remove(lblDisplayPhysical2);
			editPanel.remove(lblDisplayVirtual1);
			editPanel.remove(lblDisplayVirtual2);
		}
	}
	private void displaySetupEditor(){ //Alle Funktionen zum Editieren der Display-Setup Elemente und Unterelemente
		//Oben Zeichnen des Interface
		//Unten Actionlistener für interaktiven Elemente
		resetEdit();
		editPanel.repaint();
		
		btnDisplaySetupDelete = new JButton("x");
		btnDisplaySetupDelete.setBounds(316, 25, 43, 23);
		editPanel.add(btnDisplaySetupDelete);
		
		ArrayList<Element> DisplaySetupElements=myDoc.getDisplaySetups();		
		DisplaySetupComboBox = new JComboBox<String>();
		DisplaySetupComboBox.setBounds(170, 25, 144, 22);
		DisplaySetupComboBox.addItem("*add new*");
		for(int i=0;i<DisplaySetupElements.size();i++){
			DisplaySetupComboBox.addItem((String)DisplaySetupElements.get(i).getAttribute("id"));
		}
		editPanel.add(DisplaySetupComboBox);
		
		lblDisplaySetupId = new JLabel("ID*");
		lblDisplaySetupId.setBounds(10, 58, 150, 14);
		editPanel.add(lblDisplaySetupId);
		
		lblSetupDisplayEyeDistance = new JLabel("eye distance");
		lblSetupDisplayEyeDistance.setBounds(170, 58, 144, 14);
		editPanel.add(lblSetupDisplayEyeDistance);
		
		txtDisplaySetupId = new JTextField();
		txtDisplaySetupId.setBounds(10, 75, 150, 20);
		editPanel.add(txtDisplaySetupId);
		txtDisplaySetupId.setColumns(10);
		
		txtDisplaySetupEyeDistance = new JTextField();
		txtDisplaySetupEyeDistance.setText("0.0");
		txtDisplaySetupEyeDistance.setBounds(170, 75, 144, 20);
		editPanel.add(txtDisplaySetupEyeDistance);
		txtDisplaySetupEyeDistance.setColumns(10);
		
		btnSetDisplaySetup = new JButton("Set Display-Setup");
		btnSetDisplaySetup.setBounds(10, 106, 304, 23);
		editPanel.add(btnSetDisplaySetup);
		
		displaySetupSeperator1 = new JSeparator();
		displaySetupSeperator1.setBounds(10, 140, 349, 2);
		editPanel.add(displaySetupSeperator1);
		
		lblUser = new JLabel("user");
		lblUser.setBounds(20, 153, 140, 14);
		editPanel.add(lblUser);
		
		userComboBox = new JComboBox<String>();
		userComboBox.setBounds(170, 149, 144, 22);
		userComboBox.setEnabled(false);
		editPanel.add(userComboBox);
		
		btnUserDelete = new JButton("x");
		btnUserDelete.setBounds(316, 149, 43, 23);
		btnUserDelete.setEnabled(false);
		editPanel.add(btnUserDelete);
		
		lblPosition = new JLabel("position* (x,y,z)");
		lblPosition.setBounds(10, 178, 91, 14);
		editPanel.add(lblPosition);
		
		txtPositionX = new JTextField();
		txtPositionX.setBounds(111, 175, 71, 20);
		editPanel.add(txtPositionX);
		txtPositionX.setColumns(10);
		
		txtPositionY = new JTextField();
		txtPositionY.setBounds(192, 175, 71, 20);
		editPanel.add(txtPositionY);
		txtPositionY.setColumns(10);
		
		txtPositionZ = new JTextField();
		txtPositionZ.setBounds(273, 175, 71, 20);
		editPanel.add(txtPositionZ);
		txtPositionZ.setColumns(10);
		
		lblOrientation = new JLabel("orientation* (x,y,z)");
		lblOrientation.setBounds(10, 203, 91, 14);
		editPanel.add(lblOrientation);
		
		txtOrientationX = new JTextField();
		txtOrientationX.setBounds(111, 200, 71, 20);
		editPanel.add(txtOrientationX);
		txtOrientationX.setColumns(10);
		
		txtOrientationY = new JTextField();
		txtOrientationY.setBounds(192, 200, 71, 20);
		editPanel.add(txtOrientationY);
		txtOrientationY.setColumns(10);
		
		txtOrientationZ = new JTextField();
		txtOrientationZ.setBounds(273, 200, 71, 20);
		editPanel.add(txtOrientationZ);
		txtOrientationZ.setColumns(10);
		
		btnSetUser = new JButton("Set User");
		btnSetUser.setBounds(10, 228, 334, 23);
		btnSetUser.setEnabled(false);
		editPanel.add(btnSetUser);
		
		DisplaySetupSeperator2 = new JSeparator();
		DisplaySetupSeperator2.setBounds(10, 262, 349, 2);
		editPanel.add(DisplaySetupSeperator2);
		
		lblDisplay = new JLabel("display");
		lblDisplay.setBounds(20, 275, 46, 14);
		editPanel.add(lblDisplay);
		
		btnDisplayDelete = new JButton("x");
		btnDisplayDelete.setBounds(316, 271, 43, 23);
		btnDisplayDelete.setEnabled(false);
		editPanel.add(btnDisplayDelete);
		
		displayComboBox = new JComboBox<String>();
		displayComboBox.setBounds(170, 271, 144, 22);
		displayComboBox.setEnabled(false);
		editPanel.add(displayComboBox);
		
		lblDisplayId = new JLabel("ID*");
		lblDisplayId.setBounds(10, 300, 85, 14);
		editPanel.add(lblDisplayId);
		
		lblDisplayPortref = new JLabel("portref*");
		lblDisplayPortref.setBounds(105, 300, 85, 14);
		editPanel.add(lblDisplayPortref);
		
		lblDisplayType = new JLabel("type");
		lblDisplayType.setBounds(200, 300, 85, 14);
		editPanel.add(lblDisplayType);
		
		lblDisplayStereo = new JLabel("stereo*");
		lblDisplayStereo.setBounds(295, 300, 74, 14);
		editPanel.add(lblDisplayStereo);
		
		txtDisplayId = new JTextField();
		txtDisplayId.setBounds(10, 316, 85, 20);
		editPanel.add(txtDisplayId);
		txtDisplayId.setColumns(10);
		
		txtDisplayPortref = new JTextField();
		txtDisplayPortref.setBounds(105, 316, 85, 20);
		editPanel.add(txtDisplayPortref);
		txtDisplayPortref.setColumns(10);
		
		txtDisplayType = new JTextField();
		txtDisplayType.setBounds(200, 316, 85, 20);
		editPanel.add(txtDisplayType);
		txtDisplayType.setColumns(10);
		
		displayStereoComboBox = new JComboBox<String>();
		displayStereoComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"none", "left-eye", "right-eye"}));
		displayStereoComboBox.setBounds(295, 315, 64, 22);
		editPanel.add(displayStereoComboBox);
		
		lblDisplayPixelsize = new JLabel("pixel-size* (x,y)");
		lblDisplayPixelsize.setBounds(10, 347, 150, 14);
		editPanel.add(lblDisplayPixelsize);
		
		lblDisplayMetricsize = new JLabel("metric-size* (x,y)");
		lblDisplayMetricsize.setBounds(10, 372, 150, 14);
		editPanel.add(lblDisplayMetricsize);
		
		txtDisplayPixelsizeX = new JTextField();
		txtDisplayPixelsizeX.setBounds(170, 344, 86, 20);
		editPanel.add(txtDisplayPixelsizeX);
		txtDisplayPixelsizeX.setColumns(10);
		
		txtDisplayPixelsizeY = new JTextField();
		txtDisplayPixelsizeY.setBounds(273, 344, 86, 20);
		editPanel.add(txtDisplayPixelsizeY);
		txtDisplayPixelsizeY.setColumns(10);
		
		txtDisplayMetricsizeX = new JTextField();
		txtDisplayMetricsizeX.setBounds(170, 369, 86, 20);
		editPanel.add(txtDisplayMetricsizeX);
		txtDisplayMetricsizeX.setColumns(10);
		
		txtDisplayMetricsizeY = new JTextField();
		txtDisplayMetricsizeY.setBounds(273, 369, 86, 20);
		editPanel.add(txtDisplayMetricsizeY);
		txtDisplayMetricsizeY.setColumns(10);
		
		btnSetDisplay = new JButton("Set Display");
		btnSetDisplay.setBounds(85, 457, 199, 139);
		btnSetDisplay.setEnabled(false);
		editPanel.add(btnSetDisplay);
		
		txtDisplayVupperLeftX = new JTextField();
		txtDisplayVupperLeftX.setBounds(10, 457, 35, 20);
		editPanel.add(txtDisplayVupperLeftX);
		txtDisplayVupperLeftX.setColumns(10);
		
		txtDisplayVupperLeftY = new JTextField();
		txtDisplayVupperLeftY.setColumns(10);
		txtDisplayVupperLeftY.setBounds(45, 457, 35, 20);
		editPanel.add(txtDisplayVupperLeftY);
		
		txtDisplayVlowerLeftX = new JTextField();
		txtDisplayVlowerLeftX.setColumns(10);
		txtDisplayVlowerLeftX.setBounds(10, 576, 35, 20);
		editPanel.add(txtDisplayVlowerLeftX);
		
		txtDisplayVlowerLeftY = new JTextField();
		txtDisplayVlowerLeftY.setColumns(10);
		txtDisplayVlowerLeftY.setBounds(45, 576, 35, 20);
		editPanel.add(txtDisplayVlowerLeftY);
		
		txtDisplayVupperRightY = new JTextField();
		txtDisplayVupperRightY.setColumns(10);
		txtDisplayVupperRightY.setBounds(324, 457, 35, 20);
		editPanel.add(txtDisplayVupperRightY);
		
		txtDisplayVupperRightX = new JTextField();
		txtDisplayVupperRightX.setColumns(10);
		txtDisplayVupperRightX.setBounds(289, 457, 35, 20);
		editPanel.add(txtDisplayVupperRightX);
		
		txtDisplayVlowerRightX = new JTextField();
		txtDisplayVlowerRightX.setColumns(10);
		txtDisplayVlowerRightX.setBounds(289, 576, 35, 20);
		editPanel.add(txtDisplayVlowerRightX);
		
		txtDisplayVlowerRightY = new JTextField();
		txtDisplayVlowerRightY.setColumns(10);
		txtDisplayVlowerRightY.setBounds(324, 576, 35, 20);
		editPanel.add(txtDisplayVlowerRightY);
		
		txtDisplayPupperLeftX = new JTextField();
		txtDisplayPupperLeftX.setColumns(10);
		txtDisplayPupperLeftX.setBounds(10, 426, 35, 20);
		editPanel.add(txtDisplayPupperLeftX);
		
		txtDisplayPupperLeftY = new JTextField();
		txtDisplayPupperLeftY.setColumns(10);
		txtDisplayPupperLeftY.setBounds(45, 426, 35, 20);
		editPanel.add(txtDisplayPupperLeftY);
		
		txtDisplayPupperLeftZ = new JTextField();
		txtDisplayPupperLeftZ.setColumns(10);
		txtDisplayPupperLeftZ.setBounds(80, 426, 35, 20);
		editPanel.add(txtDisplayPupperLeftZ);
		
		txtDisplayPupperRightX = new JTextField();
		txtDisplayPupperRightX.setColumns(10);
		txtDisplayPupperRightX.setBounds(254, 426, 35, 20);
		editPanel.add(txtDisplayPupperRightX);
		
		txtDisplayPupperRightY = new JTextField();
		txtDisplayPupperRightY.setColumns(10);
		txtDisplayPupperRightY.setBounds(289, 426, 35, 20);
		editPanel.add(txtDisplayPupperRightY);
		
		txtDisplayPupperRightZ = new JTextField();
		txtDisplayPupperRightZ.setColumns(10);
		txtDisplayPupperRightZ.setBounds(324, 426, 35, 20);
		editPanel.add(txtDisplayPupperRightZ);
		
		txtDisplayPlowerLeftX = new JTextField();
		txtDisplayPlowerLeftX.setColumns(10);
		txtDisplayPlowerLeftX.setBounds(10, 607, 35, 20);
		editPanel.add(txtDisplayPlowerLeftX);
		
		txtDisplayPlowerLeftY = new JTextField();
		txtDisplayPlowerLeftY.setColumns(10);
		txtDisplayPlowerLeftY.setBounds(45, 607, 35, 20);
		editPanel.add(txtDisplayPlowerLeftY);
		
		txtDisplayPlowerLeftZ = new JTextField();
		txtDisplayPlowerLeftZ.setColumns(10);
		txtDisplayPlowerLeftZ.setBounds(80, 607, 35, 20);
		editPanel.add(txtDisplayPlowerLeftZ);
		
		txtDisplayPlowerRightX = new JTextField();
		txtDisplayPlowerRightX.setColumns(10);
		txtDisplayPlowerRightX.setBounds(254, 607, 35, 20);
		editPanel.add(txtDisplayPlowerRightX);
		
		txtDisplayPlowerRightY = new JTextField();
		txtDisplayPlowerRightY.setColumns(10);
		txtDisplayPlowerRightY.setBounds(289, 607, 35, 20);
		editPanel.add(txtDisplayPlowerRightY);
		
		txtDisplayPlowerRightZ = new JTextField();
		txtDisplayPlowerRightZ.setColumns(10);
		txtDisplayPlowerRightZ.setBounds(324, 607, 35, 20);
		editPanel.add(txtDisplayPlowerRightZ);
		
		lblDisplayPhysical1 = new JLabel("< physical* >");
		lblDisplayPhysical1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayPhysical1.setBounds(125, 432, 119, 14);
		editPanel.add(lblDisplayPhysical1);
		
		lblDisplayPhysical2 = new JLabel("< physical* >");
		lblDisplayPhysical2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayPhysical2.setBounds(125, 610, 119, 14);
		editPanel.add(lblDisplayPhysical2);
		
		lblDisplayVirtual1 = new JLabel("virtual*");
		lblDisplayVirtual1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayVirtual1.setBounds(10, 488, 75, 77);
		editPanel.add(lblDisplayVirtual1);
		
		lblDisplayVirtual2 = new JLabel("virtual*");
		lblDisplayVirtual2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayVirtual2.setBounds(284, 488, 75, 77);
		editPanel.add(lblDisplayVirtual2);
		
		
		
		DisplaySetupComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userComboBox.removeAllItems();
				displayComboBox.removeAllItems();
				
				txtDisplayPupperLeftX.setText("");
				txtDisplayPupperLeftY.setText("");
				txtDisplayPupperLeftZ.setText("");
				txtDisplayPlowerLeftX.setText("");
				txtDisplayPlowerLeftY.setText("");
				txtDisplayPlowerLeftZ.setText("");
				txtDisplayPlowerRightX.setText("");
				txtDisplayPlowerRightY.setText("");
				txtDisplayPlowerRightZ.setText("");
				txtDisplayPupperRightX.setText("");
				txtDisplayPupperRightY.setText("");
				txtDisplayPupperRightZ.setText("");
				
				txtDisplayVupperLeftX.setText("");
				txtDisplayVupperLeftY.setText("");
				txtDisplayVlowerLeftX.setText("");
				txtDisplayVlowerLeftY.setText("");
				txtDisplayVlowerRightX.setText("");
				txtDisplayVlowerRightY.setText("");
				txtDisplayVupperRightX.setText("");
				txtDisplayVupperRightY.setText("");
				
				txtDisplayId.setText("");
				txtDisplayType.setText("");
				txtDisplayPortref.setText("");
				displayStereoComboBox.setSelectedItem("none");
				
				txtDisplayMetricsizeX.setText("");
				txtDisplayMetricsizeY.setText("");
				txtDisplayPixelsizeX.setText("");
				txtDisplayPixelsizeY.setText("");	
				
				txtPositionX.setText("");
				txtPositionY.setText("");
				txtPositionZ.setText("");
				txtOrientationX.setText("");
				txtOrientationY.setText("");
				txtOrientationZ.setText("");	
				
				if(!(DisplaySetupComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					Element tempDS = myDoc.getDisplaySetup((String)DisplaySetupComboBox.getSelectedItem());
					txtDisplaySetupId.setText(tempDS.getAttribute("id"));
					if(tempDS.getAttribute("eye-distance").equals(""))txtDisplaySetupEyeDistance.setText("0.0");
					else txtDisplaySetupEyeDistance.setText(tempDS.getAttribute("eye-distance"));
					
					userComboBox.setEnabled(true);
					btnUserDelete.setEnabled(true);
					displayComboBox.setEnabled(true);
					btnDisplayDelete.setEnabled(true);
					btnSetUser.setEnabled(true);
					btnSetDisplay.setEnabled(true);
					
					ArrayList<Element> DisplayElements=myDoc.getDisplays((String)DisplaySetupComboBox.getSelectedItem());
					
					userComboBox.addItem("*set new*");
					userComboBox.addItem("user");
					
					displayComboBox.addItem("*add new*");
					for(int i=0;i<DisplayElements.size();i++){
						displayComboBox.addItem((String)DisplayElements.get(i).getAttribute("id"));
					}
					
				}else{
					txtDisplaySetupId.setText("");
					txtDisplaySetupEyeDistance.setText("0.0");
					userComboBox.setEnabled(false);
					btnUserDelete.setEnabled(false);
					displayComboBox.setEnabled(false);
					btnDisplayDelete.setEnabled(false);
					btnSetUser.setEnabled(false);
					btnSetDisplay.setEnabled(false);
				}
				editPanel.repaint();
			}
		});
		
		btnDisplaySetupDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(DisplaySetupComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removeDisplaySetup(DisplaySetupComboBox.getSelectedItem().toString());
					textLogPane("removed display-setup with the id "+DisplaySetupComboBox.getSelectedItem().toString());
					DisplaySetupComboBox.removeItemAt(DisplaySetupComboBox.getSelectedIndex());
					txtDisplaySetupId.setText("");
					txtDisplaySetupEyeDistance.setText("0.0");
				}else{
					textLogPane("error: choose a display-setup to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		btnSetDisplaySetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DisplaySetupComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
					if(!(txtDisplaySetupId.getText().equals("") || txtDisplaySetupId.getText().equals("*add new*"))){
						ArrayList<Element> tempList = myDoc.getDisplaySetups();
						ArrayList<String> idList = new ArrayList<String>();
						for(int i=0;i<tempList.size();i++){
							idList.add((String)tempList.get(i).getAttribute("id"));
						}
						if(idList.contains((String)txtDisplaySetupId.getText())){
							textLogPane("error: there is already a display-setup with the id"+txtDisplaySetupId.getText());
						}else{
							myDoc.addDisplaySetup(txtDisplaySetupId.getText());
							if(txtDisplaySetupEyeDistance.getText()!="" && isNumeric(txtDisplaySetupEyeDistance.getText()))myDoc.setDisplaySetupEyeDistance(txtDisplaySetupId.getText(), Float.parseFloat(txtDisplaySetupEyeDistance.getText()));
							else myDoc.setDisplaySetupEyeDistance(txtDisplaySetupId.getText(), 0.0f);
							DisplaySetupComboBox.addItem(txtDisplaySetupId.getText());
							textLogPane("added display-setup with the id "+txtDisplaySetupId.getText());
							DisplaySetupComboBox.setSelectedItem(txtDisplaySetupId.getText());
						}
					}else{
						textLogPane("error: missing mandatory values");
					}
				}else{
					ArrayList<Element> tempList = myDoc.getDisplaySetups();
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtDisplaySetupId.getText()) && txtDisplaySetupId.getText().compareTo(DisplaySetupComboBox.getSelectedItem().toString())!=0){
						textLogPane("error: you can not rename this display-setup, there is already a node with the id "+txtDisplaySetupId.getText());
					}else{
						if(!(txtDisplaySetupId.getText().equals("") || txtDisplaySetupId.getText().equals("*add new*"))){
							String tempID = txtDisplaySetupId.getText();
							String tempED = txtDisplaySetupEyeDistance.getText();
							if(txtDisplaySetupId.getText().compareTo(DisplaySetupComboBox.getSelectedItem().toString())!=0){
								myDoc.setDisplaySetupId(DisplaySetupComboBox.getSelectedItem().toString(), tempID);
								DisplaySetupComboBox.removeItemAt(DisplaySetupComboBox.getSelectedIndex());
								DisplaySetupComboBox.addItem(tempID);
							}
							if(!tempED.equals("") && isNumeric(tempED))myDoc.setDisplaySetupEyeDistance(tempID, Float.parseFloat(tempED));
							else myDoc.setDisplaySetupEyeDistance(tempID, 0.0f);
							textLogPane("updated dispaly-setup with the id "+tempID);
							DisplaySetupComboBox.setSelectedItem(tempID);
						}else{
							textLogPane("error: missing mandatory values");
						}
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		
		
		userComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userComboBox.getSelectedItem()!=null){
					if(!(userComboBox.getSelectedItem().toString().compareTo("*set new*")==0)){
						Element tempP = myDoc.getUserPosition((String)DisplaySetupComboBox.getSelectedItem());
						Element tempO = myDoc.getUserOrientation((String)DisplaySetupComboBox.getSelectedItem());
						txtPositionX.setText(tempP.getAttribute("x"));
						txtPositionY.setText(tempP.getAttribute("y"));
						txtPositionZ.setText(tempP.getAttribute("z"));
						txtOrientationX.setText(tempO.getAttribute("x"));
						txtOrientationY.setText(tempO.getAttribute("y"));
						txtOrientationZ.setText(tempO.getAttribute("z"));					
					}else{
						txtPositionX.setText("0.0");
						txtPositionY.setText("0.0");
						txtPositionZ.setText("0.0");
						txtOrientationX.setText("0.0");
						txtOrientationY.setText("0.0");
						txtOrientationZ.setText("-1.0");		
					}
					editPanel.repaint();
				}
			}
		});
		
		btnUserDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(userComboBox.getSelectedItem().toString().compareTo("*set new*")==0)){
					myDoc.removeUser(DisplaySetupComboBox.getSelectedItem().toString());
					textLogPane("removed user from the display-setup with the id "+DisplaySetupComboBox.getSelectedItem().toString());
					userComboBox.removeItemAt(userComboBox.getSelectedIndex());
					txtPositionX.setText("0.0");
					txtPositionY.setText("0.0");
					txtPositionZ.setText("0.0");
					txtOrientationX.setText("0.0");
					txtOrientationY.setText("0.0");
					txtOrientationZ.setText("-1.0");		
				}else{
					textLogPane("error: choose the user to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		btnSetUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNumeric(txtPositionX.getText())&&isNumeric(txtPositionY.getText())&&isNumeric(txtPositionZ.getText())&&isNumeric(txtOrientationX.getText())&&isNumeric(txtOrientationY.getText())&&isNumeric(txtOrientationZ.getText())){
					if(userComboBox.getSelectedItem().toString().equals("*set new*")){
						myDoc.removeUser(DisplaySetupComboBox.getSelectedItem().toString());
						myDoc.addUser(DisplaySetupComboBox.getSelectedItem().toString());
						myDoc.addUserPosition(DisplaySetupComboBox.getSelectedItem().toString(), Float.parseFloat(txtPositionX.getText()), Float.parseFloat(txtPositionY.getText()), Float.parseFloat(txtPositionZ.getText()));
						myDoc.addUserOrientation(DisplaySetupComboBox.getSelectedItem().toString(), Float.parseFloat(txtOrientationX.getText()), Float.parseFloat(txtOrientationY.getText()), Float.parseFloat(txtOrientationZ.getText()));
						textLogPane("new user set in the display-setup with the id "+DisplaySetupComboBox.getSelectedItem().toString());
					}else{
						myDoc.setUserPosition(DisplaySetupComboBox.getSelectedItem().toString(), Float.parseFloat(txtPositionX.getText()), Float.parseFloat(txtPositionY.getText()), Float.parseFloat(txtPositionZ.getText()));
						myDoc.setUserOrientation(DisplaySetupComboBox.getSelectedItem().toString(), Float.parseFloat(txtOrientationX.getText()), Float.parseFloat(txtOrientationY.getText()), Float.parseFloat(txtOrientationZ.getText()));
						textLogPane("updated user in the display-setup with the id "+DisplaySetupComboBox.getSelectedItem().toString());
					}
				}else{
					textLogPane("error: no numeric values");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		
		
		displayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(displayComboBox.getSelectedItem()!=null){
					if(!(displayComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
						Element tempPUL = myDoc.getDisplayPupperleft((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempPLL = myDoc.getDisplayPlowerleft((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempPLR = myDoc.getDisplayPlowerright((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempPUR = myDoc.getDisplayPupperright((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempVUL = myDoc.getDisplayVupperleft((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempVLL = myDoc.getDisplayVlowerleft((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempVLR = myDoc.getDisplayVlowerright((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempVUR = myDoc.getDisplayVupperright((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						Element tempDisplay = myDoc.getDisplay((String)DisplaySetupComboBox.getSelectedItem(), (String)displayComboBox.getSelectedItem());
						
						txtDisplayPupperLeftX.setText(tempPUL.getAttribute("x"));
						txtDisplayPupperLeftY.setText(tempPUL.getAttribute("y"));
						txtDisplayPupperLeftZ.setText(tempPUL.getAttribute("z"));
						txtDisplayPlowerLeftX.setText(tempPLL.getAttribute("x"));
						txtDisplayPlowerLeftY.setText(tempPLL.getAttribute("y"));
						txtDisplayPlowerLeftZ.setText(tempPLL.getAttribute("z"));
						txtDisplayPlowerRightX.setText(tempPLR.getAttribute("x"));
						txtDisplayPlowerRightY.setText(tempPLR.getAttribute("y"));
						txtDisplayPlowerRightZ.setText(tempPLR.getAttribute("z"));
						txtDisplayPupperRightX.setText(tempPUR.getAttribute("x"));
						txtDisplayPupperRightY.setText(tempPUR.getAttribute("y"));
						txtDisplayPupperRightZ.setText(tempPUR.getAttribute("z"));
						
						txtDisplayVupperLeftX.setText(tempVUL.getAttribute("x"));
						txtDisplayVupperLeftY.setText(tempVUL.getAttribute("y"));
						txtDisplayVlowerLeftX.setText(tempVLL.getAttribute("x"));
						txtDisplayVlowerLeftY.setText(tempVLL.getAttribute("y"));
						txtDisplayVlowerRightX.setText(tempVLR.getAttribute("x"));
						txtDisplayVlowerRightY.setText(tempVLR.getAttribute("y"));
						txtDisplayVupperRightX.setText(tempVUR.getAttribute("x"));
						txtDisplayVupperRightY.setText(tempVUR.getAttribute("y"));
						
						txtDisplayId.setText(tempDisplay.getAttribute("id"));
						txtDisplayType.setText(tempDisplay.getAttribute("type"));
						txtDisplayPortref.setText(tempDisplay.getAttribute("portref"));
						if(tempDisplay.getAttribute("stereo").equals("left-eye"))displayStereoComboBox.setSelectedItem("left-eye");
						else if(tempDisplay.getAttribute("stereo").equals("right-eye"))displayStereoComboBox.setSelectedItem("right-eye");
						else displayStereoComboBox.setSelectedItem("none");
						
						txtDisplayMetricsizeX.setText(tempDisplay.getAttribute("metric-size-x"));
						txtDisplayMetricsizeY.setText(tempDisplay.getAttribute("metric-size-y"));
						txtDisplayPixelsizeX.setText(tempDisplay.getAttribute("pixel-size-x"));
						txtDisplayPixelsizeY.setText(tempDisplay.getAttribute("pixel-size-y"));
					}else{
						txtDisplayPupperLeftX.setText("");
						txtDisplayPupperLeftY.setText("");
						txtDisplayPupperLeftZ.setText("");
						txtDisplayPlowerLeftX.setText("");
						txtDisplayPlowerLeftY.setText("");
						txtDisplayPlowerLeftZ.setText("");
						txtDisplayPlowerRightX.setText("");
						txtDisplayPlowerRightY.setText("");
						txtDisplayPlowerRightZ.setText("");
						txtDisplayPupperRightX.setText("");
						txtDisplayPupperRightY.setText("");
						txtDisplayPupperRightZ.setText("");
						
						txtDisplayVupperLeftX.setText("");
						txtDisplayVupperLeftY.setText("");
						txtDisplayVlowerLeftX.setText("");
						txtDisplayVlowerLeftY.setText("");
						txtDisplayVlowerRightX.setText("");
						txtDisplayVlowerRightY.setText("");
						txtDisplayVupperRightX.setText("");
						txtDisplayVupperRightY.setText("");
						
						txtDisplayId.setText("");
						txtDisplayType.setText("");
						txtDisplayPortref.setText("");
						displayStereoComboBox.setSelectedItem("none");
						
						txtDisplayMetricsizeX.setText("");
						txtDisplayMetricsizeY.setText("");
						txtDisplayPixelsizeX.setText("");
						txtDisplayPixelsizeY.setText("");
					}
					editPanel.repaint();
				}
			}
		});
		
		btnDisplayDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(displayComboBox.getSelectedItem().toString().compareTo("*set new*")==0)){
					myDoc.removeDisplay(DisplaySetupComboBox.getSelectedItem().toString(),displayComboBox.getSelectedItem().toString());
					textLogPane("removed display with the id "+displayComboBox.getSelectedItem().toString());
					displayComboBox.removeItemAt(displayComboBox.getSelectedIndex());
					
					txtDisplayPupperLeftX.setText("");
					txtDisplayPupperLeftY.setText("");
					txtDisplayPupperLeftZ.setText("");
					txtDisplayPlowerLeftX.setText("");
					txtDisplayPlowerLeftY.setText("");
					txtDisplayPlowerLeftZ.setText("");
					txtDisplayPlowerRightX.setText("");
					txtDisplayPlowerRightY.setText("");
					txtDisplayPlowerRightZ.setText("");
					txtDisplayPupperRightX.setText("");
					txtDisplayPupperRightY.setText("");
					txtDisplayPupperRightZ.setText("");
					
					txtDisplayVupperLeftX.setText("");
					txtDisplayVupperLeftY.setText("");
					txtDisplayVlowerLeftX.setText("");
					txtDisplayVlowerLeftY.setText("");
					txtDisplayVlowerRightX.setText("");
					txtDisplayVlowerRightY.setText("");
					txtDisplayVupperRightX.setText("");
					txtDisplayVupperRightY.setText("");
					
					txtDisplayId.setText("");
					txtDisplayType.setText("");
					txtDisplayPortref.setText("");
					displayStereoComboBox.setSelectedItem("none");
					
					txtDisplayMetricsizeX.setText("");
					txtDisplayMetricsizeY.setText("");
					txtDisplayPixelsizeX.setText("");
					txtDisplayPixelsizeY.setText("");		
				}else{
					textLogPane("error: choose a display to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		btnSetDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(displayComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
					if(!(txtDisplayId.getText().equals("") 
							|| txtDisplaySetupId.getText().equals("*add new*")
							|| txtDisplayPortref.getText().equals("")
							|| txtDisplayMetricsizeX.getText().equals("")
							|| txtDisplayMetricsizeY.getText().equals("")
							|| txtDisplayPixelsizeX.getText().equals("")
							|| txtDisplayPixelsizeY.getText().equals("")
							|| !isNumeric(txtDisplayPupperLeftX.getText())
							|| !isNumeric(txtDisplayPupperLeftY.getText())
							|| !isNumeric(txtDisplayPupperLeftZ.getText())
							|| !isNumeric(txtDisplayPlowerLeftX.getText())
							|| !isNumeric(txtDisplayPlowerLeftY.getText())
							|| !isNumeric(txtDisplayPlowerLeftZ.getText())
							|| !isNumeric(txtDisplayPlowerRightX.getText())
							|| !isNumeric(txtDisplayPlowerRightY.getText())
							|| !isNumeric(txtDisplayPlowerRightZ.getText())
							|| !isNumeric(txtDisplayPupperRightX.getText())
							|| !isNumeric(txtDisplayPupperRightY.getText())
							|| !isNumeric(txtDisplayPupperRightZ.getText())
							|| !isNumeric(txtDisplayVupperLeftX.getText())
							|| !isNumeric(txtDisplayVupperLeftY.getText())
							|| !isNumeric(txtDisplayVlowerLeftX.getText())
							|| !isNumeric(txtDisplayVlowerLeftY.getText())
							|| !isNumeric(txtDisplayVlowerRightX.getText())
							|| !isNumeric(txtDisplayVlowerRightY.getText())
							|| !isNumeric(txtDisplayVupperRightX.getText())
							|| !isNumeric(txtDisplayVupperRightY.getText())
							)){
						ArrayList<Element> tempList = myDoc.getDisplays(DisplaySetupComboBox.getSelectedItem().toString());
						ArrayList<String> idList = new ArrayList<String>();
						for(int i=0;i<tempList.size();i++){
							idList.add((String)tempList.get(i).getAttribute("id"));
						}
						if(idList.contains((String)txtDisplayId.getText())){
							textLogPane("error: there is already a display with the id"+txtDisplayId.getText());
						}else{
							myDoc.addDisplay(DisplaySetupComboBox.getSelectedItem().toString(),txtDisplayId.getText());
							myDoc.setDisplayMetricsize(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), Float.parseFloat(txtDisplayMetricsizeX.getText()), Float.parseFloat(txtDisplayMetricsizeY.getText()));
							myDoc.setDisplayPixelsize(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), Float.parseFloat(txtDisplayPixelsizeX.getText()), Float.parseFloat(txtDisplayPixelsizeY.getText()));
							myDoc.setDisplayPortref(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), txtDisplayPortref.getText());
							myDoc.setDisplayStereo(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), displayStereoComboBox.getSelectedItem().toString());
							if(!txtDisplayType.getText().equals(""))myDoc.setDisplayType(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), txtDisplayType.getText());
							Float physicalX[] = {Float.parseFloat(txtDisplayPupperLeftX.getText()),Float.parseFloat(txtDisplayPlowerLeftX.getText()),Float.parseFloat(txtDisplayPlowerRightX.getText()),Float.parseFloat(txtDisplayPupperRightX.getText())};
							Float physicalY[] = {Float.parseFloat(txtDisplayPupperLeftY.getText()),Float.parseFloat(txtDisplayPlowerLeftY.getText()),Float.parseFloat(txtDisplayPlowerRightY.getText()),Float.parseFloat(txtDisplayPupperRightY.getText())};
							Float physicalZ[] = {Float.parseFloat(txtDisplayPupperLeftZ.getText()),Float.parseFloat(txtDisplayPlowerLeftZ.getText()),Float.parseFloat(txtDisplayPlowerRightZ.getText()),Float.parseFloat(txtDisplayPupperRightZ.getText())};
							Float virtualX[] = {Float.parseFloat(txtDisplayVupperLeftX.getText()),Float.parseFloat(txtDisplayVlowerLeftX.getText()),Float.parseFloat(txtDisplayVlowerRightX.getText()),Float.parseFloat(txtDisplayVupperRightX.getText())};
							Float virtualY[] = {Float.parseFloat(txtDisplayVupperLeftY.getText()),Float.parseFloat(txtDisplayVlowerLeftY.getText()),Float.parseFloat(txtDisplayVlowerRightY.getText()),Float.parseFloat(txtDisplayVupperRightY.getText())};
							myDoc.addDisplayPhysical(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), physicalX, physicalY, physicalZ);
							myDoc.addDisplayVirtual(DisplaySetupComboBox.getSelectedItem().toString(), txtDisplayId.getText(), virtualX, virtualY);
							displayComboBox.addItem(txtDisplayId.getText());
							textLogPane("added display with the id "+txtDisplayId.getText());
							displayComboBox.setSelectedItem(txtDisplayId.getText());
						}
					}else{
						textLogPane("error: missing mandatory values or missing numeric values");
					}
				}else{
					if(!(txtDisplayId.getText().equals("") 
							|| txtDisplaySetupId.getText().equals("*add new*")
							|| txtDisplayPortref.getText().equals("")
							|| txtDisplayMetricsizeX.getText().equals("")
							|| txtDisplayMetricsizeY.getText().equals("")
							|| txtDisplayPixelsizeX.getText().equals("")
							|| txtDisplayPixelsizeY.getText().equals("")
							|| !isNumeric(txtDisplayPupperLeftX.getText())
							|| !isNumeric(txtDisplayPupperLeftY.getText())
							|| !isNumeric(txtDisplayPupperLeftZ.getText())
							|| !isNumeric(txtDisplayPlowerLeftX.getText())
							|| !isNumeric(txtDisplayPlowerLeftY.getText())
							|| !isNumeric(txtDisplayPlowerLeftZ.getText())
							|| !isNumeric(txtDisplayPlowerRightX.getText())
							|| !isNumeric(txtDisplayPlowerRightY.getText())
							|| !isNumeric(txtDisplayPlowerRightZ.getText())
							|| !isNumeric(txtDisplayPupperRightX.getText())
							|| !isNumeric(txtDisplayPupperRightY.getText())
							|| !isNumeric(txtDisplayPupperRightZ.getText())
							|| !isNumeric(txtDisplayVupperLeftX.getText())
							|| !isNumeric(txtDisplayVupperLeftY.getText())
							|| !isNumeric(txtDisplayVlowerLeftX.getText())
							|| !isNumeric(txtDisplayVlowerLeftY.getText())
							|| !isNumeric(txtDisplayVlowerRightX.getText())
							|| !isNumeric(txtDisplayVlowerRightY.getText())
							|| !isNumeric(txtDisplayVupperRightX.getText())
							|| !isNumeric(txtDisplayVupperRightY.getText())
							)){
						ArrayList<Element> tempList = myDoc.getDisplays(DisplaySetupComboBox.getSelectedItem().toString());
						ArrayList<String> idList = new ArrayList<String>();
						for(int i=0;i<tempList.size();i++){
							idList.add((String)tempList.get(i).getAttribute("id"));
						}
						if(idList.contains((String)txtDisplayId.getText()) && txtDisplayId.getText().compareTo(displayComboBox.getSelectedItem().toString())!=0){
							textLogPane("error: you can not rename this display, there is already a display with the id "+txtDisplayId.getText());
						}else{
							String tempID = txtDisplayId.getText();
							String tempPortref = txtDisplayPortref.getText();
							String tempType = txtDisplayType.getText();
							String tempStereo = displayStereoComboBox.getSelectedItem().toString();
							String tempMetricsizeX = txtDisplayMetricsizeX.getText();
							String tempMetricsizeY = txtDisplayMetricsizeY.getText();
							String tempPixelsizeX = txtDisplayPixelsizeX.getText();
							String tempPixelsizeY = txtDisplayPixelsizeY.getText();
							Float tempPhysicalX[] = {Float.parseFloat(txtDisplayPupperLeftX.getText()),Float.parseFloat(txtDisplayPlowerLeftX.getText()),Float.parseFloat(txtDisplayPlowerRightX.getText()),Float.parseFloat(txtDisplayPupperRightX.getText())};
							Float tempPhysicalY[] = {Float.parseFloat(txtDisplayPupperLeftY.getText()),Float.parseFloat(txtDisplayPlowerLeftY.getText()),Float.parseFloat(txtDisplayPlowerRightY.getText()),Float.parseFloat(txtDisplayPupperRightY.getText())};
							Float tempPhysicalZ[] = {Float.parseFloat(txtDisplayPupperLeftZ.getText()),Float.parseFloat(txtDisplayPlowerLeftZ.getText()),Float.parseFloat(txtDisplayPlowerRightZ.getText()),Float.parseFloat(txtDisplayPupperRightZ.getText())};
							Float tempVirtualX[] = {Float.parseFloat(txtDisplayVupperLeftX.getText()),Float.parseFloat(txtDisplayVlowerLeftX.getText()),Float.parseFloat(txtDisplayVlowerRightX.getText()),Float.parseFloat(txtDisplayVupperRightX.getText())};
							Float tempVirtualY[] = {Float.parseFloat(txtDisplayVupperLeftY.getText()),Float.parseFloat(txtDisplayVlowerLeftY.getText()),Float.parseFloat(txtDisplayVlowerRightY.getText()),Float.parseFloat(txtDisplayVupperRightY.getText())};
							if(txtDisplayId.getText().compareTo(displayComboBox.getSelectedItem().toString())!=0){
								myDoc.setDisplayId(DisplaySetupComboBox.getSelectedItem().toString(),displayComboBox.getSelectedItem().toString(), tempID);
								displayComboBox.removeItemAt(displayComboBox.getSelectedIndex());
								displayComboBox.addItem(tempID);
							}
							myDoc.setDisplayMetricsize(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), Float.parseFloat(tempMetricsizeX), Float.parseFloat(tempMetricsizeY));
							myDoc.setDisplayPixelsize(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), Float.parseFloat(tempPixelsizeX), Float.parseFloat(tempPixelsizeY));
							myDoc.setDisplayPortref(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), tempPortref);
							myDoc.setDisplayStereo(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), tempStereo);
							myDoc.setDisplayType(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), tempType);
							myDoc.setDisplayPhysical(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), tempPhysicalX, tempPhysicalY, tempPhysicalZ);
							myDoc.setDisplayVirtual(DisplaySetupComboBox.getSelectedItem().toString(), displayComboBox.getSelectedItem().toString(), tempVirtualX, tempVirtualY);
							textLogPane("updated dispaly with the id "+tempID);
							displayComboBox.setSelectedItem(tempID);
						}
					}else{
						textLogPane("error: missing mandatory values or numeric values");
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		
		editPanel.repaint();
	}
	private void nodeEditor(){//Alle Funktionen zum Editieren der Node Elemente und Unterelemente
		//Oben Zeichnen des Interface
		//Unten Actionlistener für interaktiven Elemente
		resetEdit();
		editPanel.repaint();
		
		ArrayList<Element> NodeElements=myDoc.getNodes();
		nodeComboBox = new JComboBox<String>();
		nodeComboBox.setBounds(170, 25, 150, 22);
		nodeComboBox.addItem("*add new*");
		for(int i=0;i<NodeElements.size();i++){
			nodeComboBox.addItem((String)NodeElements.get(i).getAttribute("id"));
		}
		editPanel.add(nodeComboBox);
		
		btnNodeDelete = new JButton("x");
		btnNodeDelete.setBounds(318, 25, 43, 23);
		editPanel.add(btnNodeDelete);
		
		lblNodeId = new JLabel("ID*");
		lblNodeId.setBounds(10, 58, 46, 14);
		editPanel.add(lblNodeId);
		
		lblNodePurpose = new JLabel("purpose");
		lblNodePurpose.setBounds(170, 58, 56, 14);
		editPanel.add(lblNodePurpose);
		
		txtNodeId = new JTextField();
		txtNodeId.setBounds(10, 73, 150, 20);
		editPanel.add(txtNodeId);
		txtNodeId.setColumns(10);
		
		txtNodePurpose = new JTextField();
		txtNodePurpose.setBounds(170, 73, 150, 20);
		editPanel.add(txtNodePurpose);
		txtNodePurpose.setColumns(10);
		
		btnSetNode = new JButton("Set Node");
		btnSetNode.setBounds(10, 104, 310, 23);
		editPanel.add(btnSetNode);
		
		networkDeviceComboBox = new JComboBox<String>();
		networkDeviceComboBox.setBounds(170, 148, 150, 22);
		editPanel.add(networkDeviceComboBox);
		
		nodeSeparator1 = new JSeparator();
		nodeSeparator1.setForeground(Color.BLACK);
		nodeSeparator1.setBounds(10, 138, 349, 2);
		editPanel.add(nodeSeparator1);
		
		lblNetworkDevice = new JLabel("network-device:");
		lblNetworkDevice.setBounds(20, 152, 140, 14);
		editPanel.add(lblNetworkDevice);
		
		lblNetworkDeviceId = new JLabel("ID*");
		lblNetworkDeviceId.setBounds(10, 177, 46, 14);
		editPanel.add(lblNetworkDeviceId);
		
		lblNetworkDeviceType = new JLabel("type*");
		lblNetworkDeviceType.setBounds(93, 177, 46, 14);
		editPanel.add(lblNetworkDeviceType);
		
		lblNetworkDeviceNetwork = new JLabel("network*");
		lblNetworkDeviceNetwork.setBounds(180, 177, 72, 14);
		editPanel.add(lblNetworkDeviceNetwork);
		
		lblNetworkDeviceAddress = new JLabel("address*");
		lblNetworkDeviceAddress.setBounds(262, 177, 56, 14);
		editPanel.add(lblNetworkDeviceAddress);
		
		txtNetworkDeviceId = new JTextField();
		txtNetworkDeviceId.setBounds(10, 194, 78, 20);
		editPanel.add(txtNetworkDeviceId);
		txtNetworkDeviceId.setColumns(10);
		
		txtNetworkDeviceType = new JTextField();
		txtNetworkDeviceType.setBounds(93, 194, 78, 20);
		editPanel.add(txtNetworkDeviceType);
		txtNetworkDeviceType.setColumns(10);
		
		txtNetworkDeviceNetwork = new JTextField();
		txtNetworkDeviceNetwork.setBounds(180, 194, 78, 20);
		editPanel.add(txtNetworkDeviceNetwork);
		txtNetworkDeviceNetwork.setColumns(10);
		
		txtNetworkDeviceAddress = new JTextField();
		txtNetworkDeviceAddress.setBounds(262, 194, 78, 20);
		editPanel.add(txtNetworkDeviceAddress);
		txtNetworkDeviceAddress.setColumns(10);
		
		btnSetNetworkDevice = new JButton("Set Network-Device");
		btnSetNetworkDevice.setBounds(10, 218, 330, 23);
		btnSetNetworkDevice.setEnabled(false);
		editPanel.add(btnSetNetworkDevice);
		
		btnNetworkDeviceDelete = new JButton("x");
		btnNetworkDeviceDelete.setBounds(318, 148, 43, 23);
		btnNetworkDeviceDelete.setEnabled(false);
		editPanel.add(btnNetworkDeviceDelete);
		
		nodeSeparator2 = new JSeparator();
		nodeSeparator2.setForeground(Color.BLACK);
		nodeSeparator2.setBounds(10, 252, 349, 2);
		editPanel.add(nodeSeparator2);
		
		graphicsDeviceComboBox = new JComboBox<String>();
		graphicsDeviceComboBox.setBounds(170, 260, 150, 22);
		editPanel.add(graphicsDeviceComboBox);
		
		lblGraphicsDevice = new JLabel("graphics-device:");
		lblGraphicsDevice.setBounds(20, 264, 140, 14);
		editPanel.add(lblGraphicsDevice);
		
		btnGraphicsDeviceDelete = new JButton("x");
		btnGraphicsDeviceDelete.setBounds(318, 260, 43, 23);
		btnGraphicsDeviceDelete.setEnabled(false);
		editPanel.add(btnGraphicsDeviceDelete);
		
		lblGraphicsDeviceId = new JLabel("ID*");
		lblGraphicsDeviceId.setBounds(10, 289, 46, 14);
		editPanel.add(lblGraphicsDeviceId);
		
		lblGraphicsDeviceGpucount = new JLabel("gpu-count");
		lblGraphicsDeviceGpucount.setBounds(93, 289, 72, 14);
		editPanel.add(lblGraphicsDeviceGpucount);
		
		lblGraphicsDeviceVram = new JLabel("vram");
		lblGraphicsDeviceVram.setBounds(180, 289, 46, 14);
		editPanel.add(lblGraphicsDeviceVram);
		
		lblGraphicsDeviceModelname = new JLabel("model-name");
		lblGraphicsDeviceModelname.setBounds(262, 289, 78, 14);
		editPanel.add(lblGraphicsDeviceModelname);
		
		txtGrapicsDeviceId = new JTextField();
		txtGrapicsDeviceId.setBounds(10, 304, 78, 20);
		editPanel.add(txtGrapicsDeviceId);
		txtGrapicsDeviceId.setColumns(10);
		
		txtGrapicsDeviceGPUcount = new JTextField();
		txtGrapicsDeviceGPUcount.setBounds(93, 304, 78, 20);
		editPanel.add(txtGrapicsDeviceGPUcount);
		txtGrapicsDeviceGPUcount.setColumns(10);
		
		txtGrapicsDeviceVram = new JTextField();
		txtGrapicsDeviceVram.setBounds(180, 304, 78, 20);
		editPanel.add(txtGrapicsDeviceVram);
		txtGrapicsDeviceVram.setColumns(10);
		
		txtGrapicsDeviceModelName = new JTextField();
		txtGrapicsDeviceModelName.setBounds(262, 304, 78, 20);
		editPanel.add(txtGrapicsDeviceModelName);
		txtGrapicsDeviceModelName.setColumns(10);
		
		lblPort = new JLabel("port:");
		lblPort.setBounds(21, 356, 46, 14);
		editPanel.add(lblPort);
		
		portComboBox = new JComboBox<String>();
		portComboBox.setBounds(170, 352, 150, 22);
		editPanel.add(portComboBox);
		
		btnPortDelete = new JButton("x");
		btnPortDelete.setBounds(318, 352, 43, 23);
		btnPortDelete.setEnabled(false);
		editPanel.add(btnPortDelete);
		
		lblPortId = new JLabel("ID*");
		lblPortId.setBounds(10, 385, 110, 14);
		editPanel.add(lblPortId);
		
		lblPortType = new JLabel("type");
		lblPortType.setBounds(131, 385, 110, 14);
		editPanel.add(lblPortType);
		
		lblPortSlot = new JLabel("slot");
		lblPortSlot.setBounds(250, 385, 109, 14);
		editPanel.add(lblPortSlot);
		
		txtPortId = new JTextField();
		txtPortId.setBounds(10, 401, 110, 20);
		editPanel.add(txtPortId);
		txtPortId.setColumns(10);
		
		txtPortType = new JTextField();
		txtPortType.setBounds(130, 401, 110, 20);
		editPanel.add(txtPortType);
		txtPortType.setColumns(10);
		
		txtPortSlot = new JTextField();
		txtPortSlot.setBounds(250, 401, 109, 20);
		editPanel.add(txtPortSlot);
		txtPortSlot.setColumns(10);
		
		btnSetGraphicsDevicsAndPort = new JButton("Set Graphics-Device & Port");
		btnSetGraphicsDevicsAndPort.setBounds(10, 432, 349, 23);
		btnSetGraphicsDevicsAndPort.setEnabled(false);
		editPanel.add(btnSetGraphicsDevicsAndPort);
		
		nodeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				networkDeviceComboBox.removeAllItems();
				graphicsDeviceComboBox.removeAllItems();
				portComboBox.removeAllItems();
				
				txtNetworkDeviceId.setText("");
				txtNetworkDeviceAddress.setText("");
				txtNetworkDeviceNetwork.setText("");
				txtNetworkDeviceType.setText("");
				
				if(!(nodeComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					Element tempNode = myDoc.getNode((String)nodeComboBox.getSelectedItem());
					txtNodeId.setText(tempNode.getAttribute("id"));
					txtNodePurpose.setText(tempNode.getAttribute("purpose"));
					
					networkDeviceComboBox.setEnabled(true);
					btnNetworkDeviceDelete.setEnabled(true);
					graphicsDeviceComboBox.setEnabled(true);
					btnGraphicsDeviceDelete.setEnabled(true);
					portComboBox.setEnabled(true);
					btnPortDelete.setEnabled(true);
					btnSetNetworkDevice.setEnabled(true);
					btnSetGraphicsDevicsAndPort.setEnabled(true);
					
					ArrayList<Element> NDElements=myDoc.getNetworkDevices((String)nodeComboBox.getSelectedItem());
					ArrayList<Element> GDElements=myDoc.getGraphicsDevices((String)nodeComboBox.getSelectedItem());
					
					networkDeviceComboBox.addItem("*add new*");
					for(int i=0;i<NDElements.size();i++){
						networkDeviceComboBox.addItem((String)NDElements.get(i).getAttribute("id"));
					}
					
					graphicsDeviceComboBox.addItem("*add new*");
					for(int i=0;i<GDElements.size();i++){
						graphicsDeviceComboBox.addItem((String)GDElements.get(i).getAttribute("id"));
					}
					
					portComboBox.addItem("*add new*");
					
				}else{
					txtNodeId.setText("");
					txtNodePurpose.setText("");
					networkDeviceComboBox.setEnabled(false);
					btnNetworkDeviceDelete.setEnabled(false);
					graphicsDeviceComboBox.setEnabled(false);
					btnGraphicsDeviceDelete.setEnabled(false);
					portComboBox.setEnabled(false);
					btnPortDelete.setEnabled(false);
					btnSetNetworkDevice.setEnabled(false);
					btnSetGraphicsDevicsAndPort.setEnabled(false);
				}
				editPanel.repaint();
			}
		});
		
		btnNodeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(nodeComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removeNode(nodeComboBox.getSelectedItem().toString());
					textLogPane("removed node with the id "+nodeComboBox.getSelectedItem().toString());
					nodeComboBox.removeItemAt(nodeComboBox.getSelectedIndex());
					txtNodeId.setText("");
					txtNodePurpose.setText("");
				}else{
					textLogPane("error: choose a node to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		btnSetNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nodeComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
					if(!(txtNodeId.getText().equals("") || txtNodeId.getText().equals("*add new*"))){
						ArrayList<Element> tempList = myDoc.getNodes();
						ArrayList<String> idList = new ArrayList<String>();
						for(int i=0;i<tempList.size();i++){
							idList.add((String)tempList.get(i).getAttribute("id"));
						}
						if(idList.contains((String)txtNodeId.getText())){
							textLogPane("error: there is already a node with the id"+txtNodeId.getText());
						}else{
							myDoc.addNode(txtNodeId.getText());
							if(txtNodePurpose.getText()!="")myDoc.setNodePurpose(txtNodeId.getText(), txtNodePurpose.getText());
							nodeComboBox.addItem(txtNodeId.getText());
							textLogPane("added node with the id "+txtNodeId.getText());
							nodeComboBox.setSelectedItem(txtNodeId.getText());
						}
					}else{
						textLogPane("error: missing mandatory values");
					}
				}else{
					ArrayList<Element> tempList = myDoc.getNodes();
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtNodeId.getText()) && txtNodeId.getText().compareTo(nodeComboBox.getSelectedItem().toString())!=0){
						textLogPane("error: you can not rename this node, there is already a node with the id "+txtNodeId.getText());
					}else{
						if(!(txtNodeId.getText().equals("") || txtNodeId.getText().equals("*add new*"))){
							String tempID = txtNodeId.getText();
							String tempPurpose = txtNodePurpose.getText();
							if(txtNodeId.getText().compareTo(nodeComboBox.getSelectedItem().toString())!=0){
								myDoc.setNodeId(nodeComboBox.getSelectedItem().toString(), tempID);
								nodeComboBox.removeItemAt(nodeComboBox.getSelectedIndex());
								nodeComboBox.addItem(tempID);
							}
							if(!tempPurpose.equals(""))myDoc.setNodePurpose(tempID, tempPurpose);
							textLogPane("updated node with the id "+tempID);
							nodeComboBox.setSelectedItem(tempID);
						}else{
							textLogPane("error: missing mandatory values");
						}
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		
		
		networkDeviceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(networkDeviceComboBox.getSelectedItem()!=null){
					if(!(networkDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
						Element tempND = myDoc.getNetworkDevice((String)nodeComboBox.getSelectedItem(),(String)networkDeviceComboBox.getSelectedItem());
						txtNetworkDeviceId.setText(tempND.getAttribute("id"));
						txtNetworkDeviceAddress.setText(tempND.getAttribute("address"));
						txtNetworkDeviceNetwork.setText(tempND.getAttribute("network"));
						txtNetworkDeviceType.setText(tempND.getAttribute("type"));
					}else{
						txtNetworkDeviceId.setText("");
						txtNetworkDeviceAddress.setText("");
						txtNetworkDeviceNetwork.setText("");
						txtNetworkDeviceType.setText("");
					}
					editPanel.repaint();
				}
			}
		});
		
		btnNetworkDeviceDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(networkDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removeNetworkDevice(nodeComboBox.getSelectedItem().toString(),networkDeviceComboBox.getSelectedItem().toString());
					textLogPane("removed network-device with the id "+networkDeviceComboBox.getSelectedItem().toString());
					networkDeviceComboBox.removeItemAt(networkDeviceComboBox.getSelectedIndex());
					txtNetworkDeviceId.setText("");
					txtNetworkDeviceAddress.setText("");
					txtNetworkDeviceNetwork.setText("");
					txtNetworkDeviceType.setText("");
				}else{
					textLogPane("error: choose a network-device to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		btnSetNetworkDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(networkDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
					if(!txtNetworkDeviceId.getText().equals("") && !txtNetworkDeviceAddress.getText().equals("") && !txtNetworkDeviceNetwork.getText().equals("") && !txtNetworkDeviceType.equals("") && !txtNetworkDeviceId.getText().equals("*add new*")){
						ArrayList<Element> tempList = myDoc.getNetworkDevices((String)nodeComboBox.getSelectedItem());
						ArrayList<String> idList = new ArrayList<String>();
						for(int i=0;i<tempList.size();i++){
							idList.add((String)tempList.get(i).getAttribute("id"));
						}
						if(idList.contains((String)txtNetworkDeviceId.getText())){
							textLogPane("error: there is already a network-device with the id"+txtNetworkDeviceId.getText());
						}else{
							myDoc.addNetworkDevice(nodeComboBox.getSelectedItem().toString(), txtNetworkDeviceId.getText());
							myDoc.setNetworkDeviceAddress(nodeComboBox.getSelectedItem().toString(),txtNetworkDeviceId.getText(),txtNetworkDeviceAddress.getText());
							myDoc.setNetworkDeviceNetwork(nodeComboBox.getSelectedItem().toString(),txtNetworkDeviceId.getText(),txtNetworkDeviceNetwork.getText());
							myDoc.setNetworkDeviceType(nodeComboBox.getSelectedItem().toString(),txtNetworkDeviceId.getText(),txtNetworkDeviceType.getText());
							networkDeviceComboBox.addItem(txtNetworkDeviceId.getText());
							textLogPane("added network-device with the id "+txtNetworkDeviceId.getText());
							networkDeviceComboBox.setSelectedItem(txtNetworkDeviceId.getText());
						}
					}else{
						textLogPane("error: missing mandatory values");
					}
				}else{
					ArrayList<Element> tempList = myDoc.getNetworkDevices((String)nodeComboBox.getSelectedItem());
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtNetworkDeviceId.getText()) && txtNetworkDeviceId.getText().compareTo(networkDeviceComboBox.getSelectedItem().toString())!=0){
						textLogPane("error: you can not rename this network-device, there is already a network-device with the id "+txtNetworkDeviceId.getText());
					}else{
						if(!txtNetworkDeviceId.getText().equals("") && !txtNetworkDeviceAddress.getText().equals("") && !txtNetworkDeviceNetwork.getText().equals("") && !txtNetworkDeviceType.getText().equals("") && !txtNetworkDeviceId.getText().equals("*add new*")){
							String tempID = txtNetworkDeviceId.getText();
							String tempAddress = txtNetworkDeviceAddress.getText();
							String tempNetwork = txtNetworkDeviceNetwork.getText();
							String tempType = txtNetworkDeviceType.getText();
							if(txtNetworkDeviceId.getText().compareTo(networkDeviceComboBox.getSelectedItem().toString())!=0){
								myDoc.setNetworkDeviceId(nodeComboBox.getSelectedItem().toString(),networkDeviceComboBox.getSelectedItem().toString(),tempID);
								networkDeviceComboBox.removeItemAt(networkDeviceComboBox.getSelectedIndex());
								networkDeviceComboBox.addItem(tempID);
							}
							myDoc.setNetworkDeviceAddress(nodeComboBox.getSelectedItem().toString(),tempID,tempAddress);
							myDoc.setNetworkDeviceNetwork(nodeComboBox.getSelectedItem().toString(),tempID,tempNetwork);
							myDoc.setNetworkDeviceType(nodeComboBox.getSelectedItem().toString(),tempID,tempType);
							textLogPane("updated network-device with the id "+tempID);
							networkDeviceComboBox.setSelectedItem(tempID);
						}else{
							textLogPane("error: missing mandatory values");
						}
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		
		
		graphicsDeviceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				portComboBox.removeAllItems();
				portComboBox.addItem("*add new*");
				if(graphicsDeviceComboBox.getSelectedItem()!=null){
					if(!(graphicsDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
						Element tempGD = myDoc.getGraphicsDevice((String)nodeComboBox.getSelectedItem(),(String)graphicsDeviceComboBox.getSelectedItem());
						txtGrapicsDeviceId.setText(tempGD.getAttribute("id"));
						txtGrapicsDeviceGPUcount.setText(tempGD.getAttribute("gpu-count"));
						txtGrapicsDeviceModelName.setText(tempGD.getAttribute("model-name"));
						txtGrapicsDeviceVram.setText(tempGD.getAttribute("vram"));
						
						ArrayList<Element> PortElements=myDoc.getPorts(nodeComboBox.getSelectedItem().toString(), graphicsDeviceComboBox.getSelectedItem().toString());
						
						for(int i=0;i<PortElements.size();i++){
							portComboBox.addItem((String)PortElements.get(i).getAttribute("id"));
						}
					}else{
						txtGrapicsDeviceId.setText("");
						txtGrapicsDeviceGPUcount.setText("");
						txtGrapicsDeviceModelName.setText("");
						txtGrapicsDeviceVram.setText("");
					}
					editPanel.repaint();
				}
			}
		});
		
		btnGraphicsDeviceDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(graphicsDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removeGraphicsDevice(nodeComboBox.getSelectedItem().toString(),graphicsDeviceComboBox.getSelectedItem().toString());
					textLogPane("removed graphics-device with the id "+graphicsDeviceComboBox.getSelectedItem().toString());
					graphicsDeviceComboBox.removeItemAt(graphicsDeviceComboBox.getSelectedIndex());
					txtGrapicsDeviceId.setText("");
					txtGrapicsDeviceGPUcount.setText("");
					txtGrapicsDeviceModelName.setText("");
					txtGrapicsDeviceVram.setText("");
				}else{
					textLogPane("error: choose a graphics-device to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		portComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(portComboBox.getSelectedItem()!=null){
					if(!(portComboBox.getSelectedItem().toString().compareTo("*add new*")==0 || graphicsDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
						Element tempPort = myDoc.getPort((String)nodeComboBox.getSelectedItem(),(String)graphicsDeviceComboBox.getSelectedItem(),(String)portComboBox.getSelectedItem());
						txtPortId.setText(tempPort.getAttribute("id"));
						txtPortSlot.setText(tempPort.getAttribute("slot"));
						txtPortType.setText(tempPort.getAttribute("type"));
					}else{
						txtPortId.setText("");
						txtPortSlot.setText("");
						txtPortType.setText("");
					}
					editPanel.repaint();
				}
			}
		});
		
		btnPortDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(portComboBox.getSelectedItem().toString().compareTo("*add new*")==0 ||  graphicsDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removePort(nodeComboBox.getSelectedItem().toString(),graphicsDeviceComboBox.getSelectedItem().toString(),portComboBox.getSelectedItem().toString());
					textLogPane("removed port with the id "+portComboBox.getSelectedItem().toString());
					portComboBox.removeItemAt(portComboBox.getSelectedIndex());
					txtPortId.setText("");
					txtPortSlot.setText("");
					txtPortType.setText("");
				}else{
					textLogPane("error: choose a graphics-device or port to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		btnSetGraphicsDevicsAndPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphicsDeviceComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
					if(!(txtGrapicsDeviceId.getText().equals("") || txtGrapicsDeviceId.getText().equals("*add new*"))){
						ArrayList<Element> tempList = myDoc.getGraphicsDevices((String)nodeComboBox.getSelectedItem());
						ArrayList<String> idList = new ArrayList<String>();
						for(int i=0;i<tempList.size();i++){
							idList.add((String)tempList.get(i).getAttribute("id"));
						}
						if(idList.contains((String)txtGrapicsDeviceId.getText())){
							textLogPane("error: there is already a graphics-device with the id"+txtNetworkDeviceId.getText());
						}else{
							myDoc.addGraphicsDevice(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText());
							if(!txtGrapicsDeviceGPUcount.getText().equals(""))myDoc.setGraphicsDeviceGpuCount(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText(), txtGrapicsDeviceGPUcount.getText());
							if(!txtGrapicsDeviceModelName.getText().equals(""))myDoc.setGraphicsDeviceModelName(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText(), txtGrapicsDeviceModelName.getText());
							if(!txtGrapicsDeviceVram.getText().equals(""))myDoc.setGraphicsDeviceVram(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText(), txtGrapicsDeviceVram.getText());
							graphicsDeviceComboBox.addItem(txtGrapicsDeviceId.getText());
							textLogPane("added graphics-device with the id "+txtGrapicsDeviceId.getText());
							if(portComboBox.getSelectedItem().toString().equals("*add new*") && !(txtPortId.getText().equals("")||txtPortId.getText().equals("*add new*"))){
								myDoc.addPort(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText(), txtPortId.getText());
								if(!txtPortSlot.getText().equals(""))myDoc.setPortSlot(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText(), txtPortId.getText(), txtPortSlot.getText());
								if(!txtPortType.getText().equals(""))myDoc.setPortType(nodeComboBox.getSelectedItem().toString(), txtGrapicsDeviceId.getText(), txtPortId.getText(), txtPortType.getText());
								textLogPane("also added port with the id "+txtPortId.getText());
							}
						}
					}else{
						textLogPane("error: missing mandatory values");
					}
				}else{
					ArrayList<Element> tempList = myDoc.getGraphicsDevices((String)nodeComboBox.getSelectedItem());
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtGrapicsDeviceId.getText()) && txtGrapicsDeviceId.getText().compareTo(graphicsDeviceComboBox.getSelectedItem().toString())!=0){
						textLogPane("error: you can not rename this graphics-device, there is already a graphics-device with the id "+txtGrapicsDeviceId.getText());
					}else{
						if(!txtGrapicsDeviceId.getText().equals("") && !txtGrapicsDeviceId.getText().equals("*add new*")){
							String tempID = txtGrapicsDeviceId.getText();
							String tempGPUcount = txtGrapicsDeviceGPUcount.getText();
							String tempVram = txtGrapicsDeviceVram.getText();
							String tempModelName = txtGrapicsDeviceModelName.getText();
							if(txtGrapicsDeviceId.getText().compareTo(graphicsDeviceComboBox.getSelectedItem().toString())!=0){
								myDoc.setGraphicsDeviceId(nodeComboBox.getSelectedItem().toString(),graphicsDeviceComboBox.getSelectedItem().toString(),tempID);
								graphicsDeviceComboBox.removeItemAt(graphicsDeviceComboBox.getSelectedIndex());
								graphicsDeviceComboBox.addItem(tempID);
							}
							if(!tempGPUcount.equals(""))myDoc.setGraphicsDeviceGpuCount(nodeComboBox.getSelectedItem().toString(), tempID, tempGPUcount);
							if(!tempVram.equals(""))myDoc.setGraphicsDeviceVram(nodeComboBox.getSelectedItem().toString(), tempID, tempVram);
							if(!tempModelName.equals(""))myDoc.setGraphicsDeviceModelName(nodeComboBox.getSelectedItem().toString(), tempID, tempModelName);
							textLogPane("updated graphics-device with the id "+tempID);
							
							if(portComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
								if(!(txtPortId.getText().equals("") || txtPortId.getText().equals("*add new*"))){
									ArrayList<Element> tempList2 = myDoc.getPorts((String)nodeComboBox.getSelectedItem(),(String)graphicsDeviceComboBox.getSelectedItem());
									ArrayList<String> idList2 = new ArrayList<String>();
									for(int i=0;i<tempList2.size();i++){
										idList2.add((String)tempList2.get(i).getAttribute("id"));
									}
									if(idList2.contains((String)txtPortId.getText())){
										textLogPane("error: there is already a port with the id"+txtPortId.getText());
									}else{
										myDoc.addPort(nodeComboBox.getSelectedItem().toString(), tempID,txtPortId.getText().toString());
										if(!txtPortSlot.getText().equals(""))myDoc.setPortSlot(nodeComboBox.getSelectedItem().toString(), tempID, txtPortId.getText().toString(), txtPortSlot.getText().toString());
										if(!txtPortType.getText().equals(""))myDoc.setPortType(nodeComboBox.getSelectedItem().toString(), tempID, txtPortId.getText().toString(), txtPortType.getText().toString());
										portComboBox.addItem(txtPortId.getText());
										textLogPane("added port with the id "+txtPortId.getText());
									}
								}else{
									textLogPane("error: missing mandatory values for port");
								}
							}else{
								ArrayList<Element> tempList2 = myDoc.getGraphicsDevices((String)nodeComboBox.getSelectedItem());
								ArrayList<String> idList2 = new ArrayList<String>();
								for(int i=0;i<tempList2.size();i++){
									idList2.add((String)tempList2.get(i).getAttribute("id"));
								}
								if(idList2.contains((String)txtPortId.getText()) && txtPortId.getText().compareTo(portComboBox.getSelectedItem().toString())!=0){
									textLogPane("error: you can not rename this port, there is already a port with the id "+txtPortId.getText());
								}else{
									if(!txtPortId.getText().equals("") && !txtPortId.getText().equals("*add new*")){
										String tempID2 = txtPortId.getText();
										String tempSlot = txtPortSlot.getText();
										String tempType = txtPortType.getText();
										if(txtPortId.getText().compareTo(portComboBox.getSelectedItem().toString())!=0){
											myDoc.setPortId(nodeComboBox.getSelectedItem().toString(),tempID,nodeComboBox.getSelectedItem().toString(),tempID2);
											portComboBox.removeItemAt(portComboBox.getSelectedIndex());
											portComboBox.addItem(tempID2);
										}
										if(!tempSlot.equals(""))myDoc.setPortSlot(nodeComboBox.getSelectedItem().toString(), tempID,tempID2, tempGPUcount);
										if(!tempType.equals(""))myDoc.setPortType(nodeComboBox.getSelectedItem().toString(), tempID,tempID2, tempVram);
										textLogPane("updated port with the id "+tempID2);
									}else{
										textLogPane("error: missing mandatory values for port");
									}
								}
							}
						}else{
							textLogPane("error: missing mandatory values");
						}
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});
		
		editPanel.repaint();
	}
	private void networkEditor(){//Alle Funktionen zum Editieren der Network Elemente und Unterelemente
		//Oben Zeichnen des Interface
		//Unten Actionlistener für interaktiven Elemente
		resetEdit();
		editPanel.repaint();
		
		ArrayList<Element> networkElements=myDoc.getNetworks();
		networkComboBox = new JComboBox<String>();
		networkComboBox.addItem("*add new*");
		for(int i=0;i<networkElements.size();i++){
			networkComboBox.addItem((String)networkElements.get(i).getAttribute("id"));
		}
		networkComboBox.setBounds(165, 25, 150, 22);
		editPanel.add(networkComboBox);
		
		btnDeleteNetwork = new JButton("x");
		btnDeleteNetwork.setBounds(316, 25, 43, 23);
		editPanel.add(btnDeleteNetwork);
		
		txtNetworkIDLbl = new JLabel("ID*");
		txtNetworkIDLbl.setBounds(10, 58, 103, 14);
		editPanel.add(txtNetworkIDLbl);
		
		txtNetworkID = new JTextField();
		txtNetworkID.setBounds(10, 73, 103, 20);
		editPanel.add(txtNetworkID);
		txtNetworkID.setColumns(10);
		
		txtNetworkBandwidthLbl = new JLabel("Bandwidth");
		txtNetworkBandwidthLbl.setBounds(132, 58, 103, 14);
		editPanel.add(txtNetworkBandwidthLbl);
		
		txtNetworkBandwidth = new JTextField();
		txtNetworkBandwidth.setBounds(132, 73, 103, 20);
		editPanel.add(txtNetworkBandwidth);
		txtNetworkBandwidth.setColumns(10);
		
		txtNetworkSubnetmaskLbl = new JLabel("Subnetmask");
		txtNetworkSubnetmaskLbl.setBounds(256, 58, 103, 14);
		editPanel.add(txtNetworkSubnetmaskLbl);
		
		txtNetworkSubnetmask = new JTextField();
		txtNetworkSubnetmask.setBounds(256, 73, 103, 20);
		editPanel.add(txtNetworkSubnetmask);
		txtNetworkSubnetmask.setColumns(10);
				
		BtnSetNetwork = new JButton("Set Network");
		BtnSetNetwork.setBounds(10, 99, 349, 23);
		editPanel.add(BtnSetNetwork);
		
		editPanel.repaint();

		btnDeleteNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(networkComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removeNetwork(networkComboBox.getSelectedItem().toString());
					textLogPane("removed network with the id "+networkComboBox.getSelectedItem().toString());
					networkComboBox.removeItemAt(networkComboBox.getSelectedIndex());
					txtNetworkID.setText("");
					txtNetworkBandwidth.setText("");
					txtNetworkSubnetmask.setText("");
				}else{
					textLogPane("error: choose a network to delete first");
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});

		networkComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(networkComboBox.getSelectedItem()!="*add new*"){
					Element tempNetwork = myDoc.getNetwork((String)networkComboBox.getSelectedItem());
					txtNetworkID.setText(tempNetwork.getAttribute("id"));
					txtNetworkBandwidth.setText(tempNetwork.getAttribute("bandwidth"));
					txtNetworkSubnetmask.setText(tempNetwork.getAttribute("subnet-mask"));
				}else{
					txtNetworkID.setText("");
					txtNetworkBandwidth.setText("");
					txtNetworkSubnetmask.setText("");
				}
				editPanel.repaint();
			}
		});

		BtnSetNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(networkComboBox.getSelectedItem().toString().compareTo("*add new*")==0){
					ArrayList<Element> tempList = myDoc.getNetworks();
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtNetworkID.getText())){
						textLogPane("error: there is already a network with the id"+txtNetworkID.getText());
					}else{
						if(!(txtNetworkID.getText().equals("") || txtNetworkID.getText().equals("*add new*"))){
							myDoc.addNetwork(txtNetworkID.getText());
							if(!txtNetworkBandwidth.getText().equals(""))myDoc.setNetworkBandwidth(txtNetworkID.getText(), txtNetworkBandwidth.getText());
							if(!txtNetworkSubnetmask.getText().equals(""))myDoc.setNetworkSubnetMask(txtNetworkID.getText(), txtNetworkBandwidth.getText());
							networkComboBox.addItem(txtNetworkID.getText());
							textLogPane("added network with the id "+txtNetworkID.getText());
							networkComboBox.setSelectedItem(txtNetworkID.getText());
						}else{
							textLogPane("error: missing mandatory values");
						}
					}
				}else{
					ArrayList<Element> tempList = myDoc.getNetworks();
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtNetworkID.getText()) && txtNetworkID.getText().compareTo(networkComboBox.getSelectedItem().toString())!=0){
						textLogPane("error: there is already a network with the id"+txtNetworkID.getText());
					}else{
						if(!(txtNetworkID.getText().equals("") || txtNetworkID.getText().equals("*add new*"))){
							String tempID = txtNetworkID.getText();
							String tempBandW = txtNetworkBandwidth.getText();
							String tempSubN = txtNetworkSubnetmask.getText();
							if(txtNetworkID.getText().compareTo(networkComboBox.getSelectedItem().toString())!=0){
								myDoc.setNetworkId(networkComboBox.getSelectedItem().toString(), tempID);
								networkComboBox.addItem(tempID);
								networkComboBox.removeItemAt(networkComboBox.getSelectedIndex());
							}
							if(tempBandW!="")myDoc.setNetworkBandwidth(tempID, tempBandW);
							if(tempSubN!="")myDoc.setNetworkSubnetMask(tempID, tempSubN);
							textLogPane("updated network with the id "+tempID);
							networkComboBox.setSelectedItem(tempID);
						}else{
							textLogPane("error: missing mandatory values");
						}
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});

	}
	
	//Update der XML-Highlight-Ansicht über eine Zwischenkopie
	private void updateEditorPane(){
		try {
			myDoc.save("temp.output.xml");
		} catch (TransformerFactoryConfigurationError e1) {
			
			e1.printStackTrace();
			textLogPane("error: "+e1.getMessage());
		} catch (TransformerException e1) {
			
			e1.printStackTrace();
			textLogPane("error: "+e1.getMessage());
		}
		File file = new File("temp.output.xml");
		String dateiinhalt=loadFile(file.getPath());
		editorPane.setEditorKit(new XMLEditorKit());
		editorPane.setText(dateiinhalt);
	}
	
	//Laden des Textes einer Datei
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
			textLogPane("error: "+e.getMessage());
        } 

        return buff.toString(); 
	}
	
	//Text in Statuszeile schreiben
	private void textLogPane(String text){
		logPane.setText(logPane.getText()+"\n- "+text);
	}
	
	//Test ob String ein numerischer Wer ist
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
