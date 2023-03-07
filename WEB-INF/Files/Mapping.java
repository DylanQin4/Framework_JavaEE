public class Mapping {
    String className;
    String Methode;

    public Mapping(String className, String methode) {
        setClassName(className);
        setMethode(methode);
    }

    public Mapping(){}

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethode() {
        return Methode;
    }

    public void setMethode(String methode) {
        Methode = methode;
    }
    
}
