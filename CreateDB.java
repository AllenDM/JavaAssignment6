// IF DATABASE HAS NOT BEEN CREATED, UNCOMMENT LINES 170-174.
// IF DATABASE HAS BEEN CREATED AND FRESH RESULTS ARE DESIRED,
// UNCOMMENT LINE 171. THE CONTENTS OF THE SMALL COLUMN OF THE PIZZA TABLE
// ARE DISPLAYED IN THE CONSOLE AFTER PRESSING SUBMIT.
// ALLEN MOODY 



package com.Database.App;


import java.sql.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CreateDB extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:pizzadb;create=true";

     private JCheckBox smallSize = new JCheckBox();
     private JCheckBox mediumSize = new JCheckBox();
     private JCheckBox largeSize = new JCheckBox();
     private JCheckBox pineapple = new JCheckBox();
     private JCheckBox pepperoni = new JCheckBox();
     private JCheckBox sausage = new JCheckBox();
     private JCheckBox onions = new JCheckBox();
     private JCheckBox stuffedCrust = new JCheckBox();
     private JCheckBox extraCheese = new JCheckBox();
     private JTextArea tax = new JTextArea(10,1);
     private JTextArea total = new JTextArea(10,1);
     private JButton submit = new JButton("Submit");
     
     private Integer smallPrice = 6;
     private Integer mediumPrice = 7;
     private Integer largePrice = 8;
     private Integer pineapplePrice = 1;
     private Integer pepperoniPrice = 1;
     private Integer sausagePrice = 1;
     private Integer onionsPrice = 1;
     private Integer stuffedCrustPrice = 2;
     private Integer extraCheesePrice = 2;
     private Double  taxRate = 0.07;
     private Double  totalPrice = 0.0;
     private Double  taxTotal = 0.0;
     private Double  subTotal = 0.0;
     
     private String smallColumn;
     private String mediumColumn;
     private String largeColumn;
     private String pineappleColumn;
     private String pepperoniColumn;
     private String sausageColumn;
     private String onionsColumn;
     private String stuffedCrustColumn;
     private String extraCheeseColumn;
  
     
     public CreateDB ( ) {
         super("Pizza app");
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(4,3));
         panel.add(new JLabel("Small"));
         panel.add(smallSize);
         panel.add(new JLabel("Medium"));
         panel.add(mediumSize);
         panel.add(new JLabel("Large"));
         panel.add(largeSize);
         panel.add(new JLabel("Pineapple"));
         panel.add(pineapple);
         panel.add(new JLabel("Pepperoni"));
         panel.add(pepperoni);
         panel.add(new JLabel("Sausage"));
         panel.add(sausage);
         panel.add(new JLabel("Onions"));
         panel.add(onions);
         panel.add(new JLabel("Stuffed Crust"));
         panel.add(stuffedCrust);
         panel.add(new JLabel("Extra Cheese"));
         panel.add(extraCheese);
         panel.add(new JLabel("Tax"));
         panel.add(tax);
         panel.add(new JLabel("Total"));
         panel.add(total);
       
       
         submit.addActionListener(this);
         panel.add(submit);
         
         add(panel, BorderLayout.CENTER);
         
         setVisible(true);   
      }  // end AddEmployee constructor
     
     public void actionPerformed(ActionEvent e){
    	 
    	  
         if (smallSize.isSelected()){
        	 subTotal+=smallPrice;
        	 smallColumn = "YES";
         }
         else smallColumn = "NO";
         
         if (mediumSize.isSelected()){
        	 subTotal+=mediumPrice;
        	 mediumColumn = "YES";
        	 }
         else mediumColumn = "NO";
         
         if (largeSize.isSelected()){
        	 subTotal+=largePrice;
        	 largeColumn = "YES";
         }
         else largeColumn = "NO";
        	 
         if (pineapple.isSelected()){
        	 subTotal+=pineapplePrice;
        	 pineappleColumn = "YES";
         }
         else pineappleColumn = "NO";
         
         if (pepperoni.isSelected()){
        	 subTotal+=pepperoniPrice;
        	 pepperoniColumn = "YES";
         }
         else pepperoniColumn = "NO";
         
         if (sausage.isSelected()){
        	 subTotal+=sausagePrice;
        	 sausageColumn = "YES";
         }
         else sausageColumn = "NO";
         
         if (onions.isSelected()){
        	 subTotal+=onionsPrice;
        	 onionsColumn = "YES";
         }
         else onionsColumn = "NO";
         
         if(stuffedCrust.isSelected()){
        	 subTotal+=stuffedCrustPrice;
        	 stuffedCrustColumn = "YES";
         }
         else stuffedCrustColumn = "NO";
         
         if(extraCheese.isSelected()){
        	 subTotal+=extraCheesePrice;
        	 extraCheeseColumn = "YES";
         }
         else extraCheeseColumn = "NO";
         
         taxTotal = subTotal * taxRate;
         totalPrice = subTotal + taxTotal;
         
        
         tax.setText(Double.toString(taxTotal));
         total.setText(Double.toString(totalPrice));
         
          try {
         Class.forName(DRIVER);
         final String sql = "insert into pizzadb (small, medium, large, pineapple,"
         		+ " pepperoni, sausage, onions, stuffed, "
         		+ "extrac, total) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
 	  Connection connection = DriverManager.getConnection(JDBC_URL);
  //  connection.createStatement().execute("drop table pizzadb");
  /*    connection.createStatement().execute("create table pizzadb(small varchar(5),"
      		+ " medium varchar(5), large varchar(5), "
      		+ "pineapple varchar(5), pepperoni varchar(5), sausage varchar(5), onions varchar(5)"
      		+ ", stuffed varchar(5), extrac varchar(5), total float)");
      */
      PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1, smallColumn);
        stmt.setString(2, mediumColumn);
        stmt.setString(3, largeColumn);
        stmt.setString(4, pineappleColumn);
        stmt.setString(5, pepperoniColumn);
        stmt.setString(6, sausageColumn);
        stmt.setString(7, onionsColumn);
        stmt.setString(8, stuffedCrustColumn);
        stmt.setString(9, extraCheeseColumn);
        stmt.setDouble(10, totalPrice);
        stmt.executeUpdate();
        
        Statement resultSTMT = connection.createStatement();
        ResultSet rs;
        rs = resultSTMT.executeQuery("select small from pizzadb");
        while (rs.next()){
        	String pizzaSmall = rs.getString("small");
        	System.out.println(pizzaSmall);
        }
        
 		System.out.println("pizzadb table successfully updated");
          }catch(Exception e1 ) {e1.printStackTrace();}
     }
}
