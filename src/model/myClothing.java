package model;

import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;

public class myClothing implements Writable {
    private String clothingName;
    private String clothingType;
    private String photo;
    private JButton button;
    private JLabel label;


    public myClothing(String clothingName, String givenType, String photo) {
        this.clothingName = clothingName;
        this.clothingType = givenType;
        this.photo = photo;
        this.button = new JButton();
        this.label = new JLabel();
    }


    public String getClothingName() {
        return this.clothingName;
    }


    public String getClothingType() {
        return this.clothingType;
    }


    public String getPhoto() {
        return this.photo;
    }


    public void updateButton(JButton givenButton) {
        this.button = givenButton;
    }


    public JButton getButton() {
        return this.button;
    }


    public void updateLabel(JLabel givenLabel) {
        this.label = givenLabel;
    }


    public JLabel getLabel() {
        return this.label;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("clothingName", clothingName);
        json.put("clothingType", clothingType);
        json.put("clothingPhoto", photo);
        return json;
    }

}
