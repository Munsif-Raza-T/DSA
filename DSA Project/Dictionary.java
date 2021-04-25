import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Dictionary {

	private JFrame frame;
	private JTextField textFieldWord;
	private JTextField tfWord;
	private JTextField tfDefination;
	private  File file;
    private Scanner reader;
    private FileWriter writer;
    private  Hashtable dictionary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dictionary window = new Dictionary();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Dictionary() throws IOException {
		initialize();
		file = new File("C:\\Users\\Munsif Raza\\eclipse-workspace\\JavaInEclipse\\src\\dictionary.txt");
        reader = new Scanner(file);
        writer = new FileWriter(file,true);
        dictionary  = new Hashtable(100);
        readDictionary();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Dictionary");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome in the Munsif's Dictionary");
		lblWelcome.setBackground(Color.CYAN);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		lblWelcome.setBounds(0, 0, 484, 77);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnSearchWord = new JButton("Search Word");
		btnSearchWord.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnSearchWord.setBounds(30, 132, 161, 36);
		frame.getContentPane().add(btnSearchWord);
		
		JButton btnAddWord = new JButton("Add Word");
		btnAddWord.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnAddWord.setBounds(288, 132, 161, 36);
		frame.getContentPane().add(btnAddWord);
		
		JPanel panelAddWord = new JPanel();
		panelAddWord.setBounds(10, 184, 464, 266);
		frame.getContentPane().add(panelAddWord);
		panelAddWord.setLayout(null);
		panelAddWord.setVisible(false);
		
		tfWord = new JTextField();
		tfWord.setToolTipText("Write Word");
		tfWord.setBounds(115, 46, 339, 34);
		panelAddWord.add(tfWord);
		tfWord.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Word:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 56, 68, 24);
		panelAddWord.add(lblNewLabel);
		
		tfDefination = new JTextField();
		tfDefination.setBounds(115, 113, 339, 79);
		panelAddWord.add(tfDefination);
		tfDefination.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Defination:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 141, 90, 14);
		panelAddWord.add(lblNewLabel_1);
		
		JButton btnAddWord_Update = new JButton("Add");
		btnAddWord_Update.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnAddWord_Update.setBounds(232, 214, 143, 41);
		panelAddWord.add(btnAddWord_Update);
		
		
		JPanel panelSearchWord = new JPanel();
		panelSearchWord.setBounds(10, 195, 464, 255);
		frame.getContentPane().add(panelSearchWord);
		panelSearchWord.setLayout(null);
		panelSearchWord.setVisible(false);
		
		textFieldWord = new JTextField();
		textFieldWord.setBounds(59, 11, 331, 37);
		panelSearchWord.add(textFieldWord);
		textFieldWord.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnSearch.setBounds(165, 59, 126, 37);
		panelSearchWord.add(btnSearch);
		
		JLabel lblDefination = new JLabel("");
		lblDefination.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblDefination.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefination.setBounds(10, 123, 444, 132);
		panelSearchWord.add(lblDefination);
		
		btnSearchWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddWord.setVisible(false);
				panelSearchWord.setVisible(true);
			}
		});
		
		btnAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSearchWord.setVisible(false);
				panelAddWord.setVisible(true);
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDefination.setText((String) searchWord(textFieldWord.getText()));
			}
		});
		
		btnAddWord_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					writeDictionary((String)tfWord.getText(),(String)tfDefination.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tfWord.setText("");
				tfDefination.setText("");
				JOptionPane.showMessageDialog(frame, "Word Added");
			}
		});
	}
	
	private  void readDictionary(){
        Object[] temp = new Object[2];
        int index = 0;
        while (reader.hasNextLine()){
            temp = reader.nextLine().split("%");
            if(temp[0] != null)
            dictionary.put(temp[0].toString().toLowerCase(),temp[1]);
        }
    }
	
	public Object searchWord(Object word){
        if(dictionary.get(word.toString().toLowerCase()) == null)
            return "Word doesn't Exists!";
        else
            return dictionary.get(word.toString().toLowerCase());
    }
	
	public  void writeDictionary(Object word, Object def) throws IOException {
        writer.append(System.lineSeparator()+word+"%"+def);
        dictionary.put(word, def);
        writer.close();
    }
}
