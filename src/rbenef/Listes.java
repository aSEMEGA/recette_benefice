package rbenef;
/*Application réalisée du 21 au 23 Juin 2020 à N'djaména au Tchad par
 *TARGOTO CHRISTIAN
 *Contact : 23560316682 / ct@chrislink.net */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Listes extends JFrame {
	Statement st,st2,st3;
	Connectage con1,con2,con3;
	ResultSet rst,rst2,rst3;
	JTable tb1,tb2,tb3;
	JScrollPane scrl1,scrl2,scrl3;
	JLabel lbtitre1,lbtitre2,lbtitre3;
	JButton bform;
public Listes(){
	this.setTitle("chcode_appli");
	this.setSize(800,700);
	this.setLocationRelativeTo(null);
	JPanel pn=new JPanel();
	pn.setLayout(null);
	pn.setBackground(new Color(0,200,200));
	add(pn);
	JButton bform=new JButton("Formulaire");
	bform.setBounds(5,5,100,30);
	bform.addActionListener(new ActionListener(){
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
			Principale pr=new Principale();
			pr.setVisible(true);
		}

	});
	pn.add(bform);
	lbtitre1=new JLabel("Recettes et Bénéfices par jour et par produit vendu (devise = fcfa)");
	lbtitre1.setBounds(120,10,800,30);
	lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
	pn.add(lbtitre1);
	lbtitre2=new JLabel("Recettes et Bénéfices par  mois et par produit vendu");
	lbtitre2.setBounds(100,240,800,30);
	lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
	pn.add(lbtitre2);
	lbtitre3=new JLabel("Recette et Bénéfice total par mois de tous les produits vendus");
	lbtitre3.setBounds(70,470,800,30);
	lbtitre3.setFont(new Font("Arial",Font.BOLD,18));
	pn.add(lbtitre3);
	////////////////////////////// recette benefice par jour et par produit
	DefaultTableModel df=new DefaultTableModel();
	init();
	df.addColumn("Année");
	df.addColumn("Mois");
	df.addColumn("Jour");
	df.addColumn("produit");
	df.addColumn("Nombre");
	df.addColumn("Recette");
	df.addColumn("Bénéfice");
	tb1.setModel(df);
	pn.add(scrl1);
 String sql="select annee,mois,jour,produit,nombre,prix_vente * nombre as recette,"
	 + "prix_vente * nombre - prix_achat * nombre as benefice from vente inner join produit "
	 + "on vente.produit=produit.codeprod order by annee,mois,jour";
 con1=new Connectage();
	try{
		st=con1.laconnexion().createStatement();
		rst=st.executeQuery(sql);
		while(rst.next()){
	df.addRow(new Object[]{
			rst.getString("annee"),
			rst.getString("mois"),
			rst.getString("jour"),
			rst.getString("produit"),
			rst.getString("nombre"),
			rst.getString("recette"),
			rst.getString("benefice")
	});
		}
	}
	catch(SQLException ex){
		
	}
	/////////////////////////////////////////////////////// total recette benefice par mois et produit
	DefaultTableModel df2=new DefaultTableModel();
	init2();
	df2.addColumn("Année");
	df2.addColumn("Mois");
	df2.addColumn("produit");
	df2.addColumn("Nombre");
	df2.addColumn("Recette");
	df2.addColumn("Bénéfice");
	tb2.setModel(df2);
	pn.add(scrl2);
String sql2="select annee,mois,produit,sum(nombre) as snombre,sum(prix_vente * nombre) as srecette,"
	 + "sum(prix_vente * nombre) - sum(prix_achat * nombre) as sbenefice" 
	 + " from vente inner join produit on vente.produit=produit.codeprod group by annee,mois,produit";
	
 con2=new Connectage();
	try{
		st2=con2.laconnexion().createStatement();
		rst2=st2.executeQuery(sql2);
		while(rst2.next()){
	df2.addRow(new Object[]{
			rst2.getString("annee"),
			rst2.getString("mois"),
			rst2.getString("produit"),
			rst2.getString("snombre"),
			rst2.getString("srecette"),
			rst2.getString("sbenefice")
	});
		}
	}
	catch(SQLException ex){
		
	}
	
	///////////////////////////////////////////////////////total recette benefice par mois
	DefaultTableModel df3=new DefaultTableModel();
	init3();
	df3.addColumn("Année");
	df3.addColumn("Mois");
	df3.addColumn("Recette");
	df3.addColumn("Bénéfice");
	tb3.setModel(df3);
	pn.add(scrl3);
String sql3="select annee,mois,sum(srecette) as trecette,sum(sbenefice) as tbenefice from produit_mois group by annee,mois";
	 
	
 con3=new Connectage();
	try{
		st3=con3.laconnexion().createStatement();
		rst3=st3.executeQuery(sql3);
		while(rst3.next()){
	df3.addRow(new Object[]{
			rst3.getString("annee"),
			rst3.getString("mois"),
			rst3.getString("trecette"),
			rst3.getString("tbenefice")
	});
		}
	}
	catch(SQLException ex){
		
	}
	
	//////////////////////////////////////////////////////
}
private void init(){
	tb1=new JTable();
	scrl1=new JScrollPane();
	scrl1.setViewportView(tb1);
	scrl1.setBounds(40,40,700,190);
}
private void init2(){
	tb2=new JTable();
	scrl2=new JScrollPane();
	scrl2.setViewportView(tb2);
	scrl2.setBounds(40,270,700,190);
}
private void init3(){
	tb3=new JTable();
	scrl3=new JScrollPane();
	scrl3.setViewportView(tb3);
	scrl3.setBounds(40,500,700,150);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Listes ls=new Listes();
		ls.setVisible(true);

	}

}
