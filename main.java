//Alex Ginella
//CS21 Cabrillo Spring 2019
//Breadth First Search path finding algorithm

import java.util.*;

class main{

    private static int[] color;
    private static int[] parent;
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
	private static ArrayList<Integer> maze;
	private static Generator gen;

    public static void main(String[] args){
		//Scanner input = new Scanner(System.in);
		Generator g = new Generator(args[0]);
		maze = new ArrayList<>(Arrays.asList(g.getMaze()));
		/*
		while(input.hasNext()){ //input loop
	    	String inputLine = input.nextLine();
	    	int value = 0;
	    	for(int i = 0; i < inputLine.length(); i++){
				String hex = inputLine.substring(i, i+1);
				try{
		    		value = Integer.parseInt(hex);
		    
				} catch(NumberFormatException e){
		    		value = toInt(hex.toCharArray()[0]); //if letter, convert letter to hex value 
				}
				maze.add(value);//maze contains all hex values as ints
	    	}
		}
		*/
		int numVertexes = maze.size();
		//System.out.println("maze representation: " + maze + " size: " + numVertexes + "\n");
		color = new int[numVertexes];//number of spaces to traverse
		parent = new int[numVertexes];
		ArrayList<Integer> path = bfsearch(maze, 0);
		print(path);	
    }

    private static void print(ArrayList<Integer> path){
		int n = (int)Math.sqrt(maze.size());
		int x;
		int y;
		for(int i = path.size()-1; i >= 0; i--){
		    y = (int)Math.floor(path.get(i)/n);
		    x = (int)(path.get(i) + n)%n;
		    System.out.println("(" + x + ", " + y + ")");
		}
       
    }

    private static ArrayList<Integer> bfsearch(ArrayList<Integer> graph, int s){
		ArrayList<Integer> path = null;
		Queue<Integer> queue = new LinkedList<Integer>(); 
		for(int i = 0; i < graph.size(); i++){ //initialization
		    color[i] = WHITE;
		    parent[i] = -1; //null
		}
		color[s] = GRAY;
		parent[s] = -1;
		queue.add(s);
		while(!queue.isEmpty()){
		    int t = queue.remove(); //t adjacensies = whicher walls are missing	    
		    if(t == graph.size()-1){path = findAncestry(t);}//Arrays.copyOfRange(parent, 0, t);}
		    int[] adj = findAdjacencies(graph, t);
		    for(int i:adj){ //this needs to check the adjecencies of t
			if(i > 0){		    
			    if(color[i] == WHITE){
				color[i] = GRAY;
				queue.add(i);
				parent[i] = t;
			    }
			}
			
		    }
		    color[t] = BLACK;
	}
	return path;
    }

    private static ArrayList<Integer> findAncestry(int t){
	int temp;
	ArrayList<Integer> path = new ArrayList<Integer>();
	path.add(maze.size()-1);
	while(parent[t] != -1){
	    path.add(parent[t]);
	    t = parent[t];
	}
	return path;
    }
    
    //returns an array with the indexes of the adjacent nodes
    private static int[] findAdjacencies(ArrayList<Integer> maze, int index){
	int [] adjacentNodes = {0, 0, 0, 0}; //max num adjacencies is probably 4
	int hexValue = maze.get(index); //use this to find whcihc walls are missing
	if((hexValue & 1) == 0){adjacentNodes[0] = getRight(index);} //theres no right wall
	if((hexValue & 2) == 0){adjacentNodes[1] = getBottom(index);} //tjeres no bottom wall
	if((hexValue & 4) == 0){adjacentNodes[2] = getLeft(index);}//theres no left wall
	if((hexValue & 8) == 0){adjacentNodes[3] = getTop(index);} //theres no top wall
	return adjacentNodes;
    }

    private static int toInt(Character value){
	return Character.toUpperCase(value) - 55;
    }

    private static int getRight(int index){
        int sideLength = (int)Math.sqrt(maze.size());
        if((index + 1) % sideLength == 0){ return -1;}
        return index+1;
    }

    private static int getLeft(int index){
        int sideLength = (int)Math.sqrt(maze.size());
        if(index % sideLength == 0){ return -1;}
        return index - 1;
    }

    private static int getBottom(int index){
        int sideLength = (int)Math.sqrt(maze.size());
        if(index+sideLength >= maze.size()){ return -1;}
        return index+sideLength;
    }

    private static int getTop(int index){
        int sideLength = (int)Math.sqrt(maze.size());
	if(index - sideLength < 0){return -1;}
        return index - sideLength;
    }


}
