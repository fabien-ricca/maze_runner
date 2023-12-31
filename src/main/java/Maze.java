import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;

public class Maze {
    private int rows;       // Lignes (hauteur)
    private int cols;       // Colonnes (largeur)
    private int enter;      // Entrée du labyrinthe
    private Stack<int[]> stack = new Stack<>();     // Pile de poisitions visitées viables
    private String[][] lab;     // Labyrinthe



    /**
     * - Constructor -<br>
     * Set les colonnes, les lignes et lance l'initialisation du labyrinthe.
     */
    public Maze(int rows, int cols) {
        this.rows = rows * 3;
        this.cols = cols * 3;
        this.lab = new String[this.rows][this.cols];

        init();
    }



    /**
     * Initialisation d'un labyrinthe vide (uniquement de '#' et des ' ').
     */
    public void init(){
        // On rempli le labyrinthe de murs
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.lab[i][j] = "#";
            }
        }

        // On ouvre le centre de chaque cellule
        for (int i = 0; i < this.rows; i += 3) {
            for (int j = 0; j < this.cols; j += 3) {
                this.lab[i + 1][j + 1] = " ";
            }
        }
        startEndPoints();
    }



    /**
     * Méthode qui va créer une entrée au Nord et une sortie au Sud.
     */
    public String[][] startEndPoints(){
        // On crée un tableau avec les entrées potentielles
        int[] enters = new int[this.rows/3];
        for (int i = 0; i < enters.length; i++) {
            enters[i] = 1 + i * 3;
        }

        Random random = new Random();

        int ent = random.nextInt(enters.length);
        this.lab[0][enters[ent]] = ".";
        this.lab[1][enters[ent]] = ".";
        this.enter = enters[ent];


        int exi = random.nextInt(enters.length);
        this.lab[this.lab.length-1][enters[exi]] = ".";

        return this.lab;
    }



    /**
     *Méthode qui va établir une liste de direction possibles, puis en retourner une au hasard.
     */
    public String direction(String target, int i, int j){
        ArrayList<String> dir = new ArrayList<>();

        if((i-3>0) && this.lab[i-3][j].equals(target)){ dir.add("nord");}
        if((i+3<rows) && this.lab[i+3][j].equals(target)){ dir.add("sud");}
        if((j-3>0) && this.lab[i][j-3].equals(target)){ dir.add("ouest");}
        if((j+3<cols) && this.lab[i][j+3].equals(target)){ dir.add("est");}


        if(dir.size() > 0){
            if(dir.size() >= 2){
                int[] pos = {i, j};
                this.stack.push(pos);
            }

            Random random = new Random();
            int x = random.nextInt(dir.size());

            return dir.get(x);
        }

        return "none";
    }



    /**
     * Méthode pour vérifier si le labyrinthe est terminée (s'il contient encore un ou plusieurs ' ').
     */
    public boolean isValidMaze(){
        return Arrays.stream(this.lab)
                .flatMap(Arrays::stream)
                .noneMatch(cell -> " ".equals(cell));
    }



    /**
     * Méthode pour afficher le labyrinthe.
     */
    public void printMaze() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.lab[i][j]);
            }
            System.out.println();
        }
    }



    /**
     * Getters...
     */
    public String[][] getMaze(){
        return this.lab;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getEnter() {
        return enter;
    }

    public Stack<int[]> getStack() {
        return stack;
    }
}