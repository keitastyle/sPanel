package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Course;
import com.spanel.beans.Exam;
import com.spanel.dao.AffectationDAO;
import com.spanel.dao.CourseDAO;
import com.spanel.dao.DAOException;
import com.spanel.dao.ExamDAO;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Yasmine on 04/01/2017.
 */
public class ExamModificationForm extends Form {
    ExamDAO examDAO;
    CourseDAO courseDAO;
    AffectationDAO affectationDAO;

    public static final String COURSE_NAME_FIELD = "Matière";
    public static final String TYPE_FIELD = "Type";
    public static final String DATE_FIELD = "Date";
    public static final String HOUR_FIELD = "Heure";
    public static final String ROOM_FIELD = "Salle";
    public static final String DESCRIPTION_FIELD = "Description";
    public static final String EXAM_ID = "id";
    public static final String PROFESSOR_ID = "Id professeur";
    public static final String CLASS_ID = "Id classe";

    public ExamModificationForm(ExamDAO examDAO, CourseDAO courseDAO, AffectationDAO affectationDAO) {
        this.examDAO = examDAO;
        this.courseDAO = courseDAO;
        this.affectationDAO = affectationDAO;
    }

    public void update(Request request){
        validateNotNullFields(request);
        if(hasErrors()) return;

        try{
            String id = request.getFieldValue(EXAM_ID);
            String courseName = request.getFieldValue(COURSE_NAME_FIELD);
            String type = request.getFieldValue(TYPE_FIELD);
            String date = request.getFieldValue(DATE_FIELD);
            String hour = request.getFieldValue(HOUR_FIELD);
            String room = request.getFieldValue(ROOM_FIELD);
            String description = request.getFieldValue(DESCRIPTION_FIELD);
            String professorId = request.getFieldValue(PROFESSOR_ID);
            String classId = request.getFieldValue(CLASS_ID);

            Course course = courseDAO.findByName(courseName);
            Long affectationId = (affectationDAO.find(Long.parseLong(professorId), Long.parseLong(classId), course.getId())).getId();
            Exam exam = examDAO.find(Long.parseLong(id));
            exam.setAffectationId(affectationId);
            exam.setType(type);
            exam.setRoom(room);
            exam.setDescription(description);
            validateDate(exam, Long.parseLong(classId), date, hour);

            if(!hasErrors())
                examDAO.update(exam);
        }catch(DAOException e ){
            System.out.println(e.getMessage());
        }

    }

    private void validateDate(Exam exam, Long classId, String date, String hour){
        try{
            dateValidation(date);
            hourValidation(hour);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }

        String finalDate = date + " " + hour;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy H:mm");
        try{
            java.util.Date javaDate = format.parse(finalDate);
            Timestamp sqlDate = new Timestamp(javaDate.getTime());
            java.util.Date currentDate = format.parse(format.format(new java.util.Date()));
            if(javaDate.compareTo(currentDate)<0)
                setError("Date déjà passée");
            else{
                exam.setDate(sqlDate);
                Exam sameDateExam = examDAO.find(classId, sqlDate);
                if( sameDateExam != null){
                    if(!sameDateExam.getId().equals(exam.getId()))
                        setError("Examen déjà programmé à cette date");
                }

            }
        }catch (ParseException e){
            setError("Format de date ou heure invalide");
        }catch (DAOException e){
            setError("Examen déjà programmé à cette date");
        }
    }

    public void dateValidation(String date) throws FormValidationException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        try{
            format.parse(date);
        }catch (ParseException e){
            throw new FormValidationException("Format de date invalide");
        }
    }

    public void hourValidation(String hour) throws FormValidationException{
        SimpleDateFormat format = new SimpleDateFormat("H:mm");
        try{
            format.parse(hour);
        }catch (ParseException e){
            throw new FormValidationException("Format d'heure");
        }
    }

}
