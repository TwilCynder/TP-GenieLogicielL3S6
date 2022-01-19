package dijkstra;

import java.util.Iterator;
import java.util.PriorityQueue;

import utilities.TestResult;

public class Result {
	private float[] distances;
	private int[] parents;
	
	public Result(float[] distances, int[] parents) {
		super();
		this.distances = distances;
		this.parents = parents;
	}

	public float[] getDistances() {
		return distances;
	}

	public int[] getParents() {
		return parents;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("Vertex\tDistance\tParent Vertex\n");
		for (int i = 0; i < parents.length; i++) {
			builder.append(i + "\t" + distances[i] + "\t\t" + parents[i]+"\n");
		}
		return builder.toString();
	}

	public TestResult equals(Result r) {
		// Compare the arrays sizes
		if(r.distances.length!=distances.length) {
			return new TestResult("Difference taille des tableaux distances");
		}
		if(r.parents.length!=parents.length) {
			return new TestResult("Difference taille des tableaux parents");
		}
		if(r.parents.length!=r.distances.length) {
			return new TestResult("Difference entre les tailles des tableaux distances et parents");
		}
		// Compare the arrays :
		for(int i=0;i<r.parents.length;i++) {
			if(r.distances[i] != distances[i]) {
				return new TestResult("Difference à l'indice : "+i+" result.distances="+r.distances[i]+" et expectedResult.distances="+distances[i]);
			}
			if(r.parents[i]!=parents[i]) {
				return new TestResult("Difference à l'indice : "+i+" result.parents="+r.parents[i]+" et expectedResult.parents="+parents[i]);
			}
		}
		return new TestResult();
	}
	
	
}
