package astrochallenge;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author jhona
 */
public class Question {

    private int id;
    private String text;
    private int category;
    private ArrayList<Answer> answers;

    public Question(int id, String text, int category) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.answers = new ArrayList<Answer>();
    }

    public void loadAnswer(Connection con) {
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.prepareStatement("SELECT  answer.id, answer.text, answer.isRigth FROM question INNER JOIN answer on question.id=answer.question_id WHERE answer.question_id = ?");
            ps.setInt(1, this.id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Answer an = new Answer(rs.getInt("id"), rs.getString("text"), rs.getBoolean("isRigth"));
                this.answers.add(an);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query fallido" + e);
        }
    }
    
    public void randomizerAnswer(){
        Collections.shuffle(this.answers);
    }
    
    public boolean checkAnswer(int i){
        return this.answers.get(i).isRigth();
    }

    @Override
    public String toString() {
        return "Pregunta ronda " + category + "\n" + text + "\n Respuestas: \n" + "A" + answers.get(0) + "B" + answers.get(1)+ "C" + answers.get(2)+ "D" + answers.get(3);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

}
