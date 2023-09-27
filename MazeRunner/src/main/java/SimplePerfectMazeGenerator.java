public class SimplePerfectMazeGenerator implements MazeGenerator{
    private Maze maze;


    public SimplePerfectMazeGenerator(int rows, int cols){
        this.maze = new Maze(rows, cols);

        generator(1, maze.getEnter());
        maze.printMaze();
    }

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
}
