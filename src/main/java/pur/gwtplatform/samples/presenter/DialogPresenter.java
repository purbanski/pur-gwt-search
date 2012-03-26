package pur.gwtplatform.samples.presenter;

import pur.gwtplatform.samples.events.UpdateLocalStorageEvent;
import pur.gwtplatform.samples.model.Data;
import pur.gwtplatform.samples.views.IDialogView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;

public class DialogPresenter extends PresenterWidget<IDialogView> implements NavigationHandler {
	private EventBus eventBus;

	@Inject
	public DialogPresenter(final EventBus eventBus, final IDialogView view) {
		super(eventBus, view);
		this.eventBus = eventBus;
	}

	@Override
	protected void onBind() {
		super.onBind();
		enregistrerBoutonAnnuler();
		enregistrerBoutonValider();
	}

	private void enregistrerBoutonValider() {
		registerHandler(getView().getValiderButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String key = getView().getKeyStock().getText();
				String data = getView().getDataStock().getText();
				Storage stockstore = Storage.getLocalStorageIfSupported();
				if (stockstore != null) {
					stockstore.setItem(key, data);
				}

				eventBus.fireEvent(new UpdateLocalStorageEvent(new Data(key, data)));				
			}
		}));
	}

	private void enregistrerBoutonAnnuler() {
		registerHandler(getView().getAnnulerButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getView().asWidget().hide();
			}
		}));
	}
	@Override
	  public void onReveal() {
	    super.onReveal();
	     }
	@Override
	public void onNavigation(NavigationEvent navigationEvent) {
		getView().setNavigationPlace(navigationEvent.getRequest().getNameToken());

	}
}
