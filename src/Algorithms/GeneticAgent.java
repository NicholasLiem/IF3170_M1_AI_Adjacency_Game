package Algorithms;

import Algorithms.DataStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Utils.Utils;

public class GeneticAgent {

    int CHROMOSOME_SIZE = 4;

    public int[] move(int[][] board, boolean maximizingPlayer, int roundsLeft) {
        Population.isMaximizing = maximizingPlayer;
        Individual.maximizingPlayer = maximizingPlayer;

        Population population = new Population(500, board);
        Population.board = board;
        population.makeReservationTree();

        int generations = 100;
        Individual best = population.getBestIndividual();

        for (int generation = 0; generation < generations; generation++) {
            Population newPopulation = population.evolve();
            population = newPopulation;
            population.makeReservationTree();
            Individual bestGeneration = population.getBestIndividual();
//            System.out.println(" Generation " + generation);
//            System.out.println(" Best Move: (" + bestGeneration.chromosome.get(0)[0] + ", " + bestGeneration.chromosome.get(0)[1] + ")");
//            System.out.println(" Fitness: " + bestGeneration.fitness);
            if (best.fitness < bestGeneration.fitness) {
                best = bestGeneration;
            }
        }

        return best.chromosome.get(0);
    }



    class Individual {
        static int id_count = 0;
        public int id;
        private List<int[]> chromosome;
        private double fitness;

        private int[][] board;
        public static boolean maximizingPlayer;


        public Individual(List<int[]> chromosome, int[][] board) {
            this.chromosome = chromosome;
            int[][] currentBoard = board;
            boolean isMaximizingPlayer = false;

            for (int i = 0; i < chromosome.size(); i++) {
                int[] selectedMove = chromosome.get(i);
                currentBoard = Utils.transition(Utils.copyBoard(currentBoard), selectedMove, isMaximizingPlayer);

                this.board = currentBoard;
            }

            this.fitness = Utils.evaluateBoard(this.board);

            this.chromosome = chromosome;
            this.id = id_count;
            id_count += 1;
        }

        public List<int[]> getChromosome() {
            return this.chromosome;
        }

        public Individual(int[][] initialBoard) {
            List<int[]> chromosome = new ArrayList<>();
            int[][] currentBoard = initialBoard;
            boolean isMaximizingPlayer = maximizingPlayer; // Start with minimizing player

            // Generate a sequence of moves
            for (int moveNumber = 0; moveNumber < CHROMOSOME_SIZE; moveNumber++) {
                List<int[]> possibleMoves = Utils.getPossibleMoves(currentBoard, isMaximizingPlayer, 5);

                if (possibleMoves.isEmpty()) {
                    break;
                }

                int randomMoveIndex = new Random().nextInt(possibleMoves.size());
                int[] selectedMove = possibleMoves.get(randomMoveIndex);

                chromosome.add(selectedMove);

                currentBoard = Utils.transition(Utils.copyBoard(currentBoard), selectedMove, isMaximizingPlayer);

                this.board = currentBoard;

                isMaximizingPlayer = !isMaximizingPlayer;
            }

            this.fitness = Utils.evaluateBoard(this.board);

            this.chromosome = chromosome;
            this.id = id_count;
            id_count += 1;
        }


        public double getFitness() {
            return fitness;
        }

        public void setFitness(double fitness) {
            this.fitness = fitness;
        }

        public Individual crossover(Individual parent2) {
            int crossoverPoint = new Random().nextInt(this.getChromosome().size());

            List<int[]> childChromosome = new ArrayList<>();
            childChromosome.addAll(this.getChromosome().subList(0, crossoverPoint));
            childChromosome.addAll(parent2.getChromosome().subList(crossoverPoint, parent2.getChromosome().size()));

            return new Individual(childChromosome, board);
        }

        public boolean isValid(int[][] board) {
            HashMap<Integer, Boolean> unique = new HashMap<Integer, Boolean>();
            for (int[] move : chromosome) {
                if (board[move[0]][move[1]] != 0) {
                    return false;
                }
                Integer key = (Integer) move[0]*8 + move[1];
                if (unique.get(key) != null) {
                    return false;
                } else {
                    unique.put(key, true);
                }
            }

            return true;
        }
        // Mutation function
        public void mutate(double mutationRate, int[][] currBoard) {
            if (Math.random() < mutationRate) {
                int randomPoint = new Random().nextInt(this.getChromosome().size());
                List<int[]> possibleMoves = Utils.getPossibleMoves(currBoard, (randomPoint % 2 == 1), 1);
                int[] mutateMove = possibleMoves.get(0);
                chromosome.set(randomPoint, mutateMove);
                }
            }
        }

    class Population {

        private ReservationTree tree;
        private List<Individual> individuals;
        double mutationRate = 0.01;
        public static boolean isMaximizing;

        public static int[][] board;

        public Population() {
            individuals = new ArrayList<>();
        }

        public void addIndividual(Individual individual) {
                individuals.add(individual);
        }


        public Population(int size, int[][] initialBoard) {
            individuals = new ArrayList<>();
            this.board = initialBoard;
            for (int i = 0; i < size; i++) {
                Individual individual = new Individual(initialBoard);
                individuals.add(individual);
            }
        }

        public void makeReservationTree() {
            ReservationTree.isMaximizing = this.isMaximizing;
            tree = new ReservationTree();
            for (Individual individual : individuals) {
//                System.out.println(" INDIVIDUAL ");
//                for (int[] move : individual.chromosome) {
//                    System.out.println("Move: (" + move[0] + ", " + move[1] + ")");
//                }
                tree.attachIndividual(individual);
            }
            tree.propagate();
        }



        public Individual getBestIndividual() {
            int bestId = tree.getBestId();

            for (Individual individual : individuals) {
                if (individual.id == bestId) {
                    return individual;
                }
            }
            return null;
        }

        public int size() {
            return individuals.size();
        }


        public Population evolve() {
            Population newPopulation = new Population();
            while (newPopulation.size() < individuals.size()) {
                Individual parent1 = selectParent();
                Individual parent2 = selectParent();
                Individual child = parent1.crossover(parent2);
                child.mutate(mutationRate, this.board);
                if (!child.isValid(board)) {
                    continue;
                }
                newPopulation.addIndividual(child);
            }
            return newPopulation;
        }

        public Individual selectParent() {
            Individual selectedParent = null;
            double totalFitness = individuals.stream().mapToDouble(i -> tree.getFitnessValue(i)).sum();
            double randomValue = Math.random() * totalFitness;

            for (Individual individual : individuals) {
                randomValue -= tree.getFitnessValue(individual);
                if (randomValue <= 0) {
                    selectedParent = individual;
                    break;
                }
            }

            return selectedParent;
        }

        public void printFitness() {
            tree.print(tree.root, 0);
        }
    }

    public class ReservationTree {
        private Node root;

        public HashMap<Integer, Integer> fitness;

        static public boolean isMaximizing = false;

        public ReservationTree() {
            root = new Node(null, 0, Double.NEGATIVE_INFINITY); // Initialize the root node
            fitness = new HashMap<>();
        }

        public int getBestId() {
            double bestValue = Double.NEGATIVE_INFINITY;
            int bestId = -1;
            for (Integer id : fitness.keySet()) {
                double value = fitness.get(id);
                if (value > bestValue) {
                    bestValue = value;
                    bestId = id;
                }
            }
            return bestId;
        }

        public void attachIndividual(Individual individual) {
            fitness.put(individual.id, 1);
            Node currentNode = root;
            int level = 0;
            double leafValue = individual.getFitness(); // Use fitness as the leaf value

            // Traverse the tree based on the individual's chromosome
            for (int[] move : individual.getChromosome()) {
                Node child = currentNode.getChild(move);

                if (child == null) {
                    child = new Node(move, level + 1, Double.NEGATIVE_INFINITY);
                    currentNode.addChild(child);
                }

                currentNode = child;
                level++;
            }
            currentNode.setLeafValue((int) leafValue);
            currentNode.addId(individual.id);
        }

        public void propagate() {
            calculateFitnessValues(this.root);
        }

        private double calculateFitnessValues(Node node) {
            if (node.isLeaf()) {
                return node.getLeafValue();
            } else {
                double maxChildValue = Double.NEGATIVE_INFINITY;
                List<Integer> maxIds = new ArrayList<>();
                double minChildValue = Double.POSITIVE_INFINITY;
                List<Integer> minIds = new ArrayList<>();

                for (Node child : node.getChildren()) {
                    double childValue = calculateFitnessValues(child);
                    List<Integer> childrenIds = child.getIds();

                    if (childValue > maxChildValue) {
                        maxChildValue = childValue;
                        maxIds.clear();
                        maxIds.addAll(childrenIds);
                    } else if (childValue == maxChildValue) {
                        maxIds.addAll(childrenIds);
                    }

                    if (childValue < minChildValue) {
                        minChildValue = childValue;
                        minIds.clear();
                        minIds.addAll(childrenIds);
                    } else if (childValue == minChildValue) {
                        minIds.addAll(childrenIds);
                    }
                }

                if (node.isMaxLevel()) {
                    node.setNodeValue(maxChildValue);
                    node.setId(maxIds);
                    for (Integer id : maxIds) {
                        fitness.put(id, fitness.get(id) + 1);
                    }
                } else {
                    node.setNodeValue(minChildValue);
                    node.setId(minIds);
                    for (Integer id : minIds) {
                        fitness.put(id, fitness.get(id) + 1);
                    }
                }

                return node.getNodeValue();
            }
        }

        public double getFitnessValue(Individual individual) {
            int indivId = individual.id;
            int fitness_ = fitness.get(indivId);
            return fitness_*fitness_;
        }

        private void print(Node node, int depth) {
            if (node == null) {
                node = root;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                sb.append("\t");
            }
            node.printMove();
            sb.append("Score: ").append(node.getNodeValue()).append(" ").append(" " + node.getLeafValue());
            System.out.print(sb.toString());
            System.out.println(" Individuals: " +  node.getIds().toString());
            for (Node child : node.getChildren()) {
                print(child, depth+1);
            }
        }

        private class Node {
            private int[] move;
            private int level;
            private double nodeValue;
            private double leafValue;
            private List<Node> children;
            private List<Integer> ids;

            public List<Integer> getIds() {
                return ids;
            }
            public void printMove() {
                if (move != null) {
                    System.out.print("Move: (" + move[0] + ", " + move[1] + ")");
                }
            }

            public void setId(List<Integer> ids) {
                this.ids = ids;
            }

            public void addId(Integer individualId) {
                ids.add(individualId);
            }

            public Node(int[] move, int level, double leafValue) {
                this.move = move;
                this.level = level;
                this.leafValue = leafValue;
                this.children = new ArrayList<>();
                this.ids = new ArrayList<>();
            }

            public int[] getMove() {
                return move;
            }


            public double getNodeValue() {
                return nodeValue;
            }

            public void setNodeValue(double nodeValue) {
                this.nodeValue = nodeValue;
            }

            public double getLeafValue() {
                return leafValue;
            }

            public boolean isLeaf() {
                return children.isEmpty();
            }

            public boolean isMaxLevel() {
                if (isMaximizing) {
                    return level % 2 == 0;
                } else {
                    return level % 2 == 1;
                }
            }

            public void setLeafValue(int value) {
                this.leafValue = value;
            }

            public List<Node> getChildren() {
                return children;
            }

            public Node getChild(int[] move) {
                for (Node child : children) {
                    if (java.util.Arrays.equals(child.getMove(), move)) {
                        return child;
                    }
                }
                return null;
            }

            public void addChild(Node child) {
                children.add(child);
            }


        }
    }

}
