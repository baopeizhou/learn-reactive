package org.bob.learn.service;

import io.reactivex.Observable;
import org.bob.learn.common.model.BaseStudentDTO;
import org.bob.learn.common.model.StudentDTO;

public interface StudentService {

    Observable<Object> createStudent(BaseStudentDTO student);

    Observable<StudentDTO> findByName(String name);
}



