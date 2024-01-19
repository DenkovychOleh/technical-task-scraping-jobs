package com.dataox.scrapingjobs.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jobs")
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "organization_url")
    private String organizationUrl;

    @Column(name = "logo")
    private String logo;

    @Column(name = "organization_title")
    private String organizationTitle;

    @Column(name = "location")
    private String location;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "tags")
    private String tags;

    @Column(nullable = false, name = "created")
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "job_fuction_id")
    private JobFunction jobFunction;
}
