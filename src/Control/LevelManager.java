package Control;

import Model.*;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Effects.*;
import Model.Tiles.*;
import View.EquipmentView;
import View.EquipmentViews.AxeView;
import View.EquipmentViews.BagView;
import View.EquipmentViews.CapeView;
import View.EquipmentViews.GloveView;
import View.GeneticCodeView;
import View.GeneticCodeViews.ImmunityView;
import View.GeneticCodeViews.MemoryLossView;
import View.GeneticCodeViews.StunningView;
import View.GeneticCodeViews.VitusDanceView;
import View.TileView;
import View.TileViews.LabView;
import View.TileViews.InfectedLabView;
import View.TileViews.ShelterView;
import View.TileViews.WarehouseView;
import View.VirologistView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class LevelManager {
    private List<Tile> tiles = new ArrayList<>();
    private List<Virologist> virologists = new ArrayList<>();
    private HashMap<String, GeneticCode> allGc = new HashMap<>();
    private List<GeneticCode> liveGcs = new ArrayList<>();

    //region Views
    private HashMap<Virologist, VirologistView> virogistViews = new HashMap<>();
    private HashMap<GeneticCode, GeneticCodeView> geneticCodeViews = new HashMap<>();
    private HashMap<Equipment, EquipmentView> equipmentViews = new HashMap<>();
    private HashMap<Tile, TileView> tileViews = new HashMap<>();
    //endregion

    private static LevelManager instance = new LevelManager();
    private LevelManager() {
        allGc.put("immunity", new GeneticCode(() -> new Immunity(1), new SubstanceContainer(20, 20), 2));
        allGc.put("stunning", new GeneticCode(() -> new Stunning(), new SubstanceContainer(20, 20), 2));
        allGc.put("memoryloss", new GeneticCode(() -> new MemoryLoss(), new SubstanceContainer(20, 20), 2));
        allGc.put("vitusdance", new GeneticCode(() -> new VitusDance(), new SubstanceContainer(20, 20), 2));
    }
    public static LevelManager getInstance() {
        return instance;
    }

    public List<GeneticCode> getGeneticCodes() { return liveGcs; }

    public VirologistView getVirologistView(Virologist v) {
        return virogistViews.get(v);
    }

    public GeneticCodeView getGeneticCodeView(GeneticCode gc) {
        return geneticCodeViews.get(gc);
    }

    public EquipmentView getEquipmentView(Equipment eq) {
        return equipmentViews.get(eq);
    }

    public TileView getTileView(Tile t) {
        return tileViews.get(t);
    }

    public void createLevel(String levelPath, int n) {
        generateTiles();
        addVirologists(n);
    }

    public void generateTiles() {
        tiles.clear();
        liveGcs.clear();
        geneticCodeViews.put(allGc.get("stunning"), new StunningView(allGc.get("stunning")));
        geneticCodeViews.put(allGc.get("immunity"), new ImmunityView(allGc.get("immunity")));
        geneticCodeViews.put(allGc.get("memoryloss"), new MemoryLossView(allGc.get("memoryloss")));
        geneticCodeViews.put(allGc.get("vitusdance"), new VitusDanceView(allGc.get("vitusdance")));

        // Generating new tiles

        InfectedLab il1 = new InfectedLab(allGc.get("stunning"), 50);
        createInfectedLabView(il1);
        liveGcs.add(allGc.get("stunning"));

        Tile t1 = new Tile();
        createTileView(t1);
        Tile t2 = new Tile();
        createTileView(t2);
        Tile t3 = new Tile();
        createTileView(t3);
        Tile t4 = new Tile();
        createTileView(t4);

        Lab l1 = new Lab(allGc.get("immunity"));
        createLabView(l1);
        liveGcs.add(allGc.get("immunity"));

        Lab l2 = new Lab(allGc.get("memoryloss"));
        createLabView(l2);
        liveGcs.add(allGc.get("memoryloss"));

        Lab l3 = new Lab(allGc.get("vitusdance"));
        createLabView(l3);
        liveGcs.add(allGc.get("vitusdance"));

        Warehouse w1 = new Warehouse();
        createWarehouseView(w1);
        Warehouse w2 = new Warehouse();
        createWarehouseView(w2);
        Warehouse w3 = new Warehouse();
        createWarehouseView(w3);

        Equipment glove = new Equipment(new Glove());
        Shelter sh1 = new Shelter(glove);
        createShelterView(sh1);
        equipmentViews.put(glove, new GloveView(glove));

        Equipment axe = new Equipment(new Axe());
        Shelter sh2 = new Shelter(axe);
        createShelterView(sh2);
        equipmentViews.put(axe, new AxeView(axe));

        Equipment cape = new Equipment(new Immunity(82));
        Shelter sh3 = new Shelter(cape);
        createShelterView(sh3);
        equipmentViews.put(cape, new CapeView(cape));

        Equipment bag = new Equipment(new Bag(2));
        Shelter sh4 = new Shelter(bag);
        createShelterView(sh4);
        equipmentViews.put(bag, new BagView(bag));

        // Setting neighbours
        il1.setNeighbours(t1);
        il1.setNeighbours(t2);
        il1.setNeighbours(w1);

        t1.setNeighbours(t2);
        t1.setNeighbours(l1);

        t2.setNeighbours(w1);
        t2.setNeighbours(sh1);

        w1.setNeighbours(sh1);
        w1.setNeighbours(sh2);

        sh1.setNeighbours(l1);
        sh1.setNeighbours(t3);

        sh2.setNeighbours(l2);

        l2.setNeighbours(t4);
        l2.setNeighbours(t3);

        t4.setNeighbours(t3);
        t4.setNeighbours(w3);
        t4.setNeighbours(sh3);

        w3.setNeighbours(l3);

        l3.setNeighbours(sh3);
        l3.setNeighbours(w2);

        sh3.setNeighbours(w2);
        sh3.setNeighbours(t3);
        sh3.setNeighbours(l2);

        l1.setNeighbours(sh4);
        l2.setNeighbours(w2);
        l1.setNeighbours(t3);

        w2.setNeighbours(sh4);

        // Adding the new tiles
        tiles.add(il1);
        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        tiles.add(t4);
        tiles.add(l1);
        tiles.add(l2);
        tiles.add(l3);
        tiles.add(w1);
        tiles.add(w2);
        tiles.add(w3);
        tiles.add(sh1);
        tiles.add(sh2);
        tiles.add(sh3);
        tiles.add(sh4);
    }

    public void addTiles(Collection<Tile> tiles) {
        tiles.addAll(tiles);
    }

    public void createTileView(Tile t) {
        tileViews.put(t, new TileView(t));
    }

    public void createLabView(Lab t) {
        tileViews.put(t, new LabView(t));
    }

    public void createInfectedLabView(InfectedLab t) {
        tileViews.put(t, new InfectedLabView(t));
    }

    public void createShelterView(Shelter t) {
        tileViews.put(t, new ShelterView(t));
    }

    public void createWarehouseView(Warehouse t) {
        tileViews.put(t, new WarehouseView(t));
    }

    public void addVirologists(int n) {
        virologists.clear();

        for (int i = 0; i < n; i++) {
            Virologist v = new Virologist(tiles.get((i * n) % tiles.size() + 1));
            virologists.add(v);
            virogistViews.put(v, new VirologistView(v, i));
        }
    }

    /**
     * Checks if the parameter genetic codes list contains all possible genetic codes.
     * If so, the game is over.
     *
     * @param gcs The list to be checked.
     */
    public void checkGeneticCodes(List<GeneticCode> gcs) {
        if (gcs.containsAll(liveGcs)) RoundManager.getInstance().finishGame();
    }

    /**
     * @return List of the tiles the level contains.
     */
    public List<Tile> getTiles(){
        return tiles;
    }

    /**
     * @return List of the virologist currently on the level.
     */
    public List<Virologist> getVirologists(){
        return virologists;
    }

    public void save(List<String> args){
        // TODO
    }

    public void load(List<String> args){
        // TODO
    }
}