package examen.ejercicio1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Juego extends JPanel implements ActionListener {

	private Lienzo lienzo;
	private static String letras = "abcdefghijklmnñopqrstuvwxyz";
	private static Font font;
	JButton cmdJugar = new JButton("jugar");
	JButton[] cmdLetra = new JButton[letras.length()];
	JLabel lblPalabra = new JLabel("palabra");
	static String palabra;
	static String p;
	static String p2 = " ";
	JLabel jl1;
	static JButton boton = new JButton("ok");
	JTextArea textArea;
	JScrollPane scroll;
	JPanel sup;
	static int fallos; 
	static int aciertos; 
	static char[] letrass; 
	static char[] letrasGuiones; 

	Juego(Lienzo lienzo) throws FontFormatException, IOException {

		jl1 = new JLabel();
		this.lienzo = lienzo;
		InputStream in = getClass().getResourceAsStream("/font.ttf");
		font = Font.createFont(Font.PLAIN, in).deriveFont(30f);
		in.close();
		setLayout(new BorderLayout());
		JPanel sup = new JPanel(new GridLayout(1, 1));
		lblPalabra = new JLabel("PALABRA");
		lblPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30),
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
						BorderFactory.createEmptyBorder(20, 20, 20, 20))));
		lblPalabra.setHorizontalAlignment(JLabel.CENTER);
		lblPalabra.setFont(font);
		sup.add(lblPalabra);

		JPanel inf = new JPanel(new GridLayout(4, 7));
		inf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30),
				BorderFactory.createBevelBorder(BevelBorder.RAISED)));

		for (int i = 0; i < letras.length(); i++) {
			cmdLetra[i] = new JButton(letras.substring(i, i + 1));
			cmdLetra[i].setFont(font);
			inf.add(cmdLetra[i]);
			cmdLetra[i].addActionListener(this);
			cmdLetra[i].setEnabled(false);
		}

		inf.add(cmdJugar);
		cmdJugar.addActionListener(this);

		boton.addActionListener(this);
		add(sup, BorderLayout.CENTER);
		add(inf, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Object boton=e.getSource();

		String ac = e.getActionCommand();

		if (ac.equals("jugar")) {
			palabra = leerFichero.leerFichero();
			letrass = palabra.toCharArray();
			letrasGuiones = new char[letrass.length];
			p=" ";
			fallos=0;
			aciertos=0;
			for (JButton b : cmdLetra)
				b.setEnabled(true);
			cmdJugar.setBackground(Color.GREEN);
			for (int i = 0; i < letrass.length; i++) {
				letrasGuiones[i] = '_';
				p += letrasGuiones[i] = '_';
				System.out.println(letrasGuiones);

				lblPalabra.setText(p);
                
			}

		} else {

			char c = ac.charAt(0);
			boolean fallo = true;
			for (int i = 0; i < letrass.length; i++) {
				if (c == letrass[i]) {
					letrasGuiones[i] = c;
					lblPalabra.setText(String.valueOf(letrasGuiones));
					fallo = false;
				}
			}
			if (fallo)
				if (lienzo.incFallos())
				{
				  JOptionPane.showMessageDialog(lienzo, "HA PERDIDO!! FIN JUEGO");	
				  
				  for (JButton b : cmdLetra)
						b.setEnabled(false);
					cmdJugar.setBackground(Color.GRAY);
					
					lblPalabra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30),
							BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
									BorderFactory.createEmptyBorder(20, 20, 20, 20))));
					lblPalabra.setHorizontalAlignment(JLabel.CENTER);
					lblPalabra.setFont(font);
					lblPalabra.setText("PALABRA");
					
				  lienzo.reset();
				}

			
				
		}

	}
}
