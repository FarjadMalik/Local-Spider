import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LocalSpiderGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTypeYourFile;
	private Crawler mCrawler;
	private JTextArea textArea;
	private JPanel panel;
	private boolean mCheck;
	

	/*/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		
	}*/

	/**
	 * Create the frame.
	 */
	public LocalSpiderGUI(Crawler crawler) {
		mCheck = false;
		mCrawler = crawler;
		setTitle("Local Spider");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTypeYourFile = new JTextField();
		txtTypeYourFile.setText("type your directory/file name here");
		txtTypeYourFile.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		txtTypeYourFile.setBounds(10, 25, 187, 20);
		contentPane.add(txtTypeYourFile);
		txtTypeYourFile.setColumns(10);
		
		JButton btnSearch = new JButton(" Start Crawler Here");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mCheck){
					searchCalled(txtTypeYourFile.getText());
				}else{
					startCrawler(txtTypeYourFile.getText());
					mCheck=true;
					btnSearch.setText("Search");
				}
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSearch.setBounds(227, 24, 175, 23);
		contentPane.add(btnSearch);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Files Found", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 56, 414, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 21, 394, 151);
		panel.add(textArea);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setWrapStyleWord(true);
		textArea.setEnabled(false);
		textArea.setEditable(false);
		
		
	}
	protected void startCrawler(String dir) {
		System.out.println("Crawling...");
		Tester.CrawlStarted(dir);		
		System.out.println("Crawl Completed");
		
		
	}
	protected void searchCalled(String filename) {
		String allFiles="";
		System.out.println("Searching your file..");
		String filepaths = mCrawler.SearchFile(filename);
		
		if(filepaths.compareTo("File not found.")!=0){
			filepaths=filepaths.substring(1, filepaths.length()-1);
			String[] files=filepaths.split(",");
		
			for(String i:files){
				i=i.trim();
				allFiles+=i+"\n";
			}
		}
		else{
			allFiles="File not Found";			
		}
		
		textArea.setText(allFiles);
	}
	
	
}
