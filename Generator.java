//maze generation program
//implementing disjoint set
//Alex Ginella
//CS21 Cabrillo Spring 2019

class Generator{

    private static MazeGen maze;
    private static DSet set;
	private static Integer[] myMaze;

	public Generator(String size){
		generate(size);
	}

	public static Integer[] getMaze(){
		return myMaze;
	}

    public static void generate(String arg){
		String argument = "";
		try{	
			argument = arg;
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Enter a number as argument: ");
			System.exit(-1);
		}
		int size = Integer.parseInt(argument);
		if(size < 3){
			System.err.println("size must be at least 3");
			System.exit(-1);
		}

		maze = new MazeGen(size);       
		myMaze = maze.generate();
			
		System.out.println("Maze:");
		for(int i = 0; i < myMaze.length; i++){
			int element = myMaze[i];
			if(element >= 10){
			element += 55;
			System.out.print(Character.toString((char) element));
			} else{
			System.out.print(myMaze[i]);
			}
			if((i+1) % size == 0)
			System.out.println();
		}
		

    }



}
