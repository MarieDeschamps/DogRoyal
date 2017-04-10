# DogRoyal

## Jeu

Le Dog Royal est un jeu proche des petits chevaux qui se joue avec des cartes plutôt que des dés.

### Plateau
* 16 cases par joueur dont une première case pour les entrées et sortie des pions.
* Une case sortie par pion par joueur.

### Deck
* 56 cartes de déplacement : 
	* 7 cartes de : 
		* 2
		* 3
		* 5
		* 6
		* 8
		* 9
		* 10
		* 12
* 20 cartes de sortie de pion
* 34 cartes à effet (non implémentées pour le moment)

## Base de données
Enregistrement de la partie dans deux tables : 
* table contenant :
	* la liste des cartes 
	* leur statut (présent dans la pioche)
	* si dans une main de joueur.
* table contenant :
	* la liste des pions (avec un id)
	* le joueur le possédant
	* la position du pion 
	* la situation du pion dans le jeu(sorti ou non)

## Structure du projet
### Objet Joueur
    Liste<pions>
    int numéro
    String couleur
    case finale ()
    Main
    Select(pion)
    pionMêmeCase()
### Objet Main
    pick()
    pick(5)
    selectCard()
### Objet pion
    sorti?
    int position
    rentré()
### Objet Deck
    List<card>
    card pick()
    List<card> pick(5)
    generate()
    List<card> //(défausse)
    Mix()
    addToThrow()
### Objet Carte
    boolean AllowsExit
    number()
### Objet Pion
    position
    id
    onBoard()
