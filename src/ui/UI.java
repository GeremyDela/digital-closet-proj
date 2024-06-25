package ui;

import model.functions.imageDetector;
import model.functions.imageResizer;
import model.myCloset;
import model.myClothing;
import model.myOutfit;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UI extends JFrame {
    private myCloset thisCloset;
    private String JSON_STORE;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private CardLayout panelCards = new CardLayout();
    private JPanel mainPanel = new JPanel();
    private File file_path;
    private JFrame frame = new JFrame("｡･:*:･ﾟ★my digital closet｡･:*:･ﾟ☆");
    private List<JPanel> panels = new ArrayList<>();
    private ArrayList<String> audioPaths;

    private String carriedString;
    private String carriedString2;

    private int clothesShown = 6;
    private int outfitSlide = 0;
    private int slide = 0;

    private int deleteMode = 0;

    private int accessories = 0;
    private int hats = 0;
    private int tops = 0;
    private int bottoms = 0;
    private int shoes = 0;

    private int deleteModePressed = 0;

    private JPanel outfitMaker;
    private JPanel closetP;

    private ArrayList<JPanel> closetSlides = new ArrayList<>(10);
    private ArrayList<JPanel> outfitsSlides = new ArrayList<>(10);
    private ArrayList<myClothing> currentOutfit;

    private JPanel calendar = new JPanel();

    public UI() throws FileNotFoundException {
        setUpCards();
    }

    public void setUpCards() {
        currentOutfit = new ArrayList<>();
        panels = new ArrayList<>();
        instantiatePanels();
        initializeOutfitsSlides(outfitsSlides);
        instantiateAudios();

        mainPanel.setLayout(panelCards);
        instantiateWeekOutfits();

        mainMenu(panels.get(0));
        clothesPanel(panels.get(1));
        addClothingTransitionPanel(panels.get(2));
        addClothingPanel(panels.get(3));
        newClosetOptionalPanel(panels.get(4));
        outfitMaker(outfitMaker);
        outfitsPanel(panels.get(6));
        calendarSlide(calendar);

        for (int x = 1; x < 9; x++) {
            mainPanel.add(panels.get(x - 1), Integer.toString(x));
        }

        panelCards.show(mainPanel, "1");
        mainPanel.add(calendar, "calendar");

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void calendarSlide(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JLabel sunday = new JLabel("sun");
        JButton sundaySelect = new JButton("select outfit");
        JLabel monday = new JLabel("mon");
        JButton mondaySelect = new JButton("select outfit");
        JLabel tues = new JLabel("tue");
        JButton tuesSelect = new JButton("select outfit");
        JLabel wed = new JLabel("wed");
        JButton wedSelect = new JButton("select outfit");
        JLabel thurs = new JLabel("thu");
        JButton thursSelect = new JButton("select outfit");
        JLabel fri = new JLabel("fri");
        JButton friSelect = new JButton("select outfit");
        JLabel sat = new JLabel("sat");
        JButton satSelect = new JButton("select outfit");
        JButton returnLol = new JButton("return");

        sunday.setBounds(50, 10, 100, 20);
        sundaySelect.setBounds(0, 80, 100, 20);
        monday.setBounds(230, 10, 100, 20);
        mondaySelect.setBounds(230, 80, 100, 20);
        tues.setBounds(410, 10, 100, 20);
        tuesSelect.setBounds(410, 80, 100, 20);
        wed.setBounds(590, 10, 100, 20);
        wedSelect.setBounds(590, 80, 100, 20);
        thurs.setBounds(780, 10, 100, 20);
        thursSelect.setBounds(780, 80, 100, 20);
        fri.setBounds(950, 10, 100, 20);
        friSelect.setBounds(950, 80, 100, 20);
        sat.setBounds(1100, 10, 100, 20);
        satSelect.setBounds(1100, 80, 100, 20);

        panel.add(sunday);
        panel.add(monday);
        panel.add(tues);
        panel.add(wed);
        panel.add(thurs);
        panel.add(fri);
        panel.add(sat);
        panel.add(sundaySelect);
        panel.add(mondaySelect);
        panel.add(tuesSelect);
        panel.add(wedSelect);
        panel.add(thursSelect);
        panel.add(friSelect);
        panel.add(satSelect);

        sundaySelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 0;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(sundaySelect);
                repaint();
            }
        });

        mondaySelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 1;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(mondaySelect);
                repaint();
            }
        });

        tuesSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 2;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(tuesSelect);
                repaint();
            }
        });

        wedSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 3;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(wedSelect);
                repaint();
            }
        });

        thursSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 4;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(thursSelect);
                repaint();
            }
        });

        friSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 5;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(friSelect);
                repaint();
            }
        });

        satSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                day = 6;
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
                panel.remove(satSelect);
                repaint();
            }
        });

        returnLol.setBounds(0, 700, 100, 20);

        returnLol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                calendarViewing = 0;
                panelCards.show(mainPanel, "2");
            }
        });

        panel.add(returnLol);
    }

    private void showWeekOutfits() {
        calendar.removeAll();
        calendar.validate();
        calendar.repaint();
        calendarSlide(calendar);
        int positionx = 0;
        for (int x = 0; x < 7; x++) {
            if (weekOutfits.get(x) != null) {
                myOutfit outfit = weekOutfits.get(x);
                JPanel die = outfit.getThirdOutfitPanel();
                //die.setBackground(Color.blue);
                die.setBounds(positionx, 0, 170, 800);
                //System.out.println(weekOutfits.get(x).getOutfitName());
                JLabel yass = new JLabel("rawrr");
                yass.setBounds(positionx, 200, 100, 100);
                calendar.add(die);
                frame.validate();
                frame.repaint();
                System.out.println(x + "is not null");
            } else {
                System.out.println(x + "is null");
            }
            positionx += 170;
        }

        calendar.setBackground(Color.pink);
        frame.validate();
    }

    private void instantiateWeekOutfits() {
        for (int x = 0; x < 7; x++) {
            weekOutfits.add(x, null);
        }
    }

    private void instantiateAudios() {
        audioPaths = new ArrayList<>();
        audioPaths.add("./data/delete.wav");
        audioPaths.add("./data/buttonclick.wav");
        audioPaths.add("./data/mouseclick.wav");
        audioPaths.add("./data/hardwareinsert.wav");
        audioPaths.add("./data/hardwareremove.wav");
        audioPaths.add("./data/hardwarefail.wav");
        audioPaths.add("./data/ding.wav");
        audioPaths.add("./data/dingnoclick.wav");
    }

    private void playSoundEffect(int index) {
        try {
            Clip clip = AudioSystem.getClip();
            try {
                clip.open(AudioSystem.getAudioInputStream(new File(audioPaths.get(index)).getAbsoluteFile()));
            } catch (UnsupportedAudioFileException e) {
            }
            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                        clip.close();
                    }
                }
            });
            clip.setFramePosition(2);
            clip.start();
        } catch (LineUnavailableException e) {
        } catch (IOException e) {
        }
    }

    private void mainMenu(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.add(labelAdder("welcome to your digital closet!", 470, 400, 1000, 80, 20, "Serif"));
        panel.add(labelAdder("closet files", 550, 500, 1000, 80, 20, "Serif"));


        DefaultListModel<String> saveFileList = new DefaultListModel<>();
        saveFileList.addElement("closet file 1");
        saveFileList.addElement("closet file 2");
        saveFileList.addElement("closet file 3");
        saveFileList.addElement("closet file 4");


        JList<String> saveFiles = new JList<>(saveFileList);
        saveFiles.setBounds(565, 560, 70, 70);
        panel.add(saveFiles);


        JButton overwrite = new JButton("overwrite");
        JButton load = new JButton("load");


        overwrite.setBounds(500, 640, 200, 20);
        load.setBounds(500, 670, 200, 20);


        panel.add(overwrite);
        panel.add(load);


        overwrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                if (saveFiles.getSelectedIndex() != -1) {
                    if (Objects.equals(saveFiles.getSelectedValue(), "closet file 1")) {
                        JSON_STORE = "./data/closet1.json";
                        jsonWriter = new JsonWriter(JSON_STORE);
                        jsonReader = new JsonReader(JSON_STORE);
                        panelCards.show(mainPanel, "5");
                    } else if (Objects.equals(saveFiles.getSelectedValue(), "closet file 2")) {
                        JSON_STORE = "./data/closet2.json";
                        jsonWriter = new JsonWriter(JSON_STORE);
                        jsonReader = new JsonReader(JSON_STORE);
                        panelCards.show(mainPanel, "5");
                    } else if (Objects.equals(saveFiles.getSelectedValue(), "closet file 3")) {
                        JSON_STORE = "./data/closet3.json";
                        jsonWriter = new JsonWriter(JSON_STORE);
                        jsonReader = new JsonReader(JSON_STORE);
                        panelCards.show(mainPanel, "5");
                    } else if (Objects.equals(saveFiles.getSelectedValue(), "closet file 4")) {
                        JSON_STORE = "./data/closet4.json";
                        jsonWriter = new JsonWriter(JSON_STORE);
                        jsonReader = new JsonReader(JSON_STORE);
                        panelCards.show(mainPanel, "5");
                    }
                }
            }
        });


        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                if (saveFiles.getSelectedIndex() != -1) {
                    if (Objects.equals(saveFiles.getSelectedValue(), "closet file 1")) {
                        JSON_STORE = "./data/closet1.json";
                        loadCloset();
                        showClothes(0, 5, true);
                        panelCards.show(mainPanel, "2");
                    } else if (Objects.equals(saveFiles.getSelectedValue(), "closet file 2")) {
                        JSON_STORE = "./data/closet2.json";
                        loadCloset();
                        showClothes(0, 5, true);
                        panelCards.show(mainPanel, "2");
                    } else if (Objects.equals(saveFiles.getSelectedValue(), "closet file 3")) {
                        JSON_STORE = "./data/closet3.json";
                        loadCloset();
                        showClothes(0, 5, true);
                        panelCards.show(mainPanel, "2");
                    } else if (Objects.equals(saveFiles.getSelectedValue(), "closet file 4")) {
                        JSON_STORE = "./data/closet3.json";
                        loadCloset();
                        showClothes(0, 5, true);
                        panelCards.show(mainPanel, "2");
                    }
                }
            }
        });

        JLabel image = new JLabel();
        imageResizer resizer;

        try {
            resizer = new imageResizer("./data/IMG_8741.GIF", 1200, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        image.setIcon(new ImageIcon(resizer.getImage()));
        image.setBounds(0, 0, 1200, 800);
        panel.add(image);
    }

    private void pictureManager(JPanel panel, String key) {
        showClothes(0, 5, false);
        switch (key) {
            case "accessories":
                clothesShown = 1;
                showClothes(0, 1, true);
                return;
            case "hats":
                clothesShown = 2;
                showClothes(1, 2, true);
                return;
            case "tops":
                clothesShown = 3;
                showClothes(2, 3, true);
                return;
            case "bottoms":
                clothesShown = 4;
                showClothes(3, 4, true);
                return;
            case "shoes":
                clothesShown = 5;
                showClothes( 4, 5, true);
                return;
            case "all":
                clothesShown = 6;
                showClothes(0, 5, true);
        }
    }

    private void showClothes(int min, int max, boolean show) {
        int clothes = 0;
        int panelz = 0;
        for (int x = min; x < max; x++) {
            for (myClothing clothing : thisCloset.getCloset().get(x)) {
                JButton image = clothing.getButton();
                if (show) {
                    clothes += 1;
                    if (clothes == 7) {
                        clothes = 1;
                        panelz += 1;
                    }
                    closetSlides.get(panelz).add(image);
                } else {
                    for (JPanel temp : closetSlides) {
                        temp.remove(image);
                        frame.repaint();
                    }
                }
            }
        }
        frame.validate();
    }

    private void outfitMaker(JPanel panel) {
        panel.setLayout(null);
        JLabel label = new JLabel("enter outfit name:");
        panel.add(label);
        label.setBounds(140, 650, 400, 30);

        JTextField field;
        field = new JTextField(5);
        panel.add(field);
        field.setBounds(120, 680, 150, 30);

        JButton buttonOne = new JButton(new AbstractAction("confirm outfit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOutfit.isEmpty()) {
                    playSoundEffect(5);
                } else {
                    playSoundEffect(6);
                    myOutfit temp = new myOutfit(field.getText());
                    temp.addPieces(currentOutfit);
                    thisCloset.addOutfit(temp);
                    addActionSingle(temp);

                    accessories = 0;
                    hats = 0;
                    bottoms = 0;
                    tops = 0;
                    shoes = 0;
                    currentOutfit = new ArrayList<>();
                    panel.removeAll();
                    panel.updateUI();
                    outfitMaker(panel);
                }
            }
        });

        panel.add(buttonOne);
        buttonOne.setBounds(120, 715, 150, 30);
    }

    public void addPiece(myClothing piece) {
        outfitMaker.add(piece.getLabel());

        if (Objects.equals(piece.getClothingType(), "accessory")) {
            if (accessories != 10) {
                piece.getLabel().setBounds(20, 20, 100, 200);
                accessories += 1;
            }
        } else if (Objects.equals(piece.getClothingType(), "hat")) {
            if (hats != 2) {
                piece.getLabel().setBounds(140, 20, 100, 100);
                hats += 1;
            }
        } else if (Objects.equals(piece.getClothingType(), "top")) {
            if (tops != 3) {
                piece.getLabel().setBounds(120, 130, 200, 200);
                tops += 1;
            }
        } else if (Objects.equals(piece.getClothingType(), "bottom")) {
            if (bottoms != 3) {
                piece.getLabel().setBounds(120, 340, 170, 170);
                bottoms += 1;
            }
        } else if (Objects.equals(piece.getClothingType(), "shoe")) {
            if (shoes != 2) {
                piece.getLabel().setBounds(140, 510, 100, 100);
                shoes += 1;
            }
        }
        frame.validate();
        frame.repaint();
    }

    public void removePiece(myClothing piece) {
        outfitMaker.add(piece.getLabel());

        if (Objects.equals(piece.getClothingType(), "accessory")) {
            accessories -= 1;
        } else if (Objects.equals(piece.getClothingType(), "hat")) {
            hats -= 1;
        } else if (Objects.equals(piece.getClothingType(), "top")) {
            tops -= 1;
        } else if (Objects.equals(piece.getClothingType(), "bottom")) {
            bottoms -= 1;
        } else if (Objects.equals(piece.getClothingType(), "shoe")) {
            shoes -= 1;
        }
        frame.validate();
        frame.repaint();

        currentOutfit.remove(piece);
        outfitMaker.remove(piece.getLabel());
        frame.repaint();
        frame.validate();
    }

    private void outfitAddButtonSetter(myClothing piece) {
        piece.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (deleteMode != 1) {
                    if (!currentOutfit.contains(piece)) {
                        playSoundEffect(2);
                        currentOutfit.add(piece);
                        addPiece(piece);
                    } else {
                        playSoundEffect(4);
                        removePiece(piece);
                    }
                } else {
                    playSoundEffect(0);
                    showClothes(0, 5, false);
                    thisCloset.removePiece(piece);
                    switch (clothesShown) {
                        case 1:
                            showClothes(0, 1, true);
                        case 2:
                            showClothes(1, 2, true);
                        case 3:
                            showClothes(2, 3, true);
                        case 4:
                            showClothes(3, 4, true);
                        case 5:
                            showClothes(4, 5, true);
                        case 6:
                            showClothes(0, 5, true);
                    }
                }
            }
        });
    }

    private void setButtons() {
        for (ArrayList<myClothing> temp : thisCloset.getCloset()) {
            for (myClothing piece : temp) {
                outfitAddButtonSetter(piece);
            }
        }
    }

    private void buttonPanel(JPanel panel) {
        closetP = new JPanel();
        closetPanel(closetP);
        panel.setPreferredSize(new Dimension(200, 800));
        JButton viewAllClothes = new JButton("view all clothes");
        JButton addClothes = new JButton("add clothes");

        JButton viewOnlyAccessories = new JButton("view only accessories");
        JButton viewOnlyHats = new JButton("view only hats");
        JButton viewOnlyTops = new JButton("view only tops");
        JButton viewOnlyBottoms = new JButton("view only bottoms");
        JButton viewOnlyShoes = new JButton("view only shoes");

        JButton viewOutfits = new JButton("view outfits");

        JButton saveCloset = new JButton("save closet");
        JButton removeClothes = new JButton("remove clothes");
        panel.add(viewAllClothes);
        panel.add(addClothes);
        panel.add(removeClothes);

        panel.add(saveCloset);

        panel.add(viewOnlyAccessories);
        panel.add(viewOnlyHats);
        panel.add(viewOnlyTops);
        panel.add(viewOnlyBottoms);
        panel.add(viewOnlyShoes);

        panel.add(viewOutfits);

        removeClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (deleteModePressed == 0) {
                    playSoundEffect(3);
                    deleteMode = 1;
                    closetP.setBackground(Color.RED);
                    frame.repaint();
                    frame.revalidate();
                    deleteModePressed = 1;

                } else if (deleteModePressed == 1){
                    playSoundEffect(4);
                    deleteMode = 0;
                    closetP.setBackground(Color.PINK);
                    frame.repaint();
                    frame.revalidate();
                    deleteModePressed = 0;
                }
            }
        });

        viewOnlyAccessories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                pictureManager(closetP, "accessories");
            }
        });

        viewOnlyHats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                pictureManager(closetP, "hats");
            }
        });

        viewOnlyTops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                pictureManager(closetP, "tops");
            }
        });

        viewOnlyBottoms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                pictureManager(closetP, "bottoms");
            }
        });

        viewOnlyShoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                pictureManager(closetP, "shoes");
            }
        });

        viewAllClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                pictureManager(closetP, "all");
            }
        });

        addClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panelCards.show(mainPanel, "3");
                showClothes(0, 5, false);
            }
        });

        saveCloset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(3);
                saveFile();
            }
        });

        viewOutfits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                showOutfits(false);
                showOutfits(true);
                panelCards.show(mainPanel, "7");
            }
        });
    }

    private void initializeClosetSlides(ArrayList<JPanel> panels) {
        for (int o = 0; o < 9; o++) {
            JPanel panel = new JPanel();
            closetPanel(panel);
            panel.setBounds(400, 0, 800, 800);
            panels.add(panel);
        }
    }

    private void initializeOutfitsSlides(ArrayList<JPanel> panels) {
        for (int o = 0; o < 9; o++) {
            JPanel panel = new JPanel();
            outfitsP(panel);
            panel.setBounds(0, 0, 1000, 800);
            panels.add(panel);
        }
    }

    private void closetPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(800, 800));
        panel.setBackground(Color.PINK);
    }

    private void outfitsP(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(900, 800));
        panel.setLayout(new GridLayout(0,5));
        panel.setBackground(Color.WHITE);
    }

    private void clothesPanel(JPanel panel) {
        panel.setBackground(Color.PINK);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1200, 800));

        JButton next = new JButton(" > ");
        JButton previous = new JButton(" < ");

        next.setBounds(1150, 670, 50, 50);
        previous.setBounds(1100, 670, 50, 50);

        panel.add(next);
        panel.add(previous);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panel.remove(closetSlides.get(slide));
                frame.validate();
                frame.repaint();
                if (slide != 8) {
                    slide += 1;
                }
                panel.add(closetSlides.get(slide));
                frame.validate();
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panel.remove(closetSlides.get(slide));
                frame.validate();
                frame.repaint();
                if (slide != 0) {
                    slide -= 1;
                }
                panel.add(closetSlides.get(slide));
                frame.validate();
            }
        });

        JPanel buttonP = new JPanel();
        buttonPanel(buttonP);
        buttonP.setBounds(400, 720, 800, 400);
        panel.add(buttonP);
        initializeClosetSlides(closetSlides);
        panel.add(closetSlides.get(slide));
        outfitMaker.setBounds(0, 0, 400, 800);
        panel.add(outfitMaker);
    }

    private void outfitsPanel(JPanel panel) {
        panel.setBackground(Color.BLUE);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1200, 800));

        JButton next = new JButton(" > ");
        JButton previous = new JButton(" < ");

        next.setBounds(1050, 750, 50, 50);
        previous.setBounds(1000, 750, 50, 50);

        panel.add(next);
        panel.add(previous);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panel.remove(outfitsSlides.get(outfitSlide));
                frame.validate();
                frame.repaint();
                if (outfitSlide != 8) {
                    outfitSlide += 1;
                }
                panel.add(outfitsSlides.get(outfitSlide));
                frame.validate();
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panel.remove(outfitsSlides.get(outfitSlide));
                frame.validate();
                frame.repaint();
                if (outfitSlide != 0) {
                    outfitSlide -= 1;
                }
                panel.add(outfitsSlides.get(outfitSlide));
                frame.validate();
            }
        });

        JPanel buttonP = new JPanel();
        outfitButton(buttonP);
        buttonP.setBounds(1000, 0, 200, 800);
        panel.add(buttonP);
        panel.add(outfitsSlides.get(outfitSlide));
    }

    private void outfitButton(JPanel panel) {
        panel.setPreferredSize(new Dimension(200, 800));
        JButton buttonTwo = new JButton("return");
        JButton buttonThree = new JButton("view week's outfits");

        panel.add(buttonTwo);
        panel.add(buttonThree);

        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panelCards.show(mainPanel, "2");
            }
        });

        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                showWeekOutfits();
                playSoundEffect(1);
                panelCards.show(mainPanel, "calendar");
                calendarViewing = 1;
            }
        });
    }


    private void addAction() {
        for (myOutfit outfit : thisCloset.getOutfits()) {
            outfit.getButton().addActionListener(null);
            outfit.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    playSoundEffect(0);
                    showOutfits(false);
                    thisCloset.removeOutfit(outfit);
                    showOutfits(true);
                }
            });
            outfit.getSelect().addActionListener(null);
            outfit.getSelect().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    playSoundEffect(1);
                    if (!weekOutfits.contains(outfit)) {
                        //weekOutfits.add(day, outfit);
                        addToWeek(outfit, day);
                    }
                    showWeekOutfits();
                    panelCards.show(mainPanel, "calendar");
                }
            });
        }
    }

    private void addToWeek(myOutfit outfit, int index) {
        ArrayList<myOutfit> temp = new ArrayList<>(7);
        for (int x = 0; x < 7; x++) {
            temp.add(x, weekOutfits.get(x));
            if (index == x){
                temp.add(index, outfit);
            }
        }
        weekOutfits.clear();
        for (int x = 0; x < 7; x++) {
            weekOutfits.add(x, temp.get(x));
        }
    }

    private int day = 0;
    private ArrayList<myOutfit> weekOutfits = new ArrayList<>(7);

    private int calendarViewing = 0;

    private void addActionSingle(myOutfit outfit) {
        outfit.getButton().addActionListener(null);
        outfit.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(0);
                showOutfits(false);
                thisCloset.removeOutfit(outfit);
                showOutfits(true);
            }
        });
        outfit.getSelect().addActionListener(null);
        outfit.getSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                if (!weekOutfits.contains(outfit)) {
                    //weekOutfits.add(day, outfit);
                    addToWeek(outfit, day);
                }
                showWeekOutfits();
                panelCards.show(mainPanel, "calendar");
            }
        });
    }

    private void showOutfits(boolean show) {
        int clothes = 0;
        int panelz = 0;
        int pooop = 0;

        for (myOutfit outfit : thisCloset.getOutfits()) {
            if (show) {
                clothes += 1;
                if (clothes == 6) {
                    clothes = 1;
                    panelz += 1;
                    pooop = 0;
                }
                if (calendarViewing != 1) {
                    outfit.getOutfitPanel().setBounds(pooop, 0, 200, 800);
                    outfitsSlides.get(panelz).add(outfit.getOutfitPanel());
                } else {
                    outfit.getSecondOutfitPanel().setBounds(pooop, 0, 200, 800);
                    outfitsSlides.get(panelz).add(outfit.getSecondOutfitPanel());
                }
                pooop += 200;
            } else {
                for (JPanel temp : outfitsSlides) {
                    temp.remove(outfit.getOutfitPanel());
                    temp.removeAll();
                    temp.updateUI();
                    frame.repaint();
                }
            }
        }
        frame.validate();
    }

    private void addClothingTransitionPanel(JPanel panel) {
        GridLayout layout = new GridLayout(10, 1);
        panel.setLayout(layout);
        panel.add(new JLabel("select clothing type"));
        JButton buttonOne = new JButton("accessory");
        JButton buttonTwo = new JButton("hat");
        JButton buttonThree = new JButton("top");
        JButton buttonFour = new JButton("bottom");
        JButton buttonFive = new JButton("shoes");
        JButton buttonSix = new JButton("confirm");
        JButton buttonSeven = new JButton("return");

        JTextField field;
        field = new JTextField(5);
        panel.add(buttonOne);
        panel.add(buttonTwo);
        panel.add(buttonThree);
        panel.add(buttonFour);
        panel.add(buttonFive);

        panel.add(new JLabel("enter the piece name:"));
        panel.add(field);
        panel.add(buttonSix);
        panel.add(buttonSeven);

        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                carriedString = "accessory";
            }
        });

        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                carriedString = "hat";
            }
        });

        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                carriedString = "top";
            }
        });

        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                carriedString = "bottom";
            }
        });

        buttonFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                carriedString = "shoe";
            }
        });

        buttonSix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                carriedString2 = field.getText();
                if (Objects.equals(carriedString, "")) {
                    playSoundEffect(5);
                }
                if ((!Objects.equals(carriedString, ""))) {
                    playSoundEffect(1);
                    panelCards.show(mainPanel, "4");
                }
            }
        });

        buttonSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panelCards.show(mainPanel, "2");
                carriedString = "";
            }
        });
    }

    private void addClothingPanel(JPanel panel) {
        JButton buttonOne = new JButton("upload file");
        JButton button = new JButton("return");
        panel.add(buttonOne);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(1);
                panelCards.show(mainPanel, "3");
            }
        });

        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSoundEffect(2);
                JFileChooser file_upload = new JFileChooser();
                int res_2 = file_upload.showSaveDialog(null);
                if (res_2 == JFileChooser.APPROVE_OPTION) {
                    file_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                    System.out.println(file_path);
                    imageDetector detector = new imageDetector();
                    if (detector.isPicture(file_path.toString())) {
                        if (file_path != null) {
                            thisCloset.addClothing(carriedString2, carriedString, file_path.toString());
                            outfitAddButtonSetter(thisCloset.getRecentPiece());
                            carriedString = "";
                            carriedString2 = "";
                            file_path = null;
                            playSoundEffect(7);
                            showClothes(0, 5, false);
                            switch (clothesShown) {
                                case 1:
                                    showClothes(0, 1, true);
                                case 2:
                                    showClothes(1, 2, true);
                                case 3:
                                    showClothes(2, 3, true);
                                case 4:
                                    showClothes(3, 4, true);
                                case 5:
                                    showClothes(4, 5, true);
                                case 6:
                                    showClothes(0, 5, true);
                            }
                            panelCards.show(mainPanel, "2");
                        } else {
                            playSoundEffect(5);
                            panelCards.show(mainPanel, "4");
                        }
                    } else {
                        playSoundEffect(5);
                        panelCards.show(mainPanel, "4");
                    }
                }
            }
        });
    }

    private void newClosetOptionalPanel(JPanel panel) {
        panel.add(new JLabel("enter your name:"));

        JTextField field;
        field = new JTextField(5);
        panel.add(field);

        JButton buttonOne = new JButton(new AbstractAction("confirm name") {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSoundEffect(1);
                thisCloset = new myCloset(field.getText());
                panelCards.show(mainPanel, "2");
            }
        });

        panel.add(buttonOne);
    }

    private JLabel labelAdder(String string, int x, int y, int width, int size, int fontSize, String font) {
        JLabel text = new JLabel(string);
        text.setFont(new Font(font, Font.PLAIN, fontSize));
        text.setBounds(x, y, width, size);
        return text;
    }

    public void instantiatePanels() {
        outfitMaker = new JPanel();
        for (int x = 1; x < 9; x++) {
            panels.add(new JPanel());
        }
    }

    public void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(thisCloset);
            jsonWriter.close();
            System.out.println("Saved " + thisCloset.getName() + " to " + JSON_STORE);
        } catch (
                FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadCloset() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        try {
            thisCloset = jsonReader.read();
            setButtons();
            addAction();
            System.out.println("Loaded " + thisCloset.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}