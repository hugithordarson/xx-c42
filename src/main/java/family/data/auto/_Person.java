package family.data.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.EntityProperty;
import org.apache.cayenne.exp.property.ListProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.exp.property.StringProperty;

import family.data.Division;
import family.data.Person;

/**
 * Class _Person was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Person extends BaseDataObject {

    private static final long serialVersionUID = 1L;

    public static final String ID_PK_COLUMN = "id";

    public static final StringProperty<String> NAME = PropertyFactory.createString("name", String.class);
    public static final ListProperty<Person> CHILDREN = PropertyFactory.createList("children", Person.class);
    public static final EntityProperty<Division> DIVISION = PropertyFactory.createEntity("division", Division.class);
    public static final EntityProperty<Person> LAST_ADDED_CHILD = PropertyFactory.createEntity("lastAddedChild", Person.class);
    public static final ListProperty<Division> MANAGED_DIVISIONS = PropertyFactory.createList("managed_divisions", Division.class);
    public static final EntityProperty<Person> PARENT = PropertyFactory.createEntity("parent", Person.class);

    protected String name;

    protected Object children;
    protected Object division;
    protected Object lastAddedChild;
    protected Object managed_divisions;
    protected Object parent;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void addToChildren(Person obj) {
        addToManyTarget("children", obj, true);
    }

    public void removeFromChildren(Person obj) {
        removeToManyTarget("children", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Person> getChildren() {
        return (List<Person>)readProperty("children");
    }

    public void setDivision(Division division) {
        setToOneTarget("division", division, true);
    }

    public Division getDivision() {
        return (Division)readProperty("division");
    }

    public void setLastAddedChild(Person lastAddedChild) {
        setToOneTarget("lastAddedChild", lastAddedChild, true);
    }

    public Person getLastAddedChild() {
        return (Person)readProperty("lastAddedChild");
    }

    public void addToManaged_divisions(Division obj) {
        addToManyTarget("managed_divisions", obj, true);
    }

    public void removeFromManaged_divisions(Division obj) {
        removeToManyTarget("managed_divisions", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Division> getManaged_divisions() {
        return (List<Division>)readProperty("managed_divisions");
    }

    public void setParent(Person parent) {
        setToOneTarget("parent", parent, true);
    }

    public Person getParent() {
        return (Person)readProperty("parent");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "children":
                return this.children;
            case "division":
                return this.division;
            case "lastAddedChild":
                return this.lastAddedChild;
            case "managed_divisions":
                return this.managed_divisions;
            case "parent":
                return this.parent;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "name":
                this.name = (String)val;
                break;
            case "children":
                this.children = val;
                break;
            case "division":
                this.division = val;
                break;
            case "lastAddedChild":
                this.lastAddedChild = val;
                break;
            case "managed_divisions":
                this.managed_divisions = val;
                break;
            case "parent":
                this.parent = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.name);
        out.writeObject(this.children);
        out.writeObject(this.division);
        out.writeObject(this.lastAddedChild);
        out.writeObject(this.managed_divisions);
        out.writeObject(this.parent);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.children = in.readObject();
        this.division = in.readObject();
        this.lastAddedChild = in.readObject();
        this.managed_divisions = in.readObject();
        this.parent = in.readObject();
    }

}
