package AI;

/**
 *
 * @author BusterK
 */

import java.awt.Dimension;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import model.*;

public class AI {
    final Model model;
    final Dimension dim;
    final Field[][] map;
    Field position;
    final Deque<Node> queue = new LinkedList<>();
    final LinkedList<Field> visited = new LinkedList<>();
    
    private StringBuilder path;
    
    boolean searching;
    
    public AI(Model model){
        this.model = model;
        this.dim = model.getDimension();
        this.map = model.getGameField();
        this.position = model.getSnakePosition();
        this.path = new StringBuilder();
        this.searching = true;
    }
    
    public char run() throws InterruptedException{
        this.position = model.getSnakePosition();
        this.queue.clear();
        this.visited.clear();
        searching = true;
        
        try{
            findRoute();
        }catch(ArrayIndexOutOfBoundsException e){
            model.setUseAI(false);
            this.searching = false;
            System.out.println("AI: I made a bobo (AI Crashed!):(");
        }
        

        char c = ' ';
        if (!(path.length() == 0)){
            c = path.charAt(0);
            path.deleteCharAt(0);
            if (!(path.length() == 0)){
                path.deleteCharAt(0);
            }
        }
        return c;
    }
    
    private void findRoute() {
            findApple();
            while(searching){
                    try{
                        findPathToApple(queue.getFirst());
                    }catch(NoSuchElementException e){
                        //No viable route found. 
                        this.queue.clear();
                        this.visited.clear();
                        this.path = new StringBuilder();
                        moveToNearestClearSpace();
                        this.searching = false;
                    }
            }
    }
    
    private void moveToNearestClearSpace(){
        if(model.getAvailableFields().contains(map[position.getWidth()+1][position.getHeight()])){
            this.path.append('E');
        }
            
        else if(model.getAvailableFields().contains(map[position.getWidth()-1][position.getHeight()])){
            this.path.append('W');
        }
            
        else if(model.getAvailableFields().contains(map[position.getWidth()][position.getHeight()+1])){
            this.path.append('S');
        }
            
        else if (model.getAvailableFields().contains(map[position.getWidth()][position.getHeight()-1])){
            this.path.append('N');
        }
        if (path.length() == 0)
            System.out.println("AI: No more routes, I'm dead!");
    }
    
    private void findPathToApple(Node head){
        queue.removeFirst();
        int i = (int) head.key.getWidth();
        int j = (int) head.key.getHeight();
        
        if(isWithinMap(i,j)){
            if(
                (model.getAvailableFields().contains(map[i+1][j]) ||
                    model.getSnakePosition() == map[i+1][j]) && 
                    !(visited.contains(map[i+1][j]))){
                createNode(head, i+1, j);
            }

            if(
                (model.getAvailableFields().contains(map[i][j+1]) ||
                    model.getSnakePosition() == map[i][j+1]) && 
                    !(visited.contains(map[i][j+1]))){
                createNode(head, i, j+1);
            }
            if(
                (model.getAvailableFields().contains(map[i-1][j]) ||
                    model.getSnakePosition() == map[i-1][j]) && 
                    !(visited.contains(map[i-1][j]))){
                createNode(head, i-1, j);
            }
            if(
                (model.getAvailableFields().contains(map[i][j-1]) ||
                    model.getSnakePosition() == map[i][j-1]) && 
                    !(visited.contains(map[i][j-1]))){
                createNode(head, i, j-1);
            }
        }      
    }
    
    private boolean isWithinMap(int i, int j){
        return !((i == dim.width) || (j == dim.height) || (i == 0) || (j == 0));
    }
    
    private void findApple(){
            for(int i = 0; i < model.getDimension().width; i++) {
                    for(int j = 0; j < model.getDimension().height; j++) {
                            if (map[i][j].getType() == Objects.APPLE){
                                    Node head = new Node(i,j);
                                    queue.addFirst(head);

                                    findPathToApple(queue.getFirst());
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
                    path = path.append("S ");	
            } else if(node.next.key.getHeight() == (node.key.getHeight() + 1)) {
                    path = path.append("N ");
            } else if(node.next.key.getWidth() == (node.key.getWidth() - 1)) {
                    path = path.append("E ");
            } else if(node.next.key.getWidth() == (node.key.getWidth() + 1)) {
                    path = path.append("W ");
            }
                    node = node.next;
            }
            this.path.reverse();
            this.path.deleteCharAt(0);
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