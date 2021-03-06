package core;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.util.ArrayList;
import core.help.HelpFrame;
import core.settings.SettingsFrame;

import javax.swing.*;

/**
 * GUI for the Printer Search Program.
 * 
 * @author  Joshua Becker, Jake Leonard, Alireza Bahremand, Marcinina Alvaran
 * @version 1.1
 * @see     PrinterLabel
 * @see     AddPrinterFrame
 */
@SuppressWarnings("serial")
public class MenuWindow extends JFrame
{
	private JFrame m_Menu_F;
	private JPanel m_Menu_P;
	private SearchResultPanel m_SearchResult_P;
	private SearchFiltersPanel m_SearchFilter_P;
	private JToolBar m_ToolBar;
	private JScrollPane m_ScrollPane;
	@SuppressWarnings("unused")
	private ToolBox m_ToolBox;
	private MenuWindow m_MenuUI;

	public static int FRAME_WIDTH;
	public static int FRAME_HEIGHT;
	public static int s_SCREEN_WIDTH;
	public static int s_SCREEN_HEIGHT;

	public static boolean haveWeUpdated = false;
	private boolean  macOSFound = ToolBox.isMacOS();


/**
 * Creates window for Printer Search Program
 */
public MenuWindow(String name)
{
	super(name);
	m_ToolBox = new ToolBox();
	GraphicsDevice graphicsDevice;
	
	// Obtain window dimensions
	graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice();
	s_SCREEN_WIDTH = graphicsDevice.getDisplayMode().getWidth();
	s_SCREEN_HEIGHT = graphicsDevice.getDisplayMode().getHeight();
	if(s_SCREEN_WIDTH < 1370)
	{
		FRAME_WIDTH = (int) ((int) s_SCREEN_WIDTH *.90);
		FRAME_HEIGHT = (int) ((int) s_SCREEN_HEIGHT *0.90);
	}else
	{
		FRAME_WIDTH = (int) ((int) s_SCREEN_WIDTH *0.80);
		FRAME_HEIGHT = (int) ((int) s_SCREEN_HEIGHT *0.80);
	}

	// Set up menu UI window
    createComponents();
    designComponents();
    addActionListeners();
    addComponents();
    m_MenuUI = this;
    
	// Instantiate non-GUI objects
	m_Menu_F = this;
}

/**
 * Instantiates GUI components.
 */
private void createComponents() {
	// Instantiate GUI framework
	m_Menu_P = new JPanel();
	m_ToolBar = new JToolBar("ToolBar");
	
	// Instantiate GUI layout components and button
	m_ScrollPane = new JScrollPane();
	m_SearchResult_P = new SearchResultPanel("Search Results");
	m_SearchFilter_P = new SearchFiltersPanel("Search Filter");
}

/**
 * Sets GUI component values.
 */
private void designComponents() {
    addIcon();
	
	/*
	 * Finalize window close operation and set window size
	 * Added 50 to height because mac interface will not show filter button.
	 * Set to resizable to accommodate for filter button at bottom.
	 */
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//setLayout(new BorderLayout(5,5));
	setSize(FRAME_WIDTH, (FRAME_HEIGHT+50));
	setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	setMaximumSize(new Dimension(FRAME_WIDTH , (FRAME_HEIGHT+50)));
	setLocationRelativeTo(null);						
	setResizable(true);
	
	// Set panel layout and size
	m_Menu_P.setLayout(new BorderLayout(5,5));
	m_Menu_P.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	m_Menu_P.setMaximumSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));

	// Set up scroll pane
	m_ScrollPane.setOpaque(false);
	m_ScrollPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	// Add results to scroll pane
	m_ScrollPane.setViewportView(m_SearchResult_P);
	m_ScrollPane.setBounds(new Rectangle(FRAME_WIDTH , FRAME_HEIGHT*2));
	
	// Set up remaining GUI components
	designToolBar();
}

/**
 * Sets values for the tool bar GUI components.
 */
private void designToolBar()
{
	// Set up settings button
	JButton button = new JButton("Settings");
	button.setActionCommand("Settings");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
	
	// Set up help button
	button = new JButton("Help");
	button.setActionCommand("Help");
    button.addActionListener(new ButtonListener());
    m_ToolBar.add(button);
	
	// Set up button to add printer
	button = new JButton("Add Printer");
	button.setActionCommand("Add Printer");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
	
	button = new JButton("Export");
	button.setActionCommand("Export");
	button.addActionListener(new ButtonListener());
	m_ToolBar.add(button);
}

/**
 * Adds action listeners to GUI components.
 */
private void addActionListeners()
{
	m_SearchFilter_P.getClearResults().addActionListener(new ButtonListener());
	m_SearchFilter_P.getFilterResults().addActionListener(new ButtonListener());
	m_SearchFilter_P.getMaterials().addActionListener(new ComboListener());
}

/**
 * Add components to menu framework.
 */
private void addComponents()
{	
	m_Menu_P.add(m_ToolBar, BorderLayout.PAGE_START);
	m_Menu_P.add(m_SearchFilter_P, BorderLayout.LINE_START);
	m_Menu_P.add(m_ScrollPane, BorderLayout.LINE_END);
	
	add(m_Menu_P);
	setPreferredSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
}
/**
 * adds an Icon to the frame works with Windows and Mac.
 */
private void addIcon()
{
	ImageIcon img = new ImageIcon("src\\sift-logo-color.png");	// Windows image path.;

	if(macOSFound){
		ImageIcon imgChange = new ImageIcon("sift-logo-color.png");	// Mac image path.
		img = imgChange;
	}
	Image image = (img.getImage());
	setIconImage(image);
}
/**
 * An action listener for a Combo Box.
 * 
 * @author Joshua Becker
 *
 */
private class ComboListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent action) {
		String command;
		
		command = action.getActionCommand();
		switch(command)
		{
		    case "RangeOfMaterials":
		    	/*-----------------------TESTING STAGES-------------------
									selectedItem = (String) m_SearchFilter_P.getMaterials().getSelectedItem();
				
									// Add new material to materials combo box if not already included
									if(!selectedItem.equals("Search All") && !selectedItem.equals("Clear All"))
									{
										JLabel temp = new JLabel(selectedItem);
										temp.setAlignmentX(Component.CENTER_ALIGNMENT);
										m_RangeOfMaterials.add(selectedItem);
										index = m_SearchFilter_P.getComponentZOrder(m_SearchFilter_P.getMaterials());
										m_SearchFilter_P.add(temp,index);
									}
				
									// Refresh search parameters
									else
									{
										m_SearchFilter_P.revalidate();
									}
									m_Menu_P.revalidate();
									*/
				break;
				
			default: JOptionPane.showMessageDialog(m_Menu_F,"Command: " + command,"Unknown Command", JOptionPane.PLAIN_MESSAGE);
				break;
		}
		
	}
	
}
/**
 * An action listener for a button.
 * <p>
 * <b>NOTE on print method:</b>
 * <p>
 * A <code>PrinterJob</code> calls the <code>Printable</code>
 * interface to request that a page be rendered into the context
 * specified by <code>graphics</code>.  The format of the page to
 * be drawn is specified by <code>pageFormat</code>.  The zero
 * based index of the requested page is specified by
 * <code>pageIndex</code>.  If the requested page does not exist
 * then this method returns NO_SUCH_PAGE; otherwise PAGE_EXISTS is
 * returned.  The <code>Graphics</code> class or subclass implements
 * the {@link PrinterGraphics} interface to provide additional
 * information.  If the <code>Printable</code> object aborts the
 * print job then it throws a {@link PrinterException}.
 * 
 * @author Joshua Becker, Jacob Leonard, Alireza Bahremand, Marcinina Alvaran
 *
 */
private class ButtonListener implements ActionListener, Printable
{
	@Override
	public void actionPerformed(ActionEvent action) {

		String command = action.getActionCommand();
		switch(command)
		{
			case "Filter Results":
									haveWeUpdated = true;
									m_SearchFilter_P.revalidate();
									m_SearchResult_P.updateSearchResults(m_SearchFilter_P);
									m_Menu_P.revalidate();
									revalidate();
				break;
			case "Clear Results":
									haveWeUpdated = false;
									m_SearchResult_P.clearResults();
									// Reset compression
									m_SearchFilter_P.getCompression().getMinSpinner().setValue(0.000);
									// Reset tension
									m_SearchFilter_P.getTension().getMinSpinner().setValue(0.000);
									// Reset tolerance
									m_SearchFilter_P.getTolerance().getMaxSpinner().setValue(0.000);
									// Reset impact
									m_SearchFilter_P.getImpact().getMinSpinner().setValue(0.000);
									// Reset finish
									m_SearchFilter_P.getFinish().getMaxSpinner().setValue(0.000);
									// Reset Materials
									m_SearchFilter_P.getMaterials().setSelectedIndex(0);
									m_Menu_P.revalidate();
									revalidate();
				break;

			case "Help": 
			                HelpFrame help = new HelpFrame();
			                help.pack();
			                help.setVisible(true);
				break;
			case "Settings":
							SettingsFrame settingsFrame = new SettingsFrame();
							settingsFrame.pack();
							settingsFrame.setVisible(true);
				break;
			case "Add Printer": 
							AddPrinterFrame apf = new AddPrinterFrame(m_MenuUI);
							apf.pack();
							apf.setVisible(true);
				break;
			case "Export":
						    PrintDocument printDocument;
						    
						    // getPrinterList is called twice to access ArrayList<Printer>.
						    if (!haveWeUpdated) // Print full printer database
						        printDocument = new PrintDocument(
						                m_SearchResult_P.getPrinterList().getPrinterList(), haveWeUpdated);
						    else   // Print filtered reslts
						        printDocument = new PrintDocument(
	                                    m_SearchResult_P.getFilteredList(), haveWeUpdated);
						    printDocument.print();
				break;
				
			default: JOptionPane.showMessageDialog(m_Menu_F,"Command: " + command,"Unknown Command", JOptionPane.PLAIN_MESSAGE);
				break;
		}
		
	}

	public BufferedImage createImage(JPanel panel) {

		int w = panel.getWidth();
		int h = panel.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		panel.paint(g);
		return bi;
	}
	
	/**
	 * Prints the page at the specified index into the specified
	 * {@link Graphics} context in the specified
	 * format.
	 *
	 * @param graphics   the context into which the page is drawn
	 * @param pageFormat the size and orientation of the page being drawn
	 * @param pageIndex  the zero based index of the page to be drawn
	 * @return PAGE_EXISTS if the page is rendered successfully
	 *         or NO_SUCH_PAGE if <code>pageIndex</code> specifies a
	 *         non-existent page.
	 * @throws PrinterException thrown when the print job is terminated.
     */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// We have only one page, and 'page'
		// is zero-based
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}

		if (haveWeUpdated) {        // If we have clicked search thus updating the panel of printer list results/
			@SuppressWarnings("unused")
			ArrayList<Printer> printingResults = new ArrayList<Printer>();        // Create a array list that will store all results of printer.
			// At this point we have updated the arraylist for printer results based on query, so we can safely call accessor
			// method for retrieval of printer list.
			printingResults = m_SearchResult_P.getPrinterList().getPrinterList();


			// User (0,0) is typically outside the
			// imageable area, so we must translate
			// by the X and Y values in the PageFormat
			// to avoid clipping.
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

			// Now we perform our rendering
			//int widthPanel = m_SearchResult_P.getWidth();
			//int heightPanel = m_SearchResult_P.getHeight();
			int x_axis = (int) pageFormat.getWidth();
			int y_axis = (int) pageFormat.getHeight();

			BufferedImage panelImage = createImage(m_SearchResult_P);
			//Image img = panelImage.getScaledInstance(792, 600,Image.SCALE_SMOOTH);

			if(macOSFound){
				pageFormat.setOrientation(PageFormat.LANDSCAPE);
			} else {
				pageFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
			}

			((Graphics2D) graphics).drawImage(panelImage,0,0, x_axis-60, y_axis-100, null);

		} else {
			@SuppressWarnings("unused")
			ArrayList<Printer> printingResults = new ArrayList<Printer>();        // Create a array list that will store all results of printer.
			// At this point we have updated the arraylist for printer results based on query, so we can safely call accessor
			// method for retrieval of printer list.
			printingResults = m_SearchResult_P.getPrinterList().getPrinterList();


			// User (0,0) is typically outside the
			// imageable area, so we must translate
			// by the X and Y values in the PageFormat
			// to avoid clipping.
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

			// Now we perform our rendering
			//int widthPanel = m_SearchResult_P.getWidth();
			//int heightPanel = m_SearchResult_P.getHeight();
			int x_axis = (int) pageFormat.getWidth();
			int y_axis = (int) pageFormat.getHeight();

			BufferedImage panelImage = createImage(m_SearchResult_P);
			//Image img = panelImage.getScaledInstance(792, 600,Image.SCALE_SMOOTH);

			if(macOSFound){
				pageFormat.setOrientation(PageFormat.LANDSCAPE);
			} else {
				pageFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
			}

			g2d.drawString("\t\t\tUnfiltered Results", 0, 10);
			((Graphics2D) graphics).drawImage(panelImage,0,30, x_axis-60, y_axis-100, null);
		}

		// tell the caller that this page is part
		// of the printed document
		return PAGE_EXISTS;
	}
}
	
	/**
	 * Removes components from specified panel.
	 * 
	 * @param panel the JPanel whose components will be removed
	 */
	public void clearInterface(JPanel panel){
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}

	/**
	 * Removes components from specified tool bar.
	 * 
	 * @param toolbar the JToolBat whose components will be removed
	 */
	public void clearInterface(JToolBar toolbar){
		toolbar.removeAll();
		toolbar.revalidate();
		toolbar.repaint();
	}
	/**
	 * Sets components to their default values.
	 */
	public void resetResults()
	{
		this.removeAll();
		createComponents();
	    designComponents();
	    addComponents();
	    
	    this.revalidate();
	    this.repaint();
	}
	//----------------------------Getters/Setters--------------------------------//
	/**
	 * Returns menu frame.
	 * 
	 * @return the menu frame
	 */
	public JFrame getMenu_F(){
		
		return  m_Menu_F;
		
	}

	/**
	 * Returns the search result panel.
	 * 
	 * @return the search result panel
	 */
	public SearchResultPanel getSearchResultsPanel()
	{
		return m_SearchResult_P;
	}

	/**
	 * @return the m_ScrollPane
	 */
	public JScrollPane getScrollPane() {
		return m_ScrollPane;
	}

	/**
	 * @param m_ScrollPane the m_ScrollPane to set
	 */
	public void setScrollPane(JScrollPane m_ScrollPane) {
		this.m_ScrollPane = m_ScrollPane;
	}

	/**
	 * @return the m_SearchParam_P
	 */
	public SearchFiltersPanel getSearchFilterPanel() {
		return m_SearchFilter_P;
	}


	/**
	 * @param m_SearchFilter_P the m_SearchParam_P to set
	 */
	public void setSearchFilterPanel(SearchFiltersPanel m_SearchFilter_P) {
		this.m_SearchFilter_P = m_SearchFilter_P;
	}
}
