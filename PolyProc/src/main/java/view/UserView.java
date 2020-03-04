package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserView extends JFrame {

	private JLabel text0; // label to display some text

	private JLabel text1; // label to display some text
	private JTextField poly1;

	private JLabel text2; // label to display some text
	private JTextField poly2;

	private JLabel text3; // label to display some text
	private JButton addition;
	private JButton subtraction;
	private JButton multiplication;
	private JButton division;
	private JButton derivation;
	private JButton integration;

	private JLabel text4; // label to display some text
	private JTextField result;

	public UserView() {

		this.setTitle("Polynomial Operations");

		JPanel pan = new JPanel();
		Dimension d = new Dimension(650, 500);
		pan.setPreferredSize(d);
		pan.setLayout(null);

		this.text0 = new JLabel("Enter 2 polynomials to do operations with", JLabel.CENTER);
		text0.setBounds(0, 0, d.width, 30);
		text0.setOpaque(true);
		text0.setBackground(Color.gray);

		this.text1 = new JLabel("First polynome:");
		text1.setBounds(pan.getX() + 50, text0.getHeight() + 50, 200, 20);

		this.poly1 = new JTextField();
		poly1.setBounds(text1.getX() + text1.getWidth() + 10, text0.getHeight() + 50, 200, 20);

		this.text2 = new JLabel("Second polynome:");
		text2.setBounds(pan.getX() + 50, text1.getY() + 50, 200, 20);

		this.poly2 = new JTextField();
		poly2.setBounds(text2.getX() + text2.getWidth() + 10, text1.getY() + 50, 200, 20);

		this.text3 = new JLabel("Choose an operation to perform", JLabel.CENTER);
		text3.setBounds(0, text2.getY() + text2.getHeight() + 50, d.width, 30);
		text3.setOpaque(true);
		text3.setBackground(Color.gray);

		this.addition = new JButton("+");
		addition.setBounds(pan.getX() + 50, text3.getY() + text3.getHeight() + 50, 50, 50);

		this.subtraction = new JButton("-");
		subtraction.setBounds(addition.getX() + addition.getWidth() + 50, text3.getY() + text3.getHeight() + 50, 50,
				50);

		this.multiplication = new JButton("*");
		multiplication.setBounds(subtraction.getX() + subtraction.getWidth() + 50,
				text3.getY() + text3.getHeight() + 50, 50, 50);

		this.division = new JButton("/");
		division.setBounds(multiplication.getX() + multiplication.getWidth() + 50,
				text3.getY() + text3.getHeight() + 50, 50, 50);

		this.derivation = new JButton("'");
		derivation.setBounds(division.getX() + division.getWidth() + 50, text3.getY() + text3.getHeight() + 50, 50, 50);

		this.integration = new JButton("S");
		integration.setBounds(derivation.getX() + derivation.getWidth() + 50, text3.getY() + text3.getHeight() + 50, 50,
				50);

		this.text4 = new JLabel("Result", JLabel.CENTER);
		text4.setBounds(0, addition.getY() + addition.getHeight() + 50, d.width, 30);
		text4.setOpaque(true);
		text4.setBackground(Color.gray);

		this.result = new JTextField();
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setBackground(pan.getBackground());
		result.setBounds(0, text4.getY() + text4.getHeight(), d.width, d.height - text4.getY() - text4.getHeight());

		pan.add(text0);
		pan.add(text1);
		pan.add(poly1);
		pan.add(text2);
		pan.add(poly2);
		pan.add(text3);
		pan.add(addition);
		pan.add(subtraction);
		pan.add(multiplication);
		pan.add(division);
		pan.add(derivation);
		pan.add(integration);
		pan.add(text4);
		pan.add(result);

		this.setResizable(false);
		this.add(pan);

		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public String getPoly1() {
		return poly1.getText();
	}

	public void setPoly1(String poly1) {
		this.poly1.setText(poly1);
	}

	public String getPoly2() {
		return poly2.getText();
	}

	public void setPoly2(String poly2) {
		this.poly2.setText(poly2);
	}

	public String getResult() {
		return result.getText();
	}

	public void setResult(String result) {
		this.result.setText(result);
	}

	public void addAdditionListener(ActionListener a) {
		addition.addActionListener(a);
	}

	public void addSubtractionListener(ActionListener a) {
		subtraction.addActionListener(a);
	}

	public void addMultiplicationListener(ActionListener a) {
		multiplication.addActionListener(a);
	}

	public void addDivisionListener(ActionListener a) {
		division.addActionListener(a);
	}

	public void addDerivationListener(ActionListener a) {
		derivation.addActionListener(a);
	}

	public void addIntegrationListener(ActionListener a) {
		integration.addActionListener(a);
	}

}
