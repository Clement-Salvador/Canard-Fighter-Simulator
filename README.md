# Canard Fighter Simulator

Ce projet a pour objectif de mettre en pratique des concepts avancés de programmation orientée objet en développant un simulateur de combat mettant en scène des canards légendaires aux types et capacités variés. Ce fichier readme sera emmené à disparaître dans le cadre du livrable.

---

## Objectifs

- Implémenter un simulateur de combat pour explorer des concepts avancés de la programmation orientée objet.
- Comprendre et utiliser l’héritage, le polymorphisme et la liaison dynamique.
- Développer une application modulaire et extensible en Java.

---

## Introduction

Dans ce projet, vous créerez un jeu de combat mettant en scène des canards dotés de différents types (Eau, Feu, Glace, Vent) et capacités spéciales. Chaque canard possède un nom, des points de vie (PV), des points d’attaque (PA) et une capacité spéciale activable une fois par bataille.

### Spécifications de Base

- **Attributs du canard :**
  - Nom
  - Type (Eau, Feu, Glace, Vent)
  - Points de Vie (PV)
  - Points d’Attaque (PA)
  - Capacité spéciale

- **Relations de forces et faiblesses :**
  - Eau > Feu
  - Feu > Glace
  - Glace > Vent
  - Vent > Eau

Les dégâts infligés lors des attaques sont calculés en tenant compte de ces forces et faiblesses.

---

## Instructions du Projet

### Étape 1 : Modélisation des Classes

1. Créer une classe de base `Canard` avec les accesseurs suivants :
   - `String getNom()`
   - `int getPointsDeVie()`
   - `int getPointsAttaque()`
   - `TypeCanard getType()`

2. Ajouter les méthodes suivantes :
   - `attaquer(Canard autreCanard)` : inflige des dégâts en fonction du type.
   - `subirDegats(int degats)` : réduit les PV du canard.
   - `boolean estKO()` : retourne vrai si le canard est hors combat (PV ≤ 0).

3. Implémenter des classes filles :
   - `CanardEau`
   - `CanardFeu`
   - `CanardGlace`
   - `CanardVent`

Chaque classe fille pourra redéfinir la capacité spéciale via une méthode `activerCapaciteSpeciale()`.

### Étape 2 : Gestion des Forces/Faiblesses

1. Définir un `enum TypeCanard` pour représenter les différents types.
2. Implémenter une méthode statique dans `TypeCanard` :
   - `double getMultiplicateur(TypeCanard attaquant, TypeCanard cible)` qui retourne :
     - `1.5` si l'attaque est efficace (forte),
     - `0.5` si l'attaque est désavantagée (faible),
     - `1.0` en cas de neutralité.
3. Modifier la méthode `attaquer` pour intégrer ce multiplicateur.

### Étape 3 : Interface Utilisateur Minimaliste

1. Créer une classe `Main` qui :
   - Utilise un `Scanner` pour permettre à l’utilisateur de créer des canards.
   - Affiche un menu proposant les options suivantes :
     - Créer des canards.
     - Lancer une bataille.
     - Quitter.
2. Permettre la simulation d’un combat tour par tour entre deux canards.

### Étape 4 : Tests Unitaires

- Ajouter des tests unitaires pour vérifier les interactions entre les différents types et pour tester les méthodes principales de la classe `Canard`.

---

## Questionnements sur la Modélisation

1. Quelles classes pourraient être abstraites ?
2. Quels comportements communs pourraient être définis dans une interface ?
3. Comment représenter un changement de statut (par exemple, brûlé ou paralysé) dans la modélisation ?
4. Quels seraient les avantages d’utiliser une classe ou une interface supplémentaire pour gérer les capacités spéciales ?
5. Quels défis sont associés à l’extensibilité de votre modèle pour ajouter de nouveaux types de canards ou de nouvelles capacités ?

---

## Fonctionnalités Avancées (Optionnelles)

### 1. Gestion Avancée des Combats

- **Effets de Statut :** Implémenter des effets persistants comme "brûlé" (perte de PV à chaque tour), "gelé" (inaptitude à agir pendant 2 tours) ou "paralysé" (capacité d'agir 50% du temps) via une méthode `appliquerEffets()`.
- **Points d’Énergie (PE) :** Limiter l’utilisation des attaques et capacités spéciales avec une ressource PE (par exemple, attaque = 5 PE, capacité spéciale = 15 PE).
- **Attaques Critiques :** Ajouter une probabilité (10%) pour infliger des dégâts critiques (2x les dégâts).

### 2. Types et Interactions Supplémentaires

- **Nouveaux Types de Canards :**
  - **Canard Électrique** : Neutralise les faiblesses (ignore les multiplicateurs de type lors d’une attaque).
  - **Canard Toxique** : Inflige un poison qui diminue les PV de l’adversaire de 5 PV par tour pendant 3 tours.
  - **Canard Sol** : Résistant aux attaques Électriques et Feu, mais faible face aux attaques Eau.

### 3. Personnalisation et Progression

- **Création Personnalisée :** Permettre au joueur de personnaliser le nom, le type, les PV et les PA du canard dans des limites prédéfinies.
- **Évolution :** Offrir la possibilité à un canard de gagner des statistiques ou de débloquer de nouvelles capacités après une victoire.
- **Objets Utilisables :** Intégrer des objets (comme des potions) pour restaurer les PV ou les PE.

---

## Livrables Attendus

1. **Code Source :**
   - Organisation claire avec des classes séparées et documentées (JavaDoc).
   - Utilisation de commits réguliers et normalisés dans le dépôt.

2. **README.md :**
   - Contient un diagramme UML des classes et une description des choix techniques.
   - Liste des réalisations bonus le cas échéant.

3. **Fichier HELPERS :**
   - Liste des personnes ayant apporté leur aide sur le projet, afin de reconnaître leur contribution.

---

## Exemple de Départ : Classe Main 

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans Canard Fighter Simulator !");
        System.out.println("1. Créer un canard");
        System.out.println("2. Lancer une bataille");
        System.out.println("3. Quitter");
        scanner.close();
    }
}
