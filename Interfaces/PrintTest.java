package Interfaces;

interface Printable {
    void printContent();
}

class PDFDocument implements Printable {
    public void printContent() {
        System.out.println("Printing PDF content: 'PDF Document Sample'");
    }
}

class WordDocument implements Printable {
    public void printContent() {
        System.out.println("Printing Word content: 'Word Document Sample'");
    }
}

public class PrintTest {
    public static void main(String[] args) {
        Printable[] docs = {new PDFDocument(), new WordDocument()};

        for (Printable doc : docs) {
            doc.printContent();
        }
    }
}
