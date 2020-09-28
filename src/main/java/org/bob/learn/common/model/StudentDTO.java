package org.bob.learn.common.model;

import com.fasterxml.jackson.databind.ser.Serializers;

public class StudentDTO extends BaseStudentDTO {

    private String id;

    public StudentDTO(String name, int age, double credit, String major) {
        super(name, age, credit, major);
    }

    public StudentDTO(String name, int age, double credit, String major, String id) {
        super(name, age, credit, major);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
