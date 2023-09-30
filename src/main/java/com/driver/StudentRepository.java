package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student>studentDB=new HashMap<>();
    HashMap<String,Teacher>teacherDB=new HashMap<>();
    HashMap<String, List<String>>teacherStudentDB=new HashMap<>();

    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentDB.containsKey(student)&&teacherDB.containsKey(teacher)){
            List<String>studentlist=teacherStudentDB.getOrDefault(teacher,new ArrayList<>());
            studentlist.add(student);
            teacherStudentDB.put(teacher,studentlist);
        }
    }

    public Student getStudentByName(String name) {
        return studentDB.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDB.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherStudentDB.getOrDefault(teacher,new ArrayList<>());
    }

    public List<String> getAllStudentts() {
        List<String>studentslist=new ArrayList<>();
        for(String s:studentDB.keySet()){
            studentslist.add(s);
        }
        return studentslist;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherDB.containsKey(teacher)){

            if(teacherStudentDB.containsKey(teacher)){
                for(String s:teacherStudentDB.get(teacher)){
                    studentDB.remove(s);
                }
                teacherStudentDB.remove(teacher);
            }
            teacherDB.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        for(String teacher:teacherDB.keySet()){
            if(teacherStudentDB.containsKey(teacher)){
                for(String s:teacherStudentDB.get(teacher)){
                    studentDB.remove(s);
                }
                teacherStudentDB.remove(teacher);
            }
            teacherDB.remove(teacher);
        }
    }
}