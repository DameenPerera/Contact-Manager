import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

class Contact{
	private String name;
	private String phone;
	
	public Contact(String name, String phone){
		this.name = name;
		this.phone = phone;
	}
	
	public String getName(){
		return name;	
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String setList(){
		return name + " - " + phone;
	}
}

class MainFrame extends JFrame implements ActionListener{
	private DefaultListModel<String> listModel;
	private JList<String> contactList;
	
	JPanel panTop, panBottom;
	JLabel labName, labPhone;
	JTextField texName, texPhone;
	JButton butAdd, butDelete;
	
	MainFrame(){
		listModel = new DefaultListModel<>();
		contactList = new JList<>(listModel);
		
		// Set a custom cell renderer to add spaces
		contactList.setCellRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel(value);
                label.setOpaque(true);

                // Set background and foreground colors
                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }

                // Set padding (top, left, bottom, right)
                label.setBorder(new EmptyBorder(10, 10, 10, 10));

                return label;
            }
        });
	
		//Top Panel
		panTop = new JPanel(new GridLayout(2, 2, 0, 2));
		panTop.setBorder(new EmptyBorder(20, 20, 20, 20));//(top, left, bottom, right)
		
		//Labels
		labName = new JLabel("Name");
		labPhone = new JLabel("Phone Number");
		
		//TextFields
		texName = new JTextField();
		Border borDefault = texName.getBorder();
		Border borMargin = new EmptyBorder(2, 2, 2, 2);//(top, left, bottom, right)
		texName.setBorder(new CompoundBorder(borDefault, borMargin));
		texPhone = new JTextField();
		borDefault = texPhone.getBorder();
		texPhone.setBorder(new CompoundBorder(borDefault, borMargin));
		
		panTop.add(labName);
		panTop.add(texName);
		panTop.add(labPhone);
		panTop.add(texPhone);
		
		//Add and Delete buttons
		butAdd = new JButton("Add New");
		butDelete = new JButton("Delete Record");
		
		butAdd.addActionListener(this);
		butDelete.addActionListener(this);
		
		//Bottom Panel
		panBottom = new JPanel(new FlowLayout());
		panBottom.add(butAdd);
		panBottom.add(butDelete);
		
		this.add(panTop, BorderLayout.NORTH);
		this.add(new JScrollPane(contactList), BorderLayout.CENTER);
		this.add(panBottom, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == butAdd){
			if (texName.getText().equals("") && texPhone.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Name and Phone can not be Empty", "Contact Manager", JOptionPane.WARNING_MESSAGE);
			else if (texName.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Name can not be Empty", "Contact Manager", JOptionPane.WARNING_MESSAGE);
			else if (texPhone.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Phone can not be Empty", "Contact Manager", JOptionPane.WARNING_MESSAGE);
			else{
				Contact newContact = new Contact(texName.getText(), texPhone.getText());
				listModel.addElement(newContact.setList());
				texName.setText("");
				texPhone.setText("");
			}
		}
		if (e.getSource() == butDelete){
			if (contactList.getSelectedIndex() == -1)
				JOptionPane.showMessageDialog(null, "Please, Select the contact which want to delete", "Contact Manager", JOptionPane.WARNING_MESSAGE);
			else{
				listModel.remove(contactList.getSelectedIndex());
			}
		}
	}
}

class ContactManager{
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		frame.setTitle("Contact Manager");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(64, 64, 64));
	}
}