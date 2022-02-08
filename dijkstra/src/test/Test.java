package test;

import java.io.FileReader;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dijkstra.Graph;
import dijkstra.Result;
import dijkstra.TestGraphs;
import utilities.Convertor;
import utilities.TestResult;

public class Test {
	
	public static class Tester {
		private Graph[] graphs;
		private int[] startDijsktra;
		public Tester(Graph[] graphs, int[] startDijsktra) {
			this.graphs = graphs;
			this.startDijsktra = startDijsktra;
		}
	}
	
	private static final String testFile = "./t1.json";
	private static final String resultFile = "./result.json";

	
	private static void addPath(Graph graph,JSONArray chemins,Boolean isOriented) {
		for(Object oChemin : chemins) {
			JSONObject jChemin = (JSONObject) oChemin;
			Long depart = (Long) jChemin.get("depart");
			Long arrivee = (Long) jChemin.get("arrivee");
			Double poids = (Double) jChemin.get("poids");
			graph.addEdge(depart.intValue(),arrivee.intValue(),poids.floatValue());
			if(!isOriented) {
				graph.addEdge(arrivee.intValue(),depart.intValue(),poids.floatValue());
			}
		}
	}
	
	
	/**
	 * Makes a array of the graphs from the .JSON
	 * @return the array of graphs
	 */
	private static Tester getTests() {
		Graph[] testGraphs = null;
		int[] startDijsktra = null;
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(testFile));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray graphs = (JSONArray) jsonObject.get("graphs");
			
			
			int size = graphs.size();
			int i = 0;
			testGraphs = new Graph[size];
			startDijsktra = new int[size];
			/**
			 * for graphs in the .JSON  
			 */
			for(Object oGraph : graphs) {
				JSONObject jGraph = (JSONObject) oGraph;
				Long sommets = (Long) jGraph.get("sommets");
				int iSommets = sommets.intValue();
				Graph graph = new Graph(iSommets);
				Long start = (Long) jGraph.get("departDijkstra");
				int iStart = start.intValue();
				Boolean isOriented = (Boolean) jGraph.get("estOriente");
				addPath(graph,(JSONArray)jGraph.get("chemins"),isOriented);
				startDijsktra[i] = iStart;
				testGraphs[i++] =  graph;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Tester t =new Tester(testGraphs,startDijsktra);
		return t;
	}
	
	/**
	 * Read in the .JSON the expected results...
	 * @return the array of expected results
	 */
	private static Result[] getResults() {
		Result[] testResults = null;
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(resultFile));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray results = (JSONArray) jsonObject.get("results");
			int size = results.size();
			int i = 0;
			testResults = new Result[size];
			
			for(Object oResult : results) {
				JSONObject jResult = (JSONObject) oResult;
				JSONArray jDistances = (JSONArray) jResult.get("distances");
				JSONArray jParents = (JSONArray) jResult.get("parents");
				double[] distances = Arrays.stream(jDistances.toArray()).mapToDouble(o -> (Double)o).toArray();
				long[] parents = Arrays.stream(jParents.toArray()).mapToLong(o -> (Long)o).toArray();
				testResults[i++] =  new Result(Convertor.doubleToFloat(distances),Convertor.longToInt(parents));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testResults;
	}
	
	public static void main(String[] args) {
		Tester tester = Test.getTests();
		Graph[] graphs = tester.graphs;
		Result[] expectedResults = Test.getResults();

		// Some verifications on the read data...
		if(graphs == null) {
			System.err.println("Erreur il n'y aucun graph, ou la lecture n'a pas fonctionnée...");
			return;
		}
		if(expectedResults==null) {
			System.err.println("Erreur il n'y aucun resultat, ou la lecture n'a pas fonctionnée...");
			return;
		}
		if(graphs.length!=expectedResults.length) {
			System.err.println("Erreur il n'y pas autant de graphs("+graphs.length+") que de resultat("+expectedResults.length+")...");
			return;
		}
		
		// Computes the result for all graphs and compare to the expected results
		int i = 0;
		int passed = 0;
		for(Graph graph : graphs) {
			Result result = TestGraphs.Dijkstra(graph,tester.startDijsktra[i]);
			//System.out.println(result);
			TestResult testResult = expectedResults[i].equals(result);
			passed += testResult.areEquals() ? 1 : 0;
			testResult.print(i);
			i++;
		}
		System.out.println("Resultat : ["+passed+"/"+i+"]");
		
		
	}
}
