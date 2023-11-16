package src.chess.factory;

import src.common.PieceType;
import src.chess.validators.*;
import src.common.Color;
import src.common.Piece;
import src.common.validators.*;

public class PieceFactory {


    public Piece createWhitePawn(int id){

        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new VerticalMoveValidator(true),
                                new NotEatingValidator(),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(true),
                                new EatingValidator(),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new VerticalMoveValidator(true),
                                new NotEatingValidator(),
                                new LimitedMoveValidator(2),
                                new PieceFirstMoveValidator()
                        )
                )
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);

    }

    public Piece createBlackPawn(int id){

        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                    new CompositeAndValidator(
                            new VerticalMoveValidator(false),
                            new NotEatingValidator(),
                            new LimitedMoveValidator(1)
                    ),
                    new CompositeAndValidator(
                            new DiagonalMoveValidator(false),
                            new EatingValidator(),
                            new LimitedMoveValidator(1)
                    ),
                    new CompositeAndValidator(
                            new VerticalMoveValidator(false),
                            new NotEatingValidator(),
                            new LimitedMoveValidator(2),
                            new PieceFirstMoveValidator()
                    )
                )
        );
            return new Piece(id, Color.BLACK, PieceType.PAWN, validator);

    }

    public Piece createWhiteRook(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false)
                        ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.ROOK, validator);
    }

    public Piece createBlackRook(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false)
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.ROOK, validator);
    }

    public Piece createWhiteKnight(int id){
        Validator validator = new CompositeAndValidator(
                new LMoveValidator(),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KNIGHT, validator);
    }

    public Piece createBlackKnight(int id){
        Validator validator = new CompositeAndValidator(
                new LMoveValidator(),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KNIGHT, validator);
    }

    public Piece createWhiteBishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.BISHOP, validator);
    }

    public Piece createBlackBishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.BISHOP, validator);
    }

    public Piece createWhiteQueen(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    public Piece createBlackQueen(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }

    public Piece createWhiteKing(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                    new VerticalMoveValidator(true),
                    new VerticalMoveValidator(false),
                    new HorizontalMoveValidator(true),
                    new HorizontalMoveValidator(false),
                    new DiagonalMoveValidator(true),
                    new DiagonalMoveValidator(false)
                ),
                new LimitedMoveValidator(1),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }

    public Piece createBlackKing(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new LimitedMoveValidator(1),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }

    public Piece createWhiteArchbishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false),
                        new LMoveValidator()
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.ARCHBISHOP, validator);
    }

    public Piece createBlackArchbishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false),
                        new LMoveValidator()
                ),
                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.ARCHBISHOP, validator);
    }



}
