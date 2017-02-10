package com.spanel.views.mark;

import com.spanel.app.Request;
import com.spanel.beans.Exam;
import com.spanel.beans.Student;
import com.spanel.beans.User;
import com.spanel.controllers.MarkController;
import com.spanel.dao.DAOFactory;
import com.spanel.forms.MarksCreationForm;
import com.spanel.views.Layout;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joel on 24/11/2016.
 */
public class Create extends JDialog {
    private JPanel bottomPanel;
    private JLabel emailLabel;
    private JComboBox<String> examComboBox;
    private JLabel examLabel;
    private JLabel firstNameLabel;
    private JLabel gapLabel;
    private JPanel headerPanel;
    private JPanel itemsContainerPanel;
    private JScrollPane jScrollPane;
    private JLabel lastNameLabel;
    private JPanel mainPanel;
    private JButton postButton;
    private JButton uploadButton;

    private Index index;
    private Map<String, String> marks = new HashMap<>();

    private List<Exam> exams;
    private List<User> students;

    public Create(com.spanel.views.mark.Index index, boolean modal, List<Exam> exams, List<User> students) {
        super(index.getClassIndex().getAppLayout(), modal);
        this.index = index;
        initComponents();

        this.students = students;
        DAOFactory daoFactory = index.getClassIndex().getMain().getDaoFactory();
        itemsContainerPanel.setLayout(new GridLayout(students.size(), 1));
        int j=0;
        for (User student :
                students) {
            itemsContainerPanel.add(composeStudentItem(student, j++));
        }

        this.exams = exams;
        String[] examsList= new String[exams.size()];
        for (int i = 0; i< exams.size(); i++){
            Exam exam = exams.get(i);
            examsList[i]= exam.getAffectation(daoFactory).getCourse(daoFactory).getName() + " - " + exam.getType() + " - " +exam.getDate();
        }
        examComboBox.setModel(new DefaultComboBoxModel<>(examsList));
    }

    private void postButtonActionPerformed(ActionEvent evt) {
        Layout layout = index.getClassIndex().getAppLayout();
        Request request = new Request(layout, layout.getMain());
        request.addField(MarksCreationForm.EXAM_ID_FIELD, Long.toString(exams.get(examComboBox.getSelectedIndex()).getId()));
        String marksString = "";
        for(User student : students){
            marksString += student.getEmail() + ";" + marks.get(student.getEmail()) + "-";
            if(marks.get(student.getEmail()) ==  null){
                JOptionPane.showMessageDialog(null,
                        "Note de "+ student.getFirstName() + " " + student.getLastName() + " non saisie", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        System.out.println(marksString);
        request.addField(MarksCreationForm.MARKS_FIELD, marksString);

        if(MarkController.create(request)){
            index.getClassIndex().setTabComponents(3);
            dispose();
        }
    }

    private void uploadButtonActionPerformed(ActionEvent evt) {
        new Upload(index.getClassIndex().getAppLayout(), true).setVisible(true);
    }

    private void initComponents() {
        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        gapLabel = new JLabel();
        lastNameLabel = new JLabel();
        firstNameLabel = new JLabel();
        emailLabel = new JLabel();
        itemsContainerPanel = new JPanel();

        examLabel = new JLabel();
        examComboBox = new JComboBox<>();
        bottomPanel = new JPanel();
        uploadButton = new JButton();
        postButton = new JButton();

        setTitle("Publier des notes");

        setMinimumSize(new Dimension(400, 300));
        setSize(new Dimension(790, 520));

        setLayout(new BorderLayout());

        jScrollPane.setBorder(null);

        mainPanel.setBackground(new Color(255, 255, 255));

        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        headerPanel.setLayout(new GridLayout(1, 4));

        gapLabel.setForeground(new Color(0, 102, 153));
        headerPanel.add(gapLabel);

        firstNameLabel.setFont(new Font("Calibri", 1, 14)); // NOI18N
        firstNameLabel.setForeground(new Color(0, 102, 153));
        firstNameLabel.setText("Prénom");
        headerPanel.add(firstNameLabel);

        lastNameLabel.setFont(new Font("Calibri", 1, 14)); // NOI18N
        lastNameLabel.setForeground(new Color(0, 102, 153));
        lastNameLabel.setText("Nom");
        headerPanel.add(lastNameLabel);

        emailLabel.setFont(new Font("Calibri", 1, 14)); // NOI18N
        emailLabel.setForeground(new Color(0, 102, 153));
        emailLabel.setText("Note");
        headerPanel.add(emailLabel);

        examLabel.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        examLabel.setText("Examen :");

        itemsContainerPanel.setBackground(new Color(255, 255, 255));

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(itemsContainerPanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(examLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(examComboBox, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(examLabel)
                                        .addComponent(examComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemsContainerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane, BorderLayout.CENTER);

        bottomPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        uploadButton.setText("Uploader fichier Excel");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(uploadButton);

        postButton.setText("Publier");
        postButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                postButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(postButton);

        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel composeStudentItem(User student, int i){
        JLabel itemFirstNameLabel;
        JLabel itemLastNameLabel;
        JPanel itemLeftPanel;
        JPanel itemPanel;
        JPanel itemPicturePanel;
        JPanel itemMarkPanel;
        JFormattedTextField markTextField;

        itemPanel = new JPanel();
        itemLeftPanel = new JPanel();
        itemPicturePanel = new JPanel();
        itemFirstNameLabel = new JLabel();
        itemLastNameLabel = new JLabel();
        itemMarkPanel = new JPanel();
        markTextField = new JFormattedTextField();

        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemPanel.setPreferredSize(new Dimension(768, 38));
        itemPanel.setLayout(new GridLayout(1, 4));

        itemPicturePanel.setBackground(new Color(204, 204, 204));
        itemPicturePanel.setMinimumSize(new Dimension(38, 38));
        itemPicturePanel.setPreferredSize(new Dimension(38, 38));
        itemPicturePanel.setRequestFocusEnabled(false);

        GroupLayout itemPicturePanelLayout = new GroupLayout(itemPicturePanel);
        itemPicturePanel.setLayout(itemPicturePanelLayout);
        itemPicturePanelLayout.setHorizontalGroup(
                itemPicturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 38, Short.MAX_VALUE)
        );
        itemPicturePanelLayout.setVerticalGroup(
                itemPicturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 38, Short.MAX_VALUE)
        );

        GroupLayout itemLeftPanelLayout = new GroupLayout(itemLeftPanel);
        itemLeftPanel.setLayout(itemLeftPanelLayout);
        itemLeftPanelLayout.setHorizontalGroup(
                itemLeftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(itemLeftPanelLayout.createSequentialGroup()
                                .addContainerGap(109, Short.MAX_VALUE)
                                .addComponent(itemPicturePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
        );
        itemLeftPanelLayout.setVerticalGroup(
                itemLeftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(itemPicturePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        if(i%2==0) itemLeftPanel.setBackground(new Color(255, 255, 255));
        itemPanel.add(itemLeftPanel);

        itemFirstNameLabel.setText(student.getFirstName());
        itemPanel.add(itemFirstNameLabel);

        itemLastNameLabel.setText(student.getLastName());
        itemPanel.add(itemLastNameLabel);

        itemMarkPanel.setMinimumSize(new Dimension(38, 38));
        if(i%2==0) itemMarkPanel.setBackground(new Color(255, 255, 255));

        markTextField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        markTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(Double.parseDouble(markTextField.getText())<0 || Double.parseDouble(markTextField.getText())>20){
                    JOptionPane.showMessageDialog(null,
                            "Note invalide", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    markTextField.setText("");
                }else{
                    marks.put(student.getEmail(), markTextField.getText());
                }
            }
        });
        GroupLayout itemMarkPanelLayout = new GroupLayout(itemMarkPanel);
        itemMarkPanel.setLayout(itemMarkPanelLayout);
        itemMarkPanelLayout.setHorizontalGroup(
                itemMarkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(itemMarkPanelLayout.createSequentialGroup()
                                .addComponent(markTextField, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 123, Short.MAX_VALUE))
        );
        itemMarkPanelLayout.setVerticalGroup(
                itemMarkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, itemMarkPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(markTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        itemPanel.add(itemMarkPanel);
        if(i%2==0) itemPanel.setBackground(new Color(255, 255, 255));

        return itemPanel;
    }

}
