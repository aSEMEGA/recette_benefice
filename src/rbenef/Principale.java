/*Application réalisée du 21 au 23 Juin 2020 à N'djaména au Tchad par
 *TARGOTO CHRISTIAN
 *Contact : 23560316682 / ct@chrislink.net */
package rbenef;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

public class Principale extends JFrame {
	Statement st;
	Connectage cn=new Connectage();
	JLabel lb1,lbprod,lban,lbmois,lbjour,lbnb;
	JTextField tfan,tfjour,tfnb;
	JButton bt_enrg,bt_supp,bt_liste;
	JComboBox comboprod,combomois;
	public Principale(){
		this.setTitle("chcode_appli");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(0,200,200));
		add(pn);
		lb1=new JLabel("Enregistrement des produits du centre commercial vendus durant la journée");
		lb1.setBounds(40,20,800,30);
		lb1.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lb1);
		//formulaire
		   //produit
		lbprod=new JLabel("Produit :");
		lbprod.setBounds(280,60,90,30);
		lbprod.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbprod);
		
		comboprod=new JComboBox();
		comboprod.addItem("Ordinateur");
		comboprod.addItem("Smartphone");
		comboprod.addItem("Tablette");
		comboprod.setBounds(350,60,100,25);
		pn.add(comboprod);
		
		    //année
				lban=new JLabel("Année :");
				lban.setBounds(285,100,90,30);
				lban.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lban);
				
				tfan=new JTextField("2020");
				tfan.setBounds(350,100,100,25);
				pn.add(tfan);
				//mois
				lbmois=new JLabel("Mois :");
				lbmois.setBounds(297,140,90,30);
				lbmois.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbmois);
			
				
				combomois=new JComboBox();
				combomois.addItem("Janvier");
				combomois.addItem("Fevrier");
				combomois.addItem("Mars");
				combomois.addItem("Avril");
				combomois.addItem("Mai");
				combomois.addItem("Juin");
				combomois.addItem("Juillet");
				combomois.addItem("Aout");
				combomois.addItem("Septembre");
				combomois.addItem("Octobre");
				combomois.addItem("Novembre");
				combomois.addItem("Decembre");
				combomois.setBounds(350,140,100,25);
				pn.add(combomois);
				//jour
				lbjour=new JLabel("Jour :");
				lbjour.setBounds(297,180,90,30);
				lbjour.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbjour);
				
				tfjour=new JTextField();
				tfjour.setBounds(350,180,100,25);
				pn.add(tfjour);
				//nombre
				lbnb=new JLabel("Nombre :");
				lbnb.setBounds(275,220,90,30);
				lbnb.setFont(new Font("Arial",Font.BOLD,15));
				pn.add(lbnb);
				
				tfnb=new JTextField();
				tfnb.setBounds(350,220,100,25);
				pn.add(tfnb);
		        //bounton 
				//bouton d'enregistrement
				bt_enrg=new JButton("Enregistrer");
				bt_enrg.setBounds(320,260,100,25);
				bt_enrg.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a,b,c,d,f;
						a=comboprod.getSelectedItem().toString();
						b=tfan.getText();
						c=combomois.getSelectedItem().toString();
						d=tfjour.getText();
						f=tfnb.getText();
				String sq="insert into vente(produit,annee,mois,jour,nombre) values('"+a+"','"+b+"','"+c+"','"+d+"','"+f+"') ";
				  try{
					  st=cn.laconnexion().createStatement();
					  if(JOptionPane.showConfirmDialog(null,"Voulez-vous ajoutez ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						   st.executeUpdate(sq);
						   JOptionPane.showMessageDialog(null,"Insertion reussie !");
					  }
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null," Echec Insertion !",null,JOptionPane.ERROR_MESSAGE);
					  
				  }
						
					}

					
				});
				pn.add(bt_enrg);
				//bouton de suppréssion
				bt_supp=new JButton("Supprimer");
				bt_supp.setBounds(320,300,100,25);
				bt_supp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String a,b,c,d,f;
						//a=comboprod.getSelectedItem().toString();
						b=tfan.getText();
						c=combomois.getSelectedItem().toString();
						d=tfjour.getText();
						//f=tfnb.getText();
				String sq="delete from vente where annee='"+b+"' and mois='"+c+"' and jour='"+d+"' ";
				  try{
					  st=cn.laconnexion().createStatement();
					  if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						   st.executeUpdate(sq);
						   JOptionPane.showMessageDialog(null,"Suppréssion reussie !");
					  }
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null," Echec suppréssion !",null,JOptionPane.ERROR_MESSAGE);
					  
				  }
						
					}

					
				});
				pn.add(bt_supp);
				//bouton liste
				bt_liste=new JButton("Requetes");
				bt_liste.setBounds(320,340,100,25);
				bt_liste.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
						Listes ls=new Listes();
						ls.setVisible(true);
					}

					
				});
				pn.add(bt_liste);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 Principale pr=new Principale();
      pr.setVisible(true);
	}

}
