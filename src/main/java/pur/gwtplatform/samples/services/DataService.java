package pur.gwtplatform.samples.services;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import pur.gwtplatform.samples.events.UpdateLocalStorageEvent;
import pur.gwtplatform.samples.model.Data;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;

public class DataService {
	// private EventBus eventBus;
	JSONArray array = new JSONArray();
	List<Data> liste   = new ArrayList<Data>();
	// public DataService(EventBus eventBus) {
	// this.eventBus = eventBus;
	// }
	public DataService() {
	}

	public List<Data> getData() {

		Resource resource = new Resource("http://localhost:8080/pur/data/mp/get");
		resource.get().send(new JsonCallback() {
			public void onSuccess(Method method, JSONValue response) {
				JSONObject keys = response.isObject().get("keys").isObject();
				array = keys.get("keys").isArray();
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsObject = array.get(i).isObject();
					String value = jsObject.get("value").isString().stringValue();
					String key = jsObject.get("key").isString().stringValue();
					liste.add(new Data(key, value));
				}
				// eventBus.fireEvent(new UpdateLocalStorageEvent());
			}

			public void onFailure(Method method, Throwable exception) {
				Window.alert("onFailure: " + exception);
			}
		});
		return liste;
	}
}
