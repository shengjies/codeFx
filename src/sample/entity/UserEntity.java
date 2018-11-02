package sample.entity;

public class UserEntity {
    private int id;
    private String name;
    private String role;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserEntity() {
    }

    public UserEntity(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public UserEntity(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public UserEntity(int id, String name, String role, String date) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.date = date;
    }

}
