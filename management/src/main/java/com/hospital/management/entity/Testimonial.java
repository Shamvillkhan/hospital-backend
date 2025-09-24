package com.hospital.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "testimonial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testimonial_id")
    private long testimonialId;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true,length = 150)
    private String email;

    @Column(name="message",nullable = false,columnDefinition = "TEXT")
    private String message;

    @Column(name="rating",length = 5)
    private int rating;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created_at",insertable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated_at",insertable = false)
    private Date updatedAt;

}
