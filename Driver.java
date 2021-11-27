import java.io.File;

public class Driver {

    public static void main(String[]args) {

        // PART 1

        // Library lib = new Library(); // compile error, constructor private
        // Library lib = Library.getLibrary(); // assertion error, must rename the library first

        Library.renameLibrary("library");
        Library lib1 = Library.getLibrary();
        Library lib2 = Library.getLibrary();
        // System.out.println(lib == lib2); // true

        // String emil = lib.getEmailID(); // assertion error, emailID is not set
        lib1.setEmailID("someone@domain.com");
        String email = lib1.getEmailID();

        // PART 2

        Movie m1 = (Movie) ContentFactory.getMovie(new File("path1"),"Name1", Language.ENGLISH, "std1");
        Movie m2 = (Movie) ContentFactory.getMovie(new File("path2"),"Name1", Language.FRENCH, "std3");
        // System.out.println(m1==m2); // true

        TVShow t1 = (TVShow) ContentFactory.getTVShow("Name1", Language.ENGLISH, "std2");
        TVShow t2 = (TVShow) ContentFactory.getTVShow("show1", Language.LATIN, "std2");

        Episode e1 = new Episode(new File(""), t2, "secret episode", 0);
        // doesn't do anything but can be added to a watchlist
        t1.createAndAddEpisode(new File(""), "episode1");
        // actually adds the episode to TVShow

        // PART 3

        WatchList wl1 = new WatchList("list1");
        wl1.addWatchable(m1);
        wl1.addWatchable(t1);
        wl1.addWatchable(e1);

        WatchList wl2 = new WatchList("list2");
        wl2.addWatchable(m2);
        wl2.addWatchable(t1);
        wl2.addWatchable(e1);

        // System.out.println(wl1 == wl2); // false
        // System.out.println(wl1.equals(wl2)); // true

        lib1.addWatchList(wl1);

        return; // debug stopper



    }

}
