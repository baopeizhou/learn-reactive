package org.bob.learn.common.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BaseStudentDTO {

    @NotNull
    @NotEmpty
    private String name;

    @Min(value = 1,message = "Age should be a positve number")
    private int age;

    @Min(value = 0,message = "Invalid credit value was given")
    private double credit;

    @NotNull
    @NotEmpty
    private String major;

    private BaseStudentDTO(){
        super();
    }

    public BaseStudentDTO(String name, int age, double credit, String major){
        super();
        this.name = name;
        this.age = age;
        this.credit=credit;
        this.major=major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
