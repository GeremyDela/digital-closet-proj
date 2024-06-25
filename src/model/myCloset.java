package model;

import model.functions.imageResizer;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class myCloset implements Writable {
    private String closetName;
    private ArrayList<ArrayList<myClothing>> closet;
    private ArrayList<myOutfit> outfits;
    private myClothing recentPiece;


    public myCloset(String name) {
        closetName = name;
        closet = new ArrayList<>(5);
        generateTypes();
        outfits = new ArrayList<>();
    }


    public ArrayList<myOutfit> getOutfits () {
        return this.outfits;
    }


    public ArrayList<ArrayList<myClothing>> getCloset() {
        return this.closet;
    }


    public String getName() {
        return this.closetName;
    }


    public void generateTypes() {
        this.closet.add(0, new ArrayList<myClothing>());
        this.closet.add(1, new ArrayList<myClothing>());
        this.closet.add(2, new ArrayList<myClothing>());
        this.closet.add(3, new ArrayList<myClothing>());
        this.closet.add(4, new ArrayList<myClothing>());
    }


    public myClothing getRecentPiece() {
        return this.recentPiece;
    }


    public void addOutfit(myOutfit outfit) {
        this.outfits.add(outfit);
    }


    public void removePiece(myClothing piece) {
        for (ArrayList<myClothing> tempCloset : closet) {
            tempCloset.remove(piece);
        }
    }


    public void removeOutfit(myOutfit piece) {
        outfits.remove(piece);
    }


    public void addClothing(String clothingName, String givenType, String photo) {
        myClothing piece = new myClothing(clothingName, givenType, photo);
        addPiece(piece);
    }


    public void updatePiece(myClothing piece, JButton button, JLabel label, ImageIcon buttonIcon, ImageIcon labelIcon) {
        piece.updateLabel(label);
        piece.updateButton(button);


        button.setIcon(buttonIcon);
        label.setIcon(labelIcon);


        piece.updateLabel(label);
        piece.updateButton(button);
    }


    public void addPiece(myClothing piece) {
        this.recentPiece = piece;
        JButton button = new JButton();
        JLabel label = new JLabel();
        imageResizer buttonIcon;
        imageResizer labelIcon;


        if (Objects.equals(piece.getClothingType(), "accessory")) {
            ArrayList<myClothing> accessories = this.closet.get(0);


            try {
                buttonIcon = new imageResizer(piece.getPhoto(), 200, 200);
                labelIcon = new imageResizer(piece.getPhoto(), 100, 100);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            updatePiece(piece, button, label, new ImageIcon(buttonIcon.getImage()), new ImageIcon(labelIcon.getImage()));
            accessories.add(piece);
        } else if (Objects.equals(piece.getClothingType(), "hat")) {
            ArrayList<myClothing> hats = this.closet.get(1);
            try {
                buttonIcon = new imageResizer(piece.getPhoto(), 125, 125);
                labelIcon = new imageResizer(piece.getPhoto(), 100, 100);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            updatePiece(piece, button, label, new ImageIcon(buttonIcon.getImage()), new ImageIcon(labelIcon.getImage()));
            hats.add(piece);


        } else if (Objects.equals(piece.getClothingType(), "top")) {
            ArrayList<myClothing> tops = this.closet.get(2);
            try {
                buttonIcon = new imageResizer(piece.getPhoto(), 250, 250);
                labelIcon = new imageResizer(piece.getPhoto(), 200, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            updatePiece(piece, button, label, new ImageIcon(buttonIcon.getImage()), new ImageIcon(labelIcon.getImage()));
            tops.add(piece);




        } else if (Objects.equals(piece.getClothingType(), "bottom")) {
            ArrayList<myClothing> bottoms = this.closet.get(3);
            try {
                buttonIcon = new imageResizer(piece.getPhoto(), 200, 200);
                labelIcon = new imageResizer(piece.getPhoto(), 170, 170);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            updatePiece(piece, button, label, new ImageIcon(buttonIcon.getImage()), new ImageIcon(labelIcon.getImage()));
            bottoms.add(piece);




        } else if (Objects.equals(piece.getClothingType(), "shoe")) {
            ArrayList<myClothing> shoes = this.closet.get(4);
            try {
                buttonIcon = new imageResizer(piece.getPhoto(), 125, 125);
                labelIcon = new imageResizer(piece.getPhoto(), 100, 100);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            updatePiece(piece, button, label, new ImageIcon(buttonIcon.getImage()), new ImageIcon(labelIcon.getImage()));
            shoes.add(piece);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", closetName);
        json.put("clothes", clothesToJson());
        json.put("outfits", outfitsToJson());
        return json;
    }

    private JSONArray clothesToJson() {
        JSONArray jsonArray = new JSONArray();


        for (int x = 0; x < 5; x++) {
            for (myClothing piece : closet.get(x)) {
                jsonArray.put(piece.toJson());
            }
        }
        return jsonArray;
    }


    private JSONArray outfitsToJson() {
        JSONArray jsonArray = new JSONArray();


        for (myOutfit outfit : outfits) {
            jsonArray.put(outfit.toJson());
        }


        return jsonArray;
    }

}
