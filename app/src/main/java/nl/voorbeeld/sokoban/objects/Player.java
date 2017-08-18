package nl.voorbeeld.sokoban.objects;

import android.graphics.Point;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

/**
 * Created by jeffrey on 26-3-17.
 */
public class Player extends GameObject {
    public static final String PLAYER_IMAGE_FACE_DOWN = "character_face_down";
    public static final String PLAYER_IMAGE_FACE_LEFT = "character_face_left";
    public static final String PLAYER_IMAGE_FACE_RIGHT = "character_face_right";
    public static final String PLAYER_IMAGE_FACE_UP = "character_face_up";
    private boolean faceLeft = false;
    private boolean faceRight = false;
    private boolean faceDown = false;
    private boolean faceUp = false;

    public Point getStartPosition() {
        return startPosition;
    }

    private Point startPosition;

    public Player(Point startPosition) {
        super();
        this.startPosition = startPosition;
    }

    @Override
    public String getImageId() {
        if(faceLeft) {
            return PLAYER_IMAGE_FACE_LEFT;
        }else if(faceRight) {
            return PLAYER_IMAGE_FACE_RIGHT;
        }else if(faceUp){
            return PLAYER_IMAGE_FACE_UP;
        }else {
            return PLAYER_IMAGE_FACE_DOWN;
        }
    }

    public void setFaceLeft() {
        restoreFaceDirection();
        this.faceLeft = true;
    }
    public void setFaceUp() {
        restoreFaceDirection();
        this.faceUp = true;
    }

    public void setFaceRight() {
        restoreFaceDirection();
        this.faceRight = true;
    }

    public void setFaceDown() {
        restoreFaceDirection();
        this.faceDown = true;
    }
     public void restoreFaceDirection(){
         this.faceLeft = false;
         this.faceRight = false;
         this.faceUp = false;
         this.faceDown = false;
     }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
