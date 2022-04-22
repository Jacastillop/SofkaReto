/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrochallenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Game {

    private Player player;
    private Round rd;
    private Connection con;
    private boolean gameON;

    public Game() {
        this.player = null;
        this.con = null;
        this.rd = null;
        this.gameON = true;
    }

    public void start() {
        DBConexion db = new DBConexion();
        this.con = db.getConnection();
        String namePlayer = JOptionPane.showInputDialog("Ingresa tu nombre de jugador");

        this.player = new Player(namePlayer, 0);
        this.rd = new Round(1, con);
        rd.loadQuestion();
    }

    public void gameOver() {
        String message = (player.getScore() == 0) ? "Lo siento, tu puntaje final es de: " : "Muy bien tu puntaje final es de: ";
        JOptionPane.showMessageDialog(null, message + player.getScore());
        if (player.getScore() != 0) {
            saveScore();
        }

    }

    public void startRound() {
        Question question = rd.getRandomQuestion();
        int seleccion = JOptionPane.showOptionDialog(
                null,
                question,
                "AstroChallenge",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"A", "B", "C", "D"}, // null para YES, NO y CANCEL
                null);
        if (question.checkAnswer(seleccion)) {
            calculateScore(50 * this.rd.getLevel());
        } else {
            calculateScore(0);
            this.gameON = false;
        }
    }

    public void nextRound() {
        int currentLevel = this.rd.getLevel();
        if (currentLevel < 5) {
            this.rd = new Round(currentLevel + 1, con);
            rd.loadQuestion();
        } else {
            JOptionPane.showMessageDialog(null, "Excelente superaste la ultima ronda");
            this.gameON = false;
        }
    }

    public void wishContinue() {

        int currentLevel = this.rd.getLevel();
        if (currentLevel < 5) {
            String message = "Tu puntaje actual es de: " + player.getScore()
                    + "\n en la proxima ronda ganaras " + 50 * this.rd.getLevel()+ " puntos"
                    + "\n si escoges una respuesta equivocada perderas tu acumulado"
                    + "\n ¿Deseas continuar? ";

            int continuar = JOptionPane.showConfirmDialog(null, message,
                    "AstroChallenge", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (continuar == 1) {
                this.gameON = false;
            }
        }
    }

    public void calculateScore(int points) {
        if (points > 0) {
            int score = player.getScore();
            player.setScore(score + points);
        } else {
            player.setScore(0);
        }
    }

    public void saveScore() {
        PreparedStatement ps;
        String query = "INSERT INTO player (name, score) VALUES (?, ?)";  
        try {
            ps = this.con.prepareStatement(query);
            ps.setString(1, player.getName());
            ps.setInt(2, player.getScore());
            int respuesta =ps.executeUpdate();            
            if (respuesta>0) {
                System.out.println("Jugador guardado");                
            } else {
                System.out.println("Error al guardar datos"); 
            }            
        } catch (SQLException e) {
            System.out.println("Error al guardar datos" + e);
        }
    }
    
    public void showPlayerRanks(){
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT name,score FROM player ORDER BY score DESC LIMIT 10;";
        
        try {
            ps = this.con.prepareStatement(query);
            rs = ps.executeQuery();
            String message ="";
            while (rs.next()) {
                message+="\n name: "+ rs.getString("name")+ " Score: "+ rs.getInt("score");
            }
            JOptionPane.showMessageDialog(null,message,"Player Ranks",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query fallido" + e);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Round getRd() {
        return rd;
    }

    public void setRd(Round rd) {
        this.rd = rd;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public boolean isGameON() {
        return gameON;
    }

    public void setGameON(boolean gameON) {
        this.gameON = gameON;
    }

}
