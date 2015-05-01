package com.Database.App;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MainApp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		CreateDB pizza = new CreateDB();
		pizza.pack();
		pizza.setVisible(true);
	
		
	}
	
	
}
