package com.dursun;

import com.dursun.model.*;
import com.dursun.service.*;
import com.dursun.ui.PersonelController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by drsnkrt on 26.07.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:hibernate-config.xml"})
public class Tester {

    @Autowired
    private IMainBoardService service;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IRamService ramService;

    @Autowired
    private IHardDiskService diskService;

    @Autowired
    private IExtraService extraSetvice;

    @Autowired
    private IPersonelService personelService;

    @Test
    public void create() {


        MainBoard mB = new MainBoard();
        mB.setBrand("Dursun");
        mB.setModel("Asus");
        mB.setInStockDate(new Date());

        MainBoardDetails details = new MainBoardDetails();
        details.setProcessorType("amd");
        details.setSerialNumber("asd21122asd");
//        details.setImage(file);
//        details.setMainBoard(mB);

//        details.setImage("C:\\Users\\drsnkrt\\Pictures\\kral.jpg");
        mB.setMainBoardDetails(details);

        service.save(mB);
    }


    @Test
    public void update() {

//        MainBoard board = service.getAllByStatus().get(0);
        MainBoard board = service.getById(6100);

        if (board == null) {
            System.out.println("kayıt bulunamadı!");
            return;
        }

        System.out.println(board.getId() + " id'li kayıt güncellenecek!");
        board.setBrand("HElloasdfasdf");
        board.setModel("madafaka");
//        mainBoard.setInStockDate("05-10-1190");
        service.save(board);
    }


    @Test
    public void getCities() {

        List<City> cities = cityService.getAllCity();

        for (City city : cities) {
            System.out.println(city.getCityId() + " " + city.getCityName());
        }
    }

    @Test
    public void getTowns() {

        List<Town> towns = cityService.getTownsByCityID(53);

        for (Town town : towns) {
            System.out.println(town.getTownId() + " " + town.getTownName());
        }
    }

    @Test
    public void getCityById() {

        System.out.println(cityService.getCityById(53));

    }

    @Test
    public void save() {

        Personel personel = new Personel();
        personel.setTckn(22222222222L);
        personel.setName("Mucahit");
        personel.setSurName("Gecimli");
        personel.setPhone("05551111100");
        personel.seteMail("muco@ddd.net");
        personel.setCity("Kayseri");
        personel.setTown("Merkez");
        personel.setMaritalStatus("Bekar");
        personel.setBloodGroup("A Rh-");
        personel.setTitle("Testci");
        personel.setRecordStatus("Aktif");
        personel.setAddress("Üsküdar Merklez");
        personelService.saveOrUpdate(personel);
    }

    @Test
    public void getById() {
        System.out.println(personelService.getById(22222222222L).getSurName());

    }

    @Test
    public void getAll() {

        List<Personel> all = personelService.getAll();

        for (Personel piece : all) {
            System.out.println(piece.getTckn() + " " + piece.getSurName());
        }
    }

    @Test
    public void xx(){

        PersonelController personelController= new PersonelController();
        personelController.randomString();

    }

}
