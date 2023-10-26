package src;

import java.util.HashMap;
import java.util.Map;

public class Board {


    private final Map<Coordinate, Piece> pieces;
    private final int rows;
    private final int columns;

    public Board(int rows, int columns, Map<Coordinate, Piece> pieces){
        this.rows = rows;
        this.columns = columns;
        this.pieces = pieces;
    }



    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Map<Coordinate, Piece> getPieces() {
        return pieces;
    }

    public Board movePiece(Movement movement){
        // no editar map viejo
        Map<Coordinate, Piece> copyPieces = new HashMap<>(pieces);
        Coordinate origin = movement.getOrigin();
        Coordinate destination = movement.getDestination();
        Piece piece = copyPieces.get(origin);
        copyPieces.remove(origin);
        copyPieces.put(destination, piece);
        return new Board(rows, columns, new HashMap<>(copyPieces));
    }


    public Coordinate getCoordinateByPieceId(int targetPieceId) {
        for (Map.Entry<Coordinate, Piece> entry : pieces.entrySet()) {
            if (entry.getValue().getId() == targetPieceId) {
                return entry.getKey();
            }
        }
        return null; // nunca va a llegar a esto
    }


}
