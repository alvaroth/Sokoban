package nl.game.sokoban.objects;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by jeffrey on 2-4-17.
 */
public class Level {

    private ArrayList<Point> boxStartPositions;
    private ArrayList<Point> wallPositions;
    private ArrayList<Finish> finishes;
    private ArrayList<Box> boxes;
    private Player player;
    private int turns;

    public Level() {
        wallPositions = new ArrayList<Point>();
        boxStartPositions = new ArrayList<Point>();
        boxes = new ArrayList<Box>();

        finishes = new ArrayList<Finish>();
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void addWallPositions(Point point) {
       wallPositions.add(point);
    }

    public void addFinishPositions(Finish finish) {
        finishes.add(finish);
    }

    public ArrayList<Point> getWallPositions() {
        return wallPositions;
    }

    public ArrayList<Point> getBoxStartPositions() {
        return boxStartPositions;
    }

    public ArrayList<Finish> getFinishes() {
        return finishes;
    }
}
