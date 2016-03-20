/**
 * 
 * @author Joshua
 *
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class MenuUI
{
	
private JFrame m_Menu_F;
private JPanel m_SearchResult_P, m_SearchParam_P;
private JLabel m_SearchParam_L;
private JButton m_FilterResults_B;
private JTextField m_BroadSearch_TF, m_Tenstion_TF, m_Impact_TF, m_LoadTime_TF, m_PartComplexity_TF, m_EOC_TF, m_ROM_TF, m_Tolerance_TF, m_Finish_TF;
private JToolBar m_ToolBar;

public static void main(String args [])
{
	new MenuUI();
}
public MenuUI()
{
    createComponents();
    designComponents();
    addComponents();
    
    m_Menu_F.setVisible(true);
}
/**
 * 
 */
private void createComponents() {
	m_Menu_F = new JFrame("Menu");
	m_ToolBar = new JToolBar("ToolBar");
	
	m_BroadSearch_TF = new JTextField();
	m_Tenstion_TF = new JTextField();
	m_Impact_TF = new JTextField();
	m_LoadTime_TF = new JTextField();
	m_PartComplexity_TF = new JTextField();
	m_EOC_TF = new JTextField();
	m_ROM_TF = new JTextField();
	m_Tolerance_TF = new JTextField();
	m_Finish_TF = new JTextField();
	
	m_SearchResult_P = new JPanel();
	m_SearchParam_P = new JPanel();
	
	m_FilterResults_B = new JButton("Filter Results");
}
/**
 * 
 */
private void designComponents() {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();// Getting size of screen
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	
	int frameWidth = (int) ((int) screenWidth *0.75);
	int frameHeight = (int) ((int) screenHeight *0.75);
    
	m_Menu_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	m_Menu_F.setLayout(new BorderLayout(5,5));
	m_Menu_F.setSize(frameWidth, frameHeight);
	m_Menu_F.setLocation((screenWidth/2) - (frameWidth/2),(screenHeight/2) - (frameHeight/2));// centering
	m_Menu_F.setResizable(false);
	
	designSearchParam(frameWidth, frameHeight);
	
	designSearchResult(frameWidth, frameHeight);
	
	designToolBar();
}
/**
 * 
 * @param frameWidth
 * @param frameHeight
 */
private void designSearchResult(int frameWidth, int frameHeight) {
	//m_SearchResult_P.setLayout(new BoxLayout(m_SearchResult_P, BoxLayout.Y_AXIS));
	//m_SearchResult_P.setPreferredSize(new Dimension(frameWidth - 180, frameHeight));
	//m_SearchResult_P.setBorder(BorderFactory.createLineBorder(Color.gray));
	
	m_SearchResult_P.add(new PrinterUI(1,frameWidth, frameHeight,"Name","Tension","Compression","Impact","Part Complexity","Lead Time","EOC","ROM","Tolerance","Finish"));
	
}
/**
 * 
 */
public void designToolBar()
{
	JButton button = new JButton("Settings");
	button.setActionCommand("Settings");
	m_ToolBar.add(button);
	
	button = new JButton("Help");
	button.setActionCommand("Help");
	m_ToolBar.add(button);
	
	button = new JButton("Add Printer");
	button.setActionCommand("Add Printer");
	m_ToolBar.add(button);
}
/**
 * 
 */
public void designSearchParam(int frameWidth, int frameHeight)
{
	m_SearchParam_P.setLayout(new BoxLayout(m_SearchParam_P, BoxLayout.Y_AXIS));
	m_SearchParam_P.setPreferredSize(new Dimension(175, frameHeight));
	m_SearchParam_P.setBorder(BorderFactory.createLineBorder(Color.black));
	
	m_BroadSearch_TF.setMaximumSize(new Dimension(170, 25));
	m_BroadSearch_TF.setMinimumSize(new Dimension(150, 25));
	m_BroadSearch_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Tenstion_TF.setMaximumSize(new Dimension(170, 25));
	m_Tenstion_TF.setMinimumSize(new Dimension(150, 25));
	m_Tenstion_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Impact_TF.setMaximumSize(new Dimension(170, 25));
	m_Impact_TF.setMinimumSize(new Dimension(150, 25));
	m_Impact_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_LoadTime_TF.setMaximumSize(new Dimension(170, 25));
	m_LoadTime_TF.setMinimumSize(new Dimension(150, 25));
	m_LoadTime_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_PartComplexity_TF.setMaximumSize(new Dimension(170, 25));
	m_PartComplexity_TF.setMinimumSize(new Dimension(150, 25));
	m_PartComplexity_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_EOC_TF.setMaximumSize(new Dimension(170, 25));
	m_EOC_TF.setMinimumSize(new Dimension(150, 25));
	m_EOC_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_ROM_TF.setMaximumSize(new Dimension(170, 25));
	m_ROM_TF.setMinimumSize(new Dimension(150, 25));
	m_ROM_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Tolerance_TF.setMaximumSize(new Dimension(170, 25));
	m_Tolerance_TF.setMinimumSize(new Dimension(150, 25));
	m_Tolerance_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_Finish_TF.setMaximumSize(new Dimension(170, 25));
	m_Finish_TF.setMinimumSize(new Dimension(150, 25));
	m_Finish_TF.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	m_FilterResults_B.setPreferredSize(new Dimension(100,25));
	m_FilterResults_B.setAlignmentX(Component.CENTER_ALIGNMENT);
}
/**
 * 
 */
private void addComponents() {
	addSearchParamComponents();
	
	
	m_Menu_F.add(m_ToolBar, BorderLayout.PAGE_START);
	m_Menu_F.add(m_SearchParam_P, BorderLayout.LINE_START);
	m_Menu_F.add(m_SearchResult_P, BorderLayout.LINE_END);
}
/**
 * 
 */
private void addSearchParamComponents() {
	JLabel label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Search:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_BroadSearch_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Tenstion:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Tenstion_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Impact");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Impact_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Load Time:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_LoadTime_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Part Complexity:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_PartComplexity_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("EOC:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_EOC_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("ROM:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_ROM_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Tolerance:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Tolerance_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	label = new JLabel("Finish:");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_Finish_TF);
	
	label = new JLabel("\n");
	label.setAlignmentX(Component.CENTER_ALIGNMENT);
	m_SearchParam_P.add(label);
	
	m_SearchParam_P.add(m_FilterResults_B);
	
}
}