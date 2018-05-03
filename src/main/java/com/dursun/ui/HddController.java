package com.dursun.ui;

import com.dursun.model.HardDisk;
import com.dursun.service.IHardDiskService;
import com.dursun.utils.SpringUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Created by drsnkrt on 28.08.2017.
 */
public class HddController implements Initializable {

    private IHardDiskService diskService;
    private ObservableList<HardDisk> diskList;

    public TableView<HardDisk> hddTable;
    public TableColumn<HardDisk, Integer> id;
    public TableColumn<HardDisk, String> brand;
    public TableColumn<HardDisk, String> model;
    public TableColumn<HardDisk, String> inStockDate;
    public TableColumn<HardDisk, Boolean> status;

    public TextField txtSearch;
    public FilteredList filter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        diskService = SpringUtils.getSingleBeanOfType(IHardDiskService.class);
        diskList = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<HardDisk, Integer>("id"));
        brand.setCellValueFactory(new PropertyValueFactory<HardDisk, String>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<HardDisk, String>("model"));
        inStockDate.setCellValueFactory(new PropertyValueFactory<HardDisk, String>("inStockDate"));
        status.setCellValueFactory(new PropertyValueFactory<HardDisk, Boolean>("status"));

        hddTable.setEditable(true);
        brand.setCellFactory(TextFieldTableCell.forTableColumn());
        model.setCellFactory(TextFieldTableCell.forTableColumn());
        inStockDate.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(col -> {
            CheckBoxTableCell<HardDisk, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty active = new SimpleBooleanProperty(hddTable.getItems().get(index).isStatus());
                active.addListener((obs, wasActive, isNowActive) -> {
                    HardDisk item = hddTable.getItems().get(index);
                    item.setStatus(isNowActive);
                    diskService.save(item);
                });
                return active;
            });
            return cell;
        });


    }


    public void refresh(ActionEvent actionEvent) {

        diskList.clear();
        diskList.addAll(diskService.getAll());
        hddTable.setItems(diskList);
        hddTable.refresh();

    }

    public void changeBrand(TableColumn.CellEditEvent<HardDisk, String> hardDiskStringCellEditEvent) {
        HardDisk selectedCell = hddTable.getSelectionModel().getSelectedItem();
        selectedCell.setBrand((hardDiskStringCellEditEvent.getNewValue().toString()));
        diskService.save(selectedCell);

    }

    public void changeModel(TableColumn.CellEditEvent<HardDisk, String> hardDiskStringCellEditEvent) {
        HardDisk selectedCell = hddTable.getSelectionModel().getSelectedItem();
        selectedCell.setModel((hardDiskStringCellEditEvent.getNewValue().toString()));
        diskService.save(selectedCell);

    }

    public void changeDate(TableColumn.CellEditEvent<HardDisk, String> hardDiskStringCellEditEvent) {
        HardDisk selectedCell = hddTable.getSelectionModel().getSelectedItem();
        selectedCell.setInStockDate((hardDiskStringCellEditEvent.getNewValue()));
        diskService.save(selectedCell);

    }


    public void search(KeyEvent keyEvent) {

        filter = new FilteredList(diskList, e -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super HardDisk>) (HardDisk disk) -> {


                if (newValue.isEmpty() || newValue == null) {

                    return true;
                } else if (disk.getBrand().toLowerCase().contains(newValue.toLowerCase())) {

                    return true;
                } else if (String.valueOf(disk.getId()).contains(newValue)) {
                    return true;

                }

                return false;
            });


        });


        SortedList list = new SortedList(filter);
        list.comparatorProperty().bind(hddTable.comparatorProperty());
        hddTable.setItems(list);

    }


}
