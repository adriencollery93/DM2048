import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;*/
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class deuxmillesquarantehuit extends JFrame implements KeyListener /*, ActionListener*/{
	
	private static final long serialVersionUID = 1L;
	private JLabel[][] casse;//=case 
	private JLabel logo;
	private JLabel score;
	private JPanel entete;
	private JPanel grille;
	/*private JButton newPartie;*/
	private int[][] tabEnt;
	private int nbLigneTab=4;
	private int nbColonneTab=4;
	private int scoreEnt;
	private Boolean deplacement = null;
	
	public deuxmillesquarantehuit(){
	    this.setTitle("2048");
	    this.setSize(600, 800);
	    this.setLayout(null);
	    this.setLocationRelativeTo(null);//Nous demandons maintenant à notre objet de se positionner au centre
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    entete();
	    grille();
	    jeu();
	    
	    this.setVisible(true);
	}
	
	public Container entete()
	{
		entete = new JPanel();
		entete.setSize(594, 200);
		entete.setLayout(null);
		entete.setOpaque(true);
		entete.setBackground(Color.white);
		entete.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(entete);
		
		logo = new JLabel("2048", SwingConstants.CENTER);
		entete.add(logo);
		logo.setFont(new Font("Arial", Font.BOLD, 25));
		logo.setLayout(null);
		logo.setBounds(10,10,100,100);
		logo.setOpaque(true);
		logo.setBackground(Color.yellow);
		logo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/*bouton();*/
		
		score = new JLabel("", SwingConstants.CENTER);
		entete.add(score);
		score.setFont(new Font("Arial", Font.BOLD, 25));
		score.setLayout(null);
		score.setBounds(300,10,150,100);
		score.setOpaque(true);
		score.setBackground(Color.white);
		score.setBorder(BorderFactory.createLineBorder(Color.black));
		
		return entete;
	}
	
	public Container grille()
	{
		grille  = new JPanel();
		grille.setBounds(0, 200, 595, 570);
		/*grille.setSize(600, 600);*/
		getContentPane().add(grille);
		grille.setLayout(new GridLayout(4,4,2,2));
		casse = new JLabel[4][4];
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				casse[i][j] = new JLabel("", SwingConstants.CENTER);
				casse[i][j].setOpaque(true);
				casse[i][j].setBackground(Color.white);
				casse[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				casse[i][j].setFont(new Font("Arial", Font.BOLD, 50));
				grille.add(casse[i][j]);
			}
		}
		
		return grille;
	}
	
	/*public void bouton() 
	{
		newPartie = new JButton ("Nouvelle partie");
		newPartie.setLayout(null);
		newPartie.setBounds(150, 10, 150, 100);
		newPartie.setOpaque(true);
		entete.add(newPartie);
		
		newPartie.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				debutPartie();				
			}
		}
		);
		
	}*/
	
	public void debutPartie()
	{
		init();
		genererAleatoire();
		genererAleatoire();
		incrementation();
	}
	
	public void gagner()
	{
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				if (tabEnt[i][j] == 2048)
				{
					JFrame fenetre = new JFrame();
					fenetre.setSize(400, 100);
					JLabel gagne = new JLabel("Vous venez de faire 2048, félicitation!!!!");
				    fenetre.add(gagne);
				    fenetre.setLocationRelativeTo(null);
				    fenetre.setResizable(false);
				    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					fenetre.setVisible(true);
				}
			}
		}
	}
	
	public void perdu()
	{
		int cpt= 0;
		boolean perdu= false;
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				if (tabEnt[i][j] != 0)
				{
					cpt += 1;
				}
			}
		}
		if ( cpt == 16)
		{
			for (int j = 0 ; j < nbColonneTab ; j++)
			{
				for (int i = nbLigneTab-2 ; i >= 0 ; i--) 
				{
					if ( tabEnt[i][j] == tabEnt[i+1][j] )
					{
						perdu = false;
					}
					else
					{
						perdu = true;
					}
				}
			}
			for (int j = 0 ; j < nbColonneTab ; j++)
			{
				for (int i = 1 ; i < nbLigneTab ; i++)
				{
					if ( tabEnt[i][j] == tabEnt[i-1][j] )
					{
						perdu = false;
					}
					else
					{
						perdu = true;
					}
				}
			}
			for (int i = 0 ; i < nbLigneTab ; i++)
			{
				for (int j = 1 ; j < nbColonneTab ; j++)
				{
					if ( tabEnt[i][j] == tabEnt[i][j-1] )
					{
						perdu = false;
					}
					else
					{
						perdu = true;
					}
				}
			}
			for (int i = 0 ; i < nbLigneTab ; i++)
			{
				for (int j = nbColonneTab-2 ; j >= 0 ; j--)
				{
					if ( tabEnt[i][j] == tabEnt[i][j+1] )
					{
						perdu = false;
					}
					else
					{
						perdu = true;
					}
				}
			}
		}
		if (perdu == true )
		{
			JFrame fenetre = new JFrame();
			fenetre.setSize(500, 100);
			JLabel fini = new JLabel("Vous avez perdu, cliquez sur le bouton nouvelle partie pour recommencer!!");
		    fenetre.add(fini);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			fenetre.setVisible(true);
		}
	}
	
	public void init()
	{
		tabEnt = new int[nbLigneTab][nbColonneTab];
		
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				tabEnt[i][j]=0;
			}
		}
		
		scoreEnt=0;
		/*tabEnt[1][0]=2;
		tabEnt[3][2]=2;
		tabEnt[1][2]=2;
		tabEnt[2][1]=2;
		tabEnt[0][3]=2;
		tabEnt[2][3]=2;*/
	}
	
	public void genererAleatoire()
	{
		boolean verif = (false);
		int cpt=0;
		do
		{
			long colAleatoire = Math.round(Math.random()*3); //random() renvoie un nombre aléatoire compris entre 0.0 et 1.0.
			long ligneAleatoire = Math.round(Math.random()*3); //round() ajoute 0,5 à l'argument et restitue la plus grande valeur entière (int) inférieure ou égale au résultat
			int col = (int)colAleatoire; //On parse
			int ligne = (int)ligneAleatoire;
			long nbAleatoire1 = Math.round(Math.random()*4);
			int i = (int)nbAleatoire1;
			
			while(verif == (false))
			{
				if(nbAleatoire1 == 0 || nbAleatoire1 == 1 || nbAleatoire1 == 3)
				{
					nbAleatoire1 = Math.round(Math.random()*4);
					i = (int)nbAleatoire1;
				}
				else
					verif = (true);
			}
			
			if (tabEnt[ligne][col] != 0)
			{
				verif = (false);
			}
			else
			{
				tabEnt[ligne][col] = i;
				verif = (true);
			}
		}while(verif == (false) && cpt != 16);
	}
	
	public void incrementation()
	{
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				if(tabEnt[i][j] == 0)
				{
					casse[i][j].setText("");
				}
				else
				{
					casse[i][j].setText( String.valueOf(tabEnt[i][j]));
				}
			}
		}
		score.setText(String.valueOf(scoreEnt));
	}
	
	public void depBas()
	{
		for (int j = 0 ; j < nbColonneTab ; j++)
		{
			for (int i = nbLigneTab-2 ; i >= 0 ; i--) // on va de l'avant dernière ligne vers la première
			{
				int a;
				if (tabEnt[i][j] != 0)
				{
					a = i + 1; // la ligne au dessus de la ligne actuelle
					while ( a < nbLigneTab && tabEnt[a][j] == 0) // tant que la ligne suivante est vide
					{
						tabEnt[a][j]= tabEnt[a-1][j];
						tabEnt[a-1][j]= 0;
						a=a+1;
					}
				}
			}
		}
	}
	
	public void calculBas()
	{	
		deplacement = false;
		for (int j = 0 ; j < nbColonneTab ; j++)
		{
			for (int i = nbLigneTab-2 ; i >= 0 ; i--) 
			{
				if ( tabEnt[i][j] == tabEnt[i+1][j] )
				{
					tabEnt[i+1][j]= tabEnt[i+1][j] + tabEnt[i][j];
					tabEnt[i][j]=0;
					scoreEnt+=tabEnt[i+1][j] + tabEnt[i][j];
					deplacement = true;
				}
			}
		}
		if (deplacement == true)
		{
			genererAleatoire();
		}
		
	}
	
	public void depHaut()
	{
		for (int j = 0 ; j < nbColonneTab ; j++)
		{
			for (int i = 1 ; i < nbLigneTab ; i++)
			{
				int a;
				if ( tabEnt[i][j] != 0) 
				{
					a = i - 1;// la ligne au dessous de la ligne actuelle
					while ( a>=0 && tabEnt[a][j] == 0 )
					{
						tabEnt[a][j] = tabEnt[a+1][j]; 
						tabEnt[a+1][j]= 0;
						a=a-1;
					}
				}
			}	
		}
	}
	
	public void calculHaut()
	{
		deplacement = false;
		for (int j = 0 ; j < nbColonneTab ; j++)
		{
			for (int i = 1 ; i < nbLigneTab ; i++)
			{
				if ( tabEnt[i][j] == tabEnt[i-1][j] )
				{
					tabEnt[i-1][j]= tabEnt[i-1][j] + tabEnt[i][j];
					tabEnt[i][j]=0;
					scoreEnt+= tabEnt[i-1][j] + tabEnt[i][j];
					deplacement = true;
				}
			}
		}
		if (deplacement == true)
		{
			genererAleatoire();
		}
	}
	
	public void depGauche()
	{
		for (int i = 0 ; i < nbLigneTab ; i++)
		{
			for (int j = 1 ; j < nbColonneTab ; j++)
			{
				int a;
				if ( tabEnt[i][j] != 0) 
				{
					a = j - 1;
					while ( a>=0 && tabEnt[i][a] == 0 )
					{
						tabEnt[i][a] = tabEnt[i][a+1]; 
						tabEnt[i][a+1]= 0;
						a=a-1;
					}
				}
			}	
		}
	}
	
	public void calculGauche()
	{
		deplacement = false;
		for (int i = 0 ; i < nbLigneTab ; i++)
		{
			for (int j = 1 ; j < nbColonneTab ; j++)
			{
				if ( tabEnt[i][j] == tabEnt[i][j-1] )
				{
					tabEnt[i][j-1]= tabEnt[i][j-1] + tabEnt[i][j];
					tabEnt[i][j]=0;
					scoreEnt+= tabEnt[i][j-1] + tabEnt[i][j];
					deplacement = true;
				}
			}
		}
		if (deplacement == true)
		{
			genererAleatoire();
		}
	}

	public void depDroite()
	{
		for (int i = 0 ; i < nbLigneTab ; i++)
		{
			for (int j = nbColonneTab-2 ; j >= 0 ; j--)
			{
				int a;
				if (tabEnt[i][j] != 0)
				{
					a = j + 1; 
					while ( a < nbColonneTab && tabEnt[i][a] == 0) 
					{
						tabEnt[i][a]= tabEnt[i][a-1];
						tabEnt[i][a-1]= 0;
						a=a+1;
					}
				}
			}
		}
	}

	public void calculDroite()
	{
		deplacement = false;
		for (int i = 0 ; i < nbLigneTab ; i++)
		{
			for (int j = nbColonneTab-2 ; j >= 0 ; j--)
			{
				if ( tabEnt[i][j] == tabEnt[i][j+1] )
				{
					tabEnt[i][j+1]= tabEnt[i][j+1] + tabEnt[i][j];
					tabEnt[i][j]=0;
					scoreEnt += tabEnt[i][j+1] + tabEnt[i][j];
					deplacement = true;
				}
			}
		}
		if (deplacement == true)
		{
			genererAleatoire();
		}
	}

	public void couleur()
	{
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				switch (tabEnt[i][j])
				{
					case 0: casse[i][j].setBackground(Color.white);
					break;			
					case 2: casse[i][j].setBackground(Color.cyan);
					break;					
					case 4: casse[i][j].setBackground(Color.green);
					break;
					case 8: casse[i][j].setBackground(Color.lightGray);
					break;
					case 16: casse[i][j].setBackground(Color.magenta);
					break;
					case 32: casse[i][j].setBackground(Color.orange);
					break;
					case 64: casse[i][j].setBackground(Color.pink);
					break;
					case 128: casse[i][j].setBackground(Color.red);
					break;
					case 256: casse[i][j].setBackground(Color.blue);
					break;
					case 512: casse[i][j].setBackground(Color.yellow);
					break;
					case 1024: casse[i][j].setBackground(Color.gray);
					break;
					case 2048: casse[i][j].setBackground(Color.DARK_GRAY);
					break;
				}			
			}
		}
	}
	
	public void jeu()
	{
		/*bouton();*/
		debutPartie();
		addKeyListener(this);	
	
	}
	
	public void keyReleased(KeyEvent event) {
		
		switch (event.getKeyCode())
		{
			case 37:
				depGauche();
				calculGauche();
				depGauche();
				incrementation();
			break;
			
			case 38:
				depHaut();
				calculHaut();
				depHaut();
				incrementation();
			break;
			
			case 39:
				depDroite();
				calculDroite();
				depDroite();
				incrementation();
			break;
			
			case 40:
				depBas();
				calculBas();
				depBas();
				incrementation();
			break;	
		}
		gagner();
		perdu();	
		couleur();
	};
	public void keyPressed (KeyEvent event) {};
	public void keyTyped (KeyEvent event) {};
	
	
	public static void main(String[] args) {
	
		new deuxmillesquarantehuit();
			
	}
	
}
