package hcmute.edu.foregroundservice2_demo;

import java.io.Serializable;

public class Song implements Serializable {
    private String tittle;
    private String singer;
    private int image;
    private int resource;

    public Song(String tittle, String singer, int image, int resource) {
        this.tittle = tittle;
        this.singer = singer;
        this.image = image;
        this.resource = resource;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
