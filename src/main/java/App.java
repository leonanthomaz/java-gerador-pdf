import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("C:\\dev\\github\\java-gerador-pdf\\src\\main\\java\\storage\\sample.pdf"));

        document.open();

        Paragraph paragraph = new Paragraph("Lorem Ipsum is simply dummy text" +
                " of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy " +
                "text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it " +
                "to make a type specim");
        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);
        Gerador.tableHeader(table);
        document.add(table);

        PdfPTable table2 = new PdfPTable(4);
        Gerador.addRow(table2);
        document.add(table2);

        PdfPTable table3 = new PdfPTable(1);
        Gerador.addCustomRow(table3);
        document.add(table3);

        PdfPTable clientInfoTable = Gerador.createClientInfoTable();
        document.add(clientInfoTable);

        document.close();
    }
}
