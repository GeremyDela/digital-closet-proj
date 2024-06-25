package model;

import model.functions.imageResizer;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class myOutfit implements Writable {
    private String outfitName;
    private ArrayList<String> keywords;
    private HashMap<String, String> photoIDs;
    private ArrayList<ArrayList<JLabel>> jLabels;
    private JButton delete = new JButton("delete outfit");
    private JButton select = new JButton("select outfit");

    private int accessory_count = 0;
    private int hat_count = 0;
    private int top_count = 0;
    private int bottom_count = 0;
    private int shoe_count = 0;

    public myOutfit(String name/*, ArrayList<myClothing> clothes*/) {
        outfitName = name;
        keywords = new ArrayList<>();
        jLabels = new ArrayList<>();
        photoIDs = new HashMap<>();
        instantiateJLabels();
    }

    public JButton getButton() {
        return this.delete;
    }

    public String getOutfitName() {
        return this.outfitName;
    }

    public JButton getSelect() {
        return this.select;
    }


    public void instantiateJLabels() {
        this.jLabels.add(0, new ArrayList<JLabel>(10));
        this.jLabels.add(1, new ArrayList<JLabel>(2));
        this.jLabels.add(2, new ArrayList<JLabel>(3));
        this.jLabels.add(3, new ArrayList<JLabel>(3));
        this.jLabels.add(4, new ArrayList<JLabel>(2));
    }


    public void addAccessory(String link) {
        try {
            imageResizer lol = null;
            JLabel image = new JLabel();
            lol = new imageResizer(link, 120, 120);
            image.setIcon(new ImageIcon(lol.getImage()));
            this.jLabels.get(0).add(image);
            accessory_count += 1;
            photoIDs.put("accessory " + Integer.toString(accessory_count), link);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addHat(String link) {
        try {
            imageResizer lol = null;
            JLabel image = new JLabel();
            lol = new imageResizer(link, 100, 100);
            image.setIcon(new ImageIcon(lol.getImage()));
            this.jLabels.get(1).add(image);
            hat_count += 1;
            photoIDs.put("hat " + Integer.toString(hat_count), link);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addTop(String link) {
        try {
            imageResizer lol = null;
            JLabel image = new JLabel();
            lol = new imageResizer(link, 200, 200);
            image.setIcon(new ImageIcon(lol.getImage()));
            this.jLabels.get(2).add(image);
            top_count += 1;
            photoIDs.put("top " + Integer.toString(top_count), link);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addBottom(String link) {
        try {
            imageResizer lol = null;
            JLabel image = new JLabel();
            lol = new imageResizer(link, 170, 170);
            image.setIcon(new ImageIcon(lol.getImage()));
            this.jLabels.get(3).add(image);
            bottom_count += 1;
            photoIDs.put("bottom " + Integer.toString(bottom_count), link);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addShoe(String link) {
        try {
            imageResizer lol = null;
            JLabel image = new JLabel();
            lol = new imageResizer(link, 100, 100);
            image.setIcon(new ImageIcon(lol.getImage()));
            this.jLabels.get(4).add(image);
            shoe_count += 1;
            photoIDs.put("shoe " + Integer.toString(shoe_count), link);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public JPanel getOutfitPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(200, 800));
        panel.add(delete);


        for (ArrayList<JLabel> temp : jLabels) {
            for (JLabel label : temp) {
                panel.add(label);
            }
        }


        return panel;
    }


    public JPanel getSecondOutfitPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(200, 800));
        panel.add(select);


        for (ArrayList<JLabel> temp : jLabels) {
            for (JLabel label : temp) {
                panel.add(label);
            }
        }


        return panel;
    }


    public JPanel getThirdOutfitPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(200, 800));


        for (ArrayList<JLabel> temp : jLabels) {
            for (JLabel label : temp) {
                panel.add(label);
            }
        }


        return panel;
    }


    public void updateKeywords(String keyword) {
        this.keywords.add(keyword);
    }


    public void addPieces(ArrayList<myClothing> clothes) {
        int accessory_count = 0;
        int hat_count = 0;
        int top_count = 0;
        int bottom_count = 0;
        int shoe_count = 0;


        for (myClothing piece : clothes) {
            JLabel image = new JLabel();
            imageResizer lol = null;


            if (Objects.equals(piece.getClothingType(), "accessory")) {
                ArrayList<JLabel> accessories = this.jLabels.get(0);


                try {
                    lol = new imageResizer(piece.getPhoto(), 120, 120);
                    accessory_count += 1;
                    photoIDs.put("accessory " + Integer.toString(accessory_count), piece.getPhoto());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                image.setIcon(new ImageIcon(lol.getImage()));
                accessories.add(image);


            } else if (Objects.equals(piece.getClothingType(), "hat")) {
                ArrayList<JLabel> hats = this.jLabels.get(1);
                try {
                    lol = new imageResizer(piece.getPhoto(), 100, 100);
                    hat_count += 1;
                    photoIDs.put("hat " + Integer.toString(hat_count), piece.getPhoto());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                image.setIcon(new ImageIcon(lol.getImage()));


                hats.add(image);


            } else if (Objects.equals(piece.getClothingType(), "top")) {
                ArrayList<JLabel> tops = this.jLabels.get(2);
                try {
                    lol = new imageResizer(piece.getPhoto(), 200, 200);
                    top_count += 1;
                    photoIDs.put("top " + Integer.toString(top_count), piece.getPhoto());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                image.setIcon(new ImageIcon(lol.getImage()));


                tops.add(image);


            } else if (Objects.equals(piece.getClothingType(), "bottom")) {
                ArrayList<JLabel> bottoms = this.jLabels.get(3);
                try {
                    lol = new imageResizer(piece.getPhoto(), 170, 170);
                    bottom_count += 1;
                    photoIDs.put("bottom " + Integer.toString(bottom_count), piece.getPhoto());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                image.setIcon(new ImageIcon(lol.getImage()));


                bottoms.add(image);


            } else if (Objects.equals(piece.getClothingType(), "shoe")) {
                ArrayList<JLabel> shoes = this.jLabels.get(4);
                try {
                    lol = new imageResizer(piece.getPhoto(), 100, 100);
                    shoe_count += 1;
                    photoIDs.put("shoe " + Integer.toString(shoe_count), piece.getPhoto());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                image.setIcon(new ImageIcon(lol.getImage()));


                shoes.add(image);
            }
        }
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("outfitName", outfitName);
        for (int x = 1; x <= 10; x++) {
            if (photoIDs.get("accessory " + Integer.toString(x)) != null) {
                json.put("accessory " + Integer.toString(x), photoIDs.get("accessory " + Integer.toString(x)));
            }
            if (photoIDs.get("hat " + Integer.toString(x)) != null) {
                json.put("hat " + Integer.toString(x), photoIDs.get("hat " + Integer.toString(x)));
            }
            if (photoIDs.get("top " + Integer.toString(x)) != null) {
                json.put("top " + Integer.toString(x), photoIDs.get("top " + Integer.toString(x)));
            }
            if (photoIDs.get("bottom " + Integer.toString(x)) != null) {
                json.put("bottom " + Integer.toString(x), photoIDs.get("bottom " + Integer.toString(x)));
            }
            if (photoIDs.get("shoe " + Integer.toString(x)) != null) {
                json.put("shoe " + Integer.toString(x), photoIDs.get("shoe " + Integer.toString(x)));
            }
        }

        return json;
    }
}

