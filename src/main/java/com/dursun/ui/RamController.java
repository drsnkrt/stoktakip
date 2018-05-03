package com.dursun.ui;

import com.dursun.model.Ram;
import com.dursun.service.IRamService;
import com.dursun.utils.SpringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by drsnkrt on 24.08.2017.
 */
public class RamController implements Initializable {

    private IRamService ramService;

    private ObservableList<Ram> ramList;

    public TextField txtBrand;
    public ComboBox<String> cbType;
    public DatePicker DPDate;
    public Label lblId;

    public TableView<Ram> ramTable;
    public TableColumn<Ram, Integer> id;
    public TableColumn<Ram, String> brand;
    public TableColumn<Ram, String> type;
    public TableColumn<Ram, String> inStockDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ramService = SpringUtils.getSingleBeanOfType(IRamService.class);

        ramList = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<Ram, Integer>("id"));
        brand.setCellValueFactory(new PropertyValueFactory<Ram, String>("brand"));
        type.setCellValueFactory(new PropertyValueFactory<Ram, String>("type"));
        inStockDate.setCellValueFactory(new PropertyValueFactory<Ram, String>("inStockDate"));

        cbType.getItems().addAll("DDR3", "DDR4");
        cbType.setValue("DDR3");

        ramTable.setEditable(true);
        brand.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    public void kaydet(ActionEvent actionEvent) {

        if (lblId.getText().isEmpty()) {
            Ram ram = new Ram();
            ram.setBrand(txtBrand.getText());
            ram.setType(cbType.getValue());
            ram.setInStockDate(Date.from(DPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ramService.save(ram);

        } else {

            int id = Integer.parseInt(lblId.getText());
            Ram ram = ramService.getById(id);
            ram.setBrand(txtBrand.getText());
            ram.setType(cbType.getValue());
            ram.setInStockDate(ramService.getById(id).getInStockDate());
            ramService.save(ram);
        }
        alanTemizle();
        yenidenDoldur();
    }

    public void alanTemizle() {

        lblId.setText("");
        txtBrand.clear();
        cbType.getItems().clear();
        cbType.getItems().addAll("DDR3", "DDR4");

    }


    public void fillValues(MouseEvent mouseEvent) {

        alanTemizle();
        if (!ramTable.getSelectionModel().isEmpty()) {

            Ram selectedItem = ramTable.getSelectionModel().getSelectedItem();
            lblId.setText(String.valueOf(selectedItem.getId()));
            cbType.setValue(selectedItem.getType().toString());
            txtBrand.setText(selectedItem.getBrand());
            DPDate.setPromptText(selectedItem.getInStockDate().toString().substring(0, 10));
            ramTable.getSelectionModel().clearSelection();

        }
    }

    public void getRams(ActionEvent actionEvent) {

        ramList.clear();
        ramList.addAll(ramService.getAll());
        ramTable.setItems(ramList);
        ramTable.refresh();
    }

    public void yenidenDoldur() {

        ramList.clear();
        ramList.addAll(ramService.getAll());
        ramTable.setItems(ramList);
        ramTable.refresh();

    }




}
