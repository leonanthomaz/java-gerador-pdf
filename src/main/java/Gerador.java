import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Gerador {
    public static void tableHeader(PdfPTable table){
        Stream.of("id", "nome", "idade", "email")
                .forEach(title -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.CYAN);
                    header.setBorder(1);
                    header.setPhrase(new Phrase(title));
                    table.addCell(header);
                });
    }

    public static void addRow(PdfPTable table){
        Cliente cliente = Cliente.builder().id(1L)
                .nome("Leonan").idade(33).email("leonan.thomaz@gmail.com")
                .build();

        table.addCell(String.valueOf(cliente.getId()));
        table.addCell(cliente.getNome());
        table.addCell(String.valueOf(cliente.getIdade()));
        table.addCell(cliente.getEmail());
    }

    public static void addCustomRow(PdfPTable table) throws IOException, DocumentException {
        try {
            // Caminho relativo para o arquivo de imagem
            String imagePath = "C:\\dev\\github\\java-gerador-pdf\\src\\main\\java\\java.jpg";

            // Obtém o caminho absoluto do arquivo de imagem
            Path path = Paths.get(imagePath);

            // Carrega a imagem a partir do caminho absoluto
            Image img = Image.getInstance(path.toAbsolutePath().toString());
//            Image img = Image.getInstance("C:\\dev\\projects\\java\\springpdf\\programador.png");

            // Adiciona a imagem à célula da tabela
            PdfPCell imageCell = new PdfPCell(img);

            // Adiciona a célula de imagem à tabela
            table.addCell(imageCell);
        } catch (BadElementException e) {
            e.printStackTrace();
        }
    }

    public static PdfPTable createClientInfoTable() throws IOException, DocumentException {
        PdfPTable clientInfoTable = new PdfPTable(2);
        clientInfoTable.setWidthPercentage(100);

        Cliente cliente = Cliente.builder().id(1L)
                .nome("Leonan").idade(33).email("leonan.thomaz@gmail.com")
                .build();

        // Adiciona imagem à tabela
        PdfPCell imageCell = new PdfPCell();
        imageCell.setBorder(Rectangle.NO_BORDER);
        imageCell.addElement(new Paragraph("Imagem do Cliente:"));
        imageCell.addElement(createImage("C:\\dev\\github\\java-gerador-pdf\\src\\main\\java\\java.jpg")); // Adiciona o nome do arquivo da imagem
        clientInfoTable.addCell(imageCell);

        // Adiciona informações do cliente à tabela
        PdfPCell infoCell = new PdfPCell();
        infoCell.setBorder(Rectangle.NO_BORDER);
        infoCell.addElement(new Paragraph("Informações do Cliente:"));
        infoCell.addElement(new Paragraph("Nome: " + cliente.getNome()));
        infoCell.addElement(new Paragraph("Idade: " + cliente.getIdade()));
        infoCell.addElement(new Paragraph("Email: " + cliente.getEmail()));
        clientInfoTable.addCell(infoCell);

        return clientInfoTable;
    }

    public static Image createImage(String imageName) throws IOException, BadElementException {
        Path imagePath = Paths.get(imageName);
        return Image.getInstance(imagePath.toAbsolutePath().toString());
    }
}
