package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.chessBoard.ChessBoard;
import com.mygdx.game.chessPieces.King;
import com.mygdx.game.chessPieces.Piece;
import com.mygdx.game.screens.GameplayScreen;

import java.util.HashMap;
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
  private TextureRegion boardRegion;
  private final Array<TextureRegion> whitePieces;
  private final Array<TextureRegion> blackPieces;

  public GameRenderer(SpriteBatch batch, ChessBoard board, OrthographicCamera camera) {
    this.batch = batch;
    this.board = board;
    this.camera = camera;
    boardTexture = new Texture("Images/board_0.png");
    boardRegion = new TextureRegion(boardTexture, 0, 0, 2000, 2000);
    pieces = new Texture("Images/pieces.png");

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

    // TESTING: Add pieces to board
    piecesList = new Array<>();
    piecesList.add(new King(2, 3, "white"));
    piecesList.add(new King(6, 6, "black"));
  }

  public void draw(float delta) {
    ScreenUtils.clear(0.4f, 0.4f, 0.6f, 1);
    camera.update();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    batch.draw(boardRegion, camera.viewportWidth/2 - 5, 1, 10, 10);
    drawPieces();
    batch.end();
  }

  public void dispose() {
    boardTexture.dispose();
    pieces.dispose();
  }

  private void drawPieces() {
    for (Piece piece: piecesList) {
      float posX = GameplayScreen.cornerX + piece.getPosX();
      float posY = GameplayScreen.cornerY + piece.getPosY();
      int type = pieceToValue.get(piece.getSymbol());
      if (Objects.equals(piece.getColor(), "black"))
        batch.draw(blackPieces.get(type), posX, posY, 1, 1.5f);
      else batch.draw(whitePieces.get(type), posX, posY, 1, 1.5f);
    }
  }

}
