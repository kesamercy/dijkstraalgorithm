package ub.cse.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private int _startNode;
    private int _endNode;
    private HashMap<Integer, ArrayList<Integer>> graph;
    private int weight;
    private int weightPosition = 0;

    public Solution(int startNode, int endNode, HashMap<Integer, ArrayList<Integer>> g) {
        _startNode = startNode;
        _endNode = endNode;
        graph = g;
    }

//    FIGURE OUT HOW TO IMPLEMENT THIS USING A PRIORITY QUEUE!!!!!
//    THINK ABOUT THIS... WHAT IS A PRIORITY QUUEUE AND HOW DOES IT WORK!?

    public ArrayList<Integer> outputPath() {
        /*
         * Find the smallest weighted path between _startNode and _endNode
         * The first number of graph's adjacency list is the weight of it's node
         */
        Map<Integer, Integer> visitedNodes = new HashMap<>();
        ArrayList<Integer> output = new ArrayList<>();

        int nodeToExplore;
        int newNodeToExplore;
        int doesntExist = -1;

        output.add(_startNode);

        nodeToExplore = findNodeWithMinWeight(graph.get(_startNode));
        output.add(nodeToExplore);

        while (nodeToExplore != _endNode && nodeToExplore != doesntExist) {
            weight = graph.get(nodeToExplore).get(weightPosition);
            visitedNodes.put(nodeToExplore, weight);

//            THERE IS A HUGE PROBLEM WITH THIS LOGIC!!!! --- STEP THROUGH WITH PAPER AND PENCIL AND IDENTIFY WHAT IS SUPPOSED TO HAPPEN
//            THE NODE TO EXPLORE AND THE HASH TABLE AREN'T WORKING THE WAY THEY ARE SUPPOSED TO
//            STEP THROUGH THIS FUNCTION AGAIN AND CORRECT EVERY ERA STEP BY STEP

            newNodeToExplore = findNodeWithMinWeight(visitedNodes);

            if (graph.get(nodeToExplore).indexOf(newNodeToExplore) == doesntExist && nodeToExplore != newNodeToExplore) {
                int nodeToremove = output.indexOf(nodeToExplore);
                output.remove(nodeToremove);
            }
            output.add(newNodeToExplore);


            nodeToExplore = findNodeWithMinWeight(graph.get(newNodeToExplore));
        }
        if (nodeToExplore == doesntExist) {
            output.clear();
            return output;
        }
        output.add(_endNode);

        return output;
    }

    public int findNodeWithMinWeight(ArrayList<Integer> listOfNodesWithWeights) {
        int minNode;
        int minNodeWeight;
        int notConnected = -1;
        int neighbour = 1;
        int currNode;
        int currNodeWeight;

        if (!listOfNodesWithWeights.isEmpty()) {
            minNode = listOfNodesWithWeights.get(neighbour);
            minNodeWeight = graph.get(minNode).get(weightPosition);

            for (int i = 2; i < listOfNodesWithWeights.size(); i++) {
                currNode = listOfNodesWithWeights.get(i);
                currNodeWeight = graph.get(currNode).get(weightPosition);

                if (currNodeWeight < minNodeWeight) {
                    minNode = currNode;
                    minNodeWeight = currNodeWeight;
                }
            }
        } else {
            minNode = notConnected;
        }
        return minNode;
    }

    public static int findNodeWithMinWeight(Map<Integer, Integer> visitedNodes) {
        int nodeWithMinWeight = 99999;

        for (int value: visitedNodes.values()) {
            if (value < nodeWithMinWeight) {
                nodeWithMinWeight = value;
            }
        }
        for (Map.Entry<Integer, Integer> entry : visitedNodes.entrySet()) {
            if (entry.getValue().equals(nodeWithMinWeight)) {
                nodeWithMinWeight = entry.getKey();
            }
        }

        return nodeWithMinWeight;
    }
}


