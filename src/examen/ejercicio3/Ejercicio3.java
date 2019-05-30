package examen.ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Ejercicio3 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		boolean fin = false;
		int cont = 0;
		String ip =" ";
		String usuario = null;
		
		Map<String,Map<String,Integer>> usuarios = new TreeMap<>();

		do {
			System.out.print(">");

			Scanner sc = new Scanner(bf.readLine());
			
			String linea;
			int estado = 0;
			while (estado != 5) {

				switch (estado) {
				case 0:
					try {
						linea = sc.skip("fin|\\s*IP\\s*=\\(").match().group();
						if (linea.equals("fin")) {
							estado = 5;
							fin = true;
						} else {
							estado = 1;
						}
					} catch (NoSuchElementException e) {
						System.out.println("fin o IP=(");
						estado = 5;
					}
					break;
				case 1:
					try {
						ip = sc.skip(
								"(?:(?:\\d{1,2}|1\\d{2}|2[0,4]\\d|25[0-5]).){3}(?:\\d{1,2}|1\\d{2}|2[0-4]\\d|25[0-5])")
								.match().group();
						estado = 2;

					} catch (NoSuchElementException e) {
						System.out.println("se esperava una ip");
						estado = 5;
					}
					break;
				case 2:
					try {
						linea = sc.skip("\\)\\s*mensaje\\s*=\\s*\\(.*\\)\\s*usuario\\s*=\\s*\\(").match().group();
                      
						estado = 3;
					} catch (NoSuchElementException e) {
						System.out.println("se esperava una mensaje=  usuario=");
						estado = 5;
						
					}

					break;
				case 3:
					try {
						usuario = sc.skip("\\p{L}+").match().group();
						estado=4;
					} catch (NoSuchElementException e) {
						System.out.println("se esperava nombre usuario=");
						estado = 5;
						// fin=true;
					}
					break;
				case 4:
					try {
						linea = sc.skip("\\)").match().group();
						estado = 5;
					
					} catch (NoSuchElementException e) {
						System.out.println("se esperava )");
						estado = 5;
						
					}

					break;
				}
			}
			
			if(!usuarios.containsKey(usuario)) {
				
				Map<String,Integer> ipsUsuario = new TreeMap<>();
				ipsUsuario.put(ip,1);
			    usuarios.put(usuario, ipsUsuario);
              
               
		}else {
			
			Map<String,Integer> ipsUsuario=usuarios.get(usuario);
			
			if(!ipsUsuario.containsKey(ip)) {
				ipsUsuario.put(ip,1);
			    
			}else {
				
				ipsUsuario.put(ip,ipsUsuario.get(ip)+1);
			    
			}
			
			
		}
			
		} while (fin != true);
         
		
		Set conjuntoUsuarios = usuarios.keySet();
		Iterator it = conjuntoUsuarios.iterator();
		while (it.hasNext()) {
			String user =(String) it.next();
			 System.out.println("Nombre de usuario =>: " + user );
			Map<String,Integer> ipsUsuario=usuarios.get(user);
			Set conjuntoIPs = ipsUsuario.keySet();
			Iterator itIps =conjuntoIPs.iterator();
			
			int totalMensajes=0;
			while (itIps.hasNext()) {
				String ip_clave=(String)itIps.next();
				System.out.print(ip_clave+"=> "+ipsUsuario.get(ip_clave)+", ");
				totalMensajes++;
			}
			System.out.println();
			System.out.println("Número de IPs :"+ipsUsuario.size());
			System.out.println("Total de mensajes: "+totalMensajes);
		}
		
		//IP=(1.2.3.4) mensaje=(hola) usuario=(ana)
	}
}
