package com.everis.alicante.courses.becajava.garage.dao.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.everis.alicante.courses.becajava.garage.domain.client.Client;

public interface ClientDaoJDBC {

		public void create(Client client) throws IOException;
		public Client read(String nif) throws IOException;;
		public void update(Client client) throws IOException;;
		public void delete(String dni) throws IOException;;
		public Connection getConnection () throws IOException;;
		public List<Client> readAll();
	}


