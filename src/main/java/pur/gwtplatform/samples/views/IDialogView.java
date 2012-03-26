package pur.gwtplatform.samples.views;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.mvp.client.PopupView;

public interface IDialogView extends PopupView {

	Button getValiderButton();

	Button getAnnulerButton();

	TextBox getKeyStock();

	TextBox getDataStock();

	PopupPanel asWidget();

	void setNavigationPlace(String placeName);
}