import java.util.ArrayList;

public class Main {

    public static String checkArgs(String[] args){

        if(args.length != 4){
            return "Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.";
        }

        if(!checkNumber(args[0]) || !checkNumber(args[1])){
            return "Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5.";
        }

        if(!checkOption(args[2]) || !checkType(args[3])){
            return "Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.";
        }


        return "true";
    }


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


    public static boolean checkOption(String args){
        if(!args.equals("perfect") || !args.equals("imperfect")){
            return false;
        }
        return true;
    }


    public static boolean checkType(String args){
        if(!args.equals("simple") && !args.equals("graph") && !args.equals("optimized")){
            return false;
        }
        return true;
    }


    public static void main(String[] args) {

        System.out.println();
        SimplePerfectMazeGenerator simplePerfect = new SimplePerfectMazeGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println();
        System.out.println();
        SimpleImperfectMazeGenerator simpleImperfect = new SimpleImperfectMazeGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));


//        System.out.println(checkArgs(args));
    }
}
