Pour les deux dernière semaines , les pouvoirs pour les pokemons ont été implémenter au sein du code. Cela implique beaucoup de changement.

Premièrement, l'ajout de la classe abstraite Pouvoir et de ses sous-classes:
Pour gérer les pouvoirs de cartes Pokémon et leurs propriétées, la classe abstraite Pouvoir a été introduite. Les sous-classes SoinSimple,SoinTotale,AffinitéEther,AffinitéPlomb,Peur,FerveurGuerriere,resistance et pour finir kamikaze sont les différents pouvoirs qui ont été ajouter au code. Certains pouvoirs changent les éléments donc il a fallu hanger getAvantage qui été un String en List de pouvoir. Une description de chaque pouvoirs peuvent être afficher au moment de jouer les pouvoirs

Mise à jour des méthodes dans les classes existantes:
Les méthodes existantes ont été mises à jour pour refléter les nouveaux besoins du jeu. Par exemple, la méthode jouerTour dans la classe Joueur.JoueurOrdinateur a été changé pour que le joeur IA puisse attaquer correctement et jouer ses pouvoirs .

Puis nous avons répartie notre code en package pour avoir vue plus simple et plus clair du code.Notre est donc séparé en 5 partie:
-Element qui contient tous les élement des pokemons càd ( feu ,eau,terre,ether,etc...);
-Jouer qui contient notre main au sein de la classe pokemon et qui contient surtout la boucle des tours et le fonctionnement de la quasi-totalité du projet;
-Joueur qui contient la sorte de structure notre projet car a l'intérieur il y a les classes joueur ordinateur , dresseur et carte pokemon;
-Pouvoir qui contient environ la même chose qu'Element mais cette fois-ci pour tous les pouvoirs des pokemons;
-son qui contient le fichier audio de la musique de fond rajouté durant les deux dernière semaine;

Modification des relations dans le diagramme UML pour le dernier rendu:
Les relations entre les classes ont été revues pour mieux représenter les interactions entre les différents composants du jeu.

Organisation des fonctions du code:
Les fonctions du code ont été organisées de manière à faciliter la compréhension et la maintenance du code. Les méthodes de la classe Jouer.Jeu gèrent la logique du jeu donc certaines modifications ont du y être ajouter pour permettre aux pouvoirs d'être afficher et surtout jouer, et celles de la classe Joueur.CartePokemon gèrent les propriétés et les actions des cartes Pokémon donc l'ajout d'un pouvoir au hasard voir aucun ont du être mis dans la génération d'un pokemon.

Pour conclure tous ce qui a été demandé a été mis dans le projet.