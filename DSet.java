//disjoint set

class DSet{

    private int[] rank; 
    private int[] parent; 
    private int numSets;
    
    public DSet(int n){	
	if(n < 0){
	    System.err.println("Enter positive integer");
	    System.exit(-1);
	}
	rank = new int[n];
	parent = new int[n];
	numSets = n;
	for(int i = 0; i < n; i++)
	    makeSet(i);	
    }

    public int getNumSets(){ return numSets;}

    public void makeSet(int x){	
	if (x < 0 || x >= parent.length){ return;}
	parent[x] = x;
	rank[x] = 0;
    }

    public void union(int x, int y){
	if(x < 0 || x >= parent.length){return;}
	if(y < 0 || y >= parent.length){return;}
	link(find(x), find(y));
	numSets--;
    }

    public int find(int x){ //find the parent of an elemnt
   	if(x < 0 || x >= parent.length){return -1;}
	if(x != parent[x]){
	    parent[x] = find(parent[x]);
	}
	return parent[x]; //the representative element
    }

    public void print(){
	System.out.println("Element\tRank");
	for(int i = 0; i < parent.length; i++){
	    System.out.println(parent[i] + "\t" + rank[i]);
	}
    }

    private void link(int x, int y){
	if(x == y){ return;}
	if(rank[x] > rank[y]){
	    parent[y] = x;
	} else {
	    parent[x] = y;
	    if(rank[x] == rank[y])
		rank[y]++;
	}
    }

}
