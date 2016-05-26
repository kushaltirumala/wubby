package webcammy;

class Point implements Comparable<Point> {
	int x;
	int y;
	
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}




	
	public int compareTo(Point o) {
		if (this.y < o.y) 
			return -1;
        if (this.y > o.y) 
        	return +1;
        if (this.x < o.x) 
        	return -1;
        if (this.x > o.x) 
        	return +1;
        return 0;
	}
}
