package rbenef;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectage {
	Connection con;
	public Connectage(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/dbcommerce","root","");
			System.out.println("bien connect�e!");
		}
		catch(Exception ex){
			
			System.out.println("probleme de connection!");	
		}
	}
	public Connection laconnexion(){
		return con;
	}

}
/*Application r�alis�e du 21 au 23 Juin 2020 � N'djam�na au Tchad par
 *TARGOTO CHRISTIAN
 *Contact : 23560316682 / ct@chrislink.net */