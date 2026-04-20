package com.session05.demo1.service;

import com.session05.demo1.model.Artwork;
import com.session05.demo1.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkService {
    @Autowired
    private ArtworkRepository repository;

    public List<Artwork> search(String keyword) {
        return repository.findAll().stream()
                .filter(a -> keyword == null || keyword.isEmpty() ||
                        a.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        a.getArtist().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public List<Artwork> findAll() {
        return repository.findAll();
    }

    public void save(Artwork artwork) {
        repository.save(artwork);
    }

    public Artwork findById(Long id) {
        return repository.findById(id);
    }

    public void update(Artwork artwork) {
        repository.update(artwork);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}

