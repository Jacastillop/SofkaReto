/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrochallenge;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author jhona
 */
public class Round {

    private int level;
    private ArrayList<Question> questionsByLevel;
    private final Connection con;

    public Round(int level,Connection con) {
        this.level = level;
        this.con =con;
        this.questionsByLevel = new ArrayList<Question>();
    }

    public void loadQuestion() {

        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT  question.id, question.text, question.category FROM question WHERE question.category = ?";
        
        try {
            ps = this.con.prepareStatement(query);
            ps.setInt(1, this.level);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String text = rs.getString("text");
                int category = rs.getInt("category");
                Question qs = new Question(id,text,category);
                
                qs.loadAnswer(con);
                qs.randomizerAnswer();
                this.questionsByLevel.add(qs);
            }            
            ps.close();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query fallido" + e);
        }

    }

    public Question getRandomQuestion() {
        Random ranMethod= new Random();
        int r = ranMethod.nextInt(this.questionsByLevel.size());
        return this.questionsByLevel.get(r);
    }


    @Override
    public String toString() {
        return "Round{" + "level=" + level + "\n Preguntas" + questionsByLevel + '}';
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Question> getQuestionsByLevel() {
        return questionsByLevel;
    }

    public void setQuestionsByLevel(ArrayList<Question> questionsByLevel) {
        this.questionsByLevel = questionsByLevel;
    }

}
