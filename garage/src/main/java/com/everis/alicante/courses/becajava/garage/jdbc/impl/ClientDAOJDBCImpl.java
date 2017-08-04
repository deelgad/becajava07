package com.everis.alicante.courses.becajava.garage.jdbc.impl;

import java.sql.*;
import java.io.*;
import java.util.*;

import com.everis.alicante.courses.becajava.garage.dao.JDBC.ClientDaoJDBC;
import com.everis.alicante.courses.becajava.garage.dao.impl.ClientDAOImpl;
import com.everis.alicante.courses.becajava.garage.domain.client.Client;

public class ClientDAOJDBCImpl    implements ClientDaoJDBC {

	
	private final String MYSQL_JDBC_DRIVER ="com.mysql.jdbc.Driver" ;
	private final String JDBC_CADENA_CONEXION = "jdbc:mysql://localhost:3306/garage_inc";
	private final String JDBC_USR = "usr_garage";
	private final String JDBC_PWD = "1515";
	//Carga del driver
	
	
	
	

	@Override
	public void create(Client client) throws IOException {
		
		Connection cn = null;
		Statement st = null;
		
		try {
			
			 cn = this.getConnection();
			 st = cn.createStatement();

			String sql = " INSERT INTO VEHICULOS (MATRICULA, TIPO_VEHICULO)"
					+ " VALUES ('"+ client.getDni() + "','" + client.getName() + "','" 
					+ client.getSurname() + "','" 
					+ client.getBirthDate() + "')";					
			st.execute(sql);
			
			
		} catch (Exception e) {
			
		System.out.println("Error al insertar cliente " + e.getMessage());
		} finally {
			
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Client read(String nif) throws IOException {
		
		Connection cn = this.getConnection();
		Client cliente = null;
		
		try {
			Statement st = cn.createStatement();
			String sql = " SELECT * FROM CLIENTES  WHERE DNI = '"+ nif + "'";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				
				cliente = new Client (rs.getString("dni"),rs.getString("nombre"), 
				rs.getString("ape_1"), rs.getString("birthdate"));
				
			}
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				cn.close();
				
			}catch( SQLException e ){
				System.out.println("Error al cerrar la conexión");
			}
		}
		
		return cliente;
	}

	@Override
	public void update(Client client) throws IOException {

		
		
	}

	@Override
	public void delete(String dni) throws IOException {

			Connection cn  = this.getConnection();
		try {
			Statement st = cn.createStatement();
			String sql = "DELETE FROM CLIENTES WHERE dni = '"+ dni + "'";
			int eliminados = st.executeUpdate(sql);
			
			 
			String msg = eliminados > 0 ?"eliminados " + eliminados + " registros": " No existe el cliente indicado.";
			System.out.println(msg);

			st.close();
			cn.close();
			
		}catch (SQLException e) {
			
			System.out.println("Error al eliminar el cliente: " + dni);
		}
			
			
	}

	@Override
	public List<Client> readAll() {
		
		Connection cn = this.getConnection();
		String sql = "SELECT * FROM CLIENTES" ;
		
		List <Client> clientes = new  ArrayList <Client>();
		
		try {
			
			Statement st = cn.createStatement();
			 ResultSet rs = st.executeQuery(sql);
			
			 while(rs.next()){
				 
				 Client client  = new Client(rs.getString("dni"), rs.getString("nombre"), rs.getString("ape_1"),rs.getString("birthDate"));
				 
				 clientes.add(client);
				 
			 }
			cn.close();
			
		}catch (SQLException e ) {
		
			System.out.println("Error al recuperar la lista de clientes.");
		} 
		
		return clientes;
	}

	@Override
	public Connection getConnection()   {

		Connection cn = null;
		
		try {
			//Carga del driver
			Class.forName(MYSQL_JDBC_DRIVER);
			
			//Conexión a la BBDD
			 cn = DriverManager.getConnection(JDBC_CADENA_CONEXION,JDBC_USR,JDBC_PWD);
			
		} catch (Exception e) {
			System.out.println("Error al obtener la conexión.");
		}
		 return cn;
	}
}
