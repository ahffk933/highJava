package exam_0130;


import java.lang.reflect.Member;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class Mem_controller implements Initializable{

	@FXML TextField mem_id;
	@FXML TextField mem_nm;
	@FXML TextField mem_tel;
	@FXML TextField mem_addr;
	
	@FXML TableColumn<Member, String> res_id;
	@FXML TableColumn<Member, String> res_nm;
	@FXML TableColumn<Member, String> res_tel;
	@FXML TableColumn<Member, String> res_addr;
	
	@FXML Button add;
	@FXML Button edit;
	@FXML Button del;
	@FXML Button confirm;
	@FXML Button cancel;
	@FXML TableView<Member> tv;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Member> data = FXCollections.observableArrayList(
				new Member("hanna_id", "hannag", "7788987702", "542rochester") );
		
		
		tv.setItems(data);
		
		//컬럼연결
		res_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		res_nm.setCellValueFactory(new PropertyValueFactory<>("name"));
		res_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		res_addr.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
	
		
		ok();
				

		//테이블 뷰 클릭시
		tv.setOnMouseClicked(e -> {
			
			Member mem = tv.getSelectionModel().getSelectedItem();
			
			mem_id.setText(mem.getId());
			mem_nm.setText(mem.getName());
			mem_tel.setText(mem.getTel());
			mem_addr.setText(mem.getAddr());
		});
		
		//추가 클릭시
		add.setOnAction(e->{
			mem_id.setEditable(true);
			mem_nm.setEditable(true);
			mem_tel.setEditable(true);
			mem_addr.setEditable(true);
			
			edit.setDisable(true);
			del.setDisable(true);
			confirm.setDisable(false);
			cancel.setDisable(false);
			
		});
		
		//수정
		edit.setOnAction(e->{
			mem_id.setEditable(true);
			mem_nm.setEditable(true);
			mem_tel.setEditable(true);
			mem_addr.setEditable(true);
			
			add.setDisable(true);
			del.setDisable(true);
			confirm.setDisable(false);
			cancel.setDisable(false);
		});
		//삭제
		del.setOnAction(e->{
			
			add.setDisable(true);
			edit.setDisable(true);
			
			confirm.setDisable(false);
			cancel.setDisable(false);
		});		
	
		
		
		confirm.setOnAction(e->{
			if(!add.isDisabled()) {
				if(mem_id.getText().isEmpty() || mem_nm.getText().isEmpty() || mem_tel.getText().isEmpty() || mem_addr.getText().isEmpty()) {
					errMsg("error", "theres no data");
					return;
				}
				data.add(new Member(mem_id.getText(), mem_nm.getText(), mem_tel.getText(), mem_addr.getText()));
				
				mem_id.clear();
				mem_nm.clear();
				mem_tel.clear();
				mem_addr.clear();
				
			}else if(!del.isDisable()) {
				if(tv.getSelectionModel().isEmpty()) {
					errMsg("error", "try again");
				}
				data.remove(tv.getSelectionModel().getSelectedIndex());
				
			}else if(!edit.isDisable()) {
				if(tv.getSelectionModel().isEmpty()) {
					errMsg("error", "select one for edit");
				}
				data.set(tv.getSelectionModel().getSelectedIndex(), 
						new Member(mem_id.getText(), mem_nm.getText(), mem_tel.getText(), mem_addr.getText()));
			}
				
			});
		//취소
		cancel.setOnAction(e->{
			add.setDisable(false);
			edit.setDisable(false);
			del.setDisable(false);
			
			confirm.setDisable(true);
			cancel.setDisable(true);
			
			mem_id.setEditable(false);
			mem_nm.setEditable(false);
			mem_tel.setEditable(false);
			mem_addr.setEditable(false);
			
		});
		
	}
	
	public void ok() {
		mem_id.setEditable(false);
		mem_nm.setEditable(false);
		mem_tel.setEditable(false);
		mem_addr.setEditable(false);
		
		confirm.setDisable(true);
		cancel.setDisable(true);
	}
	

	public void errMsg(String errTxt, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("error");
		errAlert.setHeaderText(errTxt);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	public void infoMsg(String infoTxt, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(infoTxt);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	
	
	public class Member{
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		
		public Member(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}



		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getTel() {
			return tel;
		}


		public void setTel(String tel) {
			this.tel = tel;
		}


		public String getAddr() {
			return addr;
		}


		public void setAddr(String addr) {
			this.addr = addr;
		}
		
		
		
	}

}
