package pur.gwtplatform.samples.presenter;

import pur.gwtplatform.samples.events.UpdateLocalStorageEvent;
import pur.gwtplatform.samples.events.UpdateLocalStorageEvent.InsertCompleteHandler;
import pur.gwtplatform.samples.views.IDeleteDialogView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

public class DeleteDialogPresenter extends PresenterWidget<IDeleteDialogView> {
	private EventBus eventBus;
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

	@Inject
	public DeleteDialogPresenter(final EventBus eventBus, final IDeleteDialogView view) {
		super(eventBus, view);
		this.eventBus = eventBus;
	}

	@Override
	protected void onBind() {
		super.onBind();
		enregistrerBoutonAnnuler();
		gererAutoCompleteBox();
		gererEvenements();
	}

	private void enregistrerBoutonAnnuler() {
		registerHandler(getView().getAnnulerButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getView().asWidget().hide();
			}
		}));
	}

	private void gererAutoCompleteBox() {
		refreshAutoCompBox();
		registerHandler(getView().getAutoCompleteBox().addSelectionHandler(
				new SelectionHandler<SuggestOracle.Suggestion>() {
					@Override
					public void onSelection(SelectionEvent<Suggestion> event) {
						String value = event.getSelectedItem().getReplacementString();
						Storage stockstore = Storage.getLocalStorageIfSupported();
						if (stockstore != null) {
							stockstore.removeItem(value);
							eventBus.fireEvent(new UpdateLocalStorageEvent());
							// refreshAutoCompBox();
						}
					}
				}));
	}

	private void refreshAutoCompBox() {
		oracle = (MultiWordSuggestOracle) getView().getAutoCompleteBox().getSuggestOracle();
		Storage stockstore = Storage.getLocalStorageIfSupported();
		if (stockstore != null) {
			oracle.clear();
			for (int i = 0; i < stockstore.getLength(); i++) {
				String key = stockstore.key(i);
				oracle.add(key);
			}
		}
	}

	private void gererEvenements() {
		registerHandler(eventBus.addHandler(UpdateLocalStorageEvent.TYPE, new InsertCompleteHandler() {
			public void onInsertComplete(UpdateLocalStorageEvent event) {
				refreshAutoCompBox();
			}
		}));
	}
}
