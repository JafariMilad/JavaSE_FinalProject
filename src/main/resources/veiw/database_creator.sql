CREATE TABLE users
(
    id          NUMBER         PRIMARY KEY,
    first_name  NVARCHAR2(50)  NOT NULL,
    last_name   NVARCHAR2(50)  NOT NULL,
    email       NVARCHAR2(100) UNIQUE NOT NULL,
    address     NVARCHAR2(100) NOT NULL,
    national_Id NVARCHAR2(10)  UNIQUE,
    birth_date  DATE           NOT NULL
);
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE teachers
(
    teacher_code NUMBER PRIMARY KEY,
    lesson_code  NUMBER NOT NULL,
    CONSTRAINT fk_teachers_lesson FOREIGN KEY (lesson_code) REFERENCES lessons (lesson_code)
);
CREATE SEQUENCE teacher_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE STUDENTS
(
    student_code    NUMBER PRIMARY KEY,
    enrollment_code NUMBER NOT NULL,
    project_code    NUMBER NOT NULL,
    exersise_code   NUMBER NOT NULL,
    attendance_code NUMBER NOT NULL,
    lesson_code     NUMBER NOT NULL,
    CONSTRAINT fk_student_enrollment FOREIGN KEY (enrollment_code) REFERENCES ENROLLMENT (enrollment_code),
    CONSTRAINT fk_student_project FOREIGN KEY (project_code) REFERENCES PROJECT (project_code),
    CONSTRAINT fk_student_exersise FOREIGN KEY (exersise_code) REFERENCES exersise (exersise_code),
    CONSTRAINT fk_student_attendance FOREIGN KEY (attendance_code) REFERENCES ATTENDANCE (attendance_code),
    CONSTRAINT fk_student_lesson FOREIGN KEY (lesson_code) REFERENCES PROJECT (lesson_code)
);
CREATE SEQUENCE student_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE sessions
(
    session_code    NUMBER PRIMARY KEY,
    celass_code     NUMBER NOT NULL,
    lesson_code     NUMBER NOT NULL,
    attendance_code NUMBER NOT NULL,
    exersise_code   NUMBER NOT NULL,
    CONSTRAINT fk_sessions_celass FOREIGN KEY (celass_code) REFERENCES celass (celass_code),
    CONSTRAINT fk_sessions_lesson FOREIGN KEY (lesson_code) REFERENCES lessons (lesson_code),
    CONSTRAINT fk_sessions_attendance FOREIGN KEY (attendance_code) REFERENCES ATTENDANCE (attendance_code),
    CONSTRAINT fk_sessions_exersise FOREIGN KEY (exersise_code) REFERENCES exersise (exersise_code)
);
CREATE SEQUENCE session_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE lessons
(
    lesson_code     NUMBER          PRIMARY KEY,
    lesson_name     NVARCHAR2(50)   NOT NULL,
    lesson_status   NVARCHAR2(20)   NOT NULL,
    start_date      DATE            NOT NULL,
    end_date        DATE            NOT NULL,
    lesson_price    NUMBER          NOT NULL,
    lesson_time     NVARCHAR2(50)   NOT NULL,
    lesson_teacher  NUMBER          NOT NULL,
    student_code    NUMBER          NOT NULL,
    celass_code     NUMBER          NOT NULL,
    CONSTRAINT fk_lesson_teacher FOREIGN KEY (lesson_teacher) REFERENCES TEACHERS (teacher_code),
    CONSTRAINT fk_lesson_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_lesson_celass FOREIGN KEY (celass_code) REFERENCES COURSES (celass_code)
CREATE SEQUENCE lesson_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE ENROLLMENT
(
    enrollment_code   NUMBER          PRIMARY KEY,
    enrollment_status NVARCHAR2(20)   NOT NULL,
    lesson_code       NUMBER          NOT NULL,
    teacher_code      NUMBER          NOT NULL,
    student_code      NUMBER          NOT NULL,
    payment_code      NUMBER          NOT NULL,
    register_date     DATE            NOT NULL,
    CONSTRAINT fk_enrollment_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code),
    CONSTRAINT fk_enrollment_teacher FOREIGN KEY (teacher_code) REFERENCES teachers (teacher_code),
    CONSTRAINT fk_enrollment_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_enrollment_payment FOREIGN KEY (payment_code) REFERENCES Payments (payment_code)
);
CREATE SEQUENCE enrollment_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE Payments
(
    payment_code NUMBER             PRIMARY KEY,
    payment_status NVARCHAR2(20)    NOT NULL,
    payment_date DATE               NOT NULL,
    student_code NUMBER             NOT NULL,
    lesson_code NUMBER              NOT NULL,
    CONSTRAINT fk_payment_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_payment_lesson FOREIGN KEY (lesson_code) REFERENCES lessons (lesson_code)
);
CREATE SEQUENCE payment_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE Reports
(
    report_code  NUMBER   PRIMARY KEY,
    report_date  DATE     NOT NULL,
    student_code NUMBER   NOT NULL,
    lesson_code  NUMBER   NOT NULL,
    CONSTRAINT fk_report_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_report_lesson FOREIGN KEY (lesson_code) REFERENCES lessons (lesson_code)
);
CREATE SEQUENCE report_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE PROJECT
(
    project_code  NUMBER            PRIMARY KEY,
    project_title nvarchar2(100)    NOT NULL,
    student_code  NUMBER            NOT NULL,
    lesson_code   NUMBER            NOT NULL,
    session_code  NUMBER            NOT NULL,
    status        nvarchar2(20)     NOT NULL,
    CONSTRAINT fk_project_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_project_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code),
    CONSTRAINT fk_project_session FOREIGN KEY (session_code) REFERENCES SESSION (session_code)
);
CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE exersise
(
    exersise_code NUMBER PRIMARY KEY,
    exersise_date DATE NOT NULL,
    lesson_code   NUMBER NOT NULL,
    session_code  NUMBER NOT NULL,
    student_code  NUMBER NOT NULL,
    CONSTRAINT fk_Exersise_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code),
    CONSTRAINT fk_Exersise_session FOREIGN KEY (session_code) REFERENCES SESSION (session_code),
    CONSTRAINT fk_Exersise_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code)
);
CREATE SEQUENCE Exersise_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE celass
(
    class_code      NUMBER PRIMARY KEY,
    session_code    NUMBER NOT NULL,
    lesson_code     NUMBER NOT NULL,
    enrollment_code NUMBER NOT NULL,
    student_code    NUMBER NOT NULL,
    teacher_code    NUMBER NOT NULL,
    CONSTRAINT fk_celass_session FOREIGN KEY (session_code) REFERENCES sessions (session_code),
    CONSTRAINT fk_celass_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code),
    CONSTRAINT fk_celass_enrollment FOREIGN KEY (enrollment_code) REFERENCES ENROLLMENT (enrollment_code),
    CONSTRAINT fk_celass_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_celass_teacher FOREIGN KEY (teacher_code) REFERENCES teachers (teacher_code)
);
CREATE SEQUENCE grade_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE ATTENDANCE
(
    attendance_code     NUMBER          PRIMARY KEY,
    student_code        NUMBER          NOT NULL,
    attendance_status   NVARCHAR2(20)   NOT NULL,
    celass_code         NUMBER          NOT NULL,
    session_code        NUMBER          NOT NULL,
    attendance_date     DATE            NOT NULL,
    lesson_code         NUMBER          NOT NULL,
    CONSTRAINT fk_attendance_student FOREIGN KEY (student_code) REFERENCES STUDENTS (student_code),
    CONSTRAINT fk_attendance_celass FOREIGN KEY (celass_code) REFERENCES COURSES (celass_code),
    CONSTRAINT fk_attendance_session FOREIGN KEY (session_code) REFERENCES sessions (session_code),
    CONSTRAINT fk_attendance_lesson FOREIGN KEY (lesson_code) REFERENCES LESSONS (lesson_code)
);
CREATE SEQUENCE attendance_seq START WITH 1 INCREMENT BY 1;