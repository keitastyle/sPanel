package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Student;
import com.spanel.dao.ClassDAO;
import com.spanel.dao.DAOException;
import com.spanel.dao.StudentDAO;
import com.spanel.dao.UserDAO;

/**
 * Created by koria on 22/12/2016.
 */
public class StudentAllocationForm extends Form{
    private StudentDAO studentDAO;

    public static String STUDENTS_ID_FIELD = "id";
    public static String CLASS_ID_FIELD = "class_id";

    public StudentAllocationForm(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    public void allocate(Request request){
        Long classId = Long.parseLong(request.getFieldValue(CLASS_ID_FIELD));
        String studentsId = request.getFieldValue(STUDENTS_ID_FIELD);

        try {
            for(String id: studentsId.split(",")) {
                if(id!= null && !id.equals("")) {
                    Student student = studentDAO.find(Long.parseLong(id));
                    student.setClassId(classId);
                    studentDAO.update(student);
                }
            }
        }catch (DAOException e) {
            setError(e.getMessage() );
            e.printStackTrace();
        }
    }
}
