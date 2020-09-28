package org.bob.learn.service.impl;

import io.reactivex.Observable;
import org.bob.learn.common.model.BaseStudentDTO;
import org.bob.learn.common.model.StudentDTO;
import org.bob.learn.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {


    @Override
    public Observable<Object> createStudent(BaseStudentDTO student) {
        return null;
    }

    @Override
    public Observable<StudentDTO> findByName(String name) {
        return null;
    }
}
