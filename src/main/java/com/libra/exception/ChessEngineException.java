package com.libra.exception;

/**
 * The custom exceptions that describe undesired situations while running the game.
 */
public class ChessEngineException extends RuntimeException {
    public ChessEngineException(String message) {
        super(message);
    }
}
