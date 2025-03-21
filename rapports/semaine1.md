L'objectif de ce rapport est d'expliquer pourquoi le code est organisé de cette manière.

Le diagramme UML représente les différentes classes du jeu et leurs relations. Tout d'abord, la classe Jouer.Pokemon ne contient qu'une méthode main, ce qui signifie qu'elle sert uniquement de point d'entrée pour l'exécution du programme. 
Ensuite, la classe Jouer.Jeu représente la logique du jeu, avec des méthodes pour générer des Pokémon, jouer un tour, et obtenir le joueur adverse. 
La classe Joueur.Dresseur représente un joueur, avec des attributs pour son nom, sa main, sa pioche, sa défausse et son terrain, ainsi que des méthodes pour piocher des cartes, jouer des cartes, et afficher sa main, sa pioche et sa défausse. 
Enfin, la classe Joueur.CartePokemon représente une carte Pokémon, avec des attributs pour son nom, son affinité, sa vie, sa vie maximale et son attaque, ainsi que des méthodes pour attaquer d'autres cartes et afficher la carte.
La classe Element.Type est une classe abstraite qui représente le type d'une carte Pokémon. Les classes Element.Air, Element.Eau, Element.Feu et Element.Terre héritent de la classe Element.Type et implémentent la méthode getAvantage(). Cette organisation permet de représenter les différents types de cartes Pokémon et leurs avantages respectifs de manière claire et extensible.

En termes de relations, la classe Joueur.Dresseur a une association avec la classe Joueur.CartePokemon, ce qui signifie qu'un Joueur.Dresseur peut avoir plusieurs cartes Pokémon dans sa main, sa pioche, sa défausse et son terrain. De même, la classe Jouer.Jeu a une association avec la classe Joueur.CartePokemon, ce qui signifie qu'un Jouer.Jeu peut contenir plusieurs cartes Pokémon.

Les fonctions du code sont organisées de manière à faciliter la compréhension et la maintenance du code. Tout d'abord, les méthodes de la classe Jouer.Jeu sont conçues pour gérer la logique du jeu, telles que la génération de Pokémon et le déroulement d'un tour. Ensuite, les méthodes de la classe Joueur.Dresseur sont conçues pour gérer les actions d'un joueur, telles que piocher des cartes et jouer des cartes. Enfin, les méthodes de la classe Joueur.CartePokemon sont conçues pour gérer les propriétés et les actions d'une carte Pokémon, telles que l'attaque et l'affichage de la carte.

Durant la semaine nous implémenterons la fonctionnalité de choisir qui est ce que l'on attaque avec quel pokemon et surtout nous mettrons en place le joueur Ordinateur qui contient déja quelques méthodes test.

Si à la semaine trois il restera du temps nous allons surement revoir le rendu graphiques .