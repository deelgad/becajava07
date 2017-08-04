package com.everis.alicante.courses.becajava.garage.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.everis.alicante.courses.becajava.garage.dao.ClientDAO;
import com.everis.alicante.courses.becajava.garage.dao.JDBC.ClientDaoJDBC;
import com.everis.alicante.courses.becajava.garage.domain.client.Client;
import com.everis.alicante.courses.becajava.garage.jdbc.impl.ClientDAOJDBCImpl;
//import com.everis.alicante.courses.becajava.garage.jdbc.impl.ClientDaoJDBCPrepImpl;

public class GarageMenu {

    private final String insertTheOptionYouWant = "Insert the option you want?";
    private final String separator = "************************************************************";
    private final String retornoCarro = "\n";
    private static GarageMenu garageMenu;

    public static GarageMenu getInstance() {
        if (garageMenu == null) {
            garageMenu = new GarageMenu();
        }
        return garageMenu;
    }

    private GarageMenu() {
    }

    public String play() {
        final StringBuilder option = new StringBuilder(GarageMenu.getInstance().getMainOption());
        if (!"0".equals(option.toString())) {
            final String submenuOption = GarageMenu.getInstance().getSubmenuOption(option.toString());
            if ("0".equals(submenuOption)) {
                this.play();
            } else{
                return option.append(".").append(submenuOption).toString();
            }
        }
        return option.toString();
    }

    private String getMainOption() {
        GarageMenu.getInstance().printMainMenu();
        Scanner in = new Scanner(System.in);
        Integer mainOption = in.nextInt();
        if (mainOption < 0 || mainOption > 3) {
            this.printError();
            return this.getMainOption();
        }
        return mainOption.toString();
    }

    private String getSubmenuOption(final String mainOption) {
        GarageMenu.getInstance().buildSubMenu(mainOption);
        Scanner in = new Scanner(System.in);
        Integer subMenuOption = in.nextInt();
        if (subMenuOption < 0 || subMenuOption > 5) {
            this.printError();
            return this.getSubmenuOption(mainOption);
        }
         buildOption(subMenuOption);
        return subMenuOption.toString();
    }
    
    
    private Client getClientData() throws IOException{
    	
    	 StringBuilder stringBuilder = new StringBuilder("Introduce el DNI del cliente:").append("\n");
    	 Scanner in = new Scanner(System.in);
         String dni = in.next();
         
         ClientDaoJDBC dao  = new ClientDAOJDBCImpl();
         Client cliente = dao.read(dni);
    	
    	return cliente;
    }
    private Boolean getNewClientParam() {
    	  this.clearScreen();
    	  StringBuilder stringBuilder = new StringBuilder("Introduce el DNI del cliente:").append("\n");
    	  System.out.println(stringBuilder);

    	  Boolean result = false;
    	  Scanner in = new Scanner(System.in);
          String dni = in.next();
         
          System.out.println("introduce el nombre : " + "\n");
         
          String nombre = in.next();
          
          System.out.println("introduce el apellido : " + "\n");
          String ape1 = in.next();
          
          
          System.out.println("Introduce la fecha de nacimiento:" + "\n");
          String dateBirth = in.next();
          
          
          Client  cliente = new Client(dni,nombre , ape1, dateBirth);
          
        
//          ClientDaoJDBC dao = new ClientDAOJDBCImpl();
          
          //Con preparedStatement
          ClientDaoJDBC dao  = new ClientDAOJDBCImpl();
         
          try {
			dao.create(cliente);
			result = true;
//			
//			GarageMenu.getInstance().play();
		} catch (IOException e) {
			
//			System.out.println( " Error al grabar el cliente : " + dni + e.getMessage());
			result = false;
			
		}
          
          
        
          in.close();
          
    	return result;
    }

    private void printMainMenu() {
        this.clearScreen();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(separator).append("\n");
        stringBuilder.append("MAIN MENU").append("\n");
        stringBuilder.append(separator).append("\n");
        stringBuilder.append("1. cars").append("\n");
        stringBuilder.append("2. Motor Bikes").append("\n");
        stringBuilder.append("3. Bicycles").append("\n");
        stringBuilder.append("0. Exit").append("\n");
        stringBuilder.append(separator).append("\n");
        stringBuilder.append(insertTheOptionYouWant).append("\n");
        System.out.println(stringBuilder.toString());
    }

    private void printError() {
        System.out.println("ERROR!!");
    }

    private void buildSubMenu(final String subMenu) {
        switch (subMenu) {
            case "1":
                printClientMenu();
                break;
            case "2":
                printVehicleMenu();
                break;
            case "3":
                printBookingMenu();
                break;
            default:
                printError();
                break;
        }
    }
    
    private void buildOption(final Integer subMenuOption){
    	switch (subMenuOption) {
    	
    	case 1:
    		listAllClients();
    		break;
    	case 2:
    		printNewCarMenu();
    		break;
    	case 3:
    		printDeleteClient();
    		break;
    	case 4:
    		printClientData();
    		break;
    	case 5:
    		printUpdateField();
    		break;
    	default :
    		printError();
    		break;
    	}
    }
    
    
    
    

    private void printClientMenu() {
        this.printSubMenu("CARS");
    }

    private void printVehicleMenu() {
        this.printSubMenu("MOTOR BIKES");
    }

    private void printBookingMenu() {
        this.printSubMenu("BiCYCLES");
    }

    private void clearScreen() {
//        try {
//            Runtime.getRuntime().exec("cls");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void printSubMenu(final String subMenu) {
        this.clearScreen();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(separator).append("\n");
        stringBuilder.append(subMenu.toUpperCase()).append("\n");
        stringBuilder.append(separator).append("\n");
        stringBuilder.append("1. List").append("\n");
        stringBuilder.append("2. New").append("\n");
        stringBuilder.append("3. Delete").append("\n");
        stringBuilder.append("4. Get").append("\n");
        stringBuilder.append("5. Update").append("\n");
        stringBuilder.append("0. Out").append("\n");
        stringBuilder.append(separator).append("\n");
        stringBuilder.append(insertTheOptionYouWant).append("\n");
        System.out.println(stringBuilder.toString());
    }
    
    private void printUpdateField() {
    	this.clearScreen();
    	final StringBuilder strb = new StringBuilder();
    	strb.append(separator).append(retornoCarro);
    	strb.append("Introduce el nif del cliente a actualizar:").append(retornoCarro);
    	System.out.println(strb.toString());
    	
    	Client cliente = null;
    	
    	try {
			cliente = this.getClientData();
			
			if (null != cliente ){
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("Error obteniedo los datos del cliente");
		}
    }
    private void printClientData()  {
    	this.clearScreen();
    	final StringBuilder stringBuilder = new StringBuilder();
    	
    	 stringBuilder.append(separator).append("\n");
         stringBuilder.append("Introduce el nif del cliente: " ).append("\n");
         System.out.println(stringBuilder);
         
         Client cliente = null;
		try {
			cliente = this.getClientData();
		} catch (Exception e) {
			System.out.println("Error obteniedo los datos del cliente");
		}
         
       
         if (null != cliente) {
        	 System.out.println("nombre: " + cliente.getName() + "\n") ;
        	 System.out.println("apellido: " + cliente.getSurname() + "\n") ;
        	 System.out.println("fecha de nacimiento: " + cliente.getBirthDate() + "\n" );
        	 
         } else  {
        	 
        	 System.out.println("No se ha encotrado ningún cliente con los datos indicados.");
         }
    }
    private void printNewCarMenu(){
    	this.clearScreen();
         Boolean result = this.getNewClientParam();
         
         if (result) {
        	 System.out.println( " cliente  Grabado.");
         }
         
         
    }
    
    private void printDeleteClient(){
    	this.clearScreen();
    	StringBuilder stb = new StringBuilder();
    	stb.append(separator);
    	stb.append(retornoCarro);
    	stb.append("Introduce dni del client a eliminar:");
    	stb.append(retornoCarro);
    	stb.append(separator);
    	stb.append(retornoCarro);
    	System.out.println(stb.toString());
    	
    	 this.removeClientParam();
    	
    	
    	
    }

	private void removeClientParam() {
		
		Scanner sc =  new Scanner(System.in);
		String dni  = sc.next();
		ClientDaoJDBC dao =  new ClientDAOJDBCImpl();
		
		 try {
			dao.delete(dni);
		} catch (IOException e) {
			
			System.out.println("Error al eliminar el cliente " + dni);
			
		}
		 finally{
			 sc.close();
		 }
		
	}
	
	
	private void  listAllClients() {
		
		System.out.println("Recuperando la lista de clientea:" + retornoCarro);
		ClientDaoJDBC dao = new ClientDAOJDBCImpl();
		 List <Client> clientes = dao.readAll();
		 
		 Iterator <Client> it = clientes.iterator();
		 while (it.hasNext()) {
			 Client cliente = it.next();
			 System.out.println( cliente.getDni() + " " + cliente.getName() + " " + cliente.getSurname() + retornoCarro);
			 
		 }
		
	}

}
