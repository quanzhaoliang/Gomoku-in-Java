package com.mainpackage;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel {

    private final int GO_BOARD_WIDTH      = 15;
    private final int GO_BOARD_POS_XCOORD = 118;
    private final int GO_BOARD_POS_YCOORD = 118;
    private static GamePanel panel;

    private final int size = 26;
    private int clicks = 0;
    private final int WHITE = 1;
    private final int BLACK = -1;
    private JLabel player1Label;
    private JLabel player2Label;

    private JLabel playerToMove;

    private JLabel titleLabel;

    private JLabel blackPlayerTimeLabel;
    private JLabel whitePlayerTimeLabel;

    private JLabel elapsedTimeLabel;
    private HistoryPanel historyPanel;

    Cursor[][] cursors = new Cursor[GO_BOARD_WIDTH][GO_BOARD_WIDTH];

    public List<Piece> pieces = new ArrayList<Piece>();

    BoardMatrix boardMatrix = new BoardMatrix();
    Caretaker caretaker = new Caretaker();
    CurrentWatches currentWatches;
    WatchCaretaker watchCaretaker;
    Minimax minmax_ai;
    private Board board = new Board(GO_BOARD_WIDTH);

    private int Difficulty = 3;

    private boolean multiplay = true;
    private int currentplayer = BLACK;
    private PieceType currentPiece;

    Font PlayfairDisplay_Italic = null;
    Font Jost_BoldItalic = null;
    Font titleFont = null;


    public GamePanel()  {

        this.setLayout(null);
        this.setBackground(Colors.myDarkGrey);
        this.panel = this;


        //load the piece images
        PieceImage.load();

        createBoard();
        importFonts();
        createTitleLabel();
        createTextLabels();
        createPlayerLabels();
        createToMoveLabel();


        ButtonFactory creator = new ReturnToMainMenuButtonCreator();
        panel.add(creator.createButton());
        creator = new QuitButtonCreator();
        panel.add(creator.createButton());
        creator = new RestartButtonCreator();
        panel.add(creator.createButton());
        creator = new UndoButtonCreator();
        panel.add(creator.createButton());
        creator = new RulesButtonCreator();
        panel.add(creator.createButton());

        createHistoryPanel();
        createWatches();
        createBoardMouseListener();
        createCursor();

        // save initial empty board
        caretaker.push(boardMatrix.createState());

        //later, these should be moved into the mouseClicked event
//        currentWatches.blackPlayerWatch.start();
//        currentWatches.getWhitePlayerWatch().start();
        minmax_ai = new Minimax(board);
    }

    public static GamePanel getPanel() {
        return panel;
    }

    public void createHistoryPanel() {
        historyPanel = HistoryPanel.getInstance();
        this.add(historyPanel);
    }


    public void createBoard(){
        ImageGrid gameBoard = new ImageGrid();
        gameBoard.setBounds(100, 100, 400, 400);
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        this.add(gameBoard);
    }

    public void createWatches() {
        watchCaretaker = new WatchCaretaker();
        currentWatches = new CurrentWatches();
        currentWatches.getBlackPlayerWatch().setBounds(540, 105, 120, 35);
        currentWatches.getWhitePlayerWatch().setBounds(660, 105, 120, 35);
        this.add(currentWatches.getBlackPlayerWatch());
        this.add(currentWatches.getWhitePlayerWatch());
        watchCaretaker.push(currentWatches.createMemento());
    }

    public void updatePlayerLabels(){

        this.remove(player1Label);
        this.validate();
        this.repaint();
        this.remove(player2Label);
        this.validate();
        this.repaint();
        if (!multiplay) {
            player1Label = new JLabel("<html><B>Guest (Black)</B></html>", SwingConstants.CENTER);
        }
        else player1Label = new JLabel("<html><B>Guest 1 (Black)</B></html>", SwingConstants.CENTER);

        player1Label.setBounds(126, 510, 120, 30);
        player1Label.setBackground(Colors.myLightGrey);
        player1Label.setOpaque(true);

        if (!multiplay) {
            player2Label = new JLabel("<html><B>Computer (White)</B></html>", SwingConstants.CENTER);
        }
        else player2Label = new JLabel("<html><B>Guest 2 (White)</B></html>", SwingConstants.CENTER);

        player2Label.setBounds(126, 60, 130, 30);
        player2Label.setBackground(Colors.myLightGrey);
        player2Label.setOpaque(true);
        this.add(player1Label);
        this.add(player2Label);
        this.repaint();
    }

    public void importFonts(){
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/NotoSans-BlackItalic.ttf")).deriveFont(12f);
            PlayfairDisplay_Italic = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/PlayfairDisplay-Italic.ttf")).deriveFont(12f);
            Jost_BoldItalic = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Jost-BoldItalic.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Register the fonts

            ge.registerFont(titleFont);
            ge.registerFont(PlayfairDisplay_Italic);
            ge.registerFont(Jost_BoldItalic);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    public  void createTitleLabel() {
        titleLabel = new JLabel("<html><h1 " +
                "style='text-align:center; width:120px;font-size:18px'>" +
                "<FONT COLOR=#fecb87>" +
                "<strong>GOMOKU</strong>" +
                "<hr style='margin-top:0'></h1></html>");
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(330, 0, 150, 70);
        this.add(titleLabel);
    }

    public  void createPlayerLabels(){

        if (!multiplay) {
            player1Label = new JLabel("<html><B>Guest (Black)</B></html>", SwingConstants.CENTER);
        }
        else player1Label = new JLabel("<html><B>Guest 1 (Black)</B></html>", SwingConstants.CENTER);

        player1Label.setBounds(126, 510, 120, 30);
        player1Label.setBackground(Colors.myLightGrey);
        player1Label.setOpaque(true);

        if (!multiplay) {
            player2Label = new JLabel("<html><B>Computer (White)</B></html>", SwingConstants.CENTER);
        }
        else player2Label = new JLabel("<html><B>Guest 2 (White)</B></html>", SwingConstants.CENTER);

        player2Label.setBounds(126, 60, 130, 30);
        player2Label.setBackground(Colors.myLightGrey);
        player2Label.setOpaque(true);
        this.add(player1Label);
        this.add(player2Label);
    }

    public  void setBlackToMoveLabel() {
        this.remove(playerToMove);
        this.validate();
        this.repaint();
        playerToMove = new JLabel("<html><h1 style='text-align:left; width:140px;font-size:14px'>Black To Move</h1></html>", SwingConstants.CENTER);
        playerToMove.setBounds(600, 520, 140, 30);
        playerToMove.setForeground(Color.WHITE);
        playerToMove.setFont(PlayfairDisplay_Italic);
        this.add(playerToMove);
    }

    public  void setWhiteToMoveLabel() {
        this.remove(playerToMove);
        this.validate();
        this.repaint();
        playerToMove = new JLabel("<html><h1 style='text-align:left; width:140px;font-size:14px'>White To Move</h1></html>", SwingConstants.CENTER);
        playerToMove.setBounds(600, 520, 140, 30);
        playerToMove.setForeground(Color.WHITE);
        playerToMove.setFont(PlayfairDisplay_Italic);
        this.add(playerToMove);
    }

    public  void createToMoveLabel() {
        playerToMove = new JLabel("<html><h1 style='text-align:left; width:140px;font-size:14px'>Black To Move</h1></html>", SwingConstants.CENTER);
        playerToMove.setBounds(600, 520, 140, 30);
        playerToMove.setForeground(Color.WHITE);
        playerToMove.setFont(PlayfairDisplay_Italic);
        this.add(playerToMove);
    }
    public void createTextLabels() {

        blackPlayerTimeLabel = new JLabel("<html>Guest</html>" , SwingConstants.CENTER);
        blackPlayerTimeLabel.setBounds(540, 75, 120, 30);
        blackPlayerTimeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        blackPlayerTimeLabel.setBackground(Color.WHITE);
        blackPlayerTimeLabel.setOpaque(true);

        whitePlayerTimeLabel = new JLabel("<html>Computer</html>" , SwingConstants.CENTER);
        whitePlayerTimeLabel.setBounds(660, 75, 120, 30);
        whitePlayerTimeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        whitePlayerTimeLabel.setBackground(Color.WHITE);
        whitePlayerTimeLabel.setOpaque(true);

        elapsedTimeLabel = new JLabel("<html><h1 style='text-align:center; width:140px;font-size:14px'>Elapsed Time</h1></html>" , SwingConstants.CENTER);
        elapsedTimeLabel.setFont(Jost_BoldItalic);
        elapsedTimeLabel.setBounds(540, 45, 240, 30);
        elapsedTimeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        elapsedTimeLabel.setBackground(Colors.myLightGrey);
        elapsedTimeLabel.setOpaque(true);

        whitePlayerTimeLabel.setBackground(Color.WHITE);
        whitePlayerTimeLabel.setOpaque(true);


        this.add(blackPlayerTimeLabel);
        this.add(whitePlayerTimeLabel);
        this.add(elapsedTimeLabel);
    }

    public void updateTextLabels(){

        this.remove(blackPlayerTimeLabel);
        this.validate();
        this.repaint();
        this.remove(whitePlayerTimeLabel);
        this.validate();
        this.repaint();
        if (!multiplay) {
            blackPlayerTimeLabel = new JLabel("<html>Guest</html>", SwingConstants.CENTER);
        }
        else blackPlayerTimeLabel = new JLabel("<html>Guest 1 (Black)</html>", SwingConstants.CENTER);

        blackPlayerTimeLabel.setBounds(540, 75, 120, 30);
        blackPlayerTimeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        blackPlayerTimeLabel.setBackground(Color.WHITE);
        blackPlayerTimeLabel.setOpaque(true);

        if (!multiplay) {
            whitePlayerTimeLabel = new JLabel("<html>Computer</html>", SwingConstants.CENTER);
        }
        else whitePlayerTimeLabel = new JLabel("<html>Guest 2 (White)</html>", SwingConstants.CENTER);

        whitePlayerTimeLabel.setBounds(660, 75, 120, 30);
        whitePlayerTimeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        whitePlayerTimeLabel.setBackground(Color.WHITE);
        whitePlayerTimeLabel.setOpaque(true);

        this.add(blackPlayerTimeLabel);
        this.add(whitePlayerTimeLabel);
        this.repaint();
    }


    private void createBoardMouseListener() {
        PieceFactory pieceFactory = new PieceFactory();
        PieceFlyweightFactory flyweight = new PieceFlyweightFactory();
        String blackColor = flyweight.getBlack();
        BufferedImage blackImage = flyweight.getImage(blackColor);
        String whiteColor = flyweight.getWhite();
        BufferedImage whiteImage = flyweight.getImage(whiteColor);
        PieceType blackPiece = pieceFactory.createPiece(blackColor, blackImage);
        PieceType whitePiece = pieceFactory.createPiece(whiteColor, whiteImage);

        MouseAdapter mouseAdapter = new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (currentplayer == BLACK){
                    currentPiece = blackPiece;
                    if(!currentWatches.getBlackPlayerWatch().isActive()) currentWatches.getBlackPlayerWatch().start();
                }
                else {
                    currentPiece = whitePiece;
                    if(!currentWatches.getWhitePlayerWatch().isActive()) currentWatches.getWhitePlayerWatch().start();
                }
                // Get the coordinate of the mouse
                int x = e.getX();
                int y = e.getY();

                // Board coordinates
                int xCoordinate;
                int yCoordinate;


                Cursor cursor;


                for (int i = 0; i < GO_BOARD_WIDTH; i++) {
                    for (int j = 0; j < GO_BOARD_WIDTH; j++) {
                         cursor = cursors[i][j];
                         if (cursor.showCursor(x, y) && cursor.getOccupied() == 0){

                             //play the place piece sound
                             playSound("src/main/resources/pieceSound.wav");
                             //get board coordinates for black
                             xCoordinate = cursor.getJ();
                             yCoordinate = cursor.getI();

                             Piece piece = new Piece(cursor.getX()-10, cursor.getY()-10, currentPiece);
                             pieces.add(piece);

                             //set the current grid to be occupied
                             cursor.setOccupied(1);

                             BoardMatrix.setBoardPiecePositionMatrix(xCoordinate, yCoordinate, currentplayer);
                             if (!multiplay){
                                 board.addStoneNoGUI(yCoordinate, xCoordinate, true);
                             }

                             System.out.println("Black piece goes:");
                             BoardMatrix.printMatrix();
                             caretaker.push(boardMatrix.createState());
                             historyPanel.addMovement(xCoordinate, yCoordinate);

                             if (BoardMatrix.checkFiveInRow(currentplayer) == true){
                                 currentWatches.getWhitePlayerWatch().stop();
                                 currentWatches.getBlackPlayerWatch().stop();
                                 System.out.println("Black won!");
                                 playSound("src/main/resources/victoryff.wav");
                                 WinPopup popUp = new WinPopup(GameFrame.getInstance(), -1);
                                 popUp.setVisible(true);
                                 break;
                             }

                             //switch player when in multiplayer mode
                             if (multiplay){
                                 watchCaretaker.push(currentWatches.createMemento());
                                 if(currentWatches.getBlackPlayerWatch().isActive()) {
                                     currentWatches.getBlackPlayerWatch().stop();
                                     if(!currentWatches.getWhitePlayerWatch().isActive())
                                         currentWatches.getWhitePlayerWatch().start();
                                 }
                                 else if(currentWatches.getWhitePlayerWatch().isActive()) {
                                     currentWatches.getWhitePlayerWatch().stop();
                                     if(!currentWatches.getBlackPlayerWatch().isActive())
                                         currentWatches.getBlackPlayerWatch().start();
                                 }

                                 if (currentplayer == BLACK){
                                     currentplayer = WHITE;
                                     setWhiteToMoveLabel();
                                     currentPiece = whitePiece;
                                 }
                                 else{
                                     currentplayer = BLACK;
                                     setBlackToMoveLabel();
                                     currentPiece = blackPiece;
                                 }
                             }


                             // Request a repaint when the mouse is clicked
                             repaint();

                            if (!multiplay){
                                //When playing against the computer, the computer plays so quickly
                                //That updating the "To Move" label is not necessary
                                //setWhiteToMoveLabel();
                                watchCaretaker.push(currentWatches.createMemento());
                                currentWatches.getBlackPlayerWatch().stop();
                                currentWatches.getWhitePlayerWatch().start();
                                int bestMove[] = minmax_ai.calculateNextMove(getDifficulty());
                                int bestI = bestMove[1];
                                int bestJ = bestMove[0];
                                //create a cursor for the AI
                                Cursor cursorW = cursors[bestI][bestJ];
                                //set delay for the AI move
                                minmax_ai.getNext(panel, cursorW, whitePiece);
                                playSound("src/main/resources/pieceSound.wav");

                                //get board coordinates for white
                                xCoordinate = cursorW.getJ();
                                yCoordinate = cursorW.getI();

                                BoardMatrix.setBoardPiecePositionMatrix(xCoordinate, yCoordinate, WHITE);
                                board.addStoneNoGUI(yCoordinate, xCoordinate, false);
                                System.out.println("White piece goes:");
                                BoardMatrix.printMatrix();
                                caretaker.push(boardMatrix.createState());
                                historyPanel.addMovement(xCoordinate, yCoordinate);

                                currentWatches.getWhitePlayerWatch().stop();
                                currentWatches.getBlackPlayerWatch().start();
                                //setBlackToMoveLabel();
                                if (BoardMatrix.checkFiveInRow(WHITE) == true){
                                    currentWatches.getWhitePlayerWatch().stop();
                                    currentWatches.getBlackPlayerWatch().stop();
                                    System.out.println("White won!");
                                    playSound("src/main/resources/lose_sound.wav");
                                    WinPopup popUp = new WinPopup(GameFrame.getInstance(), 0);
                                    popUp.setVisible(true);
                                    break;
                                }
                            }

                             return;
                         }
                     }
                 }
            }

            //show the cursor when mouse moved to a valid coordinate
            public void mouseMoved(MouseEvent e){
                //get mouse coordinate
                int x = e.getX();
                int y = e.getY();
                Cursor cursor;
                for (int i = 0; i < GO_BOARD_WIDTH; i++) {
                    for (int j = 0; j < GO_BOARD_WIDTH; j++) {
                        cursor = cursors[i][j];
                        if (cursor.showCursor(x, y) && cursor.getOccupied() == 0){
                            cursor.setPresent(true);
                        }
                        else{
                            cursor.setPresent(false);
                        }
                    }
                }
                // Request a repaint when the mouse is clicked
                repaint();
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void paint(Graphics g){
        super.paint(g);
        drawDot(g);
        drawCursor(g);
        drawPiece(g);
    }

    private void drawDot(Graphics g) {
        super.paint(g);
        //draw top-left dot
        int dotDiameter = 8;
        int x = this.GO_BOARD_POS_XCOORD + size*3 - dotDiameter/2;
        int y = this.GO_BOARD_POS_YCOORD + size*3 - dotDiameter/2;
        g.fillArc(x, y, 8, 8, 0, 360);
        //draw top-right dot
        x =  this.GO_BOARD_POS_XCOORD + size*11 - dotDiameter/2;
        g.fillArc(x, y, 8, 8, 0, 360);
        //draw bottom-left dot
        x = this.GO_BOARD_POS_XCOORD + size*3 - dotDiameter/2;
        y = this.GO_BOARD_POS_YCOORD + size*11 - dotDiameter/2;
        g.fillArc(x, y, 8, 8, 0, 360);
        //draw bottom-right dot
        x=  this.GO_BOARD_POS_XCOORD + size*11 - dotDiameter/2;
        g.fillArc(x, y, 8, 8, 0, 360);
        //draw middle dot
        x = this.GO_BOARD_POS_XCOORD + size*7 - dotDiameter/2;
        y = this.GO_BOARD_POS_YCOORD + size*7 - dotDiameter/2;
        g.fillArc(x, y, 8, 8, 0, 360);
    }

    private void createCursor() {
        int x = 0;
        int y = 0;
        Cursor cursor;
        for (int i = 0; i < GO_BOARD_WIDTH; i++) {
            for (int j = 0; j < GO_BOARD_WIDTH; j++) {
                x = j*26 + this.GO_BOARD_POS_XCOORD;
                y = i*26 + this.GO_BOARD_POS_YCOORD;
                cursor = new Cursor(i, j, x, y);
                this.cursors[i][j] = cursor;
            }
        }
    }

    private void drawCursor(Graphics g) {
        Cursor cursor;
        for (int i = 0; i < GO_BOARD_WIDTH; i++) {
            for (int j = 0; j < GO_BOARD_WIDTH; j++) {
                cursor = this.cursors[i][j];
                if (cursor != null){
                    cursor.draw(g);
                }
            }
        }
    }

    private void drawPiece(Graphics g) {
        Piece piece;
        for (int i = 0; i < pieces.size(); i++){
            piece = pieces.get(i);
            piece.draw(g);
        }
    }

    public void playSound(String path){
        try{
            float gain; //sound gain in dB
            File soundFile = new File(path);
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            if (path == "src/main/resources/victoryff.wav") {
                gain = 20.0f;
            }
            else{
                gain = 10.0f;
            }
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-gain); // Reduce volume by [gain] decibels.
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetGame(){

        updatePlayerLabels();
        updateTextLabels();
        caretaker = new Caretaker();
        boardMatrix = new BoardMatrix();
        currentplayer = BLACK;
        board.boardRest();
        BoardMatrix.setZero();
        caretaker.push(boardMatrix.createState());

        this.pieces.clear();

        System.out.println("Board Matrix after reset:");
        BoardMatrix.printMatrix();
        for (int i = 0; i < GO_BOARD_WIDTH; i++) {
            for (int j = 0; j < GO_BOARD_WIDTH; j++) {
                cursors[i][j].setOccupied(0);
            }
        }
        this.historyPanel.resetPanel();
        repaint();
        currentWatches.getBlackPlayerWatch().reset();
        currentWatches.getWhitePlayerWatch().reset();
        watchCaretaker.push(currentWatches.createMemento());
    }


    public void undoMove(){

        //undo button will do nothing if board is empty
        int count = 0;
        int [][] matrix = BoardMatrix.getBoardPiecePositionMatrix();
        for (int i = 0; i < GO_BOARD_WIDTH; i++) {
            for (int j = 0; j < GO_BOARD_WIDTH; j++) {
                if (matrix[i][j] != 0) {
                    count++;
                }
            }
        }

        if (pieces.size() == 0)
            return;

        int listSize = this.pieces.size();
        Cursor cursor;

        if (!multiplay){

            Piece pieceW = pieces.get(listSize-1);
            Piece pieceB = pieces.get(listSize-2);

            //reset the cursors
            for (int i = 0; i < cursors.length; i++) {
                for (int j = 0; j < cursors[i].length; j++) {
                    cursor = cursors[i][j];
                    if ((cursor.getX() - 10 == pieceW.getX() && cursor.getY() - 10 == pieceW.getY()) ||
                            (cursor.getX() - 10 == pieceB.getX() && cursor.getY() - 10 == pieceB.getY())) {
                        board.removeStoneNoGUI(cursor.getI(), cursor.getJ());
                        cursor.setOccupied(0);
                    }
                }
            }

            //undo the pieces
            if (listSize >= 2) {
                pieces.remove(listSize - 1); // Remove the last element
                pieces.remove(listSize - 2); // Remove the second-to-last element (after the last element is removed)
            }
            BoardMatrix.restore(caretaker.pop());
            BoardMatrix.restore(caretaker.pop());
            count -= 2;
            if (count == 0){
                BoardMatrix.setZero();
            }
            BoardMatrix.printMatrix();

            // Undo CurrentWatches
            // this.currentWatches.restoreMemento(watchCaretaker.pop());
            //undo the history panel
            this.historyPanel.undoPanel();
        }

        else{
            Piece piece = pieces.get(listSize-1);

            //reset the cursors
            for (int i = 0; i < cursors.length; i++) {
                for (int j = 0; j < cursors[i].length; j++) {
                    cursor = cursors[i][j];
                    if ((cursor.getX() - 10 == piece.getX() && cursor.getY() - 10 == piece.getY())) {
                        cursor.setOccupied(0);
                    }
                }
            }

            //undo the pieces
            if (listSize >= 1) {
                pieces.remove(listSize - 1); // Remove the last element
            }
            BoardMatrix.restore(caretaker.pop());
            count -= 1;
            if (count == 0){
                BoardMatrix.setZero();
            }

            //undo the current player
            if (currentplayer == BLACK){
                currentplayer = WHITE;
                setWhiteToMoveLabel();
            }
            else{
                currentplayer = BLACK;
                setBlackToMoveLabel();
            }
            BoardMatrix.printMatrix();

            this.historyPanel.undoPanelMultiPlay();
        }

        repaint();
    }

    public void setDifficulty(int difficulty) {
        Difficulty = difficulty;
    }

    public int getDifficulty() {
        return Difficulty;
    }

    public void setMultiplay(boolean multiplay) {
        updatePlayerLabels();
        updateTextLabels();
        this.multiplay = multiplay;
    }

}

