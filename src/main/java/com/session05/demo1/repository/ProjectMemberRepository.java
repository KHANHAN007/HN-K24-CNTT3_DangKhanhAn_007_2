package com.session05.demo1.repository;

import com.session05.demo1.model.ProjectMember;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProjectMemberRepository {
    private final Map<Long, ProjectMember> db = new HashMap<>();
    private Long idCounter = 1L;

    public ProjectMemberRepository() {
        save(new ProjectMember(null, "Nguyễn Văn A", "Developer", 2.5, null, "vana@gmail.com"));
        save(new ProjectMember(null, "Trần Thị B", "Tester", 1.0, null, "b@gmail.com"));
        save(new ProjectMember(null, "Lê Văn C", "PM", 5.0, null, "c@rikkeisoft.com"));
        save(new ProjectMember(null, "Phạm Văn D", "Bridge SE", 3.0, null, "d@rikkeisoft.com"));
    }

    public List<ProjectMember> findAll() {
        return new ArrayList<>(db.values());
    }

    public void save(ProjectMember member){
        member.setId(idCounter++);
        db.put(member.getId(), member);
    }

    public ProjectMember findById(Long id){
        return db.get(id);
    }

    public void update(ProjectMember member){
        db.put(member.getId(), member);
    }

    public void delete(Long id){
        db.remove(id);
    }
}
