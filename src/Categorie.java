


/**
 * 
 */
public class Categorie {
    public static Integer VETEMENT = 1;
    public static Integer CHAUSSURE = 5;
    public static Integer ACCESSOIRE = 6;
    public static Integer ELECTROMENAGER = 7;
    public static Integer INFORMATIQUE = 8;
    public static Integer JEUX = 9;
    public static Integer LIVRE = 10;
    public static Integer MUSIQUE = 11;
    public static Integer SPORT = 12;
    public static Integer VEHICULE = 13;
    public static Integer USTENSILECUISINE = 2;
    public static Integer MEUBLE = 3;
    public static Integer OUTIL = 4;


    public static Integer getIntCategorie(String cate) {
    Integer intCategorie = null;
    switch (cate) {
        case "Vêtement":
            intCategorie = 1;
            break;
        case "Chaussure":
            intCategorie = 5;
            break;
        case "Electromenager":
            intCategorie = 7;
            break;
        case "Informatique":
            intCategorie = 4;
            break;
        case "Jeux":
            intCategorie = 9;
            break;
        case "Livre":
            intCategorie = 10;
            break;
        case "Meuble":
            intCategorie = 3;
            break;
        case "Musique":
            intCategorie = 11;
            break;
        case "Outil":
            intCategorie = 4;
            break;
        case "Sport":
            intCategorie = 12;
            break;
        case "Ustensile Cuisine":
            intCategorie = 2;
            break;
        case "Vehicule":
            intCategorie = 13;
            break;
        case "Accessoire":
            intCategorie = 6;
            break;
        default:
            // Cas où la catégorie n'est pas trouvée
            break;
    }
    return intCategorie;
}
}
