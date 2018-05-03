package com.dursun.ui;

import com.dursun.model.*;
import com.dursun.service.ICityService;
import com.dursun.service.IExtraService;
import com.dursun.service.IPersonelService;
import com.dursun.utils.SpringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Created by drsnkrt on 7.09.2017.
 */
public class PersonelController implements Initializable {

    //Services
    private IPersonelService personelService;
    private ICityService cityService;
    private IExtraService extraService;


    //ObsevableLists
    private ObservableList<Personel> personelList;
    private ObservableList<City> cityList;
    private ObservableList<Town> townList;
    private ObservableList<MaritalStatus> maritalList;
    private ObservableList<Title> titleList;
    private ObservableList<BloodGroup> bloodList;
    private ObservableList<RecordStatus> recordList;

    //TableView and columns
    public TableView<Personel> personelTV;
    public TableColumn<Personel, Long> tckn;
    public TableColumn<Personel, String> name;
    public TableColumn<Personel, String> surname;


    //TextFields Buttons vs.
    public ImageView iVAvatar;
    public Image avatar;
    public TextField txtTckn;
    public TextField txtName;
    public TextField txtSurname;
    public TextField txtPhone;
    public TextField txtEmail;
    public ComboBox cBCity;
    public ComboBox cBTown;
    public ComboBox cBMaritalStatus;
    public ComboBox cBTitle;
    public ComboBox cBBloodGroup;
    public ComboBox cBRecordStatus;
    public TextArea txtAddress;
    public Button btnRefresh;
    public Button btnSave;
    public DatePicker dpBirthDate;

    String s;
    String ext;
    private File selectedFile;
    String copiedFileName;
    public TextField txtSearch;
    public FilteredList filter;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dirImgFolder();
        setPhoneFormat();

        //Services
        personelService = SpringUtils.getSingleBeanOfType(IPersonelService.class);
        cityService = SpringUtils.getSingleBeanOfType(ICityService.class);
        extraService = SpringUtils.getSingleBeanOfType(IExtraService.class);

        //ObservableLists
        personelList = FXCollections.observableArrayList();
        cityList = FXCollections.observableArrayList();
        townList = FXCollections.observableArrayList();
        maritalList = FXCollections.observableArrayList();
        titleList = FXCollections.observableArrayList();
        bloodList = FXCollections.observableArrayList();
        recordList = FXCollections.observableArrayList();

        //TableView and columns
        tckn.setCellValueFactory(new PropertyValueFactory<Personel, Long>("tckn"));
        name.setCellValueFactory(new PropertyValueFactory<Personel, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Personel, String>("surName"));


        //Fill the table
        personelList.clear();
        personelList.addAll(personelService.getAll());
        personelTV.setItems(personelList);
        personelTV.refresh();

        //FillComboboxes
        cityList.addAll(cityService.getAllCity());
        cBCity.setItems(cityList);
        maritalList.addAll(extraService.getAllMaritals());
        cBMaritalStatus.setItems(maritalList);
        titleList.addAll(extraService.getAllTitles());
        cBTitle.setItems(titleList);
        bloodList.addAll(extraService.getAllBloods());
        cBBloodGroup.setItems(bloodList);
        recordList.addAll(extraService.getAllRecordStatus());
        cBRecordStatus.setItems(recordList);

        //İmageViews settings
        final Circle clip = new Circle(62.5, 62.5, 58);
        clip.setEffect(new Shadow(10, Color.BLACK));
        File file = new File("C:\\Users\\drsnkrt\\IdeaProjects\\StokTakip\\src\\main\\resources\\img\\images.png");
        avatar = new Image(file.toURI().toString());
        iVAvatar.setClip(clip);
        iVAvatar.setImage(avatar);


        //Fields Rules
        txtTckn.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(11));
        txtName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
        txtSurname.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
//        txtPhone.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(11));

    }


    public void refreshPersonelTable(ActionEvent actionEvent) {

        personelList.clear();
        personelList.addAll(personelService.getAll());
        personelTV.setItems(personelList);
        personelTV.refresh();

    }


    public void makeAlert(String title, String header, String context) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
        return;

    }

    public void fillFields(MouseEvent mouseEvent) {


        txtTckn.setDisable(true);

        if (!personelTV.getSelectionModel().isEmpty()) {

            Personel sp = personelTV.getSelectionModel().getSelectedItem();

            txtTckn.setText(String.valueOf(sp.getTckn()));
            txtName.setText(sp.getName());
            txtSurname.setText(sp.getSurName());
            txtPhone.setText(sp.getPhone());
            txtEmail.setText(sp.geteMail());
            cBCity.setPromptText(sp.getCity());
            cBTown.setPromptText(sp.getTown());
            cBMaritalStatus.setValue(sp.getMaritalStatus());
            cBTitle.setValue(sp.getTitle());
            cBBloodGroup.setValue(sp.getBloodGroup());
            cBRecordStatus.setValue(sp.getRecordStatus());
            txtAddress.setText(sp.getAddress());
            dpBirthDate.setValue(LocalDate.parse(sp.getBirthDate().toString()));
            File file = new File(sp.getImage().toString());
            avatar = new Image(file.toURI().toString());
            iVAvatar.setImage(avatar);

            personelTV.getSelectionModel().clearSelection();

        }

    }

    public void chooseImage(MouseEvent mouseEvent) {

        iVAvatar.setDisable(true);

        File defaultPath = new File(System.getProperty("user.home"), "./");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(defaultPath);
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            avatar = new Image(selectedFile.toURI().toString(), 200, 200, true, true);
            iVAvatar.setImage(avatar);
            iVAvatar.setDisable(false);

        } else {
            makeAlert("Hata", "Resim seçilemedi", "İşlem Kullanıcı Tarafından iptal edildi");
            iVAvatar.setDisable(false);
        }
    }

    public void save(ActionEvent actionEvent) {

        if (txtTckn.isDisable() == false) {

            Personel personel = new Personel();
            if (selectedFile == null) {

                personel.setImage("C:\\Users\\drsnkrt\\Pictures\\images.png");
            } else {
                copyImg();
                personel.setImage("C:\\Users\\" + System.getProperty("user.name") + "\\IMG\\" + copiedFileName + "." + ext);
            }
            personel.setTckn(Long.parseLong(txtTckn.getText()));
            personel.setName(txtName.getText());
            personel.setSurName(txtSurname.getText());
            personel.setPhone(txtPhone.getText());
            personel.seteMail(txtEmail.getText());
            personel.setCity(cBCity.getValue().toString());
            personel.setTown(cBTown.getValue().toString());
            personel.setMaritalStatus(cBMaritalStatus.getValue().toString());
            personel.setTitle(cBTitle.getValue().toString());
            personel.setBloodGroup(cBBloodGroup.getValue().toString());
            personel.setRecordStatus(cBRecordStatus.getValue().toString());
            personel.setBirthDate(Date.from(dpBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            personel.setAddress(txtAddress.getText());

            personelService.saveOrUpdate(personel);

        } else {

            long id = Long.parseLong(txtTckn.getText());
            Personel personel = personelService.getById(id);
            if (!(selectedFile == null)) {
                copyImg();
                personel.setImage("C:\\Users\\" + System.getProperty("user.name") + "\\IMG\\" + copiedFileName + "." + ext);
            } else {

                personel.setImage(personel.getImage());

            }
            personel.setName(txtName.getText());
            personel.setSurName(txtSurname.getText());
            personel.setPhone(txtPhone.getText());
            personel.seteMail(txtEmail.getText());

            personel.setCity((String) cBCity.getSelectionModel().getSelectedItem().toString());
            personel.setTown((String) cBTown.getSelectionModel().getSelectedItem().toString());
            personel.setMaritalStatus(cBMaritalStatus.getValue().toString());
            personel.setTitle(cBTitle.getValue().toString());
            personel.setBloodGroup(cBBloodGroup.getValue().toString());
            personel.setRecordStatus(cBRecordStatus.getValue().toString());
            personel.setBirthDate(Date.from(dpBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            personel.setAddress(txtAddress.getText());

            personelService.saveOrUpdate(personel);
        }

    }

    public void getTownById(ActionEvent actionEvent) {

        cBTown.getItems().clear();
        townList.addAll(cityService.getTownsByCityID(cBCity.getSelectionModel().getSelectedIndex() + 1));
        cBTown.setItems(townList);


    }


    public void search(KeyEvent keyEvent) {

        filter = new FilteredList(personelList, e -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super Personel>) (Personel personel) -> {

                if (newValue.isEmpty() || newValue == null) {

                    return true;
                } else if (personel.getName().toLowerCase().contains(newValue.toLowerCase())) {

                    return true;
                } else if (String.valueOf(personel.getTckn()).contains(newValue)) {
                    return true;

                }

                return false;
            });


        });


        SortedList list = new SortedList(filter);
        list.comparatorProperty().bind(personelTV.comparatorProperty());
        personelTV.setItems(list);
    }

    public void dirImgFolder() {

        new File("C:\\Users\\" + System.getProperty("user.name") + "\\IMG").mkdir();
    }

    public String randomString() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        copiedFileName = sb.toString();
        return sb.toString();
    }

    public String uzantiBul(File f) {
        ext = null;
        s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public void copyImg() {

        FileInputStream in = null;
        FileOutputStream out = null;

        String copiedImagePath = "C:\\Users\\" + System.getProperty("user.name") + "\\IMG\\";
        randomString();
        uzantiBul(selectedFile);

        try {
            in = new FileInputStream(selectedFile.getPath().toString());
            out = new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\IMG\\" + copiedFileName + "." + ext);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPhoneFormat() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#(###)###-##-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtPhone);
        tff.formatter();
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
                if (e.getCharacter().matches("[A-Za-zI-İŞ-şÜ-üÇ-ç@]")) {
                } else {
                    e.consume();
                }
            }
        };
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

}

