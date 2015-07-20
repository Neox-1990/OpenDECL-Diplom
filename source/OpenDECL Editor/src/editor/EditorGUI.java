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
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import xmleditorkit.*;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class EditorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel editPanel;
	private OpenDECLdoc myDoc;
	private JTextPane logPane;
	private final static String DEFAULT_MIN_URI = "default-min.xml";
	
	private static final JFileChooser xmlfc = new JFileChooser();
    
    private JEditorPane editorPane;
    private JTextField txtID;
    private JTextField txtBandwidth;
    private JTextField txtSubnetmask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		xmlfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		xmlfc.setFileFilter(new FileNameExtensionFilter("XML file", "xml"));
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
		
		editPanel = new JPanel();
		editPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		editPanel.setBounds(637, 11, 369, 647);
		contentPane.add(editPanel);
		editPanel.setLayout(null);
		
		String[] superComboBoxString = {"node:","network:","displaysetup:"};
		JComboBox superComboBox = new JComboBox(superComboBoxString);
		superComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myDoc!=null){
					if((String) superComboBox.getSelectedItem()=="network:") networkEditor();
				}else{
					textLogPane("no document selected");
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
		            textLogPane("opening: "+file.getPath());
		            String dateiinhalt=loadFile(file.getPath());
					editorPane.setText("");
					editorPane.setText(dateiinhalt);
					try {
						myDoc = new  OpenDECLdoc(file.getPath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textLogPane(file.getPath()+" succesfully loaded");
		        } else {
		        	textLogPane("aborted opening");
		        }
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
	private void resetEdit(){
		editPanel.removeAll();
	}
	private void networkEditor(){
		ArrayList<Element> networkElements=myDoc.getNetworks();
		JComboBox<String> networkComboBox = new JComboBox<String>();
		networkComboBox.addItem("*add new*");
		for(int i=0;i<networkElements.size();i++){
			networkComboBox.addItem((String)networkElements.get(i).getAttribute("id"));
		}
		networkComboBox.setBounds(165, 25, 150, 22);
		editPanel.add(networkComboBox);
		
		JButton btnDeleteNetwork = new JButton("x");
		btnDeleteNetwork.setBounds(316, 25, 43, 23);
		editPanel.add(btnDeleteNetwork);
		
		JLabel txtIDLbl = new JLabel("ID*");
		txtIDLbl.setBounds(10, 58, 103, 14);
		editPanel.add(txtIDLbl);
		
		txtID = new JTextField();
		txtID.setBounds(10, 73, 103, 20);
		editPanel.add(txtID);
		txtID.setColumns(10);
		
		JLabel txtBandwidthLbl = new JLabel("Bandwidth");
		txtBandwidthLbl.setBounds(132, 58, 103, 14);
		editPanel.add(txtBandwidthLbl);
		
		txtBandwidth = new JTextField();
		txtBandwidth.setBounds(132, 73, 103, 20);
		editPanel.add(txtBandwidth);
		txtBandwidth.setColumns(10);
		
		JLabel txtSubnetmaskLbl = new JLabel("Subnetmask");
		txtSubnetmaskLbl.setBounds(256, 58, 103, 14);
		editPanel.add(txtSubnetmaskLbl);
		
		txtSubnetmask = new JTextField();
		txtSubnetmask.setBounds(256, 73, 103, 20);
		editPanel.add(txtSubnetmask);
		txtSubnetmask.setColumns(10);
				
		JButton BtnSetNetwork = new JButton("Set Network");
		BtnSetNetwork.setBounds(10, 99, 349, 23);
		editPanel.add(BtnSetNetwork);
		
		editPanel.repaint();

		btnDeleteNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(networkComboBox.getSelectedItem().toString().compareTo("*add new*")==0)){
					myDoc.removeNetwork(networkComboBox.getSelectedItem().toString());
					textLogPane("removed network with the id "+networkComboBox.getSelectedItem().toString());
					networkComboBox.removeItemAt(networkComboBox.getSelectedIndex());
					txtID.setText("");
					txtBandwidth.setText("");
					txtSubnetmask.setText("");
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
					txtID.setText(tempNetwork.getAttribute("id"));
					txtBandwidth.setText(tempNetwork.getAttribute("bandwidth"));
					txtSubnetmask.setText(tempNetwork.getAttribute("subnet-mask"));
				}else{
					txtID.setText("");
					txtBandwidth.setText("");
					txtSubnetmask.setText("");
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
					if(idList.contains((String)txtID.getText())){
						textLogPane("error: there is already a network with the id"+txtID.getText());
					}else{
						myDoc.addNetwork(txtID.getText());
						if(txtBandwidth.getText()!="")myDoc.setNetworkBandwidth(txtID.getText(), txtBandwidth.getText());
						if(txtSubnetmask.getText()!="")myDoc.setNetworkSubnetMask(txtID.getText(), txtBandwidth.getText());
						networkComboBox.addItem(txtID.getText());
						textLogPane("added network with the id "+txtID.getText());
					}
				}else{
					ArrayList<Element> tempList = myDoc.getNetworks();
					ArrayList<String> idList = new ArrayList<String>();
					for(int i=0;i<tempList.size();i++){
						idList.add((String)tempList.get(i).getAttribute("id"));
					}
					if(idList.contains((String)txtID.getText()) && txtID.getText().compareTo(networkComboBox.getSelectedItem().toString())!=0){
						textLogPane("error: there is already a network with the id"+txtID.getText());
					}else{
						String tempID = txtID.getText();
						String tempBandW = txtBandwidth.getText();
						String tempSubN = txtSubnetmask.getText();
						if(txtID.getText().compareTo(networkComboBox.getSelectedItem().toString())!=0){
							myDoc.setNetworkId(networkComboBox.getSelectedItem().toString(), tempID);
							networkComboBox.addItem(tempID);
							networkComboBox.removeItemAt(networkComboBox.getSelectedIndex());
						}
						if(tempBandW!="")myDoc.setNetworkBandwidth(tempID, tempBandW);
						if(tempSubN!="")myDoc.setNetworkSubnetMask(tempID, tempSubN);
						textLogPane("updated network with the id "+tempID);
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});

		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ArrayList<Element> tempList = myDoc.getNetworks();
				ArrayList<String> idList = new ArrayList<String>();
				for(int i=0;i<tempList.size();i++){
					idList.add((String)tempList.get(i).getAttribute("id"));
				}
				if((idList.contains(txtID.getText()) && txtID.getText().compareTo(networkComboBox.getSelectedItem().toString())!=0)||txtID.getText().compareTo("*add new*")==0) txtID.setBackground(new Color(255, 64, 64));
				else txtID.setBackground(new Color(255, 255, 255));
				editPanel.repaint();
			}
		});
	}
	
	private void updateEditorPane(){
		try {
			myDoc.save("temp.output.xml");
		} catch (TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	private void textLogPane(String text){
		logPane.setText(logPane.getText()+"\n- "+text);
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
}
