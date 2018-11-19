package com.epam.races.controller;

public class Router {
    private enum MoveType{
        FORWARD,
        REDIRECT
    }
    private MoveType moveType = MoveType.FORWARD;

    public Router(MoveType moveType) {
        this.moveType = moveType;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setRedirect(){
        moveType = MoveType.REDIRECT;
    }

    public void setForward(){
        moveType = MoveType.FORWARD;
    }
}
