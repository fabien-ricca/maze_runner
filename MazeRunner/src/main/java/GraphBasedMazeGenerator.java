import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GraphBasedMazeGenerator implements MazeGenerator{
    private boolean murNord = false;
    private boolean murSud = false;
    private boolean murEst = false;
    private boolean murOuest = false;
    private boolean center = false;
    private Stack<int[]> stack = new Stack<>();
    private GraphBasedMazeGenerator[][] maze;


    public void createMaze(int rows, int cols){
        maze = new GraphBasedMazeGenerator[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                maze[i][j] = new GraphBasedMazeGenerator();
            }
        }
        startEndPoints();
        printMaze();
    }


    public void generator(int i, int j) {


    }



    // Méthode qui va créer une entrée et une sortie
    public void startEndPoints(){

        Random random = new Random();

        int ent = random.nextInt(maze[0].length);
        maze[0][ent].setMurNord(true);
        maze[0][ent].setCenter(true);

        int exi = random.nextInt(maze[0].length);
        maze[maze.length-1][exi].setCenter(true);
        maze[maze.length-1][exi].setMurSud(true);
    }



    // Méthode qui va retourner une direction
    public String direction(boolean center, int i, int j){

        ArrayList<String> dir = new ArrayList<>();

        if((i-1>0) && maze[i-1][j].isCenter() == center){ dir.add("nord");}
        if((i+1< maze.length) && maze[i+1][j].isCenter() == center){ dir.add("sud");}
        if((j-1>0) && maze[i][j-1].isCenter() == center){ dir.add("ouest");}
        if((j+1<maze[0].length) && maze[i][j+1].isCenter() == center){ dir.add("est");}


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



    // Méthode pour vérifier si le labyrinthe est terminé
    public boolean isValidMaze(){

        for(GraphBasedMazeGenerator[] line : maze){
            for(GraphBasedMazeGenerator cell : line){
                if(!cell.isCenter()){
                    return false;
                }
            }
        }

        return true;
    }



    // Méthode pour afficher le labyrinthe
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {

            System.out.print("#");

            // Imprimer les murs Nord de chaque cellule sur la ligne i
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print((maze[i][j].murNord ? "•" : "#"));
                if(j < maze[i].length-1){
                    System.out.print("##");
                }
            }

            System.out.print("#");
            System.out.println();

            // Imprimer les murs Ouest, le centre et les murs Est de chaque cellule sur la ligne i
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print((maze[i][j].murOuest ? "•" : "#"));
                System.out.print((maze[i][j].center ? "•" : "#"));
                System.out.print((maze[i][j].murEst ? "•" : "#"));
            }

            System.out.println();
            System.out.print("#");

            // Imprimer les murs Sud de chaque cellule sur la ligne i
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print((maze[i][j].murSud ? "•" : "#"));
                if(j < maze[i].length-1){
                    System.out.print("##");
                }
            }

            System.out.print("#");
            System.out.println();
        }
    }



    public boolean isMurNord() {
        return murNord;
    }

    public void setMurNord(boolean murNord) {
        this.murNord = murNord;
    }

    public boolean isMurSud() {
        return murSud;
    }

    public void setMurSud(boolean murSud) {
        this.murSud = murSud;
    }

    public boolean isMurEst() {
        return murEst;
    }

    public void setMurEst(boolean murEst) {
        this.murEst = murEst;
    }

    public boolean isMurOuest() {
        return murOuest;
    }

    public void setMurOuest(boolean murWest) {
        this.murOuest = murWest;
    }

    public boolean isCenter() {
        return center;
    }

    public void setCenter(boolean center) {
        this.center = center;
    }
}
