package com.everis.alicante.courses.becajava.garage.jdbc.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.everis.alicante.courses.becajava.garage.dao.JDBC.ClientDaoJDBC;
import com.everis.alicante.courses.becajava.garage.domain.client.Client;

public class ClientDaoJDBCPrepImpl extends ClientDAOJDBCImpl implements ClientDaoJDBC {

	@Override
	public void create(Client client) throws IOException {
		
	try {
			
		String sql = " INSERT INTO CLIENTES "
				+ " VALUES (?,?,?,?)" ;
				
			Connection cn = this.getConnection();
			
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, client.getDni());
			pst.setString(2, client.getName());
			pst.setString(3,client.getSurname());
			pst.setString(4, client.getBirthDate());
			
						
			pst.execute();
			
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("Error al insertar cliente " + e.getMessage());
		}
		

	}

	

	@Override
	public void update(Client client) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String dni) throws IOException {
		// TODO Auto-generated method stub

	}

	
	@Override
	public List<Client> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
