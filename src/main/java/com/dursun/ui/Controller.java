package com.dursun.ui;

import com.dursun.model.City;
import com.dursun.model.MainBoard;
import com.dursun.model.MainBoardDetails;
import com.dursun.model.Town;
import com.dursun.service.ICityService;
import com.dursun.service.IMainBoardService;
import com.dursun.utils.SpringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class Controller implements Initializable {


    private IMainBoardService boardService;
    private ICityService cityService;

    private ObservableList<MainBoard> boardList;

    private ObservableList<City> cityList;
    private ObservableList<Town> townList;

    ToggleGroup group;
    public RadioButton rBMan;
    public RadioButton rBWoman;
    public TextField txtBrand;
    public TextField txtModel;
    public ComboBox<String> cbProcessType;
    public ComboBox<Town> cbTown;
    public ComboBox<City> cbCity;
    public TextField txtSerialNumber;
    public DatePicker DPDate;
    public TextField txtID;
    public Label lblId;
    public Label lblBrowsedFile;
    public ImageView iVimage;
    public ImageView avatar;

    public Image image;
    public TableView<MainBoard> boardTable;
    public TableColumn<MainBoard, Integer> id;
    public TableColumn<MainBoard, String> brand;
    public TableColumn<MainBoard, String> model;
    public TableColumn<MainBoard, String> inStockDate;

    private File selectedFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boardService = SpringUtils.getSingleBeanOfType(IMainBoardService.class);
        cityService = SpringUtils.getSingleBeanOfType(ICityService.class);

        boardList = FXCollections.observableArrayList();
        cityList = FXCollections.observableArrayList();
        townList = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<MainBoard, Integer>("id"));
        brand.setCellValueFactory((new PropertyValueFactory<MainBoard, String>("brand")));
        model.setCellValueFactory(new PropertyValueFactory<MainBoard, String>("model"));
        inStockDate.setCellValueFactory(new PropertyValueFactory<MainBoard, String>("inStockDate"));

        final Circle clip = new Circle(74, 75, 70);
        clip.setEffect(new Shadow(10, Color.BLACK));

        File file = new File("C:\\Users\\drsnkrt\\Pictures\\images.png");
        image = new Image(file.toURI().toString());
//        iVimage.getStyleClass().add("cb2");
        iVimage.setClip(clip);

        iVimage.setImage(image);

        group = new ToggleGroup();
        rBMan.setToggleGroup(group);
        rBWoman.setToggleGroup(group);
        rBMan.setSelected(true);

//        StringConverter<City> converter = new StringConverter<City>() {
//            @Override
//            public String toString(City object) {
//                return object.getCityName();
//            }
//
//            @Override
//            public City fromString(String string) {
//                return null;
//            }
//        };
//        cbCity.setConverter(converter);
//
//        StringConverter<Town> townConverter = new StringConverter<Town>() {
//            @Override
//            public String toString(Town object) {
//                return object.getTownName();
//            }
//
//            @Override
//            public Town fromString(String string) {
//                return null;
//            }
//        };
//        cbTown.setConverter(townConverter);


        cityList.addAll(cityService.getAllCity());
        cbCity.setItems(cityList);
        cbCity.getStyleClass().add("combbx");

        cbProcessType.getItems().addAll("Intel", "AMD");
        cbProcessType.setValue("Intel");

//        boardList.addAll(boardService.getAllByStatus());
//        boardTable.setItems(boardList);


        // Metoda istenen rakam kadar parametre gonderılecek
        txtSerialNumber.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(15));
        // Metoda istenen harf kadar parametre gonderılecek
        txtBrand.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
        txtModel.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));


    }


    /**
     * Bu metod sadece numara girilmesi gereken alanlar için kullnılacak tckn gibi
     */
        public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
            return new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    TextField txt_TextField = (TextField) e.getSource();
                    if (txt_TextField.getText().length() >= max_Lengh) {
                        e.consume();
                    }
                    if (e.getCharacter().matches("[0-9.]")) {
                        if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                            e.consume();
                        } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                            e.consume();
                        }
                    } else {
                        e.consume();
                    }
                }
            };
        }

    /**
     * Bu metod sadece harf girilmesi gereken alanlar için kullnılacak isim soyisim gibi
     */
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[A-Za-z]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public void kaydet(ActionEvent actionEvent) {

        if (lblId.getText().equals("")) {
            MainBoard mB = new MainBoard();
            mB.setBrand(txtBrand.getText());
            mB.setModel(txtModel.getText());
            DPDate.setPromptText("GG-AA-YYYY");

            if (DPDate.getValue() == null) {
                mB.setInStockDate(new Date());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Program bu günün tarihini atayacak");
                alert.showAndWait();

            } else {
                mB.setInStockDate(Date.from(DPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            }
            MainBoardDetails details = new MainBoardDetails();
            details.setSerialNumber(txtSerialNumber.getText());
            details.setProcessorType(cbProcessType.getValue());
            if (selectedFile != null) {
                details.setImage(selectedFile.getPath().toString());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("Program otomatik resim atayacak");
                alert.showAndWait();

                details.setImage("C:\\Users\\drsnkrt\\Pictures\\images.png");
            }
            mB.setMainBoardDetails(details);

//            if (txtBrand.getText().length() < 4 || txtModel.getText().length() < 6 || txtSerialNumber.getText().length() < 11) {
//                txtSerialNumber.setPromptText("Alan Boş Bırakılamaz");
//                txtBrand.setPromptText("Alan Boş Bırakılamaz");
//                txtModel.setPromptText("Alan Boş Bırakılamaz");
//            } else {

            boardService.save(mB);
        } else {
            int id = Integer.parseInt(lblId.getText());
            MainBoard byId = boardService.getById(id);
            byId.setBrand(txtBrand.getText());
            byId.setModel(txtModel.getText());
            DPDate.setPromptText("GG-AA-YYYY");
            byId.setInStockDate(Date.from(DPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            boardService.save(byId);
        }


    }

    public void sil(ActionEvent actionEvent) {


        int id = Integer.parseInt(txtID.getText());
        MainBoard byId = boardService.getById(id);

        if (byId == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("kayıt yok");

            alert.showAndWait();
            return;
        }

        boardService.delete(byId);
        boardList.removeAll();
        boardList.addAll(boardService.getAll());
        boardTable.setItems(boardList);

    }

    public void getAllRecords(ActionEvent actionEvent) {

        boardList.clear();
        boardList.addAll(boardService.getAll());
        boardTable.setItems(boardList);
        boardTable.refresh();
    }

    public void fillTxt(MouseEvent mouseEvent) {

        if (!boardTable.getSelectionModel().isEmpty()) {

            MainBoard selectedItem = boardTable.getSelectionModel().getSelectedItem();
            lblId.setText(String.valueOf(selectedItem.getId()));
            txtModel.setText(selectedItem.getModel());
            txtBrand.setText(selectedItem.getBrand());
            DPDate.setPromptText(selectedItem.getInStockDate().toString().substring(0, 10));
            cbProcessType.setValue(selectedItem.getMainBoardDetails().getProcessorType().toString());
            txtSerialNumber.setText(selectedItem.getMainBoardDetails().getSerialNumber().toString());

            File file = new File(selectedItem.getMainBoardDetails().getImage().toString());
            image = new Image(file.toURI().toString());
            iVimage.setImage(image);

            boardTable.getSelectionModel().clearSelection();

        }
    }

    public void dosyaAc(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            lblBrowsedFile.setText("Seçilen Dosya: " + selectedFile.getName());
            image = new Image(selectedFile.toURI().toString(), 200, 200, true, true);
            iVimage.setImage(image);
        } else {
            lblBrowsedFile.setText("İşlem İptal edildi.");
        }

    }

    public void txtBrandOnValueChanged(KeyEvent keyEvent) {
        colorTxtFields(txtBrand, 3);
    }

    public void txtModelOnValueChanged(KeyEvent keyEvent) {
        colorTxtFields(txtModel, 5);
    }

    public void txtSerialNumberOnValueChanged(KeyEvent keyEvent) {
        colorTxtFields(txtSerialNumber, 10);
    }

    public void colorTxtFields(TextField textField, int charLenght) {

        if (textField.getText().length() < charLenght && !textField.getStyleClass().contains("txt-border-red")) {
            textField.getStyleClass().add("txt-border-red");
        }
        if (textField.getText().length() >= charLenght) {
            textField.getStyleClass().remove("txt-border-red");
            textField.getStyleClass().add("txt-border-green");
        }

    }

    public void cbFill(ActionEvent actionEvent) {

//        System.out.println(cbCity.getSelectionModel().getSelectedItem().getCityId());
        cbTown.getItems().clear();
        townList.addAll(cityService.getTownsByCityID(cbCity.getSelectionModel().getSelectedItem().getCityId()));
        cbTown.setItems(townList);
    }

    public void opernPopUp(ActionEvent actionEvent) {


        /**
         * radiobutton Control
         * */
        if (rBMan.selectedProperty().getValue() == true) {
            System.out.println(rBMan.getText());
        } else {

            System.out.println(rBWoman.getText());
        }


        /**
         * Yeni fxml Page Açar
         * */
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ram.fxml"));
//            Parent root1 = null;
//
//            root1 = (Parent) fxmlLoader.load();
//
//            Stage stage = new Stage();
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("ABC");
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


}

