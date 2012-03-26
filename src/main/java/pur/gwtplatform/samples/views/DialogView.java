package pur.gwtplatform.samples.views;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

public class DialogView extends PopupViewImpl implements IDialogView {

	private final PopupPanel widget;

	public interface Binder extends UiBinder<PopupPanel, DialogView> {
	}

	@Inject
	public DialogView(Binder binder, EventBus eventBus) {
		super(eventBus);
		widget = binder.createAndBindUi(this);
	}

	@Override
	public PopupPanel asWidget() {
		return widget;
	}

	@UiField
	Button validerButton;
	@UiField
	Button annulerButton;
	@UiField TextBox keyStock;
	@UiField TextBox dataStock;	

	public Button getValiderButton() {
		return validerButton;
	}

	public Button getAnnulerButton() {
		return annulerButton;
	}
	
	public TextBox getKeyStock() {
		return keyStock;
	}

	public TextBox getDataStock() {
		return dataStock;
	}

	@Override
	public void setNavigationPlace(String placeName) {
		// TODO Auto-generated method stub

	}
}
