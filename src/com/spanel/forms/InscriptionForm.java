package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Professor;
import com.spanel.beans.Student;
import com.spanel.beans.User;
import com.spanel.dao.*;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Created by Joel on 25/11/2016.
 */
public class InscriptionForm extends Form {
    public static final String TYPE_PROFESSOR_VALUE = "Professeur";
    public static final String TYPE_STUDENT_VALUE = "Etudiant";

    public static final String TYPE_FIELD = "type";
    public static final String FIRST_NAME_FIELD = "first_name";
    public static final String LAST_NAME_FIELD = "last_name";
    public static final String PICTURE_FIELD = "picture";
    public static final String GRADE_FIELD = "grade";
    public static final String DEPARTMENT_FIELD = "department";
    public static final String EMAIL_FIELD = "email";
    public static final String PHONE_FIELD = "phone";
    public static final String PASSWORD_FIELD = "password";
    public static final String PASSWORD_CONFIRMATION_FIELD = "password_confirmation";

    private static final String CODING_ALGORITHM                = "SHA-256";

    private UserDAO userDAO;
    private ProfessorDAO professorDAO;
    private StudentDAO studentDAO;
    private DepartmentDAO departmentDAO;

    public InscriptionForm (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public InscriptionForm(UserDAO userDAO, StudentDAO studentDAO, ProfessorDAO professorDAO){
        this(userDAO);
        this.studentDAO = studentDAO;
        this.professorDAO = professorDAO;
    }

    public InscriptionForm(UserDAO userDAO, StudentDAO studentDAO, ProfessorDAO professorDAO,DepartmentDAO departmentDAO){
        this(userDAO);
        this.studentDAO = studentDAO;
        this.professorDAO = professorDAO;
        this.departmentDAO = departmentDAO;
    }

    public User register(Request request) {
        validateNotNullFields(request, FIRST_NAME_FIELD, LAST_NAME_FIELD, EMAIL_FIELD, PHONE_FIELD, PASSWORD_FIELD, PASSWORD_CONFIRMATION_FIELD);

        String type = request.getFieldValue(TYPE_FIELD);
        String firstName = request.getFieldValue(FIRST_NAME_FIELD);
        String lastName = request.getFieldValue(LAST_NAME_FIELD);
        String picture = request.getFieldValue(PICTURE_FIELD);
        String studentId = "50091";
        String grade = request.getFieldValue(GRADE_FIELD);
        String department = request.getFieldValue(DEPARTMENT_FIELD);
        String email = request.getFieldValue(EMAIL_FIELD);
        Long phone = Long.parseLong(request.getFieldValue(PHONE_FIELD)) ;
        String password = request.getFieldValue(PASSWORD_FIELD);
        String password_confirmation = request.getFieldValue(PASSWORD_CONFIRMATION_FIELD);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);


        try {
            validateEmail( email, user );
            validatePassword( password,password_confirmation, user );

            if ( errors.isEmpty() ) {
                userDAO.create( user );
                if(type.equals(TYPE_STUDENT_VALUE)){
                    /*  Normallement on doit vérifier si le matricule n'existe pas déjà
                    if(studentDAO.findByStudentId(studentId)!= null){
                        Student student = new Student();
                        student.setStudentId(studentId);

                        studentDAO.create(student);
                        userDAO.link(user, student);
                    }
                     */

                    Student student = new Student();
                    student.setStudentId(studentId);

                    studentDAO.create(student);
                    userDAO.link(user, student);
                }else{
                    // Koyranga
                    Professor professor = new Professor();
                    professor.setGrade(grade);
                    Long id = departmentDAO.findByName(department).getId();
                    professor.setDepartmentId(id);
                    professorDAO.create(professor);
                    userDAO.link(user,professor);

                }
            }
        } catch ( DAOException e ) {
            setError(e.getMessage() );
            e.printStackTrace();
        }

        return user;
    }


    private void validateEmail( String email, User user ) {
        try {
            emailValidation( email );
        } catch ( FormValidationException e ) {
            setError(e.getMessage() );
        }
        user.setEmail( email );
    }


    private void validatePassword( String password, String confirmation, User user ) {
        try {
            passwordValidation( password, confirmation );
        } catch ( FormValidationException e ) {
            setError(e.getMessage() );
        }

        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( CODING_ALGORITHM );
        passwordEncryptor.setPlainDigest( false );
        String cryptedPassword = passwordEncryptor.encryptPassword( password );

        user.setPassword( cryptedPassword );
    }

    private void emailValidation( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Adresse e-mail invalide" );
            } else if ( userDAO.find( email ) != null ) {
                throw new FormValidationException( "Adresse email déjà  utilisée" );
            }
        } else {
            throw new FormValidationException( "Adresse email non saisie" );
        }
    }


    private void passwordValidation( String password, String confirmation ) throws FormValidationException {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new FormValidationException( "Mots de passe différents" );
            } else if ( password.length() < 3 ) {
                throw new FormValidationException( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }
}
