public class Cats {
    private String id;
    private String text;
    private String type;
    private String user;
    private Integer upvotes;

    public Cats(String id, String text, String type, String user, Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
