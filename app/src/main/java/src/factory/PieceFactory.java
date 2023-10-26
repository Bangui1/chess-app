package src.factory;

import src.Color;
import src.Piece;
import src.PieceType;
import src.validators.*;

public class PieceFactory {


    public Piece createWhitePawn(int id){

        Validator validator = new CompositeOrValidator(
                new CompositeAndValidator(
                        new VerticalMoveValidator(true),
                        new NotEatingValidator(),
                        new LimitedMoveValidator(1)
                ),
                new CompositeAndValidator(
                        new DiagonalMoveValidator(),
                        new EatingValidator(),
                        new LimitedMoveValidator(1)
                )
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);

    }

    public Piece createBlackPawn(int id){

        Validator validator = new CompositeOrValidator(
                new CompositeAndValidator(
                        new VerticalMoveValidator(false),
                        new NotEatingValidator(),
                        new LimitedMoveValidator(1)
                ),
                new CompositeAndValidator(
                        new DiagonalMoveValidator(),
                        new EatingValidator(),
                        new LimitedMoveValidator(1)
                )
        );
            return new Piece(id, Color.BLACK, PieceType.PAWN, validator);

    }

    public Piece createWhiteRook(int id){
        Validator validator = new CompositeOrValidator(
                new VerticalMoveValidator(true),
                new VerticalMoveValidator(false),
                new HorizontalMoveValidator(true),
                new HorizontalMoveValidator(false)
        );
        return new Piece(id, Color.WHITE, PieceType.ROOK, validator);
    }

    public Piece createBlackRook(int id){
        Validator validator = new CompositeOrValidator(
                new VerticalMoveValidator(true),
                new VerticalMoveValidator(false),
                new HorizontalMoveValidator(true),
                new HorizontalMoveValidator(false)
        );
        return new Piece(id, Color.BLACK, PieceType.ROOK, validator);
    }

    public Piece createWhiteKnight(int id){
        Validator validator = new CompositeOrValidator(
                new LMoveValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KNIGHT, validator);
    }

    public Piece createBlackKnight(int id){
        Validator validator = new CompositeOrValidator(
                new LMoveValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KNIGHT, validator);
    }

    public Piece createWhiteBishop(int id){
        Validator validator = new CompositeOrValidator(
                new DiagonalMoveValidator(),
                new DiagonalMoveValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.BISHOP, validator);
    }

    public Piece createBlackBishop(int id){
        Validator validator = new CompositeOrValidator(
                new DiagonalMoveValidator(),
                new DiagonalMoveValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.BISHOP, validator);
    }

    public Piece createWhiteQueen(int id){
        Validator validator = new CompositeOrValidator(
                new VerticalMoveValidator(true),
                new VerticalMoveValidator(false),
                new HorizontalMoveValidator(true),
                new HorizontalMoveValidator(false),
                new DiagonalMoveValidator(),
                new DiagonalMoveValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    public Piece createBlackQueen(int id){
        Validator validator = new CompositeOrValidator(
                new VerticalMoveValidator(true),
                new VerticalMoveValidator(false),
                new HorizontalMoveValidator(true),
                new HorizontalMoveValidator(false),
                new DiagonalMoveValidator(),
                new DiagonalMoveValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }

    public Piece createWhiteKing(int id){
        Validator validator = new CompositeOrValidator(
                new VerticalMoveValidator(true),
                new VerticalMoveValidator(false),
                new HorizontalMoveValidator(true),
                new HorizontalMoveValidator(false),
                new DiagonalMoveValidator(),
                new DiagonalMoveValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }

    public Piece createBlackKing(int id){
        Validator validator = new CompositeOrValidator(
                new VerticalMoveValidator(true),
                new VerticalMoveValidator(false),
                new HorizontalMoveValidator(true),
                new HorizontalMoveValidator(false),
                new DiagonalMoveValidator(),
                new DiagonalMoveValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }



}
