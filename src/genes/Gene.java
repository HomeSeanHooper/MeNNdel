package genes;

public interface Gene {

    Boolean mutatable = true;

    String getName();

    void mutate();

    void randomize();

    void normalize();

    String getCommandItem();

    public float getValue();

    public void setValue(float val);
}
