import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Maze {
    private int rows, cols, enter;
    private ArrayList<String> numbers = new ArrayList<String>();
    public String[][] lab;

    public Maze(int rows, int cols) {
        this.rows = rows * 3;
        this.cols = cols * 3;
        this.lab = new String[this.rows][this.cols];

        init();


        while(this.numbers.size() > 1){
            generator();
        }
    }


    public void init(){
        // On rempli le labyrinthe de murs
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.lab[i][j] = "#";
            }
        }

        startEndPoints();

        // On ouvre le centre de chaque cellules
        int nb = 1;
        for (int i = 0; i < this.rows; i += 3) {
            for (int j = 0; j < this.cols; j += 3) {
                this.lab[i + 1][j + 1] = String.valueOf(nb);
                numbers.add(String.valueOf(nb));
                nb++;
            }
        }
    }



    // Méthode pour créer une entrée et une sortie
    public String[][] startEndPoints(){
        // On crée un tableau avec les entrées potentielles
        int[] enters = new int[this.rows/3];
        for (int i = 0; i < enters.length; i++) {
            enters[i] = 1 + i * 3;
        }


        int ent = ThreadLocalRandom.current().nextInt(0, enters.length-1);
        this.enter = enters[ent];
        this.lab[0][this.enter] = ".";

        int exi = ThreadLocalRandom.current().nextInt(0, enters.length-1);
        this.lab[this.lab.length-1][enters[exi]] = ".";

        return this.lab;
    }



    // Méthode de génération de labyrinthe
    public void generator(){
        String dir = direction();

        int nb = ThreadLocalRandom.current().nextInt(0, this.numbers.size());

        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
                if(this.lab[i][j].equals(this.numbers.get(nb))) {

                    System.out.println("nb = " + this.numbers.get(nb));
                    System.out.println("le = "+this.lab[i][j]);

                    // Sud
                    if (i+3<rows && this.lab[i+3][j] != this.numbers.get(nb)) {
                        this.lab[i][j] = this.lab[i+3][j];
                    }

                    // Nord
                    else if (i-3>0 && this.lab[i-3][j] != this.numbers.get(nb)) {
                        this.lab[i][j] = this.lab[i-3][j];
                    }

                    // Est
                    else if (j+3<cols && this.lab[i][j+3] != this.numbers.get(nb)) {
                        this.lab[i][j] = this.lab[i][j+3];
                    }

                    // Ouest
                    else if (j-3>0 && this.lab[i][j-3] != this.numbers.get(nb)) {
                        this.lab[i][j] = this.lab[i][j-3];
                    }

                    this.numbers.remove(nb);
                    printMaze();
                    System.out.println();
                    System.out.println();
                    break;
                }
            }
        }


        generator();
    }



    // Méthode pour choisir une direction au hasard
    public String direction(){
        String[] dir = {"nord", "sud", "est", "ouest"};
        int i = ThreadLocalRandom.current().nextInt(0, 3);

        return dir[i];
    }



    // Méthode pour vérifier si le labyrinthe est ok
    public boolean isValidMaze(){
        return Arrays.stream(this.lab)
                .flatMap(Arrays::stream)
                .allMatch(cell -> "#".equals(cell) || ".".equals(cell));
    }




    public void printMaze() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.lab[i][j]);
            }
            System.out.println();
        }
    }
}