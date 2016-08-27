package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;

import java.util.LinkedList;

public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "serzhcello@ukr.net";

//    private static final String USER_NAME = Email.email;
    private Board board;

    @Override
    public String get(Board board) {
        this.board = board;

        Point head = board.getSnakeHead();
        Point apple = board.getApples().get(0);

        return getDirection(head, apple).toString();
    }

    private Direction getDirection(Point head, Point apple) {
        int whereAppleX = head.getX() - apple.getX();
        int whereAppleY = head.getY() - apple.getY();

        LinkedList directions = getDirections(whereAppleX, whereAppleY);

        Direction directionsFirst = (Direction) directions.getFirst();
        Direction clockwise = board.getSnakeDirection().clockwise();
        Direction anticlockwise = board.getSnakeDirection().clockwise().inverted();

        if (board.isEmptyPlaceOrApple(head, directionsFirst, board)) {
            return directionsFirst;
        }

        if (directions.size() == 1) {
            return clockWiseOrAntiClockOrSame(head, clockwise, anticlockwise);
        }
        Direction directionsLast = (Direction) directions.getLast();
        if (board.isEmptyPlaceOrApple(head, directionsLast, board)) {
            return directionsLast;
        }
        return clockWiseOrAntiClockOrSame(head, clockwise, anticlockwise);
    }

    private Direction clockWiseOrAntiClockOrSame(Point head, Direction clockwise, Direction anticlockwise) {
        if (board.isEmptyPlaceOrApple(head, clockwise, board)) {
            return clockwise;
        }
        if (board.isEmptyPlaceOrApple(head, anticlockwise, board)) {
            return anticlockwise;
        }
        return board.getSnakeDirection();
    }

    private LinkedList getDirections(int dx, int dy) {
        LinkedList result = new LinkedList();
        if (dx < 0) {
            result.add(Direction.RIGHT);
        }
        if (dx > 0) {
            result.add(Direction.LEFT);
        }
        if (dy < 0) {
            result.add(Direction.DOWN);
        }
        if (dy > 0) {
            result.add(Direction.UP);
        }
        return result;
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean dontInsideClosed(Point head, Direction direction) {
        int size = board.getSnake().size();
        for (int i = 0; i < size; i++) {
            // взять копию борда проходиться по пустым клеткам, заменяя их заполненными
            // и проверить, влезет ли змея

        }
        return false;
    }
/*
    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());

        return Direction.UP.toString();
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
