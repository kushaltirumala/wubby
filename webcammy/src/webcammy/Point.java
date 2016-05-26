package webcammy;
/**
 *  A class to represent a point.
 *  has an x and y coordinate, and is comparable.
 *
 *  @author  Kavi Nelakonda
 *  @version May 26, 2016
 *  @author  Period: 6
 *  @author  Assignment: JMCh19_SafeTrade
 *
 *  @author  Sources: TODO
 */
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
