package Model;

/**
 * This class handles the usage and storing of the two different substances
 * available in the game.
 */
public class SubstanceContainer {
    private int nucleotid;
    private int aminoAcid;
    private int capacity; // a kapacitás külön-külön vonatkozik a kétféle anyagra

    /**
     * Creates a new instance of a substance container using the constructor parameters.
     *
     * @param aminoAcid The amount of amino acid stored in the container.
     * @param nucleotid The amount of nucleotid stored in the container.
     * @param capacity The maximum capacity of the different kinds of substance.
     */
    public SubstanceContainer(int aminoAcid, int nucleotid, int capacity){
        this.nucleotid = nucleotid;
        this.aminoAcid = aminoAcid;
        this.capacity = capacity;
    }

    /**
     * Creates a new instance of a substance container. Uses the larger
     * substance quantity as the capacity.
     *
     * @param aminoAcid The amount of amino acid stored in the container.
     * @param nucleotid The amount of nucleotid stored in the container.
     */
    public SubstanceContainer(int aminoAcid, int nucleotid) {
        this.nucleotid = nucleotid;
        this.aminoAcid = aminoAcid;
        this.capacity = Math.max(aminoAcid, nucleotid);
    }

    /**
     * Creates a new instance of a substance container by copying the
     * one given as a parameter.
     *
     * @param sc The SubstanceContainer to copy
     */
    public SubstanceContainer(SubstanceContainer sc) {
        this.nucleotid = sc.nucleotid;
        this.aminoAcid = sc.aminoAcid;
        this.capacity = sc.capacity;
    }

    /**
     *
     * @return The amount of nucleotid stored in the container.
     */
    public int getNucleotidCount() {
        return nucleotid;
    }

    /**
     *
     * @return The amount of amino acid stored in the container.
     */
    public int getAminoCount() {
        return aminoAcid;
    }

    /**
     * Changes the capacity of the container by a given amount.
     * @param delta The amount the capacity will change by.
     */
    public void changeCapacity(int delta) {
        capacity += delta;
        if (capacity < 0) capacity = 0;
        if (aminoAcid > capacity) aminoAcid = capacity;
        if (nucleotid > capacity) nucleotid = capacity;
    }

    /**
     * Subtracts a given amount of substance from the container.
     * @param sc Represents the amount that will be subtracted of the different substances.
     * @return Whether the subtraction was successful (true) or not (false).
     */
    public boolean use(SubstanceContainer sc) {
        if (sc.nucleotid > nucleotid || sc.aminoAcid > aminoAcid) return false;
        nucleotid -= sc.nucleotid;
        aminoAcid -= sc.aminoAcid;
        return true;
    }

    /**
     * Adds a given amount of substances to the container. If the container's capacity
     * is smaller than what it receives, the container will only fill up to the max capacity.
     *
     * @param sc Represents the amount that will be added of the different substances.
     */
    public boolean gain(SubstanceContainer sc) {
        if (aminoAcid >= capacity && nucleotid >= capacity) return false;
        aminoAcid = Math.min(capacity, aminoAcid + sc.aminoAcid);
        nucleotid = Math.min(capacity, nucleotid + sc.nucleotid);
        return true;
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof  SubstanceContainer) {
            SubstanceContainer sc = (SubstanceContainer) o;
            return sc.aminoAcid == aminoAcid && sc.nucleotid == nucleotid;
        }
        return false;
    }
}
