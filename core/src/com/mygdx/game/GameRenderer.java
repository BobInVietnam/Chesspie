package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.chessboard.ChessBoard;
import com.mygdx.game.chesspieces.King;
import com.mygdx.game.chesspieces.Piece;
import com.mygdx.game.screens.GameplayScreen;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class GameRenderer {
  private SpriteBatch batch;
  private ChessBoard board;
  public Array<Piece> piecesList;
  private HashMap<Character, Integer> pieceToValue;
  private OrthographicCamera camera;
  private Viewport vp;

  private final Texture boardTexture;
  private final Texture pieces;
  private final TextureRegion colorRectangle;
  private final TextureRegion boardRegion;
  private final Array<TextureRegion> whitePieces;
  private final Array<TextureRegion> blackPieces;

  public boolean pieceChosen;
  public Piece chosenPiece;

  public GameRenderer(SpriteBatch batch, ChessBoard board, OrthographicCamera camera) {
    this.batch = batch;
    this.board = board;
    this.camera = camera;
    boardTexture = new Texture("Images/board_0.png");
    boardRegion = new TextureRegion(boardTexture, 0, 0, 2000, 2000);
    pieces = new Texture("Images/pieces.png");
    colorRectangle = new TextureRegion(pieces, 0, 0, 1, 1);

    // Pieces sprites
    whitePieces = new Array<>();
    blackPieces = new Array<>();
    for (int i = 0; i < 5; i++) {
      whitePieces.add(new TextureRegion(pieces, i * 400, 0, 400, 600));
      blackPieces.add(new TextureRegion(pieces, i * 400, 600, 400, 600));
    }
    whitePieces.add(new TextureRegion(pieces, 0, 1200, 400, 600));
    blackPieces.add(new TextureRegion(pieces,400, 1200, 400, 600));

    // Viewport stuffs


    // Piece-to-value map
    pieceToValue = new HashMap<>();
    pieceToValue.put('P', 0);
    pieceToValue.put('N', 1);
    pieceToValue.put('B', 2);
    pieceToValue.put('R', 3);
    pieceToValue.put('Q', 4);
    pieceToValue.put('K', 5);

    chosenPiece = null;
    pieceChosen = false;
  }

  public void draw(float delta) {
    ScreenUtils.clear(0.4f, 0.4f, 0.6f, 1);
    camera.update();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(boardRegion, GameplayScreen.cornerX, GameplayScreen.cornerY, 10, 10);
    if (pieceChosen) {
      float posX = GameplayScreen.cornerX + chosenPiece.getPosX();
      float posY = GameplayScreen.cornerY + chosenPiece.getPosY();
      batch.draw(colorRectangle, posX, posY, 1, 1);
      batch.setColor(1.0f, 0.0f, 0.0f, 0.2f);
      for (int i = 1; i <= 8; i++) {
        for (int j = 1; j <= 8; j++) {
          if (chosenPiece.canMove(board, i, j)) {
            posX = GameplayScreen.cornerX + i;
            posY = GameplayScreen.cornerY + j;
            batch.draw(colorRectangle, posX, posY, 1, 1);
          }
        }
      }
      batch.setColor(Color.WHITE);
    }
    drawPieces();
    batch.end();
  }

  public void dispose() {
    boardTexture.dispose();
    pieces.dispose();
  }

  private void drawPieces() {
    for (Piece piece: board.pieces) {
      float posX = GameplayScreen.cornerX + piece.getPosX();
      float posY = GameplayScreen.cornerY + piece.getPosY();
      int type = pieceToValue.get(piece.getSymbol());
      if (Objects.equals(piece.getColor(), "black"))
        batch.draw(blackPieces.get(type), posX, posY, 1, 1.5f);
      else batch.draw(whitePieces.get(type), posX, posY, 1, 1.5f);
    }
  }

}
