public static class Video {
    private String title;
    private boolean checkedOut;
    private double averageRating;
    private int totalRatings;
    private int ratingCount;

    public Video(String title) {
        this.title = title;
        this.checkedOut = false;
        this.averageRating = 0.0;
        this.ratingCount = 0;
        this.totalRatings = 0;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void checkOut() {
        this.checkedOut = true;
    }

    public void returnVideo() {
        this.checkedOut = false;
    }

    public void receiveRating(int rating) {
        totalRatings += rating;
        ratingCount++;
        averageRating = (double) totalRatings / ratingCount;
    }

    public String toString() {
        return "Title: " + title + ", Checked Out: " + checkedOut + ", Avg. Rating: " + String.format("%.1f", averageRating);
    }
}

//VideoStore
public static  class VideoStore {
    private Video[] inventory;
    private int count;

    public VideoStore() {
        inventory = new Video[10];
        count = 0;
    }

    public void addVideo(String title) {
        if (count < inventory.length) {
            inventory[count++] = new Video(title);
            System.out.println("Added video: " + title);
        } else {
            System.out.println("Inventory full! Cannot add more videos.");
        }
    }

    public void checkOut(String title) {
        Video v = findVideo(title);
        if (v != null && !v.isCheckedOut()) {
            v.checkOut();
            System.out.println(title + " has been checked out.");
        } else {
            System.out.println(title + " is either not found or already checked out.");
        }
    }

    public void returnVideo(String title) {
        Video v = findVideo(title);
        if (v != null && v.isCheckedOut()) {
            v.returnVideo();
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " is either not found or wasn't checked out.");
        }
    }

    public void receiveRating(String title, int rating) {
        Video v = findVideo(title);
        if (v != null) {
            v.receiveRating(rating);
            System.out.println("Received rating " + rating + " for " + title);
        } else {
            System.out.println(title + " not found.");
        }
    }

    public void listInventory() {
        System.out.println("\n--- Video Store Inventory ---");
        for (int i = 0; i < count; i++) {
            System.out.println(inventory[i]);
        }
        System.out.println("-----------------------------\n");
    }

    private Video findVideo(String title) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getTitle().equalsIgnoreCase(title)) {
                return inventory[i];
            }
        }
        return null;
    }
}


public class VideoStoreLauncher {
    public static void main(String[] args) {
        VideoStore store = new VideoStore();
        store.addVideo("The Matrix");
        store.addVideo("Godfather II");
        store.addVideo("Star Wars Episode IV: A New Hope");
      
        store.receiveRating("The Matrix", 5);
        store.receiveRating("The Matrix", 4);
        store.receiveRating("Godfather II", 5);
        store.receiveRating("Godfather II", 5);
        store.receiveRating("Star Wars Episode IV: A New Hope", 4);
        store.receiveRating("Star Wars Episode IV: A New Hope", 5);

        store.checkOut("The Matrix");
        store.checkOut("Godfather II");
        store.checkOut("Star Wars Episode IV: A New Hope");

        store.returnVideo("The Matrix");
        store.returnVideo("Star Wars Episode IV: A New Hope");

        store.listInventory();
    }
}
