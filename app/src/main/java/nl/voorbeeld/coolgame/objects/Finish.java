package nl.voorbeeld.coolgame.objects;

import android.graphics.Point;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

/**
 * Created by jeffrey on 26-3-17.
 */
public class Finish extends GameObject {


    public static final String FINISH_IMAGE = "cell_x";
    private Point startPosition;


    public Finish(Point startPosition) {
        super();
        this.startPosition = startPosition;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    @Override
    public String getImageId() {
        return FINISH_IMAGE;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
