package model;

import java.util.LinkedList;

class Snake {
    private final LinkedList<Field> queue;
    private final Model model;
    private Field position;
    private char reverseDirection;
    private char direction;
    private boolean isReverseDirection;
    private boolean hasTakenStep;

    {
        hasTakenStep = true;
        isReverseDirection = false;
        queue = new LinkedList<>();
    }

    Snake(Model model) {
        int width = model.getDimension().width/2;
        int height = model.getDimension().height/2;
        
        this.model = model;
        Field field = model.getGameField()[width][height];
        queue.add(field);
        model.setFieldValue(Enumerators.TAIL, field);
        
        field = model.getGameField()[width-1][height];
        queue.add(field);
        model.setFieldValue(Enumerators.HEAD, field);
        position = field;
        setDirection('W');
//        this.direction = 'W';
//        this.reverseDirection = 'E';
//        this.isReverseDirection = false;
//        this.hasTakenStep = true;
    }

    Field getPosition() {
        return this.position;
    }

    private boolean hasGoneTroughEdge() {
        if(position.getHeight() == 0 && this.reverseDirection == 'S'){
            this.position = model.getGameField()[position.getWidth()][model.getDimension().height-1];
            return true;
        }
        if(position.getHeight() == model.getDimension().getHeight()-1 && this.reverseDirection == 'N'){
            this.position = model.getGameField()[position.getWidth()][0];
            return true;
        }
        if(position.getWidth() == 0 && this.reverseDirection == 'E'){
            this.position = model.getGameField()[model.getDimension().width-1][position.getHeight()];
            return true;
        }
        if(position.getWidth() == model.getDimension().getWidth()-1 && this.reverseDirection == 'W'){
            this.position = model.getGameField()[0][position.getHeight()];
            return true;
        }
        else
            return false;
    }
    
    private boolean hasHitWormHole(){
        return (position.getType() == Enumerators.WORMHOLE);
    }

    char getDirection() {
        return direction;
    }

    void setDirection(char direction) {
        if (this.reverseDirection == direction) {
//            this.isReverseDirection = true;
            this.hasTakenStep = true;
        } else if (this.hasTakenStep) {
            this.isReverseDirection = false;
            this.hasTakenStep = false;

            switch (direction) {
                case 'N':
                    this.reverseDirection = 'S';
                    this.direction = 'N';
                    break;
                case 'S':
                    this.reverseDirection = 'N';
                    this.direction = 'S';
                    break;
                case 'E':
                    this.reverseDirection = 'W';
                    this.direction = 'E';
                    break;
                case 'W':
                    this.reverseDirection = 'E';
                    this.direction = 'W';
                    break;
            }
        }
    }

    void walk(int widht, int height) {
        if(isReverseDirection){
            //Do nothing at the moment.
            this.hasTakenStep = true;
            }
        else{
            this.hasTakenStep = true;
            Field oldPosition = position;
            //Check if next position is at the other edge of the screen.
            if (!hasGoneTroughEdge()) {
                position = model.getGameField()[position.getWidth()+widht][position.getHeight()+height];
            }
            if(hasHitWormHole()){
                for (Field wh : model.getWormHoles()){
                    if ((position != wh) && (wh.getWhNumber() == position.getWhNumber())) {
                        position = wh;
                        break;
                    }       
                }
            }
            
            queue.add(position);
            //Before marking the field as a snake, check if there's and apple, snake or wall there.
            
            switch(position.getType()){
                case APPLE:
                    model.setFieldValue(Enumerators.HEAD, position);
                    model.setFieldValue(Enumerators.SNAKE, oldPosition);
                    model.setFieldValue(Enumerators.TAIL, queue.getFirst());
                    model.newApple();
                    break;
                case WALL:
                    model.setGameOver();
                    break;
                case SNAKE:
                    model.setGameOver();
                    break;
                default:
                    model.setFieldValue(Enumerators.BLANK, queue.getFirst());
                    model.setFieldValue(Enumerators.HEAD, position);
                    model.setFieldValue(Enumerators.SNAKE, oldPosition);
                    queue.removeFirst();
                    model.setFieldValue(Enumerators.TAIL, queue.getFirst());
                    break;  
            }
        }
    }
}

