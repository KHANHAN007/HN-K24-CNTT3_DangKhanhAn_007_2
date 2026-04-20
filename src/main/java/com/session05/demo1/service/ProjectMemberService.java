package com.session05.demo1.service;

import com.session05.demo1.model.ProjectMember;
import com.session05.demo1.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberService {
    @Autowired
    private ProjectMemberRepository repository;

    public List<ProjectMember> search(String keyword, String position){
        return repository.findAll().stream()
                .filter(m ->
                        (keyword == null || m.getFullName().toLowerCase().contains(keyword.toLowerCase())) ||
                        m.getEmail().toLowerCase().contains(keyword.toLowerCase())
                )
                .filter(m -> position == null || position.isEmpty() || m.getPosition().equalsIgnoreCase(position))
                .toList();
    }

    public void save(ProjectMember m){
        repository.save(m);
    }

    public ProjectMember findById(Long id) {
        return repository.findById(id);
    }

    public void update(ProjectMember m) {
        repository.update(m);
    }

    public void delete(Long id){
        repository.delete(id);
    }
}
