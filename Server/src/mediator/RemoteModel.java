package mediator;

import model.Student;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;

public interface RemoteModel extends RemoteSubject<Student, Student>, Remote
{
  Student getStudentByStudentNumber(String studyNumber) throws Exception;
  Student getStudentByName(String name) throws Exception;
  void addStudent(Student student) throws Exception;
  void close() throws Exception;

}
