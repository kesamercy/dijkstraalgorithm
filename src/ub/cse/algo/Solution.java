package ub.cse.algo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Objects;


public class Solution {

    private int _startNode;
    private int _endNode;
    private HashMap<Integer, ArrayList<Integer>> graph;


    public Solution(int startNode, int endNode, HashMap<Integer, ArrayList<Integer>> g) {
        _startNode = startNode;
        _endNode = endNode;
        graph = g;
    }

    class Node implements Comparable<Node> {
        private int weight;
        private int node;

        public Node(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(String name) {
            this.weight = weight;
        }

        public int getNode() {
            return node;
        }

        public void setNode(double salary) {
            this.node = node;
        }

        @Override
        public String toString() {
            return "NODE{" +
                    "weight='" + weight + '\'' +
                    ", node=" + node +
                    '}';
        }

        @Override
        public int compareTo(Node employee) {
            if (this.getWeight() > this.getWeight()) {
                return 1;
            } else if (this.getWeight() < employee.getWeight()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public ArrayList<Integer> outputPath() {
        /*
         * Find the smallest weighted path between _startNode and _endNode
         * The first number of graph's adjacency list is the weight of it's node
         */
        PriorityQueue<Node> visitedNodes = new PriorityQueue<>();
        ArrayList<Integer> output = new ArrayList<>();

        int weightPosition = 0;
        int currNode = _startNode;
        int graphSize = graph.size();
        int counter = 0;
        ArrayList<Integer> exploredNodes = new ArrayList<>();

        while (counter < graphSize && currNode != _endNode) {
            int nodeWeight = graph.get(currNode).get(weightPosition);

            for (int i = 1; i < graph.get(currNode).size(); i++) {
                int neighbour = graph.get(currNode).get(i);
                int newNodeWeight = nodeWeight + graph.get(neighbour).get(weightPosition);
                visitedNodes.add(new Node(newNodeWeight, neighbour));
            }
            if (output.indexOf(currNode) == -1) {
                if (!output.isEmpty()) {
                    int lastElement = output.get(output.size() - 1);
                    if (graph.get(lastElement).indexOf(currNode) == -1) {
                        output.remove(lastElement);
                    }
                    output.add(currNode);

                } else {
                    output.add(currNode);
                }
            }

            ++counter;

            currNode = visitedNodes.poll().getNode();
            exploredNodes.add(currNode);

            while (exploredNodes.indexOf(currNode) == -1) {
                visitedNodes.remove(currNode);
            }
            currNode = visitedNodes.poll().getNode();
        }
        output.add(_endNode);
        return output;
    }


}


