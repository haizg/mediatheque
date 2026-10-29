

| # | Principe | Ligne(s) | Explication |
|---|----------|----------|-------------|
| 1 | SRP | `genererRapport` entière | La méthode génère le contenu, écrit sur disque et notifie : 3 raisons de changer. |
| 2 | OCP | `if (format.equals("CSV")) ... else if ("TXT")` | Ajouter un format (JSON) oblige à modifier la méthode existante. |
| 3 | DIP | `new FileWriter("C:\\rapports\\rapport.txt")` | Le métier dépend directement du disque, avec un chemin en dur, donc non testable. |
| 4 | DIP | `new ServeurSmtp("smtp.itbs.tn")` | Le métier construit lui-même un détail technique : test impossible sans vrai serveur mail. |
| 5 | Qualité | `catch (IOException e) { }` | Exception avalée : l'échec est silencieux et le mail « Rapport généré » part quand même. |
| 6 | Qualité | `contenu += ...` dans une boucle | Concaténation de `String` en boucle, à remplacer par `StringBuilder`. |
| 7 | Qualité | `throw new RuntimeException("Format inconnu")` | Format passé en `String` libre et exception trop générique. |


```
RapportService --> FormatRapport  <|.. FormatCsv, FormatTxt, FormatJson
               --> SortieRapport  <|.. SortieFichier
               --> Notificateur   <|.. NotificateurSmtp --> ServeurSmtp
```

`RapportService` ne connaît que les 3 interfaces, reçues par constructeur.
Les classes concrètes sont assemblées uniquement dans `Main`.


Fichiers existants modifiés : 0. Seul `FormatJson.java` est ajouté.