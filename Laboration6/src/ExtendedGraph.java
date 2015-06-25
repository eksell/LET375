import java.util.ArrayList;
import java.util.List;


public class ExtendedGraph extends Graph {
	
	public List<Integer> getPath( int destInd ) {
		
		unweighted(0);
		
		Vertex ver = vertexMap.get( destInd );
        List<Integer> l = new ArrayList<Integer>();
		return getPath( ver, l );
	}
	
	private List<Integer> getPath( Vertex dest, List<Integer> l ) {
		if( dest == null )
			return l;
		
		l.add(dest.name);
		return getPath( dest.prev, l);
	}
}
