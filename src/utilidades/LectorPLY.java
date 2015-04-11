/**
 * @class	anibal.ingenieroenfurecido.utilidades.LectorPLY
 * @brief	Lee un fichero ply.
 * @note	Solo admite ficheros ply sin información de color, sin normales y sin coordenadas de textura.
 * @author	Aníbal García García
 * @author	Carlos Ureña Almagro
 * @date	02/04/2014
 * @note	Tutoriales:
				http://en.wikipedia.org/wiki/PLY_%28file_format%29
				http://paulbourke.net/dataformats/ply/
				http://www.mathworks.com/matlabcentral/fx_files/5459/1/content/ply.htm
 * @note	Modelos ply en:
				http://people.sc.fsu.edu/~jburkardt/data/ply/ply.html
				https://graphics.stanford.edu/data/3Dscanrep/
				http://graphics.im.ntu.edu.tw/~robin/courses/cg03/model/
 * @note	Copyright 2014 Aníbal García García <anibal_garcia_garcia@outlook.com>
 * @note	This program is free software: you can redistribute it and/or modify
			it under the terms of the GNU General Public License as published by
			the Free Software Foundation, either version 3 of the License, or
			(at your option) any later version.
 * @note	This program is distributed in the hope that it will be useful,
			but WITHOUT ANY WARRANTY; without even the implied warranty of
			MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
			GNU General Public License for more details.
 * @note	You should have received a copy of the GNU General Public License
			along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package utilidades;


import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LectorPLY {

	/// Etiqueta con el nombre de la clase.
	private static final String TAG = "LectorPLY";

	/// Número de vértices del modelo del fichero ply.
	private static int numVertices;

	/// Número de caras del modelo del fichero ply.
	private static int numCaras;

	/// Comentario del modelo del fichero ply.
	private static String comentario;

	/// Extensión del fichero ply.
	public static final String extensionPLY = ".ply";

	/// Coordenadas por vértice.
	private static final int coordenadasPorVertice = 3;  // Vértices con 3 coordenadas (x, y y z)



	/**
	 * @brief	Lee un fichero ply.
	 * @param	in	contexto Contexto de la actividad principal.
	 * @param	in	fichero Fichero ply.
	 * @return	Un ArrayList donde el 1º elemento es un array de floats con los vértices del modelo y el 2º elemento es un array de shorts con los índices de las caras del modelo.
	 * @note	El fichero se recibe como identificador de los recursos.
	 */

	public static ArrayList <Object> leer (Context contexto, int fichero){
		float vertices[];
		short caras[];
		BufferedReader br;  // BufferedReader para leer el fichero


		// Apertura del fichero
		br = abrirFichero(contexto, fichero);

		// Lectura de la cabecera
		leerCabecera(br);

		// Lectura de los vértices
		vertices = leerVertices(br);

		// Lectura de las caras
		caras = leerCaras(br);

		// Cierre del fichero
		cerrarCichero(br);

		// Resumen de lectura del fichero
		resumen(contexto, fichero);

		// Preparación de los vértices y caras al formato de salida
		ArrayList <Object> salida = new ArrayList <>();
		salida.add(vertices);  // Añado los vértices
		salida.add(caras);  // Añado las caras


		return salida;
	}


	/**
	 * @brief	Abre un fichero ply.
	 * @param 	in	contexto Contexto de la actividad principal.
	 * @param	in	fichero Fichero ply.
	 * @return	Un BufferedReader.
	 * @note	El fichero se recibe como identificador de los recursos.
	 */

	private static BufferedReader abrirFichero (Context contexto, int fichero){
		InputStream is = contexto.getResources().openRawResource(fichero);  // Flujo de entrada del fichero
		BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Buffer de lectura creado a partir del flujo de entrada del fichero


		try{
			if (!br.ready()){  // Si el BufferedReader no está listo
				String nombre_fichero = contexto.getResources().getResourceEntryName(fichero) + extensionPLY;  // Nombre del fichero

				error("No se pudo abrir el fichero '" + nombre_fichero + "' para lectura.");  // Error
			}
		}

		catch (IOException e){  // Capturo la excepción
			Logger.getLogger(TAG).log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}


		return br;
	}


	/**
	 * @brief	Cierra el fichero ply.
	 * @param	in,out	br Buffer de lectura del fichero.
	 */

	private static void cerrarCichero (BufferedReader br){
		try{
			br.close();  // Cierro el buffer de lectura del fichero
		}

		catch (IOException e){  // Capturo la excepción
			Logger.getLogger(TAG).log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * @brief	Lee la cabecera de un fichero ply.
	 * @param	in,out	br Buffer de lectura del fichero.
	 */

	private static void leerCabecera (BufferedReader br){
		String linea = null, token;
		int estado = 0;  // 0: antes de leer 'element vertex' y 'element face'; 1: antes de leer 'element face'; 2: después de leer 'element vertex' y 'element face'
		boolean en_cabecera = true;


		// Lectura de la cabecera
		while (en_cabecera){
			// Lectura de la línea
			try{
				linea = br.readLine();  // Leo una línea del fichero
			}

			catch (IOException e){  // Capturo la excepción
				Logger.getLogger(TAG).log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			}


			// Lectura del token
			if (linea.contains(" "))  // Si la línea contiene varios elementos
				token = linea.substring(0, linea.indexOf(" "));  // El token es el 1º elemento

			else
				token = linea;  // El token es la línea completa


			// Análisis del token obtenido
			switch (token){  // Token
				case "ply":  // Comienzo del fichero
					// Ignoro esta línea
					break;

				case "end_header":  // Final de la cabecera
					if (estado != 2)
						error("No se encuentra 'element vertex' y/o 'element face' en la cabecera.");  // Error

					en_cabecera = false;

					break;

				case "comment":  // Comentario sobre el modelo
					comentario = linea.substring(linea.indexOf(" ") + 1);  // Obtengo el comentario

					break;

				case "format":  // Formato en el que está escrito el fichero
					linea = linea.substring(linea.indexOf(" ") + 1);  // Obtengo el resto de la línea sin el token
					token = linea.substring(0, linea.indexOf(" "));  // Vuelvo a obtener un token

					if (!token.equals("ascii"))  // Si el token no es "ascii"
						error("El formato del fichero ply no es 'ascii', es '" + token + "'. No se puede leer.");  // Error

					break;

				case "element":  // Elemento del modelo
					linea = linea.substring(linea.indexOf(" ") + 1);  // Obtengo el resto de la línea sin el token
					token = linea.substring(0, linea.indexOf(" "));  // Vuelvo a obtener un token

					// Análisis del token obtenido
					switch (token){  // Token
						case "vertex":  // Vértice
							if (estado != 0)  // Si el estado no es 0
								error("La línea 'element vertex' está repetida o se encuentra antes de 'element face'.");  // Error

							linea = linea.substring(linea.indexOf(" ") + 1);  // Obtengo el resto de la línea sin el token
							numVertices = Integer.parseInt(linea);  // Obtengo el número de vértices

							estado = 1;  // Siguiente estado

							break;

						case "face":  // Cara
							if (estado != 1)  // Si estado no es 1
								error("La línea 'element face' está repetida o se encuentra después de 'element vertex'.");  // Error

							linea = linea.substring(linea.indexOf(" ") + 1);  // Obtengo el resto de la línea sin el token
							numCaras = Integer.parseInt(linea);  // Obtengo el número de caras

							estado = 2;  // Siguiente estado

							break;

						default:  // Cualquier otro token
							Logger.getLogger(TAG).log(Level.WARNING, "Elemento '" + token + "' ignorado.");

							break;
					}

					break;

				case "property":  // Propiedades de los elementos del modelo
					// Ignoro esta línea
					break;

				default:  // Cualquier otro token
					Logger.getLogger(TAG).log(Level.WARNING, "Token '" + token + "' ignorado.");

					break;
			}
		}


		// Mensajes de error
		if (numVertices <= 0)  // Si el número de vértices es negativo
			error("Número de vértices incorrecto (o bien es 0 o negativo).");  // Error

		if (numCaras <= 0)  // Si el número de caras es negativo
			error("Número de caras incorrecto (o bien es 0 o negativo).");  // Error
	}


	/**
	 * @brief	Lee los vértices de un fichero ply.
	 * @param	in,out	br Buffer de lectura del fichero.
	 * @return	Un array de floats.
	 */

	private static float[] leerVertices (BufferedReader br){
		String linea = null;  // Línea leída del fichero
		String[] linea_split;  // Elementos de la línea leída del fichero
		float[] vertices = new float[numVertices * coordenadasPorVertice];  // Nota: "Tamaño = número de vértices por coordenadas por vértice"


		// Lectura de vértices
		for (int i = 0; i < numVertices; i++){  // Para cada vértice del fichero
			try{
				linea = br.readLine();  // Leo una línea del fichero
			}

			catch (IOException e){  // Capturo la excepción
				Logger.getLogger(TAG).log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			}


			linea_split = linea.split(" ");  // Obtengo cada elemento de la línea


			// Añado el nuevo vértice
			vertices[i * coordenadasPorVertice] = Float.parseFloat(linea_split[0]);      // x
			vertices[i * coordenadasPorVertice + 1] = Float.parseFloat(linea_split[1]);  // y
			vertices[i * coordenadasPorVertice + 2] = Float.parseFloat(linea_split[2]);  // z
		}


		return vertices;
	}


	/**
	 * @brief	Lee las caras de un fichero ply.
	 * @param	in,out	br Buffer de lectura del fichero.
	 * @return	Un array de short.
	 */

	private static short[] leerCaras (BufferedReader br){
		String linea = null;  // Línea leída del fichero
		String[] linea_split;  // Elementos de la línea leída del fichero
		int num_vertices_cara = 0;
		short[] caras = null;
		short vertice_cara;


		// Lectura de caras
		for (int i = 0; i < numCaras; i++){  // Para cada cara del fichero
			try{
				linea = br.readLine();  // Leo una línea del fichero
			}

			catch (IOException e){  // Capturo la excepción
				Logger.getLogger(TAG).log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			}


			linea_split = linea.split(" ");  // Obtengo cada elemento de la línea

			if (i == 0){  // Si es la 1º iteración del bucle
				num_vertices_cara = Integer.parseInt(linea_split[0]);  // Obtengo el número de vértices que tiene la cara
				caras = new short[numCaras * num_vertices_cara];  // Nota: "Tamaño = número de caras por número de vértices por cara"
			}

			// Añado la nueva cara
			for (int j = 0; j < num_vertices_cara; j++){  // Para cada vértice de la cara
				vertice_cara = Short.parseShort(linea_split[j+1]);

				if (vertice_cara >= numVertices)
					error("Encontrado algún índice de vértice igual o superior al número de vértices.");  // Error

				caras[i * num_vertices_cara + j] = vertice_cara;  // Añado el vértice de la cara
			}
		}


		return caras;
	}


	/**
	 * @brief	Muestra un resumen del fichero ply leído.
	 * @param	in	contexto Contexto de la actividad principal.
	 * @param	in	fichero Fichero ply.
	 * @note	El fichero se recibe como identificador de los recursos.
	 */

	private static void resumen (Context contexto, int fichero){
		String nombre_fichero = contexto.getResources().getResourceEntryName(fichero) + extensionPLY;  // Nombre del fichero

		// Resumen
		Logger.getLogger(TAG).log(Level.INFO, "Resumen del fichero ply");  // Cabecera
		Logger.getLogger(TAG).log(Level.INFO, nombre_fichero);  // Nombre del fichero

		if (comentario != null)  // Si el fichero incluye un comentario
			Logger.getLogger(TAG).log(Level.INFO, comentario);  // Comentario

		Logger.getLogger(TAG).log(Level.INFO, numVertices + " vértices y " + numCaras + " caras");  // Número de vértices y caras
	}


	/**
	 * @brief	Muestra un mensaje de error y finaliza la aplicación.
	 * @param 	in	error Mensaje de error.
	 */

	private static void error (String error){
		Logger.getLogger(TAG).log(Level.SEVERE, "Error leyendo el fichero ply: " + error);
		Logger.getLogger(TAG).log(Level.INFO, "Programa terminado");

		System.exit(1);  // Finalizo la ejecución de la aplicación
	}

}