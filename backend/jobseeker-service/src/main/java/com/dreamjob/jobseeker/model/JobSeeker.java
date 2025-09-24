package com.dreamjob.jobseeker.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "job_seekers")
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Embedded
    private Profile profile;

    private String status;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "jobseeker_skills",
            joinColumns = @JoinColumn(name = "jobseeker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    public JobSeeker() {}

    public JobSeeker(Profile profile, String status) {
        this.profile = profile;
        this.status = status;
    }

    // Helper methods
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Set<Skill> getSkills() { return skills; }
    public void setSkills(Set<Skill> skills) { this.skills = skills; }

    public void update(JobSeeker jobSeeker) {
        setStatus(jobSeeker.getStatus());
        profile.update(jobSeeker.getProfile());
        setSkills(jobSeeker.getSkills());
    }
}
