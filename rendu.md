# Question 0
b) L'implémentaiton utilisée est celle proposée en exemple, étendue par nous-même. Elle applique l'algorithme *et* réalise les tests sur ses résultats (au lieu d'avoir un script de test qui appelle un programme implémentant l'algorithme, tout est réalisé par un seul programme).   
Les fichiers de l'implémentation d'origine sont présents tels quels, et nos ajouts se situent dans d'autres classes qui exploitent les précédentes.

## Partie Algo de Dijkstra 
**Entrées** : liste non-vide d'*arcs (orientés ou non) pondérés* (c'est à dire numéro de sommet de départ, numéro de sommet d'arrivée, poids ∈ ℝ) décrivant un graphe (l'existence d'un sommet est impliquée par l'existence d'un arc l'ayant comme extrémité), ainsi que le *numéro du sommet de départ* pour le calcul des distances, au format JSON*  
**Sortie** : *Distance* entre chaque sommet du graphe et le sommet de départ choisi, ("Distance" désignant la somme des poids des arcs parcourus par le chemin minimisant cette somme)

*structure attendue du JSON :   
Pour les données d'entrée  
```JSON
{
    "graphs" : [
        {
            "departDijkstra" : <numéro du sommet de départ de l'algo>,
            "estOriente" : <booléen>
            "chemins" : [
                {
                    "depart" : <numéro sommet de départ>,
                    "arrivée" : <numéro sommet d'arrivée>,
                    "poids" : <poids de l'arc (flottant)>
                } ,
                ...
            ]
        }
    ] 
}
```

## Partie Test
**Entrée** : Résultats attendus (oracles) pour l'application de l'algo aux données d'entrées spécifiées  
**Sortie** : résultat des tests.



Pour les résultats attendus :
```JSON
{
    "results": [
        "distances" : [...] <liste des distances attendues>
        "parents" : [...] <liste des parents de chaque sommets>
    ]
}
```


# Question 3

Dans la version originale de l'implémentation, qui nous est donnée dans le sujet, n'importe quelle suite de test passait par toutes les instructions, à l'exception de certains fonctions utilitaires qui ne sont jamais appelées.  

Cependant, dans notre version (qui ajoute un système de lecture d'entrée sous forme de fichier JSON, et la comparaison de la sortie avec la sortie attendue, ce qui permet l'automatisation des tests, là où avec l'implémentatation initiale les données d'entrée devaient se trouver dans le code directement), Certaines instructions ne pourront pas être éxécutées, notament celles liées au contrôle d'erreur : en effet, on trouve beaucoup de conditions de la forme "si erreur, afficher qque chose, et terminer le programme" : ainsi, s'il n'y a pas d'erreur les instructions d'affichage ne sont pas couvertes, s'il y a erreur le reste du programme n'est pas éxécuté.  
Autre exemple du même type, toutes les séquences de lecture/écriture de fichier impliquent l'utilisation d'un bloc try/catch : 
- si une exception est levée avant la fin du bloc try, celui-ci est interrompu, les dernières instructions ne sont donc pas couvertes
- si aucune exception n'est levée, le bloc catch n'est jamais éxécuté  
Par conséquent, tout bloc try/catch implique qu'une certaine portion de code ne sera pas éxécutée.  

Globalement il est impossible de passer par toutes les instructions du programme, cependant les instructions servant à réaliser l'algorithme en lui-même peuvent être toutes traversées par plus ou moins n'importe quelle suite de tests.  

# Todo
impossible de tester avec un autre type