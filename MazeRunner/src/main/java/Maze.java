import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.Random;

public class Maze {
    private int rows, cols, enter;
    private Stack<int[]> stack = new Stack<>();
    public String[][] lab;

    public Maze(int rows, int cols) {
        this.rows = rows * 3;
        this.cols = cols * 3;
        this.lab = new String[this.rows][this.cols];

        init();



        System.out.println();
        generator(1, this.enter);
    }


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



    // Méthode pour créer une entrée et une sortie
    public String[][] startEndPoints(){
        // On crée un tableau avec les entrées potentielles
        int[] enters = new int[this.rows/3];
        for (int i = 0; i < enters.length; i++) {
            enters[i] = 1 + i * 3;
        }


        int ent = ThreadLocalRandom.current().nextInt(0, enters.length-1);
        this.enter = enters[ent];
        this.lab[0][this.enter] = "•";
        this.lab[1][this.enter] = "•";

        int exi = ThreadLocalRandom.current().nextInt(0, enters.length-1);
        this.lab[this.lab.length-1][enters[exi]] = "•";

        return this.lab;
    }



    // Méthode de génération de labyrinthe
    public void generator(int i, int j){
        String dir = direction(" ", i, j);

        switch(dir){
            case "nord":
                this.lab[i-1][j] = "•";
                this.lab[i-2][j] = "•";
                this.lab[i-3][j] = "•";
                i-=3;
                break;

            case "sud":
                this.lab[i+1][j] = "•";
                this.lab[i+2][j] = "•";
                this.lab[i+3][j] = "•";
                i+=3;
                break;

            case "est":
                this.lab[i][j+1] = "•";
                this.lab[i][j+2] = "•";
                this.lab[i][j+3] = "•";
                j+=3;
                break;

            case "ouest":
                this.lab[i][j-1] = "•";
                this.lab[i][j-2] = "•";
                this.lab[i][j-3] = "•";
                j-=3;
                break;

            case "none":
                stack.pop();
                int[] newPos = this.stack.pop();
                i = newPos[0];
                j = newPos[1];
                break;
        }

        int[] pos = {i, j};
        this.stack.push(pos);

//        System.out.println();
//        System.out.println();
//        printMaze();

        if(!isValidMaze()){
            generator(i, j);
        }
    }



    // Méthode pour choisir une direction au hasard
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



    // Méthode pour vérifier si le labyrinthe est ok
    public boolean isValidMaze(){
        return Arrays.stream(this.lab)
                .flatMap(Arrays::stream)
                .noneMatch(cell -> " ".equals(cell));
    }



    // Méthode pour afficher le labyrinthe
    public void printMaze() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.lab[i][j]);
            }
            System.out.println();
        }
    }
}