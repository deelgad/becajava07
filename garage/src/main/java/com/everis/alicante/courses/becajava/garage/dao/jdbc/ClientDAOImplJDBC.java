package com.everis.alicante.courses.becajava.garage.dao.jdbc;

import java.sql.*;
import java.io.*;
import java.util.*;

import com.everis.alicante.courses.becajava.garage.dao.ClientDAO;
import com.everis.alicante.courses.becajava.garage.domain.client.Client;


public class ClientDAOImplJDBC implements ClientDAO {
	
	public Connection getConnection() throws SQLException{
		
		String url = "jdbc:mysql://localhost:3306/garage";
		
		try {
			//Carga del driver jdbc
			Class.forName("com.mysql.jdbc.Driver");
			//Conexion con la base de datos.
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("Error al conectar con el driver JDBC");
		}
		Connection cn = DriverManager.getConnection(url);
					
		
		return cn;
	}

	public void create(Client client) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public Client read(String nif) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Client client) throws IOException {
		
		try {
			Connection cn = this.getConnection();
			 PreparedStatement pst;
			 
			 String sql = "UPDATE clientes SET nombre =? WHERE  NIF= ?"; 
			
		}catch(Exception e){
			
		}
	}

	public void delete(Client client) throws IOException {

		
		String sql = "DELETE FROM CLIENTES WHERE NIF =' " + client.getDni() + "'";
		int total;
		
		try {
			
			Connection cn = this.getConnection();
			Statement st = cn.createStatement();
			total = st.executeUpdate(sql);
			System.out.println("Se han eliminado " + total + "registros");
			
			//Cerramos la conexión
			st.close();
			
			
			ResultSet rs  = st.executeQuery("SELECT * FROM CLIENTES");
			
			while (rs.next()) {
				System.out.println(rs.getString("nombre"));
			}
			
			
			 
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		
		 
		 
		
			
       
		
	}

	public List<Client> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
