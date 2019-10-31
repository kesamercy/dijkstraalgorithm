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
        PriorityQueue<Node> pq = new PriorityQueue<>();
        ArrayList<Integer> output = new ArrayList<>();
        HashMap<Integer, ArrayList> tempGraph = new HashMap<>();
        ArrayList<Integer> exploredNodes = new ArrayList<>();

        tempGraph.putAll(graph);

        int currentNode = _startNode;
        int leastAmntNeibas = 1;
        int weightPosition = 0;
        int weightOfCurrentNode = 0;

        //end if the graph is emepty or if we find the end node
        while (!graph.isEmpty() && currentNode != _endNode) {
            //check if the graph is connected, that is, had at least one neighbour

            //compute the weight for the current node
            if (exploredNodes.indexOf(currentNode) != -1){
                weightOfCurrentNode = graph.get(currentNode).get(weightPosition);
            }


            for (int i = 1; i < graph.get(currentNode).size(); i++) {
                //if it has already been visited, do not add it to the nodes in the pq
                if (exploredNodes.indexOf(i) == -1){
                    int neighbour = graph.get(currentNode).get(i);
                    if (exploredNodes.indexOf(neighbour) == -1 && exploredNodes.indexOf(currentNode) != -1) {
                        int newNodeWeight = weightOfCurrentNode + graph.get(neighbour).get(weightPosition);
                        pq.add(new Node(newNodeWeight, neighbour));
                    }

                }

            }
            exploredNodes.add(currentNode);
            tempGraph.get(currentNode).remove(weightPosition);

            if (!output.isEmpty()) {
                int lastElement = output.get(output.size() - 1);
                if (tempGraph.get(lastElement).indexOf(currentNode) == -1) {
                    output.remove(output.size() - 1);
                }
            }//end if output is empty
            output.add(currentNode);
            graph.remove(currentNode);
            if (!pq.isEmpty()){
                currentNode = pq.poll().node;
            }


            while (exploredNodes.indexOf(currentNode) != -1 && !pq.isEmpty()) {
                pq.remove();
                currentNode = pq.poll().node;
            }

            if (tempGraph.get(currentNode).size() < leastAmntNeibas) {
                output.clear();
                return output;
            }

        }//end while


        output.add(_endNode);
        return output;
    }


}


