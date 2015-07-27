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
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;




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
    
    
    /**
     * UI Elemente 
     */
    
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
    private JComboBox DisplaySetupComboBox;
    private JLabel lblDisplaySetupId;
    private JLabel lblSetupDisplayEyeDistance;
    private JButton btnSetDisplaySetup;
    private JSeparator displaySetupSeperator1;
    private JLabel lblUser;
    private JComboBox userComboBox;
    private JButton btnUserDelete;
    private JLabel lblPosition;
    private JLabel lblOrientation;
    private JButton btnSetUser;
    private JSeparator DisplaySetupSeperator2;
    private JLabel lblDisplay;
    private JButton btnDisplayDelete;
    private JComboBox displayComboBox;
    private JLabel lblDisplayId;
    private JLabel lblDisplayPortref;
    private JLabel lblDisplayType;
    private JLabel lblDisplayStereo;
    private JComboBox displayStereoComboBox;
    private JLabel lblDisplayPixelsize;
    private JLabel lblDisplayMetricsize;
    private JButton btnSetDisplay;
    private JLabel lblDisplayPhysical1;
    private JLabel lblDisplayPhysical2;
    private JLabel lblDisplayVirtual1;
    private JLabel lblDisplayVirtual2;

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
					if((String) superComboBox.getSelectedItem()=="node:") nodeEditor();
					if((String) superComboBox.getSelectedItem()=="displaysetup:") displaySetupEditor();
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
	private void displaySetupEditor(){
		resetEdit();
		editPanel.repaint();
		
		btnDisplaySetupDelete = new JButton("x");
		btnDisplaySetupDelete.setBounds(316, 25, 43, 23);
		editPanel.add(btnDisplaySetupDelete);
		
		DisplaySetupComboBox = new JComboBox();
		DisplaySetupComboBox.setBounds(170, 25, 144, 22);
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
		
		userComboBox = new JComboBox();
		userComboBox.setBounds(170, 149, 144, 22);
		editPanel.add(userComboBox);
		
		btnUserDelete = new JButton("x");
		btnUserDelete.setBounds(316, 149, 43, 23);
		editPanel.add(btnUserDelete);
		
		lblPosition = new JLabel("position (x,y,z)");
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
		
		lblOrientation = new JLabel("orientation (x,y,z)");
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
		editPanel.add(btnSetUser);
		
		DisplaySetupSeperator2 = new JSeparator();
		DisplaySetupSeperator2.setBounds(10, 262, 349, 2);
		editPanel.add(DisplaySetupSeperator2);
		
		lblDisplay = new JLabel("display");
		lblDisplay.setBounds(20, 275, 46, 14);
		editPanel.add(lblDisplay);
		
		btnDisplayDelete = new JButton("x");
		btnDisplayDelete.setBounds(316, 271, 43, 23);
		editPanel.add(btnDisplayDelete);
		
		displayComboBox = new JComboBox();
		displayComboBox.setBounds(170, 271, 144, 22);
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
		
		lblDisplayStereo = new JLabel("stereo");
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
		
		displayStereoComboBox = new JComboBox();
		displayStereoComboBox.setModel(new DefaultComboBoxModel(new String[] {"none", "left-eye", "right-eye"}));
		displayStereoComboBox.setBounds(295, 315, 64, 22);
		editPanel.add(displayStereoComboBox);
		
		lblDisplayPixelsize = new JLabel("pixel-size (x,y)");
		lblDisplayPixelsize.setBounds(10, 347, 150, 14);
		editPanel.add(lblDisplayPixelsize);
		
		lblDisplayMetricsize = new JLabel("metric-size (x,y)");
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
		
		lblDisplayPhysical1 = new JLabel("< physical >");
		lblDisplayPhysical1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayPhysical1.setBounds(125, 432, 119, 14);
		editPanel.add(lblDisplayPhysical1);
		
		lblDisplayPhysical2 = new JLabel("< physical >");
		lblDisplayPhysical2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayPhysical2.setBounds(125, 610, 119, 14);
		editPanel.add(lblDisplayPhysical2);
		
		lblDisplayVirtual1 = new JLabel("virtual");
		lblDisplayVirtual1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayVirtual1.setBounds(10, 488, 75, 77);
		editPanel.add(lblDisplayVirtual1);
		
		lblDisplayVirtual2 = new JLabel("virtual");
		lblDisplayVirtual2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayVirtual2.setBounds(284, 488, 75, 77);
		editPanel.add(lblDisplayVirtual2);
		
		editPanel.repaint();
	}
	private void nodeEditor(){
		resetEdit();
		editPanel.repaint();
		nodeComboBox = new JComboBox<String>();
		nodeComboBox.setBounds(170, 25, 150, 22);
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
		editPanel.add(btnSetNetworkDevice);
		
		btnNetworkDeviceDelete = new JButton("x");
		btnNetworkDeviceDelete.setBounds(318, 148, 43, 23);
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
		editPanel.add(btnSetGraphicsDevicsAndPort);
		
		editPanel.repaint();
	}
	private void networkEditor(){
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
						myDoc.addNetwork(txtNetworkID.getText());
						if(txtNetworkBandwidth.getText()!="")myDoc.setNetworkBandwidth(txtNetworkID.getText(), txtNetworkBandwidth.getText());
						if(txtNetworkSubnetmask.getText()!="")myDoc.setNetworkSubnetMask(txtNetworkID.getText(), txtNetworkBandwidth.getText());
						networkComboBox.addItem(txtNetworkID.getText());
						textLogPane("added network with the id "+txtNetworkID.getText());
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
					}
				}
				updateEditorPane();
				editPanel.repaint();
			}
		});

		txtNetworkID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ArrayList<Element> tempList = myDoc.getNetworks();
				ArrayList<String> idList = new ArrayList<String>();
				for(int i=0;i<tempList.size();i++){
					idList.add((String)tempList.get(i).getAttribute("id"));
				}
				if((idList.contains(txtNetworkID.getText()) && txtNetworkID.getText().compareTo(networkComboBox.getSelectedItem().toString())!=0)||txtNetworkID.getText().compareTo("*add new*")==0) txtNetworkID.setBackground(new Color(255, 64, 64));
				else txtNetworkID.setBackground(new Color(255, 255, 255));
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
