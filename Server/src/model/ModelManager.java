package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private StudentList studentList;
  private PropertyChangeAction<Student, Student> property;

  public ModelManager()
  {
    this.studentList = new StudentList();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override
  public Student getStudentByStudyNumber(String studyNumber) throws IllegalArgumentException,
      RemoteException
  {
    return studentList.getStudentByNumber(studyNumber);
  }

  @Override
  public Student getStudentByName(String name) throws IllegalArgumentException, RemoteException
  {
    return studentList.getStudentByName(name);
  }

  @Override public void addStudent(Student student) throws IllegalArgumentException, RemoteException
  {
    studentList.addStudent(student);
    property.firePropertyChange("add", null, student);
  }

  @Override public int getStudentListSize() throws Exception, RemoteException
  {
    return studentList.getSize();
  }

  @Override public Student getStudent(int index) throws Exception, RemoteException
  {
    return studentList.getStudent(index);
  }

  @Override public void close()
  {
    property.close();
  }

  @Override public boolean addListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
