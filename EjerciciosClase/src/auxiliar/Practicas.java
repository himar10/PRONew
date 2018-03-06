package auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import modelo.Datos;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Persona;
import modelo.Vehiculo;

public class Practicas {

	// SEGUNDA EVALUACION
	
	
	//PASAR METODOS CON ARRAYS A ARRAYLIST
	
	
		public void ordenaEnteros(ArrayList<Integer> numeros) {
			for (int i = 0; (i < numeros.size() - 1); i++) {
				for (int j = i + 1; j < numeros.size(); j++) {
					if (numeros.get(i) > numeros.get(j)) {
						Integer aux = numeros.get(i);
						numeros.set(i, numeros.get(j));
						numeros.set(j, aux);
					}
				}
			}

		}
/*		public void numerosFibonacci(ArrayList<Integer>numeros) {
			
			//int x = 0;
			//int y = 1;
			int z;
			numeros.add(0,0);
			numeros.add(1,1);
			for (int i = 2; i < numeros.size(); i++) {
				z = numeros.get(0) + numeros.get(1);
				numeros.get[i] = z;
				numeros.get(0) = numeros.get(1);
				numeros.get(1) = z;
			}
		}
		*/
		
		
		
		//Practica copia estudiante a objeto 27/02/2018
		
		/*public Estudiante crearEstudianteLeido(String[] campos){
			int grupo = Integer.parseInt(campos[0]);
			Estudiante estudiante = new Estudiante(grupo);
			estudiante.setNif(campos[1]);
			estudiante.setSexo(campos[3].charAt(0));
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate fechaNac = LocalDate.parse(campos[4], fmt);
			estudiante.setFecha(fechaNac);
			estudiante.setAltura(Integer.parseInt(campos[5]));
			estudiante.setMadre(null);
			estudiante.setPadre(null);
			return estudiante;
			
		}
		
		//Practica copia estudiante txt a objeto 27/02/2018
		
		public void copiaEstudiantestxtAObjetos(String ficheroEntrada, String ficheroSalida) {
			try {
				// Abrir el fichero
				FileReader fr = new FileReader(ficheroEntrada);
				BufferedReader br = new BufferedReader(fr);
				FileOutputStream fIs = new FileOutputStream(ficheroSalida);
				ObjectOutputStream fObj = new ObjectOutputStream(fIs);
				String linea;// Leer el fichero linea a linea
				while ((linea = br.readLine()) != null) {
					String[] campos = linea.split("#");
					//crar estudiante a partir del registro leido
					Estudiante estudiante = crearEstudianteLeido(campos);
					//grabar objeto estudiante en fichero
					fObj.writeObject(estudiante);
				}
				fObj.close();
				fIs.close();
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}	
		}*/
		
		
		//01/03/2018 Comunidades y provicias ficheros
		
		public String[] ficheroComunidadesArray() {
			String[] comunidades = new String[19];
			try {
				// Abrir el fichero
				FileReader fr = new FileReader("ficheros/comunidades.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea;
				int contador = 0;
				// System.out.println(LocalDate.now());
				// Leer el fichero linea a linea
				while ((linea = br.readLine()) != null) {
				
					String[] campos = linea.split("%");
						comunidades[contador] = campos[1];
						contador++;
					}
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return comunidades;
			
		}
		
		
		
		public HashMap<String, ArrayList<String>> generarDatosListadoProvincias() {
			HashMap<String, ArrayList<String>> resultado = new HashMap<String, ArrayList<String>>();
			ArrayList<String> AL = new ArrayList<String>();
			String[] comunidades = ficheroComunidadesArray();
			try {

				// Abrir el fichero
				FileReader fr = new FileReader("ficheros/provincias.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea;
				int contador = 0;
				while ((linea = br.readLine()) != null) {
						
						String[] campos = linea.split("%");
						int idComunidad = Integer.parseInt(campos[2]);
						String prov = campos[1];
						String padron = campos[3];
						
						if(resultado.get(comunidades[idComunidad - 1]) == null ) {
							resultado.put(comunidades[contador], AL);
						}
						resultado.get(comunidades[idComunidad-1]).add(campos[1]+ "#" + campos[3]);
						
						contador++;
					}

				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return resultado;
		}
		
		public void listadoProvinciasPorCA(HashMap<String, ArrayList<String>> resultado) {
			// recorrer hm de entrada creando el de salida
			Set<String> comunidades = resultado.keySet();
			for (String comunidad : comunidades) {
				ArrayList<String> listaProvincias = resultado.get(comunidad);
				int acumuladoCA = 0;
				System.out.println("Comunidad Autonoma: " + comunidad);
				for (String provincia : listaProvincias) {
					System.out.println(provincia.split("#")[0] + ", " + provincia.split("#")[1]);
					acumuladoCA+= Integer.parseInt(provincia.split("#")[1]);
				}
				System.out.println("Total Padron Comunidad Autonoma: " + comunidad + "= " + acumuladoCA);
			}
			
		}
		
		
		// copia estudiante obj a txt
		
		public void copiaEstudiantesObjATxt(String rutaObj, String rutaTxt) {
			try {
				// Abrir el fichero
				FileInputStream fIs = new FileInputStream(rutaObj);
				ObjectInputStream fObj = new ObjectInputStream(fIs);
				FileWriter fw2 = new FileWriter(rutaTxt);
				BufferedWriter fw = new BufferedWriter(fw2);
				//recorrer el fichero
				String linea;
				while(fIs.available() > 0) {
					Estudiante est = null;
					est = (Estudiante) fObj.readObject();
					//est = (Estudiante) fObj.readObject(); //hay que hacer casting de objeto estudiante (conversion)
					//hay que hacer casting de objeto estudiante (conversion)
					linea = est.getCodGrupo()+ "#" +est.getNif()+ "#" + est.getNombre() + "#" +est.getSexo()+ "#" +est.getFecha()+ "#" + est.getAltura();
					//System.out.println(linea);
					fw2.write(linea+"\n");
					
				}
				fw.close();
				fw2.close();
				fIs.close();
				fObj.close();	
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.out.println("fichero no encontrado");
			} catch (IOException e) {
				System.out.println("Error IO");
			}
		}
		
		
		
		
		//Leer Objeto y crear fichero
		
		
		
		//Practica leer fichero visitantesIsla 19/02/2018
		public void inicializaVisitantesIsla(HashMap<Integer, ArrayList<Float>> resultado) {
			ArrayList<Float> visitantesMeses;
			for (int isla = 0; isla < 7; isla++) { // recorre cada isla
				visitantesMeses = new ArrayList<Float>();
				for (int mes = 0; mes < 12; mes++) // poner a 0 cada uno de los meses
					visitantesMeses.add(0f);
				resultado.put(isla, visitantesMeses);
			}
		}

		public HashMap<Integer, ArrayList<Float>> visitantesIslaMes(String rutaFicheroVisitantes) {
			HashMap<Integer, ArrayList<Float>> resultado = new HashMap<Integer, ArrayList<Float>>();
			try {
				// Abrir el fichero
				FileReader fr = new FileReader(rutaFicheroVisitantes);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				inicializaVisitantesIsla(resultado);
				// Leer el fichero linea a linea
				while ((linea = br.readLine()) != null) {

					String[] campos = linea.split("@");
					int isla = Integer.parseInt(campos[0]);
					int mes = Integer.parseInt(campos[1]);
					float numeroVisitantes = Float.parseFloat(campos[2]);
					resultado.get(isla - 1).set(mes - 1, numeroVisitantes);

				}
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return resultado;
		}

		public void listadoIslasMeses(String rutaFicheroVisitantes) {
			ArrayList<Float> visitantesIsla;
			HashMap<Integer, ArrayList<Float>> hm = visitantesIslaMes(rutaFicheroVisitantes);

			String[] islas = { "GRAN CANARIA", "LANZAROTE", "FUERTEVENTURA", "TENERIFE", "LA PALMA", "LA GOMERA",
					"EL HIERRO" };
			String[] meses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIE",
					"OCTUBRE", "NOVIEM", "DICIEMBRE" };

			// recorrer hm
			float acumuladoMes[] = new float[12];
			Set<Integer> claves = hm.keySet();
			System.out.print("\t\t");
			for (int i = 0; i < meses.length; i++) {
				System.out.print(meses[i] + "\t");
			}
			System.out.println();
			for (Integer clave : claves) {
				// islas[clave]
				visitantesIsla = hm.get(clave);
				System.out.print(islas[clave] + "\t");
				float acumuladoIsla = 0f;
				for (int i=0;i<visitantesIsla.size();i++) {
					acumuladoIsla += visitantesIsla.get(i);
					acumuladoMes[i] += visitantesIsla.get(i);
					System.out.printf("%.0f\t", visitantesIsla.get(i) * 1000);
				}
				System.out.print("\t total visitantes " + islas[clave] + " = " + acumuladoIsla);
				System.out.println();
			}
			for (Float valor : acumuladoMes) {
				System.out.print("\t\t" + valor);
			}
		}
	
		
		
		
		//Practica repaso 5 Febrero 2018
		
		
		public static ArrayList<Vehiculo> leerFicheroVehiculoArrayList() {
			ArrayList <Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();
			try {
				
				// Abrir el fichero
				
				FileReader fr = new FileReader("ficheros/vehiculos.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea;
				// System.out.println(LocalDate.now());
				// Leer el fichero linea a linea
				while ((linea = br.readLine()) != null) {
					
					String[] campos = linea.split("%");
					DateTimeFormatter fechaMat1 = DateTimeFormatter.ofPattern("yyyyMMdd");
					
					LocalDate fechaMat2 = LocalDate.parse(campos[3], fechaMat1);
					Vehiculo v1= new Vehiculo(Integer.parseInt(campos[0]), campos[1], 
							campos[2], fechaMat2, Float.parseFloat(campos[4])); 
			
				
					
					listaVehiculo.add(v1);
					System.out.println(v1.getFechaMatricula());
				
				

				}
				
				br.close();
				fr.close();
				
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return listaVehiculo;
		}
		
		     
		
		
		
		
	//Crear fichero con Objetos de la clase vehiculo
		
	public static void grabarObjetoClaseVehiculo(String fichero) {
		Vehiculo v1 = new Vehiculo();
		ArrayList<Vehiculo> listaVehiculo2 = Practicas.leerFicheroVehiculoArrayList();
		
		try {
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(fichero));
			
			//guardar los objetos estudiantes en el fichero...
			fObj.writeObject(listaVehiculo2);
			/*fObj.writeObject(est1);
			fObj.writeObject(est2);
			fObj.writeObject(est3);*/
			fObj.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			e.printStackTrace();
		}
		System.out.println("Fin del método");
		
	
	}
		
	
		
	// Grabar Objetos en fichero
		
	public static void grabarObjetoEnFichero(String fichero) {
		Estudiante est1 = new Estudiante(1, "123G", "Paco1", 'M', null, 180, null, null);
		Estudiante est2 = new Estudiante(2, "345Z", "Paco2", 'M', null, 180, null, null);
		Estudiante est3 = new Estudiante(3, "456F", "Paco3", 'M', null, 180, null, null);
		//abrir el fichero de objetos...
		ArrayList<Estudiante> listaE = new ArrayList<Estudiante>();
		listaE.add(est1);
		listaE.add(est2);
		listaE.add(est3);
		try {
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(fichero));
			
			//guardar los objetos estudiantes en el fichero...
			fObj.writeObject(listaE);
			/*fObj.writeObject(est1);
			fObj.writeObject(est2);
			fObj.writeObject(est3);*/
			fObj.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			e.printStackTrace();
		}
		System.out.println("Fin del método");
		
	}
		//leer fichero creado del Objeto estudiante
	public static void leerFicheroObjetoEstudianteDesdeFichero(String fichero) {
		try {
			// Abrir el fichero
			FileInputStream fIs = new FileInputStream(fichero);
			ObjectInputStream fObj = new ObjectInputStream(fIs);
			
			//recorrer el fichero
			Estudiante est = null;
			ArrayList<Estudiante> listaE = null;
			while(fIs.available() > 0) {
				//est = (Estudiante) fObj.readObject(); //hay que hacer casting de objeto estudiante (conversion)
				listaE = (ArrayList<Estudiante>) fObj.readObject();
				//System.out.println(est.getNombre());
			}
			fIs.close();
			fObj.close();	
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error IO");
		}
	}
	//Meter estudiantes en un arraylist, 
	public static void grabarObjetoEnFichero3(String fichero) {
		ArrayList<Estudiante> listaE = new ArrayList<Estudiante>();
		Estudiante est1 = new Estudiante(1, "123G", "Paco1", 'M', null, 180, null, null);
		Estudiante est2 = new Estudiante(2, "345Z", "Paco2", 'M', null, 180, null, null);
		Estudiante est3 = new Estudiante(3, "456F", "Paco3", 'M', null, 180, null, null);
		listaE.add(est1);
		listaE.add(est2);
		listaE.add(est3);
		//abrir el fichero de objetos...
		try {
			
			
			ObjectOutputStream fObj = new ObjectOutputStream(new FileOutputStream(fichero));
			
			//guardar los objetos estudiantes en el fichero...
			fObj.writeObject(listaE);
			fObj.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error IO");
			e.printStackTrace();
		}
		System.out.println("Fin del método");
		
	}
		
	
	//Generar aleatorios con ficheros
		
	public void generaFicheroLanzamientoDado(int cuantos, String rutaFichero) {
		Practicas practicas = new Practicas();
		int superior = 6;
		int inferior = 1;
		FileWriter fichero = null;
        PrintWriter pw = null; 
        try
        {
            fichero = new FileWriter(rutaFichero);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < cuantos; i++) {
            	//retardo en generarlo
            	Random rnd = new Random();
				int retardo = 1 + rnd.nextInt(1000);
            	Thread.sleep(retardo);
                pw.println("Aleatorio nº: " + practicas.generaAleatorios2(cuantos, inferior, superior));
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                   fichero.close();
                } catch (Exception e2) {
                   e2.printStackTrace();
                }
        }
       
	
	}
	
	
	
	
	
	// ESCRIBIR FICHERO
		/*public void generaFicheroLanzamientosDado2(int cuantos, String rutaFichero) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero));
				for (int i = 1; i <= cuantos; i++) {
					int numero = generaAleatorios(1, 6);
					bw.write(i + "#" + numero + "\n");
				}
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	
		
		
		
		
	/*	try {
			int lanzamientoDados = practicas.generaAleatorios3(cuantos, inferior, superior);// Abrir el fichero
			Random rnd = new Random();
			FileWriter fw=new FileWriter("ficheros/lazamientoDados.txt");
			for (int i = 0; i < cuantos; i++) {
				resultado[i] = inferior + rnd.nextInt(superior - inferior + 1);
			}
			return resultado;

			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
			
		*/
		
		
		

	public ArrayList<Estudiante> introListas() {
		ArrayList<Estudiante> listaE;
		listaE = new ArrayList<Estudiante>();
		Estudiante est1 = new Estudiante(123);
		listaE.add(est1);
		listaE.add(est1);
		listaE.add(est1);
		listaE.add(est1);
		listaE.add(est1);
		int tam = listaE.size();
		Estudiante est2 = new Estudiante(321);
		listaE.add(0, est2);
		listaE.remove(listaE.size() - 1);
		// listaE.set(0, est1);
		for (Estudiante estudiante : listaE) {
			// System.out.println(estudiante.getCodGrupo());
		}
		for (int i = 0; i < listaE.size(); i++) {
			// System.out.println(listaE.get(i).getCodGrupo());
		}

		// System.out.println("fin introListas");
		return listaE;

	}

	// 11 enero 2018
	// Leer una matriz de int y devolverla como ArrayList

	public ArrayList<ArrayList<Integer>> convierteMatrizArrayLista(int[][] matriz) {

		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		for (int[] filaMatriz : matriz) {
			// crear alist
			ArrayList<Integer> filaLista = new ArrayList<Integer>();
			for (int numero : filaMatriz)
				filaLista.add(numero);
			resultado.add(filaLista);
		}
		return resultado;
	}

	// Mapas, clase HashMap

	public HashMap<String, Estudiante> introMapas() {
		// la clave representa el nif del Estudiante
		HashMap<String, Estudiante> resultado = new HashMap<String, Estudiante>();
		Estudiante est = new Estudiante(123, "435G", "Paco", 'M', null, 180, null, null);
		resultado.put(est.getNif(), est);
		Estudiante estudiante = resultado.get("435G");
		Estudiante est2 = new Estudiante(321, "435G", "Carlos", 'M', null, 180, null, null);

		resultado.put("435G", est2);
		resultado.put("123T", new Estudiante(123, "123T", "Pepe", 'M', null, 180, null, null));
		Set<String> claves = resultado.keySet();
		for (String clave : claves) {
			// System.out.println(resultado.get(clave).getNombre());
		}

		return resultado;
	}

	public void leerFicheroTexto() {
		try {
			// Abrir el fichero
			FileReader fr = new FileReader("ficheros/personas.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// System.out.println(LocalDate.now());
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {

				String[] campos = linea.split("&");
				System.out.println(linea);
				System.out.println(calculaEdad(campos[2]));

			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void leerFicheroTextoOrdenadoClave(String rutaFichero) {
		try {
			// Abrir el fichero
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);
			String codigo_leido, codigo_anterior = null;
			int contador_grupo = 0;
			int contador_total = 0;
			String linea;
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("&&");
				codigo_leido = campos[0];
				if (codigo_anterior == null) // primer registro
					codigo_anterior = codigo_leido;
				if (!codigo_leido.equals(codigo_anterior)) {
					System.out.println("Hay " + contador_grupo + " alumnos en el grupo " + codigo_anterior);
					contador_total += contador_grupo;
					contador_grupo = 0;
					codigo_anterior = codigo_leido;
				}
               contador_grupo ++;
			}
			System.out.println("Hay " + contador_grupo + " alumnos en el grupo " + codigo_anterior);//esta instruccion es porque no muestra el ultimo registro
			contador_total += contador_grupo;//cuenta el ultimo
			System.out.println("Hay " + contador_total + " alumnos en total");//muestra el total
			contador_total += contador_grupo;
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public HashMap<String, ArrayList<Float>> resumenVentasVendedor(String ficheroVentas) {
		HashMap<String, ArrayList<Float>> resultado = new HashMap<String, ArrayList<Float>>();

		try {

			// Abrir el fichero
			FileReader fr = new FileReader(ficheroVentas);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			int acumulado = 0;
			int contador = 0;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("%");
				if (resultado.get(campos[1]) == null)
					resultado.put(campos[1], new ArrayList<Float>());
				resultado.get(campos[1]).add(Float.parseFloat((campos[2])));
			}

			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	public HashMap<String, Float> resumenVentasPorVendedor(HashMap<String, ArrayList<Float>> ventas) {
		HashMap<String, Float> resultado = new HashMap<String, Float>();
		// recorrer hm de entrada creando el de salida
		Set<String> claves = ventas.keySet();
		for (String clave : claves) {
			ArrayList<Float> listaVentas = ventas.get(clave);
			float acumuladoVendedor = 0;
			for (Float importe : listaVentas)
				acumuladoVendedor += importe;
			resultado.put(clave, acumuladoVendedor);
		}
		return resultado;
	}

	public int calculaEdad(String fechaNacimiento) { // ddmmaaaa
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		// System.out.printf("Tu edad es: %s años, %s meses y %s días",
		// periodo.getYears(), periodo.getMonths(),
		// periodo.getDays());
		return periodo.getYears();
	}

	// private static String[] diasSemana = { "lunes", "martes", "miercoles",
	// "jueves", "viernes", "sábado", "domingo" };

	public boolean esPrimo(int numero) {

		for (int i = 2; i < numero; i++) {
			if (numero % i == 0)
				return false;
		}

		return true;
	}

	public int[] numerosPrimos(int cuantos) {
		int[] primos = new int[cuantos];
		int i = 0;
		int j = 1;
		while (i < cuantos) {
			if (esPrimo(j))
				primos[i++] = j;
			j++;
		}
		return primos;
	}

	public int[] numerosFibonacci(int cuantos) {
		int[] numeros = new int[cuantos];
		int x = 0;
		int y = 1;
		int z;
		numeros[0] = x;
		numeros[1] = y;
		for (int i = 2; i < cuantos; i++) {
			z = x + y;
			numeros[i] = z;
			x = y;
			y = z;
		}
		return numeros;
	}

	// LIGA: Obtener clasificación a partir de resultados
	public int[] obtenerClasificacion(String[][] goles) {
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int i = 0; i < goles.length; i++)
			for (int j = 0; j < goles[i].length; j++)
				if (goles[i][j].indexOf('-') != -1) {
					resultado = goles[i][j].split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
					}
				}
		return puntos;
	}

	public int[] obtenerClasificacion2(String[][] goles) {
		// la diferencia con el anterior es que recorre la
		// matriz por columnas
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int j = 0; j < goles[0].length; j++)
			for (int i = 0; i < goles.length; i++)
				if (goles[i][j].indexOf('-') != -1) {
					resultado = goles[i][j].split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
					}
				}
		return puntos;
	}

	public Equipo[] obtenerClasificacion3(int[][] puntosJornadas) {
		Equipo[] clasificacion = new Equipo[5];
		String[] equipos = new Datos().getEquipos();
		for (int j = 0; j < puntosJornadas[0].length; j++) {
			Equipo e = new Equipo();
			e.setNombre(equipos[j]);
			e.setPuntos(0);
			for (int i = 0; i < clasificacion.length; i++)
				e.setPuntos(e.getPuntos() + puntosJornadas[i][j]);
			clasificacion[j] = e;
		}

		return clasificacion;
	}

	public boolean validarNif(String nif) {
		char[] letrasValidas = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		if (nif.length() != 9)
			return false;
		String numeros = nif.substring(0, 8);
		// System.out.println(numeros);
		int numerosOK;
		try {
			numerosOK = Integer.parseInt(numeros);
		} catch (NumberFormatException e) {
			return false;
		}
		int resto = numerosOK % 23;
		if (letrasValidas[resto] != nif.charAt(8))
			return false;
		return true;
	}

	// ORDENACION
	public void ordenaEnteros(int[] numeros) {
		for (int i = 0; i < numeros.length - 1; i++)
			for (int j = i + 1; j < numeros.length; j++)
				if (numeros[i] > numeros[j]) {
					int aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
				}
	}

	public void ordenaClasificacion(int[] numeros, String[] equipos) {
		for (int i = 0; i < numeros.length - 1; i++)
			for (int j = i + 1; j < numeros.length; j++)
				if (numeros[i] < numeros[j]) {
					int aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
					String aux2 = equipos[i];
					equipos[i] = equipos[j];
					equipos[j] = aux2;
				}
	}
	// mezcla dos arrays ordenados

	public int[] mezclaArrays(int[] l1, int[] l2) {
		int i = 0, j = 0, k = 0;
		int[] resultado = new int[l1.length + l2.length];

		while (l1[i] != Integer.MAX_VALUE || l2[j] != Integer.MAX_VALUE) {
			if (l1[i] < l2[j])
				resultado[k] = l1[i++];
			else
				resultado[k] = l2[j++];
			k++;

			if (i == l1.length)
				l1[--i] = Integer.MAX_VALUE;

			if (j == l2.length)
				l2[--j] = Integer.MAX_VALUE;
		}
		return resultado;
	}

	public void ordenaCadenas(String[] cadenas) {
		for (int i = 0; i < cadenas.length - 1; i++)
			for (int j = i + 1; j < cadenas.length; j++)
				if (cadenas[i].compareTo(cadenas[j]) > 0) {
					String aux = cadenas[i];
					cadenas[i] = cadenas[j];
					cadenas[j] = aux;
				}

	}

	public void ordenaEstudiantes(Estudiante[] estudiantes) {
		// ejemplo de uso de la interfaz Comparable
		// debe implementarse el método compareTo

		for (int i = 0; i < estudiantes.length - 1; i++)
			for (int j = i + 1; j < estudiantes.length; j++)
				if (estudiantes[i].compareTo(estudiantes[j]) > 0) {
					Estudiante aux = estudiantes[i];
					estudiantes[i] = estudiantes[j];
					estudiantes[j] = aux;
				}
	}

	public float calculaSaldo(float saldoInicial, float[] movimientos) {
		float saldoFinal = saldoInicial;
		for (int i = 0; i < movimientos.length; i++)
			saldoFinal += movimientos[i];
		return saldoFinal;
	}

	public float calculaSaldo(float saldoInicial, ArrayList<Float> movimientos) {
		float saldoFinal = saldoInicial;
		for (Float movimiento : movimientos)
			saldoFinal += movimiento.floatValue();
		return saldoFinal;

	}

	public int[] convierteCadenasANumeros(String[] cadenas) {
		int[] resultado = new int[cadenas.length];
		for (int i = 0; i < resultado.length; i++) {

			try {

				resultado[i] = Integer.parseInt(cadenas[i]);
			} catch (NumberFormatException e) {

				resultado[i] = -1;
			}
		}
		return resultado;
	}

	public ArrayList<Integer> convierteCadenasANumeros(ArrayList<String> cadenas) {
		// int[] resultado = new int[cadenas.length];
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		// for (int i = 0; i < resultado.length; i++) {
		for (String cadena : cadenas) {
			try {

				// resultado[i] = Integer.parseInt(cadenas[i]);
				resultado.add(Integer.parseInt(cadena));
			} catch (NumberFormatException e) {

				// resultado[i] = -1;
				resultado.add(-1);
			}
		}
		return resultado;
	}

	public void muestraNumeros() {

		int x = 0;
		while (x <= 1000) {
			System.out.println("x: " + x);
			x++;
		}
	}

	public void muestraNumeros(int min, int max) {

		if (min <= max) {
			int x = min;
			while (x <= max) {
				System.out.println("x: " + x);
				x++;
			}
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void muestraNumeros2(int min, int max) {

		if (min <= max) {
			int x = min;
			do {
				System.out.println("x: " + x);
				x++;
			} while (x <= max);
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void muestraNumeros3(int min, int max) {
		int x = min;
		if (min <= max) {
			// for (int x = min; x < max; x++) {
			// for (;;) {
			while (true) {
				System.out.println("x: " + x);
				x++;
			}
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void generaAleatorios(int cuantos, int inferior, int superior) // max 30, min 10
	{

		for (int i = 0; i < cuantos; i++)
			System.out.println(inferior + (int) (Math.random() * (superior - inferior + 1)));

	}

	public int generaAleatorios2(int cuantos, int inferior, int superior) // max 30, min 10
	{
		int resultado = 0;
		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++)
			System.out.println(resultado = inferior + rnd.nextInt(superior - inferior + 1));
		return resultado;
	}

	public int[] generaAleatorios3(int cuantos, int inferior, int superior) // max 30, min 10
	{
		int[] resultado = new int[cuantos];
		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++)
			// System.out.println(inferior + rnd.nextInt(superior - inferior + 1));
			resultado[i] = inferior + rnd.nextInt(superior - inferior + 1);
		return resultado;
	}

	public int[] frecuenciaAparicion(int cuantos, int inferior, int superior) {
		int[] resultado = new int[superior - inferior + 1];
		int[] lanzamientos = this.generaAleatorios3(cuantos, inferior, superior);
		for (int i = 0; i < lanzamientos.length; i++) {
			resultado[lanzamientos[i] - 1]++;
		}
		return resultado;

	}

	public void estadisticaCadena(String cadena) {
		int contadorVocales = 0;
		int contadorMayusculas = 0;
		int contadorEspeciales = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == 'a' || cadena.charAt(i) == 'e' || cadena.charAt(i) == 'i' || cadena.charAt(i) == 'o'
					|| cadena.charAt(i) == 'u' || cadena.charAt(i) == 'A' || cadena.charAt(i) == 'E'
					|| cadena.charAt(i) == 'I' || cadena.charAt(i) == 'O' || cadena.charAt(i) == 'U')
				contadorVocales++;
			if (cadena.charAt(i) >= 'A' && cadena.charAt(i) <= 'Z')
				contadorMayusculas++;
			int caracterAscii = cadena.charAt(i);
			if ((caracterAscii >= 0 && caracterAscii <= 47) || (caracterAscii >= 58 && caracterAscii <= 64) ||

					(caracterAscii >= 91) && (caracterAscii <= 96))

				contadorEspeciales++;
		}
		// System.out.println("Hay " + contadorVocales + " vocales en " + cadena);
		System.out.println("Hay " + contadorEspeciales + " caracteres especiales en " + cadena);

	}

	public void listaDiasSemana() {
		Datos datos = new Datos();
		// String[] diasSemana = { "lunes", "martes", "miercoles", "jueves", "viernes",
		// "sábado", "domingo" };
		// for (int i = 0; i < datos.getDiasSemana().length; i++)
		for (String dia : datos.getDiasSemana())
			// System.out.println(datos.getDiasSemana()[i]);
			System.out.println(dia);
	}

	public void listaEstudiantes(Estudiante[] lista) {
		for (Estudiante estudiante : lista) {
			// if (estudiante != null)
			try {
				System.out.println(estudiante.getNombre());
			} catch (NullPointerException e) {

			}
		}
	}

	public void listaEstudiantes(ArrayList<Estudiante> lista) {
		for (Estudiante estudiante : lista) {
			// if (estudiante != null)
			try {
				System.out.println(estudiante.getNombre());
			} catch (NullPointerException e) {

			}
		}
	}

	public int visitantesIslaYear(int isla, int[][] v) {
		int acu = 0;
		for (int j = 0; j < v[0].length; j++)
			acu += v[isla][j];
		return acu;
	}

	public int visitantesMesYear(int mes, int[][] v) {
		int acu = 0;
		for (int i = 0; i < v.length; i++)
			acu += v[i][mes];
		return acu;
	}

	public void recorrerMatrizIrregularPorColumnas(int[][] matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length > JMAX)
				JMAX = matriz[i].length;
		}

		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.length; i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz[i][j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}

			}
		}
	}

	public void recorrerMatrizIrregularPorColumnas2(Integer[][] matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length > JMAX)
				JMAX = matriz[i].length;
		}

		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.length; i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz[i][j].byteValue());
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				} catch (NullPointerException e) {
					continue;
				}

			}
		}
	}



}
