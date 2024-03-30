package com.reservationapp.util;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.reservationapp.entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;


@Service
public class PdfTicketGeneratorService {

    public byte[] generatePdfTicket(Passenger passenger, String fromLocation, String toLocation, String fromDate,
                                   String busNumber, String busType, String totalDuration){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document =new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph("Ticket Information:"));
            document.add(new Paragraph("Name: " + passenger.getFirstName() + " " + passenger.getLastName()));
            document.add(new Paragraph("Email: " +passenger.getEmail() ));
            document.add(new Paragraph("Mobile: " +passenger.getMobile() ));
            document.add(new Paragraph("Bus Id: " +passenger.getBusId() ));
            document.add(new Paragraph("Route Id: " + passenger.getRouteId()));
            document.add(new Paragraph("From Location: " + fromLocation));
            document.add(new Paragraph("To Location: " + toLocation));
            document.add(new Paragraph("From Date: " + fromDate));

            document.add(new Paragraph("Bus Name: " + busNumber));
            document.add(new Paragraph("Bus Type: " + busType));
            document.add(new Paragraph("Total Duration: " + totalDuration));

            // Add more ticket details as needed
            document.close();
        }catch (DocumentException e){
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
