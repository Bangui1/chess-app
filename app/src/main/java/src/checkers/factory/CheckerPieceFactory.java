package src.checkers.factory;

import src.checkers.validators.DiagonalMoveValidator;
import src.checkers.validators.HasEatenValidator;
import src.checkers.validators.NoFriendlyFireValidator;
import src.checkers.validators.NotBlockedValidator;
import src.common.validators.InboundsValidator;
import src.common.Color;
import src.common.Piece;
import src.common.PieceType;
import src.common.validators.CompositeAndValidator;
import src.common.validators.CompositeOrValidator;
import src.common.validators.LimitedMoveValidator;
import src.common.validators.Validator;

public class CheckerPieceFactory {

    public Piece createWhitePiece(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(true),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(true),
                                new LimitedMoveValidator(2),
                                new NoFriendlyFireValidator(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InboundsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);
    }

    public Piece createBlackPiece(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(false),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(false),
                                new LimitedMoveValidator(2),
                                new NoFriendlyFireValidator(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InboundsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);
    }

    public Piece createWhiteKing(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                new DiagonalMoveValidator(true),
                                          new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(2),
                                new NoFriendlyFireValidator(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InboundsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }
    
    public Piece createBlackKing(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(2),
                                new NoFriendlyFireValidator(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InboundsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }
}
