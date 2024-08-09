/**
 * Ý tưởng cho Move và MoveHistory:
 *
 * Lưu lại lịch sử bàn đấu trên 1 bảng hiển thị. Có thể undo, redo khi chạm vào dòng lịch sử.
 *
 * Ý tưởng thiết kế:
 *
 * MoveHistory sẽ chứa một Array chứa Move và các hàm nhập xuất hoặc thay đổi thông tin (chưa rõ có cần thiết không?)
 *
 * Move sẽ chứa những thông tin cơ bản của hành động đã xảy ra và trạng thái ChessBoard sau hành động đó.
 * Thông tin cơ bản bao gồm quân cờ đã hành động trong lượt đó và những quân cờ chịu tác động sau đó (bị tấn công, được
 * buff, bị tiêu diệt). Move sẽ có hàm khởi tạo và hàm xuất ra string thuyết minh hành động đó. (VD "Mã trắng đi đến c4",
 * "Hậu đen dùng kĩ năng. Tốt trắng, tốt trắng bị tấn công, tượng trắng bị tiêu diệt"). Khi được khởi tạo, lập trình viên
 * phải tự đưa các giá trị cần thiết vào (sẽ phải thêm hàm hỗ trợ trong phần model và controller).
 *
 * PieceHistory là 1 phần của Move, gồm 1 quân cờ và trạng thái tác động. Khi được khởi tạo, lập trình viên phải tự gán
 * trạng thái cho quân cờ.
 */
package com.mygdx.game.moves;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.Piece;

public class Move {
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private PieceHistory movedPieceHistory;
    private Array<PieceHistory> affectedPieceHistory;
    private ChessBoard board;

    /**
     * A "move" contains details of the action taken this turn and the board state after it takes place.
     * Board state is there to enable undo and redo actions.
     * @param startX
     * @param endX
     * @param startY
     * @param endY
     * @param movedPieceHistory The piece the player interacted with this turn
     * @param affectedPieceHistory Any piece that was affected this turn
     * @param board
     */
    public Move(int startX, int endX, int startY, int endY,
                PieceHistory movedPieceHistory, Array<PieceHistory> affectedPieceHistory, ChessBoard board) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.movedPieceHistory = movedPieceHistory;
        this.affectedPieceHistory = affectedPieceHistory;
        this.board = board;
    }

    /**
     * Generate a string for display on the history board.
     * @return String to put on labels.
     */
    public String getMoveLine() {
        return "placeholder";
    }
}
