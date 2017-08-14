package nl.voorbeeld.coolgame.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

/**
 * Created by jeffrey on 26-3-17.
 */
public class Box extends GameObject {
    public static final String BOX_IMAGE = "box";


    public Box() {
        super();
    }

    @Override
    public String getImageId() {
        return BOX_IMAGE;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
