package org.bob.learn.dao;

import io.reactivex.Observable;
import org.bob.learn.common.model.BaseStudentDTO;
import org.bob.learn.common.model.StudentDTO;

public interface StudentRepository {

    Observable<Object> createStudent(BaseStudentDTO student);

    Observable<StudentDTO> findByName(String name);
}
