package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;

public class PagingController implements Initializable{

	@FXML TableView<MemberVO> tableView;
	@FXML TableColumn<MemberVO, String> id;
	@FXML TableColumn<MemberVO, String> name;
	@FXML TableColumn<MemberVO, String> addr;
	@FXML Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<MemberVO> allTableData, currentPageData;

	@Override   //데이터 출력하려면 필요!
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		addr.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		//전체 테이블 데이터 설정
		allTableData = FXCollections.observableArrayList();
		
		allTableData.add(new MemberVO("1", "hanna", "daejeon"));
		allTableData.add(new MemberVO("2", "janna", "daejeon"));
		allTableData.add(new MemberVO("3", "sona", "daejeon"));
		allTableData.add(new MemberVO("4", "nami", "daejeon"));
		allTableData.add(new MemberVO("5", "olaf", "daejeon"));
		allTableData.add(new MemberVO("6", "kled", "daejeon"));
		allTableData.add(new MemberVO("7", "kaisa", "daejeon"));
		allTableData.add(new MemberVO("8", "jinx", "daejeon"));
		allTableData.add(new MemberVO("9", "garen", "daejeon"));
		allTableData.add(new MemberVO("10", "lux", "daejeon"));
		allTableData.add(new MemberVO("11", "blitz", "daejeon"));
		allTableData.add(new MemberVO("12", "morgana", "daejeon"));
		allTableData.add(new MemberVO("13", "zed", "daejeon"));
		allTableData.add(new MemberVO("14", "leesin", "daejeon"));
		
		itemsForPage = 5;   //한페이지에 보여줄 항목 수 설정
		int totPageCount = allTableData.size()%itemsForPage ==0 
				? allTableData.size()/itemsForPage : allTableData.size()/itemsForPage + 1;
		pagination.setPageCount(totPageCount);  //전체 페이지 수 설정
		
		pagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage -1;
				tableView.setItems(getTableViewData(from, to));
				return tableView;
			}
		});
		
	}
	/**
	 * TableView에 채워줄 데이터를 가져오는 메서드
	 * @param from
	 * @param to
	 * @return
	 */
	protected ObservableList<MemberVO> getTableViewData(int from, int to){
		currentPageData = FXCollections.observableArrayList();
		
		int totSize = allTableData.size();
		for(int i= from; i<=to && i<totSize; i++) {
			currentPageData.add(allTableData.get(i));
			
		}
		return currentPageData;
	}

	
	
	
}