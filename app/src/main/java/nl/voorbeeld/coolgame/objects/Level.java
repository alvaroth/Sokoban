package nl.voorbeeld.coolgame.objects;

import android.graphics.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jeffrey on 2-4-17.
 */
public class Level {

    private ArrayList<Point> boxPositions;
    private ArrayList<Point> wallPositions;
    private ArrayList<Point> finishPositions;
    private Point playerStartPosition;
    private Player player;

    public Level() {
        wallPositions = new ArrayList<Point>();
        boxPositions = new ArrayList<Point>();
        finishPositions = new ArrayList<Point>();
    }

    public Point getPlayerStartPosition() {
        return playerStartPosition;
    }

    public void setPlayerStartPosition(Point playerStartPosition) {
        this.playerStartPosition = playerStartPosition;
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
        boxPositions.add(point);
    }

    public void addFinishPositions(Point point) {
        finishPositions.add(point);
    }

    public ArrayList<Point> getWallPositions() {
        return wallPositions;
    }

    public ArrayList<Point> getBoxPositions() {
        return boxPositions;
    }

    public ArrayList<Point> getFinishPositions() {
        return finishPositions;
    }
}
