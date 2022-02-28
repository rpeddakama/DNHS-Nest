package com.example.sping_portfolio.controllers;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ExamEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String examName;
    private String className;
    private String teacher;
    private String classPeriod;
    private String examDate;

    public ExamEvent(String examName, String className, String teacher, String classPeriod, String examDate) {
        this.examName = examName;
        this.className = className;
        this.teacher = teacher;
        this.classPeriod = classPeriod;
        this.examDate = examDate;
    }

    public String getExamName() {
        return this.examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassPeriod() {
        return this.classPeriod;
    }

    public void setClassPeriod(String classPeriod) {
        this.classPeriod = classPeriod;
    }

    public String getExamDate() {
        return this.examDate;
    }

    public void setExamDate(String ExamEvent) {
        this.examDate = ExamEvent;
    }
}
