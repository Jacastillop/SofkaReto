/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astrochallenge;

/**
 *
 * @author jhona
 */
public class Answer {
    private int id;
    private String text;
    private boolean isRigth;

    public Answer(int id, String text, boolean isRigth) {
        this.id = id;
        this.text = text;
        this.isRigth = isRigth;
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

    public boolean isRigth() {
        return isRigth;
    }

    public void setIsRigth(boolean isRigth) {
        this.isRigth = isRigth;
    }

    @Override
    public String toString() {
        return ": " + text + "\n";
    }
    
    
    
}
