/*
public class brouillon {

	public void depBas()
	{
	   for(int i = nbLigneTab-1 ; i >= 0  ; i--) 
		   bougeLigne(i);
	}

	private void bougeLigne(int i)
	{
		for(int j = 0 ; j < nbColonneTab ; j++) 
			bougeCase(i, j);
	}

	private void bougeCase(int i, int j)
	{
	    int a = 1;
		while (tabEnt[i][j] == 0 || a <= 1)
		{
			System.out.println("[" + i + "][" + j + "]=[" + tabEnt[i][j] + "]");
			
			if (tabEnt[i-a][j] != 0 )// erreur provenant d'ici
			{				
				tabEnt[i][j]=tabEnt[i-a][j];
				tabEnt[i-a][j]=0;
			}
			else
			{
				a=a+1;
			}
		}
	}
	
}
*/
	/*public void depBas()
	{
		int a=1;
				
		for(int i = nbLigneTab-1 ; i >= 0  ; i--) 
		{
			for(int j = 0 ; j <= nbColonneTab-1 ; j++)
			{		
				System.out.println("[" + i + "][" + j + "]=[" + tabEnt[i][j] + "]");
				a=1;
				while (tabEnt[i][j] == 0 || a <= 1)
				{
					System.out.println("[" + i + "][" + j + "]=[" + tabEnt[i][j] + "]");
					
					if (tabEnt[i-a][j] != 0 )
					{				
						tabEnt[i][j]=tabEnt[i-a][j];
						tabEnt[i-a][j]=0;
					}
					else
					{
						a=a+1;
					}
				}
			}
		}
		
		tabEnt[3][0]=tabEnt[1][0];
		tabEnt[1][0]=0;
		
	}*/


/*int renvoie = event.getKeyCode();
		int gauche=37;
		int haut=38;
		int droite=39;
		int bas=40;
		
		if (renvoie == bas)
		{
			depBas();
			calculBas();
			depBas();
			incrementation();
			genererAleatoire();
			incrementation();
		}
		else
			if (renvoie == droite)
			{
				depDroite();
				calculDroite();
				depDroite();
				incrementation();
			}
			else 
				if (renvoie == gauche)
				{
					depGauche();
					calculGauche();
					depGauche();
					incrementation();
					genererAleatoire();
					incrementation();
				}
				else
					if (renvoie == haut)
					{
						depHaut();
						calculHaut();
						depHaut();
						incrementation();
						genererAleatoire();
						incrementation();
					}*/