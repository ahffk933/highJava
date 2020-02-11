package exam_0129;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class controll_controller implements Initializable{

	
	@FXML RadioButton male;
	@FXML RadioButton fm;
	@FXML CheckBox travel;
	@FXML CheckBox rdbook;
	@FXML CheckBox hiking;
	@FXML CheckBox badook;
	@FXML CheckBox changgi;
	@FXML CheckBox game;
	@FXML CheckBox bdmin;
	@FXML CheckBox tennis;
	@FXML Button sel;
	@FXML TextArea memo;
	@FXML Label gender;
	@FXML TextField nameResult;
	
	//toggleGroup객체 생성(성별 radio)
	private ToggleGroup toggroup = new ToggleGroup();
	
	//checkBox배열 선언(hobby)
	private CheckBox[] chkbox;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		male.setToggleGroup(toggroup);
		male.setUserData("남자");
		male.setSelected(true);
		
		fm.setToggleGroup(toggroup);
		fm.setUserData("여자");
		
		
		//체크박스 배역에 체크박스들 넣기
		chkbox = new CheckBox[] {travel, rdbook, hiking, badook, changgi, game, bdmin, tennis};
		
		
		
	}


	@FXML public void show(ActionEvent event) {
		// 유저데이터 취미
				String hobby = "";
				for(int i = 0; i < chkbox.length; i++) {
					if(chkbox[i].isSelected()) { 
						hobby += chkbox[i].getText() + " ";
					}
				}
				
				// 유저데이터 이름
				String name = nameResult.getText();
				
				// 유저데이터 성별
				if(toggroup.getSelectedToggle().getUserData() != null) {
					String gender = toggroup.getSelectedToggle().getUserData().toString();
					
				memo.setText("이름 : " + name + "\n");
				memo.appendText("성별 : " + gender + "\n");
				memo.appendText("취미 : " + hobby + "\n");
				
				}
	}

	

}
