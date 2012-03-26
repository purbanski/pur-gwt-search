package pur.gwtplatform.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pur.gwtplatform.samples.model.Data;

@Path("/mp/")
public class DataRest {

	@GET
	@Produces("application/xml")
	@Path("search/{query}")
	public Data search(@PathParam("query") String query) {

		return new Data("id", "value");
	}

	@GET
	@Produces("application/json")
	@Path("get")
	public ListeData  get() {
		ListeData ld = new ListeData();
		List<Data> liste = new ArrayList<Data>();
		for (int i = 0; i < 10; i++) {
			Data data = new Data("test" + String.valueOf(i), "toto"+String.valueOf(i));
			liste.add(data);
		}
		ld.setKeys(liste);
		return ld;
	}
}
