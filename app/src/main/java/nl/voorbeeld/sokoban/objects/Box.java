package nl.voorbeeld.sokoban.objects;

import android.graphics.Point;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

/**
 * Created by jeffrey on 26-3-17.
 */
public class Box extends GameObject {
    public static final String BOX_IMAGE = "box";
    public static final String BOX_IMAGE_FINISH = "box_finish";

    public Point getStartPosition() {
        return startPosition;
    }

    private Point startPosition;

    public void setBoxFinished(boolean boxFinished) {
        this.boxFinished = boxFinished;
    }

    private boolean boxFinished = false;

    public Box(Point startPosition) {
        super();
        this.startPosition = startPosition;
    }

    @Override
    public String getImageId() {
        if(boxFinished){
            return BOX_IMAGE_FINISH;
        }
        return BOX_IMAGE;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }

    public boolean isBoxFinished() {
        return boxFinished;
    }
}
