package com.htw.project.eventplanner.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.htw.project.eventplanner.model.converter.IdOnlyConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_conversation")
public class GroupConversation implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "gc_participant",
            joinColumns = @JoinColumn(name = "gc_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;

    @JsonSerialize(converter = IdOnlyConverter.class)
    @OneToOne(cascade = CascadeType.ALL)
    private Event event;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
