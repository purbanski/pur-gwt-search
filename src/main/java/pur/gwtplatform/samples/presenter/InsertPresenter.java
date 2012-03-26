package pur.gwtplatform.samples.presenter;

import pur.gwtplatform.samples.events.UpdateLocalStorageEvent;
import pur.gwtplatform.samples.model.Data;
import pur.gwtplatform.samples.modules.NameTokens;
import pur.gwtplatform.samples.views.IInsertView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class InsertPresenter extends Presenter<IInsertView, InsertPresenter.MyProxy> {

	private EventBus eventBus;
	private final PlaceManager placeManager;

	@ProxyCodeSplit
	@NameToken(NameTokens.msg)
	public interface MyProxy extends ProxyPlace<InsertPresenter> {
	}

	@Inject
	public InsertPresenter(EventBus eventBus, IInsertView view, MyProxy proxy, PlaceManager placeManager,
			DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		registerHandler(getView().getInsertButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String key = getView().getKeyStock().getText();
				String data = getView().getDataStock().getText();
				Storage stockstore = Storage.getLocalStorageIfSupported();
				if (stockstore != null) {
					stockstore.setItem(key, data);
				}

				eventBus.fireEvent(new UpdateLocalStorageEvent(new Data(key, data)));
				// on retourne sur le main
				placeManager.revealPlace(new PlaceRequest(NameTokens.main));
			}
		}));

		registerHandler(getView().getCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getView().getKeyStock().setText("");
				getView().getDataStock().setText("");
				// on retourne sur le main
				placeManager.revealPlace(new PlaceRequest(NameTokens.main));
			}
		}));

	}

	@Override
	protected void onReveal() {
		super.onReveal();

	}
}
