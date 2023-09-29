public class Errors {

    //////////////////////////// Validation des arguments \\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * Méthode qui vérifie la validité des arguments passés.
     */
    public static String checkArgs(String[] args){
        String usage = "Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]";

        // On vérifie que 4 arguments ont bien étaient passés
        if(args.length != 4){
            return "Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.\n" + usage;
        }

        // On vérifie que les 2 premiers arguments sont biens des nombres, et qu'ils sont bien superieur à 5
        if(!checkNumber(args[0]) || !checkNumber(args[1])){
            return "Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5.\n" + usage;
        }

        // On vérifie que les 2 derniers arguments sont bien valides
        if(!checkOption(args[2]) || !checkOption(args[3])){
            return "Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.\n" + usage;
        }

        return "true";
    }


    /**
     * Vérifie la validité d'un nombre (arguments 0 et 1).
     */
    public static boolean checkNumber(String n){
        try {
            int nb = Integer.parseInt(n);
            if(nb < 5){
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Vérifie la validité des oprions (arguments 2 et 3).
     */
    public static boolean checkOption(String args){
        if(!args.equals("simple") && !args.equals("graph") && !args.equals("optimized") && !args.equals("perfect") && !args.equals("imperfect")){
            return false;
        }
        return true;
    }

}
