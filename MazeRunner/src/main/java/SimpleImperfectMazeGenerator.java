import java.util.Random;

public class SimpleImperfectMazeGenerator implements MazeGenerator{
    private Maze maze;      // Labyrinthe



    /**
     * - Constructor -<br>
     * Crée le labyrinthe et l'affiche.
     */
    public SimpleImperfectMazeGenerator(int rows, int cols){
        this.maze = new Maze(rows, cols);

        generator(1, maze.getEnter());
        breakPerfection(maze.getRows());

        maze.printMaze();
    }



    /**
     * Méthode qui génère un labyrinthe parfait.
     */
    public void generator(int i, int j){

        while(!maze.isValidMaze()){
            String dir = maze.direction(" ", i, j);

            switch(dir){
                case "nord":
                    maze.getMaze()[i-1][j] = "•";
                    maze.getMaze()[i-2][j] = "•";
                    maze.getMaze()[i-3][j] = "•";
                    i-=3;
                    break;

                case "sud":
                    maze.getMaze()[i+1][j] = "•";
                    maze.getMaze()[i+2][j] = "•";
                    maze.getMaze()[i+3][j] = "•";
                    i+=3;
                    break;

                case "est":
                    maze.getMaze()[i][j+1] = "•";
                    maze.getMaze()[i][j+2] = "•";
                    maze.getMaze()[i][j+3] = "•";
                    j+=3;
                    break;

                case "ouest":
                    maze.getMaze()[i][j-1] = "•";
                    maze.getMaze()[i][j-2] = "•";
                    maze.getMaze()[i][j-3] = "•";
                    j-=3;
                    break;

                case "none":
                    maze.getStack().pop();
                    int[] newPos = maze.getStack().pop();
                    i = newPos[0];
                    j = newPos[1];
                    break;
            }

            int[] pos = {i, j};
            maze.getStack().push(pos);
        }
    }



    /**
     * Méthode qui va rendre le labyrinthe imparfait.
     */
    public void breakPerfection(int rows) {
        Random random = new Random();
        String dir = "";
        int x = 0;
        int y = 0;

        int[] enters = new int[maze.getRows() / 3];
        for (int i = 0; i < enters.length; i++) {
            enters[i] = 1 + i * 3;
        }

        for (int i = 0; i < rows / 2; i++) {
            x = enters[random.nextInt(1, enters.length)];
            y = enters[random.nextInt(1, enters.length)];

            dir = this.maze.direction("•", x, y);


            switch (dir) {
                case "nord":
                    if (maze.getMaze()[x - 1][y] == "#") {
                        maze.getMaze()[x - 1][y] = "•";
                        maze.getMaze()[x - 2][y] = "•";
                        x -= 3;
                    }
                    break;

                case "sud":
                    if (maze.getMaze()[x + 1][y] == "#") {
                        maze.getMaze()[x + 1][y] = "•";
                        maze.getMaze()[x + 2][y] = "•";
                        x += 3;
                    }
                    break;

                case "est":
                    if (maze.getMaze()[x][y + 1] == "#") {
                        maze.getMaze()[x][y + 1] = "•";
                        maze.getMaze()[x][y + 2] = "•";
                        y += 3;
                    }
                    break;

                case "ouest":
                    if (maze.getMaze()[x][y - 1] == "#") {
                        maze.getMaze()[x][y - 1] = "•";
                        maze.getMaze()[x][y - 2] = "•";
                        y -= 3;
                    }
                    break;
            }
        }
    }

}

