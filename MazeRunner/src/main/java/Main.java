public class Main {

    //////////////////////////// Gestion du lancement de l'application \\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * Méthode pour démarrer l'application (premier "switch").
     */
    public static void runAppli(String[] args){
        try{
            switch (args[2]){
                case "perfect":
                    choicePerfect(args);
                    break;

                case "imperfect":
                    choiceImperfect(args);
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | IllegalArgumentException | OutOfMemoryError e){
            System.out.println("Erreur inattendue lors de la génération du labyrinthe. Veuillez réessayer.");
        }
    }


    /**
     * Méthode appelée si le choix est 'perfect'.
     */
    public static void choicePerfect(String[] args){
        switch (args[3]){
            case "simple":
                SimplePerfectMazeGenerator simplePerfect = new SimplePerfectMazeGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;

            case "graph":
                GraphBasedMazeGenerator graphPerfect = new GraphBasedMazeGenerator();
                graphPerfect.setDifficult(true);
                graphPerfect.createMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;

            case "optimized":
                System.out.println("Désolé, la fonctionnalité Optimzed est actuellement indisponible...");
                break;
        }
    }


    /**
     * Méthode appelée si le choix est 'imperfect'.
     */
    public static void choiceImperfect(String[] args){
        switch (args[3]){
            case "simple":
                SimpleImperfectMazeGenerator simpleImperfect = new SimpleImperfectMazeGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;

            case "graph":
                GraphBasedMazeGenerator graphPerfect = new GraphBasedMazeGenerator();
                graphPerfect.setDifficult(false);
                graphPerfect.createMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;

            case "optimized":
                System.out.println("Désolé, la fonctionnalité Optimzed est actuellement indisponible...");
                break;
        }
    }




    /**
     * ---------------------------------------------------------------------<br>
     * ------------------- <strong><u>Point d'entrée de l'application</u></strong> ------------------<br>
     * ---------------------------------------------------------------------<br>
     */
    public static void main(String[] args) {

        if(Errors.checkArgs(args).equals("true")){
            runAppli(args);
        }
        else{
            System.out.println(Errors.checkArgs(args));
        }
    }
}
