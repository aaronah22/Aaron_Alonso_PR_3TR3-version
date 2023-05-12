package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import modelo.Ciudades;
import modelo.Paises;
import vista.Consultarciudades;
	
public class Basededatos {
	Connection conexion;
	Statement consulta = null;
	
	public Statement conectar() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
		} catch (CommunicationsException err) {
			System.err.println("\nError: BASE DE DATOS NO INICIADA");
			 System. exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consulta;
	}
	
	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Ciudades>consultarnombre(String selected) {
		
		ArrayList<Ciudades> arrlCiudades = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/world","paises2023","paises2023");
			
			Statement consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("Select c.name ,d.name, district, c.population,continent,language "
													+ "from city c, country d, countrylanguage l "
													+ "where l.countrycode=code "
													+ "and  c.countrycode=code "
													+ "and IsOfficial='T' "
													+ "and c.name like '%" + selected + "%'");
				while(rs.next()) {
					Ciudades nombre = new Ciudades(
							rs.getString("c.name"),
							rs.getString("d.name"),
							rs.getString("district"),
							rs.getInt("population"),
							rs.getString("continent"),
							rs.getString("language")
					);
					arrlCiudades.add(nombre);
				}
			conexion.close();
		
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return arrlCiudades;
	}
	
	public ArrayList<Paises>consultapais() {
		ArrayList<Paises>arrlpais = new ArrayList<>();
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select Code, Name from country");
			
			while(rs.next()) {
				Paises pais = new Paises();
				pais.setCode(rs.getString("Code"));
				pais.setName(rs.getString("Name"));
				
				arrlpais.add(pais);
			}
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrlpais;
	}
	
	public ArrayList<Ciudades>consultarpaises(String selected) {
		
		ArrayList<Ciudades> arrlCiudades = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/world","paises2023","paises2023");
			
			Statement consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("Select c.name ,d.name, district, c.population,continent,language "
													+ "from city c, country d, countrylanguage l "
													+ "WHERE l.countrycode=code "
													+ "and c.countrycode=code "
													+ "and IsOfficial='T' "
													+ "and c.CountryCode = "
													+ "(SELECT Code FROM country WHERE Name = '" + selected+"') ORDER BY d.NAME");
				while(rs.next()) {
					Ciudades pais = new Ciudades(
							rs.getString("c.name"),
							rs.getString("d.name"),
							rs.getString("district"),
							rs.getInt("c.population"),
							rs.getString("continent"),
							rs.getString("language")
					);
					arrlCiudades.add(pais);
				}
			conexion.close();
		
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return arrlCiudades;
	}
	
	public ArrayList<Ciudades>consultadistrito() {
		ArrayList<Ciudades>arrldistrito = new ArrayList<>();
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select District from city GROUP BY District");
			
			while(rs.next()) {
				Ciudades distrito = new Ciudades();
				distrito.setDistrict(rs.getString("District"));
				
				arrldistrito.add(distrito);
			}
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrldistrito;
	}
	
	public ArrayList<Ciudades>consultardistritos(String selected) {
		
		ArrayList<Ciudades> arrldistritos = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/world","paises2023","paises2023");
			
			Statement consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("Select c.name ,d.name, district, c.population,continent,language "
													+ "from city c, country d, countrylanguage l "
													+ "WHERE l.countrycode=code "
													+ "and c.countrycode=code "
													+ "and IsOfficial='T' "
													+ "and district = "
													+ "(SELECT District FROM city WHERE District = '" + selected+"' GROUP BY District) ORDER BY c.district");
				while(rs.next()) {
					Ciudades distrito = new Ciudades(
							rs.getString("c.name"),
							rs.getString("d.name"),
							rs.getString("district"),
							rs.getInt("c.population"),
							rs.getString("continent"),
							rs.getString("language")
					);
					arrldistritos.add(distrito);
				}
			conexion.close();
		
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return arrldistritos;
	}
	
	public ArrayList<Ciudades>consultapoblacion() {
		ArrayList<Ciudades>arrlpoblacion = new ArrayList<>();
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select Population from city");
			
			while(rs.next()) {
				Ciudades poblacion = new Ciudades();
				poblacion.setPopulation(rs.getInt("Population"));
				
				arrlpoblacion.add(poblacion);
			}
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrlpoblacion;
	}
	
	public ArrayList<Ciudades>consultarpoblaciones(int selected) {
		
		ArrayList<Ciudades> arrlpoblaciones = new ArrayList<>();
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/world","paises2023","paises2023");
			
			Statement consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("Select c.name ,d.name, district, c.population,continent,language "
													+ "from city c, country d, countrylanguage l "
													+ "WHERE l.countrycode=code "
													+ "and c.countrycode=code "
													+ "and IsOfficial='T' "
													+ "and c.Population >=" + selected + " ORDER BY c.population desc");
				while(rs.next()) {
					Ciudades poblacion = new Ciudades(
							rs.getString("c.name"),
							rs.getString("d.name"),
							rs.getString("district"),
							rs.getInt("c.population"),
							rs.getString("continent"),
							rs.getString("language")
					);
					arrlpoblaciones.add(poblacion);

				}
			conexion.close();
		
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return arrlpoblaciones;
	}
	
	public void modificarciudad(String ciudad, int poblacion, String distrito, String nuevonombre, int nuevopoblacion, String nuevodistrito ) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			int valor = consulta.executeUpdate("update city SET name ='"+ nuevonombre +"', population="+ nuevopoblacion + ", district='"+ nuevodistrito +"' WHERE name ='"+ ciudad +"'and population="+ poblacion + " and district='"+distrito+"'");
			conexion.close();
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarciudad(String nuevonombre,String nuevopais, int nuevopoblacion, String nuevodistrito) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		
		try {
			 rs = consultaActual.executeQuery("select code from country where name= '"+ nuevopais+"'");
			if(rs.next()) {
				String countrycode = rs.getString("code");
				int valor = consultaActual.executeUpdate("insert into city(name, countrycode, population, district) values ('" + nuevonombre + "', '"+ countrycode +"'," + nuevopoblacion + ",'"+ nuevodistrito +"')" );
			}
			conexion.close();
			
		
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void borrarciudad(String ciudad, int poblacion, String distrito) {
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			int valor = consulta.executeUpdate("delete from city WHERE name ='"+ ciudad +"'");
			conexion.close();
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Ciudades>consultadistritosi(String selected) {
		ArrayList<Ciudades>arrldistritosi = new ArrayList<>();
		Statement consultaActual = conectar();
		ResultSet rs = null;
		try {
			rs = consultaActual.executeQuery("select district from city c, country d where c.name=d.name and d.name='" + selected + "'"
);
			
			while(rs.next()) {
				Ciudades poblacion = new Ciudades();
				poblacion.setPopulation(rs.getInt("Population"));
				
				arrldistritosi.add(poblacion);
			}
		}catch (CommunicationsException err) {
			System.err.println("La base de datos no esta arrancada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrldistritosi;
	}
}



