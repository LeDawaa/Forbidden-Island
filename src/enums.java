import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

enum WaterStage {
    DRY, FLOODED, SUBMERGED;

    public BufferedImage texture;

    private WaterStage() {
        try {
            this.texture = ImageIO.read(new File("res\\images\\zones\\extra\\Tile_Flood_Water@2x.png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
};

enum Case {
    BreakersBridge("Breakers Bridge"), BronzeGate("Bronze Gate"), CaveOfEmbers("Cave of Embers"),
    CaveOfShadows("Cave of Shadows"), CliffsOfAbandon("Cliffs of Abandon"), CopperGate("Copper Gate"),
    CoralPalace("Coral Palace"), CrimsonForest("Crimson Forest"), DunesOfDeception("Dunes of Deception"),
    FoolsLanding("Fools' Landing"), GoldGate("Gold Gate"), HowlingGarden("Howling Garden"),
    IronGate("Iron Gate"), LostLagoon("Lost Lagoon"), MistyMarsh("Misty Marsh"),
    Observatory("Observatory"), PhantomRock("Phantom Rock"), SilverGate("Silver Gate"),
    TempleOfTheMoon("Temple of the Moon"), TempleOfTheSun("Temple of the Sun"), TidalPalace("Tidal Palace"),
    TwilightHollow("Twilight Hollow"), Watchtower("Watchtower"), WhisperingGarden("Whispering Garden"),
    TileMoat("extra\\Tile_Moat");

    public BufferedImage texture;
    public BufferedImage flooded_texture;

    private Case(String Path) {
        try {
            this.texture = ImageIO.read(new File("res\\images\\zones\\" + Path + "@2x.png"));
            if (!(Path.equals("extra\\Tile_Moat"))) {
                this.flooded_texture = ImageIO.read(new File("res\\images\\zones\\" + Path + "_flood@2x.png"));
            }
        } catch (IOException ie) {
            ie.printStackTrace();
            System.out.println(Path);
        }
    }
}

enum Pawns {
    Black("Diver_Adventurer_Icon"), Yellow("Navigator_Adventurer_Icon"), Green("Explorer_Adventurer_Icon"),
    White("Messenger_Adventurer_Icon");

    public BufferedImage texture;
    public BufferedImage selected_texture;

    private Pawns(String Path) {
        try {
            this.texture = ImageIO.read(new File("res\\images\\pion\\" + Path + "@2x.png"));
            this.selected_texture = ImageIO.read(new File("res\\images\\pion\\" + Path + "Select@2x.png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}

enum Keys {
    EarthKey, WaterKey, FireKey, WindKey;

    public String toString() {
        return this.name();
    }
};

enum Artifacts {
    EarthArtifact("TERRE"), WaterArtifact("EAU"), FireArtifact("FEU"), WindArtifact("AIR");

    public BufferedImage texture;
    public BufferedImage card_texture;


    private Artifacts(String Path) {
        try {
            this.texture = ImageIO.read(new File("res\\images\\tresors\\" + Path + ".png"));
            this.card_texture = ImageIO.read(new File("res\\images\\cles\\" + Path + ".png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public String toString() {
        return this.name();
    }

    public Keys associatedKey() {
        Keys key;
        switch (this) {
            case EarthArtifact -> key = Keys.EarthKey;
            case FireArtifact -> key = Keys.FireKey;
            case WindArtifact -> key = Keys.WindKey;
            case WaterArtifact -> key = Keys.WaterKey;
            default -> key = null;
        } return key;
    }
};

enum Direction {
    UP, DOWN, STAY, LEFT, RIGHT
};

enum Items {
    SandBag
};