package zip;

import java.net.URL;
import java.util.ResourceBundle;

import zipService.zipServiceImpl;
import zipVO.zipVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class zipController implements Initializable{
	zipServiceImpl service = zipService.zipServiceImpl.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<zipVO, String> zip;

    @FXML
    private TableColumn<zipVO, String> gugun;

    @FXML
    private TableColumn<zipVO, String> dong;
    
    @FXML
    private TableColumn<zipVO, String> city;
    
    @FXML
    private TableColumn<zipVO, String> staddr;
    //-----------------------------------------------------------
    @FXML
    private Button search;
    private int chk;

    @FXML
    private ComboBox<String> villnm;

    @FXML
    private TextField txtfd;

    @FXML
    private TableView<zipVO> zipTable;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	ObservableList<String> list = FXCollections.observableArrayList("우편번호", "동이름");
    	villnm.setItems(list);  //콤보박스를 내리면 list에 있는 우편번호, 동이름이 나옴
    	villnm.setValue("동이름");  //처음에 보여지는 값
    	
    	zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
    	dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
    	staddr.setCellValueFactory(new PropertyValueFactory<>("staddr"));
    	
		
    	zipVO vo = new zipVO();
    	vo.setZipcode(txtfd.getText());
		
	}
    @FXML
    void goSearch(ActionEvent event) {


		if(villnm.getValue().equals("동이름")) {
			String dong = txtfd.getText();
			
			ObservableList<zipVO> list = FXCollections.observableArrayList(service.getDong(dong));
			
			zipTable.setItems(list);
			
		}else {
			String zip = txtfd.getText();
			
			ObservableList<zipVO> list = FXCollections.observableArrayList(service.getZip(zip));
			
			zipTable.setItems(list);
		}
		
	}
}

