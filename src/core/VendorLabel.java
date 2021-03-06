package core;
import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * A JLabel containing vendor information.
 * 
 * @author Joshua Becker
 *
 */
@SuppressWarnings("serial")
public class VendorLabel extends JLabel implements Serializable
{
	private JLabel printerInfo, name, webSite, otherPrinters;
	private JPanel header_P, body_P;
	private String [] vendorInfo;
    

	/**
	 * Creates a vendor label with the specified vendor name.
	 * 
	 * @param vendorName  the String containing the vendor's name
	 */
    VendorLabel(String vendorName)
    {
    	vendorInfo = ToolBox.getVendorInfo(vendorName);
    	if(vendorInfo.length == 0)
    	{
    		createComponents(vendorName, "No Information","No Information", "No Information");
    	}else
    	{
    		String printerList = "Printer List: ";
    		for(int i = 2; i < vendorInfo.length; i++)
    		{
    			if(i == 2)
    			{
    				printerList += vendorInfo[i];
    			}else
    			{
    				printerList += ", " + vendorInfo[i];
    			}	
    		}
    		createComponents(vendorName, vendorInfo[0],vendorInfo[1], printerList);
    	}
    	
        designComponents();
        addComponents();
    }
    
    /**
     * Instantiates vendor label components.
     * 
     * @param vendorName   the String with the vendor's name
     * @param webSite      the String with the vndor's website
     * @param vendorInfo   the String with additional vendor information
     * @param printerList  the String with printers associated with the vendor
     */
    private void createComponents(String vendorName, String webSite, String vendorInfo, String printerList)
    {
    	String tag = "<html><h2>  ", endTag = "  </h2></html>";
    	this.printerInfo = new JLabel(tag + vendorInfo + endTag, JLabel.CENTER);
        this.name = new JLabel(tag+vendorName+ endTag, JLabel.CENTER);
        this.webSite = new JLabel(tag + webSite+ endTag, JLabel.CENTER);
        this.otherPrinters = new JLabel(tag + printerList + endTag, JLabel.CENTER);
        this.body_P = new JPanel();
        this.header_P = new JPanel();
    }

    /**
     * Sets default component dimensions, alignment, and colors.
     */
    private void designComponents()
    {
    	int height = VendorInfoFrame.FRAME_HEIGHT - 10;
    	int width = VendorInfoFrame.FRAME_WIDTH - 10;
    	
    	setPreferredSize(new Dimension(width,height));
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        
        this.printerInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
    	this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.webSite.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.otherPrinters.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.printerInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    	this.name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.webSite.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.otherPrinters.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        this.printerInfo.setBackground(Color.GRAY);
        this.name.setBackground(Color.GRAY);
        this.webSite.setBackground(Color.GRAY);
        this.otherPrinters.setBackground(Color.GRAY);
        
        this.printerInfo.setPreferredSize(new Dimension((int) (width*.98),(int)(height*.53)));
    	this.name.setPreferredSize(new Dimension((int) (width*.36),(int)(height*.18)));
        this.webSite.setPreferredSize(new Dimension((int) (width*.61),(int)(height*.18)));
        this.otherPrinters.setPreferredSize(new Dimension((int) width-15,(int)(height*.18)));
        
        this.printerInfo.setMinimumSize(new Dimension((int) (width),(int)(height*.53)));
    	this.name.setMinimumSize(new Dimension((int) (width*.30),(int)(height*.05)));
        this.webSite.setMinimumSize(new Dimension((int) (width*.30),(int)(height*.05)));
        this.otherPrinters.setMinimumSize(new Dimension((int) width-10,(int)(height*.05)));
        
        this.otherPrinters.setForeground(Color.WHITE);
    	this.name.setForeground(Color.WHITE);
        this.webSite.setForeground(Color.WHITE);
        this.otherPrinters.setForeground(Color.WHITE);
        this.printerInfo.setForeground(Color.WHITE);
        
        //this.header.setLayout(new FlowLayout());
        this.header_P.setPreferredSize(new Dimension(width-5,(int) ( height*.40)));   
        this.body_P.setPreferredSize(new Dimension(width-5, (int) (height*.55)));
        this.body_P.setMaximumSize(new Dimension(width-5, (int) (height*.60)));
        this.header_P.setMaximumSize(new Dimension(width-5, (int) (height*.45)));
        
        this.header_P.setAlignmentX(CENTER_ALIGNMENT);
        this.body_P.setAlignmentX(CENTER_ALIGNMENT);
        
        this.header_P.setBackground(Color.GRAY);
        this.body_P.setBackground(Color.GRAY);
    }
    
    /**
     * add components to JLabel
     */
    private void addComponents()
    {
    	this.header_P.add(name);
    	this.header_P.add(new JLabel("\n"));
    	this.header_P.add(webSite);
    	this.header_P.add(new JLabel("\n"));
    	this.header_P.add(otherPrinters);
    	this.header_P.add(new JLabel("\n"));
    	this.body_P.add(printerInfo);
    	add(header_P);
    	add(body_P);
    }
    
    /**
     * Returns the JLabel with the vendor's name.
     * 
     * @return the JLabel containing the vendor's name
     */
    public JLabel getPrinterName() {
    	return name;
    }
    
    /**
     * Returns the JLabel with the vendor's website.
     * 
     * @return the JLabel containing the vendor's website
     */
    public JLabel getwebSite() {
    	return webSite;
    }
    
    /**
     * Returns the JLabel with the vendor's associated printers.
     * 
     * @return the JLabel containing the vendor's associated printers
     */
    public JLabel getotherPrinters() {
    	return otherPrinters;
    }
    
}