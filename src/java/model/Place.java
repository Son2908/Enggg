package model;

/**
 * khai báo tên và đường dẫn hình ảnh của địa điểm.
 * @author sodok
 */
public class Place {
    private String name ;
    private  String imageLink;

    public Place() {
    }

    public Place(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "place{" + "name=" + name + ", imageLink=" + imageLink + '}';
    }
}
