import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GraphBasedMazeGenerator implements MazeGenerator{
    private boolean murNord = false;    // Mur du Nord
    private boolean murSud = false;     // Mur du Sud
    private boolean murEst = false;     // Mur de l'est
    private boolean murOuest = false;   // Mur de l'ouest
    private boolean center = false;     // Centre de la cellule
    private boolean difficult;          // Parfait ou imparfait
    private int enter;                  // Entrée du labyrinthe
    private Stack<int[]> stack = new Stack<>();     // Pile de poisitions visitées viables
    private GraphBasedMazeGenerator[][] maze;       // Labyrinthe



    /**
     * Méthode appelée dans la Main. Créer le labyrinthe et l'affiche.
     */
    public void createMaze(int rows, int cols){

        // On crée le tableau dans les bonnes dimensions.
        maze = new GraphBasedMazeGenerator[rows][cols];

        // On crée le tableau de cellules
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                maze[i][j] = new GraphBasedMazeGenerator();
            }
        }

        // On crée une entrée et une sortie
        startEndPoints();

        // On génère le labyrinthe
        generator(0, enter);

        // Si le paramètre 'imperfect' a été passé on le rend imparfait
        if(!difficult){ breakPerfection();}

        // On affiche e labyrinthe
        printMaze();
    }



    /**
     * Méthode qui génère un labyrinthe parfait.
     */
    public void generator(int i, int j) {

        while(!isValidMaze()){
            String dir = direction(false, i, j);

            switch(dir){
                case "nord":
                    maze[i][j].setMurNord(true);
                    maze[i-1][j].setMurSud(true);
                    maze[i-1][j].setCenter(true);
                    i-=1;
                    break;

                case "sud":
                    maze[i][j].setMurSud(true);
                    maze[i+1][j].setMurNord(true);
                    maze[i+1][j].setCenter(true);
                    i+=1;
                    break;

                case "est":
                    maze[i][j].setMurEst(true);
                    maze[i][j+1].setMurOuest(true);
                    maze[i][j+1].setCenter(true);
                    j+=1;
                    break;

                case "ouest":
                    maze[i][j].setMurOuest(true);
                    maze[i][j-1].setMurEst(true);
                    maze[i][j-1].setCenter(true);
                    j-=1;
                    break;

                case "none":
                    stack.pop();
                    int[] newPos = stack.pop();
                    i = newPos[0];
                    j = newPos[1];
                    break;
            }

            int[] pos = {i, j};
            stack.push(pos);
        }

    }



    /**
     * Méthode qui va rendre le labyrinthe imparfait.
     */
    public void breakPerfection(){
        Random random = new Random();

        int count = 0;
        while(count <= maze.length / 2){
            int x = random.nextInt(0, maze.length);
            int y = random.nextInt(0, maze[0].length);

            String dir = direction(true, x, y);


            switch (dir) {
                case "nord":
                    if (!maze[x - 1][y].isMurSud()) {
                        maze[x - 1][y].setMurSud(true);
                        maze[x][y].setMurNord(true);
                        count++;
                    }
                    break;

                case "sud":
                    if (!maze[x + 1][y].isMurNord()) {
                        maze[x + 1][y].setMurNord(true);
                        maze[x][y].setMurSud(true);
                        count++;
                    }
                    break;

                case "est":
                    if (!maze[x][y + 1].isMurOuest()) {
                        maze[x][y + 1].setMurOuest(true);
                        maze[x][y].setMurEst(true);
                        count++;
                    }
                    break;

                case "ouest":
                    if (!maze[x][y - 1].isMurEst()) {
                        maze[x][y - 1].setMurEst(true);
                        maze[x][y].setMurOuest(true);
                        count++;
                    }
                    break;
            }
        }
    }



    /**
     * Méthode qui va créer une entrée au Nord et une sortie au Sud.
     */
    public void startEndPoints(){

        Random random = new Random();

        int ent = random.nextInt(maze[0].length);
        maze[0][ent].setMurNord(true);
        maze[0][ent].setCenter(true);
        enter = ent;

        int exi = random.nextInt(maze[0].length);
        maze[maze.length-1][exi].setMurSud(true);
    }



    /**
     * Méthode qui va établir une liste de direction possibles, puis en retourner une au hasard.
     */
    public String direction(boolean center, int i, int j){

        ArrayList<String> dir = new ArrayList<>();

        if((i-1>=0) && maze[i-1][j].isCenter() == center){ dir.add("nord");}
        if((i+1< maze.length) && maze[i+1][j].isCenter() == center){ dir.add("sud");}
        if((j-1>=0) && maze[i][j-1].isCenter() == center){ dir.add("ouest");}
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



    /**
     * Méthode pour vérifier si le labyrinthe est terminée (si le centre de chaque cellule est true).
     */
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


    /**
     * Méthode pour afficher le labyrinthe.
     */
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {

            System.out.print("#");

            // Imprimer les murs Nord de chaque cellule sur la ligne i
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print((maze[i][j].murNord ? "." : "#"));
                if(j < maze[i].length-1){
                    System.out.print("##");
                }
            }

            System.out.print("#");
            System.out.println();

            // Imprimer les murs Ouest, le centre et les murs Est de chaque cellule sur la ligne i
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print((maze[i][j].murOuest ? "." : "#"));
                System.out.print((maze[i][j].center ? "." : "#"));
                System.out.print((maze[i][j].murEst ? "." : "#"));
            }

            System.out.println();
            System.out.print("#");

            // Imprimer les murs Sud de chaque cellule sur la ligne i
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print((maze[i][j].murSud ? "." : "#"));
                if(j < maze[i].length-1){
                    System.out.print("##");
                }
            }

            System.out.print("#");
            System.out.println();
        }
    }



    /**
     * Getters et Setters...
     */
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

    public void setDifficult(boolean difficult) {
        this.difficult = difficult;
    }

}
