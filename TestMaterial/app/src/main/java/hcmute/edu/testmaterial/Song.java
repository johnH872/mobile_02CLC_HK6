package hcmute.edu.testmaterial;

public class Song {
    private int Image;
    private String name;
    private String singer;

    public Song(int image, String name, String singer) {
        Image = image;
        this.name = name;
        this.singer = singer;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
