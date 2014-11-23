MagicTutor
==========

Main.java
----------
-Executes the program
-Uses password database

User.java
----------
-Superclass for Staff.java and Student.java
-Has firstName, lastName, username and passwordHash
-Has methods getDisplayName(), getPasswordHash(), getUserName(), changePassword(), logout()

Staff.java
----------
-Subclass of User.java
-Has title
-Has createStudent() and displayStudent()

Teacher.java
----------
-Subclass of Staff.java
-Has createQuiz(), changePassword(Student student), addToGroup(), viewData()

Admin.java
----------
-Subclass of Staff.java
-Has createTeacher(), deleteTeacher(), changePassword()

Student.java
----------
-Subclass of User.java
-Has viewData(), changePassword()

Group.java
----------
-Has students, name, teacher, quizzes
-Has getStudents(), getName(), getTeacher(), getQuizzes()

Quiz.java
----------
-Has timeCreated, duration 
-Uses text file database for questions and student marks
-Has getResults(), takeQuiz(), getTime(), getDuration()
