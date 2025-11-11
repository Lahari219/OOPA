package Generics;

import java.util.UUID;

class PatientRecord<T> {
    private T patientId;
    private String name;

    public PatientRecord(T patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public <U> void display(U department) {
        System.out.println("Department: " + department + ", ID: " + patientId + ", Name: " + name);
    }
}

public class HospitalGenericsExample {
    public static void main(String[] args) {
        PatientRecord<Integer> cardio = new PatientRecord<>(101, "John");
        PatientRecord<String> neuro = new PatientRecord<>("N-202", "Alice");
        PatientRecord<UUID> pediatrics = new PatientRecord<>(UUID.randomUUID(), "Emma");

        cardio.display("Cardiology");
        neuro.display("Neurology");
        pediatrics.display("Pediatrics");
    }
}
