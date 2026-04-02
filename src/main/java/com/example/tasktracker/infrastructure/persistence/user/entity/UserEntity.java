package com.example.tasktracker.infrastructure.persistence.user.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "users",
        schema = "tasktracker_schema"
    )
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    protected UserEntity(){}

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public UserEntity(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return this.id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", email = " + email +
                '}';
    }
}
