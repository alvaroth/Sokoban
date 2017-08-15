package nl.voorbeeld.coolgame.objects;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by jeffrey on 2-4-17.
 */
public class Level {

    private ArrayList<Point> boxStartPositions;
    private ArrayList<Point> wallPositions;
    private ArrayList<Point> finishPositions;
    private ArrayList<Box> boxes;
    private Point playerStartPosition;
    private Player player;

    public Level() {
        wallPositions = new ArrayList<Point>();
        boxStartPositions = new ArrayList<Point>();
        boxes = new ArrayList<Box>();

        finishPositions = new ArrayList<Point>();
    }

    public Point getPlayerStartPosition() {
        return playerStartPosition;
    }

    public void setPlayerStartPosition(Point playerStartPosition) {
        this.playerStartPosition = playerStartPosition;
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

    public void addWallPositions(Point point) {
       wallPositions.add(point);
    }

    public void addBoxPositions(Point point) {
        boxStartPositions.add(point);
    }

    public void addFinishPositions(Point point) {
        finishPositions.add(point);
    }

    public ArrayList<Point> getWallPositions() {
        return wallPositions;
    }

    public ArrayList<Point> getBoxStartPositions() {
        return boxStartPositions;
    }

    public ArrayList<Point> getFinishPositions() {
        return finishPositions;
    }
}
