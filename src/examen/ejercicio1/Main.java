package examen.ejercicio1;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Main {

	public static void main(String[] args) throws FontFormatException, IOException {
		JFrame frame = new JFrame("Ejercicio 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		Lienzo lienzo = new Lienzo(500, 500);
		frame.getContentPane().add(lienzo, BorderLayout.CENTER);
		frame.getContentPane().add(new Juego(lienzo), BorderLayout.EAST);
		frame.pack();
		frame.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(() -> {frame.setVisible(true);});
		
		
	}
		

}
