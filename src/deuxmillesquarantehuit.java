import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class deuxmillesquarantehuit extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel[][] casse;//=case 
	private JLabel logo;
	private JPanel entete;
	private JPanel grille;
	private int[][] tabInt;
	private int nbLigneTab=4;
	private int nbColonneTab=4;
	
	public deuxmillesquarantehuit(){
	    this.setTitle("2048");
	    this.setSize(600, 800);
	    this.setLayout(new GridLayout(2,1));
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
		entete.setSize(600, 200);
		entete.setLayout(null);
		entete.setBounds(0, 0, 600, 200);
		entete.setOpaque(true);
		entete.setBackground(Color.white);
		entete.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(entete);
		
		logo = new JLabel("2048");
		entete.add(logo);
		logo.setLayout(null);
		logo.setBounds(10,10,100,100);
		logo.setOpaque(true);
		logo.setBackground(Color.yellow);
		logo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		return entete;
	}
	
	public Container grille()
	{
		grille  = new JPanel();
		grille.setSize(600, 600);
		getContentPane().add(grille);
		grille.setLayout(new GridLayout(4,4,2,2));
		casse = new JLabel[4][4];
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				casse[i][j] = new JLabel("");
				casse[i][j].setOpaque(true);
				casse[i][j].setBackground(Color.white);
				casse[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				casse[i][j].setFont(new Font("Arial", Font.BOLD, 50));
				grille.add(casse[i][j]);
			}
		}
		casse[1][0].setText("2");
		casse[2][0].setText("2");
		casse[1][3].setText("2");
		casse[0][1].setText("2");
		casse[3][1].setText("2");
		
		return grille;
	}
	
	public void init()
	{
		for(int i = 0 ; i < nbLigneTab ; i++) 
		{
			for(int j = 0 ; j < nbColonneTab ; j++)
			{
				tabInt[i][j]=0;
			}
		}
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
			
			if (tabInt[ligne][col] != 0)
			{
				verif = (false);
			}
			else
			{
				tabInt[ligne][col] = i;
				verif = (true);
			}
		}while(verif == (false) && cpt != 16);
	}
	
	public Container depBas()
	{
		
		int a=1;
		boolean rempli = (false);
		for(int i = nbLigneTab-1 ; i == 0 ; i--) 
		{
			for(int j = 0 ; j == nbColonneTab-1 ; j++)
			{
				a=1;
				if (casse[i][j].getText() != ("") )
				{
					rempli= (false);
				}
				while (rempli == (true) || a == i)
				{
					if (casse[i-a][j].getText() == ("") )
					{
						casse[i][j].setText(casse[i-a][j].getText());
						casse[i-a][j].setText("");
						rempli=(true);
					}
					a=a+1;
				}
			}
		}
		
		/*casse[3][0].setText(casse[2][0].getText());
		casse[2][0].setText("");*/
		
		return grille;
	}
	
	public void jeu()
	{
		addKeyListener(this);
		
	}
	
	public void keyReleased(KeyEvent event) {
		
		int renvoie = event.getKeyCode();
		/*int gauche=37;
		int haut=38;
		int droite=39;*/
		int bas=40;
		
		if (renvoie == bas)
		{
			depBas();			
		}
		/*else
			if (renvoie == droite)
			{
				depDroite();
			}
			else 
				if (renvoie == gauche)
				{
					depGauche();
				}
				else
					if (renvoie == haut)
					{
						depHaut();
					}*/	
	};
	//Inutile de redéfinir ces méthodes, nous n'en avons plus l'utilité !
	public void keyPressed (KeyEvent event) {};
	public void keyTyped (KeyEvent event) {};
	
	
	public static void main(String[] args) {
	
		new deuxmillesquarantehuit();
			
	}
}
