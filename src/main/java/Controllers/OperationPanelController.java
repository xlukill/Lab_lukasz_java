package Controllers;

import DAO.*;
import Entity.*;
import LogicalAspects.DataOperation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import net.bytebuddy.asm.Advice;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


public class OperationPanelController {
    @FXML
    Tab adminPane;

    @FXML
    DatePicker dateValuePicker;

    @FXML
    ChoiceBox choiceBox;

    @FXML
    ChoiceBox flyChoiceBox;

    @FXML
    TextField countryField;

    @FXML
    TextField cityField;



    @FXML
    TextField costField;
    @FXML
    TextField countryUserField;
    @FXML
    TextField cityUserField;
    @FXML
    TextField costUserField;

    @FXML
    RadioButton availableButton;

    UserInfoEntity userInfoEntity;
    DataOperation dataOperation;
    DateDAO dateDAO;
    DateEntity dateEntity;
    FlyDAO flyDAO;
    FlyEntity flyEntity;
    List<FlyEntity> listFlies;

    @FXML
    public void initialize(){

        userInfoEntity = new UserInfoEntity();
        dataOperation = new DataOperation();
        userInfoEntity = dataOperation.activeUser();

        if(!userInfoEntity.getLogin().trim().equals("admin")) {
            adminPane.setDisable(true);
        }
        else{
            dateEntity = new DateEntity();
            dateDAO = new DateDAO();
            flyDAO = new FlyDAO();
           //List<String> listDate = new ArrayList<>();
            List<DateEntity> dateEntities = new ArrayList<>();
            dateEntities = dateDAO.dateList();

            choiceBox.setOnAction((event) -> { // inicjalizacja choiceBox
                int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
                Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            });

            flyChoiceBox.setOnAction((event) -> { // inicjalizacja choiceBox
                int selectedIndex = flyChoiceBox.getSelectionModel().getSelectedIndex();
                Object selectedItem = flyChoiceBox.getSelectionModel().getSelectedItem();
            });

            List<FlyEntity> listFlies  = new ArrayList<>();

            for(FlyEntity flyEntity:flyDAO.getAllFlies()){
                listFlies.add(flyEntity);
                flyChoiceBox.getItems().add(String.valueOf(flyEntity.getIdFly()).trim());
            }

            for(DateEntity dateEntity:dateEntities){
                choiceBox.getItems().add(dateEntity.getIdDate()+":"+dateEntity.getDate());
            }
        }
    } //function initialize start values

    public void selectFly(){

        listFlies = new ArrayList<>();
        flyDAO = new FlyDAO();

        for(FlyEntity flyEntity:flyDAO.getAllFlies()){
            listFlies.add(flyEntity);
        }

        String selectionFly = (String)flyChoiceBox.getSelectionModel().getSelectedItem();

        System.out.println(selectionFly);

        countryUserField.setText(listFlies.get(Integer.parseInt(selectionFly.trim())-1).getCountry());
        cityUserField.setText(listFlies.get(Integer.parseInt(selectionFly.trim())-1).getCity());
        costUserField.setText(String.valueOf(listFlies.get(Integer.parseInt(selectionFly.trim())-1).getCost()));

    } //select fly
    public void setDate(){
        dateDAO = new DateDAO();
        LocalDate localDate = dateValuePicker.getValue();
        Date date = Date.valueOf(localDate);
        dateDAO.addDateToSys(date);
    } //set date

    public void addFly(){

        boolean value = true;
        DateEntity dateEntity = new DateEntity();
        flyDAO = new FlyDAO();

        String selectionDate = (String)choiceBox.getSelectionModel().getSelectedItem();
        String tabSelectionDate[] = selectionDate.split(":");

        dateEntity.setIdDate(Integer.parseInt(tabSelectionDate[0].trim()));
        dateEntity.setDate(Date.valueOf(tabSelectionDate[1].trim()));

        if(!availableButton.isSelected()){
            value = false;
        }

        System.out.println(countryField.getText());
        System.out.println(cityField.getText());
        System.out.println(Double.parseDouble(costField.getText()));
        System.out.println(value);
        System.out.println(dateEntity);
        System.out.println(dateEntity.getDate());
        System.out.println(dateEntity.getIdDate());
        flyDAO.setFly(countryField.getText(),
                cityField.getText(),
                Double.parseDouble(costField.getText()),
                value,dateEntity);
    } // add fly

    public void addReservation(){
        ReservationEntity reservationEntity = new ReservationEntity();
        UsersEntity usersEntity = new UsersEntity();
        UsersDAO usersDAO = new UsersDAO();
        UsersInfoDAO usersInfoDAO = new UsersInfoDAO();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        FlyEntity flyEntity = new FlyEntity();
        PromotionEntity promotionEntity = new PromotionEntity();
        ReservationDAO reservationDAO = new ReservationDAO();
        PromotionDAO promotionDAO = new PromotionDAO();

        List<UsersEntity> usersListEntities = new ArrayList<>();
        List<PromotionEntity> promotionListEnitities = new ArrayList<>();
        promotionListEnitities = promotionDAO.getAllPromotion();

        int id = usersInfoDAO.getUser().get(0).getIdUser();

        usersListEntities = usersDAO.getAllUsers();

        for(int i=0;i<id;i++){
            for(UsersEntity user:usersListEntities){
                usersEntity = user;
            }
        }

        listFlies = new ArrayList<>();
        flyDAO = new FlyDAO();

        for(FlyEntity flyEntity2:flyDAO.getAllFlies()){
            listFlies.add(flyEntity);
        }

        String selectionFly = (String)flyChoiceBox.getSelectionModel().getSelectedItem();

        flyEntity.setCountry(listFlies.get(Integer.parseInt(String.valueOf(selectionFly).trim())-1).getCountry());
        flyEntity.setCity(listFlies.get(Integer.parseInt(String.valueOf(selectionFly).trim())-1).getCity());
        flyEntity.setCost(listFlies.get(Integer.parseInt(String.valueOf(selectionFly).trim())-1).getCost());
        flyEntity.setAvailable(listFlies.get(Integer.parseInt(String.valueOf(selectionFly).trim())-1).isAvailable());
        flyEntity.setDateEntity(listFlies.get(Integer.parseInt(String.valueOf(selectionFly).trim())-1).getDateEntity());

        System.out.println(selectionFly);

        for(PromotionEntity promotion:promotionListEnitities){
            promotionEntity.setIdPromotion(promotion.getIdPromotion());
            promotionEntity.setPromotion(promotion.getPromotion());
        }

        reservationDAO.addReservation(usersEntity,flyEntity,promotionEntity,3000);

    } // add Reservation


}
