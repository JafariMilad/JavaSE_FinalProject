create table student
(
    student_code     number primary key,
    enrollment       nvarchar2(20) not null,
    project          nvarchar2(20) not null,
    exersise         nvarchar2(20) not null,
    attendance       nvarchar2(20) not null,
    ticket           nvarchar2(20) not null,
);

create sequence student_seq start with 1 increment by 1;


create table users
(
    id            number primary key,
    first_name    nvarchar2(20) not null,
    last_name     nvarchar2(20) not null,
    email         nvarchar2(30) not null,
    address       nvarchar2(40) not null,
    nationalId    number(1) default 0,
    birth_date    date default sysdate,

);

create sequence user_seq start with 1 increment by 1;


create table class
(
    class_code              number primary key,
    constraint lesson_code FOREIGN KEY (lesson_code) references lesson (lesson_code),
    teacher_name      nvarchar2(30) not null,
);

create sequence class_seq start with 1 increment by 1;


create table teachers 
(
    teacher_code    number primary key,
    lesson          nvarchar2(30) not null,
);

create sequence teacher_seq start with 1 increment by 1;




create table lessons
(
    lesson_code     number primary key,
    class_code      number,
    lesson_status   nvarchar2(20) not null,
    lesson_date     date          not null,
    lesson_teacher  nvarchar2(20) not null,
    class           nvarchar2(20) not null,  
);

create sequence lesson_seq start with 1 increment by 1;


create table enrollment
(
    constraint lesson_code FOREIGN KEY (lesson_code) references lesson (lesson_code),
    constraint class_code FOREIGN KEY (lesson_code) references class (class_code),
    paymentId         nvarchar2(20) not null,
    enrollment_status nvarchar2(20) not null,
    constraint student FOREIGN KEY (student) references student (student_code),
    constraint teacher FOREIGN KEY (teacher) references teacher (teacher_code),
    registerDate date,
    classStatus     nvarchar2(20) not null,
);

create sequence enrollment_seq start with 1 increment by 1;

