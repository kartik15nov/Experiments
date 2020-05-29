package model;

public class Person {
    String id;

    String FatherId;
    String MotherId;

    public Person(String id, String fatherId, String motherId) {
        this.id = id;
        FatherId = fatherId;
        MotherId = motherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatherId() {
        return FatherId;
    }

    public void setFatherId(String fatherId) {
        FatherId = fatherId;
    }

    public String getMotherId() {
        return MotherId;
    }

    public void setMotherId(String motherId) {
        MotherId = motherId;
    }
}
