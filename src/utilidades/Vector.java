/**
 * @class	anibal.ingenieroenfurecido.utilidades.Vector
 * @brief	Crea un vector de 3 elementos.
 * @author	Aníbal García García
 * @date	26/03/2014
 * @see		Vertice
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


import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;


public final class Vector implements Cloneable {

	/// Coordenada x del vector.
	private float x;

	/// Coordenada y del vector.
	private float y;

	/// Coordenada z del vector.
	private float z;



	/**
	 * @brief	Constructor.
	 * @note	Constructor por defecto.
	 * @note	Los atributos "x", "y" y "z" tendrán el valor 0 por defecto.
	 * @see		Vector(float, float, float)
	 * @see		Vector(Vector)
	 * @see		Vector(Vertice)
	 */

	public Vector(){
		this(0, 0, 0);  // Vector(float, float, float)
	}


	/**
	 * @brief	Constructor.
	 * @param	in	x Coordenada x del vector.
	 * @param	in	y Coordenada y del vector.
	 * @param	in	z Coordenada z del vector.
	 * @note	Constructor con parámetros.
	 * @see		Vector()
	 * @see		Vector(Vector)
	 * @see		Vector(Vertice)
	 */

	public Vector (float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}


	/**
	 * @brief	Constructor.
	 * @param	in	vector Vector a copiar.
	 * @note	Constructor de copia para un objeto del tipo Vector.
	 * @see		Vector()
	 * @see		Vector(float, float, float)
	 * @see		Vector(Vertice)
	 */

	public Vector (Vector vector){
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}


	/**
	 * @brief	Constructor.
	 * @param	in	vertice Vértice a copiar.
	 * @note	Constructor de copia para un objeto del tipo Vertice.
	 * @see		Vector()
	 * @see		Vector(float, float, float)
	 * @see		Vector(Vector)
	 */

	public Vector (Vertice vertice){
		x = vertice.x();
		y = vertice.y();
		z = vertice.z();
	}


	/// Crea y devuelve una copia de este objeto.

	@Override
	public Object clone(){
		Object o = null;

		try{
			o = super.clone();  // Método clone de la superclase
		}

		catch (CloneNotSupportedException e){  // Capturo la excepción
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}

		return o;
	}


	/// Compara este objeto con el objeto especificado y devuelve si son iguales.

	@Override
	public boolean equals (Object o){
		if (o == null  ||  !(o instanceof Vector))  // Si el objeto es null o no es instancia de Vector
			return false;  // No son iguales

		else if (this == o)  // Si son el mismo objeto
			return true;  // Son iguales

		else if (x == ((Vector) o).x  &&  y == ((Vector) o).y  &&  z == ((Vector) o).z)  // Si sus atributos son iguales
			return true;  // Son iguales

		else
			return false;  // No son iguales
	}


	/**
	 * @brief	Suma al vector otro vector.
	 * @param	in	vector Vector a sumar.
	 * @see		sumar(Vertice)
	 */

	public void sumar (Vector vector){
		if (vector != null){  // Si el vector no es nulo
			x += vector.x;  // x
			y += vector.y;  // y
			z += vector.z;  // z
		}
	}


	/**
	 * @brief	Suma al vector un vértice.
	 * @param	in	vertice Vértice a sumar.
	 * @see		sumar(Vector)
	 */

	public void sumar (Vertice vertice){
		if (vertice != null){  // Si el vértice no es nulo
			x += vertice.x();  // x
			y += vertice.y();  // y
			z += vertice.z();  // z
		}
	}


	/**
	 * @brief	Suma 2 vectores.
	 * @param	in	vector1 1º vector a sumar.
	 * @param	in	vector2 2º vector a sumar.
	 * @return	Un vector con la suma de los 2 vectores; null en caso de que algún vector sea nulo.
	 * @see		sumar(Vertice, Vertice)
	 * @see		sumar(Vector, Vertice)
	 * @see		sumar(Vertice, Vector)
	 */

	static public Vector sumar (Vector vector1, Vector vector2){
		Vector nuevo_vector = null;

		if (vector1 != null || vector2 != null){  // Si los vectores no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vector1.x + vector2.x;  // x
			nuevo_vector.y = vector1.y + vector2.y;  // y
			nuevo_vector.z = vector1.z + vector2.z;  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Suma 2 vértices.
	 * @param	in	vertice1 1º vértice a sumar.
	 * @param	in	vertice2 2º vértice a sumar.
	 * @return	Un vector con la suma de los 2 vértices; null en caso de que algún vértice sea nulo.
	 * @see		sumar(Vector, Vector)
	 * @see		sumar(Vector, Vertice)
	 * @see		sumar(Vertice, Vector)
	 */

	static public Vector sumar (Vertice vertice1, Vertice vertice2){
		Vector nuevo_vector = null;

		if (vertice1 != null || vertice2 != null){  // Si los vértices no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vertice1.x() + vertice2.x();  // x
			nuevo_vector.y = vertice1.y() + vertice2.y();  // y
			nuevo_vector.z = vertice1.z() + vertice2.z();  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Suma 1 vector y 1 vértice.
	 * @param	in	vector Vector a sumar.
	 * @param	in	vertice Vértice a sumar.
	 * @return	Un vector con la suma del vector y el vértice; null en caso de que el vector o el vértice sean nulos.
	 * @see		sumar(Vector, Vector)
	 * @see		sumar(Vertice, Vertice)
	 * @see		sumar(Vertice, Vector)
	 */

	static public Vector sumar (Vector vector, Vertice vertice){
		Vector nuevo_vector = null;

		if (vector != null || vertice != null){  // Si el vector y el vértice no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vector.x + vertice.x();  // x
			nuevo_vector.y = vector.y + vertice.y();  // y
			nuevo_vector.z = vector.z + vertice.z();  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Suma 1 vértice y 1 vector.
	 * @param	in	vertice Vértice a sumar.
	 * @param	in	vector Vector a sumar.
	 * @return	Un vector con la suma del vértice y el vector; null en caso de que el vértice o el vector sean nulos.
	 * @see		sumar(Vector, Vector)
	 * @see		sumar(Vertice, Vertice)
	 * @see		sumar(Vector, Vertice)
	 */

	static public Vector sumar (Vertice vertice, Vector vector){
		return sumar(vector, vertice);  // sumar(Vector, Vertice)
	}


	/**
	 * @brief	Resta al vector otro vector.
	 * @param	in	vector Vector a restar.
	 * @see		restar(Vertice)
	 */

	public void restar (Vector vector){
		if (vector != null){  // Si el vector no es nulo
			x -= vector.x;  // x
			y -= vector.y;  // y
			z -= vector.z;  // z
		}
	}


	/**
	 * @brief	Resta al vector un vértice.
	 * @param	in	vertice Vértice a restar.
	 * @see		restar(Vector)
	 */

	public void restar (Vertice vertice){
		if (vertice != null){  // Si el vértice no es nulo
			x -= vertice.x();  // x
			y -= vertice.y();  // y
			z -= vertice.z();  // z
		}
	}


	/**
	 * @brief	Resta 2 vectores.
	 * @param	in	vector1 1º vector a restar.
	 * @param	in	vector2 2º vector a restar.
	 * @return	Un vector con la resta de los 2 vectores; null en caso de que algún vector sea nulo.
	 * @see		restar(Vertice, Vertice)
	 * @see		restar(Vector, Vertice)
	 * @see		restar(Vertice, Vector)
	 */

	static public Vector restar (Vector vector1, Vector vector2){
		Vector nuevo_vector = null;

		if (vector1 != null || vector2 != null){  // Si los vectores no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vector1.x - vector2.x;  // x
			nuevo_vector.y = vector1.y - vector2.y;  // y
			nuevo_vector.z = vector1.z - vector2.z;  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Resta 2 vértices.
	 * @param	in	vertice1 1º vértice a restar.
	 * @param	in	vertice2 2º vértice a restar.
	 * @return	Un vector con la resta de los 2 vértices; null en caso de que algún vértice sea nulo.
	 * @see		restar(Vector, Vector)
	 * @see		restar(Vector, Vertice)
	 * @see		restar(Vertice, Vector)
	 */

	static public Vector restar (Vertice vertice1, Vertice vertice2){
		Vector nuevo_vector = null;

		if (vertice1 != null || vertice2 != null){  // Si los vértices no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vertice1.x() - vertice2.x();  // x
			nuevo_vector.y = vertice1.y() - vertice2.y();  // y
			nuevo_vector.z = vertice1.z() - vertice2.z();  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Resta 1 vector y 1 vértice.
	 * @param	in	vector Vector a restar.
	 * @param	in	vertice Vértice a restar.
	 * @return	Un vector con la resta del vector y el vértice; null en caso de que el vector o el vértice sean nulos.
	 * @see		restar(Vector, Vector)
	 * @see		restar(Vertice, Vertice)
	 * @see		restar(Vertice, Vector)
	 */

	static public Vector restar (Vector vector, Vertice vertice){
		Vector nuevo_vector = null;

		if (vector != null || vertice != null){  // Si el vector y el vértice no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vector.x - vertice.x();  // x
			nuevo_vector.y = vector.y - vertice.y();  // y
			nuevo_vector.z = vector.z - vertice.z();  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Resta 1 vértice y 1 vector.
	 * @param	in	vertice Vértice a restar.
	 * @param	in	vector Vector a restar.
	 * @return	Un vector con la resta del vértice y el vector; null en caso de que el vértice o el vector sean nulos.
	 * @see		restar(Vector, Vector)
	 * @see		restar(Vertice, Vertice)
	 * @see		restar(Vector, Vertice)
	 */

	static public Vector restar (Vertice vertice, Vector vector){
		return restar(vector, vertice);  // restar(Vector, Vertice)
	}


	/**
	 * @brief	Multiplica el vector por un número.
	 * @param	in	numero Número por el cual multiplicar.
	 */

	public void multiplicar (double numero){
		x *= numero;  // x
		y *= numero;  // y
		z *= numero;  // z
	}


	/**
	 * @brief	Multiplica un vector por un número.
	 * @param	in	vector Vector a multiplicar.
	 * @param	in	numero Número por el cual multiplicar.
	 * @return	Un vector con el producto del vector por el número; null en caso de que el vector sea nulo.
	 * @see		multiplicar(double, Vector)
	 * @see		multiplicar(Vertice, double)
	 * @see		multiplicar(double, Vertice)
	 */

	static public Vector multiplicar (Vector vector, double numero){
		Vector nuevo_vector = null;

		if (vector != null){  // Si el vector no es nulo
			nuevo_vector = new Vector();

			nuevo_vector.x = (float) (vector.x * numero);  // x
			nuevo_vector.y = (float) (vector.y * numero);  // y
			nuevo_vector.z = (float) (vector.z * numero);  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Multiplica un vector por un número.
	 * @param	in	numero Número por el cual multiplicar.
	 * @param	in	vector Vector a multiplicar.
	 * @return	Un vector con el producto del vector por el número; null en caso de que el vector sea nulo.
	 * @see		multiplicar(Vector, double)
	 * @see		multiplicar(Vertice, double)
	 * @see		multiplicar(double, Vertice)
	 */

	static public Vector multiplicar (double numero, Vector vector){
		return multiplicar(vector, numero);  // multiplicar(Vector, double)
	}


	/**
	 * @brief	Multiplica un vértice por un número.
	 * @param	in	vertice Vértice a multiplicar.
	 * @param	in	numero Número por el cual multiplicar.
	 * @return	Un vector con el producto del vértice por el número; null en caso de que el vértice sea nulo.
	 * @see		multiplicar(Vector, double)
	 * @see		multiplicar(double, Vector)
	 * @see		multiplicar(double, Vertice)
	 */

	static public Vector multiplicar (Vertice vertice, double numero){
		Vector nuevo_vector = null;

		if (vertice != null){  // Si el vértice no es nulo
			nuevo_vector = new Vector();

			nuevo_vector.x = (float) (vertice.x() * numero);  // x
			nuevo_vector.y = (float) (vertice.y() * numero);  // y
			nuevo_vector.z = (float) (vertice.z() * numero);  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Multiplica un vértice por un número.
	 * @param	in	numero Número por el cual multiplicar.
	 * @param	in	vertice Vértice a multiplicar.
	 * @return	Un vector con el producto del vértice por el número; null en caso de que el vértice sea nulo.
	 * @see		multiplicar(Vector, double)
	 * @see		multiplicar(double, Vector)
	 * @see		multiplicar(Vertice, double)
	 */

	static public Vector multiplicar (double numero, Vertice vertice){
		return multiplicar(vertice, numero);  // multiplicar(Vertice, double)
	}


	/**
	 * @brief	Normaliza el vector.
	 */

	public void normalizar(){
		float modulo = modulo();

		x /= modulo;  // x
		y /= modulo;  // y
		z /= modulo;  // z
	}


	/**
	 * @brief	Calcula el módulo del vector.
	 * @return	El módulo del vector.
	 * @note	Utiliza la fórmula de la distancia euclídea siendo uno de los puntos 0.
	 */

	public float modulo(){
		return (float) sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));  // Raiz cuadrada de x^2 + y^2 + z^2
	}


	/**
	 * @brief	Calcula el producto vectorial del vector con otro almacenando el resultado en el propio vector.
	 * @param	in	vector Vector con el que calcular el producto vectorial.
	 */

	public void productoVectorial (Vector vector){
		float nueva_x = y * vector.z - z * vector.y;  // x
		float nueva_y = z * vector.x - x * vector.z;  // y
		float nueva_z = x * vector.y - y * vector.x;  // z

		// Actualizo las coordenadas
		x = nueva_x;
		y = nueva_y;
		z = nueva_z;
	}


	/**
	 * @brief	Calcula el producto vectorial de 2 vectores.
	 * @param	in	vector1 1º vector para calcular el producto vectorial.
	 * @param	in	vector2 2º vector para calcular el producto vectorial.
	 * @return	Un vector con producto vectorial de los 2 vectores; null en caso de que algún vector sea nulo.
	 */

	static public Vector productoVectorial (Vector vector1, Vector vector2){
		Vector nuevo_vector = null;

		if (vector1 != null || vector2 != null){  // Si los vectores no son nulos
			nuevo_vector = new Vector();

			nuevo_vector.x = vector1.y * vector2.z - vector1.z * vector2.y;  // x
			nuevo_vector.y = vector1.z * vector2.x - vector1.x * vector2.z;  // y
			nuevo_vector.z = vector1.x * vector2.y - vector1.y * vector2.x;  // z
		}

		return nuevo_vector;
	}


	/**
	 * @brief	Rota el vector en el eje X.
	 * @param	in	angulo Ángulo a rotar en grados sexagesimales.
	 */

	public void rotarEjeX (double angulo){
		angulo = angulo * (PI / 180);  // Paso el ángulo a radianes

		// [x, y * cos(angulo) - z * sin(angulo), y * sin(angulo) + z * cos(angulo)]
		float nueva_x = x;
		float nueva_y = (float) (y * cos(angulo) - z * sin(angulo));
		float nueva_z = (float) (y * sin(angulo) + z * cos(angulo));

		// Actualizo las coordenadas
		x = nueva_x;
		y = nueva_y;
		z = nueva_z;
	}


	/**
	 * @brief	Rota el vector en el eje Y.
	 * @param	in	angulo Ángulo a rotar en grados sexagesimales.
	 */

	public void rotarEjeY (double angulo){
		angulo = angulo * (PI / 180);  // Paso el ángulo a radianes

		// [x * cos(angulo) + z * sin(angulo), y, -x * sin(angulo) + z * cos(angulo)]
		float nueva_x = (float) (x * cos(angulo) + z * sin(angulo));
		float nueva_y = y;
		float nueva_z = (float) (- x * sin(angulo) + z * cos(angulo));

		// Actualizo las coordenadas
		x = nueva_x;
		y = nueva_y;
		z = nueva_z;
	}


	/**
	 * @brief	Rota el vector en el eje Z.
	 * @param	in	angulo Ángulo a rotar en grados sexagesimales.
	 */

	public void rotarEjeZ (double angulo){
		angulo = angulo * (PI / 180);  // Paso el ángulo a radianes

		// [x * cos(angulo) - y * sin(angulo), x * sin(angulo) + y * cos(angulo), z]
		float nueva_x = (float) (x * cos(angulo) - y * sin(angulo));
		float nueva_y = (float) (x * sin(angulo) + y * cos(angulo));
		float nueva_z = z;

		// Actualizo las coordenadas
		x = nueva_x;
		y = nueva_y;
		z = nueva_z;
	}


	/// Devuelve un String que contiene una descripción concisa y legible por humanos de este vector.

	@Override
	public String toString(){
		return (getClass().getName() + ": x = " + x + ", y = " + y + ", z =" + z);
	}


	/**
	 * @brief	Introduce la coordenada x del vector.
	 * @param	in	x Nueva coordenada x del vector.
	 */

	public void x (float x){
		this.x = x;
	}


	/**
	 * @brief	Devuelve la coordenada x del vector.
	 * @return	La coordenada x del vector.
	 */

	public float x(){
		return x;
	}


	/**
	 * @brief	Introduce la coordenada y del vector.
	 * @param	in	y Nueva coordenada y del vector.
	 */

	public void y (float y){
		this.y = y;
	}


	/**
	 * @brief	Devuelve la coordenada y del vector.
	 * @return	La coordenada y del vector.
	 */

	public float y(){
		return y;
	}


	/**
	 * @brief	Introduce la coordenada z del vector.
	 * @param	in	z Nueva coordenada z del vector.
	 */

	public void z (float z){
		this.z = z;
	}


	/**
	 * @brief	Devuelve la coordenada z del vector.
	 * @return	La coordenada z del vector.
	 */

	public float z(){
		return z;
	}

}