
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Window extends JFrame {

	private static final long serialVersionUID = 1894037743641945270L;
	static JTextField cattegory, maxprice;
	static JButton button1, button2, boy, girl, ds, find;
	static JLabel spacer;
	static JComboBox comboBoxSynonyms;
	static JList list;
	static String cattegory_string, max_price, gender_type;
	static PobieranieZInternetu downloadFromInternet = new PobieranieZInternetu();

	Window() {

		JFrame frame = new JFrame("Prezentowy Bot o zdumiewającym imieniu Henryk");
		frame.getContentPane().setLayout(new FlowLayout());
		cattegory = new JTextField(45);
		maxprice = new JTextField(45);

		JLabel label = new JLabel("Give cattegory  ");
		JLabel mprice = new JLabel("Maximum price");
		JLabel gender = new JLabel("Select gender");
		JLabel spac = new JLabel("                                                                                ");

		boy = new JButton("Male");
		girl = new JButton("Female");
		ds = new JButton("Doesn't matter");
		find = new JButton("Find");

		boy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender_type = "men";
			}
		});

		girl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender_type = "women";
			}
		});

		ds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender_type = "";
			}
		});

		cattegory.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				cattegory_string = cattegory.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				cattegory_string = cattegory.getText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				cattegory_string = cattegory.getText();
			}
		});

		maxprice.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				max_price = maxprice.getText();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				max_price = maxprice.getText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				max_price = maxprice.getText();
			}
		});

		find.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> synonims = downloadFromInternet.getSynonimsList(cattegory_string);
					synonims.add(cattegory_string);
					comboBoxSynonyms = new JComboBox(synonims.toArray());
					frame.add(spacer = new JLabel("                    "));
					frame.add(comboBoxSynonyms);
					List<String> query = new LinkedList<String>();
					query.add((String) comboBoxSynonyms.getSelectedItem());
					query.add(max_price);
					query.add("$");
					list = new JList(downloadFromInternet.getItemsList(query));
					frame.add(list);
					frame.setVisible(true);
				} catch (IOException ex) {
					Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		frame.add(label);
		frame.getContentPane().add(cattegory);
		frame.add(mprice);
		frame.add(maxprice);
		frame.add(gender);
		frame.add(boy);
		frame.add(girl);
		frame.add(ds);
		frame.add(spac);
		frame.add(find);
		/*
		 * 
		 * ans1 = new JTextField(45); button2 = new JButton("Choose"); ans2 =
		 * new JTextField(45); button3 = new JButton("Choose"); ans3 = new
		 * JTextField(45); button4 = new JButton("Choose"); ans4 = new
		 * JTextField(45); frame.add(button1); frame.add(ans1);
		 * frame.add(button2); frame.add(ans2); frame.add(button3);
		 * frame.add(ans3); frame.add(button4); frame.add(ans4);
		 */
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String args[]) {
		new Window();

	}
}
