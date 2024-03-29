package pur.gwtplatform.samples.services;

import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import pur.gwtplatform.samples.events.DicoCompleteEvent;
import pur.gwtplatform.samples.events.SearchCompleteEvent;
import pur.gwtplatform.samples.model.Data;
import pur.gwtplatform.samples.model.ElementResult;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;


public class DataService {
	@Inject
	private EventBus eventBus;
	
	private JSONArray array = new JSONArray();

	public DataService() {
	}

	public void getDataDico(final List<Data> liste, String query) {
		Resource resource = new Resource("/rest/mp/dico/" +query + ".json");
		resource.get().send(new JsonCallback() {
			public void onSuccess(Method method, JSONValue response) {
				JSONObject keys = response.isObject().get("listIndexDico").isObject();
				array = keys.get("result").isArray();
				for (int i = 0; i < array.size(); i++) {
					String value = array.get(i).isString().stringValue();
					String key = value;
					liste.add(new Data(key, value));
				}
				eventBus.fireEvent(new DicoCompleteEvent());
			}
			public void onFailure(Method method, Throwable exception) {
				Window.alert("onFailure: " + exception);
			}
		});
	}
	
	public void getDataIndex(final List<ElementResult> liste, String query) {
		Resource resource = new Resource("/rest/mp/search/" +query + ".json");		
		resource.get().send(new JsonCallback() {
			public void onSuccess(Method method, JSONValue response) {
				JSONObject keys = response.isObject().get("listSearchResult").isObject();
				array = keys.get("listSearchResult").isArray();
				for (int i = 0; i < array.size(); i++) {
					ElementResult elementResult = new ElementResult();
					JSONObject jsObject = array.get(i).isObject();
					elementResult.setL(jsObject.get("highs").isString().stringValue());
					elementResult.setK(jsObject.get("k").isString().stringValue());				
					liste.add(elementResult);
				}
				eventBus.fireEvent(new SearchCompleteEvent());
			}
			public void onFailure(Method method, Throwable exception) {
				Window.alert("onFailure: " + exception);
			}
		});
	}
}
