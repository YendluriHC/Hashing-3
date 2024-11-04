//TC: O(G + U * S)
//SC: O(G + U)
import java.util.*;

public class Main {
    
    public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres) {
        Map<String, String> songToGenre = new HashMap<>();
        Map<String, List<String>> result = new HashMap<>();
        
        // Build the reverse map: song -> genre
        for (Map.Entry<String, List<String>> entry : songGenres.entrySet()) {
            String genre = entry.getKey();
            List<String> songs = entry.getValue();
            for (String song : songs) {
                songToGenre.put(song, genre);
            }
        }
        
        // For each user, calculate the genre frequencies
        for (Map.Entry<String, List<String>> entry : userSongs.entrySet()) {
            String user = entry.getKey();
            List<String> songs = entry.getValue();
            
            // Map to count how many times each genre is played by the user
            Map<String, Integer> genreCount = new HashMap<>();
            
            for (String song : songs) {
                String genre = songToGenre.get(song);
                if (genre != null) {
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                }
            }
            
            // Find the favorite genre(s) with maximum frequency
            int maxCount = 0;
            List<String> favoriteGenres = new ArrayList<>();
            
            for (Map.Entry<String, Integer> genreEntry : genreCount.entrySet()) {
                int count = genreEntry.getValue();
                if (count > maxCount) {
                    favoriteGenres.clear();
                    favoriteGenres.add(genreEntry.getKey());
                    maxCount = count;
                } else if (count == maxCount) {
                    favoriteGenres.add(genreEntry.getKey());
                }
            }
            
            // Assign the favorite genres to the user
            result.put(user, favoriteGenres);
        }
        
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> userSongs = new HashMap<>();
        userSongs.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        userSongs.put("Emma", Arrays.asList("song5", "song6", "song7"));

        HashMap<String, List<String>> songGenres = new HashMap<>();
        songGenres.put("Rock", Arrays.asList("song1", "song3"));
        songGenres.put("Dubstep", Arrays.asList("song7"));
        songGenres.put("Techno", Arrays.asList("song2", "song4"));
        songGenres.put("Pop", Arrays.asList("song5", "song6"));
        songGenres.put("Jazz", Arrays.asList("song8", "song9"));

        Map<String, List<String>> res = favoritegenre(userSongs, songGenres);
        System.out.println(res);
    }
}