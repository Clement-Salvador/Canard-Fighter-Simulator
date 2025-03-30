# Canard Fighter Simulator

Canard Fighter Simulator est un mini-projet développé en Java permettant de simuler des combats entre canards de différents types (Feu, Eau, Glace, Vent). Chaque canard possède ses propres points de vie, points d’attaque, une capacité spéciale, ainsi qu’un système de points d’énergie (PE).

---

## Choix techniques et justifications

**Effets de Statut :**  
Les effets de statut (brûlure, gel) ont été intégrés pour simuler des altérations temporaires classiques dans les jeux de combat ou RPG. Chaque effet a une durée d’un tour. 

Il était demandé d'intégrer des effets de statuts qui entraient en contradiction avec les effets des canards. Nous n'avons donc pas modifié la logique des attaques spéciales.

**Points d’Énergie (PE) :**  
Chaque canard commence avec 50 points d’énergie.  
Les actions consomment de l’énergie :
- Une attaque normale coûte 5 PE.
- Une capacité spéciale coûte 15 PE.

**Attaques Critiques :**  
À chaque attaque, il y a 10 % de chances que celle-ci soit critique. Une attaque critique inflige deux fois plus de dégâts.  

---

## Réponses aux questions de modélisation

1. **Quelles classes pourraient être abstraites ?**  
La classe `Canard` doit être abstraite, car elle représente un concept générique de canard. Elle ne doit pas être instanciée directement, mais uniquement via des sous-classes concrètes (`CanardFeu`, `CanardEau`, etc.) qui définissent les comportements spécifiques.

2. **Quels comportements communs pourraient être définis dans une interface ?**  
Les effets de statut pourraient être gérés à travers une interface commune, par exemple `EffetStatut`, avec des méthodes comme `appliquerEffet(Canard cible)` ou `estActif()`.  
De même, une interface `CapaciteSpeciale` pourrait définir un contrat standard pour toute capacité (coût en PE, activation, effet).

3. **Comment représenter un changement de statut (par exemple, brûlé ou paralysé) dans la modélisation ?**  
Une bonne approche consiste à créer une classe `Effet` ou une hiérarchie de classes (`EffetBrulure`, `EffetGel`, etc.), qu’on associerait dynamiquement à un canard.  
Chaque canard aurait alors une liste ou un attribut représentant ses statuts actifs, avec des méthodes pour appliquer, vérifier ou supprimer ces effets à chaque tour.

4. **Quels seraient les avantages d’utiliser une classe ou une interface supplémentaire pour gérer les capacités spéciales ?**  
Cela permettrait de séparer la logique métier (le comportement d’une capacité) de la structure des canards. On pourrait facilement ajouter de nouvelles capacités, les réutiliser entre types, ou même permettre à un canard de changer de capacité.  
C’est aussi plus conforme aux principes SOLID, notamment celui de la responsabilité unique et celui de l’extension ouverte.

5. **Quels défis sont associés à l’extensibilité de votre modèle pour ajouter de nouveaux types de canards ou de nouvelles capacités ?**  
Si les effets, types, et capacités sont codés en dur dans les classes, cela devient vite difficile à maintenir.  
Chaque ajout nécessiterait une nouvelle sous-classe, des modifications dans les comparaisons de types, etc.  
Pour mieux évoluer, il faudrait isoler les règles dans des systèmes modulaires (ex: map de multiplicateurs, classes `Capacite`, etc.).  
Cela éviterait une explosion du nombre de classes ou de conditions.

---

## Description des choix techniques réalisés

- **Langage** : Java  
- **Paradigme utilisé** : Programmation orientée objet (POO)  
- **Types élémentaires** : Gérés par un `enum` `TypeCanard`  
- **Polymorphisme** : La méthode `activerCapaciteSpeciale(Canard cible)` est définie abstraitement dans `Canard` et redéfinie dans chaque sous-classe.  
- **Attaques** : La méthode `attaquer()` applique les multiplicateurs de type, le coût en PE, et la probabilité de coup critique.  
- **Effets spéciaux** : Brûlure, gel, et bonus d’attaque sont codés dans les sous-classes, sous forme d’attributs booléens ou multiplicateurs.  
- **Interface utilisateur** : Le programme fonctionne via une boucle console interactive simple.  
- **Extensibilité** : Limitée à ce stade, car les effets et types sont intégrés directement dans les sous-classes. Une refactorisation orientée "comportements objets" (capacité, effet) serait plus évolutive.

---

## Structure du projet

    src/
    ├── Canard.java                → Classe abstraite principale
    ├── CanardEau.java             → Sous-classe Eau
    ├── CanardFeu.java             → Sous-classe Feu
    ├── CanardGlace.java           → Sous-classe Glace
    ├── CanardVent.java            → Sous-classe Vent
    ├── TypeCanard.java            → Enum des types + gestion des multiplicateurs
    ├── Main.java                  → Interface console et logique de combat

---

## Lancement du programme

1. Compiler tous les fichiers depuis le terminal :

       javac *.java

2. Exécuter le programme :

       java Main

--- 

## Auteurs : 

- Clément SALVADOR
- Guillaume POMIÈS
