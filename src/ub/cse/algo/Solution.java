package ub.cse.algo;


import java.util.*;


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

        ArrayList<Integer> outPutNodes = new ArrayList<>();
        HashMap<Integer, ArrayList> tempGraph = new HashMap<>();
        int graphSize = graph.size();
        int[] weightsFromTheStartNode = new int[graphSize];
        boolean[] nodesInShortestPath = new boolean[graphSize];
        PriorityQueue<Node> pq = new PriorityQueue<>(graphSize);
        int weightPosition = 0;
        int currntNode = _startNode;
        PriorityQueue<Node> shortestPathNodes = new PriorityQueue<>(graphSize);

//        add the start node at the begining
        for (int i = 0; i < weightsFromTheStartNode.length; i++) {
            weightsFromTheStartNode[i] = Integer.MAX_VALUE;
        }

        //assign the weight of the start node to the array that keeps track of the weights of each node
        weightsFromTheStartNode[_startNode] = graph.get(_startNode).get(weightPosition);

        //get the weight of the start node and add it to the pq
        int weight = graph.get(currntNode).get(weightPosition);
        pq.add(new Node(weight, currntNode));

        while (!pq.isEmpty()) {

//            remove the smallest node from the pq
            Node removedNodePair = pq.poll();
            int newNode = removedNodePair.getNode();


            if (newNode == _endNode) {
                //add the start node to the out put nodes
                outPutNodes.add(_startNode);
                System.out.println("I found the end node, time to get out of here");
                //print the index for the nodes in the shortest path boolen
                for (int i = 0; i < nodesInShortestPath.length ; i++) {
                    if (nodesInShortestPath[i] == false){
                        System.out.println("I came before the sucker over there " + i);
                        outPutNodes.add(i);
                        if (i == _endNode) {
                            return outPutNodes;
                        }
                    }

                }

//                print out the array for the nodes at this point
                System.out.println(Arrays.toString(weightsFromTheStartNode));
                //add the start node to the output array
                outPutNodes.add(_startNode);
                System.out.println(outPutNodes);
                //find the nodes with the smallest weight, if they are the end node
                //copy the array to a pq. the pq will have the index being i and weignth being value/ sort them using compar4
                for (int i = 0; i < weightsFromTheStartNode.length; i++) {
                    shortestPathNodes.add(new Node(weightsFromTheStartNode[i], i));
                }
                int noderetrived = 0;
                while (noderetrived != _endNode){
                    noderetrived = shortestPathNodes.poll().node;
                    outPutNodes.add(noderetrived);
                }
                System.out.println("Node with the least dist " + outPutNodes);

                System.exit(-1);
            }

            //if the node doesn't exist in the nodes in the shortest path, add it
            if (nodesInShortestPath[newNode] == false) {
                nodesInShortestPath[newNode] = true;

                //go through all the neighbours of the new node
                for (int i = 1; i < graph.get(newNode).size(); i++) {
                    int neighbour = graph.get(newNode).get(i);
                    int nodeWeight = graph.get(newNode).get(weightPosition);

                    //if the neighbour for the node is not in the nodes in shortest path already, meaning it's not explored, then add it
                    if (nodesInShortestPath[neighbour] == false) {
                        int newneighbourWeight = nodeWeight + graph.get(neighbour).get(weightPosition);
                        int oldNeighbourWeight = weightsFromTheStartNode[neighbour];

                       //check if the current weight of the node is less than the weight in the weightsfromthestrat node array
                        if (oldNeighbourWeight > newneighbourWeight) {
                            pq.add(new Node(newneighbourWeight, neighbour));
                            weightsFromTheStartNode[neighbour] = newneighbourWeight;
                        }//end if
                    }

                }
            }
        }

        //use a prioirty to queue to add the numbers
        //take care of the cost of the start node
        return outPutNodes;
    }


}


