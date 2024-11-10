package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.sql.SQLException;

public class ReportFormController {

    @FXML
    void btnEmployeeReportOnAction(ActionEvent event) {

        try {

            JasperDesign design= JRXmlLoader.load("src/main/resources/report/employee_report.jrxml");
            JRDesignQuery designQuery=new JRDesignQuery();
            designQuery.setText("SELECT * FROM Employee");
            design.setQuery(designQuery);

            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

            // Create a JRViewer and display it in a JFrame
            JRViewer viewer = new JRViewer(jasperPrint);
            JFrame frame = new JFrame("Employee Report");
            frame.add(viewer);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Use DISPOSE_ON_CLOSE to allow closing just the frame
            frame.setVisible(true);


        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnProductReportOnAction(ActionEvent event) {

        try {

            JasperDesign design= JRXmlLoader.load("src/main/resources/report/product_report.jrxml");
            JRDesignQuery designQuery=new JRDesignQuery();
            designQuery.setText("SELECT * FROM Product");
            design.setQuery(designQuery);

            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

            // Create a JRViewer and display it in a JFrame
            JRViewer viewer = new JRViewer(jasperPrint);
            JFrame frame = new JFrame("Product Report");
            frame.add(viewer);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Use DISPOSE_ON_CLOSE to allow closing just the frame
            frame.setVisible(true);


        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSalesReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierReportOnAction(ActionEvent event) {

        try {

            JasperDesign design= JRXmlLoader.load("src/main/resources/report/supplier_report.jrxml");
            JRDesignQuery designQuery=new JRDesignQuery();
            designQuery.setText("SELECT * FROM Supplier");
            design.setQuery(designQuery);

            JasperReport jasperReport= JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());

            // Create a JRViewer and display it in a JFrame
            JRViewer viewer = new JRViewer(jasperPrint);
            JFrame frame = new JFrame("Supplier Report");
            frame.add(viewer);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Use DISPOSE_ON_CLOSE to allow closing just the frame
            frame.setVisible(true);


        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
