package practice;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DoraLaCalculadora {

	public static void main(String[] args) {
		MarcoCalculadora calculadora = new MarcoCalculadora();
		calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MarcoCalculadora extends JFrame {
	public MarcoCalculadora() {
		setVisible(true);
		setTitle("Calculadora");
		
		Calculadora calculadora = new Calculadora();
		add(calculadora);
		pack();
	}
}

class Calculadora extends JPanel {
	
	private JPanel milamina2;
	private JButton pantalla;
	private boolean principio;
	private double resultado;
	private String ultimaOperacion;
	
	public Calculadora(){
		principio = true;
		setLayout(new BorderLayout());
		pantalla = new JButton("0");
		pantalla.setEnabled(false);
		add(pantalla, BorderLayout.NORTH);
		
		milamina2 = new JPanel();
		milamina2.setLayout(new GridLayout(4,4));
		
		ActionListener insertar = new InsertaNumero();
		ActionListener operaciones = new AccionOrden();

		crearBotones("0", insertar);
		crearBotones("1", insertar);
		crearBotones("2", insertar);
		crearBotones("3", insertar);
		crearBotones("4", insertar);
		crearBotones("5", insertar);
		crearBotones("6", insertar);
		crearBotones("7", insertar);
		crearBotones("8", insertar);
		crearBotones("9", insertar);
		crearBotones(".", insertar);
		
		crearBotones("=", operaciones);
		crearBotones("+", operaciones);
		crearBotones("-", operaciones);
		crearBotones("*", operaciones);
		crearBotones("/", operaciones);
		
		add(milamina2, BorderLayout.CENTER);
		ultimaOperacion="=";
	}
	
	private void crearBotones(String rotulo, ActionListener oyente) {
		JButton boton = new JButton(rotulo);
		boton.addActionListener(oyente);
		milamina2.add(boton);
	}
	
	private class InsertaNumero implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String entrada = e.getActionCommand();
			if (principio) {
				pantalla.setText("");
				principio = false;
			}
			pantalla.setText(pantalla.getText() + entrada);
		}		
	}
	
	private class AccionOrden implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String operacion = e.getActionCommand();
			calcular(Double.parseDouble(pantalla.getText()));	
			ultimaOperacion = operacion;
			principio=true;
		}
		
		public void calcular(double x) {
			if (ultimaOperacion.equals("+")) {
				resultado += x;
				System.out.println(" + " + resultado);
				
			} else if (ultimaOperacion.equals("-")) {
				resultado -= x;
				System.out.println(" - " + resultado);
			
			} else if (ultimaOperacion.equals("*")) {
				resultado *= x;
				System.out.println(" * " + resultado);
			
			} else if (ultimaOperacion.equals("/")) {
				resultado -= x;
				System.out.println(" / " + resultado);
				
			} else if (ultimaOperacion.equals("=")){
				resultado=x;
				System.out.println(" = " + resultado);
			}	
			pantalla.setText(""+resultado);
		}
	}
}
