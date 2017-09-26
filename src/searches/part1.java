package searches;

import maze.Maze;
import maze.Node;

import java.io.IOException;
import java.util.*;

public class part1 {
    public static String bfs(Node root){
        Queue<Pair<Node, String>> queue = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        queue.add(new Pair<>(root,""));

        while(!queue.isEmpty()){
            Pair<Node, String> current = queue.poll();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node.getType()=='.'){
                return steps;
            }
            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft());
                queue.add(new Pair<>(node.getNorth(), steps+"n"));
                queue.add(new Pair<>(node.getSouth(), steps+"s"));
                queue.add(new Pair<>(node.getWest(), steps+"w"));
                queue.add(new Pair<>(node.getEast(), steps+"e"));
            }
        }
        return "";
    }


    public static String dfs(Node root){
        Stack<Pair<Node, String>> stack = new Stack<>();

        Set<Node> visited = new HashSet<>();
        stack.add(new Pair<>(root,""));

        while(!stack.isEmpty()) {
            Pair<Node, String> current = stack.pop();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if (node.getType() == '.') {
                return steps;
            }

            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft());
                stack.push(new Pair<>(node.getNorth(), steps+"n"));
                stack.push(new Pair<>(node.getSouth(), steps+"s"));
                stack.push(new Pair<>(node.getWest(), steps+"w"));
                stack.push(new Pair<>(node.getEast(), steps+"e"));
            }
        }
        return "";
    }

    public static void main(String[] args){
        try {
            Maze m = new Maze("mediumMaze");
            String solution = dfs(m.getRoot());
            System.out.println(solution.length());
            Node node = m.getRoot();
            while(!solution.isEmpty()){
                if(solution.charAt(0)=='n'){
                    node = node.getNorth();
                }
                if(solution.charAt(0)=='s'){
                    node = node.getSouth();
                }
                if(solution.charAt(0)=='w'){
                    node = node.getWest();
                }
                if(solution.charAt(0)=='e'){
                    node = node.getEast();
                }
                node.type = '.';
                solution = solution.substring(1);
            }
            m.printMaze();

        } catch (IOException e){
            System.out.print("Oops something wrong with Maze constructor");
            return;
        }
    }
}
