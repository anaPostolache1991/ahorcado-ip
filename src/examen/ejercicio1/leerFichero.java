package examen.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class leerFichero {

	public static String leerFichero() {
		File archivo = null;

		FileReader fr = null;

		BufferedReader br = null;

		String palabra = "";
		ArrayList<String> palabras = new ArrayList<String>();
		Map<Integer, String> mapa = new HashMap();
		try {

			archivo = new File("C:/Users/Ana/prueba/listado-general.txt");

			fr = new FileReader(archivo);

			br = new BufferedReader(fr);

			System.out.println("Leyendo el contendio del archivo.txt");
			Random r = new Random();

			String linea = " ";
			while ((linea = br.readLine()) != null) {

				palabras.add(linea);

			}

			int min = 0;
			int max = palabras.size() - 1;
			int aleatorio = (int) (Math.random() * (max - min) + min);
			palabra = palabras.get(aleatorio);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (null != fr) {

					fr.close();

				}

			} catch (Exception e2) {

				e2.printStackTrace();

			}

		}
		return palabra;
	}
}
