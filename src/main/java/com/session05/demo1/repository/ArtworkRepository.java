package com.session05.demo1.repository;

import com.session05.demo1.model.Artwork;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArtworkRepository {
    private final Map<Long, Artwork> db = new HashMap<>();
    private Long idCounter = 1L;

    public ArtworkRepository() {
        save(new Artwork(null, "Đêm đầy sao", "Vincent van Gogh", 1889, null));
        save(new Artwork(null, "Kẻ nước có cầu kính", "Claude Monet", 1900, null));
        save(new Artwork(null, "Người khóc", "Pablo Picasso", 1937, null));
        save(new Artwork(null, "Bộ tứ công chúa", "Walt Disney", 2013, null));
    }

    public List<Artwork> findAll() {
        return new ArrayList<>(db.values());
    }

    public void save(Artwork artwork) {
        artwork.setId(idCounter++);
        db.put(artwork.getId(), artwork);
    }

    public Artwork findById(Long id) {
        return db.get(id);
    }

    public void update(Artwork artwork) {
        db.put(artwork.getId(), artwork);
    }

    public void delete(Long id) {
        db.remove(id);
    }
}

