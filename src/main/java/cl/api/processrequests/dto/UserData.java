package cl.api.processrequests.dto;

public class UserData {

    private int userId;

    private int id;

    private String title;

    private Boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(final Boolean completed) {
        this.completed = completed;
    }
}
