package AbstractClass;

abstract class Course {
    abstract void startCourse();  // Behavior differs per course
    abstract void endCourse();    // Behavior differs per course

    void getCourseDetails() {     // Common for all
        System.out.println("Course details are available.");
    }

    void giveCertificate() {      // Same for all courses
        System.out.println("Certificate issued.");
    }
}

class ProgrammingCourse extends Course {
    @Override
    void startCourse() {
        System.out.println("Starting programming course...");
    }

    @Override
    void endCourse() {
        System.out.println("Ending programming course...");
    }
}

class LanguageCourse extends Course {
    @Override
    void startCourse() {
        System.out.println("Starting language course...");
    }

    @Override
    void endCourse() {
        System.out.println("Ending language course...");
    }
}

public class ElearningApp {
    public static void main(String[] args) {
        Course prog = new ProgrammingCourse();
        prog.startCourse();
        prog.getCourseDetails();
        prog.giveCertificate();
        prog.endCourse();

        Course lang = new LanguageCourse();
        lang.startCourse();
        lang.getCourseDetails();
        lang.giveCertificate();
        lang.endCourse();
    }
}



