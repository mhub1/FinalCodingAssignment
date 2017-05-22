package rocket.app.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	@FXML TextField txtCreditScore;
	@FXML TextField txtMortgageAmt;
	@FXML Label lblMortgagePayment;
	@FXML TextField txtIncome;
	@FXML TextField txtExpenses;
	@FXML TextField txtDownPayment;
	@FXML TextField txtHouseCost;
	@FXML ComboBox<String> cmbTerm;
	@FXML Label lblCreditScore;
	@FXML Label lblIncome;
	@FXML Label lblExpenses;
	@FXML Label lblDownPayment;
	@FXML Label lblHouseCost;
	@FXML Label lblTerm;
	@FXML Label lblPayment;
	private TextField txtNew;
	
	private MainApp mainApp;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	ObservableList<String> LoanTerms = FXCollections.observableArrayList("15 years", "30 years");
	@FXML
	private void initialize(){
		cmbTerm.setItems(LoanTerms);
	}
	
	@FXML
	public void btnCalculatePayment(ActionEvent event) throws RateException
	{
		Object message = null;		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		try {
			lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
			if (cmbTerm.getStyle() == "15 years") {
				lq.setiTerm(15);
			}else {
				lq.setiTerm(30);
			}
		lq.setdRate(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setiTerm(Integer.parseInt(cmbTerm.getStyle()));
		lq.setIncome(Double.parseDouble((txtIncome.getText())));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		}
		catch(RateException e) {
			lblMortgagePayment.setText("There is no rate");
		}
		a.setLoanRequest(lq);
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		NumberFormat formatter = new DecimalFormat("#0.00");
		
		double PITI = Double.parseDouble(formatter.format((String.valueOf((lRequest.getIncome()- lRequest.getExpenses()) * 0.28 ))));
		double pmt = lRequest.getdPayment();
		int result = (int)(pmt - PITI) * 100;
		if(result > 0) {
			lblMortgagePayment.setText("House Costs too much");
		} else {
			lblMortgagePayment.setText("This house is affordable!");
		}
	}
}
