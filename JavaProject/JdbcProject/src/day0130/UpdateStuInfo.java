package day0130;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpdateStuInfo extends JFrame {

	Container cp;
	JTextField tfName, tfJava, tfJsp, tfSpring;
	JComboBox<String> cbBan;
	JButton btnMod;
	String num; //수정할번호

	public UpdateStuInfo(String title) {
		super(title);
		cp = this.getContentPane();
		this.setBounds(1200, 200, 200, 250);
		cp.setBackground(new Color(255, 255, 200));
		initDesign();
		//this.setVisible(true);
	}

	public void initDesign() {
		this.setLayout(null);

		JLabel lblTitle1 = new JLabel("이름");
		JLabel lblTitle2 = new JLabel("반");
		JLabel lblTitle3 = new JLabel("JAVA");
		JLabel lblTitle4 = new JLabel("JSP");
		JLabel lblTitle5 = new JLabel("SPRING");

		tfName = new JTextField();
		tfJava = new JTextField(3);
		tfJsp = new JTextField(3);
		tfSpring = new JTextField(3);

		String[] ban = { "aws반", "front반", "backend반", "design반" };
		cbBan = new JComboBox<String>(ban);
		btnMod = new JButton("수정");

		lblTitle1.setBounds(30, 10, 60, 30);
		this.add(lblTitle1);
		lblTitle2.setBounds(30, 40, 60, 30);
		this.add(lblTitle2);
		lblTitle3.setBounds(30, 70, 60, 30);
		this.add(lblTitle3);
		lblTitle4.setBounds(30, 100, 60, 30);
		this.add(lblTitle4);
		lblTitle5.setBounds(30, 130, 60, 30);
		this.add(lblTitle5);

		// 이름,반,java,jsp,spring 순으로
		tfName.setBounds(80, 15, 70, 22);
		this.add(tfName);
		cbBan.setBounds(80, 45, 70, 22);
		this.add(cbBan);
		tfJava.setBounds(80, 75, 70, 22);
		this.add(tfJava);
		tfJsp.setBounds(80, 105, 70, 22);
		this.add(tfJsp);
		tfSpring.setBounds(80, 135, 70, 22);
		this.add(tfSpring);

		btnMod.setBounds(60, 170, 70, 30);
		this.add(btnMod);

	}

	
	 //public static void main(String[] args) { new UpdateStuInfo("학생정보수정프레임"); }
	 

}
