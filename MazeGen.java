
import java.util.ArrayList;
import java.util.Random;

class MazeGen{

	private Integer[] maze;
    private DSet set;
    private int[] random;

    public MazeGen(int size){ //initializes maze and random index arrays
	set = new DSet((int)Math.pow(size, 2));
	Random rng = new Random();
	maze = new Integer[(int)Math.pow(size, 2)];
        random = new int[maze.length];
	maze[0] = 11;
	//sets default maze config and random
	for(int i = 1; i < maze.length; i++){
	    maze[i] = 15;
	    random[i] = i;
	}
	maze[maze.length-1] = 14;
	shuffle(random);
    }

    public Integer[] generate(){//generates maze usng DSet
		int sideLength = (int)Math.sqrt(maze.length);
		int index;
		while(set.getNumSets() != 1){		
			for(int i = 0; i < maze.length; i++){
			index = random[i]; //get random cell
			breakWall(index); //break a random allowed wall
			}	
		}
		return maze;
		}        

    private void breakWall(int index){	
	int [] randomWall = new int[] {0, 1, 2, 3};
	shuffle(randomWall);
	for(int i:randomWall){
	    if(i == 0){
		if(getLeft(index) < 0 || sameSet(index, getLeft(index))){
		    //do nothing
		} else{
		    breakLeft(index);
		    return;
		}
	    } if(i == 1){
		if(getRight(index) < 0 || sameSet(index, getRight(index))){
		    //do nothing;
		} else {
		    breakRight(index);
		    return;
	    }
	    } if(i == 2){
		if(getAbove(index) < 0 || sameSet(index, getAbove(index))){
		    //do nothing;
		} else{
		    breakAbove(index);
		    return;
		}
	    } if(i == 3){
		if(getBelow(index) < 0 || sameSet(index, getBelow(index))){
		    //do nothing;
		} else {
		    breakBelow(index);
		    return;
		}
	    }
	}
    }
    
    private void breakLeft(int index){
	int left = getLeft(index);
	maze[index] = maze[index] & 11;
	maze[left] = maze[left] & 14;
	set.union(index, left); //connect in set
    }
	
    private void breakRight(int index){
	int right = getRight(index);
	maze[index] = maze[index] & 14;
	maze[right] = maze[right] & 11;
	set.union(index, right); //connect in set
       
    }
    private void breakAbove(int index){
	int above = getAbove(index);
	maze[index] = maze[index] & 7;
	maze[above] = maze[above] & 13;
	set.union(index, above);
	
	
    }
    private void breakBelow(int index){
	int below = getBelow(index);
	maze[index] = maze[index] & 13;
	maze[below] = maze[below] & 7;
	set.union(index, below);

    }
    
    private Boolean sameSet(int x, int y){
	return (set.find(x) == set.find(y));
    }

    private int getRight(int index){
	int sideLength = (int)Math.sqrt(maze.length);
	if((index + 1) % sideLength == 0){ return -1;}
	return index+1;
    }

    private int getLeft(int index){
	int sideLength = (int)Math.sqrt(maze.length);
	if(index % sideLength == 0){ return -1;}
	return index - 1;
    }

    private int getBelow(int index){
	int sideLength = (int)Math.sqrt(maze.length);
	if(index+sideLength >= maze.length){ return -1;}
	return index+sideLength;
    }

    private int getAbove(int index){
	int sideLength = (int)Math.sqrt(maze.length);
	if(index - sideLength < 0){return -1;}
	return index - sideLength;
    }

    private void shuffle(int[] A){
	int index, temp, j;
	Random rng = new Random();
	for(int i = A.length-1; i > 0; i--){
	    j = rng.nextInt(i+1);
	    temp = A[j];
	    A[j] = A[i];
	    A[i] = temp;
	}
	
    }    
    
}
