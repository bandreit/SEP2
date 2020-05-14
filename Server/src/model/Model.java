package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface Model extends LocalSubject<Student, Student>
{
  public Student getStudentByStudyNumber(String studyNumber) throws Exception,
      RemoteException;
  public Student getStudentByName(String name) throws Exception, RemoteException;
  public void addStudent(Student student) throws Exception, RemoteException;
  int getStudentListSize() throws Exception, RemoteException;
  Student getStudent(int index) throws Exception, RemoteException;
  public void close();
}
