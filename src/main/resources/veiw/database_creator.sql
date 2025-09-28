

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE USERS
(
    id NUMBER PRIMARY KEY,
    username NVARCHAR2(50) UNIQUE NOT NULL,
    password NVARCHAR2(100) NOT NULL,
    firstname NVARCHAR2(50) NOT NULL,
    lastname NVARCHAR2(50) NOT NULL,
    email NVARCHAR2(100) UNIQUE NOT NULL,
    address NVARCHAR2(100),
    nationalId NVARCHAR2(10) UNIQUE,
    birthdate DATE
);

CREATE SEQUENCE teacher_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE TEACHERS
(
    teacher_code NUMBER PRIMARY KEY,
    user_id NUMBER UNIQUE NOT NULL,
    lesson NVARCHAR2(50),
    CONSTRAINT fk_teacher_user FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE SEQUENCE student_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE STUDENTS
(
    student_code NUMBER PRIMARY KEY,
    user_id NUMBER UNIQUE NOT NULL,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE SEQUENCE employee_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE EMPLOYEES
(
    employee_code NUMBER PRIMARY KEY,
    user_id NUMBER UNIQUE NOT NULL,
    role NVARCHAR2(50),
    CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE SEQUENCE course_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE COURSES
(
    class_code NUMBER PRIMARY KEY,
    class_name NVARCHAR2(50) NOT NULL,
    class_points NUMBER,
    class_status NVARCHAR2(20),
    teacher_code NUMBER NOT NULL,
    CONSTRAINT fk_course_teacher FOREIGN KEY (teacher_code) REFERENCES TEACHERS (teacher_code)
);

CREATE SEQUENCE lesson_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE LESSONS
(
    lesson_code NUMBER PRIMARY KEY,
    class_code NUMBER NOT NULL,
    lesson_name NVARCHAR2(50) NOT NULL,
    lesson_status NVARCHAR2(20),
    lesson_date DATE NOT NULL,
    lesson_teacher NUMBER,
    CONSTRAINT fk_lesson_course FOREIGN KEY (class_code) REFERENCES COURSES (class_code),
    CONSTRAINT fk_lesson_teacher FOREIGN KEY (lesson_teacher) REFERENCES TEACHERS (teacher_code)
);

CREATE SEQUENCE session_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE SESSIONS
(
    session_id NUMBER PRIMARY KEY,
    class_code NUMBER NOT NULL,
    lesson_code NUMBER NOT NULL,
    session_status NVARCHAR2(20),
    session_date DATE,
    teacher_attendance NVARCHAR2(20),
    CONSTRAINT fk_session_course FOREIGN KEY (class_code) REFERENCES COURSES (class_code),
    CONSTRAINT fk_session_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code)
);

CREATE SEQUENCE enrollment_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE ENROLLMENT
(
    enrollment_code NUMBER PRIMARY KEY,
    student_code NUMBER NOT NULL,
    class_code NUMBER NOT NULL,
    enrollment_status NVARCHAR2(20),
    register_date DATE,
    lesson_code NUMBER,
    CONSTRAINT fk_enroll_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_enroll_course FOREIGN KEY (class_code) REFERENCES COURSES (class_code),
    CONSTRAINT fk_enroll_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code)
);

CREATE SEQUENCE payment_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE PAYMENT
(
    payment_id NUMBER PRIMARY KEY,
    payment_type NVARCHAR2(20),
    payment_status NVARCHAR2(20),
    payment_date DATE,
    enrollment_code NUMBER,
    user_id NUMBER,
    CONSTRAINT fk_payment_enroll FOREIGN KEY (enrollment_code) REFERENCES ENROLLMENT (enrollment_code),
    CONSTRAINT fk_payment_user FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE SEQUENCE ticket_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE TICKET
(
    ticket_id NUMBER PRIMARY KEY,
    ticket_time DATE,
    ticket_status NVARCHAR2(20),
    user_id NUMBER NOT NULL,
    employee_code NUMBER,
    payment_id NUMBER,
    CONSTRAINT fk_ticket_user FOREIGN KEY (user_id) REFERENCES USERS (id),
    CONSTRAINT fk_ticket_employee FOREIGN KEY (employee_code) REFERENCES EMPLOYEES (employee_code),
    CONSTRAINT fk_ticket_payment FOREIGN KEY (payment_id) REFERENCES PAYMENT (payment_id)
);

CREATE SEQUENCE message_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE MESSAGE
(
    message_id NUMBER PRIMARY KEY,
    message_text NVARCHAR2(500) NOT NULL,
    user_id NUMBER NOT NULL,
    ticket_id NUMBER NOT NULL,
    CONSTRAINT fk_message_user FOREIGN KEY (user_id) REFERENCES USERS (id),
    CONSTRAINT fk_message_ticket FOREIGN KEY (ticket_id) REFERENCES TICKET (ticket_id)
);

CREATE SEQUENCE report_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE REPORT
(
    report_id NUMBER PRIMARY KEY,
    report_date DATE,
    student_code NUMBER NOT NULL,
    teacher_code NUMBER NOT NULL,
    class_code NUMBER NOT NULL,
    CONSTRAINT fk_report_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_report_teacher FOREIGN KEY (teacher_code) REFERENCES TEACHERS (teacher_code),
    CONSTRAINT fk_report_course FOREIGN KEY (class_code) REFERENCES COURSES (class_code)
);

CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE PROJECT
(
    project_id NUMBER PRIMARY KEY,
    student_code NUMBER NOT NULL,
    class_code NUMBER NOT NULL,
    lesson_code NUMBER NOT NULL,
    session_id NUMBER,
    grade NUMBER(3,1),
    CONSTRAINT fk_project_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_project_course FOREIGN KEY (class_code) REFERENCES COURSES (class_code),
    CONSTRAINT fk_project_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code),
    CONSTRAINT fk_project_session FOREIGN KEY (session_id) REFERENCES SESSIONS (session_id)
);

CREATE SEQUENCE grade_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE GRADES
(
    grade_id NUMBER PRIMARY KEY,
    student_code NUMBER NOT NULL,
    class_code NUMBER NOT NULL,
    lesson_code NUMBER,
    grade_value NUMBER(3,1),
    CONSTRAINT fk_grade_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_grade_course FOREIGN KEY (class_code) REFERENCES COURSES (class_code),
    CONSTRAINT fk_grade_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code)
);

CREATE SEQUENCE attendance_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE ATTENDANCE
(
    attendance_id NUMBER PRIMARY KEY,
    student_code NUMBER NOT NULL,
    session_id NUMBER NOT NULL,
    status NVARCHAR2(20),
    CONSTRAINT fk_attend_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_attend_session FOREIGN KEY (session_id) REFERENCES SESSIONS (session_id)
);
