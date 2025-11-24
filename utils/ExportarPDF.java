package utils;

import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportarPDF {

    public static void exportarTabla(JTable tabla) {
        try {
            Document pdf = new Document(PageSize.A4.rotate());
            String nombre = "Reporte_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";

            PdfWriter.getInstance(pdf, new FileOutputStream(nombre));
            pdf.open();

            Paragraph titulo = new Paragraph("Reporte de Inventario\n\n",
                    new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            pdf.add(titulo);

            TableModel model = tabla.getModel();
            PdfPTable tb = new PdfPTable(model.getColumnCount());
            tb.setWidthPercentage(100);

            // Encabezados
            for (int i = 0; i < model.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Paragraph(model.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tb.addCell(cell);
            }

            // Filas
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    tb.addCell(model.getValueAt(r, c).toString());
                }
            }

            pdf.add(tb);
            pdf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}