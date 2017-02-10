package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Exam;
import com.spanel.beans.Mark;
import com.spanel.beans.Student;
import com.spanel.beans.User;
import com.spanel.dao.ExamDAO;
import com.spanel.dao.MarkDAO;
import com.spanel.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 05/01/2017.
 */
public class MarksCreationForm extends Form{
    public static final String MARKS_FIELD = "notes";
    public static final String EXAM_ID_FIELD = "exam";

    private ExamDAO examDAO;
    private UserDAO userDAO;
    private MarkDAO markDAO;
    public MarksCreationForm(ExamDAO examDAO, UserDAO userDAO, MarkDAO markDAO){
        this.examDAO = examDAO;
        this.userDAO = userDAO;
        this.markDAO = markDAO;
    }
    public void create(Request request){
        String marksString = request.getFieldValue(MARKS_FIELD);

        Long examId = Long.parseLong(request.getFieldValue(EXAM_ID_FIELD));
        Exam exam = examDAO.find(examId);

        if(exam == null){
            setError("Examen inexistant");
            return;
        }

        for(String m: marksString.split("-")){
            if(m.equals("")) continue;

            String email = m.split(";")[0];
            String value = m.split(";")[1];

            User user = userDAO.find(email);
            Mark mark = new Mark();
            mark.setExamId(examId);
            mark.setStudentId(user.getUserableId());
            mark.setValue(Float.parseFloat(value));

            markDAO.create(mark);
        }

    }

}
