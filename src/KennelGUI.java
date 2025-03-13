import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class KennelGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Kennel kennel;

	private DefaultListModel<String> petListModel;
	private JList<String> petList;

	private JTextField checkInNameField;
	private JComboBox<String> checkInTypeCombo;
	private JPanel dogOptionsPanel;
	private JRadioButton rdbtnStandard;
	private JRadioButton rdbtnLoud;
	private JRadioButton rdbtnBad;
	private ButtonGroup dogOptionGroup;
	private JButton btnCheckIn;

	private JTextField checkOutNameField;
	private JComboBox<String> checkOutTypeCombo;
	private JButton btnCheckOut;
	private ButtonGroup modeGroup;
	private JTextField textFieldSearch;

	/**
	 * Create the frame.
	 */
	public KennelGUI() {
		kennel = new Kennel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome to the Kennel");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWelcome.setBounds(200, 10, 200, 25);
		contentPane.add(lblWelcome);

		modeGroup = new ButtonGroup();

		// Check In Panel
		JPanel panelCheckIn = new JPanel();
		panelCheckIn.setBorder(BorderFactory.createTitledBorder("Check In"));
		panelCheckIn.setBounds(20, 46, 540, 136);
		panelCheckIn.setLayout(null);
		contentPane.add(panelCheckIn);

		JLabel lblNameIn = new JLabel("Pet Name:");
		lblNameIn.setBounds(10, 20, 70, 25);
		panelCheckIn.add(lblNameIn);

		checkInNameField = new JTextField();
		checkInNameField.setBounds(90, 20, 150, 25);
		panelCheckIn.add(checkInNameField);
		checkInNameField.setColumns(10);

		JLabel lblTypeIn = new JLabel("Pet Type:");
		lblTypeIn.setBounds(10, 50, 70, 25);
		panelCheckIn.add(lblTypeIn);

		checkInTypeCombo = new JComboBox<>(new String[] { "Cat", "Dog" });
		checkInTypeCombo.setBounds(90, 50, 150, 25);
		panelCheckIn.add(checkInTypeCombo);

		dogOptionsPanel = new JPanel();
		dogOptionsPanel.setBorder(BorderFactory.createTitledBorder("Dog Options"));
		dogOptionsPanel.setBounds(20, 80, 315, 50);
		dogOptionsPanel.setLayout(null);
		panelCheckIn.add(dogOptionsPanel);

		rdbtnLoud = new JRadioButton("Loud Dog");
		rdbtnLoud.setBounds(105, 15, 100, 25);
		dogOptionsPanel.add(rdbtnLoud);

		rdbtnBad = new JRadioButton("Bad Dog");
		rdbtnBad.setBounds(205, 15, 100, 25);
		dogOptionsPanel.add(rdbtnBad);

		dogOptionGroup = new ButtonGroup();
		dogOptionGroup.add(rdbtnLoud);
		dogOptionGroup.add(rdbtnBad);

		rdbtnStandard = new JRadioButton("Standard");
		rdbtnStandard.setBounds(5, 15, 100, 25);
		dogOptionsPanel.add(rdbtnStandard);
		rdbtnStandard.setSelected(true);
		dogOptionGroup.add(rdbtnStandard);

		btnCheckIn = new JButton("Check In");
		btnCheckIn.setBounds(363, 50, 120, 25);
		panelCheckIn.add(btnCheckIn);

		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = checkInNameField.getText().trim();
				String type = (String) checkInTypeCombo.getSelectedItem();
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a pet name.");
					return;
				}
				Pet pet = null;
				if ("Cat".equals(type)) {
					pet = new Cat(name);
				} else if ("Dog".equals(type)) {
					if (rdbtnLoud.isSelected()) {
						pet = new LoudDog(name);
					} else if (rdbtnBad.isSelected()) {
						pet = new BadDog(name);
					} else {
						pet = new Dog(name);
					}
				}
				if (pet != null) {
					kennel.addPet(pet);
					updatePetList();
					JOptionPane.showMessageDialog(null,
							"Checked in " + pet.getName() + " the " + pet.getClass().getSimpleName());
				}
			}
		});

		// Check Out Panel
		JPanel panelCheckOut = new JPanel();
		panelCheckOut.setBorder(BorderFactory.createTitledBorder("Check Out"));
		panelCheckOut.setBounds(20, 193, 358, 106);
		panelCheckOut.setLayout(null);
		contentPane.add(panelCheckOut);

		JLabel lblNameOut = new JLabel("Pet Name:");
		lblNameOut.setBounds(10, 25, 70, 25);
		panelCheckOut.add(lblNameOut);

		checkOutNameField = new JTextField();
		checkOutNameField.setBounds(70, 25, 150, 25);
		panelCheckOut.add(checkOutNameField);
		checkOutNameField.setColumns(10);

		JLabel lblTypeOut = new JLabel("Pet Type:");
		lblTypeOut.setBounds(10, 61, 70, 25);
		panelCheckOut.add(lblTypeOut);

		checkOutTypeCombo = new JComboBox<>(new String[] { "Cat", "Dog", "LoudDog", "BadDog" });
		checkOutTypeCombo.setBounds(70, 61, 150, 25);
		panelCheckOut.add(checkOutTypeCombo);

		btnCheckOut = new JButton("Check Out");
		btnCheckOut.setBounds(244, 44, 100, 25);
		panelCheckOut.add(btnCheckOut);

		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = checkOutNameField.getText().trim();
				String type = (String) checkOutTypeCombo.getSelectedItem();
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a pet name to check out.");
					return;
				}
				boolean removed = kennel.removePet(name, type);
				if (removed) {
					updatePetList();
					JOptionPane.showMessageDialog(null, "Checked out " + name + " (" + type + ")");
				} else {
					JOptionPane.showMessageDialog(null, "Pet not found or type mismatch.");
				}
			}
		});

		// JList and Scroll Pane
		petListModel = new DefaultListModel<>();
		petList = new JList<>(petListModel);
		JScrollPane scrollPane = new JScrollPane(petList);
		scrollPane.setBounds(20, 310, 540, 130);
		contentPane.add(scrollPane);

		// Control Panel for Search, Sort, Clear
		JPanel panelControls = new JPanel();
		panelControls.setBorder(new TitledBorder(null, "Controls", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelControls.setBounds(385, 193, 175, 106);
		contentPane.add(panelControls);
		panelControls.setLayout(null);

		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(98, 66, 64, 23);
		panelControls.add(btnSort);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kennel.sortByPetType();
				updatePetList();
				JOptionPane.showMessageDialog(null, "Pets have been sorted.");
			}
		});

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(10, 30, 152, 25);
		panelControls.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 16, 77, 14);
		panelControls.add(lblSearch);

		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setBounds(10, 66, 82, 23);
		panelControls.add(btnClearAll);
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kennel.removeAllPets();
				textFieldSearch.setText("");
				petList.setModel(petListModel);
				updatePetList();
				JOptionPane.showMessageDialog(null, "All pets have been cleared.");
			}
		});

		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchTerm = textFieldSearch.getText().trim().toLowerCase();

				if (searchTerm.isEmpty()) {
					petList.setModel(petListModel);
					updatePetList();
					return;
				}
				DefaultListModel<String> searchModel = new DefaultListModel<>();
				ArrayList<Pet> pets = kennel.getPets();

				for (int i = 0; i < pets.size(); i++) {
					Pet p = pets.get(i);
					if (p.getName().toLowerCase().contains(searchTerm)) {
						searchModel.addElement(
								"Kennel #" + (i + 1) + ": " + p.getName() + " - " + p.getClass().getSimpleName());
					}
				}
				petList.setModel(searchModel);
			}
		});

		checkInTypeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = (String) checkInTypeCombo.getSelectedItem();
				dogOptionsPanel.setVisible("Dog".equals(type));
			}
		});
		dogOptionsPanel.setVisible("Dog".equals(checkInTypeCombo.getSelectedItem()));

		updatePetList();
	}

	private void updatePetList() {
		petListModel.clear();
		ArrayList<Pet> pets = kennel.getPets();
		for (int i = 0; i < pets.size(); i++) {
			Pet p = pets.get(i);
			petListModel.addElement("Kennel #" + (i + 1) + ": " + p.getName() + " - " + p.getClass().getSimpleName());
		}
		petList.setModel(petListModel);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KennelGUI frame = new KennelGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
