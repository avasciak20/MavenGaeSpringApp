package pl.lukaz.poligon.gae.workservice.web.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class GoogleDatastoreKeyEditor extends PropertyEditorSupport {

	private static Log log=LogFactory.getLog(GoogleDatastoreKeyEditor.class);
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		log.error("setAsText: "+text);
		if(text!=null && text.length()>0){
			Key key=null;
			key=KeyFactory.stringToKey(text);
			log.error("setAsText: got key "+key);
			setValue(key);
		}else{
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Key key=(Key) getValue();
		log.error("getAsText: "+key);
		
		if(key!=null){
			log.error("getAsText: got key "+KeyFactory.keyToString(key));
			return KeyFactory.keyToString(key);
		}
		else{
			log.error("getAsText: no key");
			return "";
		}
	}
	
}
