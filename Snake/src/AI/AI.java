package AI;

/**
 *
 * @author BusterK
 */

import java.awt.Dimension;
import java.util.Deque;
import java.util.LinkedList;
import model.*;

public class AI {
    final Model model;
    final Dimension dim;
    final Field[][] map;
    final Field position;
    final Deque<Node> queue = new LinkedList<>();
    final LinkedList<Field> visited = new LinkedList<>();
    
    StringBuilder path;
    boolean searching;
    
    public AI(Model model){
        this.model = model;
        this.dim = model.getDimension();
        this.map = model.getGameField();
        this.position = model.getSnakePosition();
        this.path = new StringBuilder();
        this.searching = true;
    }
    
    public void runAI() throws InterruptedException{
        findRoute();
        for(char c : path.toString().toCharArray()){
            while(!model.snakeHasTakenStep()){
            }
            model.changeSnakeDirection(c);
        }
    }
    
    private void findRoute() {
            findApple();
            while(searching){
                    BFS(queue.getFirst());
            }
    }
    
    private void BFS(Node head){
            queue.removeFirst();
            int i = (int) head.key.getHeight();
            int j = (int) head.key.getWidth();

            if(!(i == dim.width) && (model.getAvailableFields().contains(map[i+1][j])) && !(visited.contains(map[i+1][j]))){
                    createNode(head, i+1, j);
            }

            if(!(j == dim.height) && (model.getAvailableFields().contains(map[i][j+1])) &&(visited.contains(map[i][j+1]))){
                    createNode(head, i, j+1);
            }
            if(!(i == 0) && (model.getAvailableFields().contains(map[i-1][j])) &&(visited.contains(map[i-1][j]))){
                    createNode(head, i-1, j);
            }
            if(!(j == 0) && (model.getAvailableFields().contains(map[i][j-1])) &&(visited.contains(map[i][j-1]))){
                    createNode(head, i, j-1);
            }
    }
    
    private void findApple(){
            for(int i = 0; i < model.getDimension().width; i++) {
                    for(int j = 0; j < model.getDimension().height; j++) {
                            if (map[i][j].getType() == Objects.APPLE){
                                    Node head = new Node(i,j);
                                    queue.addFirst(head);

                                    BFS(queue.getFirst());
                            }
                    }
            }
    }
    
    private void createNode(Node head, int i, int j) {
            Node node = new Node(i,j);
            node.prev = head;
            if(map[i][j] == position){
                    generatePath(node);
            }else{
                    visited.add(map[i][j]);
                    queue.addLast(node);
            }	
    }
    
    private void generatePath(Node head) {
            Node node = head;

//		#### GO TROUGH NODES ###
            while(node.prev != null){
                    node.prev.next = node;
                    node = node.prev;
            }

//		#### GENERATE STRING ###
            while(node.next != null){
                    if(node.next.key.getHeight() == (node.key.getHeight() - 1)) {
                    path = path.append("W ");	
            } else if(node.next.key.getHeight() == (node.key.getHeight() + 1)) {
                    path = path.append("E ");
            } else if(node.next.key.getWidth() == (node.key.getWidth() - 1)) {
                    path = path.append("N ");
            } else if(node.next.key.getWidth() == (node.key.getWidth() + 1)) {
                    path = path.append("S ");
            }
                    node = node.next;
            }
            this.searching = false;
    }
}

class Node {
        String path = "";
        Field key;
        Node prev;
        Node next;

        public Node(int widht, int height) {
                this.key = new Field(widht,height);
        }
}