/**
 * @class	anibal.ingenieroenfurecido.utilidades.Vertice
 * @brief	Crea un vértice.
 * @author	Aníbal García García
 * @date	26/03/2014
 * @see		Vector
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


public final class Vertice implements Cloneable {

	/// Coordenada x del vértice.
	private float x;

	/// Coordenada y del vértice.
	private float y;

	/// Coordenada z del vértice.
	private float z;



	/**
	 * @brief	Constructor.
	 * @note	Constructor por defecto.
	 * @note	Los atributos "x", "y" y "z" tendrán el valor 0 por defecto.
	 * @see		Vertice(float, float, float)
	 * @see		Vertice(Vertice)
	 * @see		Vertice(Vector)
	 */

	public Vertice(){
		this(0, 0, 0);  // Vertice(float, float, float)
	}


	/**
	 * @brief	Constructor.
	 * @param	in	x Coordenada x del vértice.
	 * @param	in	y Coordenada y del vértice.
	 * @param	in	z Coordenada z del vértice.
	 * @note	Constructor con parámetros.
	 * @see		Vertice()
	 * @see		Vertice(Vertice)
	 * @see		Vertice(Vector)
	 */

	public Vertice (float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}


	/**
	 * @brief	Constructor.
	 * @param	in	vertice Vértice a copiar.
	 * @note	Constructor de copia para un objeto del tipo Vertice.
	 * @see		Vertice()
	 * @see		Vertice(float, float, float)
	 * @see		Vertice(Vector)
	 */

	public Vertice (Vertice vertice){
		x = vertice.x;
		y = vertice.y;
		z = vertice.z;
	}


	/**
	 * @brief	Constructor.
	 * @param	in	vector Vector a copiar.
	 * @note	Constructor de copia para un objeto del tipo Vector.
	 * @see		Vertice()
	 * @see		Vertice(float, float, float)
	 * @see		Vertice(Vertice)
	 */

	public Vertice (Vector vector){
		x = vector.x();
		y = vector.y();
		z = vector.z();
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
		if (o == null  ||  !(o instanceof Vertice))  // Si el objeto es null o no es instancia de Vertice
			return false;  // No son iguales

		else if (this == o)  // Si son el mismo objeto
			return true;  // Son iguales

		else if (x == ((Vertice) o).x  &&  y == ((Vertice) o).y  &&  z == ((Vertice) o).z)  // Si sus atributos son iguales
			return true;  // Son iguales

		else
			return false;  // No son iguales
	}


	/**
	 * @brief	Suma al vértice otro vértice.
	 * @param	in	vertice Vértice a sumar.
	 * @see		sumar(Vector)
	 */

	public void sumar (Vertice vertice){
		if (vertice != null){  // Si el vértice no es nulo
			x += vertice.x;  // x
			y += vertice.y;  // y
			z += vertice.z;  // z
		}
	}


	/**
	 * @brief	Suma al vértice un vector.
	 * @param	in	vector Vector a sumar.
	 * @see		sumar(Vertice)
	 */

	public void sumar (Vector vector){
		if (vector != null){  // Si el vector no es nulo
			x += vector.x();  // x
			y += vector.y();  // y
			z += vector.z();  // z
		}
	}


	/**
	 * @brief	Suma 2 vértices.
	 * @param	in	vertice1 1º vértice a sumar.
	 * @param	in	vertice2 2º vértice a sumar.
	 * @return	Un vértice con la suma de los 2 vértices; null en caso de que algún vértice sea nulo.
	 * @see		sumar(Vector, Vector)
	 * @see		sumar(Vertice, Vector)
	 * @see		sumar(Vector, Vertice)
	 */

	static public Vertice sumar (Vertice vertice1, Vertice vertice2){
		Vertice nuevo_vertice = null;

		if (vertice1 != null || vertice2 != null){  // Si los vértices no son nulos
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = vertice1.x + vertice2.x;  // x
			nuevo_vertice.y = vertice1.y + vertice2.y;  // y
			nuevo_vertice.z = vertice1.z + vertice2.z;  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Suma 2 vectores.
	 * @param	in	vector1 1º vector a sumar.
	 * @param	in	vector2 2º vector a sumar.
	 * @return	Un vértice con la suma de los 2 vectores; null en caso de que algún vector sea nulo.
	 * @see		sumar(Vertice, Vertice)
	 * @see		sumar(Vertice, Vector)
	 * @see		sumar(Vector, Vertice)
	 */

	static public Vertice sumar (Vector vector1, Vector vector2){
		Vertice nuevo_vertice = null;

		if (vector1 != null || vector2 != null){  // Si los vectores no son nulos
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = vector1.x() + vector2.x();  // x
			nuevo_vertice.y = vector1.y() + vector2.y();  // y
			nuevo_vertice.z = vector1.z() + vector2.z();  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Suma 1 vértice y 1 vector.
	 * @param	in	vertice Vértice a sumar.
	 * @param	in	vector Vector a sumar.
	 * @return	Un vértice con la suma del vértice y el vector; null en caso de que el vértice o el vector sean nulos.
	 * @see		sumar(Vertice, Vertice)
	 * @see		sumar(Vector, Vector)
	 * @see		sumar(Vector, Vertice)
	 */

	static public Vertice sumar (Vertice vertice, Vector vector){
		Vertice nuevo_vertice = null;

		if (vertice != null || vector != null){  // Si el vértice y el vector no son nulos
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = vertice.x + vector.x();  // x
			nuevo_vertice.y = vertice.y + vector.y();  // y
			nuevo_vertice.z = vertice.z + vector.z();  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Suma 1 vector y 1 vértice.
	 * @param	in	vector Vector a sumar.
	 * @param	in	vertice Vértice a sumar.
	 * @return	Un vértice con la suma del vector y el vértice; null en caso de que el vector o el vértice sean nulos.
	 * @see		sumar(Vertice, Vertice)
	 * @see		sumar(Vector, Vector)
	 * @see		sumar(Vertice, Vector)
	 */

	static public Vertice sumar (Vector vector, Vertice vertice){
		return sumar(vertice, vector);  // sumar(Vertice, Vector)
	}


	/**
	 * @brief	Resta al vértice otro vértice.
	 * @param	in	vertice Vértice a restar.
	 * @see		restar(Vector)
	 */

	public void restar (Vertice vertice){
		if (vertice != null){  // Si el vértice no es nulo
			x -= vertice.x;  // x
			y -= vertice.y;  // y
			z -= vertice.z;  // z
		}
	}


	/**
	 * @brief	Resta al vértice un vector.
	 * @param	in	vector Vector a restar.
	 * @see		restar(Vertice)
	 */

	public void restar (Vector vector){
		if (vector != null){  // Si el vector no es nulo
			x -= vector.x();  // x
			y -= vector.y();  // y
			z -= vector.z();  // z
		}
	}


	/**
	 * @brief	Resta 2 vértices.
	 * @param	in	vertice1 1º vértice a restar.
	 * @param	in	vertice2 2º vértice a restar.
	 * @return	Un vértice con la resta de los 2 vértices; null en caso de que algún vértice sea nulo.
	 * @see		restar(Vector, Vector)
	 * @see		restar(Vertice, Vector)
	 * @see		restar(Vector, Vertice)
	 */

	static public Vertice restar (Vertice vertice1, Vertice vertice2){
		Vertice nuevo_vertice = null;

		if (vertice1 != null || vertice2 != null){  // Si los vértices no son nulos
			nuevo_vertice = new Vertice();


			nuevo_vertice.x = vertice1.x - vertice2.x;  // x
			nuevo_vertice.y = vertice1.y - vertice2.y;  // y
			nuevo_vertice.z = vertice1.z - vertice2.z;  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Resta 2 vectores.
	 * @param	in	vector1 1º vector a restar.
	 * @param	in	vector2 2º vector a restar.
	 * @return	Un vértice con la resta de los 2 vectores; null en caso de que algún vector sea nulo.
	 * @see		restar(Vertice, Vertice)
	 * @see		restar(Vertice, Vector)
	 * @see		restar(Vector, Vertice)
	 */

	static public Vertice restar (Vector vector1, Vector vector2){
		Vertice nuevo_vertice = null;

		if (vector1 != null || vector2 != null){  // Si los vectores no son nulos
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = vector1.x() - vector2.x();  // x
			nuevo_vertice.y = vector1.y() - vector2.y();  // y
			nuevo_vertice.z = vector1.z() - vector2.z();  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Resta 1 vértice y 1 vector.
	 * @param	in	vertice Vértice a restar.
	 * @param	in	vector Vector a restar.
	 * @return	Un vértice con la resta del vértice y el vector; null en caso de que el vértice o el vector sean nulos.
	 * @see		restar(Vertice, Vertice)
	 * @see		restar(Vector, Vector)
	 * @see		restar(Vector, Vertice)
	 */

	static public Vertice restar (Vertice vertice, Vector vector){
		Vertice nuevo_vertice = null;

		if (vertice != null || vector != null){  // Si el vértice y el vector no son nulos
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = vertice.x - vector.x();  // x
			nuevo_vertice.y = vertice.y - vector.y();  // y
			nuevo_vertice.z = vertice.z - vector.z();  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Resta 1 vector y 1 vértice.
	 * @param	in	vector Vector a restar.
	 * @param	in	vertice Vértice a restar.
	 * @return	Un vértice con la resta del vector y el vértice; null en caso de que el vector o el vértice sean nulos.
	 * @see		restar(Vertice, Vertice)
	 * @see		restar(Vector, Vector)
	 * @see		restar(Vertice, Vector)
	 */

	static public Vertice restar (Vector vector, Vertice vertice){
		return restar(vertice, vector);  // restar(Vertice, Vector)
	}


	/**
	 * @brief	Multiplica el vértice por un número.
	 * @param	in	numero Número por el cual multiplicar.
	 */

	public void multiplicar (double numero){
		x *= numero;  // x
		y *= numero;  // y
		z *= numero;  // z
	}


	/**
	 * @brief	Multiplica un vértice por un número.
	 * @param	in	vertice Vértice a multiplicar.
	 * @param	in	numero Número por el cual multiplicar.
	 * @return	Un vértice con el producto del vértice por el número; null en caso de que el vértice sea nulo.
	 * @see		multiplicar(double, Vertice)
	 * @see		multiplicar(Vector, double)
	 * @see		multiplicar(double, Vector)
	 */

	static public Vertice multiplicar (Vertice vertice, double numero){
		Vertice nuevo_vertice = null;

		if (vertice != null){  // Si el vértice no es nulo
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = (float) (vertice.x * numero);  // x
			nuevo_vertice.y = (float) (vertice.y * numero);  // y
			nuevo_vertice.z = (float) (vertice.z * numero);  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Multiplica un vértice por un número.
	 * @param	in	numero Número por el cual multiplicar.
	 * @param	in	vertice Vértice a multiplicar.
	 * @return	Un vértice con el producto del vértice por el número; null en caso de que el vértice sea nulo.
	 * @see		multiplicar(Vertice, double)
	 * @see		multiplicar(Vector, double)
	 * @see		multiplicar(double, Vector)
	 */

	static public Vertice multiplicar (double numero, Vertice vertice){
		return multiplicar(vertice, numero);  // multiplicar(Vertice, double)
	}


	/**
	 * @brief	Multiplica un vector por un número.
	 * @param	in	vector Vector a multiplicar.
	 * @param	in	numero Número por el cual multiplicar.
	 * @return	Un vértice con el producto del vector por el número; null en caso de que el vector sea nulo.
	 * @see		multiplicar(Vertice, double)
	 * @see		multiplicar(double, Vertice)
	 * @see		multiplicar(double, Vector)
	 */

	static public Vertice multiplicar (Vector vector, double numero){
		Vertice nuevo_vertice = null;

		if (vector != null){  // Si el vector no es nulo
			nuevo_vertice = new Vertice();

			nuevo_vertice.x = (float) (vector.x() * numero);  // x
			nuevo_vertice.y = (float) (vector.y() * numero);  // y
			nuevo_vertice.z = (float) (vector.z() * numero);  // z
		}

		return nuevo_vertice;
	}


	/**
	 * @brief	Multiplica un vector por un número.
	 * @param	in	numero Número por el cual multiplicar.
	 * @param	in	vector Vector a multiplicar.
	 * @return	Un vértice con el producto del vector por el número; null en caso de que el vector sea nulo.
	 * @see		multiplicar(Vertice, double)
	 * @see		multiplicar(double, Vertice)
	 * @see		multiplicar(Vector, double)
	 */

	static public Vertice multiplicar (double numero, Vector vector){
		return multiplicar(vector, numero);  // multiplicar(Vector, double)
	}


	/// Devuelve un String que contiene una descripción concisa y legible por humanos de este vértice.

	@Override
	public String toString(){
		return (getClass().getName() + ": x = " + x + ", y = " + y + ", z =" + z);
	}


	/**
	 * @brief	Introduce la coordenada x del vértice.
	 * @param	in	x Nueva coordenada x del vértice.
	 */

	public void x (float x){
		this.x = x;
	}


	/**
	 * @brief	Devuelve la coordenada x del vértice.
	 * @return	La coordenada x del vértice.
	 */

	public float x(){
		return x;
	}


	/**
	 * @brief	Introduce la coordenada y del vértice.
	 * @param	in	y Nueva coordenada y del vértice.
	 */

	public void y (float y){
		this.y = y;
	}


	/**
	 * @brief	Devuelve la coordenada y del vértice.
	 * @return	La coordenada y del vértice.
	 */

	public float y(){
		return y;
	}


	/**
	 * @brief	Introduce la coordenada z del vértice.
	 * @param	in	z Nueva coordenada z del vértice.
	 */

	public void z (float z){
		this.z = z;
	}


	/**
	 * @brief	Devuelve la coordenada z del vértice.
	 * @return	La coordenada z del vértice.
	 */

	public float z(){
		return z;
	}

}