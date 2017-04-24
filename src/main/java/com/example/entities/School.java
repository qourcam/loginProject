package com.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gorkem on 13.04.2017.
 */

@Entity
@Table(name = "school")
public class School implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String schoolName;

    public School () {}

    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


}
