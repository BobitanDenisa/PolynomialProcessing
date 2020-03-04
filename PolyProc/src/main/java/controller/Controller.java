package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Polynomial;
import view.UserView;

public class Controller {

	private UserView ui;

	public Controller(UserView ui) {
		this.ui = ui;

		ui.addAdditionListener(new Addition());
		ui.addSubtractionListener(new Subtraction());
		ui.addMultiplicationListener(new Multiplication());
		ui.addDivisionListener(new Division());
		ui.addDerivationListener(new Derivation());
		ui.addIntegrationListener(new Integration());

	}

	class Addition implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Polynomial p1 = new Polynomial();
			int ok1 = p1.generateMonomials(ui.getPoly1());
			if (ok1 == 0) {
				Polynomial p2 = new Polynomial();
				int ok2 = p2.generateMonomials(ui.getPoly2());
				if (ok2 == 0) {
					ui.setResult(p1.addition(p2).generateOutputString());
					return;
				}
			}
			ui.setResult("");
		}

	}

	class Subtraction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Polynomial p1 = new Polynomial();
			int ok1 = p1.generateMonomials(ui.getPoly1());
			if (ok1 == 0) {
				Polynomial p2 = new Polynomial();
				int ok2 = p2.generateMonomials(ui.getPoly2());
				if (ok2 == 0) {
					ui.setResult(p1.subtraction(p2).generateOutputString());
					return;
				}
			}
			ui.setResult("");
		}

	}

	class Multiplication implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Polynomial p1 = new Polynomial();
			int ok1 = p1.generateMonomials(ui.getPoly1());
			if (ok1 == 0) {
				Polynomial p2 = new Polynomial();
				int ok2 = p2.generateMonomials(ui.getPoly2());
				if (ok2 == 0) {
					ui.setResult(p1.multiplication(p2).generateOutputString());
					return;
				}
			}
			ui.setResult("");
		}

	}

	class Division implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Polynomial p1 = new Polynomial();
			int ok1 = p1.generateMonomials(ui.getPoly1());
			if (ok1 == 0) {
				Polynomial p2 = new Polynomial();
				if (ui.getPoly2().equals("0") || ui.getPoly2().equals("")) {
					JOptionPane.showMessageDialog(null, "Division by zero! Input must be non null!", "Division by zero", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int ok2 = p2.generateMonomials(ui.getPoly2());
				if (ok2 == 0) {
					Polynomial[] p3 = new Polynomial[2];
					p3 = p1.division(p2);
					String s0 = p3[0].generateOutputString();
					String s1 = p3[1].generateOutputString();
					if (s0.length() > 0 && s1.length() > 0) {
						ui.setResult("Quotient = " + p3[0].generateOutputString() + "   &   Remainder = "
								+ p3[1].generateOutputString());
						return;
					} else {
						ui.setResult(p3[0].generateOutputString());
						return;
					}
				}
			}
			ui.setResult("");
		}

	}

	class Derivation implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Polynomial p1 = new Polynomial();
			if (ui.getPoly1().equals("0") || ui.getPoly1().equals("")) {
				JOptionPane.showMessageDialog(null, "Input must be non null!", "Null input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int ok1 = p1.generateMonomials(ui.getPoly1());
			if (ok1 == 0) {
					ui.setResult(p1.derivation().generateOutputString());
					return;
			}
			ui.setResult("");
		}

	}

	class Integration implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Polynomial p1 = new Polynomial();
			if (ui.getPoly1().equals("0") || ui.getPoly1().equals("")) {
				JOptionPane.showMessageDialog(null, "Input must be non null!", "Null input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int ok1 = p1.generateMonomials(ui.getPoly1());
			if (ok1 == 0) {
					ui.setResult(p1.integration().generateOutputString() + "+C");
					return;
			}
			ui.setResult("");
		}
	}

}
