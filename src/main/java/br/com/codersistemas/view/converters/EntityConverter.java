package br.com.codersistemas.view.converters;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 * <p:selectOneMenu value="#{tag1Action.filter.gitec}" converter="#{entityConverter}" styleClass="wd200">
 * 	<f:selectItem itemLabel="Selecione..." itemValue="#{null}"/>
 *  <f:selectItems value="#{tag1Action.gitecs}" var="g" itemLabel="#{g.nome}" itemValue="#{g}"/>
 * </p:selectOneMenu>
 * @author f768557
 */
@Named
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	private static Map<Object, String> entities = new WeakHashMap<Object, String>();

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		synchronized (entities) {
			if (!entities.containsKey(entity)) {
				String uuid = UUID.randomUUID().toString();
				entities.put(entity, uuid);
				return uuid;
			} else {
				return entities.get(entity);
			}
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
		for (Entry<Object, String> entry : entities.entrySet()) {
			if (entry.getValue().equals(uuid)) {
				return entry.getKey();
			}
		}
		return null;
	}

}