package com.example.studentlist.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
        for(int i=0;i<10;i++){
            Student s = new Student("name",""+i,"Israel" + i,"0523456789",false);
            data.add(s);
        }
    }

    List<Student> data = new LinkedList<Student>();

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student student){
        data.add(student);
    }

    public void editStudent(Student student){
        Student existingStudent = data.get(Integer.parseInt(student.getId()));
        existingStudent.setName(student.name);
        existingStudent.setFlag(student.isFlag());
        existingStudent.setPhoneNumber(student.phoneNumber);
        existingStudent.setAddress(student.address);
        existingStudent.setId(student.id);
    }

    public void deleteStudent(String studentId) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == studentId) {
                data.remove(i);
                break;
            }
        }
    }
}